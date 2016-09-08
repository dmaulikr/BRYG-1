package org.ldpgames.kurt.bryg.selectors;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kurt on 2016-08-06.
 */
public class SelectorOne {

    public ArrayList<String> colours2;

    public String[] colors = {
            "#ff0000",
            "#0000ff"
    };

    public String[] words = {
            "Blue",
            "Red"

    };

    public SelectorOne() {
        colours2 = new ArrayList<>();
        colours2.add("#ff0000");
        colours2.add("#0000ff");
    }


    public int getColor() {
        String color;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(colors.length);
        color = colors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

    public String getWord() {
        String word;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(words.length);
        word = words[randomNumber];
        return word;
    }

    public int assignColor() {
        String color;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(colours2.size());
        color = colours2.get(randomNumber);
        colours2.remove(randomNumber);
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }




}
