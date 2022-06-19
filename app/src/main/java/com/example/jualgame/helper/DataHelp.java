package com.example.jualgame.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHelp extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jualbeli.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table pembeli (" +
                "nama text," +
                "email text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table gameitm(" +
                "item text," +
                "harga int," +
                "primary key(item)" +
                ");" +
                "");
        db.execSQL("create table beli(" +
                "item text," +
                "nama text," +
                "nmitm text," +
                "foreign key(item) references gameitm (item), " +
                "foreign key(nama) references pembeli (nama) " +
                ");" +
                "");

        db.execSQL("insert into gameitm values (" +
                "'Dota#1'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into gameitm values (" +
                "'Dota#2'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into gameitm values (" +
                "'Dota#3'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into gameitm values (" +
                "'Dota#4'," +
                "1500000" +
                ");" +
                "");
        db.execSQL("insert into gameitm values (" +
                "'Csgo#1'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into gameitm values (" +
                "'Csgo#2'," +
                "500000" +
                ");" +
                "");
        db.execSQL("insert into gameitm values (" +
                "'Csgo#3'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into gameitm values (" +
                "'ML#1'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into gameitm values (" +
                "'ML#2'," +
                "700000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from gameitm";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}