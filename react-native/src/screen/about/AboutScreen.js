import React, { Component } from "react";
import { Image, StyleSheet } from "react-native";
import { ListItem } from "react-native-elements";
import { theme } from "../../constants";
import { Block } from "../../components";
import {
    Container,
    Header,
    Text,
    Button,
    Icon,
  } from "native-base";
export class AboutScreen extends Component {

  render() {
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
        <Block flex={false} row style={styles.tabs}>
            <Text style={styles.textHeader}>Team Kelompok 3</Text>
        </Block>
        <ListItem 
        title="Herdiansyah"
        contentContainerStyle={styles.list}
        />
        <ListItem 
        title="Gamal"
        contentContainerStyle={styles.list}
        />
        <ListItem 
        title="Kevin"
        contentContainerStyle={styles.list}
        />
        <ListItem 
        title="Heriyanto"
        contentContainerStyle={styles.list}
        />
      </Container>
    );
  }
}

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
    paddingBottom: 10,
  },
  list: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  }
});
