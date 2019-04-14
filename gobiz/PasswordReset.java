package com.example.vaibh.gobiz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordReset extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnSendReset;
    private String emailResetPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        edtEmail = findViewById(R.id.edtEmail);

        mAuth = FirebaseAuth.getInstance();

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        btnSendReset = findViewById(R.id.btnSendResetLink);

        btnSendReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
//                Matcher mat = pattern.matcher(emailResetPass);
                Reset();



            }
        });



    }

    public void Reset(){
        emailResetPass = edtEmail.getText().toString();
        Log.i("EMAIL",emailResetPass);
        if (emailResetPass.isEmpty()){
            return;
        }

        mAuth.sendPasswordResetEmail(emailResetPass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    //
                    Intent i = new Intent(getApplicationContext(), LoginScreen.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
