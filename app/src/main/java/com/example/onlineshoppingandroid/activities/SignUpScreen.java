package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.data.UserSingleton;
import com.example.onlineshoppingandroid.database.UserDatabase;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SignUpScreen extends Activity {
    UserSingleton ourInstance = UserSingleton.getInstance();
    UserDatabase userDb = new UserDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        TextView customHeaderText = findViewById(R.id.header_text);
        ImageView cartImage = findViewById(R.id.cart_imageView);

        customHeaderText.setText(R.string.sign_up_str);
        cartImage.setVisibility(View.INVISIBLE);

        Button submitDetails = findViewById(R.id.signup_submit_btn);
        final EditText name = findViewById(R.id.signup_name);
        final EditText email = findViewById(R.id.signup_email);
        final EditText password = findViewById(R.id.signup_password);
        final EditText confirmPassword = findViewById(R.id.signup_confirm_password);
        final EditText address = findViewById(R.id.signup_address);
        final EditText mobileNumber = findViewById(R.id.signup_mobile_number);
        final ArrayList<String> addressList = new ArrayList<>();

        submitDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(confirmPassword.getText().toString()) || TextUtils.isEmpty(mobileNumber.getText().toString()) || TextUtils.isEmpty(address.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Empty fields are not allowed.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password fields do not match.", Toast.LENGTH_SHORT).show();
                    } else if (!emailValidation(String.valueOf(email.getText()))) {
                        Toast.makeText(getApplicationContext(), "You have entered an invalid Email Address!", Toast.LENGTH_SHORT).show();
                    } else if (!isValidMobile(String.valueOf(mobileNumber.getText()))) {
                        Toast.makeText(getApplicationContext(), "You have entered an invalid Mobile Number!", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean isInserted = userDb.insertUsers(name.getText().toString(),
                                email.getText().toString(),
                                password.getText().toString(),
                                mobileNumber.getText().toString(),
                                address.getText().toString());
                        if (isInserted == true){
                            showConfimationDialog();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Data not Inserted", Toast.LENGTH_LONG).show();

                        }
                    }

                }
                }
            });
    }
    private void showConfimationDialog() {
        LayoutInflater li = LayoutInflater.from(SignUpScreen.this);
        final View popUpView = li.inflate(R.layout.popup_dialog, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignUpScreen.this);
        alertDialogBuilder.setView(popUpView);

        TextView popUpHeadingText = popUpView.findViewById(R.id.alert_text);
        popUpHeadingText.setText("Congratulations!! you have registered");

        TextView messageText = popUpView.findViewById(R.id.message_text);
        messageText.setText("Click on oK and sign in to continue shopping");

        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton(R.string.str_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent signInIntent = new Intent(SignUpScreen.this, SignInScreen.class);
                                startActivity(signInIntent);
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public boolean emailValidation(String emailString) {
        String EmailAdr = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!emailString.trim().matches(EmailAdr)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidMobile(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 13;
        }
        return false;
    }

}
