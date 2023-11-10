package com.example.personalefficiencydiary;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {
    TextView textViewDate;
    EditText editTextRating;
    EditText editTextHowMuch;
    EditText editTextHowMuchNot;
    EditText editTextComent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        textViewDate = findViewById(R.id.textView);
        editTextRating = findViewById(R.id.editTextEvaluation);
        editTextHowMuch = findViewById(R.id.editTextHowMuch);
        editTextHowMuchNot = findViewById(R.id.editTextHowMuchNot);
        editTextComent = findViewById(R.id.editText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String date = extras.getString(MainActivity.KEY_DATE);
            String rating = extras.getString(MainActivity.KEY_RATING);
            String howMuch = extras.getString(MainActivity.KEY_HOW_MACH);
            String howmuchnot = extras.getString(MainActivity.KEY_HOW_MATH_NOT);
            String coment = extras.getString(MainActivity.KEY_COMENT);
;
            textViewDate.setText(date);
            editTextRating.setText(rating);
            editTextHowMuch.setText(howMuch);
            editTextHowMuchNot.setText(howmuchnot);
            editTextComent.setText(coment);

        }
    }

    public void onClickBtnEnd(View view) {
        Intent result = new Intent();
        result.putExtra(MainActivity.KEY_DATE, String.valueOf(textViewDate.getText()));
        result.putExtra(MainActivity.KEY_RATING, String.valueOf(editTextRating.getText()));
        result.putExtra(MainActivity.KEY_HOW_MACH, String.valueOf(editTextHowMuch.getText()));
        result.putExtra(MainActivity.KEY_HOW_MATH_NOT, String.valueOf(editTextHowMuchNot.getText()));
        result.putExtra(MainActivity.KEY_COMENT, String.valueOf(editTextComent.getText()));

        setResult(RESULT_OK, result);
        finish();
    }
}