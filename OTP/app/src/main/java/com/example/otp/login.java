package com.example.otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener {
    private TextView register, forgetpass;
    private ImageView registration_button;
    private Button login1;
    private EditText editTextemail, editTextpassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private SwitchCompat adminswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.register_login);
        register.setOnClickListener(this);

        forgetpass = findViewById(R.id.resetpass);
        forgetpass.setOnClickListener(this);

        login1 = findViewById(R.id.login_button);
        login1.setOnClickListener(this);

        registration_button = findViewById(R.id.registration_page_button);
        registration_button.setOnClickListener(this);

        adminswitch = findViewById(R.id.admin_switch);
        adminswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (adminswitch.isChecked()) {
                    startActivity(new Intent(login.this, SendOTP.class));
                }else{
                    Toast.makeText(login.this,"Welcome to user login",Toast.LENGTH_LONG).show();
                }
            }
        });

        editTextemail = findViewById(R.id.login_email);
        editTextpassword = findViewById(R.id.login_pass);
        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                userLogin();
                break;
            case R.id.register_login:
            case R.id.registration_page_button:
                startActivity(new Intent(this, Registration.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
            case R.id.resetpass:
                resetPassword();
                break;
        }
    }

    private void resetPassword() {
        String loginemail = editTextemail.getText().toString().trim();
        progressBar.setVisibility(View.GONE);
        mAuth.sendPasswordResetEmail(loginemail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "Check your mail to reset password", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(login.this, "Try again!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void userLogin() {
        String loginemail = editTextemail.getText().toString().trim();
        String loginpass = editTextpassword.getText().toString().trim();

        if (loginemail.isEmpty()) {
            editTextemail.setError("Email is required");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(loginemail).matches()) {
            editTextemail.setError("Enter a valid email");
            editTextemail.requestFocus();
            return;
        }
        if (loginpass.isEmpty()) {
            editTextpassword.setError("Password is required");
            editTextpassword.requestFocus();
            return;
        }
        if (loginpass.length() < 8) {
            editTextpassword.setError("Password length must be 8 characters");
            editTextpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.GONE);
        mAuth.signInWithEmailAndPassword(loginemail, loginpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {
                        startActivity(new Intent(login.this, UserHOME.class));
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(login.this, "Check your email to verify account", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(login.this, "Failed to Login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}