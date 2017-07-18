
package com.urlresolver;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.util.Log;
import com.facebook.react.bridge.Promise;

public class RNUrlResolverModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNReactNativeUrlResolverModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNUrlResolver";
    }

    @ReactMethod
    public void resolveUrl(final String url, final Promise promise) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    URL originalURL = new URL(url);
                    HttpURLConnection ucon = (HttpURLConnection) originalURL.openConnection();
                    ucon.setInstanceFollowRedirects(false);
                    URL resolvedURL = new URL(ucon.getHeaderField("Location"));
                    promise.resolve(resolvedURL.toString());
                }
                catch (MalformedURLException ex) {
                    Log.e("App Link",Log.getStackTraceString(ex));
                    promise.reject("Cannot resolve url MalformedURLException:", Log.getStackTraceString(ex));
                }
                catch (IOException ex) {
                    Log.e("App Link",Log.getStackTraceString(ex));
                    promise.reject("Cannot resolve url IOException:", Log.getStackTraceString(ex));
                }
            };
        }).start();
    }

}