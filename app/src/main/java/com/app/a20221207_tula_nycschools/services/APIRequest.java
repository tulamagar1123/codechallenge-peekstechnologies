package com.app.a20221207_tula_nycschools.services;
import static com.app.a20221207_tula_nycschools.helper.JsonParser.*;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class APIRequest {
    private RequestQueue requestQueue;
    String base_url="https://data.cityofnewyork.us/resource/";
    private boolean cached = true;
    private Context context;

    public APIRequest(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }

    // JSON array request for fetching data
    public <T> void getArrayRequest(String url, final Class clazz, final ArrayCallback<T> callback) {
       JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, base_url+url, null, response -> {
           Log.e("response",response.toString()+"->");
           if (callback != null) {
               callback.onData(toList(response.toString(), clazz));
           }
       }, error -> {
           if (callback != null) {
               setErrorMsg(error, callback);
           }
       }) {
           @Override
           public Map<String, String> getHeaders() throws AuthFailureError {
               Map<String, String> map = new HashMap<>();
               return map;
           }
       };
       addRequest(request);
    }

    // Set error message in case api did not return data successfully
    private void setErrorMsg(VolleyError error, ArrayCallback callback) {
        NetworkResponse response = error.networkResponse;
        if (response != null) {
            String errorMsg = new String(error.networkResponse.data).replaceAll("^\"|\"$", "");
            callback.onError(errorMsg);
        } else {
            callback.onError("Something Went Wrong...");
        }
    }

    // Set request properties
    private void addRequest(Request request) {
        long timeout = TimeUnit.SECONDS.toMillis(60);
        int maxRetries = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
        float backoffMultiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        RetryPolicy retryPolicy = new DefaultRetryPolicy((int) timeout, maxRetries, backoffMultiplier);
        request.setRetryPolicy(retryPolicy);
        request.setShouldCache(true);
        requestQueue.add(request);
    }
}
