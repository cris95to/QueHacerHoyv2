package com.ingcrisman.quehacerhoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InicioSesion2 extends AppCompatActivity implements View.OnClickListener{
    EditText user, pass;
    Button bntEntrar, btnRegistrar;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion2);
        user=(EditText)findViewById(R.id.User);
        pass=(EditText)findViewById(R.id.Pass);
        bntEntrar=(Button)findViewById(R.id.btnEntrar);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);
        bntEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dao=new daoUsuario(this);
    }

    @Override
    //creacion de un metodo onclick que como su nombre lo indica es el encargado de redireccionar a donde el boton lo indique
    public void onClick(View v) {
        switch(v.getId())/*v es la abreviatura de view*/{
            case R.id.btnEntrar:
                String u=user.getText().toString();
                String p=pass.getText().toString();
                if (u.equals("") && p.equals("")) {
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.login(u,p)==1){
                    Usuario ux=dao.getUsuario(u,p);
                    Toast.makeText(this, "Datos correctos", Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(InicioSesion2.this,SelecDestinoActividad.class);
                    i2.putExtra("Id", ux.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnRegistrar:
                /*Intent: sirve para invocar componentes, en android entendemos por componentes las activities*/
                Intent i=new Intent(InicioSesion2.this,Registro2.class);
                startActivity(i);
                break;
        }
    }

    /*public void goToRegistro(View view) {
        Intent newIntent = new Intent(this, Registro2.class);
        startActivity(newIntent);
    }*/

    //Destruir la aplicacion
    public void onBackPressed(){
        finish();
    }

    //Averiguar que boton fue el que se presiono
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void goToMenu2(View view) {
        Intent newIntent = new Intent(this, InicioSesion2.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }



}