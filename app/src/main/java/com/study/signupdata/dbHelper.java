package com.study.signupdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.study.signupdata.database.DbConstants;

import java.util.ArrayList;

/**
 * Created by $raina on $5/23/2017.
 */

public class dbHelper extends SQLiteOpenHelper{

    dbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    public dbHelper(Context context) {
        super(context, DbConstants.DB_NAME,null, DbConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStudentTableSql = "CREATE TABLE if not exists `student_detail` (\n" +
                "\t`id`\tTEXT,\n" +
                "\t`name`\tTEXT,\n" +
                "\t`address`\tTEXT,\n" +
                "\tPRIMARY KEY(id)\n" +
                ")";
        db.execSQL(createStudentTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertMembers(ContentValues cv) {
        getWritableDatabase().insert("student_detail","",cv);

    }

    public long insertMembers(Member member){
        ContentValues cv= new ContentValues();
        //cv.put("id", member.getId());
        cv.put("name", member.getName());
        cv.put("address", member.getAddress());
        long id = getWritableDatabase().insert("student_detail","",cv);
        Log.d("name",member.getName());
        return id;
    }


    public ArrayList<Member> getStudentInfo(){
        String sql = "select * from student_detail";

        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        ArrayList<Member> list = new ArrayList<Member>();

        while(cursor.moveToNext()){
            Member info = new Member();

            info.id = cursor.getString(cursor.getColumnIndex("id"));
            info.name = cursor.getString(cursor.getColumnIndex("name"));
            info.address = cursor.getString(cursor.getColumnIndex("address"));

            list.add(info);

            Log.d("datadisplay", info.getName());


        }
        cursor.close();
        return list;

    }

    public void delete(Member member) {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Log.d("deleted : ", member.getId());
        getReadableDatabase().delete(DbConstants.MEMBER_TABLE, DbConstants.ID + "=?", new String[]{member.getId()});
    }
}
