package Controller;

import Model.ApplicationData;
import Model.Card;

import static Controller.GameController.showCardProperty;

public class ShopMenuController {
    public void showUnpossessedCards(){
        for(Card card: ApplicationData.getAllCardsArraylist()){
            boolean found=false;
            for(Card e:ApplicationData.getHost().getAllPossessedCards()){
                if(e.getName().equals(card.getName()))
                    found=true;
            }
            if(!found){
                showCardProperty(card);

            }
        }
    }
}
