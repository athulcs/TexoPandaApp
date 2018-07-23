package com.texopanda.texopandaapp;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventsActivity extends AppCompatActivity {
    TextView user;
    FirebaseAuth auth;
    DatabaseReference dbRef;
    Button logOut;
    LinearLayout eventsLayout;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        user.setText("");
        auth.signOut();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        user = findViewById(R.id.user_tv);
        auth = FirebaseAuth.getInstance();
        eventsLayout = findViewById(R.id.linearLayout);
        logOut = findViewById(R.id.signout_bt);
        if(auth.getUid()!=null){
            user.setText(auth.getCurrentUser().getDisplayName());
            dbRef = FirebaseDatabase.getInstance().getReference("users").child(auth.getUid());
        }


        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                List<Boolean> eventList= new ArrayList<Boolean>(Arrays.asList(new Boolean[4]));
                eventList = user.getEvents();
                for(int i=0;i<eventList.size();i++){
                    if(eventList.get(i)){
                        Button button;
                        View v=eventsLayout.getChildAt((2*i)+1);
                        v.setBackgroundColor(Color.GREEN);
                        v.setClickable(false);
                        if (v instanceof Button){
                            button = (Button) v;
                            button.setText("REGISTERED");
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setText("");
                auth.signOut();
                finish();
            }
        });


    }

    public void register(View view){
        dbRef.child("events").child(view.getTag().toString()).setValue(true);
        view.setBackgroundColor(Color.GREEN);
        view.setClickable(false);
        Button button = (Button) view;
        button.setText("REGISTERED");
    }
}
