package com.example.lab1korbachdmytro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);




        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

        Button myButton = getView().findViewById(R.id.button_start_game);

        View.OnClickListener buttonStartClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle outState = getArguments();
                if(outState == null){
                    outState = new Bundle();
                }
                outState.putInt("setting_game_place_size", ((RadioGroup)(getView().findViewById(R.id.setting_game_place_size))).getCheckedRadioButtonId());
                outState.putInt("setting_game_time", ((RadioGroup)(getView().findViewById(R.id.setting_game_time))).getCheckedRadioButtonId());
                outState.putInt("setting_reaction_time", ((RadioGroup)(getView().findViewById(R.id.setting_reaction_time))).getCheckedRadioButtonId());
                setArguments(outState);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();



                Bundle gameArgument = new Bundle();
                RadioButton timeGame = getView().findViewById(((RadioGroup)(getView().findViewById(R.id.setting_game_time))).getCheckedRadioButtonId());
                gameArgument.putInt("timeGame", Integer.parseInt(timeGame.getText().toString()));
                int setReactionTime = ((RadioGroup)(getView().findViewById(R.id.setting_reaction_time))).getCheckedRadioButtonId();
                if(setReactionTime == R.id.r_button_time_reaction_300_600) {
                    gameArgument.putInt("minReactionTime", 300);
                    gameArgument.putInt("maxReactionTime", 600);
                }else if(setReactionTime == R.id.r_button_time_reaction_500_700) {
                    gameArgument.putInt("minReactionTime", 500);
                    gameArgument.putInt("maxReactionTime", 700);
                }else if(setReactionTime == R.id.r_button_time_reaction_700_1000) {
                    gameArgument.putInt("minReactionTime", 700);
                    gameArgument.putInt("maxReactionTime", 1000);
                }else if(setReactionTime == R.id.r_button_time_reaction_1000_1500){
                        gameArgument.putInt("minReactionTime", 1000);
                        gameArgument.putInt("maxReactionTime", 1500);
                }
                int setGamePlace = ((RadioGroup)(getView().findViewById(R.id.setting_game_place_size))).getCheckedRadioButtonId();
                if(setGamePlace == R.id.r_button_place_3_3){
                    game_place_3x3 gamePlace = new game_place_3x3();
                    gamePlace.setArguments(gameArgument);
                    transaction.replace(R.id.main_activity_f_layout, gamePlace);
                }else if(setGamePlace == R.id.r_button_place_4_4){
                    game_place_4x4 gamePlace = new game_place_4x4();
                    gamePlace.setArguments(gameArgument);
                    transaction.replace(R.id.main_activity_f_layout, gamePlace);

                }else if(setGamePlace == R.id.r_button_place_5_5){
                    game_place_5x5 gamePlace = new game_place_5x5();
                    gamePlace.setArguments(gameArgument);
                    transaction.replace(R.id.main_activity_f_layout, gamePlace);
                }
                transaction.addToBackStack(null);
                transaction.commit();
            }
        };
        myButton.setOnClickListener(buttonStartClickListener);
    }

    @Override
    public void onResume() {

        super.onResume();
        Bundle savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            ((RadioGroup) (getView().findViewById(R.id.setting_game_place_size))).check(savedInstanceState.getInt("setting_game_place_size"));
            ((RadioGroup) (getView().findViewById(R.id.setting_game_time))).check(savedInstanceState.getInt("setting_game_time"));
            ((RadioGroup) (getView().findViewById(R.id.setting_reaction_time))).check(savedInstanceState.getInt("setting_reaction_time"));
        }
    }


}