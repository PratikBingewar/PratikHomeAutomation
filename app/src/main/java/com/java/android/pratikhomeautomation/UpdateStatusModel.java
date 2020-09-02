package com.java.android.pratikhomeautomation;

import android.content.Context;
import android.support.v4.os.IResultReceiver;
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

public class UpdateStatusModel {
    public final String  STATUS_URL = "https://iotseminar.000webhostapp.com/Pratik-Home-Automation/update.php";
    HAPresenter haPresenter;
    RequestQueue requestQueue;
    boolean CONNECTION_STATUS;
    StringRequest stringRequest;
    Integer ButtonNumber,Status;
    MainActivity mainActivity;
    public UpdateStatusModel(HAPresenter haPresenter, MainActivity mainActivity, int buttonNumber, int status) {
        this.haPresenter = haPresenter;
        this.mainActivity = mainActivity;
        this.ButtonNumber = buttonNumber;
        this.Status = status;
        requestQueue = Volley.newRequestQueue((Context) mainActivity);
    }

    public void authenticate() {
        Log.d("status","in authenticate");
        stringRequest = new StringRequest(Request.Method.POST, STATUS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
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
                String BtnNum = ButtonNumber.toString();
                String BtnStatus = Status.toString();
                Log.d("status",BtnNum+" "+BtnStatus);

                param.put("buttonNumber",BtnNum);
                param.put("status",BtnStatus);
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
            String res=obj.getString("res");

            if (res.equals("Y")) {
                Log.d("Pratik","In yes of Model error");
                haPresenter.onSuccessfulUpdate(ButtonNumber,Status);
            } else {
                Log.d("Pratik","In No of Model error");
                haPresenter.onFailedUpdate();
            }
        } catch (Exception ex) {
            Log.d("Pratik","In exception: "+ex.toString());
            haPresenter.onFailedUpdate();
        }
    }

}
