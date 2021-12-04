package com.ingcrisman.quehacerhoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
EditText username, password;
Button singin;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameSingin);
        password = findViewById(R.id.passwordSingin);
        singin = findViewById(R.id.btnSingin);
        DB = new DBHelper(this);

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(LoginActivity.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.CheckUsernamepassword(user, pass);
                    if (checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Inicio de sesion correcto", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), lugares.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Inicio de sesion incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}