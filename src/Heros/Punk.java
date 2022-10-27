package Heros;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.headroyce.declanm2022.Hero;
import org.headroyce.declanm2022.Point;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Punk extends Hero {

    public Boolean doubleJump;

    public Punk(Canvas canvas) {
        super(canvas);
        velY = 0;
        gravity = 0.2;
        x = view.getWidth()/2-width/2;
        y = view.getHeight()/2-height/2;
        Right = false;
        Left = false;
        Jump = false;
        LookingRight = true;
        mousePoint = new Point(0,0);


        doubleJump = false;
        width = 30;
        height = 46;
        holsterR = new Point(12,16);
        holsterL = new Point(18,16);

    }

    public void move(){
        velY += gravity;
        y += velY;
        if(y+height > view.getHeight()){
            y = view.getHeight()-height;
            velY = 0;
        }

        if(Right && Left){
            //not move or change orientation
        }else if(Right){
            x += 4;
            if(x+width > view.getWidth()){
                x = view.getWidth()-width;
            }
            LookingRight = true;
        }else if(Left){
            x -= 4;
            if(x < 0){
                x = 0;
            }
            LookingRight = false;
        }

        if(Jump && velY == 0){
            velY -= 7;
            Jump = false;
            doubleJump = true;
        }else if(doubleJump && Jump){
            if(velY > -3){
                velY -= 6;
            }else{
                velY -= 2.5;
            }
            doubleJump = false;
        }

        //Move Gun




        this.render();
    }

    private void render(){
        GraphicsContext gc = view.getGraphicsContext2D();
        FileInputStream input;
        Image image;

        //Draw Character
        try{
            input = new FileInputStream("assets/skin-punk.png");
            image = new Image(input);

            if(LookingRight){
                gc.drawImage(image,x,y);

            }else{
                gc.drawImage(image,x+width,y,-width,height);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}