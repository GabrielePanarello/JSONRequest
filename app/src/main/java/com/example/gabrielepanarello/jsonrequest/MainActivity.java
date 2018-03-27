package com.example.gabrielepanarello.jsonrequest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView screen = (TextView) findViewById(R.id.screen);
        final TextView name = (TextView) findViewById(R.id.name);
        final TextView os = (TextView) findViewById(R.id.os);

        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading..");
        dialog.show();

        /*JsonObjectRequest jsonObjectReq = new JsonObjectRequest("https://androidtutorialpoint.com/api/volleyJsonObject", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        output.setText(response.toString());
                        Log.d("SERVICE", "Response: " + response.toString());
                        dialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("VOLLEY", "Error: " + error.getMessage());
                dialog.hide();
            }
        });*/
        // Access the RequestQueue through your singleton class.


        GsonRequest jsonObjectReq = new GsonRequest("https://androidtutorialpoint.com/api/volleyJsonObject", DeviceOutput.class, null,
                new Response.Listener<DeviceOutput>() {
                    @Override
                    public void onResponse(DeviceOutput response) {
                        os.setText(response.getOperatingSystem());
                        name.setText(response.getName());
                        screen.setText(response.getScreenSize());
                        dialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.hide();
            }
        });

        ServiceQueueSingleton.getInstance(this).addToRequestQueue(jsonObjectReq);
    }
}
