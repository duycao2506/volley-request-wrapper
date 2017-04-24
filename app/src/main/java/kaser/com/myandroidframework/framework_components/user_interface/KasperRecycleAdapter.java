package kaser.com.myandroidframework.framework_components.user_interface;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import kaser.com.myandroidframework.framework_components.entity.SuperObject;

/**
 * Created by admin on 4/2/17.
 */

public class KasperRecycleAdapter extends RecyclerView.Adapter {

    List<SuperObject> superObjects;
    int viewholder_res;
    Context context;


    private final int VIEW_TYPE_LOADING = 1;

    private boolean isLoadingmore = false;

    public boolean isLoadingmore() {
        return isLoadingmore;
    }

    public void setLoadingmore(boolean loadingmore) {
        isLoadingmore = loadingmore;
    }


    public KasperRecycleAdapter(int viewholder_res, Context context) {
        this.viewholder_res = viewholder_res;
        this.context = context;
    }

    public KasperRecycleAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
       return superObjects.size() + (isLoadingmore? 1 : 0);
    }

    public void addElement(SuperObject object){
        this.superObjects.add(object);
        this.notifyDataSetChanged();
    }

    public void insertAt(int pos, SuperObject object){
        this.superObjects.add(pos, object);
        this.notifyDataSetChanged();
    }

    public SuperObject remove(int pos){
        return this.superObjects.remove(pos);
    }

    public SuperObject getObjectAt(int pos){
        return superObjects.get(pos);
    }

    public void setSuperObjects(List<SuperObject> superObjects) {
        this.superObjects = superObjects;
    }

    public List<SuperObject> getSuperObjects() {
        return superObjects;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
