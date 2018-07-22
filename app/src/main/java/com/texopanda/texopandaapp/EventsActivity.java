package com.texopanda.texopandaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class EventsActivity extends AppCompatActivity {
    TextView user;
    FirebaseAuth auth;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        user = findViewById(R.id.user_tv);
        auth = FirebaseAuth.getInstance();
        logOut = findViewById(R.id.signout_bt);

        if(auth!=null){
            user.setText(auth.getCurrentUser().getDisplayName());
        }

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setText("");
                auth.signOut();
                finish();
            }
        });
    }
}
