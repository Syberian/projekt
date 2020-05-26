package com.example.ksiazka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

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
    // Stworzenie Tabel
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (Id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + " TEXT UNIQUE)";
        db.execSQL(createTable);
        String createSecondTable = "CREATE TABLE PrzepisItem (Id INTEGER PRIMARY KEY AUTOINCREMENT, PrzepisId INTEGER, Nazwa TEXT UNIQUE, Ilosc TEXT)";
        db.execSQL(createSecondTable);

        String createThirdTable = "CREATE TABLE ResourcesListTable (Id INTEGER PRIMARY KEY AUTOINCREMENT, Nazwa TEXT UNIQUE, Ilosc TEXT)";
        db.execSQL(createThirdTable);
    }
    // Obsluga ulepszania wersji bazy danych na nowszą, rekreacja tabel
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS PrzepisItem");
        db.execSQL("DROP TABLE IF EXISTS ResourcesListTable");

        onCreate(db);
    }
    // Dodanie danych do przepisów
    public boolean addDataToPrzepis(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nazwa", name);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    // Dodanie składników do przepisu
    public boolean addItemDataToPrzepis(int id, String name, String amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PrzepisId", id);
        contentValues.put("Nazwa", name);
        contentValues.put("Ilosc", amount);
        long result = db.insert("PrzepisItem", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    // Dodanie składnika do "Składników"
    public boolean addDataToResourcesList(String name, String amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nazwa", name);
        contentValues.put("Ilosc", amount);
        long result = db.insert("ResourcesListTable", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    // Pobranie wszystkich danych z tabeli Przepisy
    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    // Pobranie danych wg. nazwy
    public Cursor getItemId(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + name + "'";
        Cursor x = db.rawQuery(query, null);
        return x;
    }
    // Pobranie danych wg nazwy
    public Cursor getRecourceId(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM ResourcesListTable WHERE Nazwa ='" + name + "'";
        Cursor x = db.rawQuery(query, null);
        return x;
    }
    // Pobranie skladników przepisu
    public Cursor getRecipeItemsData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM PrzepisItem WHERE PrzepisId ='" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    // Pobranie składników
    public Cursor getResourcesItemsData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM ResourcesListTable";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    // Pobranie składników po nazwie
    public Cursor getRecipeItemsId(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM PrzepisItem WHERE Nazwa = '" + name + "'";
        Cursor x = db.rawQuery(query, null);
        return x;
    }
    // Zaktualizowanie nazwy Przepisu
    public void updateName(String newName, int id, String oldName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL1 + " = '" + newName + "' WHERE Id ='" + id + "'" + " AND " + COL1 + " = '" + oldName + "'";
        db.execSQL(query);
    }
    // Usuniecie przepisu
    public void deleteName(int id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE Id='" + id + "'" + " AND " + COL1 + " = '" + name + "'";
        db.execSQL(query);
    }
    // Usuniecie składników przepisu
    public void deleteRecipeItemsData(int id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM PrzepisItem WHERE Id='" + id + "' AND " + "Nazwa ='" + name + "'";
        db.execSQL(query);
    }
    // Usuniecie skladnikow
    public void deleteRecourcesData(int id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM ResourcesListTable WHERE Id='" + id + "' AND " + "Nazwa ='" + name + "'";
        db.execSQL(query);
    }
    // Wyczyszczenie bazy danych
    public void clearDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;
        db.execSQL(query);

        query = "DELETE FROM PrzepisItem";
        db.execSQL(query);

        query = "DELETE FROM ResourcesListTable";
        db.execSQL(query);
    }
}
