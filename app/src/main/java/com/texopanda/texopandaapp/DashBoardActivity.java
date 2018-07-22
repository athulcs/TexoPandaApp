package com.texopanda.texopandaapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity {
    TextView username;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference dbRef;
    LinearLayout eventsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        username=findViewById(R.id.username_tv);
        eventsLayout = findViewById(R.id.linearLayout3);
        auth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        if(auth.getUid()!=null) {
            dbRef = database.getReference("users").child(auth.getUid());
        }

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               User user = dataSnapshot.getValue(User.class);
                List<Boolean> events=new ArrayList<Boolean>(Arrays.asList(new Boolean[4]));
                events=user.getEvents();
                for(int i=0;i<events.size();i++){
                    if(events.get(i)){
                        Button button;
                        View v=eventsLayout.getChildAt(i);
                        v.setBackgroundColor(Color.parseColor("#00FF00"));
                        if (v instanceof Button){
                            button = (Button) v;
                            button.setText("REGISTERED");
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });
    }

    public void logout(View view){
        auth.signOut();
        finish();
    }
}
