package com.example.rental;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Book extends AppCompatActivity {
    TextView txt1,txt2,mobil1;
    Button button1,button2;
    ImageButton back;
    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton radioButton;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        spinner = findViewById(R.id.spiner1);
        button1 = findViewById(R.id.button_ambil);
        button2 = findViewById(R.id.button_kembali);
        radioGroup = findViewById(R.id.radiogrup);
        linearLayout = findViewById(R.id.checkout);
        mobil1 =findViewById(R.id.mobil1);
        back = findViewById(R.id.back_book);


        txt1 = findViewById(R.id.ambil);
        txt2 = findViewById(R.id.kembali);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.Mobil));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = spinner.getSelectedItem().toString();
                mobil1.setText(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Book.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                month = month+1;
                                txt1.setText(dayOfMonth+"/"+month+"/"+year);

                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Book.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                month = month+1;
                                txt2.setText(dayOfMonth+"/"+month+"/"+year);
                            }
                        },year,month,day);
                datePickerDialog.show();

            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
//
                String ambil = txt1.getText().toString();
                String kembali = txt2.getText().toString();
                String supir = radioButton.getText().toString();
                String mobil = mobil1.getText().toString();
                Intent intent = new Intent(Book.this, checkout.class);
                intent.putExtra("mobil",mobil);
                intent.putExtra("ambil",ambil);
                intent.putExtra("kembali",kembali);
                intent.putExtra("supir",supir);
                startActivity(intent);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Book.this, MainMenu.class);
                startActivity(intent);
            }
        });


    }

}