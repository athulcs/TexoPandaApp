package com.texopanda.texopandaapp;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private TextView reguser;
    private EditText email;
    private EditText pass;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reguser=findViewById(R.id.registeruser_tv);
        auth=FirebaseAuth.getInstance();
        email=findViewById(R.id.login_email_et);
        pass=findViewById(R.id.login_pass_et);
    }


   public void login(View view){
        String em = email.getText().toString();
        String pa= pass.getText().toString();

       if (TextUtils.isEmpty(em)) {
           email.setError("Enter email address");
           return;
       }

       if (TextUtils.isEmpty(pa)) {
           pass.setError("Enter Password");
           return;
       }

       auth.signInWithEmailAndPassword(em,pa)
               .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (!task.isSuccessful()) {
                           Toast.makeText(MainActivity.this, "Username and Password does not match : "+task.getException(), Toast.LENGTH_LONG).show();
                       } else {
                           Intent intent = new Intent(MainActivity.this, EventsActivity.class);
                           startActivity(intent);
                       }
                   }
               });


    }

   public void registerIntent(View view){
        Intent regIntent = new Intent(this,RegisterUserActivity.class);
        startActivity(regIntent);
    }


}
