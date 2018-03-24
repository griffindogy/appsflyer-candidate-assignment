package com.maya.appsflyercandidateassignment;

import android.app.Application;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;

import java.util.Map;

public class MainApplication extends Application {

    private static final String DEV_KEY = "g2ycnQqufscmJfeNJeQNma";
    public static final String LOG_TAG = "MainApplication:AppsFlyer";

    @Override
    public void onCreate() {
        super.onCreate();

        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {

            /* Returns the attribution data. Note - the same conversion data is returned every time per install */
            @Override
            public void onInstallConversionDataLoaded(Map<String, String> conversionData) {
                for (String attrName : conversionData.keySet()) {
                    Log.d(LOG_TAG, "attribute: " + attrName + " = " + conversionData.get(attrName));
                }
//                setInstallData(conversionData);
            }

            @Override
            public void onInstallConversionFailure(String errorMessage) {
                Log.d(LOG_TAG, "error getting conversion data: " + errorMessage);
            }

            /* Called only when a Deep Link is opened */
            @Override
            public void onAppOpenAttribution(Map<String, String> conversionData) {
                for (String attrName : conversionData.keySet()) {
                    Log.d(LOG_TAG, "attribute: " + attrName + " = " + conversionData.get(attrName));
                }
            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d(LOG_TAG, "error onAttributionFailure : " + errorMessage);
            }
        };


        /* This API enables AppsFlyer to detect installations, sessions, and updates. */

        AppsFlyerLib.getInstance().init(DEV_KEY , conversionListener , getApplicationContext());
        AppsFlyerLib.getInstance().startTracking(this, DEV_KEY);


        /* Set to true to see the debug logs. Comment out or set to false to stop the function */

        AppsFlyerLib.getInstance().setDebugLog(true);


    }
}
