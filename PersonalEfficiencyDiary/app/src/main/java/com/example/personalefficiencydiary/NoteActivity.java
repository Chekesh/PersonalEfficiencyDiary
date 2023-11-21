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
    // Views для отображения данных
    TextView textViewDate;
    EditText editTextRating;
    EditText editTextHowMuch;
    EditText editTextHowMuchNot;
    EditText editTextComent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);



        // Инициализация Views

        Intent intent = getIntent();

        String date = intent.getExtras().getString(MainActivity.KEY_DATE);
        String rating = intent.getExtras().getString(MainActivity.KEY_RATING);
        String howMuch = intent.getExtras().getString(MainActivity.KEY_HOW_MACH);
        String howMuchNot = intent.getExtras().getString(MainActivity.KEY_HOW_MATH_NOT);
        String coment = intent.getExtras().getString(MainActivity.KEY_COMENT);

        Log.d(TAG, "\n\n\n\n\n\n"+ howMuch + " я гей");

        BlankFragment fragment = new BlankFragment();

        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.KEY_DATE,date);
        bundle.putString(MainActivity.KEY_RATING,rating);
        bundle.putString(MainActivity.KEY_HOW_MACH,howMuch);
        bundle.putString(MainActivity.KEY_HOW_MATH_NOT,howMuchNot);
        bundle.putString(MainActivity.KEY_COMENT,coment);
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .commit();


        // Получение данных из Intent
        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Извлечение данных из Intent и установка их в соответствующие Views
            String date = extras.getString(MainActivity.KEY_DATE);
            String rating = extras.getString(MainActivity.KEY_RATING);
            String howMuch = extras.getString(MainActivity.KEY_HOW_MACH);
            String howMuchNot = extras.getString(MainActivity.KEY_HOW_MATH_NOT);
            String coment = extras.getString(MainActivity.KEY_COMENT);

            String NoteText = fromMainActivityIntent.getExtras().getString(MainActivity.KEY_NOTE_TEXT);
            String NoteTheme = fromMainActivityIntent.getExtras().getString(MainActivity.KEY_THEME);


        }*/
    }

    public void BackData(String date, String rating, String howmath, String howmathnot, String coment){
        Intent result = new Intent();
        result.putExtra(MainActivity.KEY_DATE, date);
        result.putExtra(MainActivity.KEY_RATING, rating);
        result.putExtra(MainActivity.KEY_HOW_MACH, howmath);
        result.putExtra(MainActivity.KEY_HOW_MATH_NOT, howmathnot);
        result.putExtra(MainActivity.KEY_COMENT, coment);

        // Установка результата с кодом RESULT_OK и передача данных
        setResult(RESULT_OK, result);

        // Завершение активити
        finish();

    }
    //public void onClickBtnEnd(View view) {
}
