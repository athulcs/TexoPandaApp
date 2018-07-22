package com.texopanda.texopandaapp;

import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class AuthLoginActivity extends AppCompatActivity {

    private TextView loginTab, signUpTab;
    private ConstraintLayout layout;

    private ViewPager authPager;

    private PagerViewAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        //SharedElementTransition
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auth_login);


        loginTab = findViewById(R.id.auth_login_tab1);
        signUpTab = findViewById(R.id.auth_login_tab2);

        authPager = findViewById(R.id.auth_login_pager);



        pagerAdapter = new PagerViewAdapter(getSupportFragmentManager());
        authPager.setAdapter(pagerAdapter);

        loginTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authPager.setCurrentItem(0);
            }
        });

        signUpTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authPager.setCurrentItem(1);
            }
        });

        authPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private Transition enterTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(300);

        return bounds;
    }





    private void changeTab(int position) {
        if(position == 0){
            loginTab.setTextColor(getResources().getColor(R.color.tabSelectedColor));
            loginTab.setBackgroundResource(R.drawable.auth_login_tab_selected_bg);
            signUpTab.setTextColor(getResources().getColor(R.color.tabNotSelectedColor));
            signUpTab.setBackgroundResource(R.drawable.auth_login_tab_not_selected_bg);
        }
        else if(position == 1){
            loginTab.setTextColor(getResources().getColor(R.color.tabNotSelectedColor));
            loginTab.setBackgroundResource(R.drawable.auth_login_tab_not_selected_bg);
            signUpTab.setTextColor(getResources().getColor(R.color.tabSelectedColor));
            signUpTab.setBackgroundResource(R.drawable.auth_login_tab_selected_bg);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
