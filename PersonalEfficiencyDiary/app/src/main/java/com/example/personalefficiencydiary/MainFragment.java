package com.example.personalefficiencydiary;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView ThemesListView;

    Context context;

    Button button;

    // Адаптер для ListView
    SimpleCursorAdapter noteAdapter;

    Context contextd = getContext();

    DataBaseAccessor db;


    // Объект доступа к базе данных

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 6");




        context = getContext();




        Log.d(TAG, context.toString());
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 7");


        db = new DataBaseAccessor(context);


        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 8");
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 9");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "Создание 10");


        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 11");


        ThemesListView = view.findViewById(R.id.ListView);

        Log.d(TAG, String.valueOf(ThemesListView.getCount()));

        Log.d(TAG, "Создание 12");

        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 12");


        noteAdapter = AdapterUpdate();


        Log.d(TAG, "\n\n");
        Log.d(TAG, "Создание 12");

        Log.d(TAG, String.valueOf(ThemesListView.getAdapter().getItem(1).toString()));


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
        //noteAdapter = AdapterUpdate();
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
    private SimpleCursorAdapter AdapterUpdate() {
        // Получение адаптера из класса DataBaseAccessor
        Log.d(TAG, "\n\n");
        Log.d(TAG, "ЗАпрос в создании адаптера");
        SimpleCursorAdapter adapter = db.getCursorAdapter(context,
                android.R.layout.two_line_list_item, // Разметка одного элемента ListView
                new int[]{android.R.id.text1,android.R.id.text2}); // Текст этого элемента

        Log.d(TAG, "\n\n");
        Log.d(TAG, adapter.getCursor().getColumnName(2));
        Log.d(TAG, "adapter.getCursor().getColumnName(2)");
        Log.d(TAG, ((Cursor) adapter.getItem(1)).getString(1));
        Log.d(TAG, String.valueOf(adapter.getCount()));


        // Установка адаптера в ListView
        ThemesListView.setAdapter(adapter);
        Log.d(TAG, "лист");
        Log.d(TAG, String.valueOf(ThemesListView.getCount()));//ThemesListView.getCount();
        return adapter;
    }
    // закрытие
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Закрытие базы данных при уничтожении активити
        db.close();
    }
}