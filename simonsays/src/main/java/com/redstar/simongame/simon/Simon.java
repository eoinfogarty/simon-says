package com.redstar.simongame.simon;

import android.content.Context;
import android.util.AttributeSet;

import com.redstar.simongame.simon.actions.ActionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the logic of the game. It keeps track of the actions needed to be performed and handles what to do when an action is performed
 * <p/>
 * It creates a list of actions needed to be completed - List<Integer> actionsNeeded
 * It gets notified of actions from ActionManager.Listener in the gotAction() method.
 * It handles the action and calls the appropriate method
 * <p/>
 *
 * @author Eoin Fogarty
 */

//TODO Change simon extending SimonView
public class Simon extends SimonView implements ActionManager.Listener {

    //private Context context;
    private Listener listener = null;

    private ActionManager actionManager;
    private List<Integer> actionsNeeded;

    private int level = 0;
    private int progress = 0;



    /**
     * Listens for progress and level completes.
     */
    public interface Listener {
        void progress(int progress);

        void levelFinished();
    }

    public Simon(Context context) {
        super(context);
        init(context);
    }

    public Simon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Simon(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        actionManager = new ActionManager(context, this);
        actionsNeeded = new ArrayList<Integer>();
    }

    /**
     * Listener can be added later, this leaves the possibility that simon an be used in another class/fragment without a listener
     * //TODO but the parent wont know when a level is finished and the game cant continue, isFinished() method?
     *
     * @param listener - the parent of simon can add a listener to hear updates
     */
    public void addListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Called from the parent
     */
    public void startNewLevel() {
        progress = 0;
        level++;
        actionsNeeded.add(actionManager.getRandomAction());
        //TODO play sound clips of the actions needed
        this.start(); // start animation

    }

    /**
     * Generic method call sent by the ActionManager
     * When an action is received actionManager is stopped and the action is handled
     *
     * @param action -  eg shake, touch
     */
    public void gotAction(int action) {
        int actionNeeded = actionsNeeded.get(progress);
        if (action == actionNeeded) {
            updateProgress();
        } else {
            // TODO consequences of wrong action
        }

        if (progress == level) {
            completeLevel();
        }
    }

    /**
     * If an action performed was the same as what was needed.
     * If simon has a listener then a call with the current progress is sent out.
     */
    private void updateProgress() {
        this.playShakeAnimation();
        progress++;

        if (listener != null) {
            listener.progress(progress);
        }
    }

    /**
     * If the player has enough progress to finish a level.
     * <p/>
     * If simon has a listener then a call to finish the level is sent out.
     */
    private void completeLevel() {
        this.stop();
        this.playSuccessAnimation();

        if (listener != null) {
            listener.levelFinished();
        }
    }

    public int getLevel() {
        return level;
    }

    //TESTING
    public String getNextAction(int progress) {
        int actionNeeded = 0;
        if(progress<actionsNeeded.size()) {
            actionNeeded = actionsNeeded.get(progress);
        }
        String action;
        switch (actionNeeded) {
            case ActionManager.ACTION_SHAKE:
                action =  "Shake";
                break;
            case ActionManager.ACTION_TAP:
                action =  "Tap";
                break;
            default: action = "finish";
        }
        return action;
    }

    /**
     * start simon listening for actions
     */
    public void start() {
        setOnClickListener(actionManager.getTapDetector());
        actionManager.start();
    }

    /**
     * stop simon listening for actions
     */
    public void stop() {
        setOnClickListener(null);
        actionManager.stop();
    }
}
