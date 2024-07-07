package Controller;

import Model.ApplicationData;
import Model.Card;
import Model.Game;

import java.util.ArrayList;
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


    public static void nextTurn(){
        if(game.isHostTurn())
            game.setHostRemainingTurns(game.getHostRemainingTurns()-1);
        if(!game.isHostTurn())
            game.setGuestRemainingTurns(game.getGuestRemainingTurns()-1);
        game.setHostTurn();
        if(game.getHostRemainingTurns()==0 && game.getGuestRemainingTurns()==0)
            createTimeline();
    }
    public static String createTimeline(){
        System.out.println("Timeline created!");
        for(int i=0;i<21;i++){
            if(game.getHostRowStatus()[i].equals("card")){
                ApplicationData.decreaseGuestHP(game.getHostRowCards()[i].getDamage()/game.getHostRowCards()[i].getDuration());
                System.out.println("Guest HP decreased by "+ game.getHostRowCards()[i].getDamage()/game.getHostRowCards()[i].getDuration()+" at block "+ i);
            }
            if(game.getGuestRowStatus()[i].equals("card")){
                ApplicationData.decreaseHostHP(game.getGuestRowCards()[i].getDamage()/game.getGuestRowCards()[i].getDuration());
                System.out.println("Host HP decreased by "+ game.getGuestRowCards()[i].getDamage()/game.getGuestRowCards()[i].getDuration()+" at block "+ i);
            }
            if(ApplicationData.getHost().getHP()<=0){
                return "player "+ ApplicationData.getGuest().getNickname()+" wins!";
            }
            if(ApplicationData.getGuest().getHP()<0){
                return "player "+ ApplicationData.getHost().getNickname()+" wins!";
            }
        }
        game.setHostRemainingTurns(4);
        game.setGuestRemainingTurns(4);
        return "New Round";
    }
    public static String placeCard(int cardnumber, int blocknumber){
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
            if(game.getHostCardsAtHand().size()>5)
                game.getHostCardsAtHand().removeLast();
            game.removeCardFromHostCardsAtHand(i);
            game.addCardToHostCardsAtHand(game.randomCardReplace(true));
            checkBrakes();
            buffCardPossibly(i);
            nextTurn();
            if(checkPossibleBonusForHost())
                giveBonus(true);
            String str="Card "+i.getName()+" placed successfully";
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
            if(game.getGuestCardsAtHand().size()>5)
                game.getGuestCardsAtHand().removeLast();
            game.removeCardFromGuestCardsAtHand(i);
            game.addCardToGuestCardsAtHand(game.randomCardReplace(false));
            checkBrakes();
            buffCardPossibly(i);
            nextTurn();
            if(checkPossibleBonusForGuest())
                giveBonus(false);
            String str="Card "+i.getName()+" placed successfully";
            return str;
        }
        return "salam";
    }
    public static String showCardProperty(Card card){
        if(card.isSpecial()){
            return "Spell name: "+card.getName();
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Card name: ");
        stringBuilder.append(card.getName());
        stringBuilder.append("\n");
        stringBuilder.append("Card accuracy: ");
        stringBuilder.append(card.getAccuracy());
        stringBuilder.append("\n");
        stringBuilder.append("Card damage: ");
        stringBuilder.append(card.getDamage());
        stringBuilder.append("\n");
        stringBuilder.append("Card duration: ");
        stringBuilder.append(card.getDuration());
        stringBuilder.append("\n");
        stringBuilder.append("Card type: ");
        stringBuilder.append(card.getCharacter());
        stringBuilder.append("\n");
        String string=stringBuilder.toString();
        return string;
    }
    public static void checkBrakes(){
        for(int i=0;i<21;i++){
            if(game.getGuestRowStatus()[i].equals("nothing") || game.getGuestRowStatus()[i].equals("hole") ){
                if(game.getHostRowStatus()[i].equals("broken"))
                    game.setHostRowStatus("card",i);
            }
            else if(game.getHostRowStatus()[i].equals("nothing") || game.getHostRowStatus()[i].equals("hole")){
                if(game.getGuestRowStatus()[i].equals("broken"))
                    game.setGuestRowStatus("card",i);
            }
            else{
                if(game.getHostRowCards()[i].getAccuracy() ==game.getGuestRowCards()[i].getAccuracy()){
                    game.setHostRowStatus("broken",i);
                    game.setGuestRowStatus("broken",i);
                }
                if(game.getHostRowCards()[i].getAccuracy() > game.getGuestRowCards()[i].getAccuracy()){
                    game.setGuestRowStatus("broken",i);
                    game.setHostRowStatus("card",i);
                }
                if(game.getHostRowCards()[i].getAccuracy() < game.getGuestRowCards()[i].getAccuracy()){
                    game.setGuestRowStatus("card",i);
                    game.setHostRowStatus("broken",i);
                }
            }
        }
    }
    public static boolean checkPossibleBonusForGuest(){
        ArrayList<Integer> startindexes=new ArrayList<>();
        ArrayList<Integer> endindexes=new ArrayList<>();
        int i=0;
        while(i<21){
            if(game.getHostRowStatus()[i].equals("card") || game.getHostRowStatus()[i].equals("broken")){
                startindexes.add(i);
                i+=game.getHostRowCards()[i].getDuration();
                i--;
                endindexes.add(i);
            }
            i++;
        }
        for(int c=0;c<startindexes.size();c++){
            if(numInArrayList(startindexes.get(c),game.getBonusCollectedindexesInHostRow()))
                continue;
            boolean bonus=true;
            for(int d=startindexes.get(c);d<=endindexes.get(c);d++){
                if(game.getHostRowStatus()[d].equals("card"))
                    bonus=false;
            }
            if(bonus){
                game.addSthHost(c);
                return true;}
        }
        return false;
    }
    public static void buffCardPossibly(Card card){
        int a=0;
        if(game.isHostTurn()){
             int n = game.getHostCardsAtHand().size();
            if(game.getHostCardsAtHand().size()%2!=1)
                return;
            if(card.getCharacter().equals(game.getHostCardsAtHand().get(n/2).getCharacter()))
                a=ApplicationData.getRandom().nextInt(n);
            if(!card.getCharacter().equals(game.getHostCardsAtHand().get(n/2).getCharacter()))
                a=ApplicationData.getRandom().nextInt(2*n);
        }
        else{
            int n = game.getGuestCardsAtHand().size();
            if(game.getGuestCardsAtHand().size()%2!=1)
                return;
            if(card.getCharacter().equals(game.getGuestCardsAtHand().get(n/2).getCharacter()))
                a=ApplicationData.getRandom().nextInt(n);
            if(!card.getCharacter().equals(game.getGuestCardsAtHand().get(n/2).getCharacter()))
                a=ApplicationData.getRandom().nextInt(2*n);
        }
        if(a<4){
            System.out.println("Card Buffed!");
            card.setDamage(card.getDamage()+card.getDuration());
            card.setAccuracy(card.getAccuracy()+3);
        }
    }
    public static boolean checkPossibleBonusForHost(){
        ArrayList<Integer> startindexes=new ArrayList<>();
        ArrayList<Integer> endindexes=new ArrayList<>();
        int i=0;
        while(i<21){
            if(game.getGuestRowStatus()[i].equals("card") || game.getGuestRowStatus()[i].equals("broken")){
                startindexes.add(i);
                i+=game.getGuestRowCards()[i].getDuration();
                i--;
                endindexes.add(i);
            }
            i++;
        }
        for(int c=0;c<startindexes.size();c++){
            if(numInArrayList(startindexes.get(c),game.getBonusCollectedindexesInGuestRow()))
                continue;
            boolean bonus=true;
            for(int d=startindexes.get(c);d<=endindexes.get(c);d++){
                if(game.getGuestRowStatus()[d].equals("card"))
                    bonus=false;
            }
            if(bonus){
                game.addSthGuest(c);
                return true;}
        }
        return false;
    }
    public static void giveBonus(boolean forHost){
        if(forHost)
         System.out.println("Bonus activated for host!");
        if(!forHost)
            System.out.println("Bonus activated for guest!");
        int a=ApplicationData.getRandom().nextInt(4);
        if(a==0 || a==1){
            System.out.println("extra card this round!");
            Card card=game.randomCardReplace(forHost);
            if(forHost)
                game.getHostCardsAtHand().add(card);
            else
                game.getGuestCardsAtHand().add(card);
        }
        if(a==2){
            System.out.println("Xp earned!");
            if(forHost)
                ApplicationData.getHost().increaseXP(1000);
            if(!forHost)
                ApplicationData.getGuest().increaseXP(1000);
        }
        if(a==3){
            System.out.println("Coin earned!");
            if(forHost)
                ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()+20);
            if(!forHost)
                ApplicationData.getGuest().setCoins(ApplicationData.getGuest().getCoins()+20);
        }
    }
    private static boolean numInArrayList(int a,ArrayList<Integer> arrlist){
        for(int i=0;i<arrlist.size();i++){
            if(a== arrlist.get(i))
                return true;
        }
        return false;
    }
    public void hostWins(){
        StringBuilder reward=new StringBuilder();
        StringBuilder pun=new StringBuilder();
        ApplicationData.getHost().setHP(game.getHostInitialHP()+30);
        ApplicationData.getGuest().setHP(game.getGuestInitialHP()-20);
        reward.append("HP increase: ");reward.append(30);
        if(ApplicationData.getGuest().getHP()<15)
            ApplicationData.getGuest().setHP(game.getGuestInitialHP()-20);
        int decline=game.getGuestInitialHP()-ApplicationData.getGuest().getHP();
        pun.append("HP decrease: ");pun.append(decline);
        ApplicationData.getHost().increaseXP(50 *ApplicationData.getGuest().getLevel());
        reward.append(",XP increase: ");reward.append(50 *ApplicationData.getGuest().getLevel());
        if(game.getBetAmount()==0){
         ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()+15);
        reward.append(",Coins increased by 15");
        }
        if(game.getBetAmount()!=0){
            ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()+game.getBetAmount());
            ApplicationData.getGuest().setCoins(ApplicationData.getGuest().getCoins()-game.getBetAmount());
            reward.append(",Coins increased by ");reward.append(game.getBetAmount());
            pun.append(",Coins decreased by ");pun.append(game.getBetAmount());
        }
    }
    public void guestWins(){

    }
    public static void showField(){
        System.out.print("Host row:\t");
        for(int i=0;i<21;i++){
            if(game.getHostRowStatus()[i].equals("hole") || game.getHostRowStatus()[i].equals("nothing"))
                System.out.print(game.getHostRowStatus()[i]+'\t');
            if(game.getHostRowStatus()[i].equals("card"))
                System.out.print(game.getHostRowCards()[i].getName()+'\t');
            if(game.getHostRowStatus()[i].equals("broken"))
                System.out.print(game.getHostRowCards()[i].getName()+"(broken)\t");
        }
        System.out.println();
        System.out.print("Guest row:\t");
        for(int i=0;i<21;i++){
            if(game.getGuestRowStatus()[i].equals("hole") || game.getGuestRowStatus()[i].equals("nothing"))
                System.out.print(game.getGuestRowStatus()[i]+'\t');
            if(game.getGuestRowStatus()[i].equals("card"))
                System.out.print(game.getGuestRowCards()[i].getName()+'\t');
            if(game.getGuestRowStatus()[i].equals("broken"))
                System.out.print(game.getGuestRowCards()[i].getName()+"(broken)\t");
        }
        System.out.println();
        System.out.print("Host cards to play:\t");
        for(Card card:game.getHostCardsAtHand())
            System.out.print(card.getName()+'\t');
        System.out.println();
        System.out.print("Guest cards to play:\t");
        for(Card card:game.getGuestCardsAtHand())
            System.out.print(card.getName()+'\t');
        System.out.println();
        if(game.isHostTurn())
            System.out.println("Now is Host's turn to play");
        if(!game.isHostTurn())
            System.out.println("Now is Guest's turn to play");
    }
}
