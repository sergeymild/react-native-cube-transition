import * as React from 'react';

import { Dimensions, StyleSheet, View } from 'react-native';
import { CubeTransitionView } from 'react-native-cube-transition';

export default function App() {
  return (
    <View style={styles.container}>
      <CubeTransitionView style={styles.box}>
        <View style={{ ...styles.size, backgroundColor: 'green' }} />
        <View style={{ ...styles.size, backgroundColor: 'yellow' }} />
        <View style={{ ...styles.size, backgroundColor: 'black' }} />
        <View style={{ ...styles.size, backgroundColor: 'orange' }} />
      </CubeTransitionView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height,
  },
  box: {
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height,
    backgroundColor: 'purple',
  },

  size: {
    position: 'absolute',
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height,
  },
});
