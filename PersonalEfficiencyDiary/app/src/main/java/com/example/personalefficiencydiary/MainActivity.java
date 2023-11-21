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
import android.content.res.Configuration;
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

    // Константы для ключей, используемых для передачи данных между активити
    final public static String KEY_DATE = "Date";
    final public static String KEY_RATING = "Rating";
    final public static String KEY_HOW_MACH = "HowMuch";
    final public static String KEY_HOW_MATH_NOT = "HowMuchNot";
    final public static String KEY_COMENT = "Coment";

    // Контекст для приложения
    Context context = this;

    // ListView для отображения заметок
    //ListView ThemesListView;

    // Адаптер для ListView
    //SimpleCursorAdapter noteAdapter;

    // Объект доступа к базе данных
    //DataBaseAccessor db = new DataBaseAccessor(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (isTablet(this)) {
            // Устройство - планшет, используем разметку с двумя фрагментами
            setContentView(R.layout.activity_tablet);
        } else {
            // Устройство - телефон, используем разметку с одним фрагментом
            setContentView(R.layout.activity_main);
        }*/
        setContentView(R.layout.activity_main);

        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 1");

        MainFragment fragment = new MainFragment();
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 2");
        Bundle bundle = new Bundle();
        //bundle.put
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 3");
        fragment.setArguments(bundle);
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 4");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .commit();
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 5");
        //Bundle bundle = new Bundle();
        /*bundle.put(MainActivity.KEY_DATE,date);
        bundle.putString(MainActivity.KEY_RATING,rating);
        bundle.putString(MainActivity.KEY_HOW_MACH,howMuch);
        bundle.putString(MainActivity.KEY_HOW_MATH_NOT,howMuchNot);
        bundle.putString(MainActivity.KEY_COMENT,coment);*/
       // fragment.setArguments(bundle);

        //getSupportFragmentManager().beginTransaction()
          //      .replace(R.id.fragmentContainerView,fragment)
            //    .commit();

        // Инициализация ListView
        //ThemesListView = findViewById(R.id.ListView);

        // Инициализация и установка адаптера для ListView
        //noteAdapter = AdapterUpdate();

        /*Bundle bundle = new Bundle();
        //bundle.put

        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .commit();*/

        // Установка слушателя кликов для элементов ListView
        /*ThemesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // При клике на элемент открываем активити NoteActivity с деталями выбранной заметки
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                String date = ((Cursor) noteAdapter.getItem(i)).getString(1);
                List<String> permen =  db.selectNote(date);
                Log.d(TAG, permen.get(0));
                Log.d(TAG, "\n \n\n\n" + permen.get(0));

                // Передача данных в NoteActivity
                intent.putExtra(KEY_DATE, date);
                intent.putExtra(KEY_RATING, permen.get(0));
                intent.putExtra(KEY_HOW_MACH, permen.get(1));
                intent.putExtra(KEY_HOW_MATH_NOT, permen.get(2));
                intent.putExtra(KEY_COMENT, permen.get(3));

                // Запуск активити с передачей данных
                NotesLauncher.launch(intent);
            }
        });*/
    }

    public void perehod(String date ,String rating,String  howMuch,String howMuchNot,String coment){
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        intent.putExtra(KEY_DATE, date);
        intent.putExtra(KEY_RATING, rating);
        intent.putExtra(KEY_HOW_MACH, howMuch);
        intent.putExtra(KEY_HOW_MATH_NOT, howMuchNot);
        intent.putExtra(KEY_COMENT, coment);
        NotesLauncher.launch(intent);
    }

    /*private SimpleCursorAdapter AdapterUpdate() {
        // Получение адаптера из класса DataBaseAccessor
        SimpleCursorAdapter adapter = db.getCursorAdapter(this,
                android.R.layout.two_line_list_item, // Разметка одного элемента ListView
                new int[]{android.R.id.text1,android.R.id.text2}); // Текст этого элемента

        // Установка адаптера в ListView
        ThemesListView.setAdapter(adapter);
        return adapter;
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Закрытие базы данных при уничтожении активити
        //db.close();
    }

    /*public void onClickBtnNew(View view) {
        // Получение текущей даты
        String data = LocalDate.now().toString();
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
        boolean proxod = true;

        // Проверка, была ли создана запись на текущую дату
        for(int i = 0; i < ThemesListView.getAdapter().getCount(); i++){
            if(data.equals(((Cursor) noteAdapter.getItem(i)).getString(1))){
                proxod = false;
                Log.d(TAG, "Создание ");
            }
        }

        // Если записи на текущую дату нет, создаем новую запись в базе данных
        if(proxod){
            db.NewNote(data);
            noteAdapter = AdapterUpdate();
        } else {
            // Иначе выводим уведомление
            Toast.makeText(MainActivity.this, "Сегодня уже была создана запись", Toast.LENGTH_LONG).show();
        }
    }*/

    // Обработчик результата активити для обновления данных после возвращения из NoteActivity
    ActivityResultLauncher<Intent> NotesLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    // Проверка успешности получения данных из дочерней активити
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        String date = intent.getStringExtra(KEY_DATE);
                        String rating = intent.getStringExtra(KEY_RATING);
                        String howMuch = intent.getStringExtra(KEY_HOW_MACH);
                        String howMuchNot = intent.getStringExtra(KEY_HOW_MATH_NOT);
                        String coment = intent.getStringExtra(KEY_COMENT);

                        // Обновление записи в базе данных
                        //db.updateNote(date, rating, howMuch, howMuchNot, coment);

                        // Обновление адаптера после изменения данных
                        //noteAdapter = AdapterUpdate();
                    }
                }
            });
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}
