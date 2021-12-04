package com.ingcrisman.quehacerhoy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBSQliteHelper extends SQLiteOpenHelper {

    public MyDBSQliteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory c, int version) {
        super(context, nombre, c, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE lugares(_id INTEGER PRIMARY KEY AUTOINCREMENT, ciudad TEXT, lugar TEXT, tipoLugar TEXT, direccion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS lugares");
        db.execSQL("CREATE TABLE lugares(_id INTEGER PRIMARY KEY AUTOINCREMENT, ciudad TEXT, lugar TEXT, tipoLugar TEXT, direccion TEXT)");

    }
}
