package Model;

import java.util.ArrayList;

public class Game {
    private String[] hostRowStatus;//nothing-hole-card-broken
    private String[] guestRowStatus;
    private Card[] hostRowCards;//card names
    private Card[] guestRowCards;


    private ArrayList<Card> hostCardsAtHand;
    private ArrayList<Card> guestCardsAtHand;
//    private User host;
//    private User guest;
    //boolean bet;
    private boolean hostTurn;
    private int hostRemainingTurns;
    private int guestRemainingTurns;
    Game(){
        int a = ApplicationData.getRandom().nextInt(21);
        int b = ApplicationData.getRandom().nextInt(21);
        hostRowCards =new Card[21];
        guestRowCards =new Card[21];
        hostRowStatus =new String[21];
        guestRowStatus =new String[21];
        for(int i=0;i<21;i++){
            hostRowCards[i]=nullCard();
            guestRowCards[i]=nullCard();
            hostRowStatus[i]="nothing";
            guestRowStatus[i]="nothing";
        }
        hostRowStatus[a]="hole";
        guestRowStatus[b]="hole";
        hostCardsAtHand=new ArrayList<>();
        guestCardsAtHand=new ArrayList<>();
        for(int i=0;i<5;i++){
            hostCardsAtHand.add(randomCardReplace(true));
            guestCardsAtHand.add(randomCardReplace(false));
        }
        boolean startt= randomHostStart();
        if(startt)
            hostTurn =true;
        else
            hostTurn =false;
        for(int i=0;i<6;i++){
            int x=1;
            //fill deck for first time
        }
        hostRemainingTurns =4;
        guestRemainingTurns =4;
    }
    boolean randomHostStart(){//1:guest starts-2host starts
        int a= ApplicationData.getRandom().nextInt(3);
        if(a==0)
            return false;
        return true;
    }
    public Card randomCardReplace(boolean ishost){

        int a= ApplicationData.getRandom().nextInt(20);
        if(ishost)
           return ApplicationData.getHost().getTwentyCardsAtDeck().get(a);
        else{
            return ApplicationData.getGuest().getTwentyCardsAtDeck().get(a);
        }
        //bayad barresi konim type special ziad nabashe too dast
    }
    public Card nullCard(){
        Card card=new Card(0,0,0,"-1",0);
        return card;
    }

    public String getHostRowStatus(int index) {
        return hostRowStatus[index];
    }
    public String[] getHostRowStatus() {
        return hostRowStatus;
    }

    public void setHostRowStatus(String str,int index) {
        this.hostRowStatus[index] = str;
    }

    public void setHostRowStatus(String[] list) {
        this.hostRowStatus = list;
    }

    public String getGuestRowStatus(int index) {
        return guestRowStatus[index];
    }
    public String[] getGuestRowStatus() {
        return guestRowStatus;
    }

    public void setGuestRowStatus(String str,int index) {
        this.guestRowStatus[index]=str;
    }

    public void setGuestRowStatus(String[] list) {
        this.guestRowStatus=list;
    }

    public Card[] getHostRowCards() {
        return hostRowCards;
    }

    public void setHostRowCards(Card card,int index) {
        this.hostRowCards[index] = card;
    }
    public void setHostRowCards(Card[] card) {
        this.hostRowCards = card;
    }

    public Card[] getGuestRowCards() {
        return guestRowCards;
    }

    public void setGuestRowCards(Card card,int index) {
        this.guestRowCards[index] = card;
    }
    public void setGuestRowCards(Card[] card) {
        this.guestRowCards = card;
    }

    public boolean isHostTurn() {
        return hostTurn;
    }

    public void setHostTurn() {
        this.hostTurn = !this.hostTurn;
    }

    public int getHostRemainingTurns() {
        return hostRemainingTurns;
    }

    public void setHostRemainingTurns(int hostRemainingTurns) {
        this.hostRemainingTurns = hostRemainingTurns;
    }

    public int getGuestRemainingTurns() {
        return guestRemainingTurns;
    }

    public void setGuestRemainingTurns(int guestRemainingTurns) {
        this.guestRemainingTurns = guestRemainingTurns;
    }

    public ArrayList<Card> getHostCardsAtHand() {
        return hostCardsAtHand;
    }

    public void setHostCardsAtHand(ArrayList<Card> hostCardsAtHand) {
        this.hostCardsAtHand = hostCardsAtHand;
    }

    public void addCardToHostCardsAtHand(Card card) {
        this.hostCardsAtHand.add(card);
    }
    public void removeCardFromHostCardsAtHand(Card card) {
        this.hostCardsAtHand.remove(card);
    }
    public ArrayList<Card> getGuestCardsAtHand() {
        return guestCardsAtHand;
    }

    public void setGuestCardsAtHand(ArrayList<Card> guestCardsAtHand) {
        this.guestCardsAtHand = guestCardsAtHand;
    }
    public void addCardToGuestCardsAtHand(Card card) {
        this.guestCardsAtHand.add(card);
    }
    public void removeCardFromGuestCardsAtHand(Card card) {
        this.guestCardsAtHand.remove(card);
    }


}
