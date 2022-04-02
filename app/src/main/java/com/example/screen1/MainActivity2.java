 package com.example.screen1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    Sql_class.DatabaseHelper mydb;
    //public Button button;
    //ListView list_view;
    Sql_class databaseAdapter;


    //EditText name, email, nameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        setContentView(R.layout.activity_main1);
        ListView lvCont = findViewById(R.id.lvContact);
        databaseAdapter = new Sql_class(this);
        CursorAdapter simpleCursorAdapter = databaseAdapter.populateListViewFromDB();
        //button = (Button) findViewById(R.id.back);
        lvCont.setAdapter(simpleCursorAdapter);
        mydb=new Sql_class.DatabaseHelper(this);
         function2(lvCont);
        /*lvCont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mydb.deleteData("4");
            }
        });*/
    }
    public void function2(ListView lvCont) {
        lvCont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mydb.deleteData(String.valueOf(id));
                onResume();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        setContentView(R.layout.activity_main1);
        ListView lvCont = findViewById(R.id.lvContact);
        databaseAdapter = new Sql_class(this);
        CursorAdapter simpleCursorAdapter = databaseAdapter.populateListViewFromDB();
        //button = (Button) findViewById(R.id.back);
        lvCont.setAdapter(simpleCursorAdapter);
        mydb=new Sql_class.DatabaseHelper(this);
        function2(lvCont);
    }




    //Sql_class.DatabaseHelper mydb;


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView();
        button = (Button) findViewById(R.id.back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
    }

    public void listView() {
        ListView listView = (ListView) findViewById(R.id.the_listView_widget);
       Sql_class.DatabaseHelper myDB2 = new Sql_class.DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB2.getNameExp();
        //toast is a pop up that displays messages
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }
    }*/

   /* public void listView() {
        list_view = (ListView) findViewById(R.id.eee);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stuff);
        // ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.activity_main3.activity_main2, stuff);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) list_view.getItemAtPosition(position);
                Toast.makeText(MainActivity2.this, "position : " + position + " Value :" + value, Toast.LENGTH_LONG).show();
            }
        });
    }*/

    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}


