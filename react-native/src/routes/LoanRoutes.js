import { createStackNavigator } from "react-navigation-stack";
import LoanScreen from "../screen/loan/LoanScreen";
import BillingStackNavigor from "./BillingRoutes";

const LoanStackNavigor = createStackNavigator(
  {
    Loan: {
      screen: LoanScreen
    },
    Billing: {
      screen: BillingStackNavigor
    }
  },
  {
    initialRouteName: "Loan",
    headerMode: "none"
  }
);

export default LoanStackNavigor;