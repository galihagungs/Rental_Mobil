package com.example.rental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class daftarmobil extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarmobil);
        toolbar = findViewById(R.id.toolBar);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager= (ViewPager)findViewById(R.id.viewpager);
        back = findViewById(R.id.back_daftar);

        setSupportActionBar(toolbar);
        setViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(daftarmobil.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }


    private void setViewPager(ViewPager viewPager){
        Viewpageradapter viewpageradapter = new Viewpageradapter(getSupportFragmentManager());
        viewpageradapter.addFragment(new First(),"Toyota");
        viewpageradapter.addFragment(new Second(),"Mitsubishi");
        viewPager.setAdapter(viewpageradapter);
    }
}