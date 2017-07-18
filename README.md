# React Native Url Resolver
Url Resolver for react native apps. 
Get the final url from a redirect url, eg. resolving SendGrid click tracking links for deeplinks https://sendgrid.com/docs/Classroom/Build/Add_Content/universal_links.html#-Resolving-SendGrid-Click-Tracking-Links

# Installation
```
$ yarn add react-native-url-resolver
$ react-native link react-native-url-resolver
```

# Usage
```
import urlResolver from 'react-native-url-resolver';
const url = await urlResolver.resolve('http://link.your-website.com/wf/click?upn=2BpJel-2FskgqgVk-2FpxfUI2LQoBBIQ');
```
