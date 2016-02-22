package com.shyam.app.notes;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<String> notelist = new ArrayList<String>();

    ListView listview;
    ArrayAdapter<String> arrayadapter;
    Typeface lato;
    EditText edittext;
    int dbid = 0;
    String widgettext;

    public mydb notedb;
    public notesadded notif;
    public noteswidget widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext = (EditText) findViewById(R.id.editText);
        edittext.clearFocus();
        lato = Typeface.createFromAsset(getAssets(), "fonts/Lato-Regular.ttf");

        notedb = new mydb(getApplicationContext());
        listview = (ListView) findViewById(R.id.notelist);

        arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notelist);
        listview.setAdapter(arrayadapter);
        edittext.setTypeface(lato);


    }


    public Typeface getLato() {
        return lato;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            notelist.removeAll(notelist);
            arrayadapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    public void populate(View v) {
        edittext = (EditText) findViewById(R.id.editText);
        if (!(edittext.getText().toString().matches(""))) {
            notelist.add("" + edittext.getText());
            notedb.createRecords(Integer.toString(dbid), edittext.getText().toString());
            edittext.setText("");
            edittext.clearFocus();
            arrayadapter.notifyDataSetChanged();

            notif.notify(getApplicationContext(),edittext.getText().toString(),0);
            dbid++;

        }
    }

}