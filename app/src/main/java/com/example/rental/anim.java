package com.example.rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class anim extends AppCompatActivity {

    private  static  int SPLASH_SCREEN = 5000;
    //variable
    Animation topAnim;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_anim);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        //hooks
        image = findViewById(R.id.image_view);
        image.setAnimation(topAnim);
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent intent = new Intent(anim.this,MainActivity.class);
                 startActivity(intent);
                 finish();
             }
         },SPLASH_SCREEN);
    }
}