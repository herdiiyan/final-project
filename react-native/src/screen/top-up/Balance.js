import React, { Component } from "react";
import { Alert, Image, StyleSheet, Text, View } from "react-native";
import * as Animatable from 'react-native-animatable';
import { Button as ButtonEl } from "react-native-elements"
import { theme } from "../../constants";
import { Block } from "../../components";
import {
  Container,
  Header,
  Content,
  Icon,
  Form,
  Button,
  Input,
  Toast
} from "native-base";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { putBalance } from "../../actions/CustomerAccount";

class Balance extends Component {
  constructor(props) {
    super(props);

    const { navigation } = this.props;
    this.state = {
        accountNumber: navigation.getParam("accountNumber"),
        accountBalance: 0,
    };
  }

  componentDidUpdate(prevProps, prevState) {
    const { error } = this.props;
    if (error && prevProps.error !== error) {
      Toast.show({
        text: error.message,
        buttonText: 'Ok',
        type: "danger",
        duration: 5000
      })
    }
  }

  saveUpdate() {
    const { accountNumber, accountBalance } = this.state;
    this.props.putBalance(accountNumber, {
        accountBalance: accountBalance
    })

    const { Balance } = this.props;
    var data = Balance || {}; 
    if (data != this.state) {
        this.props.navigation.navigate("TopUp");
    }
  }

  render() {
    console.log(this.props.Balance);

    return (
      <Container >
        <Header>
            <Button
                transparent
                style={styles.iconBack}
                onPress={() => this.props.navigation.navigate("TopUp")}
            >
                <Icon name="angle-left" type="FontAwesome5" />
            </Button>
            <Image
                source={require("../../../assets/image.png")}
                style={{ width: 105, height: 33, top: 12 }}
            />
        </Header>
        <Content>
          <Block flex={false} row style={styles.tabs}>
              <Text style={styles.textHeader}>Top up Balance {this.props.navigation.getParam("accountNumber")}</Text>
          </Block>
          <Form>
              <Input style={styles.textInput} onChangeText={(accountBalance) => this.setState({accountBalance})} placeholder="Rp."/>
              <Animatable.View animation="flash">
                <ButtonEl transparent containerStyle={styles.button} onPress={ () => {       
                  Alert.alert(
                      'Warning!',
                      ' Are you sure you want to Top-Up ? ',
                      [                              
                        {text: 'Cancel', onPress: () => console.log('Cancel Pressed'), style: 'cancel'},
                        {text: 'Top-Up', onPress: () => {        
                          this.saveUpdate();
                        }},
                      ],
                      { cancelable: true }
                  )}} title='Top-Up' />
              </Animatable.View>
          </Form>
        </Content>
      </Container>
    );
  }
}

function mapStateToProps(state) {
  return {
    loading: state.putBalance.loading,
    Balance: state.putBalance.data,
    error: state.putBalance.error
  };
}

function matchDispatchToProps(dispatch) {
  return bindActionCreators({ putBalance }, dispatch);
}

export default connect(
  mapStateToProps,
  matchDispatchToProps
)(Balance);

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
    },
    textInput: {
      height: 50,
      borderRadius: 25,
      borderWidth: 0.5,
      marginHorizontal: 20,
      paddingLeft: 10,
      marginVertical: 5,
      borderColor: 'rgba(0,0,0,0.2)'
    },
    button: {
      marginRight: 30,
      marginLeft: 30,
    }
  });
