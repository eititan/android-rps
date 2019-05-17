package com.example.cs3270a3;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {

    private View root;
    private TextView totalGamesTv, gamesWonTv, gamesLostTv, gamesTiedTv;
    private Button btnReset;

    private onButtonListener mCallback;
    //interface declared fragment
    public interface onButtonListener{
        void resetAllCounts();
    }

    public ScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_score, container, false);
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

        //instantiate all textviews
        totalGamesTv = (TextView) root.findViewById(R.id.tvNumOfGamesPlayed);
        gamesWonTv = (TextView) root.findViewById(R.id.tvNumOfGamesWon);
        gamesLostTv = (TextView) root.findViewById(R.id.tvNumOfGamesLost);
        gamesTiedTv = (TextView) root.findViewById(R.id.tvNumOfGamesTied);

        btnReset =  root.findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(), getString(R.string.reset_counts), Toast.LENGTH_SHORT);
                toast.show();

                //actually called out main activity class we have set up to reset all counts and then update UI
                MainActivity activity = (MainActivity)getActivity();
                activity.resetAllCounts();
                updateGameResults(0,0,0,0);
            }
        });
    }

    public void updateGameResults(int gamesPlayed, int gamesWon, int gamesLost, int ties){
        totalGamesTv.setText(""+gamesPlayed);
        gamesWonTv.setText("" + gamesWon);
        gamesTiedTv.setText("" + gamesLost);
        gamesLostTv.setText("" + ties);
    }
}
