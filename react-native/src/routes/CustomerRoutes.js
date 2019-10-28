import { createStackNavigator } from "react-navigation-stack";
import CustomerScreen from "../screen/customer/CustomerScreen";
import CustomerAccountStackNavigor from "./AccountRoutes";
const CustomerStackNavigor = createStackNavigator(
  {
    Customer: {
      screen: CustomerScreen
    },
    CustomerAccount: {
      screen: CustomerAccountStackNavigor
    }
  },
  {
    initialRouteName: "Customer",
    headerMode: "none"
  }
);

export default CustomerStackNavigor;
