package com.example.ksiazka;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResourceList extends AppCompatActivity {

    private DatabaseSystem databaseSystem;
    private ListView listView;
    private String selectedItemName;
    private int selectedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_list);
        listView = (ListView)findViewById(R.id.skladnikiList);
        databaseSystem = new DatabaseSystem(this);
        getList();  /// Pobranie listy danych
    }

    // Obsluzenie dodawania nowych skladników
    public void addButtonClicked(View view)
    {
        Intent x = new Intent(ResourceList.this, AddItemToRecourceList.class);
        startActivity(x);
    }
    // Pobieranie listy składników z bazy
    public void getList()
    {
        Cursor data = databaseSystem.getResourcesItemsData();
        ArrayList<Skladnik> daneListy = new ArrayList<Skladnik>();
        while(data.moveToNext())
        {
            daneListy.add(new Skladnik(data.getString(1), data.getString(2))); // Wyciagniecie z tabeli odpowiednio Nazwy i Ilosci produktu
        }
        SkladnikAdapter adap = new SkladnikAdapter(this, R.layout.adapter_view_layout, daneListy);
        listView.setAdapter(adap);

        // Ustawia listener na przycisk w listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = ((Skladnik)parent.getItemAtPosition(position)).nazwa;
                Cursor data = databaseSystem.getRecourceId(name);

                int itemId = -1;

                while(data.moveToNext())
                {
                    itemId = data.getInt(0);
                }

                if(itemId > -1)
                {
                    databaseSystem.deleteRecourcesData(itemId, name);
                    finish();
                    startActivity(getIntent());

                }
            }
        });


    }
}
