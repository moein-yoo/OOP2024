package ViewGraphic;

import Controller.GameController2;
import Model.ApplicationData;
import Model.Game;
import Model.MatchData;
import Model.User;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.sql.Timestamp;

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
                StringBuilder reward=new StringBuilder();
                StringBuilder pun=new StringBuilder();
                ApplicationData.getGuest().setHP(game.getHostInitialHP()+30);
                ApplicationData.getHost().setHP(game.getGuestInitialHP()-20);
                reward.append("HP increase: ");reward.append(30);
                if(ApplicationData.getHost().getHP()<15)
                    ApplicationData.getHost().setHP(15);
                int decline=game.getHostInitialHP()-ApplicationData.getHost().getHP();
                pun.append("HP decrease: ");pun.append(decline);
                ApplicationData.getGuest().increaseXP(50 *ApplicationData.getHost().getLevel());
                reward.append(",XP increase: ");reward.append(50 *ApplicationData.getHost().getLevel());
                if(game.getBetAmount()==0){
                    ApplicationData.getGuest().setCoins(ApplicationData.getGuest().getCoins()+15);
                    reward.append(",Coins increased by 15");
                }
                if(game.getBetAmount()!=0){
                    ApplicationData.getGuest().setCoins(ApplicationData.getGuest().getCoins()+game.getBetAmount());
                    ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()-game.getBetAmount());
                    reward.append(",Coins increased by ");reward.append(game.getBetAmount());
                    pun.append(",Coins decreased by ");pun.append(game.getBetAmount());
                }
                String award=reward.toString();
                String punishment=pun.toString();
                Timestamp timestamp=new Timestamp(System.currentTimeMillis());

                MatchData matchData=new MatchData(ApplicationData.getGuest().getNickname(),ApplicationData.getHost().getNickname(),award,punishment,timestamp,ApplicationData.getGuest().getLevel(),ApplicationData.getHost().getLevel());
                MatchData.addToMatchData(matchData);
                MatchData.addToList(matchData);
                User.updateUserInSQL(ApplicationData.getHost());
                User.updateUserInSQL(ApplicationData.getGuest());
                this.stop();
            }
            if(ApplicationData.getGuest().getHP()<=0){
                //host wins
                StringBuilder reward=new StringBuilder();
                StringBuilder pun=new StringBuilder();
                ApplicationData.getHost().setHP(game.getHostInitialHP()+30);
                ApplicationData.getGuest().setHP(game.getGuestInitialHP()-20);
                reward.append("HP increase: ");reward.append(30);
                if(ApplicationData.getGuest().getHP()<15)
                    ApplicationData.getGuest().setHP(15);
                int decline=game.getGuestInitialHP()-ApplicationData.getGuest().getHP();
                pun.append("HP decrease: ");pun.append(decline);
                ApplicationData.getHost().increaseXP(50 *ApplicationData.getGuest().getLevel());
                reward.append(",XP increase: ");reward.append(50 *ApplicationData.getGuest().getLevel());
                if(game.getBetAmount()==0){
                    ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()+15);
                    reward.append(",Coins increased by 15");
                }
                if(game.getBetAmount()!=0){
                    ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()+game.getBetAmount());
                    ApplicationData.getGuest().setCoins(ApplicationData.getGuest().getCoins()-game.getBetAmount());
                    reward.append(",Coins increased by ");reward.append(game.getBetAmount());
                    pun.append(",Coins decreased by ");pun.append(game.getBetAmount());
                }
                String award=reward.toString();
                String punishment=pun.toString();
                Timestamp timestamp=new Timestamp(System.currentTimeMillis());

                MatchData matchData=new MatchData(ApplicationData.getHost().getNickname(),ApplicationData.getGuest().getNickname(),award,punishment,timestamp,ApplicationData.getHost().getLevel(),ApplicationData.getGuest().getLevel());
                MatchData.addToMatchData(matchData);
                MatchData.addToList(matchData);
                User.updateUserInSQL(ApplicationData.getHost());
                User.updateUserInSQL(ApplicationData.getGuest());
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
