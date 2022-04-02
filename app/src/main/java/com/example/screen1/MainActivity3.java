package com.example.screen1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity3 extends AppCompatActivity {
    public Button button;
    private static final String TAG="CalenderActivity";
    private CalendarView myCalendarView;
    //public ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button=(Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        myCalendarView=(CalendarView)findViewById(R.id.calendarView);
        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month1, int dayOfMonth) {
                int month=month1+1;
                String year1 = Integer.toString(year);
                String month2 = Integer.toString(month);
                String dayOfMonth1=Integer.toString(dayOfMonth);
                //String s=dayOfMonth+"/"+month2+"/"+year1;
                //String s=dayOfMonth+month2+year1;
                String s=month2+dayOfMonth+year1;
                System.out.println(s);
                listView(s);
            }
        });
    }
    public void listView(String s) {
        ListView listView = (ListView) findViewById(R.id.listview2);
        Sql_class.DatabaseHelper myDB2 = new Sql_class.DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB2.getNameExp2(s);
        //toast is a pop up that displays messages
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else {
            while (data.moveToNext()) {
                theList.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }}
    public void showmessage(String title, String Message){
        Context context;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    /*public void listView() {
        final ListView listview = (ListView) findViewById(R.id.listview2);
        String[] stuff={"a","b","c"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stuff);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.activity_main3.activity_main2, stuff);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) listview.getItemAtPosition(position);
                Toast.makeText(MainActivity3.this, "position : " + position + " Value :" + value, Toast.LENGTH_LONG).show();
            }
        });*/

    public void openActivity() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}