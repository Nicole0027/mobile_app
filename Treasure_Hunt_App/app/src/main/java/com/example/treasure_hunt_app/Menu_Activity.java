package com.example.treasure_hunt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Menu_Activity extends AppCompatActivity {

    private Button logOutButton;
    private Button startGameButton;
    private Button chooseLevelButton;
    private Button scoreboardButton;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        logOutButton = findViewById(R.id.logOutButton);
        startGameButton = findViewById(R.id.menuStartButton);
        chooseLevelButton = findViewById(R.id.menuChooseMapButton);
        scoreboardButton = findViewById(R.id.menuScoreboardButton);

        auth = FirebaseAuth.getInstance();


        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu_Activity.this, Game_Activity.class));

            }
        });

        chooseLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu_Activity.this, ChooseMap_Activity.class));
            }
        });

        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu_Activity.this, Scoreboard_Activity.class));
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(Menu_Activity.this, Login_Activity.class));
                Toast.makeText(Menu_Activity.this, "You Logged Out" , Toast.LENGTH_SHORT ).show();

            }
        });
    }
}