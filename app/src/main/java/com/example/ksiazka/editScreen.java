package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class editScreen extends AppCompatActivity {

    private Button saveButton, deleteButton;
    private EditText editItem;
    private DatabaseSystem databaseSystem;
    private ListView listView;
    private String selectedItemName;
    private int selectedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);

        saveButton = (Button)findViewById(R.id.saveButton);
        deleteButton = (Button)findViewById(R.id.deleteButton);
        editItem = (EditText) findViewById(R.id.editItem);
        listView = (ListView)findViewById(R.id.skladnikiList);
        databaseSystem = new DatabaseSystem(this);

        Intent getIntentData = getIntent();
        selectedItemId = getIntentData.getIntExtra("id", -1);
        selectedItemName = getIntentData.getStringExtra("name");
        editItem.setText(selectedItemName);

        getList();
    }

    public void deleteClicked(View view) {
        databaseSystem.deleteName(selectedItemId, selectedItemName);
        editItem.setText("");
        Intent y = new Intent(editScreen.this, Recipes.class);
        startActivity(y);
    }

    public void saveClicked(View view) {
        String item = editItem.getText().toString();
        if(!item.equals(""))
        {
            databaseSystem.updateName(item, selectedItemId, selectedItemName);
            Intent x = new Intent(editScreen.this, Recipes.class);
            startActivity(x);
        }
    }

    public void addButtonClicked(View view)
    {
        Intent x = new Intent(editScreen.this, AddResource.class);
        x.putExtra("id", selectedItemId);
        x.putExtra("name", selectedItemName);
        startActivity(x);
    }

    public void getList()
    {
        Cursor data = databaseSystem.getItemData(selectedItemId);
        ArrayList<Skladnik> daneListy = new ArrayList<Skladnik>();
        while(data.moveToNext())
        {
            daneListy.add(new Skladnik(data.getString(2), data.getString(3)));
        }
        SkladnikAdapter adap = new SkladnikAdapter(this, R.layout.adapter_view_layout, daneListy);
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
                    /*
                    Intent editInt = new Intent(Recipes.this, editScreen.class);
                    editInt.putExtra("id", itemId);
                    editInt.putExtra("name", name);
                    startActivity(editInt);*/
                }
            }
        });
    }
}
