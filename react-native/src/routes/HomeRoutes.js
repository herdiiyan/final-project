import React from "react";
import HomeScreen from "../screen/home/HomeScreen";
import { createStackNavigator } from "react-navigation-stack";
import { createBottomTabNavigator } from "react-navigation-tabs";
import { Icon } from "native-base";
import CustomerStackNavigor from "./CustomerRoutes";
import CameraStackNavigator from "./CameraRoutes";
import TopUpStackNavigor from "./Top-upRoutes";
import { AboutScreen } from "../screen/about/AboutScreen";
import LoginScreen from "../screen/login/LoginScreen";

const TabNavigatorCustomer = createBottomTabNavigator(
  {
    Customer: {
      screen: CustomerStackNavigor,
      navigationOptions: {
        tabBarIcon: ({ tintColor, focused }) => (
          <Icon
            type="FontAwesome"
            name="user"
            color={tintColor}
            iconStyle={{
              fontSize: 35
            }}
          />
        )
      }
    },
    TopUp: {
      screen: TopUpStackNavigor,
      navigationOptions: {
        tabBarIcon: ({ tintColor, focused }) => (
          <Icon
            type="FontAwesome"
            name="credit-card-alt"
            color={tintColor}
            iconStyle={{
              fontSize: 35
            }}
          />
        )
      }
    },
    QR: {
      screen: CameraStackNavigator,
      navigationOptions: {
        tabBarIcon: ({ tintColor, focused }) => (
          <Icon
            type="FontAwesome" 
            name="qrcode"
            color={tintColor}
            iconStyle={{
              fontSize: 35
            }}
          />
        )
      }
    }
  },
  {
    initialRouteName: "Customer"
  },
  {
    defaultNavigationOptions: {
      style: {
        backgroundColor: "black"
      },

      tabBarOptions: {
        showLabel: false,
        showIcon: true,
        tintColor: "red",
        activeTintColor: "black",
        inactiveTintColor: "grey",
        labelStyle: {
          fontSize: 12
        },
        style: {
          backgroundColor: "#ffffff",
          borderTopWidth: 1,

          borderTopColor: "#9e9e9e"
        }
      }
    }
  }
);

const TabNavigatorTopUp = createBottomTabNavigator(
  {
    Customer: {
      screen: CustomerStackNavigor,
      navigationOptions: {
        tabBarIcon: ({ tintColor, focused }) => (
          <Icon
            type="FontAwesome"
            name="user"
            color={tintColor}
            iconStyle={{
              fontSize: 35
            }}
          />
        )
      }
    },
    TopUp: {
      screen: TopUpStackNavigor,
      navigationOptions: {
        tabBarIcon: ({ tintColor, focused }) => (
          <Icon
            type="FontAwesome"
            name="credit-card-alt"
            color={tintColor}
            iconStyle={{
              fontSize: 35
            }}
          />
        )
      }
    },
    QR: {
      screen: CameraStackNavigator,
      navigationOptions: {
        tabBarIcon: ({ tintColor, focused }) => (
          <Icon
            type="FontAwesome"
            name="qrcode"
            color={tintColor}
            iconStyle={{
              fontSize: 35
            }}
          />
        )
      }
    }
  },
  {
    initialRouteName: "TopUp"
  },
  {
    defaultNavigationOptions: {
      style: {
        backgroundColor: "black"
      },

      tabBarOptions: {
        showLabel: false,
        showIcon: true,
        tintColor: "red",
        activeTintColor: "black",
        inactiveTintColor: "grey",
        labelStyle: {
          fontSize: 12
        },
        style: {
          backgroundColor: "#ffffff",
          borderTopWidth: 1,

          borderTopColor: "#9e9e9e"
        }
      }
    }
  }
);

const TabNavigatorQR = createBottomTabNavigator(
  {
    Customer: {
      screen: CustomerStackNavigor,
      navigationOptions: {
        tabBarIcon: ({ tintColor, focused }) => (
          <Icon
            type="FontAwesome"
            name="user"
            color={tintColor}
            iconStyle={{
              fontSize: 35
            }}
          />
        )
      }
    },
    TopUp: {
      screen: TopUpStackNavigor,
      navigationOptions: {
        tabBarIcon: ({ tintColor, focused }) => (
          <Icon
            type="FontAwesome"
            name="credit-card-alt"
            color={tintColor}
            iconStyle={{
              fontSize: 35
            }}
          />
        )
      }
    },
    QR: {
      screen: CameraStackNavigator,
      navigationOptions: {
        tabBarIcon: ({ tintColor, focused }) => (
          <Icon
            type="FontAwesome"
            name="qrcode"
            color={tintColor}
            iconStyle={{
              fontSize: 35
            }}
          />
        )
      }
    }
  },
  {
    initialRouteName: "QR"
  },
  {
    defaultNavigationOptions: {
      style: {
        backgroundColor: "black"
      },

      tabBarOptions: {
        showLabel: false,
        showIcon: true,
        tintColor: "red",
        activeTintColor: "black",
        inactiveTintColor: "grey",
        labelStyle: {
          fontSize: 12
        },
        style: {
          backgroundColor: "#ffffff",
          borderTopWidth: 1,

          borderTopColor: "#9e9e9e"
        }
      }
    }
  }
);

const HomeStackNavigor = createStackNavigator(
  {
    Customer: TabNavigatorCustomer,
    TopUp: TabNavigatorTopUp,
    QR: TabNavigatorQR,
    About: AboutScreen,
    Logout: LoginScreen,
    Home: {
      screen: HomeScreen
    }
  },
  {
    initialRouteName: "Home",
    headerMode: "none"
  }
);

export default HomeStackNavigor;
