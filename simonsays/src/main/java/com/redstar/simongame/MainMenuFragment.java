package com.redstar.simongame;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * MainMenuFragment - This class presents the menu to the user. The user has 3 choices. Start, Tutorial and Settings.
 * <p/>
 * Nothing special 3 buttons, 3 Fragment transactions
 * <p/>
 * Start - begin the game
 * Tutorial - learn to play the game
 * Settings - game settings
 *
 * @author Eoin Fogarty
 */

public class MainMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);

        Button startBtn = (Button) rootView.findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new GameFragment())
                        .addToBackStack("MENU")  //for back button
                        .commit();
            }
        });

        Button tutorialBtn = (Button) rootView.findViewById(R.id.tutorialBtn);
        tutorialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        Button settingsBtn = (Button) rootView.findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
