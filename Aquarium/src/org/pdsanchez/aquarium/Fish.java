/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pdsanchez.aquarium;

import java.awt.Point;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author pdsanchez
 */
public class Fish {

    Sprite sp;
    Point location;
    Point velocity;
//    Rectangle edges;
    Random random;

    public Fish() {
        random = new Random(System.currentTimeMillis());
        sp = new Sprite(new Image(getClass().getResourceAsStream("fish2.jpg")));
//        this.image2 = image2;
//        this.edges = edges;
        this.location = new Point(100
                + (Math.abs(random.nextInt()) % 400),
                100 + (Math.abs(100 + random.nextInt()) % 400));

        this.velocity = new Point(random.nextInt() % 8, random.nextInt() % 8);
    }

    public void swim() {
        if (random.nextInt() % 7 <= 1) {

            velocity.x += random.nextInt() % 4;

            velocity.x = Math.min(velocity.x, 8);
            velocity.x = Math.max(velocity.x, -8);

            velocity.y += random.nextInt() % 4;

            velocity.y = Math.min(velocity.y, 8);
            velocity.y = Math.max(velocity.y, -8);
        }
        location.x += velocity.x;
        location.y += velocity.y;
//        if (location.x < edges.x) {
//            location.x = edges.x;
//            velocity.x = -velocity.x;
//        }
//
//        if ((location.x + image1.getWidth(tank))
//                > (edges.x + edges.width)) {
//            location.x = edges.x + edges.width - image1.getWidth(tank);
//            velocity.x = -velocity.x;
//        }
//
//        if (location.y < edges.y) {
//            location.y = edges.y;
//            velocity.y = -velocity.y;
//        }
//
//        if ((location.y + image1.getHeight(tank))
//                > (edges.y + edges.height)) {
//            location.y = edges.y + edges.height - image1.getHeight(tank);
//            velocity.y = -velocity.y;
//        }

    }

    public void drawFishImage() {
//        if (velocity.x < 0) {
            
            sp.setFitWidth(100);
         sp.setPreserveRatio(true);
         sp.setSmooth(true);
         sp.setCache(true);
         
         sp.setX(location.x);
            sp.setY(location.y);
//        } else {
//            iv1.setImage(image1);
//        }
    }

}
