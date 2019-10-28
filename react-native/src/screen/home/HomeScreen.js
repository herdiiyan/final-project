import React, { Component } from "react";
import { Image, ScrollView, TouchableOpacity, StyleSheet, Dimensions, Alert } from "react-native";
import { Card, Badge, Block, Text } from '../../components';
import { theme } from '../../constants';
import { Header, Container, Button, Icon } from "native-base";

export const { width } = Dimensions.get('window');

export default class HomeScreen extends Component {
  state = {
    active: 'Menu',
  }

  renderTab(tab) {
    const { active } = this.state;
    const isActive = active === tab;

    return (
      <TouchableOpacity
        key={`tab-${tab}`}
        style={[
          styles.tab,
          isActive ? styles.active : null
        ]}
      >
        <Text size={16} medium gray={!isActive} black={isActive}>
          {tab}
        </Text>
      </TouchableOpacity>
    )
  }

  Logout() {
    Alert.alert(
      'Warning!',
      ' Are you sure you want to Logout ? ',
      [                              
        {text: 'Cancel', onPress: () => console.log('Cancel Pressed'), style: 'cancel'},
        {text: 'Logout', onPress: () => {        
          this.props.navigation.navigate("Login")
        }},
      ],
      { cancelable: true }
    ); 
  }

  render() {
    const tabs = ['Menu'];

    return (
      <Container>
        <Header>
        <Button
            transparent
            style={styles.iconBack}
            onPress={() => this.Logout()}
          >
            <Icon name="logout" type="SimpleLineIcons" />
          </Button>
          <Image source={require('../../../assets/image.png')} style={{width: 105, height: 33, top: 12}}/>
        </Header>
        <Block>
          <Block flex={false} row center space="between" style={styles.header}>
            <Text header>Welcome to Enigma Bank</Text>
          </Block>
          <Block flex={false} row style={styles.tabs}>
            {tabs.map(tab => this.renderTab(tab))}
          </Block>

          <ScrollView
            showsVerticalScrollIndicator={false}
            style={{ paddingVertical: theme.sizes.base * 2}}
          >
            <Block flex={false} row space="between" style={styles.blockMenu}>
                <TouchableOpacity onPress={() => this.props.navigation.navigate('Customer')}>
                  <Card center middle shadow style={styles.bodyMenu}>
                    <Badge margin={[0, 0, 15]} size={50} color="rgba(0, 162, 255, 1)">
                      <Image source={require('../../../assets/icons/customer.png')} />
                    </Badge>
                    <Text medium height={20}>Customer</Text>
                  </Card>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => this.props.navigation.navigate('TopUp')}>
                  <Card center middle shadow style={styles.bodyMenu}>
                    <Badge margin={[0, 0, 15]} size={50} color="rgba(0, 162, 255, 1)">
                      <Image source={require('../../../assets/icons/credit.png')} />
                    </Badge>
                    <Text medium height={20}>Top-Up</Text>
                  </Card>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => this.props.navigation.navigate('QR')}>
                  <Card center middle shadow style={styles.bodyMenu}>
                    <Badge margin={[0, 0, 15]} size={50} color="rgba(0, 162, 255, 1)">
                      <Image source={require('../../../assets/icons/qr-code.png')} />
                    </Badge>
                    <Text medium height={20}>QR</Text>
                  </Card>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => this.props.navigation.navigate('About')}>
                  <Card center middle shadow style={styles.bodyMenu}>
                    <Badge margin={[0, 0, 15]} size={50} color="rgba(0, 162, 255, 1)">
                      <Image source={require('../../../assets/icons/transaction.png')} />
                    </Badge>
                    <Text medium height={20}>About</Text>
                  </Card>
                </TouchableOpacity>
            </Block>
          </ScrollView>
        </Block>
      </Container>
    )
  }
}

const styles = StyleSheet.create({
  header: {
    paddingHorizontal: theme.sizes.base * 2,
    justifyContent: 'center',
    top: 20,
    marginBottom: 20
  },
  tabs: {
    borderBottomColor: theme.colors.gray2,
    borderBottomWidth: StyleSheet.hairlineWidth,
    marginVertical: theme.sizes.base,
    marginHorizontal: theme.sizes.base * 2,
  },
  tab: {
    marginRight: theme.sizes.base * 2,
    paddingBottom: theme.sizes.base
  },
  active: {
    borderBottomColor: theme.colors.black,
    borderBottomWidth: 3,
  },
  blockMenu: {
    flexWrap: 'wrap',
    paddingHorizontal: theme.sizes.base * 2,
    marginBottom: theme.sizes.base * 3.5,
  },
  bodyMenu: {
    // this should be dynamic based on screen width
    minWidth: (width - (theme.sizes.padding * 2.4) - theme.sizes.base) / 2,
    maxWidth: (width - (theme.sizes.padding * 2.4) - theme.sizes.base) / 2,
    maxHeight: (width - (theme.sizes.padding * 2.4) - theme.sizes.base) / 2,
  },
  iconBack: {
    zIndex: 9,
    position: "absolute",
    top: 6,
    left: 5
  },
});
