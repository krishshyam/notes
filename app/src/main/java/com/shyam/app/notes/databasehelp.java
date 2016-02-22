package com.shyam.app.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HP INDIA on 19-02-2016.
 */
public class databasehelp extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Notes";

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE = "create table mynotes ( _id integer primary key,note text not null);";

    public databasehelp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){
        Log.w(databasehelp.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS mynotes");
        onCreate(database);
    }
}
