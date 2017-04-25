package kaser.com.myandroidframework.framework_components.user_interface;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import kaser.com.myandroidframework.R;
import kaser.com.myandroidframework.framework_components.utils.MyCallBack;

public class KasperActivity extends AppCompatActivity implements MyCallBack {

    public static final String FINISH = "F";
    public static final String START = "S";
    public static final String SNACKSHOW = "SS";

    protected Fragment mainFragment;
    protected Snackbar snackbarNoti;
    protected CoordinatorLayout cl;
    protected String notice;
    private View loadingView;


    protected int loadingViewId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateful);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFragment();

        cl = (CoordinatorLayout) findViewById(R.id.coordinateLayout);
        loadingView = findViewById(getLoadingViewId());
        loadingView.setVisibility(View.GONE);
        
        changeFragment(mainFragment);

    }

    private void changeFragment(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentHolder, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    protected void initFragment() {
        mainFragment = new RecycleViewFragment();
    }


    @Override
    public void callback(String message, int code, Object data) {
        switch (message){
            case START:
                loadingView.setVisibility(View.VISIBLE);
                break;
            case FINISH:
                loadingView.setVisibility(View.GONE);
                break;

            case SNACKSHOW:
                if (snackbarNoti != null)
                    snackbarNoti.show();
                break;

        }
    }

    protected int getLoadingViewId() {
        return R.id.loadingScreen;
    }
}
