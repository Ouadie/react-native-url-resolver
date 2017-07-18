import {NativeModules} from 'react-native';

const {RNUrlResolver} = NativeModules;

export const resolveUrl = (url) => RNUrlResolver.resolveUrl(url);
