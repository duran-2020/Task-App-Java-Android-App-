package com.example.screen1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
//sql class is a facad
public class Sql_class {
    DatabaseHelper helper;
    SQLiteDatabase db;
    Context context;

    public Sql_class(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        this.context=context;
    }
    public SimpleCursorAdapter populateListViewFromDB()  {
        String columns[]={DatabaseHelper.COL_1,DatabaseHelper.COL_2,DatabaseHelper.COL_4};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,columns,null,null,null,null,"expire");
        String[] fromFieldNames=new String[]{
                DatabaseHelper.COL_1,DatabaseHelper.COL_2,DatabaseHelper.COL_4
        };
        int[] toViewIDs=new int[]{R.id.item_id,R.id.item_name,R.id.item_email};

        SimpleCursorAdapter contactAdapter=new SimpleCursorAdapter(context, R.layout.single_item, cursor, fromFieldNames, toViewIDs);
        return contactAdapter;
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        // industry design pattern to have final strings for security purposes. (see SQL injection for more info). you could could have simply wrote these string contents inside rawQuery or execSQL methods
        public static final String DATABASE_NAME = "task.db";
        public static final String TABLE_NAME = "task";
        public static final String COL_1="_id";
        public static final String COL_2 = "task_name";
        public static final String COL_3 = "priority";
        public static final String COL_4 = "expire";

        // forgot what it does
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        // creats SQL table
        public void onCreate(SQLiteDatabase db) {
            //db.execSQL("create table "+ TABLE_NAME+ " ("+ COL_1+" integer primary key autoincrement, "+ COL_2 + " text, "+ KEY_EMAIL+ " text)");
            db.execSQL("create table " + TABLE_NAME +"(_id INTEGER PRIMARY KEY AUTOINCREMENT,task_name text,priority text,expire text)");
        }

        @Override
        // distroy table
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }

        public void insertData(String task_name ,String priority ,String expire) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2,task_name);
            contentValues.put(COL_3,priority);
            contentValues.put(COL_4, expire);
            db.insert(TABLE_NAME,null,contentValues);

        }

        public Cursor getAllData() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
            return res;
        }
       /* public Cursor getID() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select _id from "+TABLE_NAME,null);
            return res;
        }*/

        public Cursor getNameExp() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select task_name from task order by expire asc ",null);
            return res;
        }
        public Cursor getNameExp2(String str) {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select task_name from task where expire ="+str,null);
            System.out.println(str);
            return res;
        }

        public boolean updateData(String id,String task_name,String priority ,String expire) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,id);
            contentValues.put(COL_2,task_name);
            contentValues.put(COL_3,priority);
            contentValues.put(COL_4,expire);
            db.update(TABLE_NAME, contentValues, "_id = ?",new String[] { id });
            return true;
        }

        public Integer deleteData (String id) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_NAME, "_id = ?",new String[] {id});
        }
    }

}
