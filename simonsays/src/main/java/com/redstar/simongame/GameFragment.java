package com.redstar.simongame;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.redstar.simongame.simon.Simon;

/**
 * the UI the user sees when they are playing the game. It holds the Simon character and the progress of the game so far.
 *
 * It listens for progress updates and updates ui.
 * It listens for finished levels then stops Simon listening and creates the LevelInterstateFragment
 * It waits for the LevelInterstateFragment to tell it the user is ready to continue
 *
 * @author Eoin Fogarty
 */

public class GameFragment extends Fragment implements Simon.Listener, LevelInterstateFragment.Listener{

    private ProgressBar progressBar;
    private TextView levelTv;
    private Simon simon;

    //for testing
    private TextView testTv;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO target KItKat and set immersive mode
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_game, container, false);

        simon = (Simon) rootView.findViewById(R.id.simon);
        simon.addListener(this);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.setMax(0);

        levelTv = (TextView) rootView.findViewById(R.id.levelTv);

        //for testing
        testTv  = (TextView) rootView.findViewById(R.id.testTv);

        startNextLevel();

        return rootView;
    }
    /**
     * called from LevelInterstateFragment when a player is ready to start the next level
     */
    @Override
    public void startNextLevel() {
        simon.startNewLevel();
        progressBar.setProgress(0);
        progressBar.setMax(simon.getLevel());
        levelTv.setText(getResources().getString(R.string.level) + " " + simon.getLevel());
        //for testing
        testTv.setText("Next Action : " + simon.getNextAction(0));
    }

    /**
     * called from Simon when the correct action is heard
     *
     * @param progress - is how far the user is through the level
     */
    @Override
    public void progress(int progress) {
        progressBar.setProgress(progress);
        //for testing
        testTv.setText("Next Action : " + simon.getNextAction(progress));
    }

    /**
     * called from Simon when a level is finished
     */
    @Override
    public void levelFinished() {
        progressBar.setProgress(progressBar.getMax());
        simon.stop();
        showLevelInterstate();
    }

    private void showLevelInterstate() {
        getActivity()
                .getFragmentManager()
                .beginTransaction()
                .add(R.id.container,new LevelInterstateFragment(this))
                .addToBackStack("INTERSTATE")
                .commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        simon.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        simon.start();
    }
}
