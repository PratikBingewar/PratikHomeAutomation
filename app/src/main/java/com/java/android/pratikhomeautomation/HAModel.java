package com.java.android.pratikhomeautomation;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HAModel {
    public final String  STATUS_URL = "https://iotseminar.000webhostapp.com/Pratik-Home-Automation/status.php";
    HAPresenter haPresenter;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    StringRequest stringRequest;

    public HAModel(HAPresenter haPresenter, MainActivity mainActivity) {
        this.haPresenter = haPresenter;
        requestQueue = Volley.newRequestQueue((Context) mainActivity);
    }

    public void authenticate() {
        Log.d("status","in authenticate");
        stringRequest = new StringRequest(Request.Method.POST, STATUS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        checkResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("status","HAmodel: In volley error");
                        changeConnectionStatus(false);
                        haPresenter.onFailedDataFetch();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String,String>();
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void changeConnectionStatus(boolean val) {
        CONNECTION_STATUS = val;
    }

    public void checkResponse(String response){
        try {
            JSONObject obj = new JSONObject(response);
            String switch1=obj.getString("switch 1");
            String switch2=obj.getString("switch 2");
            String switch3=obj.getString("switch 3");
            String switch4=obj.getString("switch 4");

            String light1=obj.getString("light 1");
            String light2=obj.getString("light 2");
            String light3=obj.getString("light 3");
            String fan=obj.getString("fan");

            Log.d("status","switch 1"+switch1+"switch 2"+switch2+"switch 3"+switch3+"switch 4"+switch4);
            Log.d("status","light 1"+light1+"light 2"+light2+"light 3"+light3+"fan"+fan);

            changeConnectionStatus(true);
            haPresenter.onSuccessfulDataFetch(switch1,switch2,switch3,switch4,light1,light2,light3,fan);


        } catch (Exception ex) {
            Log.d("status","In exception ");
            changeConnectionStatus(false);
            haPresenter.onFailedDataFetch();
        }
    }

}
