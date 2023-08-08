package com.example.tictoc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void clickOnImage(View view) {
        ImageView clickedImageView = (ImageView) view;
        clickedImageView.setTranslationY(-1500);

        if (activePlayer == 0) {
            clickedImageView.setImageResource(R.drawable.x);
            activePlayer = 1;
        } else {
            clickedImageView.setImageResource(R.drawable.oo);
            activePlayer = 0;
        }

        clickedImageView.animate().translationYBy(1500).rotation(360).setDuration(200);

    }


}