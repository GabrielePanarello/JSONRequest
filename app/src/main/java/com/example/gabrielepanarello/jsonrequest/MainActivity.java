package com.example.gabrielepanarello.jsonrequest;

import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText input = (EditText) findViewById(R.id.inputURL);
        Button send = (Button) findViewById(R.id.ctaInvia);
        final ImageView result = (ImageView) findViewById(R.id.result);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Loading..");
                dialog.show();

                String imageUrl = String.valueOf(input.getText());

                ImageRequest imageRequest = new ImageRequest(
                        imageUrl, // Image URL
                        new Response.Listener<Bitmap>() { // Bitmap listener
                            @Override
                            public void onResponse(Bitmap response) {
                                // Do something with response
                                //result.setImageBitmap(response);

                                /* Save this downloaded bitmap to internal storage*/
                                Uri uri = saveImageToInternalStorage(response);

                                // Display the internal storage saved image to image view
                                result.setImageURI(uri);
                                dialog.hide();
                            }
                        },
                        600, // Image width
                        600, // Image height
                        ImageView.ScaleType.CENTER_CROP, // Image scale type
                        Bitmap.Config.RGB_565, //Image decode configuration
                        new Response.ErrorListener() { // Error listener
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Do something with error response
                                error.printStackTrace();
                            }
                        });

                ServiceQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(imageRequest);
            }
        });


    }

    protected Uri saveImageToInternalStorage(Bitmap bitmap) {
        // Initialize ContextWrapper
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());

        // Initializing a new file
        // The bellow line return a directory in internal storage
        File file = wrapper.getDir("Images", MODE_PRIVATE);

        // Create a file to save the image
        file = new File(file, "UniqueFileName" + ".jpg");

        try {
            // Initialize a new OutputStream
            OutputStream stream = null;

            // If the output file exists, it can be replaced or appended to it
            stream = new FileOutputStream(file);

            // Compress the bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            // Flushes the stream
            stream.flush();

            // Closes the stream
            stream.close();

        } catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }

        // Parse the gallery image url to uri
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());

        // Return the saved image Uri
        return savedImageURI;
    }
}

