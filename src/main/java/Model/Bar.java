package Model;

import ViewGraphic.Animation.MovingBarAnimation;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Bar extends Rectangle {
    final double width=15;
    final double height=265;
    private MovingBarAnimation movingBarAnimation;
    public Bar(){
        this.setHeight(height);
        this.setWidth(width);
        this.setFill(Paint.valueOf("#03fcf8"));
    }
    public void setMovingBarAnimation(MovingBarAnimation movingBarAnimation){
        this.movingBarAnimation=movingBarAnimation;
    }
    public MovingBarAnimation getMovingBarAnimation(){return this.movingBarAnimation;}

}
