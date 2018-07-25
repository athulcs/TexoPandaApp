package com.texopanda.texopandaapp;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity
                            implements DevsFragment.OnFragmentInteractionListener{
    private CoordinatorLayout coordinatorLayout;
    private DrawerLayout mDrawerLayout;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("users").child(auth.getUid());

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        switch (menuItem.getItemId()){
                            case R.id.nav_logout:logout();break;
                            case R.id.nav_reg_events:;break;
                            case R.id.nav_support:;break;
                            case R.id.nav_dev:
                                DevsFragment devsFragment = new DevsFragment();
                                getSupportFragmentManager().beginTransaction()
                                .add(R.id.coordinator_layout,devsFragment)
                                .commit();break;
                        }
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                List<Boolean> eventsCard = new ArrayList<Boolean>(Arrays.asList(new Boolean[4]));
                eventsCard=user.getEvents();
                for(int i=0;i<eventsCard.size();i++){
                    if(eventsCard.get(i)){
                        Button button;
                        int resourceId = ScrollingActivity.this.getResources().
                                getIdentifier("button"+i, "id", ScrollingActivity.this.getPackageName());
                        button = findViewById(resourceId);
                        button.setText("REGISTERED");
                        }
                    }

                }



            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        auth.signOut();
        finish();
    }
    public void register(View view){
        dbRef.child("events").child(view.getTag().toString()).setValue(true);
        Button button = (Button) view;
        button.setText("REGISTERED");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
