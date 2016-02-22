package com.shyam.app.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by HP INDIA on 19-02-2016.
 */
public class mydb {

    private databasehelp dbhelp;
    private SQLiteDatabase database;

    public final static String NOTE_TABLE="mynotes"; // name of table

    public final static String NOTE_ID="_id"; // id value for employee
    public final static String NOTE="note";  // name of employee

    public mydb(Context context){
        dbhelp = new databasehelp(context);
        database = dbhelp.getWritableDatabase();
    }


    public long createRecords(String id, String note){
        ContentValues values = new ContentValues();
        values.put(NOTE_ID, id);
        values.put(NOTE, note);
        return database.insert(NOTE_TABLE, null, values);
    }

    public Cursor selectRecords() {
        String[] cols = new String[] {NOTE_ID, NOTE};
        Cursor mCursor = database.query(true, NOTE_TABLE,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public Cursor allrecords(Context context){
        dbhelp = new databasehelp(context);
        Cursor acursor = dbhelp.getReadableDatabase().rawQuery("select NOTE from mynotes",null);
        return acursor;
    }
}

