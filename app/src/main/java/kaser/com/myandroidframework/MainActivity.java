package kaser.com.myandroidframework;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import kaser.com.myandroidframework.framework_components.data.from_server.GETRequestService;
import kaser.com.myandroidframework.framework_components.data.from_server.GeneralResponse;
import kaser.com.myandroidframework.framework_components.data.from_server.POSTRequestService;
import kaser.com.myandroidframework.framework_components.entity.SuperObject;
import kaser.com.myandroidframework.framework_components.user_interface.KasperActivity;
import kaser.com.myandroidframework.framework_components.user_interface.KasperProgressDialog;
import kaser.com.myandroidframework.framework_components.utils.MyCallBack;
import kaser.com.myandroidframework.sample.SampleFragment;

public class MainActivity extends KasperActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.callback(START,2,"");
        GETRequestService grs = new GETRequestService("posts/1", new MyCallBack() {
            @Override
            public void callback(String message, int code, Object data) {
                GeneralResponse response = (GeneralResponse) data;
                Log.d("RESP", "Got response");
                MainActivity.this.callback(FINISH,2,"");
                SuperObject so = (new Gson()).fromJson(response.getResponse(),SuperObject.class);
                Log.d("SO",so.toString());

            }
        }, new GeneralResponse());

        grs.executeService();

        POSTRequestService prs = new POSTRequestService("posts", new MyCallBack() {
            @Override
            public void callback(String message, int code, Object data) {
                GeneralResponse response2 = (GeneralResponse) data;
                Log.d("SASS", response2.getResponse());
            }
        },new GeneralResponse());
        prs.executeService();


        notice = "Test Snack";
        snackbarNoti = Snackbar.make(
                cl,
                notice,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(
                        getString(R.string.close),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbarNoti.dismiss();
                            }
                        });
    }

    @Override
    protected void initFragment() {
        mainFragment = new SampleFragment();
    }
}
