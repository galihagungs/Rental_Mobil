package com.example.rental;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class DaftarSewa extends AppCompatActivity {
    FirebaseAuth Fauth;
    FirebaseFirestore Fstore2;
    String useri;
    FirebaseUser user;
    TextView mobil,awal,kembali;
    private FirebaseFirestore Fstore ;
    private DocumentReference rentref ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_sewa);

        mobil = findViewById(R.id.mobilsewa);
        awal = findViewById(R.id.ambilsewa);
        kembali = findViewById(R.id.kembalisewa);

        Fauth = FirebaseAuth.getInstance();
        Fstore2 = FirebaseFirestore.getInstance();

        useri = Fauth.getCurrentUser().getUid();
        user = Fauth.getCurrentUser();
        final DocumentReference documentReference = Fstore2.collection("Sewa").document(useri);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                mobil.setText(documentSnapshot.getString("mobil"));
                awal.setText(documentSnapshot.getString("tgl_ambil"));
                kembali.setText(documentSnapshot.getString("tgl_kembali"));
            }
        });
    }
    public void cancel (View v){
        useri = Fauth.getCurrentUser().getUid();
        Fstore = FirebaseFirestore.getInstance();
        rentref = Fstore.document("Sewa/"+useri);
        rentref.update("mobil", FieldValue.delete());
        rentref.update("nama", FieldValue.delete());
        rentref.update("tgl_ambil", FieldValue.delete());
        rentref.update("tgl_kembali", FieldValue.delete());
    }
    public void balik (View v){
        Intent intent = new Intent(DaftarSewa.this,MainMenu.class);
        finish();
        startActivity(intent);
    }
}