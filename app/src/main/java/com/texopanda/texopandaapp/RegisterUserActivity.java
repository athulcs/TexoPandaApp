package com.texopanda.texopandaapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserActivity extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth auth;
    EditText user,email,phonenum,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        user=findViewById(R.id.username_et);
        email=findViewById(R.id.email_et);
        phonenum=findViewById(R.id.phnumber_et);
        pass=findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }


    public void fireBase(View view){


        //create user
        auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegisterUserActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterUserActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Map<String, String> userData = new HashMap<>();
                            userData.put("email", email.getText().toString());
                            userData.put("phone", phonenum.getText().toString());
                            DatabaseReference  myRef = database.getReference("users");
                            myRef.child(user.getText().toString()).setValue(userData);
                            finish();
                        }

                    }
                });





    }
}
