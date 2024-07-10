package ViewGraphic;

import Controller.GameController2;
import Model.ApplicationData;
import Model.Game;
import Model.User;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class MovingBarAnimation extends Transition {
    Pane pane;
    Game game;
    Rectangle rectangle;
    boolean alreadyhit[];
    public MovingBarAnimation(Pane pane,Rectangle rectangle,Game game){
        this.game=game;
        this.pane=pane;
        this.rectangle=rectangle;
        alreadyhit=new boolean[21];
        for(boolean bool:alreadyhit)
            bool=false;
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(rectangle);
            }
        });
    }
    @Override
    protected void interpolate(double v){
        int i=getCell(rectangle.getLayoutX());
        if(i>20)
            this.stop();
        if(!alreadyhit[i]){
            if(game.getHostRowStatus()[i].equals("card")){
                ApplicationData.decreaseGuestHP(game.getHostRowCards()[i].getDamage()/game.getHostRowCards()[i].getDuration());
                GameController2.guesthp.setText("HP:"+ApplicationData.getGuest().getHP());
            }
            if(game.getGuestRowStatus()[i].equals("card")){
                ApplicationData.decreaseHostHP(game.getGuestRowCards()[i].getDamage()/game.getGuestRowCards()[i].getDuration());
                GameController2.hosthp.setText("HP:"+ApplicationData.getGuest().getHP());
            }
            if(ApplicationData.getHost().getHP()<=0){
                //guestWins
                this.stop();
            }
            if(ApplicationData.getGuest().getHP()<=0){
                //host wins
                this.stop();
            }
        }
        game.setHostRemainingTurns(4);
        game.setGuestRemainingTurns(4);
        }

    private int getCell(double x){
        double y=x-8;
        int row=(int)y/47;
        return row;
    }
}
