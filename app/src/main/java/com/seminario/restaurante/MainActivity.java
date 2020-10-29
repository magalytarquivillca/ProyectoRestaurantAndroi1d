package com.seminario.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.seminario.restaurante.utils.EndPoints;
import com.seminario.restaurante.utils.UserDataServer;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Button loginButton;
    private MainActivity root = this;
    TextView h_pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        loadComponents();
    }

    private void loadComponents() {
        loginButton = this.findViewById(R.id.accederbut);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root,inicio_login.class);
                root.startActivity(intent);
            }
        });
        h_pedidos = this.findViewById(R.id.textView2);
        h_pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root,SelecLugar.class);
                root.startActivity(intent);
            }
        });
        /*loginButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = root.findViewById(R.id.editTextTextPersonName);
                EditText password = root.findViewById(R.id.editTextTextPersonName2);

                /*if (email.toString().length() == 0) {
                    Toast.makeText(root, "correo obligatorio", Toast.LENGTH_SHORT).show();
                }
                if (password.toString().length() == 0) {
                    Toast.makeText(root, "contaseña obligatorio ", Toast.LENGTH_SHORT).show();
                }
                if (email.length() != 0 && password.length() != 0){*/

                /*
                //ENVIO A LA API
                AsyncHttpClient client = new AsyncHttpClient();
               // AsyncHttpClient client1 = new AsyncHttpClient();
                RequestParams params = new RequestParams();


                params.add("nombre", email.getText().toString());
                params.add("CI", password.getText().toString());

                client.post(EndPoints.LOGIN_SERVICE, params, new JsonHttpResponseHandler(){

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            if (response.has("msn")) {
                                UserDataServer.MSN = response.getString("msn");
                            }
                            if (response.has("token")) {
                                UserDataServer.TOKEN = response.getString("token");
                            }
                            if (UserDataServer.TOKEN.length() > 150) {
                                Intent intent = new Intent(root, inicio_login.class);
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
        }));*/

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
