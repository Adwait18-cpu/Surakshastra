package com.example.surakshastra;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        callSignUp = findViewById(R.id.signup_screen);
        image = findViewById(R.id.logo_img);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
    }

    private Boolean validateUsername(){
        String val = Objects.requireNonNull(username.getEditText()).getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = Objects.requireNonNull(password.getEditText()).getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword()) {
            return;
        }
        else{
            isUser();
        }
    }

    private void isUser() {

        final String userEnterUsername = Objects.requireNonNull(username.getEditText()).getText().toString().trim();
        final String userEnterPassword = Objects.requireNonNull(password.getEditText()).getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnterUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnterUsername).child("password").getValue(String.class);
                    if (passwordFromDB != null) {
                        if(passwordFromDB.equals(userEnterPassword)){

                            username.setError(null);
                            username.setErrorEnabled(false);

                            String nameFromDB = snapshot.child(userEnterUsername).child("name").getValue(String.class);
                            String usernameFromDB = snapshot.child(userEnterUsername).child("username").getValue(String.class);
                            String phoneNoFromDB = snapshot.child(userEnterUsername).child("phoneno").getValue(String.class);
                            String emailFromDB = snapshot.child(userEnterUsername).child("email").getValue(String.class);

                            Intent intent = new Intent(getApplicationContext(), UserProfile.class);

                            intent.putExtra("name",nameFromDB);
                            intent.putExtra("username",usernameFromDB);
                            intent.putExtra("email",emailFromDB);
                            intent.putExtra("phoneno",phoneNoFromDB);
                            intent.putExtra("password",passwordFromDB);

                            startActivity(intent);

                        }
                        else{

                            password.setError("Wrong Password");
                            password.requestFocus();
                        }
                    }
                }
                else{

                    username.setError("No User Exists");
                    username.requestFocus();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void callSignUpScreen(View view){
        Intent intent = new Intent(Login.this, SignUp.class);

        Pair[] pairs = new Pair[7];

        pairs[0] = new Pair<View, String>(image, "logo_image");
        pairs[1] = new Pair<View, String>(logoText, "logo_text_tr");
        pairs[2] = new Pair<View, String>(sloganText, "slogan_text_tr");
        pairs[3] = new Pair<View, String>(username, "uname_tr");
        pairs[4] = new Pair<View, String>(password, "pass_tr");
        pairs[5] = new Pair<View, String>(login_btn, "btn_tr");
        pairs[6] = new Pair<View, String>(callSignUp, "login_signup_tr");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
        startActivity(intent, options.toBundle());
    }
}