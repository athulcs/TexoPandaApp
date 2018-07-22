package com.texopanda.texopandaapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterUserActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth auth;
    private EditText user, email, phonenum, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        user = findViewById(R.id.username_et);
        email = findViewById(R.id.email_et);
        phonenum = findViewById(R.id.phnumber_et);
        pass = findViewById(R.id.password);
        user = findViewById(R.id.username_et);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference().child("users");
    }


    public void fireBase(View view) {


        //create user
        auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterUserActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            List<Boolean> events=new ArrayList<Boolean>(Arrays.asList(new Boolean[4]));
                            Collections.fill(events,Boolean.FALSE);
                            User newUser = new User(user.getText().toString(),email.getText().toString(), phonenum.getText().toString(),events);
                            if (auth.getUid() != null) {
                                mDatabaseReference.child(auth.getUid()).setValue(newUser);
                                finish();
                            } else
                                Toast.makeText(RegisterUserActivity.this, "UID NULL", Toast.LENGTH_SHORT).show();

                        }
                    }

                });
    }
}
