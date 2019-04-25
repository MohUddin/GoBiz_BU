package com.example.vaibh.gobiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibh.gobiz.pojos.User;
import com.example.vaibh.gobiz.utils.DatabaseConnection;
import com.example.vaibh.gobiz.utils.InternetConnectivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

public class LoginScreen extends AppCompatActivity {
    private Button btnSignUp, btnSignIn;
    private LoginButton loginButton;
    private TextView txtForgotPass;
    private EditText edtEmail, edtPassword;
    private String strEmail, strPass, uid, fbName, TAG = "FB_LOGIN";
    private DatabaseConnection dao;
    public User currentUser;
    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        btnSignUp = findViewById(R.id.btnSignup);
        mAuth = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.edtEmailSignIn);
        edtPassword = findViewById(R.id.edtPasswordSignIn);

        txtForgotPass = findViewById(R.id.txtForgotDetails);


        dao = new DatabaseConnection();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });

        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifyIDPass();
            }
        });

        mCallbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.btnfbLogin);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getFbDetails(AccessToken.getCurrentAccessToken());
                Log.d(TAG, "facebook:onSuccess:" + loginResult + fbName);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });
// ...

        findViewById(R.id.fake_fb_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });
    }

    private void getFbDetails(final AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {

                        //   Toast.makeText(Login.this, object.toString(), Toast.LENGTH_LONG).show();
                        Log.v("FB Details", object.toString());
                        if (object != null) {
                            fbName = object.optString("name");
//                            email_fb = object.optString("email");
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,first_name, last_name, email,link");
        request.setParameters(parameters);
        request.executeAsync();
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("Users");
                            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            currentUser.child(uid).child("Name").setValue(fbName);
                            Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
            startActivity(i);
        }

    }

    public void ResetPassword(View v) {
        Intent i = new Intent(getApplicationContext(), PasswordReset.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    public void VerifyIDPass() {
        strEmail = edtEmail.getText().toString();
        strPass = edtPassword.getText().toString();

        if (strEmail.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "EmailID or Password is empty.", Toast.LENGTH_LONG).show();
            return;
        }

        InternetConnectivity internet = new InternetConnectivity(this);
        if (!internet.isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection", Toast.LENGTH_LONG).show();
        } else {
            dao.mAuth.signInWithEmailAndPassword(strEmail, strPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        dao.userAccepted = true;
                        currentUser = new User(dao);
                        Log.d("User Email : ", currentUser.getEmail());

                        Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                        startActivity(i);
                    } else {
                        dao.userAccepted = false;
                        currentUser = null;
                        Toast.makeText(getApplicationContext(), "SignIn Error. " + task.getException(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
}
