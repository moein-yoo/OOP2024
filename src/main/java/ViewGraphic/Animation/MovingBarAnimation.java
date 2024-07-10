package ViewGraphic.Animation;

import Controller.GameController2;
import Model.*;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.sql.Timestamp;

public class MovingBarAnimation extends Transition {
    Pane pane;
    Game game;
    Bar bar;
    boolean alreadyhit[];
    public MovingBarAnimation(Pane pane,Bar bar,Game game){
        this.game=game;
        this.pane=pane;
        this.bar=bar;
        alreadyhit=new boolean[21];
        for(int i=0;i<21;i++){
            alreadyhit[i]=false;
        }
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v){
        System.out.println("entered interpolate func");
        double a=bar.getLayoutX();
        a+=7;
        bar.setLayoutX(a);
        int i=getCell(a);
        if(i>20)
            ApplicationData.getGameGraphic().getController().newRound();
        if(!alreadyhit[i]){
            alreadyhit[i]=true;
            if(game.getHostRowStatus()[i].equals("card")){
                ApplicationData.decreaseGuestHP(game.getHostRowCards()[i].getDamage()/game.getHostRowCards()[i].getDuration());
                ApplicationData.getGameGraphic().getController().guesthp.setText("HP:"+ApplicationData.getGuest().getHP());
            }
            if(game.getGuestRowStatus()[i].equals("card")){
                ApplicationData.decreaseHostHP(game.getGuestRowCards()[i].getDamage()/game.getGuestRowCards()[i].getDuration());
                ApplicationData.getGameGraphic().getController().hosthp.setText("HP:"+ApplicationData.getGuest().getHP());
            }
            if(ApplicationData.getHost().getHP()<=0){
                ApplicationData.getGameGraphic().getController().guestWins();
            }
            if(ApplicationData.getGuest().getHP()<=0){
                ApplicationData.getGameGraphic().getController().hostWins();
            }
        }

        }

    private int getCell(double x){
        double y=x-8;
        int row=(int)y/47;
        return row;
    }
}
