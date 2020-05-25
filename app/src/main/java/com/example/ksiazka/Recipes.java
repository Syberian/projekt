package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Recipes extends AppCompatActivity {

    DatabaseSystem databaseSystem;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        listView = (ListView)findViewById(R.id.listView);
        databaseSystem = new DatabaseSystem(this);
        wczytajListe();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast x = Toast.makeText(getApplicationContext(), String.format("%d", id) ,Toast.LENGTH_LONG);
                x.show();
            }
        });
    }

    private void wczytajListe() {
        Cursor data = databaseSystem.pobierzDane();
        ArrayList<String> daneListy = new ArrayList<String>();
        while(data.moveToNext())
        {
            daneListy.add(data.getString(1));
        }
        ListAdapter adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daneListy);
        listView.setAdapter(adap);
    }

    private void dodajRecepture(String entry)
    {
        databaseSystem.addDataToPrzepis(entry);
    }

    public void dodajPrzepis(View view) {
        Intent intent = new Intent(Recipes.this, AddRecipe.class);
        startActivity(intent);
    }

}
