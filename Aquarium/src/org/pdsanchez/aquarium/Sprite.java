/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pdsanchez.aquarium;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

/**
 *
 * @author pdsanchez
 */
public class Sprite extends ImageView {

    private Rectangle2D initialViewPort;

    private int x;
    private int y;

    private Animation currentAnimation;

    private TranslateTransition translation;

    private final Map<String, Animation> animations;

    public Sprite(Image img, Rectangle2D initialViewPort) {
        super(img);
        this.initialViewPort = initialViewPort;
        setViewport(initialViewPort);
        this.animations = new HashMap<>();
    }
    
    public Sprite(Image img) {
        this(img, new Rectangle2D(0, 0, img.getWidth(), img.getHeight()));
    }

    public void addAnimation(String name, SpriteAnimation animation) {
        animations.put(name, animation);
    }

    public void runAnimation(String name) {
        if (currentAnimation != null) {
            currentAnimation.stop();
        }
        currentAnimation = animations.get(name);
        currentAnimation.play();
    }

    public void removeAnimation() {
        if (currentAnimation != null) {
            currentAnimation.stop();
        }
        currentAnimation = null;
        setViewport(initialViewPort);
    }

    public void translation(double targetX, double targetY) {
        // If the character was already moving, stop it.
        if (translation != null) {
            translation.stop();
        }
        
        getTransforms().add(new Scale(-1, 1, initialViewPort.getWidth(), initialViewPort.getMinY()));

        // Calculate the distance to the target.
        double distanceX = targetX - getTranslateX();
        double distanceY = targetY - getTranslateY();
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        // Create the translation animation.
        // This animation will translate the node from its current location to the target location.
        // The distance to the target and the speed of the character are used to calculate the length of the animation.
        // When the animation finishes, the sprite animation is also stopped.
        translation = new TranslateTransition();
        translation.setNode(this);
        translation.setFromX(getTranslateX());
        translation.setFromY(getTranslateY());
        translation.setToX(targetX);
        translation.setToY(targetY);
        translation.setDuration(Duration.seconds(distance / 120.0));
        translation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                removeAnimation();
            }
        });
        translation.play();
    }
}
