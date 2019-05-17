package com.example.cs3270a3;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    private Random r = new Random();
    private View root;

    private Button btnRock, btnPaper, btnScissors;
    private String[] rpsArray = {"Rock", "Paper", "Scissors"};
    private TextView tvCompPick, tvWinner;
    private int totalGames = 0, gamesWon = 0, gamesLost = 0, gamesTied = 0;

    private onButtonListener mCallback;
    //interface declared fragment
    public interface onButtonListener{
        void fragGameButtonsPressed(int games, int won, int lost, int tie);
    }

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_game, container, false);
    }

    /*
     * deprecated class that try's to force MainActivity to use our mCallback
     * to use our onButtonListener
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (onButtonListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement onButtonListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        tvCompPick = (TextView) root.findViewById(R.id.tvComputerPick);
        tvWinner = (TextView) root.findViewById(R.id.tvWhoWon);


        btnRock = (Button)root.findViewById(R.id.btnRock);
        btnPaper = (Button)root.findViewById(R.id.btnPaper);
        btnScissors= (Button)root.findViewById(R.id.btnScissors);


        Drawable d = btnPaper.getBackground();

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRock.setBackgroundResource(R.color.colorBlue);
                btnPaper.setBackgroundResource(android.R.drawable.btn_default);
                btnScissors.setBackgroundResource(android.R.drawable.btn_default);

                computerHand(0);
            }
        });

        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRock.setBackgroundResource(android.R.drawable.btn_default);
                btnPaper.setBackgroundResource(R.color.colorBlue);
                btnScissors.setBackgroundResource(android.R.drawable.btn_default);

                computerHand(1);
            }
        });

        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRock.setBackgroundResource(android.R.drawable.btn_default);
                btnPaper.setBackgroundResource(android.R.drawable.btn_default);
                btnScissors.setBackgroundResource(R.color.colorBlue);

                computerHand(2);
            }
        });


        //TODO listeners for all 3 buttons and a reset button somewhere
    }

    private void computerHand(int choiceRPS) {
        //0 -> Rock, 1-> Paper, 2 -> Scissors
        totalGames++;

        //generates a number 0-2
        int randRPS = r.nextInt(3);
        tvCompPick.setText(rpsArray[randRPS]);

        if(choiceRPS == randRPS){
            //tied
            gamesTied++;
            tvWinner.setText("You Tied!");
        }else if(choiceRPS == 0 && randRPS == 2){
            //rock beat scissors
            gamesWon++;
            tvWinner.setText("You Win!");

        }else if(choiceRPS == 1 && randRPS == 0){
            //paper beat rock
            gamesWon++;
            tvWinner.setText("You Win!");
        }else if(choiceRPS == 2 && randRPS == 1){
            //scissors beat paper
            gamesWon++;
            tvWinner.setText("You Win!");
        }else{
            //the computer won
            gamesLost++;
            tvWinner.setText("Phone Wins!");
        }

        MainActivity activity = (MainActivity)getActivity();
        activity.fragGameButtonsPressed(totalGames, gamesWon, gamesLost, gamesTied);

    }

    public void resetAllCounts(){
        totalGames = 0;
        gamesWon = 0;
        gamesLost = 0;
        gamesTied = 0;
    }

}
