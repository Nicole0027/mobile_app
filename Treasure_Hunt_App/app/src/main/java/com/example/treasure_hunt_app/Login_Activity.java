package com.example.treasure_hunt_app;

import static android.content.ContentValues.TAG;

import static com.example.treasure_hunt_app.UtilFunctions.hideSoftKeyboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


public class Login_Activity extends AppCompatActivity {

    //region variable declaration
     private TextView signupText;
     private Button loginButton;
     private EditText userLoginEmail;
     private EditText userLoginPassword;
     private LinearLayout loginActivityFrame;
     private FirebaseAuth auth;
     //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region variable initializations
          signupText = (TextView) this.findViewById(R.id.linkToSignupActivity);
          loginButton = (Button) this.findViewById(R.id.loginButton);
          userLoginEmail = (EditText) this.findViewById(R.id.loginEmail);
          userLoginPassword = (EditText) this.findViewById(R.id.loginPassword);
          loginActivityFrame = (LinearLayout) this.findViewById(R.id.loginActivityFrame);
          auth = FirebaseAuth.getInstance();
        //endregion

        //entire frame
        loginActivityFrame.setOnClickListener(new View.OnClickListener()
        {
            //region if keyboard is visible on screen when we press anywhere on screen keyboard will disappear
            @Override
            public void onClick(View view) {
                hideSoftKeyboard(Login_Activity.this);
            }
            //endregion
        });

        // Signup label
        signupText.setOnClickListener(new View.OnClickListener()
        {
            //region when "Sign Up" text is pressed activity will change to "Login_Activity"
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this  , Signup_Activity.class));
            }
            //endregion
        });

        //Login button
        loginButton.setOnClickListener(view -> {loginUser();});


    }


    private void loginUser()
    {

        final String emailLoginString = userLoginEmail.getText().toString();
        final String passwordLoginString = userLoginPassword.getText().toString();

        //region in case the user doesn't fill his credentials fields
        if(emailLoginString.isEmpty() && passwordLoginString.isEmpty() )
        {
            Toast.makeText(Login_Activity.this, "Please Insert Your Email" , Toast.LENGTH_SHORT ).show();
        }
        else if(emailLoginString.isEmpty())
        {
            Toast.makeText(Login_Activity.this, "Please Insert Your Password" , Toast.LENGTH_SHORT ).show();
        }
        else if(passwordLoginString.isEmpty())
        {
            Toast.makeText(Login_Activity.this, "Please Insert Your Account Credentials" , Toast.LENGTH_SHORT ).show();
        }
        //endregion
        else
        {
            //region happy day case
            auth.signInWithEmailAndPassword(emailLoginString, passwordLoginString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        if(emailLoginString.equals("admin@admin.com") && passwordLoginString.equals("administrator") )
                        {
                            Toast.makeText(Login_Activity.this, "Admin Mode" , Toast.LENGTH_SHORT ).show();
                            startActivity(new Intent(Login_Activity.this, MapsSettings_Activity.class));
                        }
                        else
                        {
                            Toast.makeText(Login_Activity.this, "User Logged In" , Toast.LENGTH_SHORT ).show();
                            startActivity(new Intent(Login_Activity.this, Menu_Activity.class));
                        }

                    }
                    else
                    {
                        Toast.makeText(Login_Activity.this, "Could Not Login" , Toast.LENGTH_SHORT ).show();

                    }
                }
            });
        //endregion
        }

    }




}