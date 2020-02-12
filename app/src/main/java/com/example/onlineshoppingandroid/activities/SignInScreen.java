package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.data.UserData;
import com.example.onlineshoppingandroid.data.UserSingleton;
import com.example.onlineshoppingandroid.database.UserDatabase;

import java.util.ArrayList;

public class SignInScreen extends Activity {
    UserSingleton ourInstance = UserSingleton.getInstance();
    ArrayList<UserData> userArray = new ArrayList<>();
    UserDatabase userDb = new UserDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_screen);

        TextView customHeaderText = findViewById(R.id.header_text);
        ImageView cartImage = findViewById(R.id.cart_imageView);

        customHeaderText.setText(R.string.sign_in_str);
        cartImage.setVisibility(View.INVISIBLE);

        Button submitDetails = findViewById(R.id.signin_submit_btn);
        Button signup = findViewById(R.id.signin_signup_btn);
        final EditText email = findViewById(R.id.signin_email);
        final EditText password = findViewById(R.id.signin_password);

        userArray = ourInstance.getUserArray();

        submitDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Empty fields are not allowed.", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor rs = userDb.getUsersData(email.getText().toString());
                    rs.moveToFirst();


                    if (rs.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Email id is not registered.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (String.valueOf(rs.getString(rs.getColumnIndex("email"))).equals(String.valueOf(email.getText()))) {
                            if (String.valueOf(rs.getString(rs.getColumnIndex("password"))).equals(String.valueOf(password.getText()))) {
                                rs.close();
                                Intent homeIntent = new Intent(SignInScreen.this, HomeScreen.class);
                                startActivity(homeIntent);
                            } else {
                                Toast.makeText(getApplicationContext(), "You have entered wrong password.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("Emails", "Emails nothing working");
//                Cursor rs = userDb.getAllUsers();
//                rs.moveToFirst();
//                if(rs.getCount() <= 0){
//                    Log.e("Emails registered 0", String.valueOf(rs.getCount()));
//                }
//                while (rs.moveToNext()){
//                    String emai = rs.getString(rs.getColumnIndex(UserDatabase.USERS_COLUMN_EMAIL));
//                    Log.e("Emails registered", emai);
//                }
                Intent signUpIntent = new Intent(getApplicationContext(), SignUpScreen.class);
                startActivity(signUpIntent);

            }
        });
    }
}
