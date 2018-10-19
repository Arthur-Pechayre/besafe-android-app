package com.example.bgautier.besafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AlertResponseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_notification);
        Intent intent = getIntent();
        // Set up the login form.

        final String userId = intent.getStringExtra("userId");
        final String responseId = intent.getStringExtra("stringId");
        final String token = intent.getStringExtra("id");


        Button go_button = (Button) findViewById(R.id.go_button);
        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callApiGo(userId, responseId, token);
            }
        });


        Button nope_button = (Button) findViewById(R.id.nope_button);
        nope_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



    }

    public void callApiGo(String userId, String responseId, String accessToken){

        RequestQueue queue = Volley.newRequestQueue(this);
        String trueUrl ="http://hdaroit.fr:3000/api/appusers/" + userId + "/responses/" + responseId + "/resolve?access_token=" + accessToken;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, trueUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ALERT go",response);
                        startIntent();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!","");
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                return new HashMap<String, String>();
            }
        };


// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void startIntent( ){
        Log.d("toto","");
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(intent,0);
    }



}
