package com.example.vidworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class dashboard extends AppCompatActivity {
    Button button3,startCall;
    EditText etcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        button3=findViewById(R.id.button3);
        startCall=findViewById(R.id.button2);
        etcode=findViewById(R.id.etcode);
        URL serverurl;

        try {
            serverurl=new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultoptions=
                    new JitsiMeetConferenceOptions.Builder().setServerURL(serverurl)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultoptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        startCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options=new JitsiMeetConferenceOptions.Builder()
                        .setRoom(etcode.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(dashboard.this,options);


            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(dashboard.this,loginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}