package org.ldpgames.kurt.bryg.game_activites;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ldpgames.kurt.bryg.GameOverActivity;
import org.ldpgames.kurt.bryg.R;
import org.ldpgames.kurt.bryg.selectors.SelectorTwo;

public class GameActivityTwo extends AppCompatActivity {

    private static final int POINTS_UNTIL_NEXT_LEVEL = 40;

    private long timeRemaining = 16000;

    private CountDownTimer gameTimer = new CountDownTimer(timeRemaining,1000) {
        @Override
        public void onTick(long l) {
            TextView timerText = (TextView) findViewById(R.id.textView);
            assert timerText != null;
            timerText.setText(String.valueOf(l/ 1000));

        }

        @Override
        public void onFinish() {
            gameOver();
        }
    };


    private Button buttonTop;
    private Button buttonRight;
    private Button buttonLeft;

    private TextView colorText;
    private int scoreInt;

    private ColorDrawable leftBackground;
    private ColorDrawable topBackground;
    private ColorDrawable rightBackground;
    private TextView centerScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two);


        colorText = (TextView) findViewById(R.id.colourText);
        buttonTop = (Button) findViewById(R.id.buttonTop);
        buttonRight = (Button) findViewById(R.id.buttonRight);
        buttonLeft = (Button) findViewById(R.id.buttonLeft);
        centerScore = (TextView) findViewById(R.id.centerScore);


        setScore();
        gameTimer.start();
        setButtonColours();

        buttonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(topBackground);
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(rightBackground);
            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(leftBackground);
            }
        });
    }

    private void setScore() {

        Bundle bundle = getIntent().getExtras();
        int sentInt = bundle.getInt("Score", scoreInt);
        scoreInt = sentInt;
        centerScore.setText(String.valueOf(scoreInt));




    }


    private void check(ColorDrawable drawable) {


        String compareTo = (String) colorText.getText();

        switch (compareTo){
            case "Red": {
                if(drawable.getColor() ==  Color.RED){
                    updateScore();
                } else gameOver();
            }
            break;
            case "Green": {
                if(drawable.getColor() ==  Color.GREEN){
                    updateScore();
                } else gameOver();
            }
            break;
            case "Blue": {
                if(drawable.getColor() ==  Color.BLUE){
                    updateScore();
                } else gameOver();
            }
            break;
        }


    }

    private void gameOver() {
        Intent intent = new Intent(this,GameOverActivity.class);
        intent.putExtra("Score",scoreInt);
        startActivity(intent);
        gameTimer.cancel();
    }

    private void setButtonColours(){

        SelectorTwo selector = new SelectorTwo();

        buttonTop.setBackgroundColor(selector.assignColor());
        topBackground = (ColorDrawable) buttonTop.getBackground();


        buttonRight.setBackgroundColor(selector.assignColor());
        rightBackground = (ColorDrawable) buttonRight.getBackground();

        buttonLeft.setBackgroundColor(selector.assignColor());
        leftBackground = (ColorDrawable) buttonLeft.getBackground();

        colorText.setTextColor(selector.getColor());
        colorText.setText(selector.getWord());
    }

    private void updateScore(){
        scoreInt++;
        centerScore.setText(String.valueOf(scoreInt));
        if(scoreInt == POINTS_UNTIL_NEXT_LEVEL) {
            Intent nextStage = new Intent(this, GameActivityThree.class);
            nextStage.putExtra("Score", scoreInt);
            gameTimer.cancel();
            nextStage.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(nextStage);
            overridePendingTransition(0, 0);
            finish();
        }  else {
            setButtonColours();
        }
    }

    @Override
    public void onBackPressed() {

    }

}
