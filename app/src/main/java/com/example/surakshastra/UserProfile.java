package com.example.surakshastra;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName, email, phoneNo, password;
    TextView fullNameLabel, usernameLabel;
    MaterialTextView username_profile, name_profile, email_profile, phoneno_profile, password_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        username_profile = findViewById(R.id.user_name_view);
        name_profile = findViewById(R.id.name_view);
        email_profile = findViewById(R.id.email_view);
        phoneno_profile = findViewById(R.id.phone_no_view);
        password_profile = findViewById(R.id.password_view);

        showAllUserData();

    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneno");
        String user_password = intent.getStringExtra("password");
        String user_username = intent.getStringExtra("username");


        username_profile.setText(user_username);
        name_profile.setText(user_name);
        phoneno_profile.setText(user_phoneNo);
        email_profile.setText(user_email);
        password_profile.setText(user_password);
    }

    public void goToPhoneVerifyScreen(View view){
        Intent intent = new Intent(UserProfile.this, phoneVerifyScreen.class);
        startActivity(intent);
    }
}