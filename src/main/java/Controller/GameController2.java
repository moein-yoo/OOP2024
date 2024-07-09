package Controller;

import Model.ApplicationData;
import Model.Card;
import Model.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameController2 {
    private static Game game;
    boolean flag=false;
    boolean commentOn=false;
    @FXML
    public Rectangle hostComment;
    @FXML
    public Rectangle guestComment;
    @FXML
    public Button backbutton;
    @FXML
    public Label hostUsername;
    @FXML
    public Label hosthp;
    @FXML
    public Label comment1;
    @FXML
    public Label comment2;
    @FXML
    public Label guestUsername;
    @FXML
    public Label guesthp;
    @FXML
    public Label turnSolver;

    @FXML
    AnchorPane root;
    @FXML
    Rectangle hortrect0;
    @FXML
    Rectangle hortrect1;
    @FXML
    Rectangle hortrect2;
    @FXML
    Rectangle hortrect3;
    @FXML
    Rectangle hortrect4;
    @FXML
    Rectangle hortrect5;
    @FXML
    Rectangle hortrect6;
    @FXML
    Rectangle hortrect7;
    @FXML
    Rectangle hortrect8;
    @FXML
    Rectangle hortrect9;
    @FXML
    Rectangle hortrect10;
    @FXML
    Rectangle hortrect11;
    @FXML
    Rectangle hortrect12;
    @FXML
    Rectangle hortrect13;
    @FXML
    Rectangle hortrect14;
    @FXML
    Rectangle hortrect15;
    @FXML
    Rectangle hortrect16;
    @FXML
    Rectangle hortrect17;
    @FXML
    Rectangle hortrect18;
    @FXML
    Rectangle hortrect19;
    @FXML
    Rectangle hortrect20;
    @FXML
    Rectangle guestrect0;
    @FXML
    Rectangle guestrect1;
    @FXML
    Rectangle guestrect2;
    @FXML
    Rectangle guestrect3;
    @FXML
    Rectangle guestrect4;
    @FXML
    Rectangle guestrect5;
    @FXML
    Rectangle guestrect6;
    @FXML
    Rectangle guestrect7;
    @FXML
    Rectangle guestrect8;
    @FXML
    Rectangle guestrect9;
    @FXML
    Rectangle guestrect10;
    @FXML
    Rectangle guestrect11;
    @FXML
    Rectangle guestrect12;
    @FXML
    Rectangle guestrect13;
    @FXML
    Rectangle guestrect14;
    @FXML
    Rectangle guestrect15;
    @FXML
    Rectangle guestrect16;
    @FXML
    Rectangle guestrect17;
    @FXML
    Rectangle guestrect18;
    @FXML
    Rectangle guestrect19;
    @FXML
    Rectangle guestrect20;
    @FXML
    Rectangle hostslot0;
    @FXML
    Rectangle hostslot1;
    @FXML
    Rectangle hostslot2;
    @FXML
    Rectangle hostslot3;
    @FXML
    Rectangle hostslot4;
    @FXML
    Rectangle hostslot5;
    @FXML
    Rectangle guestslot0;
    @FXML
    Rectangle guestslot1;
    @FXML
    Rectangle guestslot2;
    @FXML
    Rectangle guestslot3;
    @FXML
    Rectangle guestslot4;
    @FXML
    Rectangle guestslot5;
    Rectangle [] hostCells;
    Rectangle [] guestCells;
    public static Game getGame() {
        return game;
    }
    public boolean [] selection;
    @FXML
    public void initialize(){
        root.getChildren().remove(hostComment);
        root.getChildren().remove(guestComment);
        if(!game.isHostTurn())
            turnSolver.setText("Now guest should play");
        hostUsername.setText("Host: "+ApplicationData.getHost().getNickname());
        guestUsername.setText("Guest: "+ApplicationData.getHost().getNickname());
        hosthp.setText("HP: "+ApplicationData.getHost().getHP());
        guesthp.setText("HP: "+ApplicationData.getGuest().getHP());
        selection=new boolean[6];
        for(int i=0;i<6;i++)
            selection[i]=false;
        hostCells=new Rectangle[21];
        hostCells[0]=hortrect0;
        hostCells[1]=hortrect1;
        hostCells[2]=hortrect2;
        hostCells[3]=hortrect3;
        hostCells[4]=hortrect4;
        hostCells[5]=hortrect5;
        hostCells[6]=hortrect6;
        hostCells[7]=hortrect7;
        hostCells[8]=hortrect8;
        hostCells[9]=hortrect9;
        hostCells[10]=hortrect10;
        hostCells[11]=hortrect11;
        hostCells[12]=hortrect12;
        hostCells[13]=hortrect13;
        hostCells[14]=hortrect14;
        hostCells[15]=hortrect15;
        hostCells[16]=hortrect16;
        hostCells[17]=hortrect17;
        hostCells[18]=hortrect18;
        hostCells[19]=hortrect19;
        hostCells[20]=hortrect20;
        guestCells=new Rectangle[21];
        guestCells[0]=guestrect0;
        guestCells[1]=guestrect1;
        guestCells[2]=guestrect2;
        guestCells[3]=guestrect3;
        guestCells[4]=guestrect4;
        guestCells[5]=guestrect5;
        guestCells[6]=guestrect6;
        guestCells[7]=guestrect7;
        guestCells[8]=guestrect8;
        guestCells[9]=guestrect9;
        guestCells[10]=guestrect10;
        guestCells[11]=guestrect11;
        guestCells[12]=guestrect12;
        guestCells[13]=guestrect13;
        guestCells[14]=guestrect14;
        guestCells[15]=guestrect15;
        guestCells[16]=guestrect16;
        guestCells[17]=guestrect17;
        guestCells[18]=guestrect18;
        guestCells[19]=guestrect19;
        guestCells[20]=guestrect20;


        for(int j=0;j<21;j++) {
            int finalJ1 = j;
            hostCells[j].setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    flag=true;
                    int r = 0;
                    while (!selection[r] && r < 6)
                        r++;
                    if (r == 6)
                        return;
                    try {
                        placeCard(r, finalJ1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            hostCells[j].setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    Dragboard db = dragEvent.getDragboard();
                    if (db.hasString()) {
                        dragEvent.acceptTransferModes( TransferMode.COPY_OR_MOVE);}
                }
            });

            int finalJ = j;
            hostCells[j].setOnDragEntered(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    if (!game.isHostTurn())
                        return;
                    flag=false;
                    int r = 0;
                    while (!selection[r] && r < 6)
                        r++;
                    if (r == 6)
                        return;
                    for (int i = finalJ; i < finalJ + game.getHostCardsAtHand().get(r).getDuration() && i < 21; i++) {
                        if (game.getHostRowStatus()[i].equals("nothing"))
                            hostCells[i].setFill(Paint.valueOf("#ebd834"));
                        else {
                            hostCells[i].setFill(Paint.valueOf("#cc1635"));
                        }
                    }
                }
            });

            hostCells[j].setOnDragExited(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    if (!game.isHostTurn() || flag)
                        return;
                    int r = 0;
                    while (!selection[r] && r < 6)
                        r++;
                    if (r == 6)
                        return;
                    for (int i = finalJ; i < finalJ + game.getHostCardsAtHand().get(r).getDuration() && i < 21; i++) {
                        hostCells[i].setFill(Paint.valueOf("#2a333b"));
                        if (game.getHostRowStatus()[i].equals("card")) {
                            //Image card ro bezar
                        }
                        if (game.getHostRowStatus()[i].equals("broken")) {
                            //image shekasteh
                        }
                        if (game.getHostRowStatus()[i].equals("hole")) {
                            //image hole
                        }
                    }
                }
            });
        }
        for(int j=0;j<21;j++) {
            int finalJ1 = j;
            guestCells[j].setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    flag=true;
                    int r = 0;
                    while (!selection[r] && r < 6)
                        r++;
                    if (r == 6)
                        return;
                    try {
                        placeCard(r, finalJ1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            guestCells[j].setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    Dragboard db = dragEvent.getDragboard();
                    if (db.hasString()) {
                        dragEvent.acceptTransferModes( TransferMode.COPY_OR_MOVE);}
                }
            });

            int finalJ = j;
            guestCells[j].setOnDragEntered(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    if (game.isHostTurn())
                        return;
                    flag=false;
                    int r = 0;
                    while (!selection[r] && r < 6)
                        r++;
                    if (r == 6)
                        return;
                    for (int i = finalJ; i < finalJ + game.getGuestCardsAtHand().get(r).getDuration() && i < 21; i++) {
                        if (game.getGuestRowStatus()[i].equals("nothing"))
                            guestCells[i].setFill(Paint.valueOf("#ebd834"));
                        else {
                            guestCells[i].setFill(Paint.valueOf("#cc1635"));
                        }
                    }
                }
            });

            guestCells[j].setOnDragExited(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    if (game.isHostTurn() || flag)
                        return;
                    int r = 0;
                    while (!selection[r] && r < 6)
                        r++;
                    if (r == 6)
                        return;
                    for (int i = finalJ; i < finalJ + game.getGuestCardsAtHand().get(r).getDuration() && i < 21; i++) {
                        guestCells[i].setFill(Paint.valueOf("#2a333b"));
                        if (game.getGuestRowStatus()[i].equals("card")) {
                            //Image card ro bezar
                        }
                        if (game.getGuestRowStatus()[i].equals("broken")) {
                            //image shekasteh
                        }
                        if (game.getGuestRowStatus()[i].equals("hole")) {
                            //image hole
                        }
                    }
                }
            });
        }
        hostslot0.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!game.isHostTurn())
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[0]=true;
                mouseEvent.consume();
            }
        });
        hostslot1.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!game.isHostTurn())
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[1]=true;
                mouseEvent.consume();
            }
        });
        hostslot2.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!game.isHostTurn())
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[2]=true;
                mouseEvent.consume();
            }
        });
        hostslot3.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!game.isHostTurn())
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[3]=true;
                mouseEvent.consume();
            }
        });
        hostslot4.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!game.isHostTurn())
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[4]=true;
                mouseEvent.consume();
            }
        });
        hostslot5.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!game.isHostTurn() || game.getHostCardsAtHand().size()<6)
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[5]=true;
                mouseEvent.consume();
            }
        });
        guestslot0.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(game.isHostTurn())
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[0]=true;
                mouseEvent.consume();
            }
        });
        guestslot1.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(game.isHostTurn())
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[1]=true;
                mouseEvent.consume();
            }
        });
        guestslot2.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(game.isHostTurn())
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[2]=true;
                mouseEvent.consume();
            }
        });
        guestslot3.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(game.isHostTurn())
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[3]=true;
                mouseEvent.consume();
            }
        });
        guestslot4.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(game.isHostTurn())
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[4]=true;
                mouseEvent.consume();
            }
        });
        guestslot5.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(game.isHostTurn() || game.getGuestCardsAtHand().size()<6)
                    return;
                Dragboard db= root.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                selection[5]=true;
                mouseEvent.consume();
            }
        });

