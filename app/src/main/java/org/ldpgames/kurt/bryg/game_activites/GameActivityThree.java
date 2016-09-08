package org.ldpgames.kurt.bryg.game_activites;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ldpgames.kurt.bryg.GameOverActivity;
import org.ldpgames.kurt.bryg.R;
import org.ldpgames.kurt.bryg.selectors.SelectorThree;

public class GameActivityThree extends AppCompatActivity {


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
    private Button buttonBottom;

    private TextView colorText;
    private int scoreInt;

    private ColorDrawable leftBackground;
    private ColorDrawable topBackground;
    private ColorDrawable bottomBackground;
    private ColorDrawable rightBackground;
    private TextView centerScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three);


        colorText = (TextView) findViewById(R.id.colourText);
        buttonTop = (Button) findViewById(R.id.buttonTop);
        buttonBottom = (Button) findViewById(R.id.buttonBottom);
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

        buttonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(bottomBackground);
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
        scoreInt = bundle.getInt("Score", scoreInt);
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
            case "Yellow": {
                if(drawable.getColor() ==  Color.YELLOW){
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

        SelectorThree selector = new SelectorThree();

        buttonTop.setBackgroundColor(selector.assignColor());
        topBackground = (ColorDrawable) buttonTop.getBackground();

        buttonBottom.setBackgroundColor(selector.assignColor());
        bottomBackground = (ColorDrawable) buttonBottom.getBackground();


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
        if(scoreInt % 20 == 0 && scoreInt > 10){
            gameTimer.cancel();
            gameTimer.start();
            setButtonColours();
        }
        setButtonColours();
    }

    @Override
    public void onBackPressed() {

    }

}
