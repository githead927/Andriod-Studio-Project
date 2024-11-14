package com.example.otp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;

public class rate_usdialog extends Dialog {

    private float userrate = 0;
    public rate_usdialog(@NonNull Context context) {
        super(context);
    }

    public rate_usdialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected rate_usdialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_usdialog);

        final AppCompatButton rateNowbtn = findViewById(R.id.rateNowbtn);
        final AppCompatButton laterbtn = findViewById(R.id.laterbtn);
        final RatingBar ratingbar = findViewById(R.id.rate_us_ratingbar);
        final ImageView ratingbarimage = findViewById(R.id.ratingbar_image);

        rateNowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        laterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                if(rating <= 1){
                    ratingbarimage.setImageResource(R.drawable.onestar);
                }else if(rating <=2){
                    ratingbarimage.setImageResource(R.drawable.twostar);
                }else if(rating <=3){
                    ratingbarimage.setImageResource(R.drawable.threestar);
                }else if(rating <=4){
                    ratingbarimage.setImageResource(R.drawable.fourstar);
                }else if(rating <=5){
                    ratingbarimage.setImageResource(R.drawable.fivestar);
                }

                animateImage(ratingbarimage);
                userrate = rating;
            }
        });
    }
    private void animateImage(ImageView ratingbarimage){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingbarimage.startAnimation(scaleAnimation);
    }
}