package com.seminario.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.seminario.restaurante.utils.EndPoints;
import com.seminario.restaurante.utils.UserDataServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class hacer_Pedidos extends AppCompatActivity {

    Button agregarButton;
    private Activity root = this;
    TextView Datos;
    ImageButton imagenes;
    onLoadData interfaceevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer__pedidos);
        loadComponents();
    }

    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient();
        Datos=root.findViewById(R.id.des_prec1);
        imagenes=root.findViewById(R.id.imageButton1);
        client.addHeader("authorization", UserDataServer.TOKEN);
        client.get(EndPoints.MENU_SERVICE, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                interfaceevent.onJsonArrayLoad(response);
                try {
                    Datos.setText(response.getString(1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(root, response.toString(), Toast.LENGTH_LONG).show();

            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                interfaceevent.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
        //Datos.setText(interfaceevent.toString());
        /*onLoadData Dats = interfaceevent;
        for(int i=0;i<Data.length();i++){
            Datos.setText(Dats[i].("nombreMenu")+" "+Dats[i].("precioMenu"));
            Bitmap imageBitmap = (Bitmap) Dats [i].("fotografia_del_producto["+i+"].relativepath");
            imagenes.setImageBitmap(imageBitmap);
        }*/
        agregarButton = this.findViewById(R.id.agregar1);
        agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root, hacer_Pedidos2.class);
                root.startActivity(intent);
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
    public interface onLoadData {
        void onJsonLoad(JSONObject data);
        void onJsonArrayLoad(JSONArray data);
        void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse);
    }
}