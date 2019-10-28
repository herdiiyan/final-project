import React, { Component } from "react";
import { StyleSheet, Image, RefreshControl } from "react-native";
import * as Animatable from "react-native-animatable";
import { Block } from '../../components';
import { theme } from '../../constants';
import { connect } from "react-redux";
import CurrencyFormatter from "../../constants/CurrencyFormatter"
import {
  Container,
  Header,
  Content,
  ListItem,
  Body,
  Right,
  Button,
  Icon,
  Text,
  Toast
} from "native-base";
import { getAccountByCIF } from "../../actions/CustomerAccount";
import { bindActionCreators } from "redux";
class CustomerAccountScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      customerCif: this.props.navigation.getParam("cif"),
      accountNumber: null,
      accountBalance: null,
      createdAt: null,
    };
  }

  componentDidMount() {
    this.reload();
  }
  
  async reload() {
    const data = await this.props.getAccountByCIF(this.props.navigation.getParam("cif"));
    return data
  }

  
  showDetail(accountNumber) {
    if (accountNumber != null) {
      this.props.navigation.navigate("Loan", { accountNumber:accountNumber });
    } else {
      Toast.show({
        text: "Cif Already Exist",
        buttonText: 'Ok',
        type: "danger",
        duration: 5000,
        position: 'top'
      })
    }
  }

  renderListItem(data, index) {
    return (
      <ListItem thumbnail style={styles.list} key={"item-" + index} onPress={() => this.showDetail(data.accountNumber)}>
            <Body>
              <Text note numberOfLines={1}>Number Account : {data.accountNumber}</Text>
              <Text note numberOfLines={1}>Balance                 : {CurrencyFormatter(data.accountBalance)}</Text>
              <Text note numberOfLines={1}>Create Date          : {data.createdAt}</Text>
            </Body>
            <Right>
              <Button transparent onPress={() => this.showDetail(data.accountNumber)}>
                <Animatable.View animation="fadeInLeft">
                <Icon name="angle-right" type="FontAwesome5" />
                </Animatable.View>
              </Button>
            </Right>
          </ListItem>
    );
  }
  
  render() {
    return (
      <Container>
        <Header>
          <Button
            transparent
            style={styles.iconBack}
            onPress={() => this.props.navigation.navigate('Customer')}
          >
            <Icon name="angle-left" type="FontAwesome5" />
          </Button>
          <Image source={require('../../../assets/image.png')} style={{width: 105, height: 33, top: 12}}/>
        </Header>
        <Block flex={false} row style={styles.tabs}>
            <Text style={styles.textHeader}>Account Number {this.props.navigation.getParam("cif")}</Text>
        </Block>
        <Content padder refreshControl={<RefreshControl refreshing={this.props.loading} onRefresh={() => this.reload()}/>}>
          {this.props.data.length  ?  this.props.data.map((data, index)=>(this.renderListItem(data, index))) :<Text style={{textAlign: 'center'}}>Loading...</Text>}  
        </Content>
      </Container>
    );
  }
}

function mapStateToProps(state) {
  return {
    loading: state.getByCIF.loading,
    data: state.getByCIF.data,
    error: state.getByCIF.error,
  };
}
function matchDispatchToProps(dispatch) {
  return bindActionCreators(
    { getAccountByCIF },
    dispatch
  );
}

export default connect(
  mapStateToProps,
  matchDispatchToProps
)(CustomerAccountScreen);

const styles = StyleSheet.create({
  iconBack: {
    zIndex: 9,
    position: "absolute",
    top: 6,
    left: 5
  },
  tabs: {
    borderBottomColor: theme.colors.gray2,
    borderBottomWidth: StyleSheet.hairlineWidth,
    marginVertical: theme.sizes.base,
    marginHorizontal: theme.sizes.base * 2,
    marginBottom: 1
  },
  textHeader: {
    fontSize: 26,
    fontWeight: "bold",
    paddingBottom: 10
  },
  list: {
    marginRight: 32,
    marginLeft: 32
  }
})
