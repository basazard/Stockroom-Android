package com.example.stockroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginAcitivty extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private TextView signupTxt, forgetTxt;
    private EditText loginEmail, loginPassword;
    private Button signinBtn;


    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivty);

        signupTxt = (TextView) findViewById(R.id.signup_txt);
        signupTxt.setOnClickListener(this);

        forgetTxt = (TextView) findViewById(R.id.forget_password);
        forgetTxt.setOnClickListener(this);

        loginEmail = (EditText) findViewById(R.id.login_email);
        loginPassword = (EditText) findViewById(R.id.login_password);

        signinBtn = (Button) findViewById(R.id.signin_btn);
        signinBtn.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
             case R.id.signup_txt:
                startActivity(new Intent(LoginAcitivty.this, SignupActivity.class));
                break;
            case R.id.signin_btn:
                signinBtn();
                break;
            case R.id.forget_password:
                startActivity(new Intent(LoginAcitivty.this, ForgotPasswordActivity.class));
                break;
        }
    }

    private void signinBtn() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (email.isEmpty()) {
            loginEmail.setError("Email is required");
            loginEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEmail.setError("Please provide valid email");
            loginEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            loginPassword.setError("Password is required");
            loginPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            loginPassword.setError("Min password length should be 6 characters!");
            loginPassword.requestFocus();
            return;
        }
        if(email.equals("admin@gmail.com") && password.equals("admin123")){
            startActivity(new Intent(LoginAcitivty.this, AddProductActivity.class));
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent (LoginAcitivty.this, HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginAcitivty.this, "Failed to login! Please check your credentials", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}