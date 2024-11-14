package com.example.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class bikeinfo extends AppCompatActivity {

    ImageView image;
    TextView txtnames,txtdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikeinfo);


        image = findViewById(R.id.bikeinfo_image);
        txtnames = findViewById(R.id.bikeinfo_name);
        txtdesc = findViewById(R.id.bikeinfo_desc);

        Intent intent = getIntent();
        int image1 = intent.getExtras().getInt("image");
        String names1 = intent.getStringExtra("names");
        String desc1 = intent.getStringExtra("desc");


        image.setImageResource(image1);
        txtnames.setText(names1);
        txtdesc.setText(desc1);
    }
}