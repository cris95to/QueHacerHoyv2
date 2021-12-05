package com.ingcrisman.quehacerhoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class lugares extends AppCompatActivity {
    private MyDBSQliteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor fila;

    private EditText et1, et2, et3, et4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);

        admin = new MyDBSQliteHelper(this, vars.db,null,vars.ver);

        //Activar el supoorte para la ActionBar

        //Ocultar Action Bar
        getSupportActionBar().hide();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getParametros();

        //instanciacion de las variables creadas
        et1 = findViewById(R.id.input_ciudad);
        et2 = findViewById(R.id.input_tipoLugar);
        et3 = findViewById(R.id.input_direccion);
        et4 = findViewById(R.id.input_lugar);


    }

    //creacion de metodos para las acciones de los botones
    public void agregarDatos (View view){
        String ciu = et1.getText().toString();
        String tipLu = et2.getText().toString();
        String dir = et3.getText().toString();
        String lug = et4.getText().toString();

        //condicion para obligar al usuario a ingresar los datos que se piden
        //para que no quede ningun campo vacio

        if(!ciu.equals("")  &&!tipLu.equals("") && !dir.equals("") && !lug.equals("")){
            db = admin.getWritableDatabase();
            cv = new ContentValues();
            cv.put("ciudad", ciu);
            cv.put("lugar", lug);
            cv.put("tipoLugar", tipLu);
            cv.put("direccion", dir);


            long reg = db.insert("lugares",null,cv);
            if(reg==-1){
                Toast.makeText(this, "ERROR...No se pudo agregar el registro", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Registro almacenado ", Toast.LENGTH_SHORT).show();
                //setText("") con esta instruccion los campos una vez llenados y almacenados se limpiaran
                //para volverlos a llenar
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
                et1.requestFocus();//esta instruccion hace que el foco vaya a et1(campo de nombre)
            }
        }else {
            Toast.makeText(this, "Por favor, ingrese los datos", Toast.LENGTH_SHORT).show();
        }

    }

    public void listarDatos(View view){
        //utilizacion de un intent para llamar desde la activity (EmpleadoActivity) a (ListViewActivity)
        //esto para que los datos almacenados en la tabla empleado se puedan ver en una lista en la activity listview
        //para utilizar el list view en todas las tablas de nuestra db utilizamos el siguiente parametro put.extra
        //el intent me permite llamar a otra actividad y con el put extra le envio parametros a esa actividad

        Intent intent = new Intent(lugares.this, ListViewActivity.class);
        intent.putExtra("nomTabla", "lugares");
        startActivity(intent);
    }

    public void eliminarDatos(View view){
        String ciu = et1.getText().toString();
        if(!ciu.equals("")){
            db = admin.getWritableDatabase();
            int reg = db.delete("lugares","ciudad='"+ciu+"'",null);

            if (reg==0)
                Toast.makeText(this, "No se pudo eliminar la ciudad", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Ciudad eliminada correctamente", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Por favor ingrese el nombre de la ciudad a elimninar", Toast.LENGTH_SHORT).show();
    }

    public void buscarDatos(View view){
        String ciu = et1.getText().toString();
        if(!ciu.equals("")) {
            db = admin.getReadableDatabase();
            fila = db.rawQuery("SELECT * FROM lugares WHERE ciudad='" + ciu + "'", null);
            if(fila.getCount()>0){
                String datos="";
                if(fila.moveToFirst()){
                    et2.setText(fila.getString(2));
                    et3.setText(fila.getString(3));
                    et4.setText(fila.getString(4));
                }
                db.close();
            }else
                Toast.makeText(this, "La ciudad no existe", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "por favor ingrese una ciudad para inicar la busqueda", Toast.LENGTH_SHORT).show();
    }

    public void editarDatos(View view){
        String ciu = et1.getText().toString();
        String tipLu = et2.getText().toString();
        String dir = et3.getText().toString();
        String lug = et4.getText().toString();

        //condicion para obligar al usuario a ingresar los datos que se piden
        //para que no quede ningun campo vacio

        if(!ciu.equals("")  &&!tipLu.equals("") && !dir.equals("") && !lug.equals("")){
            db = admin.getWritableDatabase();
            cv = new ContentValues();
            cv.put("lugar", lug);
            cv.put("tipoLugar", tipLu);
            cv.put("direccion", dir);


            long reg = db.update("lugares",cv,"ciudad='"+ciu+"'",null);
            if(reg==0){
                Toast.makeText(this, "ERROR...No se pudo editar la ciudad", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Edicion exitosa, ciudad actualizada ", Toast.LENGTH_SHORT).show();
                //setText("") con esta instruccion los campos una vez llenados y almacenados se limpiaran
                //para volverlos a llenar
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
                et1.requestFocus();//esta instruccion hace que el foco vaya a et1(campo de nombre)
            }
        }else {
            Toast.makeText(this, "Primero vaya a buscar, luego edite y posteriormemnte presione editar", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarDatos (View view){
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et1.requestFocus();//esta instruccion hace que el foco vaya a et1(campo de nombre)
    }


    /*//Destruir la aplicación
    public void onBackPressed() {

        finish();
    }*/
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void getParametros() {
        Bundle extras = getIntent().getExtras();
//        String msg = extras.getString("msg");
//        int year = extras.getInt("year");
//        Toast.makeText(this, msg + " " + year, Toast.LENGTH_SHORT).show();
    }

   /* public void goToActivityMain(View view) {
        Intent newIntent = new Intent(this, MainActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }*/
}