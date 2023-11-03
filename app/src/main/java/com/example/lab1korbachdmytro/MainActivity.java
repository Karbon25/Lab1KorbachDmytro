package com.example.lab1korbachdmytro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Додати фрагмент до контейнера
        StartFrame startFrame = new StartFrame();
        transaction.add(R.id.main_activity_f_layout, startFrame);

        // Застосувати транзакцію
        transaction.commit();



    }
}