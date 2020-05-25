package com.example.ksiazka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.annotation.Target;

import static android.os.Build.ID;

public class DatabaseSystem extends SQLiteOpenHelper
{
    private static final String TABLE_NAME = "Przepis";
    private static final String COL1 = "nazwa";

    public DatabaseSystem(Context context) {
        super(context, TABLE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (Id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + " TEXT)";
        String createSecondTable = "CREATE TABLE PrzepisItem (Id INTEGER PRIMARY KEY AUTOINCREMENT, PrzepisId INTEGER, Nazwa TEXT, Ilosc TEXT)";

        db.execSQL(createTable);
        db.execSQL(createSecondTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS PrzepisItem");

        onCreate(db);
    }

    public void addDataToPrzepis(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String insertInto = "INSERT INTO " + TABLE_NAME + " (nazwa) VALUES ('" + name + "')";
        db.execSQL(insertInto);

    }
    public void addItemDataToPrzepis(int id, String name, int amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String insertInto = "INSERT INTO PrzepisItem(PrzepisId, Nazwa, Ilosc) VALUES("+id+","+ name+","+amount+")";
        db.execSQL(insertInto);
    }

    public void deleteItem(String getID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE Id = '" +getID+"'");
    }

    public Cursor pobierzDane()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
