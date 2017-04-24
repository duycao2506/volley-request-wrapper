package kaser.com.myandroidframework.framework_components.data.from_server;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;


public class JsonBobyRequest extends StringRequest {

    JSONObject body;
    JSONArray bodyArr;

    public JsonBobyRequest(String url, GeneralResponse response, JSONObject body) {
        super(Method.POST, url, response, response);
        this.body = body;
    }

    public JsonBobyRequest(String url, GeneralResponse response, JSONArray body) {
        super(Method.POST, url, response, response);
        this.bodyArr = body;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return body != null ? body.toString().getBytes() : bodyArr.toString().getBytes();
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }
}
