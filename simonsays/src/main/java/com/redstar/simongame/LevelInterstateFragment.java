package com.redstar.simongame;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Waits until the player is ready to start the next level then tell the GameFragment its ready to start the next level when a button is pressed

 * @author Eoin Fogarty
 */
public class LevelInterstateFragment extends Fragment {
    // Listener from the game fragment
    private Listener listener;

    public LevelInterstateFragment() {
        // Required empty public constructor
    }

    public LevelInterstateFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_level_interstate, container, false);

        Button nextLevelBtn = (Button) rootView.findViewById(R.id.button);
        nextLevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.startNextLevel();
                getActivity().getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    /**
     * Communicates with the GameFragment
     */
    public interface Listener {
        /**
         * Tells the GameFragment that the player is ready to start the next level
         */
        void startNextLevel();
    }
}
