package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow , 1 = red
    int activePlayer = 0;

    boolean gameIsActive = true;
    // 2 means unplayed
    int [] gameState = {2,2,2,2,2,2,2,2,2}; // it chnaging like {1,0,1,0,1}

    int [][] winnigPossitons = {{0,1,2},{3,4,5},{6,7, 8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameIsActive)
        {
            gameState[tappedCounter] = activePlayer; // activePlayer = 0
            if(activePlayer == 0)
            {
                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else{
                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int [] winnigPossition : winnigPossitons)
            {
                if(gameState[winnigPossition[0]] == gameState[winnigPossition[1]] && gameState[winnigPossition[1]]
                == gameState[winnigPossition[2]] && gameState[winnigPossition[0]] != 2)
                {
                    // someone has won!
                    gameIsActive = false;
                    String winner = "WINNER";
                    if(gameState[winnigPossition[0]] == 0)
                    {
                        winner = "WINNER";
                    }


                    EditText winnerMessage = (EditText) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    return;
                }

            }
            boolean gameIsOver = true;
            for(int counterState : gameState)
            {
                if(counterState == 2)
                {
                    gameIsOver = false;
                    break;
                }
            }
            if(gameIsOver)
            {
                EditText winnerMessage = (EditText) findViewById(R.id.winnerMessage);
                winnerMessage.setText("  DRAW");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
            }
        }

    }

    public void playAgain(View view)
    {
        gameIsActive = true;
        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for(int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find the EditText by its ID
        EditText rohit = (EditText) findViewById(R.id.rohit);

// Create a TranslateAnimation for left to right motion
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0f,  // Start position: from 0% of parent width (left)
                Animation.RELATIVE_TO_PARENT, 1f,  // End position: to 100% of parent width (right)
                Animation.RELATIVE_TO_PARENT, 0f,  // Start position: no change in vertical direction
                Animation.RELATIVE_TO_PARENT, 0f   // End position: no change in vertical direction
        );

// Set the duration for the animation (in milliseconds)
        animation.setDuration(3000);  // 3 seconds for one full cycle (left to right)

// Set the repeat count to infinite so it keeps repeating
        animation.setRepeatCount(Animation.INFINITE);

// Set the repeat mode to reverse to go back from right to left after each cycle
        animation.setRepeatMode(Animation.REVERSE);

// Start the animation on the EditText
        rohit.startAnimation(animation);

    }
}