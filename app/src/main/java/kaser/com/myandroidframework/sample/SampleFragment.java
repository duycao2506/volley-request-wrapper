package kaser.com.myandroidframework.sample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

import kaser.com.myandroidframework.MainActivity;
import kaser.com.myandroidframework.R;
import kaser.com.myandroidframework.framework_components.user_interface.KasperFragment;
import kaser.com.myandroidframework.framework_components.user_interface.KasperProgressDialog;
import kaser.com.myandroidframework.framework_components.utils.MyCallBack;

/**
 * Created by admin on 4/2/17.
 */

public class SampleFragment extends KasperFragment implements View.OnClickListener{


    Button loading, recycle, activity, snack;

    public SampleFragment() {
        setResource(R.layout.fragment_sample_layout);
    }

    @Override
    protected void onKasperViewCreate(View parent) {
        super.onKasperViewCreate(parent);
        snack = (Button) parent.findViewById(R.id.snack);
        loading = (Button) parent.findViewById(R.id.loading);
        snack.setOnClickListener(this);
        loading.setOnClickListener(this);

    }

    public void loadingTask(){
        new AsyncTask<Void, Void, Void>(){
            KasperProgressDialog kasperProgressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                kasperProgressDialog = (KasperProgressDialog) KasperProgressDialog.ctor(getContext(),R.style.CustomDialogTheme,R.layout.loading_layout);
//                kasperProgressDialog.show();
                ((MyCallBack)getActivity()).callback(MainActivity.START,0,null);
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                ((MyCallBack)getActivity()).callback(MainActivity.FINISH,0,null);

            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.recycle:
                break;
            case R.id.activity:
                break;
            case R.id.snack:
                ((MyCallBack) getActivity()).callback(MainActivity.SNACKSHOW,0,null);
                break;
            case R.id.loading:
                loadingTask();
                break;

        }
    }
}
