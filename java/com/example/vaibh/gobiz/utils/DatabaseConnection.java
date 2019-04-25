package com.example.vaibh.gobiz.utils;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.vaibh.gobiz.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class DatabaseConnection {

    public DatabaseReference mDatabase;
    public FirebaseAuth mAuth;
    public boolean userAccepted;

    public DatabaseConnection(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth  =  FirebaseAuth.getInstance();
    }
}
