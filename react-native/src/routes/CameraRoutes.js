import { createStackNavigator } from "react-navigation-stack";
import CameraExample from "../screen/camera/CameraScreen";
import CustomerAccountStackNavigor from "./AccountRoutes";
const CameraStackNavigator = createStackNavigator(
  {
    Camera: {
      screen: CameraExample,
      navigationOptions: {
        header: null
      }
    },
    CustomerAccount: {
      screen: CustomerAccountStackNavigor
    }
  },

  {
    initialRouteName: "Camera",
    headerMode: 'none'
  }
);

export default CameraStackNavigator;
