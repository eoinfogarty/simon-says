package com.redstar.simongame;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;

/**
 * MainActivity - Just a shell to hold the MainMenuFragment and GameFragment
 *
 * This game is based on the popular electronic toy Simon
 * The game has different actions (any number is possible)
 * A round in the game consists of the game telling you an action in a random order, after which the player must reproduce that order by doing the action.
 * As the game progresses, the number of actions to do increases.
 *
 * @author Eoin Fogarty
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);


        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MainMenuFragment())
                    .commit();
        }
    }
}
