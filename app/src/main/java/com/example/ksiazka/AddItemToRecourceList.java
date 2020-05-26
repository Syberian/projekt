package com.example.ksiazka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemToRecourceList extends AppCompatActivity {

    EditText nazwa;
    EditText ilosc;

    private String selectedItemName;
    private int selectedItemId;


    DatabaseSystem databaseSystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_itemtorecource);
        nazwa = (EditText)findViewById(R.id.editText);
        ilosc = (EditText)findViewById(R.id.countEditText);
        databaseSystem = new DatabaseSystem(this);
        Intent x = getIntent();
        selectedItemId = x.getIntExtra("id", -1);
        selectedItemName = x.getStringExtra("name");

    }

    public void dalejClicked(View view)
    {
        if(nazwa.length() != 0 && ilosc.length() != 0)
        {
            if(databaseSystem.addDataToResourcesList(nazwa.getText().toString(), ilosc.getText().toString()))
            {
                Toast x = Toast.makeText(AddItemToRecourceList.this, "Pomyślnie dodano składnik!", Toast.LENGTH_SHORT);
                x.show();

                Intent intent = new Intent(AddItemToRecourceList.this, ResourceList.class);
                //intent.putExtra("id", selectedItemId);
                //intent.putExtra("name", selectedItemName);
                startActivity(intent);
            }
            else
            {
                Toast x = Toast.makeText(AddItemToRecourceList.this, "Nie można dodać tego składnika!", Toast.LENGTH_SHORT);
                x.show();
            }

        }
    }
}
