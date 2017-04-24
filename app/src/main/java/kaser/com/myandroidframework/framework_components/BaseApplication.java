package kaser.com.myandroidframework.framework_components;

import android.app.Application;
import android.content.Context;

import kaser.com.myandroidframework.framework_components.data.from_server.VolleyHelper;

/**
 * Created by admin on 4/2/17.
 */

public class BaseApplication extends Application {
    private static VolleyHelper instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = VolleyHelper.getInstance(this);

    }

    public static VolleyHelper getVolleyInstance() {
        return instance;
    }

}
