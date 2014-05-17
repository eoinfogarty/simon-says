package com.redstar.simongame.simon.actions;

import android.view.View;

/**
 * Basically an OnClickListener. It uses the interface hearTap() so
 * it can use the ActionManager, the same as the other detectors.
 *
 * @author Eoin Fogarty
 */
public class TapDetector implements View.OnClickListener{

    /** Listens for taps. */
    public interface Listener {
        /** Called on the main thread when the device is tapped. */
        void hearTap();
    }

    private final Listener listener;


    public TapDetector(Listener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
       listener.hearTap();
    }

}
