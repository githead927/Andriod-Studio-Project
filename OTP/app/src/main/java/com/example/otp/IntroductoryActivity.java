package com.example.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IntroductoryActivity extends AppCompatActivity {
    ImageView splashbackground;
    LottieAnimationView lottieAnimationView;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    TextView appname;

    private static int SPLASH_TIMER = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        splashbackground = findViewById(R.id.splash_bg);
        lottieAnimationView = findViewById(R.id.lottie_animation);
        appname = findViewById(R.id.textView);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_introductory);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firebaseUser != null) {
                    startActivity(new Intent(IntroductoryActivity.this, UserHOME.class));
                }else {
                    Intent intent = new Intent(IntroductoryActivity.this, login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIMER);
    }
}