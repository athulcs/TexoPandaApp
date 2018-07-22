package com.texopanda.texopandaapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthSignUpActivity extends AppCompatActivity {


    private FirebaseDatabase database;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth auth;
    private EditText user, email, phone, pass;
    private ImageView signUp,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_auth_sign_up);

        user = findViewById(R.id.auth_signup_name_edittext);
        email = findViewById(R.id.auth_signup_email_edittext);
        phone = findViewById(R.id.auth_signup_phone_edittext);
        pass = findViewById(R.id.auth_signup_password_edittext);
        signUp = findViewById(R.id.imageView2);
        back = findViewById(R.id.back_iv);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference().child("users");


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnCompleteListener(AuthSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(AuthSignUpActivity.this, "Authentication failed." + task.getException(),Toast.LENGTH_SHORT).show();
                                } else {
                                    User newUser = new User(user.getText().toString(), email.getText().toString(), phone.getText().toString());
                                    if (auth.getUid() != null) {
                                        mDatabaseReference.child(auth.getUid()).setValue(newUser);
                                        finish();
                                    } else {
                                            Toast.makeText(AuthSignUpActivity.this, "UID NULL", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                        });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
