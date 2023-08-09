package com.example.tictoc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};  // 0 -> X      1 -> O      2 -> Null
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void clickOnImage(View view) {
        ImageView clickedImageView = (ImageView) view;
        int tag = Integer.parseInt(clickedImageView.getTag().toString());
        gameState[tag] = activePlayer;


        clickedImageView.setTranslationY(-1500);
        if (activePlayer == 0) {
            clickedImageView.setImageResource(R.drawable.x);
            activePlayer = 1;
        } else {
            clickedImageView.setImageResource(R.drawable.oo);
            activePlayer = 0;
        }

        clickedImageView.animate().translationYBy(1500).rotation(360).setDuration(200);

        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]]
                    && gameState[winPosition[1]] == gameState[winPosition[2]]
                    && gameState[winPosition[0]] != 2) {
                Toast.makeText(this, "win", Toast.LENGTH_SHORT).show();
                resetGame();
            }
        }


    }



    public void resetGame(){
        Arrays.fill(gameState,2);
        activePlayer = 0;
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
//        ConstraintLayout constraintLayout = findViewById(R.id.container);
//        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
//            if (constraintLayout.getChildAt(i) instanceof ImageView) {
//                ImageView imageView = (ImageView)constraintLayout.getChildAt(i);
//                if (imageView.getTag() != null) {
//                    imageView.setImageDrawable(null);
//                }
//            }
//        }
    }


}