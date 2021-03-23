package com.example.rental;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class checkout extends AppCompatActivity {
    FirebaseAuth Fauth;
    FirebaseFirestore Fstore;
    String userId,mobil1;
    TextView nama,alamat,mobil,ambil,kembali,supir,harga;
    ImageButton back;
    Button bayar;
    String userID;
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        nama = findViewById(R.id.nama_bayar);
        alamat = findViewById(R.id.alamat_bayar);
        mobil = findViewById(R.id.mobil_bayar);
        ambil = findViewById(R.id.ambil_bayar);
        kembali = findViewById(R.id.kembali_bayar);
        supir = findViewById(R.id.supir_bayar);
        harga = findViewById(R.id.harga_bayar);
        bayar = findViewById(R.id.bayar);
        back = findViewById(R.id.back_check);


        String mobil_text = getIntent().getExtras().getString("mobil");
        String ambil_text = getIntent().getExtras().getString("ambil");
        String kembali_text = getIntent().getExtras().getString("kembali");
        String supir_text = getIntent().getExtras().getString("supir");

        mobil.setText(mobil_text);
        ambil.setText(ambil_text);
        kembali.setText(kembali_text);
        supir.setText(supir_text);
        Fauth = FirebaseAuth.getInstance();
        Fstore = FirebaseFirestore.getInstance();
        userId = Fauth.getCurrentUser().getUid();

        mobil1 = mobil.getText().toString();


        final DocumentReference documentReference = Fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                nama.setText(documentSnapshot.getString("nama"));
                alamat.setText(documentSnapshot.getString("alamat"));
            }
        });
        DocumentReference documentReference1 =Fstore.collection("Mobil").document(mobil1);
        documentReference1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                harga.setText(documentSnapshot.getString("harga"));
            }
        });

        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama1 = nama.getText().toString();
                String ambil1 = ambil.getText().toString();
                String kembali1 = kembali.getText().toString();
                String car = mobil.getText().toString();
                userID = Fauth.getCurrentUser().getUid();
                Map<String, String> bayar1 = new HashMap<>();
                bayar1.put("nama",nama1);
                bayar1.put("tgl_ambil",ambil1);
                bayar1.put("tgl_kembali",kembali1);
                bayar1.put("mobil",car);
                db.document("Sewa/"+userID);
                db.collection("Sewa").document(userID).set(bayar1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(checkout.this,"Berhasil",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(checkout.this, MainMenu.class);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(checkout.this,"Gagal",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,e.toString());

                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(checkout.this, Book.class);
                startActivity(intent);
            }
        });

    }
}