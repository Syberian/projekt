package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
        getList();

    }

    // wczytuje liste wszystkich rekordow w bazie
    private void getList() {
        Cursor data = databaseSystem.getData();
        ArrayList<String> daneListy = new ArrayList<String>();
        while(data.moveToNext())
        {
            daneListy.add(data.getString(1));
        }
        ListAdapter adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daneListy);
        listView.setAdapter(adap);

        // Ustawia listener na przycisk w listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Cursor data = databaseSystem.getItemId(name);
                int itemId = -1;
                while(data.moveToNext())
                {
                    itemId = data.getInt(0);
                }
                if(itemId > -1)
                {
                    Intent editInt = new Intent(Recipes.this, editScreen.class);
                    editInt.putExtra("id", itemId);
                    editInt.putExtra("name", name);
                    startActivity(editInt);
                }
            }
        });
    }

    public void showAddRecipes(View view) {
        Intent intent = new Intent(Recipes.this, AddRecipe.class);
        startActivity(intent);
    }

}
