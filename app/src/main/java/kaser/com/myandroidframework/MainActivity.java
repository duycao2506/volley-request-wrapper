package kaser.com.myandroidframework;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import kaser.com.myandroidframework.framework_components.user_interface.KasperActivity;
import kaser.com.myandroidframework.sample.SampleFragment;

public class MainActivity extends KasperActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
