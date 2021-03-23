package com.example.rental;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    private ImageButton out,prof,dafmob,sewa,daftarsewa;
    private TextView nama;
    FirebaseAuth Fauth;
    FirebaseFirestore Fstore;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        out = findViewById(R.id.logout);
        nama = findViewById(R.id.nama_user);
        prof = findViewById(R.id.prof);
        dafmob = findViewById(R.id.daftarmobil);
        sewa = findViewById(R.id.sewa);
        daftarsewa = findViewById(R.id.daftarsewa);


        Fauth = FirebaseAuth.getInstance();
        Fstore = FirebaseFirestore.getInstance();

        userId = Fauth.getCurrentUser().getUid();

        DocumentReference documentReference = Fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                nama.setText(documentSnapshot.getString("nama"));
            }
        });

        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://omnispace.blob.core.windows.net/image-promo/TRAC%20Shopping%20Services%20-%20Web%20Artikel-02_14mei.jpg"));
        slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS50q4Lbd1Xtln53fpdShUi9pUgRPSaOxWXuA&usqp=CAU"));
        imageSlider.setImageList(slideModels,true);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout1();
            }
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Profile.class));
            }
        });

        dafmob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainMenu.this,daftarmobil.class);
               startActivity(intent);

            }
        });
        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this,Book.class);
                startActivity(intent);
            }
        });
        daftarsewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this,DaftarSewa.class);
                startActivity(intent);
            }
        });

    }
    private void logout1(){
        Fauth.signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}