package com.example.screen1;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {
    Button add, view,update,delete;
     Sql_class.DatabaseHelper mydb;
    EditText task_name1,priority1,expire1, idEdit;
// interfaces with Sql_class. Example of facad design pattern? all the Cursor mydb.methods() are in Sql_class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mydb=new Sql_class.DatabaseHelper(this);
        task_name1=(EditText)findViewById(R.id.task_name);
        priority1=(EditText)findViewById(R.id.priority);
        idEdit = (EditText) findViewById(R.id.IdInput);
        expire1=(EditText)findViewById(R.id.expire);
        add =(Button)findViewById(R.id.button_add);
        view =(Button)findViewById(R.id.button_view);
        update =(Button)findViewById(R.id.update1);
        delete=(Button)findViewById(R.id.del);
        AddData();
        viewAll();
        UpdateData();
        Delete();

    }
    public void Delete(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRow=mydb.deleteData(idEdit.getText().toString());
                if(deleteRow>0){
                    Toast.makeText(MainActivity4.this,"data deleted",Toast.LENGTH_LONG).show();}
                else{
                    Toast.makeText(MainActivity4.this,"data not deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void viewAll(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb.getAllData();
                if(res.getCount()==0){
                    showmessage("Error:","no entries");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("id: "+res.getString(0)+"\n");
                    buffer.append("taskname: "+res.getString(1)+"\n");
                    buffer.append("priority: "+res.getString(2)+"\n");
                    buffer.append("expire: "+res.getString(3)+"\n");
                }
                showmessage("data",buffer.toString());
            }
        });
    }
    public void showmessage(String title, String Message){
        Context context;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void AddData(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.insertData(task_name1.getText().toString(),priority1.getText().toString(),expire1.getText().toString());
                Toast.makeText(MainActivity4.this,"data inserted",Toast.LENGTH_LONG).show();
                runthread();
            }

        });
    }
    public void UpdateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { boolean isUpdate= mydb.updateData(idEdit.getText().toString(),task_name1.getText().toString(),priority1.getText().toString(),expire1.getText().toString());
            if(isUpdate==true){
                Toast.makeText(MainActivity4.this,"data added",Toast.LENGTH_LONG).show();}
            else{
                Toast.makeText(MainActivity4.this,"data not added",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void runthread() {

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
