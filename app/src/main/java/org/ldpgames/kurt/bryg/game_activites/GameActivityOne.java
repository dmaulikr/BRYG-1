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
import org.ldpgames.kurt.bryg.selectors.SelectorOne;

public class GameActivityOne extends AppCompatActivity {

    private static final int POINTS_UNTIL_NEXT_LEVEL = 20;

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




    private Button buttonRight;
    private Button buttonLeft;


    private TextView colorText;
    private TextView centerScore;
    private int scoreInt;

    private ColorDrawable leftBackground;
    private ColorDrawable rightBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);


        colorText = (TextView) findViewById(R.id.colourText);
        buttonRight = (Button) findViewById(R.id.buttonRight);
        buttonLeft = (Button) findViewById(R.id.buttonLeft);
        centerScore = (TextView) findViewById(R.id.centerScore);

        gameTimer.start();
        setButtonColours();


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


    private void check(ColorDrawable drawable) {


        String compareTo = (String) colorText.getText();

        switch (compareTo){
            case "Red": {
                if(drawable.getColor() ==  Color.RED){
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
        gameTimer.cancel();
        startActivity(intent);
        finish();

    }

    private void setButtonColours(){

        SelectorOne selector = new SelectorOne();


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
        if(scoreInt == POINTS_UNTIL_NEXT_LEVEL){
            Intent nextStage = new Intent(this, GameActivityTwo.class);
            nextStage.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            nextStage.putExtra("Score",scoreInt);
            gameTimer.cancel();
            startActivity(nextStage);
            overridePendingTransition(0, 0);
            finish();
        } else {
            setButtonColours();
        }
    }

}
