package com.example.treasure_hunt_app;

import static com.example.treasure_hunt_app.UtilFunctions.hideSoftKeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Signup_Activity extends AppCompatActivity{

    //region variable declaration
    private TextView loginText;
    private Button signupButton;
    private EditText userSignupName;
    private EditText userSignupEmail;
    private EditText userSignupPassword;
    private LinearLayout signupActivityFrame;
    private FirebaseAuth mAuth;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //region variable initialization
          loginText = (TextView) this.findViewById(R.id.linkToLoginActivity);
          signupButton = (Button) this.findViewById(R.id.signupButton);
          userSignupName = (EditText) this.findViewById(R.id.signupName);
          userSignupEmail = (EditText) this.findViewById(R.id.signupEmail);
          userSignupPassword = (EditText) this.findViewById(R.id.signupPassword);
          signupActivityFrame = (LinearLayout) this.findViewById(R.id.signupActivityFrame);
          mAuth = FirebaseAuth.getInstance();
        //endregion

        //Login label
        loginText.setOnClickListener(new View.OnClickListener()
        {
            //region when "Login" text is pressed activity will change to "Login_Activity"
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup_Activity.this  , Login_Activity.class));

            }
            //endregion
        });



        //entire frame
        signupActivityFrame.setOnClickListener(new View.OnClickListener()
        {
            //region if keyboard is visible on screen when we press anywhere on screen keyboard will disappear
            @Override
            public void onClick(View view) {
                hideSoftKeyboard(Signup_Activity.this);
            }
            //endregion
        });

        //Sign Up button
        signupButton.setOnClickListener(view -> {createUser();});
    }



    private void createUser()
    {
        //get data from EditText fields and convert them into strings
        final String userSignupNameString = userSignupName.getText().toString();
        final String userSignupEmailString = userSignupEmail.getText().toString();
        final String userSignupPasswordString = userSignupPassword.getText().toString();

        //region the cases in which the user doesn't fill all the credential fields
        if(userSignupNameString.isEmpty())
        {
            Toast.makeText(Signup_Activity.this, "Please Insert Your Name" , Toast.LENGTH_SHORT ).show();
        }
        else if (userSignupNameString.length() < 6 )
        {
            Toast.makeText(Signup_Activity.this, "Name too short it should have more than 6 characters" , Toast.LENGTH_SHORT ).show();

        }
        else if(userSignupEmailString.isEmpty())
        {
            Toast.makeText(Signup_Activity.this, "Please Insert Your Email" , Toast.LENGTH_SHORT ).show();
        }
        else if(userSignupPasswordString.isEmpty())
        {
            Toast.makeText(Signup_Activity.this, "Please Insert Your Password" , Toast.LENGTH_SHORT ).show();
        }
        else if(userSignupPasswordString.length() < 6)
        {
            Toast.makeText(Signup_Activity.this, "Password too short, it should have more than 6 characters " , Toast.LENGTH_SHORT ).show();

        }
        else if(userSignupEmailString.isEmpty() && userSignupNameString.isEmpty() && userSignupPasswordString.isEmpty())
        {
            Toast.makeText(Signup_Activity.this, "Please Insert Your Credentials" , Toast.LENGTH_SHORT ).show();
        }
        //endregion

        else
        {
            //region happy day case

            mAuth.createUserWithEmailAndPassword(userSignupEmailString, userSignupPasswordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Signup_Activity.this, "User Registered" , Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(Signup_Activity.this, Login_Activity.class));

                    }
                    else
                    {
                        Toast.makeText(Signup_Activity.this, "User Could Not Register " , Toast.LENGTH_SHORT ).show();

                    }
                }
            });
        //endregion
        }
    }
}