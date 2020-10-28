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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Crear_restaurante extends AppCompatActivity {
    Button crearButton;
    TextView restaurantButton;
    static final int code_camera = 999;
    private Activity root = this;
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
        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(root,inicio_login.class);
                root.startActivity(intent);*/
               Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               if (camera.resolveActivity(root.getPackageManager()) != null){
                   root.startActivityForResult(camera,code_camera);
               }

            }


        });
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

