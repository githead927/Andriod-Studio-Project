package com.example.otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mauth;
    private EditText name, email, password;
    private Button register;
    private ProgressBar progressBar;
    private ImageView registerlogo,login_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mauth = FirebaseAuth.getInstance();
        name = findViewById(R.id.fullname);
        email = findViewById(R.id.email1);
        password = findViewById(R.id.password1);

        register = findViewById(R.id.register1);
        register.setOnClickListener(this);
        progressBar = findViewById(R.id.progressbar1);
        registerlogo = findViewById(R.id.registerlogo);
        registerlogo.setOnClickListener(this);
        login_page = findViewById(R.id.login_page_button);
        login_page.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerlogo:
            case R.id.login_page_button:
                startActivity(new Intent(this, login.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.stay);
                break;
            case R.id.register1:
                register1();
                break;
        }
    }
    private void register1() {
        String name1 = name.getText().toString().trim();
        String email11 = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (name1.isEmpty()) {
            name.setError("Name Required");
            name.requestFocus();
            return;
        }
        if (email11.isEmpty()) {
            email.setError("Email Required");
            email.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("Password Required");
            password.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email11).matches()) {
            email.setError("Please provide valid email address");
            email.requestFocus();
            return;
        }
        if (pass.length() < 8) {
            password.setError("Password length must be 8 characters");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.GONE);
        mauth.createUserWithEmailAndPassword(email11, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(name1, email11);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Registration.this, "Registration Successfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(Registration.this, "Failed to Register", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Registration.this, "Failed to Register", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}