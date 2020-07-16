package com.example.conn3ct;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //0: Red, 1: yellow, 2: None
    int chip = 1;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningState = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameActive = true;
    boolean draw = false;
    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        TextView turn = findViewById(R.id.turnView);

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        gameState[tappedCounter] = chip;

        counter.setTranslationY(-1500);
        if (gameActive == true) {
            if (chip == 1) {
                counter.setImageResource(R.drawable.yellow);
                chip = 0;
                turn.setText("Red Turn");
                turn.setTextColor(Color.parseColor("#FF0000"));
            } else {
                counter.setImageResource(R.drawable.red);
                chip = 1;
                turn.setText("Yellow Turn");
                turn.setTextColor(Color.parseColor("#ffffbb33"));
            }

            counter.animate().translationYBy(1500).setDuration(500);
            counter.setClickable(false);

            for (int[] winningPosition : winningState) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    if (gameState[winningPosition[0]] == 0) {
                        turn.setText("Red Wins!");
                        turn.setTextColor(Color.parseColor("#FF0000"));
                        gameActive = false;
                        Button reset = findViewById(R.id.resetButton);
                        reset.animate().alpha(1);
                        reset.setClickable(true);
                    } else {
                        turn.setText("Yellow Wins!");
                        turn.setTextColor(Color.parseColor("#ffffbb33"));
                        gameActive = false;
                        Button reset = findViewById(R.id.resetButton);
                        reset.animate().alpha(1);
                        reset.setClickable(true);
                    }
                } else{
                    for(int i = 0; i< 9; i++){
                        if(gameState[i] == 2){
                            draw = false;
                            break;
                        }
                        else{
                            draw = true;

                        }
                    }
                    if (draw){
                        turn.setText("LOSERS!!!");
                        turn.setTextColor(Color.parseColor("#000000"));
                        Button reset = findViewById(R.id.resetButton);
                        reset.animate().alpha(1);
                        reset.setClickable(true);
                        gameActive = false;
                    }
                }
            }
        }
    }

    public void reset(View view){
        ImageView image1 = findViewById(R.id.imageViewtl);
        ImageView image2 = findViewById(R.id.imageViewtc);
        ImageView image3 = findViewById(R.id.imageViewtr);
        ImageView image4 = findViewById(R.id.imageViewml);
        ImageView image5 = findViewById(R.id.imageViewmc);
        ImageView image6 = findViewById(R.id.imageViewmr);
        ImageView image7 = findViewById(R.id.imageViewll);
        ImageView image8 = findViewById(R.id.imageViewlc);
        ImageView image9 = findViewById(R.id.imageViewlr);
        ImageView[] images = {image1, image2, image3,  image4 , image5, image6, image7, image8, image9};
        Button reset = findViewById(R.id.resetButton);
        TextView text = findViewById(R.id.turnView);
        reset.animate().alpha(0);
        reset.setClickable(false);
        text.setText("Yellow Turn");
        text.setTextColor(Color.parseColor("#ffffbb33"));
        chip = 1;

        for(int x = 0; x <=8 ; x++){
            gameState[x] = 2;
        }

        for(int i = 0; i<=8 ; i++){
            images[i].setImageDrawable(null);
            images[i].setClickable(true);
        }

        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}