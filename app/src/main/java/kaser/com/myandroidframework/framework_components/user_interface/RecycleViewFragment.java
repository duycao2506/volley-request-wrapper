package kaser.com.myandroidframework.framework_components.user_interface;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kaser.com.myandroidframework.R;

/**
 * Created by admin on 4/2/17.
 */

public class RecycleViewFragment extends KasperFragment implements OnRefreshListener {

    RecyclerView listView;
    private KasperRecycleAdapter listViewAdapter;
    private SwipeRefreshLayout swipeLayout;

    public RecycleViewFragment() {
        setResource(R.layout.fragment_recycle_view);
    }

    @Override
    protected void onKasperViewCreate(View parent) {
        super.onKasperViewCreate(parent);

        //Swipe
        swipeLayout = (SwipeRefreshLayout) parent.findViewById(R.id.swipeRecycle);

        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        //ListView
        listView = (RecyclerView) parent.findViewById(R.id.lvRecycle);
        listViewAdapter = initListViewAdapter();
        listView.setAdapter(listViewAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);

        swipeLayout.setRefreshing(true);
        loadRefresh();
    }

    /*
    *
    * override method to custom view holder
     */
    private KasperRecycleAdapter initListViewAdapter() {
        return new KasperRecycleAdapter();
    }


    @Override
    public void onRefresh() {
        loadRefresh();
    }

    protected void loadRefresh() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                swipeLayout.setRefreshing(false);
            }
        }.execute();
    }

    protected void loadMore(){

    }

    protected void loadEntities(){

    }
}
