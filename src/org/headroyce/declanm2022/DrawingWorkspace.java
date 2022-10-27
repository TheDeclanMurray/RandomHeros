package org.headroyce.declanm2022;

import Gunners.AK47;
import Heros.Punk;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


/**
 * The entire workspace of the application.
 *
 * @author Brian Sea
 */
public class DrawingWorkspace extends Pane {

    public static final int GAME_STEP_TIMER = 17;
    private DrawingWorkspace.Animation anim;

    private DrawingArea drawingArea;
    private Canvas mainCanvas;
    private Point mousePoint;
    public Hero currHero;
    public Gunner currGun;
    public Pane GunnerPane;
    private boolean isPaused;

    public DrawingWorkspace(){


        drawingArea = new DrawingArea(this);
        mousePoint = new Point(0,0);
        initiateButtons();
        HandleKeys();
        initiateCanvas();

        GunnerPane = new Pane();
        drawingArea.getChildren().add(GunnerPane);

        anim = new Animation();
        this.onResume();




    }

    public void onPause(){
        anim.stop();
    }
    public void onResume(){
        System.out.println("Game Resumed");
        anim.start();
    }

    private class Animation extends AnimationTimer {

        private long lastUpdate = 0;
        private long timeElapsed;
        private boolean FirstRun = true;

        @Override
        public void handle(long now) {
            timeElapsed = (now - lastUpdate)/100000;

            if(timeElapsed > GAME_STEP_TIMER){

                if(FirstRun){

                    currHero = new Punk(mainCanvas);
                    currHero.x = mainCanvas.getWidth()/2;
                    currHero.y = mainCanvas.getHeight()/2;

                    currGun = new AK47(GunnerPane);
                    currHero.gun = currGun;

                    FirstRun = false;
                }
                if(!isPaused){
                    renderWorld();
                }
                lastUpdate = now;
            }
        }
    }

    private void initiateCanvas(){
        mainCanvas = new Canvas();

        // Force the canvas to resize to the screen's size

        mainCanvas.widthProperty().bind(this.widthProperty());
        mainCanvas.heightProperty().bind(this.heightProperty());

        // Attach mouse handlers to the canvas
        EventHandler<MouseEvent> handler = new DrawingWorkspace.MouseHandler();
        mainCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, handler);
        mainCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, handler);
        mainCanvas.addEventHandler(MouseEvent.MOUSE_MOVED, handler);
        mainCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);

        drawingArea.getChildren().add(mainCanvas);
    }

    /**
     * Helps to handle all of the mouse events on the canvas
     */
    private class MouseHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event){

            Point p = new Point(event.getX(), event.getY());

            if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){

            }
            else if( event.getEventType().equals(MouseEvent.MOUSE_RELEASED)){

            }
            else if( event.getEventType().equals(MouseEvent.MOUSE_MOVED)){
                mousePoint.x = event.getX();
                mousePoint.y = event.getY();
            }
            else if( event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)){

            }
        }
    }


    private void initiateButtons(){
        Button pauseButton = new Button();
        pauseButton.setTooltip(new Tooltip("Pause"));
        pauseButton.setText("||");
        pauseButton.setMinWidth(60);
        pauseButton.setMinHeight(60);
        pauseButton.setLayoutX(1000-60);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(pauseButton.getText() + " Pressed");
                if(pauseButton.getText().equals("|>")){
                    pauseButton.setText("||");
                    onResume();
                }else if(pauseButton.getText().equals("||")){
                    pauseButton.setText("|>");
                    onPause();
                }
            }
        });

        Button openShop = new Button();
        openShop.setTooltip(new Tooltip("Shop"));
        openShop.setText("[0]");
        openShop.setMinWidth(60);
        openShop.setMinHeight(60);
        openShop.setLayoutX(1000-125);
        openShop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Shop Button Pressed");
            }
        });

        drawingArea.prefHeightProperty().bind(this.heightProperty());
        drawingArea.prefWidthProperty().bind(this.widthProperty());
        this.getChildren().addAll(drawingArea);
        this.getChildren().add(pauseButton);
        this.getChildren().add(openShop);
    }


    private void HandleKeys(){
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode() ){
                    case D:
                        currHero.Right = true;
                        break;
                    case A:
                        currHero.Left = true;
                        break;
                    case W:
                        currHero.Jump = true;
                        break;
                }
            }
        });

        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode() ){
                    case D:
                        currHero.Right = false;
                        break;
                    case A:
                        currHero.Left = false;
                        break;
                    case W:
                        currHero.Jump = false;
                        break;
                }
            }
        });
    }

    /**
     * Render the viewable canvas
     */
    public void renderWorld(){
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        gc.clearRect(0,0, mainCanvas.getWidth(), mainCanvas.getHeight());
        currHero.move();
        drawingArea.getChildren().remove(GunnerPane);
        currGun.mousePoint = mousePoint;
        currHero.setGunPoint();
        GunnerPane = currGun.render();
        drawingArea.getChildren().add(GunnerPane);
        currHero.move();


    }

}
