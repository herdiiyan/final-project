import React, { Component } from "react";
import { StyleSheet, Image, RefreshControl } from "react-native";
import { Block } from '../../components';
import { theme } from '../../constants';
import { connect } from "react-redux";
import {
  Container,
  Header,
  Content,
  ListItem,
  Body,
  Button,
  Icon,
  Text,
  Toast
} from "native-base";
import { findBilling } from "../../actions/BiliingAction";
import { bindActionCreators } from "redux";
class BillingScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
        loanId: this.props.navigation.getParam("loanId"),
        billingId: null,
        billingAmount: null,
        billDate: null,
        billingDueDate: null,
        billingStatus: null
    };
  }

  componentDidMount() {
    this.reload();
  }

  reload() {
    this.props.findBilling(this.props.navigation.getParam("loanId"));
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

  renderListItem(data, index) {
    return (
      <ListItem thumbnail style={styles.list} key={"item-" + index}>
        <Body>
          <Text note numberOfLines={1}>ID               : {data.billingId}</Text>
          <Text note numberOfLines={1}>Amount    : Rp. {data.billingAmount}</Text>
          <Text note numberOfLines={1}>Date          : {data.billDate}</Text>
          <Text note numberOfLines={1}>Due Date  : {data.billingDueDate}</Text>
          <Text note numberOfLines={1}>Status       : {data.billingStatus}</Text>
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
            onPress={() => this.props.navigation.navigate('Loan')}
          >
            <Icon name="angle-left" type="FontAwesome5" />
          </Button>
          <Image source={require('../../../assets/image.png')} style={{width: 105, height: 33, top: 12}}/>
        </Header>
        <Block flex={false} row style={styles.tabs}>
            <Text style={styles.textHeader}>Billing ID {this.props.navigation.getParam("loanId")}</Text>
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
    loading: state.findBilling.loading,
    data: state.findBilling.data,
    error: state.findBilling.error
  };
}
function matchDispatchToProps(dispatch) {
  return bindActionCreators(
    { findBilling },
    dispatch
  );
}

export default connect(
  mapStateToProps,
  matchDispatchToProps
)(BillingScreen);

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
