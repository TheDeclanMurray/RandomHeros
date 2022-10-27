package Gunners;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import org.headroyce.declanm2022.Gunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AK47 extends Gunner {

    public AK47(Pane GK){
        super(GK);


    }

    public Pane render(){
        try{
            FileInputStream input = new FileInputStream("assets/gun-AK47.png");
            Image image = new Image(input);

            javafx.scene.transform.Translate translate = new Translate();
            Rotate rt = new Rotate();

            GunnerPane = new Pane();

            //TODO: Position this

            if (mousePoint.x > 205) {   //mousePoint.x > 205

            }

            if(mousePoint.x < 205){

            }

            angle-=1;
            rt.setAngle(angle);
            rt.setPivotX(x);
            rt.setPivotY(y);

            translate.setX(x);
            translate.setY(y);


            GunnerPane.getTransforms().addAll(rt);
            GunnerPane.getTransforms().addAll(translate);
            GunnerPane.getChildren().addAll(new ImageView(image));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return GunnerPane;
    }

    public void render2(){

    }

}
