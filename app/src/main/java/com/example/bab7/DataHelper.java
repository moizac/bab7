package com.example.bab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    public DataHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(id TEXT primary key, nim  TEXT, nama  TEXT, kelas TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String id, String nim, String nama, String kelas) {
        SQLiteDatabase db = this.getWritableDatabase();
            try{
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", id);
                contentValues.put("nim", nim);
                contentValues.put("nama", nama);
                contentValues.put("kelas", kelas);
                long result = db.insert("Userdetails", null, contentValues);
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            } catch(Exception e){
                e.printStackTrace();
        } return false;
    }
    public Boolean updateuserdata(String id, String nim, String nama, String kelas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nim", nim);
        contentValues.put("nama", nama);
        contentValues.put("kelas", kelas);
        Cursor cursor = db.rawQuery("Select * from Userdetails where nama = ?", new String[]{nama});
        if (cursor.getCount() > 0) {
            long result = db.update("Userdetails", contentValues, "nama=?", new String[]{nama});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Userdetails where nama = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = db.delete("Userdetails", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Userdetails", null);
            return cursor;
    }
}
