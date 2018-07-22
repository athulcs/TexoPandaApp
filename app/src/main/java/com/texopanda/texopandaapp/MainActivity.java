package com.texopanda.texopandaapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private ImageView logo;
    private TextView title;
    private GestureDetectorCompat gesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_auth_main);
        logo = findViewById(R.id.auth_main_logo);
        title = findViewById(R.id.auth_main_title);
        gesture = new GestureDetectorCompat(this, new MainActivity.LearnGesture());

        Toast.makeText(this, "Double Swipe Anywhere to Continue", Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gesture.onTouchEvent(event);
        return super.onTouchEvent(event);

    }

    private void loginInAndSignUp() {
        Pair[] pair  = new Pair[2];
        pair[0] = new Pair<View,String>(logo, "logo_shared");
        pair[1] = new Pair<View,String>(title, "texopandaapp_shared");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pair);
        Intent i = new Intent(this, AuthLoginActivity.class);
        startActivity(i, options.toBundle());
        finish();
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (distanceY > 0 && distanceX > 0) {
                // Scrolled upward
                loginInAndSignUp();
            }
            return false;
        }
    }
}

