import React from "react";
import { createAppContainer, createSwitchNavigator } from "react-navigation";
import HomeStackNavigor from "./HomeRoutes";
import LoginStackNavigor from "./LoginRoutes";
import CameraStackNavigator from "./CameraRoutes";
export default class Routes extends React.Component {
  render() {
    return <AppContainer />;
  }
}

const SwitchNavigor = createSwitchNavigator(
  {
    Home: HomeStackNavigor,
    Login: LoginStackNavigor,
    Camera: CameraStackNavigator
  },
  {
    initialRouteName: "Login"
  }
);

const AppContainer = createAppContainer(SwitchNavigor);
