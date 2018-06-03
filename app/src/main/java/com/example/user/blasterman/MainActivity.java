package com.example.user.blasterman;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    2 - key
    3 - enemy
    4 - blaster man
    5 - door
     */
    private int[][] generateLevel(){

        int[][] level;

        Random r = new Random();
        int width = r.nextInt(15) + 11; //Defines a random width for the level b/w 15-25

        level =  new int[3][width];


        level[2][0] = 1; // Ensures that there is a first block
        level[2][width-1] = 1; //Ensures there is a last block
        level[1][0] = 4; //Generates blaster man
        level[1][width-1] = 5; //Generates door

        for (int i = 1; i < width - 1; i++){

            // Sets the base blocks and holes
            double randomizer = Math.random();

            if(level[2][i-1] != 0 && randomizer < 0.25) //Create a hole 25% of them time ensuring there are no double holes
                level[2][i] = 0;
            else //Create a block 75% of the time
                level[2][i] = 1;

            // Sets the keys
            randomizer = Math.random();

            if(randomizer < 0.15 && level[0][i-1] != 2) //Generates keys 15% of the time ensures no double keys
                level[0][i] = 2;

        }

        return level;

    }

    //Prints the level onto the screen
    /*
    0 - nothing
    1 - red - block
    2 - yellow - key
    3 - green - enemy
    4 - cyan - blaster man
    5 - magenta - door
     */
    private void printLevel(int[][] level){

        for(int i = 0; i < level.length; i++){

            for(int j = 0; j < level[i].length; j++){

                int blockType = level[i][j]; //Obtains the blocktype from the level layout

                DrawSquare block = new DrawSquare(this, 100 + (55*j), 100 + (55*i), 50, 50);

                if(blockType == 1) //Brick
                    block.setColor(Color.RED);
                else if(blockType == 2)
                    block.setColor(Color.YELLOW);
                else if(blockType == 3)
                    block.setColor(Color.GREEN);
                else if(blockType == 4)
                    block.setColor(Color.CYAN);
                else if(blockType == 5)
                    block.setColor(Color.MAGENTA);
                else //Goes to the next loop if the block isn't defined
                    continue;

                levelContainer.addView(block); //Adds the block to the screen

            }
        }
    }

    public void generateButton(View view){

        levelContainer.removeAllViews();
        printLevel( generateLevel() ); //Prints the generated level

    }




}
