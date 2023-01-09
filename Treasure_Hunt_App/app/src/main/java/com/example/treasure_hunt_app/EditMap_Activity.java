package com.example.treasure_hunt_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import static com.example.treasure_hunt_app.UtilFunctions.hideSoftKeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class EditMap_Activity extends AppCompatActivity {


    private LinearLayout editMapFrame;
    private Button deleteButton;
    private Button addButton;
    private Button saveMap;
    private Intent buttonStatus;
    private FrameLayout map ;
    private static EditText pinCode;


    private boolean deleteButtonStatus = true;
    private boolean addButtonStatus = false;
    public boolean saveStat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_map);

        editMapFrame = this.findViewById(R.id.editMapActivityFrame);
        map = (FrameLayout) findViewById(R.id.editPinsMapFrame);
        pinCode = (EditText) findViewById(R.id.pinCode);
        saveMap = (Button) findViewById(R.id.saveButtonEditMap);

        //if we click on the screen and if a keyboard is open it will disappear
        editMapFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard(EditMap_Activity.this);
            }
        });

        saveMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonStatus = true;
            }
        });

        Fragment fragment = new SetPins_Map();
        getSupportFragmentManager().beginTransaction().replace(R.id.editPinsMapFrame, fragment).commit();



    }

    public Boolean getStat()
    {
        return deleteButtonStatus;
    }




}