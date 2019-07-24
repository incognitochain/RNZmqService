package com.minerx;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.tadasr.IOTWifi.IOTWifiPackage;
import fr.greweb.rnwebgl.RNWebGLPackage;
import com.oblador.vectoricons.VectorIconsPackage;
import io.fullstack.oauth.OAuthManagerPackage;
import com.pusherman.networkinfo.RNNetworkInfoPackage;
import fr.bamlab.rnimageresizer.ImageResizerPackage;
import com.reactnative.ivpusic.imagepicker.PickerPackage;
import io.invertase.firebase.RNFirebasePackage;
import com.RNFetchBlob.RNFetchBlobPackage;
import com.dylanvann.fastimage.FastImageViewPackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import im.shimo.react.cookie.CookieManagerPackage;
import com.lugg.ReactNativeConfig.ReactNativeConfigPackage;
import com.actionsheet.ActionSheetPackage;
import fr.bamlab.rnimageresizer.ImageResizerPackage;
import com.oblador.vectoricons.VectorIconsPackage;
import com.reactnative.ivpusic.imagepicker.PickerPackage;
import io.invertase.firebase.RNFirebasePackage;
import com.RNFetchBlob.RNFetchBlobPackage;
import com.dylanvann.fastimage.FastImageViewPackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import im.shimo.react.cookie.CookieManagerPackage;
import com.lugg.ReactNativeConfig.ReactNativeConfigPackage;
import com.actionsheet.ActionSheetPackage;
import io.fullstack.oauth.OAuthManagerPackage;
import com.pusherman.networkinfo.RNNetworkInfoPackage;

import io.invertase.firebase.database.RNFirebaseDatabasePackage;

import io.invertase.firebase.auth.RNFirebaseAuthPackage;

import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import com.projectseptember.RNGL.RNGLPackage;


import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
            new IOTWifiPackage(),
            new RNWebGLPackage(),
            new VectorIconsPackage(),
            new OAuthManagerPackage(),
            new RNNetworkInfoPackage(),
            new ImageResizerPackage(),
            new PickerPackage(),
            new RNFirebasePackage(),
            new RNFetchBlobPackage(),
            new FastImageViewPackage(),
            new RNDeviceInfo(),
            new CookieManagerPackage(),
            new ReactNativeConfigPackage(),
            new ActionSheetPackage(),
            new RNGLPackage(),
            new ZMQPackage(),
                    new RNFirebaseDatabasePackage(),
                    new RNFirebaseAuthPackage()
            );

        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
    }
}
