package com.example.rental;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    private TextView nama,email,alamat,telpon;
    private Button lppass;
    private ImageButton back;
    FirebaseAuth Fauth;
    String userID;
    FirebaseUser user;
    private FirebaseFirestore Fstore ;
    private DocumentReference rentref ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nama = findViewById(R.id.nama_prof);
        email = findViewById(R.id.email_prof);
        alamat = findViewById(R.id.alamat_prof);
        telpon = findViewById(R.id.telpon_prof);
        back = findViewById(R.id.back_prof);
        lppass = findViewById(R.id.ubahpass);

        Fauth = FirebaseAuth.getInstance();
        Fstore = FirebaseFirestore.getInstance();

        userID = Fauth.getCurrentUser().getUid();
        user = Fauth.getCurrentUser();
        final DocumentReference documentReference = Fstore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                nama.setText(documentSnapshot.getString("nama"));
                email.setText(documentSnapshot.getString("email"));
                alamat.setText(documentSnapshot.getString("alamat"));
                telpon.setText(documentSnapshot.getString("telpon"));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainMenu.class));
            }
        });
        lppass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetpass = new EditText(view.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setMessage("Reset Password ?");
                passwordResetDialog.setMessage("Enter New Passowrd > 6 karakter");
                passwordResetDialog.setView(resetpass);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newpass = resetpass.getText().toString();
                        user.updatePassword(newpass).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Profile.this, "password reset success", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Profile.this, "password reset failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }
    public void editnama(View v){
        userID = Fauth.getCurrentUser().getUid();
        Fstore = FirebaseFirestore.getInstance();
        rentref = Fstore.document("users/"+userID);

        final EditText ubahnama = new EditText(v.getContext());
        final AlertDialog.Builder ubahnamaDialog = new AlertDialog.Builder(v.getContext());
        ubahnamaDialog.setMessage("Ubah Nama?");
        ubahnamaDialog.setMessage("Ubah Nama");
        ubahnamaDialog.setView(ubahnama);

        ubahnamaDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newnama = ubahnama.getText().toString();
                rentref.update("nama", newnama);
            }
        });
        ubahnamaDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ubahnamaDialog.create().show();

    }
    public void editemail(View v){
        userID = Fauth.getCurrentUser().getUid();
        Fstore = FirebaseFirestore.getInstance();
        rentref = Fstore.document("users/"+userID);

        final EditText ubahemail = new EditText(v.getContext());
        final AlertDialog.Builder ubahemailDialog = new AlertDialog.Builder(v.getContext());
        ubahemailDialog.setMessage("Ubah Email?");
        ubahemailDialog.setMessage("Ubah Email");
        ubahemailDialog.setView(ubahemail);

        ubahemailDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newemail = ubahemail.getText().toString();
                rentref.update("email", newemail);
            }
        });
        ubahemailDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ubahemailDialog.create().show();

    }
    public void edittlp(View v){
        userID = Fauth.getCurrentUser().getUid();
        Fstore = FirebaseFirestore.getInstance();
        rentref = Fstore.document("users/"+userID);

        final EditText ubahtlp = new EditText(v.getContext());
        final AlertDialog.Builder ubahtlpDialog = new AlertDialog.Builder(v.getContext());
        ubahtlpDialog.setMessage("Ubah Telpon?");
        ubahtlpDialog.setMessage("Ubah Telpon");
        ubahtlpDialog.setView(ubahtlp);

        ubahtlpDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newemail = ubahtlp.getText().toString();
                rentref.update("email", newemail);
            }
        });
        ubahtlpDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ubahtlpDialog.create().show();

    }
    public void editalamat(View v){
        userID = Fauth.getCurrentUser().getUid();
        Fstore = FirebaseFirestore.getInstance();
        rentref = Fstore.document("users/"+userID);

        final EditText ubahalamat = new EditText(v.getContext());
        final AlertDialog.Builder ubahalamatDialog = new AlertDialog.Builder(v.getContext());
        ubahalamatDialog.setMessage("Ubah Alamat?");
        ubahalamatDialog.setMessage("Ubah Alamat");
        ubahalamatDialog.setView(ubahalamat);

        ubahalamatDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newemail = ubahalamat.getText().toString();
                rentref.update("email", newemail);
            }
        });
        ubahalamatDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ubahalamatDialog.create().show();

    }

}