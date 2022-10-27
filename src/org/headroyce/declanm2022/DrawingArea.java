package org.headroyce.declanm2022;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The main drawing canvas for our application
 *
 * @author Brian Sea
 */
public class DrawingArea extends StackPane {

    private DrawingWorkspace mainWorkspace;
    public boolean isPaused;

    public DrawingArea(DrawingWorkspace mw){
        isPaused = false;
        mainWorkspace = mw;
    }


}