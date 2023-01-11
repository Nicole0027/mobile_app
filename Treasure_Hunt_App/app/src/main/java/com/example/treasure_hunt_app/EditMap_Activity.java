package com.example.treasure_hunt_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import static com.example.treasure_hunt_app.UtilFunctions.hideSoftKeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class EditMap_Activity extends FragmentActivity implements OnMapReadyCallback  {


    private LinearLayout editMapFrame;
    private static EditText pinCode;

    private Button deleteButton;
    private Button addButton;
    private Button saveMap;

    private boolean deleteButtonStatus = false;
    private boolean addButtonStatus = false;
    private static boolean  saveButtonStatus;
    private int pinNumber;

    private static ArrayList<Marker> markerListOfMapCreated;
    private static GoogleMap createdMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_map);

        editMapFrame = this.findViewById(R.id.editMapActivityFrame);
        saveMap = (Button) findViewById(R.id.saveButtonEditMap);
        deleteButton = (Button) findViewById(R.id.deletePinsButton);
        addButton = (Button) findViewById(R.id.addPinsButton);
        pinCode = (EditText) findViewById(R.id.pinCode);
        markerListOfMapCreated = new ArrayList<>();
        saveButtonStatus = false;


        //region delete button action
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the button semi-transparent, and the flags of buttons
                deleteButtonStatus = true;
                deleteButton.setAlpha(0.5f);
                deleteButton.setClickable(false);

                addButtonStatus = false;
                addButton.setAlpha(1f);
                addButton.setClickable(true);
                createdMap.clear();
                Toast.makeText(EditMap_Activity.this, "Map cleaned up", Toast.LENGTH_SHORT).show();

            }
        });
        //endregion

        //region add button action
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the button semi-transparent, and the flags of buttons

                addButtonStatus = true;
                addButton.setAlpha(0.5f);
                addButton.setClickable(false);

                deleteButtonStatus = false;
                deleteButton.setAlpha(1f);
                deleteButton.setClickable(true);

            }
        });
        //endregion

        //region if we click on the screen and if a keyboard is open it will disappear
        editMapFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard(EditMap_Activity.this);
            }
        });
        //endregion

        //region save button action
        saveMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the button semi-transparent, and the flags of buttons
                saveMap.setAlpha(0.5f);
                deleteButtonStatus = false;
                addButtonStatus = false;
                deleteButton.setAlpha(1f);
                deleteButton.setClickable(true);
                addButton.setAlpha(1f);
                addButton.setClickable(true);
                saveButtonStatus = true;
                //we go back to the MapsSettings_Activity activity where is the list with the maps created
                startActivity(new Intent(EditMap_Activity.this, MapsSettings_Activity.class));
            }
        });
        //endregion

        //we put the map into a fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapTest);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //default location on the map
        createdMap = googleMap;
        LatLng x = new LatLng(45.74618, 21.227217);
        createdMap.moveCamera(CameraUpdateFactory.newLatLngZoom(x, 15));


        createdMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                //create a marker and put it on the map and add it to markerListOfMapCreated which will be used in MapsSettingsActivity.java
                if(addButtonStatus == true)
                {
                    pinNumber++;
                    Marker p = googleMap.addMarker(new MarkerOptions().position(latLng).title("Pin:" + Integer.toString(pinNumber)));
                    p.showInfoWindow();
                    markerListOfMapCreated.add(p);
                }
                else
                {
                    Toast.makeText(EditMap_Activity.this, "To add pins press the add button", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean getStatus()
    {
        return saveButtonStatus;
    }

    public static ArrayList<Marker> getMarkersOfMap()
    {
        return markerListOfMapCreated;
    }

}