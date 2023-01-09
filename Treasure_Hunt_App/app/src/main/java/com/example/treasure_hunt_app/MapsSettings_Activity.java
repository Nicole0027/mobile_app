package com.example.treasure_hunt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapsSettings_Activity extends AppCompatActivity {

    private Button addMapButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_settings);

        addMapButton = (Button) findViewById(R.id.addButtonMapsSettings);



        addMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsSettings_Activity.this, EditMap_Activity.class));
            }
        });


    }
}