package org.headroyce.declanm2022;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public abstract class Gunner extends Pane {

    protected Pane GunnerPane;
    public double x,y,width,height;
    public Boolean LookingRight;
    public Point mousePoint;
    protected double angle;

    public Gunner(Pane GP){
        GunnerPane = GP;


    }

    public Pane render(){

        return GunnerPane;
    }
}
