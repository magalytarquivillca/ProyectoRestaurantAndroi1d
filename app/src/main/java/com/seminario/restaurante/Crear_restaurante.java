package com.seminario.restaurante;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.seminario.restaurante.utils.EndPoints;
import com.seminario.restaurante.utils.UserDataServer;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Crear_restaurante extends AppCompatActivity {
    Button crearButton;
    TextView restaurantButton;
    static final int code_camera = 999;
    private Activity root = this;
    ImageView img;
    Boolean confirmationcamera=false;
    static final int PERMISION_CODE = 10;
    private Button Ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_restaurante);
        Ubicacion = (Button)findViewById(R.id.verUbicacion);
        Ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Crear_restaurante.this, Ubicacion.class);
                startActivity(intent);
            }
        });
        //Toast.makeText(root, "onCreate", Toast.LENGTH_SHORT).show();
        loadComponents();

    }

    private void loadComponents() {
        restaurantButton= this.findViewById(R.id.textView3);
        /*restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(root,inicio_login.class);
                //root.startActivity(intent);
               Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               if (camera.resolveActivity(root.getPackageManager()) != null){
                   root.startActivityForResult(camera,code_camera);
               }

            }


        });*/
        restaurantButton.setOnClickListener((new View.OnClickListener() {
        @Override
            public void onClick(View view) {

                if (checkPermission(Manifest.permission.CAMERA)) {
                    callCamera();
                //Toast.makeText(this, "TIENES PERMISOS 🤔", Toast.LENGTH_LONG).show();
                } else {
                    requestPermission();
                //Toast.makeText(this, "No tienes los permisos 😌", Toast.LENGTH_LONG).show();
                }
                if (confirmationcamera==true) {
                    
                
                
                    AsyncHttpClient client = new AsyncHttpClient();

                    EditText Nombre = root.findViewById(R.id.editTextTextPersonName3);
                    EditText Nit = root.findViewById(R.id.editTextTextPersonName4);
                    EditText Propietario = root.findViewById(R.id.editTextTextPersonName5);
                    EditText Calle = root.findViewById(R.id.editTextTextPersonName6);
                    EditText Telefono = root.findViewById(R.id.editTextTextPersonName7);
                    EditText Lugarmapslat = root.findViewById(R.id.editTextTextPersonName8);   //verificar el dato de maps
                    EditText Lugarmapslong = root.findViewById(R.id.editTextTextPersonName9);
                    

                    RequestParams params = new RequestParams();
                    params.add("nombreRestaurante", Nombre.getText().toString());
                    params.add("nit", Nit.getText().toString());
                    params.add("propietario", Propietario.getText().toString());
                    params.add("calle", Calle.getText().toString());
                    params.add("longitud", Lugarmapslong.getText().toString());
                    params.add("latitud", Lugarmapslat.getText().toString());
                    params.add("telefono",Telefono.getText().toString());
                    //RequestParams params2 = new RequestParams();
                    //RequestParams params3 = new RequestParams();
                    //params2.put("file","C:\\Users\\Adm\\Pictures\\5561fe841a054_luzMargG.png");//img.getResources().toString());
                    //params3.put("file","C:\\Users\\Adm\\Pictures\\5561fe841a054_luzMargG.png");//img.getResources().toString());

                    client.addHeader ("authorization",UserDataServer.TOKEN);

                    client.post(EndPoints.CREAR_RESTAURANT_SERVICE, params, new JsonHttpResponseHandler(){
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
                    /*RequestParams params4 = new RequestParams();
                    params4.put("file",img.getResources().toString());



                    //verificar aun
                    client.put(EndPoints.UPDATE_LOGO_SERVICE, params2, new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                if (response.has("msn")) {
                                    UserDataServer.MSN = response.getString("msn");
                                } else {
                                    Toast.makeText(root, response.getString("msn"), Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    client.put(EndPoints.UPDATE_LUGAR_SERVICE, params3, new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                if (response.has("msn")) {
                                    UserDataServer.MSN = response.getString("msn");
                                } else {
                                    Toast.makeText(root, response.getString("msn"), Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    //hasta aqui*/
                }
                else{
                Toast.makeText(root, "se requiere una foto para continuar", Toast.LENGTH_LONG).show();
                callCamera();
                }
            }
        }));
    
        crearButton = this.findViewById(R.id.crear);
        crearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root, Lista_Restaurant.class);
                root.startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == code_camera && resultCode == RESULT_OK){
            Bundle photo = data.getExtras();
            Bitmap imageBitmap = (Bitmap) photo.get("data");
            ImageView img = root.findViewById(R.id.logorest);
            img.setImageBitmap(imageBitmap);
            confirmationcamera=true;
            
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISION_CODE) {
            if (permissions.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callCamera();
                } else {
                   Toast.makeText(this, "No podemos seguir con el registro porque es necesaria una foto tuya 😭", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISION_CODE);
        }
    }
    public Boolean checkPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    /*@Override
    public  void onClick(View view){
        if (checkPermission(Manifest.permission.CAMERA)) {
            callCamera();
        //Toast.makeText(this, "TIENES PERMISOS 🤔", Toast.LENGTH_LONG).show();
        } else {
            requestPermission();
        //Toast.makeText(this, "No tienes los permisos 😌", Toast.LENGTH_LONG).show();
        }
    };*/
    private void callCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (camera.resolveActivity(this.getPackageManager()) != null) {
            this.startActivityForResult(camera, code_camera);
        }
    };

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

