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

    private int[][] level;

    private int characterIndex;
    private int keys;
    private int size;


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
    5 - door closed
    6 - door open
     */
    private int[][] generateLevel(){

        int[][] level;

        Random r = new Random();
        size = r.nextInt(15) + 21; //Defines a random width for the level b/w 15-35

        level =  new int[3][size];

        //Level base values
        characterIndex = 0;
        keys = 0;

        level[2][characterIndex] = 1; // Ensures that there is a first block
        level[2][size-1] = 1; //Ensures there is a last block
        level[1][0] = 4; //Generates blaster man
        level[1][size-1] = 5; //Generates door

        //Pre level generation block setting
        for (int i = 1; i < size - 1; i++){

            // Sets the base blocks and holes
            double randomizer = Math.random();

            if(level[2][i-1] != 0 && randomizer < 0.25) //Create a hole 25% of them time ensuring there are no double holes
                level[2][i] = 0;
            else //Create a block 75% of the time
                level[2][i] = 1;

            // Sets the keys
            randomizer = Math.random();

            if(randomizer < 0.15 && level[0][i-1] != 2) { //Generates keys 15% of the time ensures no double keys
                level[0][i] = 2;
                keys++;
            }

        }

        //Post level generation block setting
        for(int i = 1; i < size - 1; i++){

            //Sets enemies
            double randomizer =  Math.random();
            boolean jumpAbility = level[1][i-1] != 3 && level[2][i-1] != 0 && level[2][i+1] != 0; //Ensures the player can jump over

            //Generates enemies 10% of the time ensures no double keys along with enemies over holes
            //Ensuring the player can jump over the enemy
            if(randomizer < 0.3 && level[2][i] != 0 && jumpAbility)
                level[1][i] = 3;

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
    5 - magenta - door closed
    6 - white - door open
     */
    private void printLevel(int[][] level){

        for(int i = 0; i < level.length; i++){

            for(int j = 0; j < level[i].length; j++){

                int blockType = level[i][j]; //Obtains the blocktype from the level layout

                DrawSquare block = new DrawSquare(this, 100 + (33*j), 100 + (33*i), 30, 30);

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
                else if(blockType == 6)
                    block.setColor(Color.WHITE);
                else //Goes to the next loop if the block isn't defined
                    continue;

                levelContainer.addView(block); //Adds the block to the screen

            }
        }
    }

    //Clears the level and prints a new one based off the level variable
    private void updateLevel(){
        levelContainer.removeAllViews();

        //sets the door to open if all keys are collected
        if(keys == 0)
            level[1][size-1] = 6;

        printLevel(level); //Prints the generated level
    }

    //onClicks

    //Generates a level and prints it
    public void generateButton(View view){
        level = generateLevel();
        updateLevel();
    }

    //Moves the player 1 block over
    public void move(View view){
        if(level == null) //Ensures that the level is generated
            return;

        level[1][characterIndex] = 0;
        characterIndex++;
        level[1][characterIndex] = 4;
        updateLevel();
    }

    //Grabs a key if the player is under one
    public void jump(View view){
        if(level == null) //Ensures that the level is generated
            return;

        if(level[0][characterIndex] == 2){
            level[0][characterIndex] = 0;
            keys--;
        }
        updateLevel();
    }





}
