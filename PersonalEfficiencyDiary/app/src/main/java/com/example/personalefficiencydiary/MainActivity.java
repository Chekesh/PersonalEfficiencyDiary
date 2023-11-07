package com.example.personalefficiencydiary;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    final public static String KEY_DATE = "Date";
    final public static String KEY_RATING = "Rating";
    final public static String KEY_EVALUATION = "Evaluation";
    final public static String KEY_HOW_MACH = "HowMuch";
    final public static String KEY_HOW_MATH_NOT = "HowMuchNot";
    final public static String KEY_COMENT = "Coment";

    ListView ThemesListView;

    SimpleCursorAdapter noteAdapter;
    DataBaseAccessor db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;

        noteAdapter = AdapterUpdate();

        db = new DataBaseAccessor(context);
    }

    private SimpleCursorAdapter AdapterUpdate() {
        // получить адаптер из класса
        SimpleCursorAdapter adapter = db.getCursorAdapter(this,
                android.R.layout.two_line_list_item, // Разметка одного элемента ListView
                new int[]{android.R.id.text1,android.R.id.text2}); // текст этого элемента

        // установить адаптер в listview
        ThemesListView.setAdapter(adapter);
        return adapter;
    }

    ActivityResultLauncher<Intent> NotesLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    // все ли хорошо при получении данных из дочерней активити?
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        String date = intent.getStringExtra(KEY_DATE);
                        String rating = intent.getStringExtra(KEY_RATING);
                        String Evaluation = intent.getStringExtra(KEY_EVALUATION);
                        String HowMuch = intent.getStringExtra(KEY_HOW_MACH);
                        String HowMuchNot = intent.getStringExtra(KEY_HOW_MATH_NOT);
                        String Coment = intent.getStringExtra(KEY_COMENT);
                    }
                }
            });

}

