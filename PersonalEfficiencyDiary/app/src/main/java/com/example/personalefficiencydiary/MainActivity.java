package com.example.personalefficiencydiary;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final public static String KEY_DATE = "Date";
    final public static String KEY_RATING = "Rating";
    final public static String KEY_HOW_MACH = "HowMuch";
    final public static String KEY_HOW_MATH_NOT = "HowMuchNot";
    final public static String KEY_COMENT = "Coment";
    Context context = this;
    ListView ThemesListView;
    SimpleCursorAdapter noteAdapter;
    DataBaseAccessor db = new DataBaseAccessor(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ThemesListView = findViewById(R.id.ListView);
        noteAdapter = AdapterUpdate();

        ThemesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                String date = ((Cursor) noteAdapter.getItem(i)).getString(1);
                //String rating = ((Cursor) noteAdapter.getItem(i)).getString(2);
                List<String> permen =  db.selectNote(date);

                intent.putExtra(KEY_DATE, date);
                intent.putExtra(KEY_RATING, permen.get(0));
                intent.putExtra(KEY_HOW_MACH, permen.get(1));
                intent.putExtra(KEY_HOW_MATH_NOT, permen.get(2));
                intent.putExtra(KEY_COMENT, permen.get(3));

                NotesLauncher.launch(intent);
            }
        });

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // закрыть БД
        db.close();
    }
    public void onClickBtnNew(View view) {
        String data =LocalDate.now().toString();


        boolean proxod = true;
        for(int i = 0; i<ThemesListView.getAdapter().getCount(); i++){
            if(data.equals(((Cursor) noteAdapter.getItem(i)).getString(1))){
                proxod = false;
                Log.d(TAG, "Создание ");
            }
        }
        if(proxod){
            db.NewNote(data);
            noteAdapter = AdapterUpdate();
        }else {
            Toast.makeText(MainActivity.this, "Сегодня уже была создана запись", Toast.LENGTH_LONG).show();
        }
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
                        String howMuch = intent.getStringExtra(KEY_HOW_MACH);
                        String howMuchNot = intent.getStringExtra(KEY_HOW_MATH_NOT);
                        String coment = intent.getStringExtra(KEY_COMENT);

                        db.updateNote(date, rating, howMuch, howMuchNot, coment);

                        noteAdapter = AdapterUpdate();
                    }
                }
            });

}

