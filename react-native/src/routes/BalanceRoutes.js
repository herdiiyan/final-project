import { createStackNavigator } from "react-navigation-stack";
import Balance from "../screen/top-up/Balance";

const BalanceStackNavigor = createStackNavigator(
  {
    Balance: {
      screen: Balance
    }
  },
  {
    initialRouteName: "Balance",
    headerMode: "none"
  }
);

export default BalanceStackNavigor;