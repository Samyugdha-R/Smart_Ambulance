package com.example.traffic_ambulance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="AmbuSignalun.db";
    public static final String TABLE_NAME ="complnt_tbl";
    public static final String COL_1 ="CID";
    public static final String COL_2 ="fname";
    public static final String COL_3 ="mblnum";
    public static final String COL_4 ="cplntsubject";
    public static final String COL_5 ="cpltdesc";
    public static final String COL_6 ="cmplntname";
    public static final String COL_7 ="cdate";
    public static final String COL_8 ="cloc";
   // public static final String TABLE_Mppl = "complnt_tbl";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }





}