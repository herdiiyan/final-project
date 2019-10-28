import React, { Component } from "react";
import { Image, StyleSheet, RefreshControl } from "react-native";
import { SearchBar } from "react-native-elements";
import { findOneCustomers } from "../../actions/CustomersActions";
import { bindActionCreators } from "redux";
import * as Animatable from "react-native-animatable";
import { connect } from "react-redux";
import { theme } from "../../constants";
import { Block } from "../../components";
import {
    Container,
    Content,
    Header,
    Text,
    ListItem,
    Body,
    Right,
    Button,
    Icon,
    Toast
  } from "native-base";
class CustomerScreen extends Component {
  state = {
    search: "",
    cif: null
  };

  componentDidMount() {
    this.onReload();
  }
  onReload() {
    this.props.findOneCustomers(this.state.search);
  }

  updateSearch = search => {
    this.setState({ search: search });
  };

  componentDidUpdate(prevProps, prevState) {
    const { data, dataAcc, error } = this.props;
    if (data && prevProps.data == data) {
      this.onReload();
    }
    if (error && prevProps.error !== error) {
      Toast.show({
        text: error.message,
        buttonText: 'Ok',
        type: "warning",
        duration: 1000,
        position: 'top'
      })
    }
  }

  showDetail(cif) {
    if (cif != null) {
      this.props.navigation.navigate("CustomerAccount", { cif:cif });
    } else {
      Toast.show({
        text: "Please input CIF to Search",
        buttonText: 'Ok',
        type: "danger",
        duration: 5000,
        position: 'top'
      })
    }
  }

  renderListItem(data, index) {
    return (
          <ListItem thumbnail style={styles.list} key={data.cif} onPress={() => this.showDetail(data.cif)}>
            <Body>
              <Text numberOfLines={1}>{data.firstName}</Text>
              <Text note numberOfLines={1}>
                Address : {data.address}
              </Text>
            </Body>
            <Right>
              <Button transparent onPress={() => this.showDetail(data.cif)}>
                <Animatable.View animation="fadeInLeft">
                <Icon name="angle-right" type="FontAwesome5" />
                </Animatable.View>
              </Button>
            </Right>
          </ListItem>
    );
  }

  render() {
    const { search } = this.state;
    return (
      <Container>
        <Header>
          <Button
            transparent
            style={styles.iconBack}
            onPress={() => this.props.navigation.navigate("Home")}
          >
            <Icon name="angle-left" type="FontAwesome5" />
          </Button>
          <Image
            source={require("../../../assets/image.png")}
            style={{ width: 105, height: 33, top: 12 }}
          />
        </Header>
          <SearchBar
            containerStyle={{ width: "100%" }}
            placeholder="Type Here CIF..."
            onChangeText={this.updateSearch}
            value={search}
            platform= "ios"
          />
          <Block flex={false} row style={styles.tabs}>
            <Text style={styles.textHeader}>Customers</Text>
          </Block>
          <Content refreshControl={<RefreshControl refreshing={this.props.loading} onRefresh={() => this.onReload()}/>}>
            {this.props.data.length && this.state.search != ""   ?  this.props.data.map((data, index)=>(this.renderListItem(data, index))) :<Text style={{textAlign: 'center'}}>Search Cif Customer...</Text>}
          </Content>
      </Container>
    );
  }
}

function mapStateToProps(state) {
  return {
    loading: state.findOneCustomers.loading,
    data: state.findOneCustomers.data,
    error: state.findOneCustomers.error,
  };
}
function matchDispatchToProps(dispatch) {
  return bindActionCreators({ findOneCustomers }, dispatch);
}

export default connect(
  mapStateToProps,
  matchDispatchToProps
)(CustomerScreen);

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
    marginBottom: 1
  },
  textHeader: {
    fontSize: 26,
    fontWeight: "bold",
    paddingBottom: 10
  },
  list: {
    marginRight: 32,
    marginLeft: 32
  }
});
