import * as React from 'react';

import { Dimensions, StyleSheet, View } from 'react-native';
import {
  CubeTransitionView,
  PageChangeProp,
  TouchEventProp,
} from 'react-native-cube-transition';

const V2: React.FC = () => {
  console.log('[App.V2.render]');
  return <View style={{ ...styles.size, backgroundColor: 'yellow' }} />;
};

class V extends React.Component<any, any> {
  render() {
    console.log('[App.V.render]');
    return <View style={{ ...styles.size, backgroundColor: 'green' }} />;
  }
}

export default function App() {
  const onTouch = (event: TouchEventProp) => {
    console.log('[App.onTouch]', event.nativeEvent.touchType);
  };

  const onPageChange = (event: PageChangeProp) => {
    console.log('[App.PageChangeProp]', event.nativeEvent);
  };

  return (
    <View style={styles.container}>
      <CubeTransitionView
        style={styles.box}
        onTouch={onTouch}
        onPageChange={onPageChange}
      >
        <V />
        <V2 />
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
