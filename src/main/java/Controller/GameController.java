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

    public boolean run(Game game, Scanner scan, String command){
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
        }
        return false;
    }
    public void nextTurn(){
        if(game.isHostTurn())
            game.setHostRemainingTurns(game.getHostRemainingTurns()-1);
        if(!game.isHostTurn())
            game.setGuestRemainingTurns(game.getGuestRemainingTurns()-1);
        game.setHostTurn();
        if(game.getHostRemainingTurns()==0 || game.getGuestRemainingTurns()==0)
            createTimeline();
    }
    public String createTimeline(){
        System.out.println("Timeline created!");
        for(int i=0;i<21;i++){
            if(game.getHostRowStatus()[i].equals("card")){
                ApplicationData.decreaseGuestHP(game.getHostRowCards()[i].getDamage()/game.getHostRowCards()[i].getDuration());
                System.out.println("Guest HP decreased by "+ game.getHostRowCards()[i].getDamage()/game.getHostRowCards()[i].getDuration()+" at block "+ i);
            }
            if(game.getGuestRowStatus()[i].equals("card")){
                ApplicationData.decreaseHostHP(game.getGuestRowCards()[i].getDamage());
                System.out.println("Host HP decreased by "+ game.getGuestRowCards()[i].getDamage()/game.getGuestRowCards()[i].getDuration()+" at block "+ i);
            }
            if(ApplicationData.getHost().getHP()<=0)
                return "player "+ ApplicationData.getGuest().getNickname()+" wins!";
            if(ApplicationData.getGuest().getHP()<0)
                return "player "+ ApplicationData.getHost().getNickname()+" wins!";
        }
        game.setHostRemainingTurns(4);
        game.setGuestRemainingTurns(4);
        return "New Round";
    }
    public String placeCard(int cardnumber,int blocknumber){
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
            nextTurn();
            if(checkPossibleBonusForHost())
                giveBonus(true);
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
                game.setGuestRowStatus("card",j);
                game.setGuestRowCards(i,j);
            }
            if(game.getGuestCardsAtHand().size()>5)
                game.getGuestCardsAtHand().removeLast();
            game.removeCardFromGuestCardsAtHand(i);
            game.addCardToGuestCardsAtHand(game.randomCardReplace(false));
            checkBrakes();
            nextTurn();
            if(checkPossibleBonusForGuest())
                giveBonus(false);
            String str="Card "+i.getKind()+" placed successfully";
            return str;
        }
        return "salam";
    }
    public void checkBrakes(){
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
    public boolean checkPossibleBonusForGuest(){
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
    public boolean checkPossibleBonusForHost(){
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
    public void giveBonus(boolean forHost){
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
    private boolean numInArrayList(int a,ArrayList<Integer> arrlist){
        for(int i=0;i<arrlist.size();i++){
            if(a== arrlist.get(i))
                return true;
        }
        return false;
    }
}
