package com.example.vaibh.gobiz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vaibh.gobiz.pojos.ModuleMapLock;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtPass, edtConPass;
    private String strName, strEmail, strPass, strConPass, uid;
    private ImageButton btnSignUp;
    private ProgressBar progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmailSignUp);
        edtPass = findViewById(R.id.edPassSignUp);
        edtConPass = findViewById(R.id.edtConPass);

        btnSignUp = findViewById(R.id.btnSignUp);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = findViewById(R.id.progressDialog);
        progressDialog.setVisibility(View.INVISIBLE);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verfiyUser();
            }
        });
    }

    public void verfiyUser(){
        progressDialog.setVisibility(View.VISIBLE);
        strName = edtName.getText().toString();
        strEmail = edtEmail.getText().toString();
        strPass = edtPass.getText().toString();
        strConPass = edtConPass.getText().toString();

        if(strPass.equals(strConPass)){
            mAuth.createUserWithEmailAndPassword(strEmail,strPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("Users");
                        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        currentUser.child(uid).child("Name").setValue(strName);
//                        ModuleMapLock.signupModule();
//                        currentUser.child(uid).child("ModulesUnlocked").setValue(ModuleMapLock.moduleMap);
                        Intent i = new Intent(getApplicationContext(), LoginScreen.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Authentication Failed. "+ task.getException(), Toast.LENGTH_LONG).show();

                    }
                }
            });

        }
        else{
            Toast.makeText(getApplicationContext(),"Authentication Failed. Password are not same.", Toast.LENGTH_LONG).show();
        }
        progressDialog.setVisibility(View.INVISIBLE);


    }
}
