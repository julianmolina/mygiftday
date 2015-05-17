package com.msg.mygiftday;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlLiteHelper extends SQLiteOpenHelper {

    public SqlLiteHelper(Context contexto, String nombre,  CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (id INTEGER, id_user integer, id_facebook INTEGER, id_event INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

    }
}
