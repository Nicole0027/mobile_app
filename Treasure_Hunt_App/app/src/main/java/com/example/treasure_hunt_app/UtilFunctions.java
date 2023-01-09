package com.example.treasure_hunt_app;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

public class UtilFunctions {

    public static void hideSoftKeyboard(Activity activity)
    {
        //region function that helps us to hide keyboard off the screen
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
        //endregion
    }
}
