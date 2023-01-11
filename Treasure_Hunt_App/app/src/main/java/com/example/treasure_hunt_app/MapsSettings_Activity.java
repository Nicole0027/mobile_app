package com.example.treasure_hunt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MapsSettings_Activity extends AppCompatActivity {

    private Button addMapButton;
    private Button deleteMapButton;
    private Button logoutButton;
    private Boolean saveMapButtonStatus;

    private static ArrayList<ArrayList<Marker>> listsOfPinsForEachMap = new ArrayList<>();

    private Boolean deleteButtonStatus = false;


    private static ListView levelList;
    private static ArrayList<String> levelName = new ArrayList<>();
    private static ArrayAdapter adapter;
    private static int mapCounter;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_settings);

        addMapButton = (Button) findViewById(R.id.addButtonMapsSettings);
        deleteMapButton = (Button) findViewById(R.id.deleteButtonMapsSettings);
        logoutButton = (Button) findViewById(R.id.logoutButtonMapsSettings);
        levelList = (ListView) findViewById(R.id.mapsSettingsList);
        saveMapButtonStatus = EditMap_Activity.getStatus();
        auth = FirebaseAuth.getInstance();

        //region logout button action
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sign out of current user and go to login activity
                auth.signOut();
                startActivity(new Intent(MapsSettings_Activity.this, Login_Activity.class));
                Toast.makeText(MapsSettings_Activity.this, "You Logged Out" , Toast.LENGTH_SHORT ).show();

            }
        });
        //endregion

        if(saveMapButtonStatus == true) //if "Save Map" button was clicked we create a new row in Listview and add the pins of map in a list
        {
            mapCounter++;
            levelName.add("Harta" + Integer.toString(mapCounter));
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, levelName);
            levelList.setAdapter(adapter);
            listsOfPinsForEachMap.add(EditMap_Activity.getMarkersOfMap());
        }

        deleteMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the button semi-transparent, and the flags of buttons
                deleteButtonStatus = true;
                deleteMapButton.setAlpha(0.5f);
                deleteMapButton.setClickable(false);
            }
        });

        levelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(deleteButtonStatus == true)// we delete an item from the Listview
                {
                    levelName.remove(position);
                    levelList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    deleteButtonStatus = false;
                    deleteMapButton.setAlpha(1f);
                    deleteMapButton.setClickable(true);
                }
                else
                {
                    Toast.makeText(MapsSettings_Activity.this, "To delete press Delete button", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //region add new map action
        addMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsSettings_Activity.this, EditMap_Activity.class));
            }
        });
        //endregion
    }
    public static ArrayList<String>getMapsNames()
    {
        return levelName;
    }
    public static ArrayList<ArrayList<Marker>> getListsOfPinsForEachMap()
    {
        return listsOfPinsForEachMap;
    }

}