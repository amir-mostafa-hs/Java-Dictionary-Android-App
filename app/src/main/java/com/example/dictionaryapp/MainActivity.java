package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // get DatabaseController class
    DatabaseController databaseController;

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
        Button btnClear = findViewById(R.id.btnClear);

        // create new instance from DatabaseController
        databaseController = new DatabaseController(this);

        // create click event in btnClear for clear all textView
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtWord.setText("");
                txtPersianTranslate.setText("");
                txtArabicTranslate.setText("");
                txtPronounce.setText("");
                txtDescriptions.setText("");
            }
        });

        // create click event in btnAdd for add new word data
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get textViews value
                String newWordValue = txtWord.getText().toString();
                String newWordPersianTranslate = txtPersianTranslate.getText().toString();
                String newWordArabicTranslate = txtArabicTranslate.getText().toString();
                String newWordPronounce = txtPronounce.getText().toString();
                String newWordDescriptions = txtDescriptions.getText().toString();

                // Checking the textViews value is valid or no
                if (newWordValue.equals("") || newWordPersianTranslate.equals("") || newWordArabicTranslate.equals("") || newWordPronounce.equals("") || newWordDescriptions.equals("")) {
                    Toast.makeText(MainActivity.this,"Please enter all requested values. If there is no value, instead of leaving it blank, enter \"None\".",Toast.LENGTH_SHORT).show();
                }else {
                    // create new instance from DictionaryWord
                    DictionaryWord newWord = new DictionaryWord(newWordValue, newWordPersianTranslate, newWordArabicTranslate, newWordPronounce, newWordDescriptions);

                    // insert new word data and show alert
                    boolean result = databaseController.insertNewWord(newWord);
                    if (result) {
                        Toast.makeText(MainActivity.this, "Add new word successfully", Toast.LENGTH_SHORT).show();
                        txtWord.setText("");
                        txtPersianTranslate.setText("");
                        txtArabicTranslate.setText("");
                        txtPronounce.setText("");
                        txtDescriptions.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "There is a problem adding a new word", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // create click event in btsShow for show the data of the searched word
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get searched word
                String wordSearch = txtWord.getText().toString();
                // search word and save result
                DictionaryWord result = databaseController.getWord(wordSearch);
                // check the result
                if (result == null) {
                    Toast.makeText(MainActivity.this, "The desired word is not found", Toast.LENGTH_SHORT).show();
                } else {
                    txtWord.setText(result.Word);
                    txtPersianTranslate.setText(result.PersianTranslate);
                    txtArabicTranslate.setText(result.ArabicTranslate);
                    txtPronounce.setText(result.Pronounce);
                    txtDescriptions.setText(result.Descriptions);
                }
            }
        });

        // create click event in btnDelete for delete selected word
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get selected word
                String wordSelect = txtWord.getText().toString();
                // delete word from database
                boolean result = databaseController.deleteWord(wordSelect);
                // check the result
                if (result) {
                    Toast.makeText(MainActivity.this, "Delete word successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "There is a problem Deleting the word", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}