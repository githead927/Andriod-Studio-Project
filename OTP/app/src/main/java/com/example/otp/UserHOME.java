package com.example.otp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class UserHOME extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    RecyclerView featuredrecycler, featuredrecycler2;
    RecyclerView.Adapter adapter, adapter2;
    LinearLayout contentView;
    private long backPressedTime;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_home);

        featuredrecycler = findViewById(R.id.featured_recycler);
        featuredrecycler();

        featuredrecycler2 = findViewById(R.id.featured_recycler2);
        featuredrecycler2();

        contentView = findViewById(R.id.content);

        //Home Menu
        drawerLayout = findViewById(R.id.layout_drawer);
        navigationView = findViewById(R.id.nav_navigationview);
        menuicon = findViewById(R.id.navigation_bar_icon);

        navigationDrawer();


    }

    //navigation Drawer functions
    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                final float diffScaled = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaled;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaled / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        if(backPressedTime +2000 > System.currentTimeMillis()){//comparing time between first pressed back and second time;
            finish();
            super.onBackPressed();
            return;
        }else{
            Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    //Recycler cardview functions
    private void featuredrecycler2() {
        featuredrecycler2.setHasFixedSize(true);
        featuredrecycler2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass2> featuredlocations2 = new ArrayList<>();

        featuredlocations2.add(new FeaturedHelperClass2("Choose Your Vehicle", "Select the one you want to rent using the catalogue."));
        featuredlocations2.add(new FeaturedHelperClass2("Choose Your Package", "Fill the form with your booking details."));
        featuredlocations2.add(new FeaturedHelperClass2("Enjoy Your Ride", "Be flexible with our multiple rental locations"));

        adapter2 = new FeaturedAdapter2(featuredlocations2);
        featuredrecycler2.setAdapter(adapter2);
    }

    private void featuredrecycler() {
        featuredrecycler.setHasFixedSize(true);
        featuredrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredlocations = new ArrayList<>();

        featuredlocations.add(new FeaturedHelperClass(R.drawable.activa, "Activa", "Honda Activa 6G Specifications\n\n" +
                "Engine Capacity \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 109.51cc\n\n" +
                "Mileage \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 48 kmpl\n\n" +
                "Max Power \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 7.68bhp\n\n" + "" +
                "Kerb Weight \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 106kg\n\n" +
                "Fuel Tank Capacity \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 5.3 litres\n\n" +
                "Seat Height \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 692mm\n\n"));
        featuredlocations.add(new FeaturedHelperClass(R.drawable.ktm, "KTM", "KTM Duke Specifications\n\n" +
                "Engine Capacity \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 199.5cc\n\n" +
                "Mileage \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 34.5 kmpl\n\n" +
                "Transmission \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 6 Manual Speed\n\n" + "" +
                "Kerb Weight \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 159kg\n\n" +
                "Fuel Tank Capacity \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 13.4 litres\n\n" +
                "Seat Height \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t 822mm\n\n"));
        featuredlocations.add(new FeaturedHelperClass(R.drawable.pulsar, "Bajaj Pulsar NS-200", "THIRD BIKE ON THIS APP\n\n" +
                ""));
        featuredlocations.add(new FeaturedHelperClass(R.drawable.splendor, "Hero Splendor", "FOURTH BIKE ON THIS APP"));
        featuredlocations.add(new FeaturedHelperClass(R.drawable.jupiter, "TVS Jupiter", "FIVETH BIKE ON THIS APP"));
        featuredlocations.add(new FeaturedHelperClass(R.drawable.ic_testing, "Activa5", "SIXTH BIKE ON THIS APP"));

        adapter = new FeaturedAdapter(this, featuredlocations);
        featuredrecycler.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.END);
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserHOME.this, login.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
            case R.id.nav_feedback:
                Toast.makeText(this, "Feedback", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Intent sendintent = new Intent();
                sendintent.setAction(Intent.ACTION_SEND);
                sendintent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                sendintent.setType("plain/text");
                startActivity(sendintent);
                break;
            case R.id.nav_rateus:
                rate_usdialog rateUsdialog = new rate_usdialog(UserHOME.this);
                rateUsdialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                rateUsdialog.setCancelable(false);
                rateUsdialog.show();
                break;
        }
        return true;
    }
}