package com.ecommerce.networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ecommerce.models.ProductList;
import com.ecommerce.R;
import com.ecommerce.application.AppController;
import com.ecommerce.interfaces.NetworkRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkService {
    private static final Object lock = new Object();
    private static NetworkService mInstance;


    private NetworkService() {

    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            synchronized (lock) {
                if (mInstance == null) {
                    mInstance = new NetworkService();
                }
            }
        }
        return mInstance;
    }

    public void NetworkObjectRequest(String url, Context context, final NetworkRequest networkRequest) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                TypeToken<List<ProductList>> token = new TypeToken<List<ProductList>>() {
                };
                ArrayList<ProductList>  productsList = null;
                try {
                    productsList = gson.fromJson(response.getJSONArray("data").toString(), token.getType());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                networkRequest.OnSuccess(productsList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, context.getResources().getString(R.string.app_name));
    }
}
