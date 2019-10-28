import * as React from "react";
import { Text, View, StyleSheet, Button, Alert, Image, Dimensions } from "react-native";
import { Header, Button as ButtoN, Icon, Container  } from "native-base";
import * as Permissions from "expo-permissions";
import { findOneCustomers } from "../../actions/CustomersActions";
import { bindActionCreators } from "redux";
import { BarCodeScanner } from "expo-barcode-scanner";
import { connect } from "react-redux";
import { theme } from "../../constants";

const { width } = Dimensions.get('screen');

class BarcodeScannerExample extends React.Component {
  state = {
    hasCameraPermission: null,
    scanned: false
  };

  async componentDidMount() {
    this.getPermissionsAsync();
  }

  getPermissionsAsync = async () => {
    const { status } = await Permissions.askAsync(Permissions.CAMERA);
    this.setState({ hasCameraPermission: status === "granted" });
  };

  render() {
    const leftTop = {
      borderLeftWidth: 3,
      borderTopWidth: 3,
      bordeColor: 'white'
    };
    const leftBottom = {
      borderLeftWidth: 3,
      borderBottomWidth: 3,
      bordeColor: 'white'
    };
    const rightTop = {
      borderRightWidth: 3,
      borderTopWidth: 3,
      bordeColor: 'white'
    };
    const rightBottom = {
      borderRightWidth: 3,
      borderBottomWidth: 3,
      bordeColor: 'white'
    };

    const { hasCameraPermission, scanned } = this.state;

    if (hasCameraPermission === null) {
      return <Text>Requesting for camera permission</Text>;
    }
    if (hasCameraPermission === false) {
      return <Text>No access to camera</Text>;
    }
    return (
      <Container>
        <Header>
            <ButtoN
              transparent
              style={styles.iconBack}
              onPress={() => this.props.navigation.navigate("Home")}
            >
              <Icon name="angle-left" type="FontAwesome5" />
            </ButtoN>
            <Image
              source={require("../../../assets/image.png")}
              style={{ width: 105, height: 33, top: 12 }}
            />
          </Header>
          <View style={{flex: 1}}>
            <BarCodeScanner
              onBarCodeScanned={scanned ? undefined : this.handleBarCodeScanned}
              style={StyleSheet.absoluteFillObject}
            />  
            <View style={{...StyleSheet.absoluteFill, alignItems: 'center', justifyContent: 'center'}}>
              <View style={{ width: width / 2, height: width / 2}}>
                <View style={{flex: 1, flexDirection: 'row'}}>
                  <View style={{flex: 1, ...leftTop}} />
                  <View style={{flex: 1}} />
                  <View style={{flex: 1, ...rightTop}} />
                </View>
                <View style={{flex: 1}} />
                <View style={{flex: 1, flexDirection: 'row'}} >
                  <View style={{flex: 1, ...leftBottom}} />
                  <View style={{flex: 1}} />
                  <View style={{flex: 1, ...rightBottom}} />
                </View>
              </View>
            </View>
          </View>
      </Container>
    );
  }

  componentDidUpdate(prevProps, prevState) {
    const { data, error } = this.props;

    if (data && prevProps.data !== data) {
    }
  }

  handleBarCodeScanned = async ({ type, data }) => {
    this.setState({ scanned: true });
    this.props.findOneCustomers(data);
    if (this.props.error == null) {
      Alert.alert(
        `Open ${type} ${data} URL?`,
        this.state.scanned,
        [
          {
            text: "Yes",
            onPress: () => {
              this.props.navigation.navigate("CustomerAccount", {
                cif: data
              });
            }
          },
          { text: "No", onPress: () => {} }
        ],
        { cancellable: false }
      );
    } else {
      alert("data ga ada");
    }
  };
}

function mapStateToProps(state) {
  return {
    data: state.findOneCustomers.data,
    error: state.findOneCustomers.error
  };
}
function matchDispatchToProps(dispatch) {
  return bindActionCreators({ findOneCustomers }, dispatch);
}

export default connect(
  mapStateToProps,
  matchDispatchToProps
)(BarcodeScannerExample);

const styles = StyleSheet.create({
  iconBack: {
    zIndex: 9,
    position: "absolute",
    top: 6,
    left: 5
  },
  tabs: {
    borderBottomColor: theme.colors.gray2,
    borderBottomWidth: StyleSheet.hairlineWidth,
    marginVertical: theme.sizes.base,
    marginHorizontal: theme.sizes.base * 2,
    marginBottom: 2
  },
  textHeader: {
    fontSize: 26,
    fontWeight: "bold",
    paddingBottom: 10
  }
});
