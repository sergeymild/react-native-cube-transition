import {
  requireNativeComponent,
  UIManager,
  Platform,
  ViewStyle,
} from 'react-native';
import type { PropsWithChildren } from 'react';
import React from 'react';

const LINKING_ERROR =
  `The package 'react-native-cube-transition' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

interface CubeTransitionProps extends PropsWithChildren<any> {
  style: ViewStyle;
}

const ComponentName = 'CubeTransitionView';

const _CubeTransitionView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<CubeTransitionProps>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };
export const CubeTransitionView: React.FC<CubeTransitionProps> = (props) => {
  return (
    <_CubeTransitionView
      {...props}
      totalCount={React.Children.count(props.children)}
    />
  );
};
