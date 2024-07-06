package View;
import Controller.GameController;
import Model.ApplicationData;
import Model.Game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameView extends Menu{
    public static boolean run(Game game){
        Scanner scan= ApplicationData.getScanner();
        String command;
        Pattern exit=Pattern.compile("exit");
        Pattern back=Pattern.compile("back");
        Pattern selectCard=Pattern.compile("select card number (\\d+) player (\\s+)");
        Pattern placeCard=Pattern.compile("place card number (\\d+) in block (\\d+)");
        while(true){
            command=scan.nextLine();

            Matcher exitm=exit.matcher(command);
            Matcher backm= back.matcher(command);
            Matcher selectCardm=selectCard.matcher(command);
            Matcher placeCardm= placeCard.matcher(command);

            boolean exitb= exitm.find();
            boolean backb=backm.find();
            boolean selectcardb= selectCardm.find();
            boolean placecardb= placeCardm.find();

            if(exitb)
                break;
            if(backb)
                return true;
            if(placecardb)
                GameController.placeCard(Integer.parseInt(placeCardm.group(1)),Integer.parseInt(placeCardm.group(2)));
            if(selectcardb){
                if(selectCardm.group(2).equalsIgnoreCase("host") || selectCardm.group(2).equalsIgnoreCase(ApplicationData.getHost().getNickname()))
                    GameController.showCardProperty(GameController.getGame().getHostCardsAtHand().get(Integer.parseInt(selectCardm.group(1))));
                if(selectCardm.group(2).equalsIgnoreCase("guest") || selectCardm.group(2).equalsIgnoreCase(ApplicationData.getGuest().getNickname()))
                    GameController.showCardProperty(GameController.getGame().getGuestCardsAtHand().get(Integer.parseInt(selectCardm.group(1))));
            }
        }
        return false;
    }
}
