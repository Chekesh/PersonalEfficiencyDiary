package com.example.personalefficiencydiary;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    TextView textViewDate;
    EditText editTextRating;
    EditText editTextHowMuch;
    EditText editTextHowMuchNot;
    EditText editTextComent;
    Bundle bundle = getArguments();
    Button button;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public BlankFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        textViewDate = view.findViewById(R.id.textView);
        editTextRating = view.findViewById(R.id.editTextEvaluation);
        editTextHowMuch = view.findViewById(R.id.editTextHowMuch);
        editTextHowMuchNot = view.findViewById(R.id.editTextHowMuchNot);
        editTextComent = view.findViewById(R.id.editText);

        // Получение данных из Intent
        Bundle bundle = getArguments();
        //Bundle extras = getIntent().getExtras();
        if (bundle != null) {
            // Извлечение данных из Intent и установка их в соответствующие Views
            String date = bundle.getString(MainActivity.KEY_DATE);
            String rating = bundle.getString(MainActivity.KEY_RATING);
            String howMuch = bundle.getString(MainActivity.KEY_HOW_MACH);
            String howMuchNot = bundle.getString(MainActivity.KEY_HOW_MATH_NOT);
            String coment = bundle.getString(MainActivity.KEY_COMENT);

            textViewDate.setText(date);
            editTextRating.setText(rating);
            editTextHowMuch.setText(howMuch);
            editTextHowMuchNot.setText(howMuchNot);
            editTextComent.setText(coment);
        }
        //textViewDate.setText("fgdfh");
        button = view.findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteActivity activity = (NoteActivity) getActivity();
                if(activity != null)
                {
                    activity.BackData(textViewDate.getText().toString(),editTextRating.getText().toString(),
                            editTextHowMuch.getText().toString(),editTextHowMuchNot.getText().toString(),
                            editTextComent.getText().toString());
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    /*public void onClickBtnEnd(View view) {
        // Создание нового Intent для передачи данных обратно в MainActivity
        NoteActivity activity = (NoteActivity) getActivity();
        if(activity != null)
        {
            activity.BackData(textViewDate.getText().toString(),editTextRating.getText().toString(),
                    editTextHowMuch.getText().toString(),editTextHowMuchNot.getText().toString(),
                    editTextComent.getText().toString());
        }
    }*/
}