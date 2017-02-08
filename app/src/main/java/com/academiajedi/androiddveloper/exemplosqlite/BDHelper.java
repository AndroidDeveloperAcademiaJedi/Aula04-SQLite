package com.academiajedi.androiddveloper.exemplosqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexsoaresdesiqueira on 08/02/17.
 */

public class BDHelper extends SQLiteOpenHelper {

    private static final String NAME_BD = "ACADEMIA_JEDI_ANDROID";
    private static final int VERSION = 1;

    public BDHelper(Context context) {
        super(context, NAME_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario(_id integer primary key autoincrement, nome text not null, idade int not null, sexo text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE usuario;");
        onCreate(db);

    }
}
