package com.example.ejercicio3cm;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MySinglet {
    private static MySinglet mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;
    private MySinglet(Context context){
        mCtx=context;
        requestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized MySinglet getInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance= new MySinglet(context);
        }
        return mInstance;
    }
    public void addToRequest(JsonObjectRequest request)
    {
        requestQueue.add(request);

    }

}
