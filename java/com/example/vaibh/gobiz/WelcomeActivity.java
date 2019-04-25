package com.example.vaibh.gobiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.vaibh.gobiz.pojos.ModuleMapLock;
import com.example.vaibh.gobiz.pojos.ModuleScore;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ModuleMapLock.getFromDatabase(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ModuleScore.getFromDatabase(ModuleMapLock.userId);
        // remove this after we've implemented welcome activity
        gotoDashboard(null);
    }


    public void gotoDashboard(View view) {


        Intent i = new Intent(getApplicationContext(), Dashboard.class);

        // clear activity back stack
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
