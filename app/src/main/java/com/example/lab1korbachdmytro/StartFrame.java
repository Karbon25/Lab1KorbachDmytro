package com.example.lab1korbachdmytro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartFrame extends Fragment {

    CountDownTimer timer;

    public StartFrame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StartFrame.
     */
    // TODO: Rename and change types and number of parameters
    public static StartFrame newInstance() {
        StartFrame fragment = new StartFrame();
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
        View view = inflater.inflate(R.layout.fragment_start_frame, container, false);


        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onStart() {


        super.onStart();
        getView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    changeFrame();
                    return true;
                }
                return true;
            }
        });
        timer = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                changeFrame();
            }
        }.start();
    }
    private void changeFrame(){
        timer.cancel();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

        SettingFragment settingFragment = new SettingFragment();
        transaction.replace(R.id.main_activity_f_layout, settingFragment);

        transaction.commit();
    }

}