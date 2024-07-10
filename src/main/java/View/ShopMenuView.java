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
        Pattern showCardsDontHave=Pattern.compile("show unpossessed cards");
        Pattern showUpgrades=Pattern.compile("show upgrades");
        Pattern showMenu=Pattern.compile("show current menu");
        Pattern back = Pattern.compile("back");
        String command;

        while(true){
            command=scan.nextLine();
            Matcher upgradem=upgradeCard.matcher(command);
            Matcher buym= buyCard.matcher(command);
            Matcher exitm= exit.matcher(command);
            Matcher showIdontm=showCardsDontHave.matcher(command);
            Matcher showupsm=showUpgrades.matcher(command);
            Matcher showmenum = showMenu.matcher(command);
            Matcher backm = back.matcher(command);

            boolean upgradeb=upgradem.find();
            boolean buyb=buym.find();
            boolean exitb= exitm.find();
            boolean showIdontb=showIdontm.find();
            boolean showupsb=showupsm.find();
            boolean showmenub=showmenum.find();
            boolean backb = backm.find();
            if(exitb)
                break;
            else if (backb)
                return true;
            else if(showIdontb){
                ShopMenuController.showUnpossessedCards();
            }
            else if(showupsb){
                ShopMenuController.showUpgrades();
            }
            else if (showmenub) {
                System.out.println("Shop Menu");
            }
            else if(buyb){
                int cardlevel=0;
                boolean found=false;
                for(Card card:ApplicationData.getAllCardsArraylist()){
                    if(card.getName().equals(buym.group(1))) {
                        cardlevel=card.getLevel();
                        found=true;
                    }
                }
                if(!found){
                    System.out.println("invalid card!");
                }
                else{
                    System.out.println(ShopMenuController.buyCard(cardTranslator(buym.group(1),cardlevel)));
                }

            }
            else if(upgradeb){
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
                    System.out.println(ShopMenuController.upgradeCard(cardTranslator(upgradem.group(1),cardlevel)));
                }
            }
            else {
                System.out.println("Invalid Command!");
            }
        }
        return false;
    }
}
