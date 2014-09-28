/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pdsanchez.aquarium;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * http://blog.netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/
 * 
 * @author pdsanchez
 */
public class SpriteAnimation extends Transition {
    public enum AnimationType {
        ANIMATION_REPEAT,
        ANIMATION_REVERSE, // pingpong
        ANIMATION_PLAY_AND_STOP
    };
    
    private final ImageView imageView;
    private final int nframes;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int nframes,
            int offsetX, int offsetY,
            int width, int height,
            AnimationType type) {
        this.imageView = imageView;
        this.nframes = nframes;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
        setAnimationType(type);
    }
    
    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int nframes,
            int offsetX, int offsetY,
            int width, int height) {
        this(imageView, duration, nframes, offsetX, offsetY, width, height, AnimationType.ANIMATION_REPEAT);
    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(frac * nframes), nframes - 1);
        if (index != lastIndex) {
            final int x = index * width + offsetX;
            imageView.setViewport(new Rectangle2D(x, offsetY, width, height));
            lastIndex = index;
        }
    }
    
    private void setAnimationType(AnimationType type) {
        if (type == AnimationType.ANIMATION_PLAY_AND_STOP) {
            setCycleCount(1);
        }
        else {
            setCycleCount(Animation.INDEFINITE);
            if (type == AnimationType.ANIMATION_REVERSE) {
                setAutoReverse(true);
            }
        }
    }
}