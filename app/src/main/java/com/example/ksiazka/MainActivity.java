package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatabaseSystem databaseSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseSystem = new DatabaseSystem(this);
        ImageView a = (ImageView) findViewById(R.id.imageView);
        ImageView b = (ImageView) findViewById(R.id.imageView2);
        ImageView c = (ImageView) findViewById(R.id.imageView3);
        TextView txtView = (TextView)findViewById(R.id.textView);
        ImageView comp = (ImageView) findViewById(R.id.compas);
        final Button clearButton = (Button) findViewById(R.id.button7);

        Animation blink = AnimationUtils.loadAnimation(MainActivity.this, R.anim.blink_anim);
        final Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadeout);
        Animation zoomIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomin);
        Animation rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
        a.startAnimation(blink);
        b.startAnimation(blink);
        c.startAnimation(blink);
        txtView.startAnimation(zoomIn);
        comp.startAnimation(rotate);
        clearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clearButton.startAnimation(fadeOut);
            }
        });
    }

    // Klikniecie w przycisk "Przepisy"
    public void recipesClicked(View view) {
        Intent intent = new Intent(MainActivity.this, Recipes.class);
        startActivity(intent);
    }
    // Klikniecie w przycisk "Składniki"
    public void partsClicked(View view) {
        Intent intent = new Intent(MainActivity.this, ResourceList.class);
        startActivity(intent);
    }
    // Klikniecie w przycisk "Wyjście"
    public void quitClicked(View view) {
        finish();
        System.exit(0);
    }
    // Wyczyszczenie calej bazy danych
    public void clearData(View view) {
        databaseSystem.clearDatabase();
    }
}
