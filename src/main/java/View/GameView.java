package View;
import Controller.GameController;
import Model.ApplicationData;
import Model.Game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameView extends Menu{
    public static boolean run(Game game){
        GameController.setGame(game);
        Scanner scan= ApplicationData.getScanner();
        String command;
        Pattern exit=Pattern.compile("exit");
        Pattern back=Pattern.compile("back");
        Pattern selectCard=Pattern.compile("select card number (\\d+) player (\\S+)");
        Pattern placeCard=Pattern.compile("place card number (\\d+) in block (\\d+)");
        Pattern showfield=Pattern.compile("show\\s*field");
        while(true){
            command=scan.nextLine();

            Matcher exitm=exit.matcher(command);
            Matcher backm= back.matcher(command);
            Matcher selectCardm=selectCard.matcher(command);
            Matcher placeCardm= placeCard.matcher(command);
            Matcher showfieldm=showfield.matcher(command);

            boolean exitb= exitm.find();
            boolean backb=backm.find();
            boolean selectcardb= selectCardm.find();
            boolean placecardb= placeCardm.find();
            boolean showfieldb=showfieldm.find();

            if(exitb)
                break;
            if(backb)
                return true;
            if(placecardb){
                System.out.println(GameController.placeCard(Integer.parseInt(placeCardm.group(1)),Integer.parseInt(placeCardm.group(2))));}
            if(selectcardb){
                if(selectCardm.group(2).equalsIgnoreCase("host") || selectCardm.group(2).equalsIgnoreCase(ApplicationData.getHost().getNickname()))
                    System.out.println(GameController.showCardProperty(game.getHostCardsAtHand().get(Integer.parseInt(selectCardm.group(1)))));
                if(selectCardm.group(2).equalsIgnoreCase("guest") || selectCardm.group(2).equalsIgnoreCase(ApplicationData.getGuest().getNickname()))
                    System.out.println(GameController.showCardProperty(game.getGuestCardsAtHand().get(Integer.parseInt(selectCardm.group(1)))));
            }
            if(showfieldb)
                GameController.showField();
            if(GameController.gameover){
                GameController.gameover=false;
                return true;
            }
        }
        return false;
    }
}
