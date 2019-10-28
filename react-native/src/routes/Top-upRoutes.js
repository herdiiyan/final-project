import { createStackNavigator } from "react-navigation-stack";
import TopUp from "../screen/top-up/Top-up";
import BalanceStackNavigor from "./BalanceRoutes";

const TopUpStackNavigor = createStackNavigator(
  {
    TopUp: {
      screen: TopUp
    },
    Balance: {
      screen: BalanceStackNavigor
    }
  },
  {
    initialRouteName: "TopUp",
    headerMode: "none"
  }
);

export default TopUpStackNavigor;