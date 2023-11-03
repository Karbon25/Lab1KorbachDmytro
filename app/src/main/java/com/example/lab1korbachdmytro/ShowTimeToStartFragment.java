package com.example.lab1korbachdmytro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowTimeToStartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowTimeToStartFragment extends Fragment {


    // TODO: Rename and change types of parameters

    public ShowTimeToStartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ShowTimeToStartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowTimeToStartFragment newInstance() {
        ShowTimeToStartFragment fragment = new ShowTimeToStartFragment();
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
        View view = inflater.inflate(R.layout.fragment_show_time_to_start, container, false);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        TextView secondTimeView = getView().findViewById(R.id.time_to_start_finish);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.animation_start_text);
        CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) { // Початковий час 60 секунд (60000 мс), кожну секунду викликається onTick
            public void onTick(long millisUntilFinished) {

                long secondsRemaining = millisUntilFinished / 1000;
                if(secondsRemaining == 0){
                    secondTimeView.setText("Почали!");
                    secondTimeView.startAnimation(fadeInAnimation);
                }else{
                    secondTimeView.setText(String.valueOf(secondsRemaining));
                    secondTimeView.startAnimation(fadeInAnimation);
                }

            }

            public void onFinish() {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                }
            }
        };
        countDownTimer.start();

    }
}