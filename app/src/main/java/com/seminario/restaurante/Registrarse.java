package com.seminario.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.seminario.restaurante.utils.EndPoints;
import com.seminario.restaurante.utils.UserDataServer;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Registrarse extends AppCompatActivity {
    Button registrarButton;
    private Activity root = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        registrarButton =(Button)findViewById(R.id.registButton);
        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrarse.this, SelecLugar.class);
            }
        });
        loadComponents();

    }

    private void loadComponents() {
        registrarButton= this.findViewById(R.id.registButton);

        registrarButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpClient client = new AsyncHttpClient();

                EditText Nombre = root.findViewById(R.id.nombrec);
                EditText Apellidos = root.findViewById(R.id.apellidosc);
                EditText Direccion = root.findViewById(R.id.direccion);
                EditText CI = root.findViewById(R.id.ci);
                EditText Correo = root.findViewById(R.id.correoc);



                RequestParams params = new RequestParams();
                params.add("Nombre", Nombre.getText().toString());
                params.add("ApellidoP", Apellidos.getText().toString());
                params.add("ApellidoM", Direccion.getText().toString());
                params.add("CI", CI.getText().toString());
                params.add("Correo", Correo.getText().toString());

               // client.addHeader ("authorization", UserDataServer.TOKEN);

                client.post(EndPoints.REGISTRARSE_SERVICE, params, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            if (response.has("msn")) {
                                UserDataServer.MSN = response.getString("msn");
                            }
                            if (UserDataServer.TOKEN.length() > 150) {
                                Intent intent = new Intent(root,inicio_login.class);
                                root.startActivity(intent);
                            } else {
                                Toast.makeText(root, response.getString("msn"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }


        }));

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






