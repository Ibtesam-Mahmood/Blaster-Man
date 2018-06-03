package com.example.user.blasterman;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout levelContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Defines screen layout that contains the level
        levelContainer = findViewById(R.id.levelContainer);

    }

    //Generates an array that represents the level
    /*
    0 - nothing
    1 - block
     */
    private int[][] generateLevel(){

        int[][] level;

        Random r = new Random();
        int width = r.nextInt(10) + 11; //Defines a random width for the level b/w 10-20

        level =  new int[3][width];

        for (int i = 0; i < width; i++){

            level[2][i] = 1; //Sets the base blocks

        }

        return level;

    }

    //Prints the level onto the screen
    /*
    0 - nothing
    1 - red
     */
    private void printLevel(int[][] level){

        for(int i = 0; i < level.length; i++){

            for(int j = 0; j < level[i].length; j++){

                int blockType = level[i][j]; //Obtains the blocktype from the level layout

                DrawSquare block = new DrawSquare(this, 100 + (55*j), 100 + (55*i), 50, 50);

                if(blockType == 1) //Brick
                    block.setColor(Color.RED);
                else //Goes to the next loop if the block isn't defined
                    continue;

                levelContainer.addView(block); //Adds the block to the screen

            }
        }
    }

    public void generateButton(View view){

        printLevel( generateLevel() ); //Prints the generated level

    }




}
