package com.example.tictoc;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    public static int counter = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};  // 0 -> X      1 -> O      2 -> Null
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    TextView tv_playerO, tv_playerX, status, winner;
    ImageView[] imageViews;
    int playerOWinCount, playerXWinCount, activePlayer, flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_playerO = findViewById(R.id.tv_PlayerO);
        tv_playerX = findViewById(R.id.tv_PlayerX);
        status = findViewById(R.id.tv_main_status);
        winner = findViewById(R.id.tv_main_winner);
        Button btn_Reset = findViewById(R.id.btn_reset);
        ImageView imageView0 = findViewById(R.id.imageView0);
        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);
        ImageView imageView5 = findViewById(R.id.imageView5);
        ImageView imageView6 = findViewById(R.id.imageView6);
        ImageView imageView7 = findViewById(R.id.imageView7);
        ImageView imageView8 = findViewById(R.id.imageView8);
        imageViews = new ImageView[]{imageView0, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8};
        btn_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                gameActive = true;
            }
        });
    }

    public void clickOnImage(View view) {
        ImageView clickedImageView = (ImageView) view;
        int tag = Integer.parseInt(clickedImageView.getTag().toString());
//        if (!gameActive) {
//            //resetGame();
//            //Reset the counter
//            counter = 0;
//        }
        if (gameActive == true) {

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
                clickedImageView.setTranslationY(-1250);

                if (activePlayer == 0) {
                    clickedImageView.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    status.setText("O's Turn - Tap to play");
                } else {
                    clickedImageView.setImageResource(R.drawable.oo);
                    activePlayer = 0;
                    status.setText("X's Turn - Tap to play");
                }
                clickedImageView.animate().translationYBy(1250).rotation(180).setDuration(150);
            }

            if (counter > 4) {
                winnerCheck();
            }

            if (counter == 9 && flag == 0) {
                status.setText("Match Draw");
                winner.setText("Match Draw");
                //resetGame();
            }

        }
    }


    public void winnerCheck() {
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]]
                    && gameState[winPosition[1]] == gameState[winPosition[2]]
                    && gameState[winPosition[0]] != 2) {
                flag = 1;

                System.out.println(winPosition[0]);
                System.out.println(winPosition[1]);
                System.out.println(winPosition[2]);


                String winnerStr;
                gameActive = false;
                Animation pulse = AnimationUtils.loadAnimation(this, R.anim.heart_pulse);

                if (activePlayer == 0) {
                    playerOWinCount++;
                    tv_playerO.setText(playerOWinCount + "");
                    //Toast.makeText(this, "O win", Toast.LENGTH_SHORT).show();

                } else {
                    playerXWinCount++;
                    tv_playerX.setText(playerXWinCount + "");
                    //Toast.makeText(this, "X win", Toast.LENGTH_SHORT).show();
                }
                imageViews[winPosition[0]].startAnimation(pulse);
                imageViews[winPosition[1]].startAnimation(pulse);
                imageViews[winPosition[2]].startAnimation(pulse);
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won, Reset The Game"; //status Text View
                    winner.setText("X won!"); //winner Text View
                } else {
                    winnerStr = "O has won, Reset The Game"; //status Text View
                    winner.setText("O won!"); //winner Text View
                }
                status.setText(winnerStr);

                // resetGame();
            }
        }
    }

    public void resetGame() {
        Arrays.fill(gameState, 2);
        activePlayer = 0;
        counter = 0;
        flag = 0;
        for (ImageView imageView : imageViews) {
            imageView.setImageResource(0);
            imageView.clearAnimation();
        }
        status.setText("X's Turn - Tap to play");
        winner.setText(null);


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