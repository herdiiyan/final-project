import React, { Component } from "react";
import { StyleSheet, Image, RefreshControl } from "react-native";
import { Button as ButtonEl } from "react-native-elements";
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
  Button,
  Icon,
  Text,
  Toast,
} from "native-base";
import { findLoan } from "../../actions/LoanAction";
import { bindActionCreators } from "redux";
class LoanScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      accountNumber: this.props.navigation.getParam("accountNumber"),
      loanId: null,
      loanAmount: null,
      loanBalance: null,
      loanStatus: null,
      openDate: null,
      dueDate: null,
      loanTenor: null,
      loanTypeCode: null,
      customerCif: null,
    };
  }

  componentDidMount() {
    this.reload();
  }

  reload() {
    this.props.findLoan(this.props.navigation.getParam("accountNumber"));
  }
  componentDidUpdate(prevProps, prevState) {
    const { error } = this.props;
    if (error && prevProps.error !== error) {
      Toast.show({
        text: error.message,
        buttonText: 'Ok',
        type: "warning",
        duration: 5000,
        position: 'top'
      })
    }
  }

  showDetail(loanId) {
    if (loanId != null) {
      this.props.navigation.navigate("Billing", { loanId:loanId });
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
      <ListItem thumbnail style={styles.list} key={"item-" + index}>
        <Body>
          <Text note numberOfLines={1}>ID                : {data.loanId}</Text>
          <Text note numberOfLines={1}>Amount      : {CurrencyFormatter(data.loanAmount)}</Text>
          <Text note numberOfLines={1}>Balance      : {CurrencyFormatter(data.loanBalance)}</Text>
          <Text note numberOfLines={1}>Status         : {data.loanStatus}</Text>
          <Text note numberOfLines={1}>Open Date  : {data.openDate}</Text>
          <Text note numberOfLines={1}>Due Date     : {data.dueDate}</Text>
          <Text note numberOfLines={1}>Tenor           : {data.loanTenor}</Text>
          <Text note numberOfLines={1}>Type Code  : {data.loanTypeCode}</Text>
          <Text note numberOfLines={1}>CIF               : {data.customerCif}</Text>
          <Animatable.View animation="flash">
            <ButtonEl title="Billing" onPress={() => this.showDetail(data.loanId)}/>
          </Animatable.View>
        </Body>
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
            onPress={() => this.props.navigation.navigate('CustomerAccount')}
          >
            <Icon name="angle-left" type="FontAwesome5" />
          </Button>
          <Image source={require('../../../assets/image.png')} style={{width: 105, height: 33, top: 12}}/>
        </Header>
        <Block flex={false} row style={styles.tabs}>
            <Text style={styles.textHeader}>Account {this.props.navigation.getParam("accountNumber")}</Text>
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
    loading: state.findLoan.loading,
    data: state.findLoan.data,
    error: state.findLoan.error
  };
}
function matchDispatchToProps(dispatch) {
  return bindActionCreators(
    { findLoan },
    dispatch
  );
}

export default connect(
  mapStateToProps,
  matchDispatchToProps
)(LoanScreen);

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
