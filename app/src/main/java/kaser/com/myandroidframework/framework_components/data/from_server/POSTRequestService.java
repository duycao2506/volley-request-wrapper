package kaser.com.myandroidframework.framework_components.data.from_server;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import kaser.com.myandroidframework.framework_components.utils.MyCallBack;

/**
 * Created by admin on 4/24/17.
 */

public class POSTRequestService extends RequestService {

    JSONObject jsonObject;

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public POSTRequestService(String api, MyCallBack caller, GeneralResponse response) {
        super(api, caller, response);
    }

    @Override
    protected StringRequest buildStringRequest(String url) {
        if (jsonObject == null)
            return createRequestPOST(url,response,params,headers);
        return createRequestPOST( url, response, jsonObject, headers);
    }

}
