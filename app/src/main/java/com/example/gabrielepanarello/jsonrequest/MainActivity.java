package com.example.gabrielepanarello.jsonrequest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

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
