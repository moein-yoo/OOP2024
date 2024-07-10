package Controller;

import Model.ApplicationData;
import Model.Card;
import Model.User;

import static Controller.GameController.showCardProperty;

public class ShopMenuController {
    public static void showUnpossessedCards(){
        for(Card card: ApplicationData.getAllCardsArraylist()){
            boolean found=false;
            for(Card e:ApplicationData.getHost().getAllPossessedCards()){
                if(e.getName().equals(card.getName()))
                    found=true;
            }
            if(!found){
                System.out.print(showCardProperty(card));
                System.out.println("price: "+card.getUpgradeCost());
            }
        }
    }
    public static void showUpgrades(){
        for(Card card:ApplicationData.getHost().getAllPossessedCards()){
            System.out.println(showCardProperty(card));
            System.out.println("Minimum level required to upgrade this card: "+card.getUpgradeLevel());
            System.out.println("price: "+card.getUpgradeCost());
            Card card1 = card.NextLevelCard();
            System.out.println("Next level properties:");
            System.out.println(showCardProperty(card1));
        }
    }
    public static String upgradeCard(Card card){

        if(ApplicationData.getHost().getCoins()<card.getUpgradeCost() || ApplicationData.getHost().getLevel()<card.getLevel()){
            return ("Not enough coins or low level");
        }
        ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()-card.getUpgradeCost());
        ApplicationData.getHost().removeFromPossessedCards(card);
        ApplicationData.getHost().addToPossessedCards(card.NextLevelCard());

        User.updateUserInSQL(ApplicationData.getHost());
        return ("card upgraded successfully!");
    }
    public static String buyCard(Card card){
        boolean found=false;
        for(Card e:ApplicationData.getHost().getAllPossessedCards()) {
            if (card.getName().equals(e.getName()))
                found = true;
        }
        if(found){
            return "You already have this card";
        }
        if(ApplicationData.getHost().getCoins()<card.getUpgradeCost() || ApplicationData.getHost().getLevel()<card.getLevel()){
            return ("Not enough coins or low level");
        }
        ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()-card.getUpgradeCost());
        ApplicationData.getHost().addToPossessedCards(card);
        User.updateUserInSQL(ApplicationData.getHost());
        return ("card is bought");
    }
}
