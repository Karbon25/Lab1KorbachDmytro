package com.example.lab1korbachdmytro;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link game_place_5x5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class game_place_5x5 extends Fragment {

    private Random rnd;
    private boolean load = false;
    private int timeGame;
    private int min_reaction_time;
    private int max_reaction_time;
    private int score;
    private int currentButtonActive;
    private int listButtons[];
    private TextView timeView;
    private CountDownTimer timer;
    private Timer timerReaction;
    private TimerTask task;
    private Animation fadeInAnimation;

    public game_place_5x5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment game_place_5x5.
     */
    // TODO: Rename and change types and number of parameters
    public static game_place_5x5 newInstance() {
        game_place_5x5 fragment = new game_place_5x5();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        if(arg != null) {
            timeGame = arg.getInt("timeGame");
            min_reaction_time = arg.getInt("minReactionTime");
            max_reaction_time = arg.getInt("maxReactionTime");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_place_5x5, container, false);
        rnd = new Random();
        timeView = view.findViewById(R.id.time_place_5x5);
        fadeInAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.animation_start_text);
        timer = new CountDownTimer(timeGame*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                timeView.setText(String.valueOf(secondsRemaining));
                timeView.startAnimation(fadeInAnimation);
            }
            public void onFinish() {
                timerReaction.cancel();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                EndGameFragment endGameFragment = new EndGameFragment();
                Bundle args = new Bundle();
                args.putInt("score", score);
                endGameFragment.setArguments(args);
                transaction.replace(R.id.main_activity_f_layout, endGameFragment);
                transaction.commit();
            }
        };

        timerReaction = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                view.findViewById(currentButtonActive).setBackgroundColor(Color.parseColor("#C8C7C7"));
                currentButtonActive = listButtons[rnd.nextInt(25)];
                view.findViewById(currentButtonActive).setBackgroundColor(Color.parseColor("#FF0000"));
            }
        };
        View.OnClickListener commonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView scoreText = getView().findViewById(R.id.score_place_5x5);
                if (v.getId() == currentButtonActive) {
                    score+=2;
                } else {
                    score--;
                }
                scoreText.setText("Рахунок: " + Integer.toString(score));
                scoreText.startAnimation(fadeInAnimation);
            }
        };

        if(load == false) {
            load = true;
            score = 0;
            listButtons = new int[] {
                    R.id.button_place_5x5_1,
                    R.id.button_place_5x5_2,
                    R.id.button_place_5x5_3,
                    R.id.button_place_5x5_4,
                    R.id.button_place_5x5_5,
                    R.id.button_place_5x5_6,
                    R.id.button_place_5x5_7,
                    R.id.button_place_5x5_8,
                    R.id.button_place_5x5_9,
                    R.id.button_place_5x5_10,
                    R.id.button_place_5x5_11,
                    R.id.button_place_5x5_12,
                    R.id.button_place_5x5_13,
                    R.id.button_place_5x5_14,
                    R.id.button_place_5x5_15,
                    R.id.button_place_5x5_16,
                    R.id.button_place_5x5_17,
                    R.id.button_place_5x5_18,
                    R.id.button_place_5x5_19,
                    R.id.button_place_5x5_20,
                    R.id.button_place_5x5_21,
                    R.id.button_place_5x5_22,
                    R.id.button_place_5x5_23,
                    R.id.button_place_5x5_24,
                    R.id.button_place_5x5_25
            };

            currentButtonActive = listButtons[0];


            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(null); // Додайте до back stack для можливості повернення
            ShowTimeToStartFragment startTimerFragment = new ShowTimeToStartFragment();

            transaction.replace(R.id.main_activity_f_layout, startTimerFragment);


            transaction.commit();
        }else{
            for(int i:listButtons){
                view.findViewById(i).setOnClickListener((View.OnClickListener) commonClickListener);
            }
            timer.start();
            timerReaction.schedule(task, 100,rnd.nextInt(max_reaction_time - min_reaction_time + 1) + max_reaction_time);
        }
        // Inflate the layout for this fragment
        return view;
    }
}