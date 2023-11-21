package com.example.personalefficiencydiary;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.personalefficiencydiary.placeholder.PlaceholderContent;

import java.time.LocalDate;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Customize parameters
    private String mParam1;
    private String mParam2;


    // ListView для отображения заметок
    ListView ThemesListView;

    Context context;

    Button button;

    // Адаптер для ListView
    SimpleCursorAdapter noteAdapter;

    DataBaseAccessor db;

    // Объект доступа к базе данных


     /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(String param1, String param2 ) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 6");
        //context = getContext();
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 7");
        //db = new DataBaseAccessor(context);
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 8");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 9");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 10");
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 11");
        ThemesListView = view.findViewById(R.id.ListView);
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 12");
        button = view.findViewById(R.id.button);
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 13");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Получение текущей даты
                String data = LocalDate.now().toString();
                //Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "Сегодня уже была создана запись", Toast.LENGTH_LONG).show();
                }
            }
            });
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 14");
        // Инициализация и установка адаптера для ListView
        noteAdapter = AdapterUpdate();
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 15");
        // Установка слушателя кликов для элементов ListView
        ThemesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // При клике на элемент открываем активити NoteActivity с деталями выбранной заметки
                //Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                Log.d(TAG, "\n\n");
                Log.d(TAG, "Создание 16");
                String date = ((Cursor) noteAdapter.getItem(i)).getString(1);
                List<String> permen =  db.selectNote(date);
                Log.d(TAG, permen.get(0));
                Log.d(TAG, "\n \n\n\n" + permen.get(0));

                MainActivity activity = (MainActivity) getActivity();
                if(activity != null)
                {
                    activity.perehod(date ,permen.get(0), permen.get(1), permen.get(2), permen.get(3));
                }
                // Передача данных в NoteActivity
            }
        });
        return view;
    }
// обновление адаптера
    private SimpleCursorAdapter AdapterUpdate() {
        // Получение адаптера из класса DataBaseAccessor
        SimpleCursorAdapter adapter = db.getCursorAdapter(context,
                android.R.layout.two_line_list_item, // Разметка одного элемента ListView
                new int[]{android.R.id.text1,android.R.id.text2}); // Текст этого элемента

        // Установка адаптера в ListView
        ThemesListView.setAdapter(adapter);
        return adapter;
    }
    // закрытие
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Закрытие базы данных при уничтожении активити
        db.close();
    }

    /*public void onClickBtnNew(View view) {
        // Получение текущей даты
        String data = LocalDate.now().toString();
        //Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Сегодня уже была создана запись", Toast.LENGTH_LONG).show();
        }
    }*/
}