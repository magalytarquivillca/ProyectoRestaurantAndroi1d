package com.seminario.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Lista_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__menu);
        loadComponents();
        /*client.get(EndPoints.MENU_SERVICE, new JsonHttpResponseHandler(){
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
                    /*} catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Toast.makeText(root, errorResponse.toString(), Toast.LENGTH_LONG).show();

                }
            });*/

    }

    private void loadComponents() {
    }


}