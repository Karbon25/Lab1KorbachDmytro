package com.example.lab1korbachdmytro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EndGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EndGameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters


    public EndGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EndGameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EndGameFragment newInstance() {
        EndGameFragment fragment = new EndGameFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end_game, container, false);
        if (getArguments() != null){
            TextView textScore = view.findViewById(R.id.end_fragment_score);
            textScore.setText(Integer.toString(getArguments().getInt("score")));
        }
        Fragment thisFragment = this;
        CountDownTimer timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    transaction.remove(thisFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                }
            }
        };
        timer.start();
        // Inflate the layout for this fragment
        return view;
    }
}