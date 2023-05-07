# react-native-cube-transition

## About the project
Replicate easily the Instagram story cube animation for React Native

![](./preview.gif)

[Example](https://github.com/sergeymild/react-native-cube-transition/blob/main/example/src/App.tsx)

## Installation

```sh
"react-native-cube-transition":"react-native-cube-transition#0.0.1"
```

## Usage

```js
import { CubeTransitionView } from "react-native-cube-transition";

// ...

// type TouchEventProp = NativeSyntheticEvent<{
//    touchType: 'start' | 'change' | 'end';
// }>;
const onTouch = (event: TouchEventProp) => {
  console.log('[App.onTouch]', event.nativeEvent);
};

// type PageChangeProp = NativeSyntheticEvent<{
//    page: number;
// }>;
const onPageChange = (event: PageChangeProp) => {
  console.log('[App.PageChangeProp]', event.nativeEvent);
};

// pages will load lazily while scroll
<CubeTransitionView
  style={styles.box}
  onTouch={onTouch}
  onPageChange={onPageChange}
>
  <View style={{ ...styles.size, backgroundColor: 'black' }} />
  <View style={{ ...styles.size, backgroundColor: 'orange' }} />
</CubeTransitionView>
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
