package com.ingcrisman.quehacerhoy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    private MyDBSQliteHelper admin;
    private SQLiteDatabase db;
    private Cursor fila;

    private ListView lv1;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //Activar soporte para la Actionbar (boton volver <-)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        admin = new MyDBSQliteHelper(this,vars.db,null,vars.ver);

        Bundle extras = getIntent().getExtras();
        String nomTabla = extras.getString("nomTabla");//para que el nombre de la tabla salga en el action bar

        setTitle(nomTabla);


        lv1 = findViewById(R.id.listView);
        ArrayList<String> listado = new ArrayList<String>();


        db = admin.getReadableDatabase();
        fila  = db.rawQuery("SELECT * FROM lugares", null);
        while(fila.moveToNext()){
            listado.add(fila.getString(0) + " - "
                    +"CIUDAD: "+ fila.getString(1) +"\n"
                    +"LUGAR: "+ fila.getString(2)+"\n"
                    +"TIPO DE LUGAR: "+fila.getString(3)+"\n"
                    +"DIRECCION: "+fila.getString(4));

        }
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listado);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(getApplicationContext(), "Posición: "+pos+"\n"+lv1.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Destruir la aplicación
    public void onBackPressed() {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

}