package com.example.gabrielepanarello.jsonrequest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        final TextView output = (TextView) findViewById(R.id.output);

        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading..");
        dialog.show();

        String url = "https://androidtutorialpoint.com/api/volleyJsonObject";

        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(url, null,
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
        });

        ServiceQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectReq);
    }

}

