package com.zhjy.cultural.services.patrol.ui.home.main;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public abstract class AnimationDrawableCallback implements Drawable.Callback{

    /**
     * The last frame of {@link Drawable} in the {@link AnimationDrawable}.
     */
    private Drawable mLastFrame;

    /**
     * The client's {@link Drawable.Callback} implementation. All calls are proxied to this wrapped {@link .Drawable.Callback}
     * implementation after intercepting the events we need.
     */
    private Drawable.Callback mWrappedCallback;

    /**
     * Flag to ensure that {@link #onAnimationComplete()} is called only once, since
     * {@link #invalidateDrawable(Drawable)} may be called multiple times.
     */
    private boolean mIsCallbackTriggered = false;

    /**
     *
     * @param animationDrawable
     *            the {@link AnimationDrawable}.
     * @param callback
     *            the client's {@link Drawable.Callback} implementation. This is usually the {@link View} the has the
     *            {@link AnimationDrawable} as background.
     */
    public AnimationDrawableCallback(AnimationDrawable animationDrawable, Drawable.Callback callback) {
        mLastFrame = animationDrawable.getFrame(animationDrawable.getNumberOfFrames() - 1);
        mWrappedCallback = callback;
    }

    @Override
    public void invalidateDrawable(Drawable who) {
        if (mWrappedCallback != null) {
            mWrappedCallback.invalidateDrawable(who);
        }

        if (!mIsCallbackTriggered && mLastFrame != null && mLastFrame.equals(who.getCurrent())) {
            mIsCallbackTriggered = true;
            onAnimationComplete();
        }
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        if (mWrappedCallback != null) {
            mWrappedCallback.scheduleDrawable(who, what, when);
        }
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
        if (mWrappedCallback != null) {
            mWrappedCallback.unscheduleDrawable(who, what);
        }
    }

    //
    // Public methods.
    //

    /**
     * Callback triggered when {@link View#invalidateDrawable(Drawable)} has been called on the last frame, which marks
     * the end of a non-looping animation sequence.
     */
    public abstract void onAnimationComplete();

}
