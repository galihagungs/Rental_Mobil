package com.example.rental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Regis extends AppCompatActivity {
    public static final String TAG = "TAG";
    private EditText mnnama,mnemail,mnpassword,mnalamat,nmtelpon;
    private Button reg,reg2;
    FirebaseAuth Fauth;
    FirebaseFirestore Fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        mnnama= findViewById(R.id.nama_reg);
        mnemail = findViewById(R.id.email_reg);
        mnpassword =findViewById(R.id.password_reg);
        mnalamat=findViewById(R.id.alamat_reg);
        nmtelpon = findViewById(R.id.telp_reg);
        reg  = findViewById(R.id.reg2);
        reg2 = findViewById(R.id.backlog);

        Fstore = FirebaseFirestore.getInstance();
        Fauth = FirebaseAuth.getInstance();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mnemail.getText().toString().trim();
                final String password = mnpassword.getText().toString().trim();
                final String nama = mnnama.getText().toString().trim();
                final String alamat = mnalamat.getText().toString().trim();
                final String telpon = nmtelpon.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mnemail.setError("Email Kosong");
                    return;
                }
                if(password.length()<6){
                    mnemail.setError("Password Kosong");

                    if(TextUtils.isEmpty(password)){
                        mnemail.setError("Password Kosong");
                        return;
                    }
                    return;
                }
                if(TextUtils.isEmpty(nama)){
                    mnemail.setError("Nama is Required");
                    return;
                }
                if(TextUtils.isEmpty(alamat)) {
                    mnemail.setError("Alamat is Required");
                    return;
                }
                if(TextUtils.isEmpty(telpon)) {
                    mnemail.setError("Nama is Required");
                    return;
                }
                Fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Regis.this,"User Telah dibuat",Toast.LENGTH_SHORT).show();
                            userID = Fauth.getCurrentUser().getUid();
                            DocumentReference documentReference = Fstore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("nama",nama);
                            user.put("email",email);
                            user.put("alamat",alamat);
                            user.put("telpon",telpon);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: User Profile is created for "+ userID);
                                }
                            });

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else {
                            Toast.makeText(Regis.this,"Error: gagal dibuat"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        reg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regis.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}