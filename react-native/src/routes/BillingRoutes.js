import { createStackNavigator } from "react-navigation-stack";
import BillingScreen from "../screen/billing/BillingScreen";

const BillingStackNavigor = createStackNavigator(
  {
    Billing: {
        screen: BillingScreen
    }
  },
  {
    initialRouteName: "Billing",
    headerMode: "none"
  }
);

export default BillingStackNavigor;