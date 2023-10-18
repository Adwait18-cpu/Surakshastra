package com.example.surakshastra;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {


    TextInputLayout reprName, reprPhoneNo, reprEmail, repdName, repdPhoneNo, reprUserName;

    Button reprBtn;

    FirebaseDatabase rootNode;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        reprName = findViewById(R.id.rrfname);
        reprEmail = findViewById(R.id.rremail);
        reprPhoneNo = findViewById(R.id.rrphoneNo);
        reprUserName = findViewById(R.id.rrusername);
        repdName = findViewById(R.id.rdfname);
        repdPhoneNo = findViewById(R.id.rdphoneNo);

        reprBtn = findViewById(R.id.reprBtn);
    }

    private Boolean validateRName(){
        String val = reprName.getEditText().getText().toString();

        if(val.isEmpty()){
            reprName.setError("Field cannot be Empty");
            return false;
        }
        else{
            reprName.setError(null);
            reprName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateRUsername(){
        String val = reprUserName.getEditText().getText().toString();
        String moWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            reprUserName.setError("Field cannot be Empty");
            return false;
        }else if(val.length()>=15){
            reprUserName.setError("Username Too Long");
            return false;
        }
        else if(!val.matches(moWhiteSpace)){
            reprUserName.setError("White Spaces Not Allowed");
            return false;
        }
        else{
            reprUserName.setError(null);
            return true;
        }
    }

    private Boolean validateREmail(){
        String val = reprEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            reprEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            reprEmail.setError("Invalid email address");
            return false;
        } else {
            reprEmail.setError(null);
            reprEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateRPhoneNo(){
        String val = reprPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            reprPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            reprPhoneNo.setError(null);
            reprPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateRdName(){
        String val = repdName.getEditText().getText().toString();

        if(val.isEmpty()){
            repdName.setError("Field cannot be Empty");
            return false;
        }
        else{
            repdName.setError(null);
            repdName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateRdPhoneNo(){
        String val = repdPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            repdPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            repdPhoneNo.setError(null);
            repdPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    public void reportNumber(View view){

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("reportNumbers");

        if(!validateRName() |  !validateRPhoneNo() | !validateREmail() | !validateRdName() |  !validateRdPhoneNo() | !validateRUsername()){
            return;
        }

        String reporterName = reprName.getEditText().getText().toString();
        String reporterEmail = reprEmail.getEditText().getText().toString();
        String reporterPhoneNo = reprPhoneNo.getEditText().getText().toString();
        String reportedName = repdName.getEditText().getText().toString();
        String reportedPhoneNo = repdPhoneNo.getEditText().getText().toString();
        String reporterUserName = reprUserName.getEditText().getText().toString();

        ReportHelperClass helpClass = new ReportHelperClass(reporterName,  reporterPhoneNo, reporterEmail, reporterUserName, reportedName, reportedPhoneNo);

        reference.child(reportedPhoneNo).setValue(helpClass);

    }

}