//        for(int i=0;i<21;i++){
//        }
    }
    public static void setGame(Game game) {
        GameController2.game = game;
    }



    public void nextTurn(){
        if(game.isHostTurn()){
            game.setHostRemainingTurns(game.getHostRemainingTurns()-1);
            turnSolver.setText("Now guest should play");
        }
        if(!game.isHostTurn()){
            turnSolver.setText("Now guest should play");
            game.setGuestRemainingTurns(game.getGuestRemainingTurns()-1);
        }
        game.setHostTurn();
        if(game.getHostRemainingTurns()==0 && game.getGuestRemainingTurns()==0)
            createTimeline();
    }
    public static void createTimeline(){



    }

    public void placeCard(int cardnumber, int blocknumber) throws InterruptedException {
        Card i=null;
        if(game.isHostTurn()){
            i=game.getHostCardsAtHand().get(cardnumber);
        }
        if(!game.isHostTurn()){
            i=game.getGuestCardsAtHand().get(cardnumber);
        }
        if(i.isSpecial()){
            game.hitSpecialCards(i,blocknumber);
            return;
        }
        if(blocknumber+i.getDuration()>20){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Invalid spot for card placement");
            alert.setHeaderText("Invalid move!");
            alert.showAndWait();
            return;
        }
        if(game.isHostTurn()){
            for(int j=blocknumber;j<blocknumber+i.getDuration();j++){
                if(!game.getHostRowStatus(j).equals("nothing")){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Invalid spot for card placement");
                    alert.setHeaderText("Invalid move!");
                    alert.showAndWait();
                    return;
                }
            }
            for(int j=blocknumber;j<blocknumber+i.getDuration();j++) {
                game.setHostRowStatus("card", j);
                game.setHostRowCards(i, j);
                //image
            }
            if(game.getHostCardsAtHand().size()>5)
                game.getHostCardsAtHand().removeLast();
            game.removeCardFromHostCardsAtHand(i);
            game.addCardToHostCardsAtHand(game.randomCardReplace(true));
            checkBrakes();
            buffCardPossibly(i);
            nextTurn();
            if(checkPossibleBonusForHost());
                giveBonus(true);
        }
        if(!game.isHostTurn()){
            for(int j=blocknumber;j<blocknumber+i.getDuration();j++){
                if(!game.getGuestRowStatus(j).equals("nothing")){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Invalid spot for card placement");
                    alert.setHeaderText("Invalid move!");
                    alert.showAndWait();
                    return;
                }
            }
            for(int j=blocknumber;j<blocknumber+i.getDuration();j++) {
                game.setGuestRowStatus("card",j);
                game.setGuestRowCards(i,j);
                //image
            }
            if(game.getGuestCardsAtHand().size()>5)
                game.getGuestCardsAtHand().removeLast();
            game.removeCardFromGuestCardsAtHand(i);
            game.addCardToGuestCardsAtHand(game.randomCardReplace(false));
                    //image
            checkBrakes();
            buffCardPossibly(i);
            nextTurn();
            if(checkPossibleBonusForGuest());
             giveBonus(false);
        }
    }
    public void giveBonus(boolean forHost) throws InterruptedException {
        String str=turnSolver.getText();
        if(forHost){
            turnSolver.setText("Bonus activated for host!");
            Thread.sleep(1000);
        }
        if(!forHost){
            turnSolver.setText("Bonus activated for guest!");
            Thread.sleep(1000);
        }
        int a=ApplicationData.getRandom().nextInt(4);
        if(a==0 || a==1){
            turnSolver.setText("extra card this round!");
            turnSolver.setTextFill(Paint.valueOf("#f54290"));
            Thread.sleep(1000);
            turnSolver.setTextFill(Paint.valueOf("#000000"));
            turnSolver.setText(str);
            Card card=game.randomCardReplace(forHost);
            if(forHost)
                game.getHostCardsAtHand().add(card);
            else
                game.getGuestCardsAtHand().add(card);
        }
        if(a==2){
            turnSolver.setText("xp earned!");
            turnSolver.setTextFill(Paint.valueOf("#f54290"));
            Thread.sleep(1000);
            turnSolver.setTextFill(Paint.valueOf("#000000"));
            turnSolver.setText(str);
            if(forHost)
                ApplicationData.getHost().increaseXP(1000);
            if(!forHost)
                ApplicationData.getGuest().increaseXP(1000);
        }
        if(a==3){
            turnSolver.setText("coin earned!");
            turnSolver.setTextFill(Paint.valueOf("#f54290"));
            Thread.sleep(1000);
            turnSolver.setTextFill(Paint.valueOf("#000000"));
            turnSolver.setText(str);
            if(forHost)
                ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()+20);
            if(!forHost)
                ApplicationData.getGuest().setCoins(ApplicationData.getGuest().getCoins()+20);
        }
    }
    public void checkBrakes(){
        for(int i=0;i<21;i++){
            if(game.getGuestRowStatus()[i].equals("nothing") || game.getGuestRowStatus()[i].equals("hole") ){
                if(game.getHostRowStatus()[i].equals("broken"))
                    game.setHostRowStatus("card",i);
                //image
            }
            else if(game.getHostRowStatus()[i].equals("nothing") || game.getHostRowStatus()[i].equals("hole")){
                if(game.getGuestRowStatus()[i].equals("broken"))
                    game.setGuestRowStatus("card",i);
                //image
            }
            else{
                if(game.getHostRowCards()[i].getAccuracy() ==game.getGuestRowCards()[i].getAccuracy()){
                    game.setHostRowStatus("broken",i);
                    game.setGuestRowStatus("broken",i);
                    //image
                }
                if(game.getHostRowCards()[i].getAccuracy() > game.getGuestRowCards()[i].getAccuracy()){
                    game.setGuestRowStatus("broken",i);
                    game.setHostRowStatus("card",i);
                    //image
                }
                if(game.getHostRowCards()[i].getAccuracy() < game.getGuestRowCards()[i].getAccuracy()){
                    game.setGuestRowStatus("card",i);
                    game.setHostRowStatus("broken",i);
                    //image
                }
            }
        }
    }
    public void buffCardPossibly(Card card) throws InterruptedException {
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
            String str=turnSolver.getText();
            turnSolver.setText("Card Buffed!");
            Thread.sleep(2000);
            turnSolver.setText(str);
            card.setDamage(card.getDamage()+card.getDuration());
            card.setAccuracy(card.getAccuracy()+3);
        }
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


    public void dosth(MouseDragEvent mouseDragEvent) {
        for(int i=0;i<6;i++){
            selection[i]=false;
        }
    }


    public void goback(ActionEvent actionEvent) {


    }
    private static boolean numInArrayList(int a,ArrayList<Integer> arrlist){
        for(int i=0;i<arrlist.size();i++){
            if(a== arrlist.get(i))
                return true;
        }
        return false;
    }

    public void prop0(MouseEvent mouseEvent) {
        if(!commentOn){
            hostslot0.setLayoutY(hostslot0.getLayoutY()+10);
            comment1.setText("name:"+game.getHostCardsAtHand().get(0).getName()+"\ndur:"+game.getHostCardsAtHand().get(0).getDuration()
            +"\ndamage:"+game.getHostCardsAtHand().get(0).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(0).getAccuracy());
            root.getChildren().add(hostComment);
            commentOn=true;
        }
        else{
            hostslot0.setLayoutY(hostslot0.getLayoutY()-10);
            commentOn=false;
            root.getChildren().remove(hostComment);
            comment1.setText("");
        }
    }
    public void prop1(MouseEvent mouseEvent) {
        if(!commentOn){
            hostslot1.setLayoutY(hostslot1.getLayoutY()+10);
            comment1.setText("name:"+game.getHostCardsAtHand().get(1).getName()+"\ndur:"+game.getHostCardsAtHand().get(1).getDuration()
                    +"\ndamage:"+game.getHostCardsAtHand().get(1).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(1).getAccuracy());
            root.getChildren().remove(hostComment);
            commentOn=true;
        }
        else{
            hostslot1.setLayoutY(hostslot1.getLayoutY()-10);
            commentOn=false;
            root.getChildren().remove(hostComment);
            comment1.setText("");
        }
    }
    public void prop2(MouseEvent mouseEvent) {
        if(!commentOn){
            hostslot2.setLayoutY(hostslot2.getLayoutY()+10);
            comment1.setText("name:"+game.getHostCardsAtHand().get(2).getName()+"\ndur:"+game.getHostCardsAtHand().get(2).getDuration()
                    +"\ndamage:"+game.getHostCardsAtHand().get(2).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(2).getAccuracy());
            root.getChildren().remove(hostComment);
            commentOn=true;
        }
        else{
            hostslot2.setLayoutY(hostslot2.getLayoutY()-10);
            commentOn=false;
            root.getChildren().remove(hostComment);
            comment1.setText("");
        }
    }
    public void prop3(MouseEvent mouseEvent) {
        if(!commentOn){
            hostslot3.setLayoutY(hostslot3.getLayoutY()+10);
            comment1.setText("name:"+game.getHostCardsAtHand().get(3).getName()+"\ndur:"+game.getHostCardsAtHand().get(3).getDuration()
                    +"\ndamage:"+game.getHostCardsAtHand().get(3).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(3).getAccuracy());
            root.getChildren().add(hostComment);
            commentOn=true;
        }
        else{
            hostslot0.setLayoutY(hostslot3.getLayoutY()-10);
            commentOn=false;
            root.getChildren().remove(hostComment);
            comment1.setText("");
        }
    }
    public void prop4(MouseEvent mouseEvent) {
        if(!commentOn){
            hostslot0.setLayoutY(hostslot4.getLayoutY()+10);
            comment1.setText("name:"+game.getHostCardsAtHand().get(4).getName()+"\ndur:"+game.getHostCardsAtHand().get(4).getDuration()
                    +"\ndamage:"+game.getHostCardsAtHand().get(4).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(4).getAccuracy());
            root.getChildren().add(hostComment);
            commentOn=true;
        }
        else{
            hostslot0.setLayoutY(hostslot4.getLayoutY()-10);
            commentOn=false;
            root.getChildren().remove(hostComment);
            comment1.setText("");
        }
    }
    public void prop5(MouseEvent mouseEvent) {
        if(game.getHostCardsAtHand().size()<6)
            return;
        if(!commentOn){
            hostslot5.setLayoutY(hostslot5.getLayoutY()+10);
            comment1.setText("name:"+game.getHostCardsAtHand().get(5).getName()+"\ndur:"+game.getHostCardsAtHand().get(5).getDuration()
                    +"\ndamage:"+game.getHostCardsAtHand().get(5).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(5).getAccuracy());
            root.getChildren().add(hostComment);
            commentOn=true;
        }
        else{
            hostslot5.setLayoutY(hostslot5.getLayoutY()-10);
            commentOn=false;
            root.getChildren().remove(hostComment);
            comment1.setText("");
        }
    }
    public void prop6(MouseEvent mouseEvent) {
        if(!commentOn){
            guestslot0.setLayoutY(guestslot0.getLayoutY()-10);
            comment2.setText("name:"+game.getGuestCardsAtHand().get(0).getName()+"\ndur:"+game.getGuestCardsAtHand().get(0).getDuration()
                    +"\ndamage:"+game.getGuestCardsAtHand().get(0).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(0).getAccuracy());
            root.getChildren().add(guestComment);
            commentOn=true;
        }
        else{
            guestslot0.setLayoutY(guestslot0.getLayoutY()+10);
            commentOn=false;
            root.getChildren().remove(guestComment);
            comment2.setText("");
        }
    }
    public void prop7(MouseEvent mouseEvent) {
        if(!commentOn){
            guestslot1.setLayoutY(guestslot1.getLayoutY()-10);
            comment2.setText("name:"+game.getGuestCardsAtHand().get(1).getName()+"\ndur:"+game.getGuestCardsAtHand().get(1).getDuration()
                    +"\ndamage:"+game.getGuestCardsAtHand().get(1).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(1).getAccuracy());
            root.getChildren().add(guestComment);
            commentOn=true;
        }
        else{
            guestslot1.setLayoutY(guestslot1.getLayoutY()+10);
            commentOn=false;
            root.getChildren().remove(guestComment);
            comment2.setText("");
        }
    }
    public void prop8(MouseEvent mouseEvent) {
        if(!commentOn){
            guestslot2.setLayoutY(guestslot2.getLayoutY()-10);
            comment2.setText("name:"+game.getGuestCardsAtHand().get(0).getName()+"\ndur:"+game.getGuestCardsAtHand().get(0).getDuration()
                    +"\ndamage:"+game.getGuestCardsAtHand().get(0).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(0).getAccuracy());
            root.getChildren().add(guestComment);
            commentOn=true;
        }
        else{
            guestslot2.setLayoutY(guestslot2.getLayoutY()+10);
            commentOn=false;
            root.getChildren().remove(guestComment);
            comment2.setText("");
        }
    }
    public void prop9(MouseEvent mouseEvent) {
        if(!commentOn){
            guestslot3.setLayoutY(guestslot3.getLayoutY()-10);
            comment2.setText("name:"+game.getGuestCardsAtHand().get(3).getName()+"\ndur:"+game.getGuestCardsAtHand().get(3).getDuration()
                    +"\ndamage:"+game.getGuestCardsAtHand().get(3).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(3).getAccuracy());
            root.getChildren().add(guestComment);
            commentOn=true;
        }
        else{
            guestslot3.setLayoutY(guestslot3.getLayoutY()+10);
            commentOn=false;
            root.getChildren().remove(guestComment);
            comment2.setText("");
        }
    }
    public void prop10(MouseEvent mouseEvent) {
        if(!commentOn){
            guestslot4.setLayoutY(guestslot4.getLayoutY()-10);
            comment2.setText("name:"+game.getGuestCardsAtHand().get(4).getName()+"\ndur:"+game.getGuestCardsAtHand().get(4).getDuration()
                    +"\ndamage:"+game.getGuestCardsAtHand().get(4).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(4).getAccuracy());
            root.getChildren().add(guestComment);
            commentOn=true;
        }
        else{
            guestslot4.setLayoutY(guestslot4.getLayoutY()+10);
            commentOn=false;
            root.getChildren().remove(guestComment);
            comment2.setText("");
        }
    }
    public void prop11(MouseEvent mouseEvent) {
        if(game.getGuestCardsAtHand().size()<6)
            return;
        if(!commentOn){
            guestslot5.setLayoutY(guestslot5.getLayoutY()-10);
            comment2.setText("name:"+game.getGuestCardsAtHand().get(5).getName()+"\ndur:"+game.getGuestCardsAtHand().get(5).getDuration()
                    +"\ndamage:"+game.getGuestCardsAtHand().get(5).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(5).getAccuracy());
            root.getChildren().add(guestComment);
            commentOn=true;
        }
        else{
            guestslot5.setLayoutY(guestslot5.getLayoutY()+10);
            commentOn=false;
            root.getChildren().remove(guestComment);
            comment2.setText("");
        }
    }

}
