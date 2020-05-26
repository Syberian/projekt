package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecipe extends AppCompatActivity {

    int recipeId = 1;
    EditText nazwa;
    DatabaseSystem databaseSystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        nazwa = (EditText)findViewById(R.id.editText);
        databaseSystem = new DatabaseSystem(this);

    }

    public void dalejClicked(View view)
    {
        if(nazwa.length() != 0)
        {
            if(databaseSystem.addDataToPrzepis(nazwa.getText().toString()))
            {
                Toast x = Toast.makeText(AddRecipe.this, "Pomyślnie dodano recepturę!", Toast.LENGTH_SHORT);
                x.show();
                Intent intent = new Intent(AddRecipe.this, Recipes.class);
                startActivity(intent);
            }
            else
            {
                Toast x = Toast.makeText(AddRecipe.this, "Nie można dodać tej receptury!", Toast.LENGTH_SHORT);
                x.show();
            }

        }
    }
}
