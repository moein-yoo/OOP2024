package Model;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private String[] hostRowStatus;//empty-hole-card-shekaste
    private String[] guestRowStatus;
    private Card[] hostRowCards;//card names
    private Card[] guestRowCards;
    public ArrayList<Card> hostCardsAtHand;
    public ArrayList<Card> guestCardsAtHand;
    private User host;
    private User guest;
    //boolean bet;
    private boolean hostTurn;
    private int hostRemainingTurns;
    private int guestRemainingTurns;
    Game(User host,User guest){
        this.host=host;
        this.guest=guest;
        Random random=new Random();
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        hostRowCards =new Card[21];
        guestRowCards =new Card[21];
        hostRowStatus =new String[21];
        guestRowStatus =new String[21];
        for(int i=0;i<21;i++){
            hostRowCards[i]=nullCard();
            guestRowCards[i]=nullCard();
            hostRowStatus[i]="empty";
            guestRowStatus[i]="empty";
        }
        hostRowStatus[a]="hole";
        guestRowStatus[b]="hole";
        hostCardsAtHand=new ArrayList<>();
        guestCardsAtHand=new ArrayList<>();
        for(int i=0;i<5;i++){
            hostCardsAtHand.add(randomCardReplace(true));
            guestCardsAtHand.add(randomCardReplace(false));


        }
        boolean startt=randomhoststart();
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
    boolean randomhoststart(){//1:guest starts-2host starts
        Random random=new Random();
        int a=random.nextInt(3);
        if(a==0)
            return false;
        return true;
    }
    public Card randomCardReplace(boolean ishost){
        Random random=new Random();
        int a=random.nextInt(20);
        if(ishost)
           return host.getTwentyCardsAtDeck().get(a);
        else{
            return guest.getTwentyCardsAtDeck().get(a);
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

    public void setHostRowStatus(String str,int index) {
        this.hostRowStatus[index] = str;
    }

    public String getGuestRowStatus(int index) {
        return guestRowStatus[index];
    }

    public void setGuestRowStatus(String str,int index) {
        this.guestRowStatus[index]=str;
    }

    public Card[] getHostRowCards() {
        return hostRowCards;
    }

    public void setHostRowCards(Card card,int index) {
        this.hostRowCards[index] = card;
    }

    public Card[] getGuestRowCards() {
        return guestRowCards;
    }

    public void setGuestRowCards(Card card,int index) {
        this.guestRowCards[index] = card;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
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



}
