package com.texopanda.texopandaapp;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends android.support.v4.app.Fragment {

    EditText pass,email;
    FloatingActionButton loginFab;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.auth_frag_login_email_edittext);
        pass = view.findViewById(R.id.auth_frag_login_password_edittext);
        loginFab = view.findViewById(R.id.auth_frag_login_fab);

        loginFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AuthLoginActivity)getActivity()).login(email.getText().toString(),pass.getText().toString());
            }
        });
        return view;
    }

}
