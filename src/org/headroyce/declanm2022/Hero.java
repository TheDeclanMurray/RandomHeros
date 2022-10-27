package org.headroyce.declanm2022;

import javafx.scene.canvas.Canvas;

public abstract class Hero {

    protected Canvas view;
    public double x,y,width,height,velY,gravity;
    public Boolean Right,Left,Jump,LookingRight;
    public Point holsterR,holsterL,mousePoint;
    public Gunner gun;


    public Hero(Canvas canvas){

        view = canvas;
    }

    public void move(){
        System.out.println("Hero Move() not implemented");
    }

    public void setGunPoint(){

        //Render Gun
        Point holserLocation = null;
        if(LookingRight) {
            holserLocation = new Point(x + holsterR.x, y + holsterR.y);
        }else{
            holserLocation = new Point(x+holsterL.x,y+holsterL.y);
        }
        gun.x = holserLocation.x;
        gun.y = holserLocation.y;
        gun.LookingRight = LookingRight;


    }

}
