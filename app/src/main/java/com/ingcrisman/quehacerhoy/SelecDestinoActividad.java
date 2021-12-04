package com.ingcrisman.quehacerhoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelecDestinoActividad extends AppCompatActivity implements View.OnClickListener {
    Button btnSalir;
    TextView nombre;

    int id=0;
    Usuario u;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_destino_actividad);

        //Ocultar Action Bar
        getSupportActionBar().hide();

        nombre=(TextView) findViewById(R.id.nombreUsuario);
        btnSalir=(Button)findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this);


        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");
        dao=new daoUsuario(this);
        u=dao.getUsuarioById(id);

        nombre.setText(u.getNombre()+" "+u.getApellidos());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnAgregarRuta:
                Intent i3=new Intent(SelecDestinoActividad.this,lugares.class);
                startActivity(i3);
                finish();
                break;

            case R.id.btnSalir:
                Intent i2=new Intent(SelecDestinoActividad.this,InicioSesion2.class);
                startActivity(i2);
                finish();
                break;

        }
    }
}