package com.example.rembill_2;

import android.widget.Toast;

public class Message {

    private   MainActivity MA;
    void SetMA(MainActivity MA){this.MA=MA;}
    public Object msg;

    public void show(String permission_granted, MainActivity mainActivity) {

        Toast.makeText(MA.getApplicationContext(), (Integer) msg,Toast.LENGTH_SHORT).show();

    }

         void Show(String msg)
        {
            Toast.makeText(MA.getApplicationContext(), msg,Toast.LENGTH_SHORT).show();

        }

    }

