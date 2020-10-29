package com.seminario.restaurante;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.seminario.restaurante.utils.EndPoints;
import com.seminario.restaurante.utils.UserDataServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import cz.msebera.android.httpclient.Header;

public class Crear_Menu extends AppCompatActivity {
    Button insertButton;
    Button tfButton;
    static final int code_camera = 999;
    private Activity root = this;
    ImageView img2;
    String Datos;
    String rutaimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__menu);
        //Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        loadComponents();

    }
    private void loadComponents() {
        tfButton = this.findViewById(R.id.tomarfoto);
        tfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (camera.resolveActivity(root.getPackageManager()) != null){
                    root.startActivityForResult(camera,code_camera);
                }
            }
        });
        insertButton = this.findViewById(R.id.insertar);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();

            EditText Nombre = root.findViewById(R.id.NombreMenu);
            EditText Precio = root.findViewById(R.id.editTextTextPersonName12);
            EditText Descripcion = root.findViewById(R.id.editTextTextPersonName13);

            RequestParams params = new RequestParams();
            params.add("NombreMenu", Nombre.getText().toString());
            params.add("precioMenu", Precio.getText().toString());
            params.add("descripcion", Descripcion.getText().toString());
            //params.add("fotografia_del_producto", img2.getTag().toString());

            //RequestParams params3 = new RequestParams();
            //params3.put("file",img2.getTag().toString());

            client.addHeader("authorization", UserDataServer.TOKEN);

            client.post(EndPoints.CREAR_MENU_SERVICE, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        if (response.has("msn")) {
                            UserDataServer.MSN = response.getString("msn");
                        }
                        if (UserDataServer.TOKEN.length() > 150) {
                                Intent intent = new Intent(root, Lista_Menu.class);
                                root.startActivity(intent);
            
                        } else {
                            Toast.makeText(root, response.getString("msn"), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            client.get(EndPoints.MENU_SERVICE, new JsonHttpResponseHandler(){
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                    try {
                        Datos=response.getString(0);
                        /*String Datos2="";
                        int apoyo=0;
                        for (int ig=0;ig<Datos.length() ;ig ++ ) {
                            if (Datos.contains("_id").charAt(ig)==":"&&Datos.charAt(ig-1)=="d"&&Datos.charAt(ig-2)=="i") {
                                apoyo=1;
                            }
                            if (Datos.charAt(ig)",") {
                                apoyo=0;
                            }
                            if(apoyo==1){
                                Datos2=Datos2+Datos.charAt(ig);
                            }
                            
                        }*/
                        //Toast.makeText(root, /*response.getString(1)*/Datos.substring(7,32), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Toast.makeText(root, errorResponse.toString(), Toast.LENGTH_LONG).show();

                }
            });

            RequestParams params2 = new RequestParams();
            String id=Datos.substring(7,32);
            params2.add("id",id );
            params2.add("file", rutaimage);
            Toast.makeText(root, client.toString(), Toast.LENGTH_LONG).show();
            /*client.put(EndPoints.MENU_UPDATE_SERVICE,params2 ,new JsonHttpResponseHandler(){
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    try {
                        //Datos=response.getString(0);
                        Toast.makeText(root, response.getString(0), Toast.LENGTH_LONG).show();
                        
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
    
                }
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Toast.makeText(root, errorResponse.toString(), Toast.LENGTH_LONG).show();

                }
            });*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == code_camera && resultCode == RESULT_OK){
            Bundle photo = data.getExtras();
            Bitmap imageBitmap = (Bitmap) photo.get("data");
            rutaimage = (String) data.getDataString();
            //Toast.makeText(root, rutaimage, Toast.LENGTH_LONG).show();
            img2 = root.findViewById(R.id.imageButton7);
            img2.setImageBitmap(imageBitmap);
        }
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


