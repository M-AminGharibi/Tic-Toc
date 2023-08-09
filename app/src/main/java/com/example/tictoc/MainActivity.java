package com.example.tictoc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    boolean gameActive = true;
    public static int counter = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};  // 0 -> X      1 -> O      2 -> Null
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    TextView tv_playerO;
    TextView tv_playerX;
    TextView status;

    int playerOWinCount = 0;
    int playerXWinCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_playerO = findViewById(R.id.tv_PlayerO);
        tv_playerX = findViewById(R.id.tv_PlayerX);
        status = findViewById(R.id.tv_main_status);
        Button btn_Reset = findViewById(R.id.btn_reset);
        btn_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });


    }

    public void clickOnImage(View view) {
        ImageView clickedImageView = (ImageView) view;
        int tag = Integer.parseInt(clickedImageView.getTag().toString());
        if (!gameActive) {
            resetGame();
            //Reset the counter
            counter = 0;
        }
        if (gameState[tag] == 2) {
            // increase the counter
            // after every tap
            counter++;

            // check if its the last box
            if (counter == 9) {
                // reset the game
                gameActive = false;
            }

            gameState[tag] = activePlayer;
            clickedImageView.setTranslationY(-1500);

            if (activePlayer == 0) {
                clickedImageView.setImageResource(R.drawable.x);
                activePlayer = 1;
                status.setText("O's Turn - Tap to play");
            } else {
                clickedImageView.setImageResource(R.drawable.oo);
                activePlayer = 0;
                status.setText("X's Turn - Tap to play");
            }
            clickedImageView.animate().translationYBy(1500).rotation(360).setDuration(200);
        }


        int flag = 0;


        if (counter > 4) {
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]]
                        && gameState[winPosition[1]] == gameState[winPosition[2]]
                        && gameState[winPosition[0]] != 2) {
                    flag = 1;

                    String winnerStr;
                    gameActive = false;

                    if (activePlayer == 0) {
                        playerOWinCount++;
                        tv_playerO.setText(playerOWinCount + "");

                        //Toast.makeText(this, "O win", Toast.LENGTH_SHORT).show();

                    } else {
                        playerXWinCount++;
                        tv_playerX.setText(playerXWinCount + "");
                        //Toast.makeText(this, "X win", Toast.LENGTH_SHORT).show();

                    }
                    if (gameState[winPosition[0]] == 0) {
                        winnerStr = "X has won";
                    } else {
                        winnerStr = "O has won";
                    }
                    status.setText(winnerStr);

                   // resetGame();
                }
            }
        }

        if (counter == 9 && flag == 0) {
            status.setText("Match Draw");
            //resetGame();
        }


    }


    public void resetGame() {
        gameActive = true;
        Arrays.fill(gameState, 2);
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
        status.setText("X's Turn - Tap to play");


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