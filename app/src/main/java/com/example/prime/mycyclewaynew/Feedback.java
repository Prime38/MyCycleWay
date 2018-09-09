package com.example.prime.mycyclewaynew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth auth;
    private DatabaseReference mRootReference;
    EditText txtfeedback;
    Button send;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        txtfeedback=findViewById(R.id.txtfeedback);
        send=findViewById(R.id.feedbtn);


        auth=FirebaseAuth.getInstance();
        final String mUserId=auth.getCurrentUser().getUid();
        mRootReference = firebaseDatabase.getReference().child("users").child(mUserId).child("feedbacks");
        send.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        data=txtfeedback.getText().toString().trim();
                        mRootReference.setValue(data);
                    }
                });
    }
}
