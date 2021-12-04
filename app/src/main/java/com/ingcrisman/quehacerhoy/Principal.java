package com.ingcrisman.quehacerhoy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Fragment fragmento = new MapasFragment();//este es el fragmento que vamos a poner en el contenedor

        //este codigo pone el fragment dentro del activity principal
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragmento).commit();

        //Ocultar Action Bar
        getSupportActionBar().hide();
    }
}