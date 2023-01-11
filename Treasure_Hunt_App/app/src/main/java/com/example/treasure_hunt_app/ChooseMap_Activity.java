package com.example.treasure_hunt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class ChooseMap_Activity extends AppCompatActivity {

    private ListView levelList;
    private ArrayList<String> levelName;

    private static GoogleMap mapToPlay;


    private static ArrayList<ArrayList<Marker>> listsOfPinsForEachMap = new ArrayList<>();
    private static ArrayList<Marker> listOfPinsOfSelectedMap = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_map);
        levelName = MapsSettings_Activity.getMapsNames();
        levelList = (ListView) findViewById(R.id.levelsList);
        listsOfPinsForEachMap = MapsSettings_Activity.getListsOfPinsForEachMap();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, levelName);
        levelList.setAdapter(adapter);

        levelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get the list of selected map and start game activity
                listOfPinsOfSelectedMap = listsOfPinsForEachMap.get(position);
                startActivity(new Intent(ChooseMap_Activity.this, Game_Activity.class));
            }
        });
    }
    public static ArrayList<Marker> getMarkersForMapToPlay()
    {
        return listOfPinsOfSelectedMap;
    }
}