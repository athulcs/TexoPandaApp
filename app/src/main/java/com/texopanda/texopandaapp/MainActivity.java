package com.texopanda.texopandaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;

    EditText user,email,phonenum,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, AuthMainActivity.class));
        user=findViewById(R.id.username_et);
        email=findViewById(R.id.email_et);
        phonenum=findViewById(R.id.phnumber_et);
        pass=findViewById(R.id.password);


        database = FirebaseDatabase.getInstance();
    }


    void fireBase(View view){
        DatabaseReference myRef = database.getReference("users");

        Map <String, String> userData = new HashMap<>();
        userData.put("email", email.getText().toString());
        userData.put("phone", phonenum.getText().toString());
        userData.put("pass", pass.getText().toString());

        myRef.child(user.getText().toString()).setValue(userData);

        Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show();

    }
}
