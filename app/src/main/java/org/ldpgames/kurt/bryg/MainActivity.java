package org.ldpgames.kurt.bryg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ldpgames.kurt.bryg.R;

import org.ldpgames.kurt.bryg.game_activites.GameActivityOne;
import org.ldpgames.kurt.bryg.selectors.SelectorTwo;

public class MainActivity extends AppCompatActivity {

    private Button howToButton;
    private Button startButton;
    private TextView highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        howToButton = (Button) findViewById(R.id.howToButton);
        startButton = (Button) findViewById(R.id.startButton);
        highScore = (TextView) findViewById(R.id.highScoreMain);

        SelectorTwo selector = new SelectorTwo();


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        howToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHowTO();
            }
        });

        SharedPreferences prefs = getSharedPreferences("high",0);
        String score = prefs.getString("tag","null");

        SharedPreferences.Editor editor = prefs.edit();

        if(score.equals("null")){
            editor.putString("tag", "0").commit();
            highScore.setText("High Score: 0");
            highScore.setTextColor(selector.getColor());
        } else {
            highScore.setText("High Score: " + score);
            highScore.setTextColor(selector.getColor());
        }

        //Toast toast = Toast.makeText(MainActivity.this,"Welcome To Red,Blue,Yellow! \nCreated By LDP Games",Toast.LENGTH_SHORT);
    }

    private void startHowTO() {
        Intent intent = new Intent(this,HowToActivity.class);
        startActivity(intent);
    }

    private void startGame() {
        Intent intent = new Intent(this,GameActivityOne.class);
        startActivity(intent);
    }
}
