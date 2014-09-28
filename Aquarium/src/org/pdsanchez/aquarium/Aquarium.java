/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pdsanchez.aquarium;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author pdsanchez
 */
public class Aquarium extends Application {
    Fish fish;
    
    
    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        root.getStylesheets().add(this.getClass().getResource("aquarium.css").toExt‌​ernalForm());
        
        fish = new Fish();
        fish.drawFishImage();
        
        Fish fish2 = new Fish();
        fish2.drawFishImage();
        
        Pane p = new Pane();

        p.getChildren().add(fish.sp);
         p.getChildren().add(fish2.sp);
        root.getChildren().add(p);
        
        Scene scene = new Scene(root, 900, 650);
        stage.setTitle("Aquarium");
        stage.setScene(scene);
        stage.show();
    }

    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
