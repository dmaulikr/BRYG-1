package org.ldpgames.kurt.bryg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ldpgames.kurt.bryg.R;

import org.ldpgames.kurt.bryg.game_activites.GameActivityOne;
import org.ldpgames.kurt.bryg.selectors.SelectorTwo;

public class GameOverActivity extends AppCompatActivity {

    private int scoreInt;
    private TextView yourScore;
    private Button restart;
    private TextView highScore;
    private String name;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        yourScore = (TextView) findViewById(R.id.yourScore);
        restart = (Button) findViewById(R.id.submitButton);
        highScore = (TextView) findViewById(R.id.yourHighScore);
        homeButton = (Button) findViewById(R.id.homeButtonGameOver);

        SelectorTwo selector = new SelectorTwo();



        Intent startingIntent = getIntent();
        int sentInt = startingIntent.getIntExtra("Score",scoreInt);

        yourScore.setText("Your Score: " + String.valueOf(sentInt));
        yourScore.setTextColor(selector.getColor());

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });

        SharedPreferences prefs = getSharedPreferences("high",0);
        String score = prefs.getString("tag","null");

        SharedPreferences.Editor editor = prefs.edit();

        if(sentInt > Double.parseDouble(score)){
            editor.putString("tag", String.valueOf(sentInt)).commit();
            highScore.setText("High Score: " + sentInt);
            highScore.setTextColor(selector.getColor());
        } else {
            highScore.setText("High Score: " + score);
            highScore.setTextColor(selector.getColor());
        }



    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void restart() {

        Intent intent = new Intent(this,GameActivityOne.class);
        intent.putExtra("Name", name);
        intent.putExtra("Score", scoreInt);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        Toast toast = Toast.makeText(GameOverActivity.this,"The Game is Over,\nPlease Submit Score!",Toast.LENGTH_SHORT);
        toast.show();
    }
}
