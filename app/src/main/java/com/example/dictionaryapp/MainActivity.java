package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get all element from main activity
        // get TextView
        TextView txtWord = findViewById(R.id.txtWord);
        TextView txtPersianTranslate = findViewById(R.id.txtPersianTranslate);
        TextView txtArabicTranslate = findViewById(R.id.txtArabicTranslate);
        TextView txtPronounce = findViewById(R.id.txtPronounce);
        TextView txtDescriptions = findViewById(R.id.txtDescriptions);
        // get Button
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnShow = findViewById(R.id.btnShow);
        Button btnDelete = findViewById(R.id.btnDelete);
    }
}