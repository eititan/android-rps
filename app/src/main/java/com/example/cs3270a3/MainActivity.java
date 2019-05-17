package com.example.cs3270a3;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements GameFragment.onButtonListener, ScoreFragment.onButtonListener {

    private ScoreFragment fragScore;
    private GameFragment fragGame;
    FragmentManager fragMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fragGameButtonsPressed(int games, int won, int lost, int tie){
        if(fragScore == null){
            fragMan = getSupportFragmentManager();
            fragScore = (ScoreFragment) fragMan.findFragmentById(R.id.fragmentScoreTv);
        }

        fragScore.updateGameResults(games, won, lost, tie);
    }

    public void resetAllCounts() {
        if(fragGame == null){
            fragMan = getSupportFragmentManager();
            fragGame = (GameFragment) fragMan.findFragmentById(R.id.fragmentGameTv);
        }

        fragGame.resetAllCounts();
    }
}
