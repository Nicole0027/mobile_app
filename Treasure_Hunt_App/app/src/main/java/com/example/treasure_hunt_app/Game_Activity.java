package com.example.treasure_hunt_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class Game_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Fragment fragment = new Game_Map();
        getSupportFragmentManager().beginTransaction().replace(R.id.mapFrame, fragment).commit();
    }
}