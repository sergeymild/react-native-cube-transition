import {
  NativeSyntheticEvent,
  Platform,
  requireNativeComponent,
  StyleSheet,
  UIManager,
  ViewStyle,
} from 'react-native';
import type { PropsWithChildren } from 'react';
import React, { useState } from 'react';

const LINKING_ERROR =
  `The package 'react-native-cube-transition' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

export type TouchEventProp = NativeSyntheticEvent<{
  touchType: 'start' | 'change' | 'end';
}>;
export type PageChangeProp = NativeSyntheticEvent<{
  page: number;
}>;
interface CubeTransitionProps extends PropsWithChildren<any> {
  style: ViewStyle;
  onTouch?: (event: TouchEventProp) => void;
  onPageChange?: (event: PageChangeProp) => void;
}

const ComponentName = 'CubeTransitionView';

const _CubeTransitionView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<CubeTransitionProps>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

export const _CubeItemView = requireNativeComponent<any>('CubeItemView');

const CubeItemView: React.FC<PropsWithChildren<{ index: number }>> = (
  props
) => {
  const [shouldRender, setShouldRender] = useState(false);
  const event = () => setShouldRender(true);
  return (
    <_CubeItemView
      onPrepareForRender={event}
      nativeID={props.index.toString()}
      style={styles.item}
      children={shouldRender ? props.children : null}
    />
  );
};

export const CubeTransitionView: React.FC<CubeTransitionProps> = (props) => {
  const totalCount = React.Children.count(props.children);
  return (
    <_CubeTransitionView {...props} totalCount={totalCount}>
      {React.Children.map(props.children, (child, index) => (
        <CubeItemView index={index} children={child} />
      ))}
    </_CubeTransitionView>
  );
};

const styles = StyleSheet.create({
  item: { width: '100%', height: '100%' },
});
