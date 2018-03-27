package com.example.gabrielepanarello.jsonrequest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Gabriele Panarello on 27/03/2018.
 */

public class ServiceQueueSingleton {
    private static ServiceQueueSingleton mInstance;

    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private ServiceQueueSingleton(Context context){
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized ServiceQueueSingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new ServiceQueueSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
