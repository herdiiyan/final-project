import { createStackNavigator } from "react-navigation-stack";
import CustomerAccountScreen from "../screen/customer/CustomerAccountScreen";
import LoanStackNavigor from "./LoanRoutes";

const CustomerAccountStackNavigor = createStackNavigator(
  {
    CustomerAccount: {
        screen: CustomerAccountScreen
    },
    Loan : {
        screen: LoanStackNavigor
    }
  },
  {
    initialRouteName: "CustomerAccount",
    headerMode: "none"
  }
);

export default CustomerAccountStackNavigor;