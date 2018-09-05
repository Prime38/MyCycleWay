package com.example.prime.mycyclewaynew.Authentications;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.prime.mycyclewaynew.MainActivity;
import com.example.prime.mycyclewaynew.Maps.MapsActivity;
import com.example.prime.mycyclewaynew.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    Button login,showpass,createAcc;
    EditText emailET,passwordET;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById( R.id.loginBtn);
//        showpass=(Button)findViewById( R.id.showpassbtn);
        createAcc=(Button)findViewById( R.id.createAccBtn);
        emailET=(EditText)findViewById( R.id.emailET);
        passwordET=(EditText)findViewById(R.id.passET);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        //firebase
        auth=FirebaseAuth.getInstance();

//        showpass.setText("Show");
//        showpass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(showpass.getText()=="Show"){
//                    passwordET.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                    passwordET.setSelection(passwordET.getText().length());
//                    showpass.setText(" ");
//                }
//            }
//        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailET.getText().toString().trim();
                final String password=passwordET.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener. mProgressBar.setVisibility(View.GONE);
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        passwordET.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(loginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(loginActivity.this, UserAccount.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this, EmailSignUpinActivity.class));
            }
        });
    }
}