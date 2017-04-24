package kaser.com.myandroidframework.framework_components.user_interface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 4/2/17.
 */

public class KasperFragment extends Fragment {
    View parent;
    private int res;

    public KasperFragment() {

    }

    public void setResource(int res){
        this.res = res;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parent = inflater.inflate(res,container, false);
        
        onKasperViewCreate(parent);
        
        return parent;
    }

    protected void onKasperViewCreate(View parent) {

    }
}
