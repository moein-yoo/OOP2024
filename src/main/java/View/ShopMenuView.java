package View;

import Controller.GameController;
import Controller.ShopMenuController;
import Model.ApplicationData;
import Model.Card;
import javafx.css.Match;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Model.ApplicationData.cardTranslator;

public class ShopMenuView {
    public static boolean run(){
        Scanner scan= ApplicationData.getScanner();
        Pattern buyCard=Pattern.compile("buy card (\\w+)");
        Pattern upgradeCard=Pattern.compile("upgrade card (\\w+)");
        Pattern exit=Pattern.compile("exit");
        Pattern showCardsIdonthave=Pattern.compile("show unpossessed cards");
        Pattern showupgrades=Pattern.compile("show upgrades");
        String command;

        while(true){
            command=scan.nextLine();
            Matcher upgradem=upgradeCard.matcher(command);
            Matcher buym= buyCard.matcher(command);
            Matcher exitm= exit.matcher(command);
            Matcher showIdontm=showCardsIdonthave.matcher(command);
            Matcher showupsm=showupgrades.matcher(command);

            boolean upgradeb=upgradem.find();
            boolean buyb=buym.find();
            boolean exitb= exitm.find();
            boolean showIdontb=showIdontm.find();
            boolean showupsb=showupsm.find();
            if(exitb)
                break;
            if(showIdontb){
                ShopMenuController.showUnpossessedCards();
            }
            if(showupsb){
                ShopMenuController.showUpgrades();
            }
            if(buyb){
                boolean found=false;
                for(Card card:ApplicationData.getAllCardsArraylist()){if(card.getName().equals(buym.group(1))) found=true;}
                if(!found){
                    System.out.println("invalid card!");
                }
                else{
                    ShopMenuController.buyCard(cardTranslator(buym.group(1),1));
                }

            }
            if(upgradeb){
                int cardlevel=0;
                boolean found=false;
                for(Card e:ApplicationData.getHost().getAllPossessedCards()) {
                    if (upgradem.group(1).equals(e.getName())){
                        found = true;
                        cardlevel=e.getLevel();
                    }
                }
                if(!found){
                    System.out.println("You don't own ths card");}
                if(found){
                    ShopMenuController.upgradeCard(cardTranslator(upgradem.group(1),cardlevel));
                }
            }
        }
        return false;
    }
}
