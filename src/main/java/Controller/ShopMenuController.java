package Controller;

import Model.ApplicationData;
import Model.Card;

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
                showCardProperty(card);
                System.out.println("price: "+card.getUpgradeCost());
            }
        }
    }
    public static void showUpgrades(){
        for(Card card:ApplicationData.getHost().getAllPossessedCards()){
            showCardProperty(card);
            System.out.println("Minimum level required to upgrade this card: "+card.getUpgradeLevel());
            System.out.println("price: "+card.getUpgradeCost());
            Card card1 = card.NextLevelCard();
            System.out.println();
        }
    }
    public static void upgradeCard(Card card){
       boolean found=false;
        for(Card e:ApplicationData.getHost().getTwentyCardsAtDeck()) {
            if (card.getName().equals(e.getName()))
                found = true;
        }
        if(ApplicationData.getHost().getCoins()<card.getUpgradeCost() || ApplicationData.getHost().getLevel()<card.getUpgradeLevel()){
            System.out.println("Not enough coins or low level");
        return;}
        ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()-card.getUpgradeCost());
        ApplicationData.getHost().removeFromPossessedCards(card);
        ApplicationData.getHost().addToPossessedCards(card.NextLevelCard());
        if(found){
            ApplicationData.getHost().removeFromDeck(card);
            ApplicationData.getHost().addToDeck(card.NextLevelCard());
        }
        System.out.println("card upgraded successfully!");
    }
    public static void buyCard(Card card){
        boolean found=false;
        for(Card e:ApplicationData.getHost().getAllPossessedCards()) {
            if (card.getName().equals(e.getName()))
                found = true;
        }
        if(found){
            System.out.println("You already have this card");return;}
        if(ApplicationData.getHost().getCoins()<card.getUpgradeCost() || ApplicationData.getHost().getLevel()<card.getUpgradeLevel()){
            System.out.println("Not enough coins or low level");
            return;}
        ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()-card.getUpgradeCost());
        ApplicationData.getHost().addToPossessedCards(card);
        System.out.println("card is bought");
    }
}
