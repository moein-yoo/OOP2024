package Controller;

import Model.Card;
import Model.Game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {
    Game game;
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
        game.setHostTurn();
        //...
        //timeline if needed
        //reduce turn numbers
    }
    public String placeCard(int cardnumber,int blocknumber){
        Card i=null;
        if(game.isHostTurn()){
            if(cardnumber>game.hostCardsAtHand.size())
                return "select a valid number";
            i=game.hostCardsAtHand.get(cardnumber);
        }
        if(!game.isHostTurn()){
            if(cardnumber>game.guestCardsAtHand.size())
                return "select a valid number";
            i=game.guestCardsAtHand.get(cardnumber);
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
                game.setGuestRowStatus("card", j);
                game.setGuestRowCards(i, j);
            }
            game.hostCardsAtHand.remove(i);
            game.randomCardReplace(true);
            nextTurn();
            checkshekasts();
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
                game.setHostRowStatus("card",j);
                game.setHostRowCards(i,j);
            }
            game.guestCardsAtHand.remove(i);
            game.randomCardReplace(false);
            nextTurn();
            checkshekasts();
            String str="Card "+i.getKind()+" placed successfully";
            return str;
        }
        return "salam";
    }
    public void checkshekasts(){
        //....
    }
}
