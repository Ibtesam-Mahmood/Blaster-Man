package com.example.user.blasterman;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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


    private void printLevel(){

    }



}
