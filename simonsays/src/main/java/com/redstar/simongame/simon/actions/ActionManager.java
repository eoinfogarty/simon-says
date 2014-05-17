package com.redstar.simongame.simon.actions;

import android.content.Context;
import android.hardware.SensorManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Listens for actions performed by the player and sends the result to simon.
 * The class also manages the SensorManager turning it on and off
 *
 * <p/>
 * Actions are represented by integers eg ACTION_TAP = 1
 * When an action is heard a message with the integer are sent to simon
 * Different actions are waited for by detector classes
 * <p/>
 *
 * @author Eoin Fogarty
 */

public class ActionManager implements ShakeDetector.Listener,TapDetector.Listener, RotateDetector.Listener {

    public static final int ACTION_TAP = 1;
    public static final int ACTION_SHAKE = 2;
    // private static final int ACTION_ROTATE = 3;
    //TODO Add more actions ACTION_FLIP, ACTION_SPIN

    private static final List<Integer> ACTION_LIST = Arrays.asList(ACTION_SHAKE,ACTION_TAP/*, ACTION_ROTATE*/);

    private TapDetector tapDetector;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;
    private RotateDetector rotateDetector;
    private Listener Listener;

    /** Listens for actions. */
    public interface Listener {
        /** called when an action is performed. */
        void gotAction(int action);
    }



    public ActionManager(Context context, Listener listener)
    {
        Listener = listener;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        shakeDetector = new ShakeDetector(this);
        tapDetector = new TapDetector(this);
        //rotateDetector = new RotateDetector(this);
    }

    /**
     * since the tap detector is basically an onClickListener it has to be set on a view
     * @return TapDetector - basically OnClickListener
     */
    public TapDetector getTapDetector(){
        return tapDetector;
    }


    public Integer getRandomAction() {
        int randomAction = new Random().nextInt(ACTION_LIST.size());

        return ACTION_LIST.get(randomAction);
    }

    /**
     * call to simon when a shake is heard
     */
    @Override
    public void hearShake() {
       Listener.gotAction(ACTION_SHAKE);
    }
    /**
     * call to simon when a tap is heard
     */
    @Override
    public void hearTap() {
        Listener.gotAction(ACTION_TAP);
    }
    /**
     * call to simon when a rotate is heard
     */
    @Override
    public void hearRotation() {
 //       Listener.gotAction(ACTION_ROTATE);
    }


    public void stop() {
        shakeDetector.stop();
//        rotateDetector.stop();
    }

    public void start() {
        shakeDetector.start(sensorManager);
//        rotateDetector.start(sensorManager);
    }

}
