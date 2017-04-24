package kaser.com.myandroidframework.framework_components.data.from_server;

import android.os.AsyncTask;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import kaser.com.myandroidframework.framework_components.BaseApplication;
import kaser.com.myandroidframework.framework_components.entity.SuperObject;
import kaser.com.myandroidframework.framework_components.utils.GlobalUtils;
import kaser.com.myandroidframework.framework_components.utils.MyCallBack;

/**
 * Created by admin on 4/2/17.
 */

public class RequestService {
    private Map<String, Object> params;

    public interface RequestServiceConstant {
        String BASE_URL = "";
        String api1 = "";

    }


    private String api;
    MyCallBack caller;
    AsyncTask<Void, Void, Void> requestTask;
    GeneralResponse response;

    public RequestService(String api, MyCallBack caller, GeneralResponse response, Map<String, Object> params) {
        this.api = api;
        this.caller = caller;
        this.response = response;
        this.params = params;
    }

    public RequestService(String api, MyCallBack caller, GeneralResponse response) {
        this.api = api;
        this.caller = caller;
        this.response = response;
        this.params = null;
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
        this.requestTask.execute();
    }


    private void request() throws Exception {
        StringRequest stringRequest = createRequestGET(
                buildGETRequestURL(RequestServiceConstant.BASE_URL,api,this.params),
                this.response);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        BaseApplication.getVolleyInstance().addToRequestQueue(stringRequest);
        while (!response.isGotResponse()) ;
        if (!response.isResponseError()) {
            return;
        }
        throw new Exception(response.getError());
    }


    //utils

    public static String addParamstToGETRequest(String url, Map<String, Object> params) {
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


    public static StringRequest createRequestGET(String url, GeneralResponse modelResponse) {
        return new KasperStringRequest(Request.Method.GET, url, null, modelResponse, modelResponse);
    }

    public static StringRequest createRequestPOSTwithParams(String url, GeneralResponse modelResponse, Map params) {
        return new KasperStringRequest(Request.Method.POST, url, params, modelResponse, modelResponse);
    }

    public static StringRequest createRequestGETwithCustomHeaders(String url, GeneralResponse modelResponse, Map headers, Map params) {
        return new KasperStringRequest(Request.Method.GET, url, params, modelResponse, modelResponse, headers);
    }

    public static com.android.volley.toolbox.StringRequest createRequestPOSTwithJsonObject(String url, GeneralResponse modelResponse, JSONObject object) throws JSONException {
        return new JsonBobyRequest(url, modelResponse, object);
    }

    public static String buildGETRequestURL(String base, String api, Map<String, Object> params){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(api);
        return addParamstToGETRequest(builder.toString(),params);
    }
}
