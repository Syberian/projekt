package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DatabaseSystem databaseSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseSystem = new DatabaseSystem(this);
    }

    public void recipesClicked(View view) {
        Intent intent = new Intent(MainActivity.this, Recipes.class);
        startActivity(intent);
    }

    public void partsClicked(View view) {
        Intent intent = new Intent(MainActivity.this, ResourceList.class);
        startActivity(intent);
    }

    public void shoppingClicked(View view) {
    }

    public void quitClicked(View view) {
        finish();
        System.exit(0);
    }

    public void clearData(View view) {
        databaseSystem.clearDatabase();
    }
}
