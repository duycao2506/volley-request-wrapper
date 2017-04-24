package kaser.com.myandroidframework.framework_components.utils;

import android.content.Context;
import android.view.View;

import kaser.com.myandroidframework.R;
import kaser.com.myandroidframework.framework_components.utils.GlobalUtils;
import kaser.com.myandroidframework.framework_components.utils.MyCallBack;

/**
 * Created by admin on 4/2/17.
 */

public class NetworkOnClickListener implements View.OnClickListener, MyCallBack {
    Context context;
    public NetworkOnClickListener(Context context, MyCallBack caller){
        super();
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        GlobalUtils.getInstance().hasActiveInternetConnection(this.context, this);
    }



    @Override
    public void callback(String message, int code, Object data) {
        if (!(boolean) data){
            GlobalUtils.getInstance().displayDialog(context,context.getString(R.string.OK),"",message,null);
        }
    }
}
