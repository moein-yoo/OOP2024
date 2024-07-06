package View;

import Controller.GameController;
import Model.ApplicationData;
import Model.Game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenuView {
    public boolean run(Game game){
        Scanner scan= ApplicationData.getScanner();
        String command;
        Pattern exit=Pattern.compile("exit");
        Pattern back=Pattern.compile("back");
        Pattern selectcard=Pattern.compile("select card number (\\d+) player (\\s+)");
        Pattern placecard=Pattern.compile("place card number (\\d+) in block (\\d+)");
        while(true){
            command=scan.nextLine();

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
            if(placecardb)
                GameController.placeCard(Integer.parseInt(placecardm.group(1)),Integer.parseInt(placecardm.group(2)));
            if(selectcardb){
                if(selectcardm.group(2).equalsIgnoreCase("host") || selectcardm.group(2).equalsIgnoreCase(ApplicationData.getHost().getNickname()))
                    GameController.showCardProperty(GameController.getGame().getHostCardsAtHand().get(Integer.parseInt(selectcardm.group(1))));
                if(selectcardm.group(2).equalsIgnoreCase("guest") || selectcardm.group(2).equalsIgnoreCase(ApplicationData.getGuest().getNickname()))
                    GameController.showCardProperty(GameController.getGame().getGuestCardsAtHand().get(Integer.parseInt(selectcardm.group(1))));
            }
        }
        return false;
    }
}
