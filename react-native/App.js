import React, { Component } from "react";
import { StyleSheet } from "react-native";
import GeneralStatusBarColor from "./src/components/GeneralStatusBarColor";
import Routes from "./src/routes";
import { AppLoading } from "expo";
import { Container } from "native-base";
import * as Font from "expo-font";
import { Ionicons } from "@expo/vector-icons";
import { Root } from "native-base";
import { Provider } from "react-redux";
import { store } from "./src/utils/store";

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isReady: false
    };
  }

  async componentDidMount() {
    await Font.loadAsync({
      Roboto: require("native-base/Fonts/Roboto.ttf"),
      Roboto_medium: require("native-base/Fonts/Roboto_medium.ttf"),
      ...Ionicons.font
    });
    this.setState({ isReady: true });
  }

  render() {
    if (!this.state.isReady) {
      return <AppLoading />;
    }

    return (
      <Provider store={store}>
        <Root>
          <Container>
            <GeneralStatusBarColor backgroundColor="#2c3e50" barStyle="light-content" />
            <Routes />
          </Container>
        </Root>
      </Provider>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center"
  }
});
