package com.seminario.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.seminario.restaurante.utils.EndPoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Lista_Restaurant extends AppCompatActivity {
    String Datos[];
    private Activity root = this;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__restaurant);
        //Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        loadComponents();
    }

    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(EndPoints.CREAR_RESTAURANT_SERVICE, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                try {
                    for (int i =0;i<3/*response.length()*/;i++) {
                        Datos[i] = response.getString(i);
                        texto=root.findViewById(R.id.textView17);
                        texto.setText(Datos[i]);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(root, errorResponse.toString(), Toast.LENGTH_LONG).show();

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

}