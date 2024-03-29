package kaser.com.myandroidframework.framework_components.data.from_server;

import android.os.AsyncTask;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import kaser.com.myandroidframework.framework_components.BaseApplication;
import kaser.com.myandroidframework.framework_components.entity.SuperObject;
import kaser.com.myandroidframework.framework_components.utils.GlobalUtils;
import kaser.com.myandroidframework.framework_components.utils.MyCallBack;

/**
 * Created by admin on 4/2/17.
 */

public class RequestService {
    protected Map<String, Object> params;
    protected Map<String, Object> headers;
    protected GeneralResponse response;
    private String api;

    public interface RequestServiceConstant {
        String BASE_URL = "http://jsonplaceholder.typicode.com/";
        String api1 = "";

    }



    MyCallBack caller;
    AsyncTask<Void, Void, Void> requestTask;



    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public RequestService(String api, MyCallBack caller, GeneralResponse response) {
        this.api = api;
        this.caller = caller;
        this.response = response;
        this.params = new HashMap<>();
        this.headers = new HashMap<>();
    }

    public RequestService() {

    }


    public void executeService(){
        this.requestTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    request();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                caller.callback(RequestService.this.api,0,response);
            }
        };
        this.requestTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private void request() throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(RequestServiceConstant.BASE_URL);
        builder.append(api);
        StringRequest stringRequest = buildStringRequest(builder.toString());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        BaseApplication.getVolleyInstance().addToRequestQueue(stringRequest);
        while (!response.isGotResponse()) ;
        if (!response.isResponseError()) {
            return;
        }
        throw new Exception(response.getError());
    }

    protected StringRequest buildStringRequest(String url) {
        return null;
    }


    //utils







    protected static StringRequest createRequestPOST(String url, GeneralResponse modelResponse, Map params, Map headers) {
        return new KasperStringRequest(Request.Method.POST, url, params, modelResponse, modelResponse, headers);
    }

    protected static StringRequest createRequestGET(String url, GeneralResponse modelResponse, Map params, Map headers) {
        String builtUrl = addParamstToGETRequest(url,params);
        return new KasperStringRequest(Request.Method.GET, builtUrl, null, modelResponse, modelResponse, headers);
    }

    protected static StringRequest createRequestPOST(String url, GeneralResponse modelResponse, JSONObject object, Map headers) {
        return new JsonBobyRequest(url, modelResponse, object, headers);
    }
    protected static StringRequest createRequestPOST(String url, GeneralResponse modelResponse, JSONArray object , Map headers) {
        return new JsonBobyRequest(url, modelResponse, object, headers);
    }


    protected static String addParamstToGETRequest(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        Set<String> keys = params.keySet();

        for (int i = 0; i < keys.size(); ++i) {

            Object key = keys.toArray()[i];
            Object val = params.get(keys.toArray()[i]);
            if (val instanceof ArrayList){
                ArrayList<Object> arr = (ArrayList) val;
                for (int j = 0; j < arr.size(); j++) {

                    builder.append(key);
                    builder.append("=");
                    builder.append(arr.get(j));
                    if (j < arr.size() - 1){
                        builder.append("&");
                    }
                }
            }else{
                builder.append(key);
                builder.append("=");
                builder.append(val);

            }
            if (i < keys.size() - 1) {
                builder.append("&");
            }
        }
        return builder.toString();
    }



}
