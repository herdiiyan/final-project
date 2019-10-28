import LoginScreen from "../screen/login/LoginScreen";
import { createStackNavigator } from "react-navigation-stack";
const LoginStackNavigor = createStackNavigator(
  {
    Login: {
      screen: LoginScreen,
      navigationOptions: {
        title: "Login",
        header: null
      }
    }
  },

  {
    initialRouteName: "Login"
  }
);

export default LoginStackNavigor;
