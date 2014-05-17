package com.redstar.simongame.simon;

import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.redstar.simongame.R;

/**
 * SimonView - The graphic front end of Simon. This is the graphics, animations and sounds the player sees and hears
 *
 * @author Eoin Fogarty
 */

public class SimonView extends ImageView implements Animation.AnimationListener {

    private Context context;
    private MediaPlayer mp;

    public SimonView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public SimonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public SimonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.setImageDrawable(getResources().getDrawable(R.drawable.normal));
        this.setScaleType(ScaleType.FIT_CENTER);
        this.context = context;
    }

    /**
     * called when progress is made in a level
     */
    public void playShakeAnimation() {
        playSound(R.raw.progress);
        startAnimationWithListener(R.anim.shake);
    }

    /**
     * called when a level is successfully completed
     */
    public void playSuccessAnimation() {
        playSound(R.raw.success);
        this.setImageDrawable(getResources().getDrawable(R.drawable.success));
        Animation upDown = AnimationUtils.loadAnimation(context, R.anim.up_down);
        this.startAnimation(upDown);
    }

    /**
     * Add a listener to the animation to change the view drawable back to normal when the animation is finished
     *
     *  @param animationResource - shake, tap, rotate, flip etc
     */
    private void startAnimationWithListener(int animationResource) {
        Animation animation = AnimationUtils.loadAnimation(context, animationResource);
        animation.setAnimationListener(this);
        this.startAnimation(animation);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * change image drawable to a shaking image
     */
    @Override
    public void onAnimationStart(Animation animation) {
        //TODO stop listening for actions while the animation is playing
        this.setImageDrawable(getResources().getDrawable(R.drawable.shake));
    }
    /**
     * change image drawable back to normal
     */
    @Override
    public void onAnimationEnd(Animation animation) {
        //TODO start listening for animations after the animation is finished
        this.setImageDrawable(getResources().getDrawable(R.drawable.normal));
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void playSound(int sound) {
        mp = MediaPlayer.create(context, sound);

        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }
}
