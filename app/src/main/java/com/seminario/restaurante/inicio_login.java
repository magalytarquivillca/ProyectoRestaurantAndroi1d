package com.seminario.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class inicio_login extends AppCompatActivity {
    Button crearrestauButton;
    Button crearmenButton;
    private Activity root = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_login);
        Toast.makeText(root, "onCreate", Toast.LENGTH_SHORT).show();
        loadComponents();

    }

    private void loadComponents() {
        crearrestauButton = this.findViewById(R.id.buttonresta);
        crearrestauButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root,Crear_restaurante.class);
                root.startActivity(intent);
    }
        });
        crearmenButton = this.findViewById(R.id.buttonmenu);
        crearmenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root,Crear_Menu.class);
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
}

