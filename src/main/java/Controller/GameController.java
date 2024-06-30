package Controller;

import Model.Application;
import Model.Card;
import Model.Game;
import javafx.scene.control.cell.TextFieldTreeTableCell;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {
    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GameController.game = game;
    }

    private static Game game;
    public boolean run(Game game,Scanner scan,String command){
        Pattern exit=Pattern.compile("exit");
        Pattern back=Pattern.compile("back");
        Pattern selectcard=Pattern.compile("select card number (\\d+) player (\\s+)");
        Pattern placecard=Pattern.compile("place card number (\\d+) in block (\\d+)");
        while(true){
            command=scan.nextLine();

//eruyfhhieckjuy
            Matcher exitm=exit.matcher(command);
            Matcher backm= back.matcher(command);
            Matcher selectcardm=selectcard.matcher(command);
            Matcher placecardm= placecard.matcher(command);

            boolean exitb= exitm.find();
            boolean backb=backm.find();
            boolean selectcardb= selectcardm.find();
            boolean placecardb= placecardm.find();

            if(exitb)
                break;
            if(backb)
                return true;
        }
        return false;
    }
    public void nextTurn(){
        if(game.isHostTurn())
            game.setHostRemainingTurns(game.getHostRemainingTurns()-1);
        if(!game.isHostTurn())
            game.setGuestRemainingTurns(game.getGuestRemainingTurns()-1);
        game.setHostTurn();
        if(game.getHostRemainingTurns()==0 || game.getGuestRemainingTurns()==0)
            createTimeline();
    }
    public String createTimeline(){
        System.out.println("Timeline created!");
        for(int i=0;i<21;i++){
            if(game.getHostRowStatus()[i].equals("card"))
                Application.decreaseGuestHP(game.getHostRowCards()[i].getDamage());
            if(game.getGuestRowStatus()[i].equals("card"))
                Application.decreaseHostHP(game.getGuestRowCards()[i].getDamage());
            if(Application.getHost().getHP()<=0)
                return "player "+Application.getGuest().getNickname()+" wins!";
            if(Application.getGuest().getHP()<0)
                return "player "+Application.getHost().getNickname()+" wins!";
        }
        game.setHostRemainingTurns(4);
        game.setGuestRemainingTurns(4);
        return "New Round";
    }
    public String placeCard(int cardnumber,int blocknumber){
        Card i=null;
        if(game.isHostTurn()){
            if(cardnumber>game.getHostCardsAtHand().size())
                return "select a valid number";
            i=game.getHostCardsAtHand().get(cardnumber);
        }
        if(!game.isHostTurn()){
            if(cardnumber>game.getGuestCardsAtHand().size())
                return "select a valid number";
            i=game.getGuestCardsAtHand().get(cardnumber);
        }
        if(blocknumber+i.getDuration()>20){
            return "invalid spot for card placement";
        }
        if(game.isHostTurn()){
            for(int j=blocknumber;j<blocknumber+i.getDuration();j++){
                if(!game.getGuestRowStatus(j).equals("empty")){
                   return "invalid spot for card placement";
                }
            }
            for(int j=blocknumber;j<blocknumber+i.getDuration();j++) {
                game.setHostRowStatus("card", j);
                game.setHostRowCards(i, j);
            }
            game.removeCardFromHostCardsAtHand(i);
            game.addCardToHostCardsAtHand(game.randomCardReplace(true));
            checkBrakes();
            nextTurn();
            String str="Card "+i.getKind()+" placed successfully";
            return str;
        }
        if(!game.isHostTurn()){
            for(int j=blocknumber;j<blocknumber+i.getDuration();j++){
                if(!game.getHostRowStatus(j).equals("empty")){
                    return "invalid spot for card placement";
                }
            }
            for(int j=blocknumber;j<blocknumber+i.getDuration();j++) {
                game.setGuestRowStatus("card",j);
                game.setGuestRowCards(i,j);
            }
            game.removeCardFromGuestCardsAtHand(i);
            game.addCardToGuestCardsAtHand(game.randomCardReplace(false));
            checkBrakes();
            nextTurn();
            String str="Card "+i.getKind()+" placed successfully";
            return str;
        }
        return "salam";
    }
    public void checkBrakes(){
        for(int i=0;i<21;i++){
            if(game.getGuestRowStatus()[i].equals("nothing") || game.getGuestRowStatus()[i].equals("hole") || game.getHostRowStatus()[i].equals("nothing") || game.getHostRowStatus()[i].equals("hole")){continue;}
            if(game.getHostRowCards()[i].getAccuracy() <=game.getGuestRowCards()[i].getAccuracy()){
                game.setHostRowStatus("broken",i);
            }
            if(game.getHostRowCards()[i].getAccuracy() >=game.getGuestRowCards()[i].getAccuracy()){
                game.setGuestRowStatus("broken",i);
            }
        }
    }
    public void checkPossibleBonus(){

    }
}
