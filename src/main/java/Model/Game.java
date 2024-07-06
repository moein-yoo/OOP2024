package Model;

import java.util.ArrayList;

public class Game {
    private static String[] hostRowStatus;//nothing-hole-card-broken
    private static String[] guestRowStatus;
    private static Card[] hostRowCards;//card names
    private static Card[] guestRowCards;


    private static ArrayList<Card> hostCardsAtHand;
    private static ArrayList<Card> guestCardsAtHand;
//    private User host;
//    private User guest;
    //boolean bet;
    private static boolean hostTurn;
    private int hostRemainingTurns;
    private int guestRemainingTurns;
    private ArrayList<Integer> bonusCollectedindexesInHostRow;
    private ArrayList<Integer> bonusCollectedindexesInGuestRow;
    private int betAmount;
    private int hostInitialHP;
    private int guestInitialHP;

    public Game(){
        hostInitialHP=ApplicationData.getHost().getHP();
        guestInitialHP=ApplicationData.getGuest().getHP();
        int a = ApplicationData.getRandom().nextInt(21);
        int b = ApplicationData.getRandom().nextInt(21);
        bonusCollectedindexesInHostRow=new ArrayList<>();
        bonusCollectedindexesInGuestRow=new ArrayList<>();
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
        betAmount=0;
    }
    public Game(int betAmount){
        int a = ApplicationData.getRandom().nextInt(21);
        int b = ApplicationData.getRandom().nextInt(21);
        bonusCollectedindexesInHostRow=new ArrayList<>();
        bonusCollectedindexesInGuestRow=new ArrayList<>();
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
        this.betAmount=betAmount;
    }
    boolean randomHostStart(){//1:guest starts-2host starts
        int a= ApplicationData.getRandom().nextInt(3);
        if(a==0)
            return false;
        return true;
    }
    public Card randomCardReplace(boolean ishost){
        if(ishost){
            int r=0;
            for(Card card:hostCardsAtHand){if(card.isSpecial())r++;}
            Card card2= ApplicationData.getHost().getTwentyCardsAtDeck().get(ApplicationData.getRandom().nextInt(20));
            if(r>=2){
                while(card2.isSpecial())
                    card2=ApplicationData.getHost().getTwentyCardsAtDeck().get(ApplicationData.getRandom().nextInt(20));
            }
           return card2;
        }
        else{
            int r=0;
            for(Card card:guestCardsAtHand){if(card.isSpecial())r++;}
            Card card2= ApplicationData.getGuest().getTwentyCardsAtDeck().get(ApplicationData.getRandom().nextInt(20));
            if(r>=2){
                while(card2.isSpecial())
                    card2=ApplicationData.getGuest().getTwentyCardsAtDeck().get(ApplicationData.getRandom().nextInt(20));
            }
            return card2;
        }
    }
    public Card nullCard(){
        Card card=new Card("1",0,0,0,"1",0,1);
        return card;
    }
    public static void showField(){
        System.out.print("Host row:\t");
        for(int i=0;i<21;i++){
            if(hostRowStatus[i].equals("hole") || hostRowStatus[i].equals("nothing"))
                System.out.print(hostRowStatus[i]+'\t');
            if(hostRowStatus[i].equals("card"))
                System.out.print(hostRowCards[i].getName()+'\t');
            if(hostRowStatus[i].equals("broken"))
                System.out.print(hostRowCards[i].getName()+"(broken)\t");
        }
        System.out.println();
        System.out.print("Guest row:\t");
        for(int i=0;i<21;i++){
            if(guestRowStatus[i].equals("hole") || guestRowStatus[i].equals("nothing"))
                System.out.print(guestRowStatus[i]+'\t');
            if(guestRowStatus[i].equals("card"))
                System.out.print(guestRowCards[i].getName()+'\t');
            if(guestRowStatus[i].equals("broken"))
                System.out.print(guestRowCards[i].getName()+"(broken)\t");
        }
        System.out.println();
        System.out.print("Host cards to play:\t");
        for(Card card:hostCardsAtHand)
            System.out.print(card.getName()+'\t');
        System.out.println();
        System.out.print("Guest cards to play:\t");
        for(Card card:guestCardsAtHand)
            System.out.print(card.getName()+'\t');
        System.out.println();
        if(hostTurn)
            System.out.println("Now is Host's turn to play");
        if(!hostTurn)
            System.out.println("Now is Guest's turn to play");
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


    public ArrayList<Integer> getBonusCollectedindexesInGuestRow() {
        return bonusCollectedindexesInGuestRow;
    }
    public void addSthGuest(int a){this.getBonusCollectedindexesInGuestRow().add(a);}
    public void addSthHost(int a){this.getBonusCollectedindexesInHostRow().add(a);}
    public ArrayList<Integer> getBonusCollectedindexesInHostRow() {
        return bonusCollectedindexesInHostRow;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }
}
