package Controller;

import Model.*;
import ViewGraphic.Animation.*;
import ViewGraphic.EndGame;
import ViewGraphic.LoginMenu;
import ViewGraphic.MainMenu;
import ViewGraphic.SettingMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GameController2 {
    @FXML
    public javafx.scene.control.TextField copiertext;
    public Button copierButton;
    public Label hostchar;
    public Label guestchar;
    javafx.scene.image.Image hole =new javafx.scene.image.Image(String.valueOf(LoginMenu.class.getResource("/Media/Images/hole.png")));
    javafx.scene.image.Image hider =new javafx.scene.image.Image(String.valueOf(LoginMenu.class.getResource("/Media/Images/Cards/question.jpg")));

    private static Game game;
    private boolean hidden;
    Bar bar;
    boolean flag=false;
    boolean commentOn=false;
    private boolean moveMade=false;
    @FXML
    public Label hostTurnNum;
    @FXML
    public Label guestTurnNum;
    @FXML
    public Rectangle hostComment;
    @FXML
    public Rectangle guestComment;
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
        hostchar.setText(ApplicationData.getHost().getCharacter());
        guestchar.setText(ApplicationData.getGuest().getCharacter());
        hostchar.setTextFill(Paint.valueOf("#b42f2f"));
        guestchar.setTextFill(Paint.valueOf("#b42f2f"));
        ApplicationData.getGameGraphic().setController(this);
        bar=new Bar();
        bar.setLayoutX(1);
        bar.setLayoutY(120);
        root.getChildren().add(bar);
        hostTurnNum.toFront();
        guestTurnNum.toFront();
        root.getChildren().remove(copiertext);
        root.getChildren().remove(copierButton);
        setGame(ApplicationData.getGame());
        root.getChildren().remove(hostComment);
        root.getChildren().remove(guestComment);
        if(!game.isHostTurn())
            turnSolver.setText("Now guest should play");
        hostUsername.setText("Host: "+ApplicationData.getHost().getNickname());
        guestUsername.setText("Guest: "+ApplicationData.getGuest().getNickname());
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

        hostslot0.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(0).getName())));
        hostslot1.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(1).getName())));
        hostslot2.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(2).getName())));
        hostslot3.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(3).getName())));
        hostslot4.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(4).getName())));
        guestslot0.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(0).getName())));
        guestslot1.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(1).getName())));
        guestslot2.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(2).getName())));
        guestslot3.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(3).getName())));
        guestslot4.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(4).getName())));



        for(int j=0;j<21;j++) {
            if(game.getHostRowStatus()[j].equals("hole"))
                hostCells[j].setFill(new ImagePattern(hole));
            if(game.getGuestRowStatus()[j].equals("hole"))
                guestCells[j].setFill(new ImagePattern(hole));
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
                        moveMade=false;
                        placeCard(r, finalJ1);
                        for(Card card:game.getHostCardsAtHand()){
                            System.out.printf(card.getName()+'\t');
                        }
                        System.out.println();
                        for(int i=0;i<21;i++){
                            if(game.getHostRowStatus()[i].equals("hole") || game.getHostRowStatus()[i].equals("nothing"))
                                System.out.printf(game.getHostRowStatus()[i]+'\t');
                            if(game.getHostRowStatus()[i].equals("broken") || game.getHostRowStatus()[i].equals("card"))
                                System.out.printf(game.getHostRowCards()[i].getName()+'\t');
                        }
                        System.out.println();
                        for(int i=0;i<21;i++){
                            if(game.getGuestRowStatus()[i].equals("hole") || game.getGuestRowStatus()[i].equals("nothing"))
                                System.out.printf(game.getGuestRowStatus()[i]+'\t');
                            if(game.getGuestRowStatus()[i].equals("broken") || game.getGuestRowStatus()[i].equals("card"))
                                System.out.printf(game.getGuestRowCards()[i].getName()+'\t');
                        }
                        System.out.println();
                        for(Card card:game.getGuestCardsAtHand()){
                            System.out.printf(card.getName()+'\t');
                        }
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
                            hostCells[i].setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostRowCards()[i].getName())));
                        }
                        if (game.getHostRowStatus()[i].equals("broken")) {
                            hostCells[i].setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostRowCards()[i].getName())));

                            //image shekasteh
                        }
                        if (game.getHostRowStatus()[i].equals("hole")) {
                            hostCells[finalJ].setFill(new ImagePattern(hole));
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
                        moveMade=false;
                        placeCard(r, finalJ1);
                        for(Card card:game.getHostCardsAtHand()){
                            System.out.printf(card.getName()+'\t');
                        }
                        System.out.println();
                        for(int i=0;i<21;i++){
                            if(game.getHostRowStatus()[i].equals("hole") || game.getHostRowStatus()[i].equals("nothing"))
                                System.out.printf(game.getHostRowStatus()[i]+'\t');
                            if(game.getHostRowStatus()[i].equals("broken") || game.getHostRowStatus()[i].equals("card"))
                                System.out.printf(game.getHostRowCards()[i].getName()+'\t');
                        }
                        System.out.println();
                        for(int i=0;i<21;i++){
                            if(game.getGuestRowStatus()[i].equals("hole") || game.getGuestRowStatus()[i].equals("nothing"))
                                System.out.printf(game.getGuestRowStatus()[i]+'\t');
                            if(game.getGuestRowStatus()[i].equals("broken") || game.getGuestRowStatus()[i].equals("card"))
                                System.out.printf(game.getGuestRowCards()[i].getName()+'\t');
                        }
                        System.out.println();
                        for(Card card:game.getGuestCardsAtHand()){
                            System.out.printf(card.getName()+'\t');
                        }
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
                            guestCells[i].setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestRowCards()[i].getName())));

                            //Image card ro bezar
                        }
                        if (game.getGuestRowStatus()[i].equals("broken")) {
                            guestCells[i].setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestRowCards()[i].getName())));
                            //image shekasteh
                        }
                        if (game.getGuestRowStatus()[i].equals("hole")) {
                            guestCells[finalJ].setFill(new ImagePattern(hole));
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
        hidden=false;
        if(game.isHostTurn()){
            game.setHostRemainingTurns(game.getHostRemainingTurns()-1);
            int a=Integer.parseInt(hostTurnNum.getText())-1;
            hostTurnNum.setText(String.valueOf(a));
            turnSolver.setText("Now guest should play");
            turnSolver.setTextFill(Paint.valueOf("#ffad09"));
        }
        if(!game.isHostTurn()){
            turnSolver.setText("Now host should play");
            turnSolver.setTextFill(Paint.valueOf("#ffad09"));
            game.setGuestRemainingTurns(game.getGuestRemainingTurns()-1);
            int a=Integer.parseInt(guestTurnNum.getText())-1;
            guestTurnNum.setText(String.valueOf(a));
        }
        game.setHostTurn();
        for(int i=0;i<21;i++){
            if(game.getHostRowStatus()[i].equals("card") || game.getHostRowStatus()[i].equals("broken")){
                hostCells[i].setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostRowCards()[i].getName())));
            }
            if(game.getGuestRowStatus()[i].equals("card") || game.getGuestRowStatus()[i].equals("broken")){
                guestCells[i].setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestRowCards()[i].getName())));
            }
            if(game.getGuestRowStatus()[i].equals("hole"))
                guestCells[i].setFill(new ImagePattern(hole));
            if(game.getHostRowStatus()[i].equals("hole"))
                hostCells[i].setFill(new ImagePattern(hole));
            if(game.getGuestRowStatus()[i].equals("nothing"))
                guestCells[i].setFill(Paint.valueOf("#2a333b"));
            if(game.getHostRowStatus()[i].equals("nothing"))
                hostCells[i].setFill(Paint.valueOf("#2a333b"));

            hostslot0.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(0).getName())));
            hostslot1.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(1).getName())));
            hostslot2.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(2).getName())));
            hostslot3.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(3).getName())));
            if(game.getHostCardsAtHand().size()>4)
               hostslot4.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(4).getName())));
            if(game.getHostCardsAtHand().size()<=4)
                hostslot4.setFill(Paint.valueOf("#2a333b"));
            if(game.getHostCardsAtHand().size()<=5)
                hostslot5.setFill(Paint.valueOf("#2a333b"));
            if(game.getHostCardsAtHand().size()>5)
                hostslot5.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(5).getName())));
            guestslot0.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(0).getName())));
            guestslot1.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(1).getName())));
            guestslot2.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(2).getName())));
            guestslot3.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(3).getName())));
            if(game.getGuestCardsAtHand().size()>4)
                guestslot4.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(4).getName())));
            if(game.getGuestCardsAtHand().size()<=4)
                guestslot4.setFill(Paint.valueOf("#2a333b"));
            if(game.getGuestCardsAtHand().size()<=5)
                guestslot5.setFill(Paint.valueOf("#2a333b"));
            if(game.getGuestCardsAtHand().size()>5)
                guestslot5.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(5).getName())));


        }
        if(game.getHostRemainingTurns()==0 && game.getGuestRemainingTurns()==0)
            createTimeline();
    }
    public void createTimeline(){
        System.out.println("time line created");
        bar.setMovingBarAnimation(new MovingBarAnimation(root,bar,game));
        bar.getMovingBarAnimation().play();
        bar.requestFocus();
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
            if(i.getName().equalsIgnoreCase("holeremover") || i.getName().equalsIgnoreCase("holechanger")){
                if(game.isHostTurn() && !game.getHostRowStatus()[blocknumber].equalsIgnoreCase("hole")){
                    String str=turnSolver.getText();
                    turnSolver.setText("select a hole to alter");
                    Thread.sleep(1000);
                    turnSolver.setText(str);
                    turnSolver.setTextFill(Paint.valueOf("#ffad09"));
                    moveMade=true;
                }
                if(!game.isHostTurn() && !game.getGuestRowStatus()[blocknumber].equalsIgnoreCase("hole")){
                    String str=turnSolver.getText();
                    turnSolver.setText("select a hole to alter");
                    Thread.sleep(1000);
                    turnSolver.setText(str);
                    turnSolver.setTextFill(Paint.valueOf("#ffad09"));
                    moveMade=true;
                }

            }
            if(!moveMade && i.getName().equalsIgnoreCase("roundDec")){
                nextTurn();
                nextTurn();
                String str=turnSolver.getText();
                turnSolver.setText("Rounds decreased");
                Thread.sleep(1000);
                turnSolver.setText(str);
                turnSolver.setTextFill(Paint.valueOf("#ffad09"));
                moveMade=true;
            }
            if(!moveMade && i.getName().equalsIgnoreCase("cardcopier")){
                root.getChildren().add(copierButton);
                root.getChildren().add(copiertext);
                moveMade=true;
            }
            if(!moveMade && i.getName().equalsIgnoreCase("cardathandhider")){
                nextTurn();
                int a=2;
                if(game.isHostTurn())
                    a=1;
                cardsAtHandHider(a);
                moveMade=true;
            }
            if(!moveMade){
                game.hitSpecialCards(i,blocknumber);
                ApplicationData.getGameGraphic().getController().hosthp.setText("HP:"+ApplicationData.getHost().getHP());
                ApplicationData.getGameGraphic().getController().guesthp.setText("HP:"+ApplicationData.getGuest().getHP());
                moveMade=true;
                nextTurn();
            }
            moveMade=true;

        }
        if(blocknumber+i.getDuration()>21){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Invalid spot for card placement");
            alert.setHeaderText("Invalid move!");
            alert.showAndWait();
            System.out.println("got here 1");
            moveMade=true;
        }
        if(game.isHostTurn() && !moveMade){
            for(int j=blocknumber;j<blocknumber+i.getDuration() && !moveMade;j++){
                if(!game.getHostRowStatus()[j].equals("nothing")){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Invalid spot for card placement");
                    alert.setHeaderText("Invalid move!");
                    alert.showAndWait();
                    for(int t=blocknumber;t<blocknumber+i.getDuration();t++){
                        if(game.getHostRowStatus()[t].equals("hole"))
                            hostCells[t].setFill(new ImagePattern(hole));
                        if(game.getHostRowStatus()[t].equals("nothing"))
                            hostCells[t].setFill(Paint.valueOf("#2a333b"));
                        if(game.getHostRowStatus()[t].equals("card") || game.getHostRowStatus()[t].equals("broken"))
                            hostCells[t].setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostRowCards()[t].getName())));
                    }
                    System.out.println("got here 2");
                     moveMade=true;
                }
            }
            if(!moveMade){
                for(int j=blocknumber;j<blocknumber+i.getDuration();j++) {
                    game.setHostRowStatus("card", j);
                    game.setHostRowCards(i, j);
                    hostCells[j].setFill(new ImagePattern(ApplicationData.getCardsImage().get(i.getName())));
                    //image
                }
                int e=0;
                while (game.getHostCardsAtHand().get(e).getName().equals(i.getName()))
                    e++;
                if(e!=5 && game.getHostCardsAtHand().size()>5){
                    game.getHostCardsAtHand().removeLast();
                    hostslot5.setFill(Paint.valueOf("#2a333b"));
                }
                game.removeCardFromHostCardsAtHand(i);
                game.addCardToHostCardsAtHand(game.randomCardReplace(true));
                checkBrakes();
                buffCardPossibly(i);
                if(checkPossibleBonusForHost())
                    giveBonus(true);
                System.out.println("got here 3");
                moveMade=true;
                nextTurn();
            }
        }
        if(!game.isHostTurn() && !moveMade){
            for(int j=blocknumber;j<blocknumber+i.getDuration() && !moveMade;j++){
                if(!game.getGuestRowStatus()[j].equals("nothing")){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Invalid spot for card placement");
                    alert.setHeaderText("Invalid move!");
                    alert.showAndWait();
                    for(int t=blocknumber;t<blocknumber+i.getDuration();t++){
                        if(game.getGuestRowStatus()[t].equals("hole"))
                            guestCells[t].setFill(new ImagePattern(hole));
                        if(game.getGuestRowStatus()[t].equals("nothing"))
                            guestCells[t].setFill(Paint.valueOf("#2a333b"));
                        if(game.getGuestRowStatus()[t].equals("card") || game.getGuestRowStatus()[t].equals("broken"))
                            guestCells[t].setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestRowCards()[t].getName())));
                    }
                    System.out.println("got here 4");
                    moveMade=true;
                }
            }
            if(!moveMade){
                for(int j=blocknumber;j<blocknumber+i.getDuration();j++) {
                    game.setGuestRowStatus("card",j);
                    game.setGuestRowCards(i,j);
                    guestCells[j].setFill(new ImagePattern(ApplicationData.getCardsImage().get(i.getName())));
                    //image
                }
                int e=0;
                while (game.getGuestCardsAtHand().get(e).getName().equals(i.getName()))
                    e++;
                if(e!=5 && game.getGuestCardsAtHand().size()>5){
                    game.getGuestCardsAtHand().removeLast();
                    guestslot5.setFill(Paint.valueOf("#2a333b"));
                }
                game.removeCardFromGuestCardsAtHand(i);
                game.addCardToGuestCardsAtHand(game.randomCardReplace(false));
                //image
                checkBrakes();
                buffCardPossibly(i);
                if(checkPossibleBonusForGuest())
                    giveBonus(false);
                System.out.println("got here 5");
                moveMade=true;
                nextTurn();
                System.out.println(game.isHostTurn());
            }
        }
//        if(!game.isHostTurn() && !moveMade){
//            for(int j=blocknumber;j<blocknumber+i.getDuration() && !moveMade;j++){
//                if(!game.getGuestRowStatus()[j].equals("nothing")){
//                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
//                    alert.setContentText("Invalid spot for card placement");
//                    alert.setHeaderText("Invalid move!");
//                    alert.showAndWait();
//                    for(int t=blocknumber;t<blocknumber+i.getDuration();t++){
//                        if(game.getGuestRowStatus()[t].equals("hole"))
//                            guestCells[t].setFill(new ImagePattern(hole));
//                        if(game.getGuestRowStatus()[t].equals("nothing"))
//                            guestCells[t].setFill(Paint.valueOf("#2a333b"));
//                        if(game.getGuestRowStatus()[t].equals("card") || game.getGuestRowStatus()[t].equals("broken"))
//                            guestCells[t].setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestRowCards()[t].getName())));
//                    }
//                    System.out.println("got here 4");
//                    moveMade=true;
//                }
//            }
//            if(!moveMade){
//                for(int j=blocknumber;j<blocknumber+i.getDuration();j++) {
//                    game.setGuestRowStatus("card",j);
//                    game.setGuestRowCards(i,j);
//                    guestCells[j].setFill(new ImagePattern(ApplicationData.getCardsImage().get(i.getName())));
//                    //image
//                }
//                int e=0;
//                while (game.getGuestCardsAtHand().get(e).getName().equals(i.getName()))
//                    e++;
//                if(e!=5 && game.getGuestCardsAtHand().size()>5){
//                    game.getGuestCardsAtHand().removeLast();
//                    hostslot5.setFill(Paint.valueOf("#2a333b"));
//                }
//                game.removeCardFromGuestCardsAtHand(i);
//                game.addCardToGuestCardsAtHand(game.randomCardReplace(false));
//                //image
//                checkBrakes();
//                buffCardPossibly(i);
//                if(checkPossibleBonusForGuest())
//                    giveBonus(false);
//                System.out.println("got here 5");
//                moveMade=true;
//                nextTurn();
//            }
//        }
        if(!moveMade)
            System.out.println("got here 6");
    }
    public void giveBonus(boolean forHost) throws InterruptedException {
        String str=turnSolver.getText();
        if(forHost){
            turnSolver.setText("Bonus activated for host!");
            turnSolver.setTextFill(Paint.valueOf("#ffad09"));
            Thread.sleep(1000);
        }
        if(!forHost){
            turnSolver.setText("Bonus activated for guest!");
            turnSolver.setTextFill(Paint.valueOf("#ffad09"));
            Thread.sleep(1000);
        }
        int a=ApplicationData.getRandom().nextInt(4);
        if(a==0 || a==1){
            turnSolver.setText("extra card this round!");
            turnSolver.setTextFill(Paint.valueOf("#f54290"));
            Thread.sleep(1000);
            turnSolver.setTextFill(Paint.valueOf("#000000"));
            turnSolver.setText(str);
            turnSolver.setTextFill(Paint.valueOf("#ffad09"));
            Card card=game.randomCardReplace(forHost);
            if(forHost){
                game.getHostCardsAtHand().add(card);
                hostslot5.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(5).getName())));
            }
            else{
                game.getGuestCardsAtHand().add(card);
                guestslot5.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(5).getName())));
            }
        }
        if(a==2){
            turnSolver.setText("xp earned!");
            turnSolver.setTextFill(Paint.valueOf("#f54290"));
            Thread.sleep(1000);
            turnSolver.setTextFill(Paint.valueOf("#000000"));
            turnSolver.setText(str);
            turnSolver.setTextFill(Paint.valueOf("#ffad09"));
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
            turnSolver.setTextFill(Paint.valueOf("#ffad09"));
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
            turnSolver.setTextFill(Paint.valueOf("#ffad09"));
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
        if(!hidden){
            if(!commentOn){
                hostslot0.setLayoutY(hostslot0.getLayoutY()+10);
                root.getChildren().add(hostComment);
                comment1.setText("name:"+game.getHostCardsAtHand().get(0).getName()+"\ndur:"+game.getHostCardsAtHand().get(0).getDuration()
                        +"\ndamage:"+game.getHostCardsAtHand().get(0).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(0).getAccuracy()+"\n char:"+game.getHostCardsAtHand().get(0).getCharacter());
                commentOn=true;
                comment1.toFront();
            }
            else{
                hostslot0.setLayoutY(hostslot0.getLayoutY()-10);
                commentOn=false;
                root.getChildren().remove(hostComment);
                comment1.setText("");
            }
        }
    }
    public void prop1(MouseEvent mouseEvent) {
        if(!hidden){
            if(!commentOn){
                hostslot1.setLayoutY(hostslot1.getLayoutY()+10);
                root.getChildren().add(hostComment);
                comment1.setText("name:"+game.getHostCardsAtHand().get(1).getName()+"\ndur:"+game.getHostCardsAtHand().get(1).getDuration()
                        +"\ndamage:"+game.getHostCardsAtHand().get(1).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(1).getAccuracy()+"\n char:"+game.getHostCardsAtHand().get(1).getCharacter());
                commentOn=true;
                comment1.toFront();
            }
            else{
                hostslot1.setLayoutY(hostslot1.getLayoutY()-10);
                commentOn=false;
                root.getChildren().remove(hostComment);
                comment1.setText("");
            }
        }
    }
    public void prop2(MouseEvent mouseEvent) {
        if(!hidden){
            if(!commentOn){
                hostslot2.setLayoutY(hostslot2.getLayoutY()+10);
                root.getChildren().add(hostComment);
                comment1.setText("name:"+game.getHostCardsAtHand().get(2).getName()+"\ndur:"+game.getHostCardsAtHand().get(2).getDuration()
                        +"\ndamage:"+game.getHostCardsAtHand().get(2).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(2).getAccuracy()+"\n char:"+game.getHostCardsAtHand().get(2).getCharacter());
                commentOn=true;
                comment1.toFront();
            }
            else{
                hostslot2.setLayoutY(hostslot2.getLayoutY()-10);
                commentOn=false;
                root.getChildren().remove(hostComment);
                comment1.setText("");
            }
        }
    }
    public void prop3(MouseEvent mouseEvent) {
        if(!hidden){
            if(!commentOn){
                hostslot3.setLayoutY(hostslot3.getLayoutY()+10);
                root.getChildren().add(hostComment);
                comment1.setText("name:"+game.getHostCardsAtHand().get(3).getName()+"\ndur:"+game.getHostCardsAtHand().get(3).getDuration()
                        +"\ndamage:"+game.getHostCardsAtHand().get(3).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(3).getAccuracy()+"\n char:"+game.getHostCardsAtHand().get(3).getCharacter());
                commentOn=true;
                comment1.toFront();
            }
            else{
                hostslot3.setLayoutY(hostslot3.getLayoutY()-10);
                commentOn=false;
                root.getChildren().remove(hostComment);
                comment1.setText("");
            }
        }
    }
    public void prop4(MouseEvent mouseEvent) {
        if(!hidden){
            if(!commentOn){
                hostslot4.setLayoutY(hostslot4.getLayoutY()+10);
                root.getChildren().add(hostComment);
                comment1.setText("name:"+game.getHostCardsAtHand().get(4).getName()+"\ndur:"+game.getHostCardsAtHand().get(4).getDuration()
                        +"\ndamage:"+game.getHostCardsAtHand().get(4).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(4).getAccuracy()+"\n char:"+game.getHostCardsAtHand().get(4).getCharacter());
                commentOn=true;
                comment1.toFront();
            }
            else{
                hostslot4.setLayoutY(hostslot4.getLayoutY()-10);
                commentOn=false;
                root.getChildren().remove(hostComment);
                comment1.setText("");
            }
        }
    }
    public void prop5(MouseEvent mouseEvent) {
        if(game.getHostCardsAtHand().size()<6)
            return;
        if(!hidden){
            if(!commentOn){
                hostslot5.setLayoutY(hostslot5.getLayoutY()+10);
                root.getChildren().add(hostComment);
                comment1.setText("name:"+game.getHostCardsAtHand().get(5).getName()+"\ndur:"+game.getHostCardsAtHand().get(5).getDuration()
                        +"\ndamage:"+game.getHostCardsAtHand().get(5).getDamage()+"\nacc:"+game.getHostCardsAtHand().get(5).getAccuracy()+"\n char:"+game.getHostCardsAtHand().get(5).getCharacter());
                commentOn=true;
                comment1.toFront();
            }
            else{
                hostslot5.setLayoutY(hostslot5.getLayoutY()-10);
                commentOn=false;
                root.getChildren().remove(hostComment);
                comment1.setText("");
            }
        }
    }
    public void prop6(MouseEvent mouseEvent) {
        if(!hidden){
            if(!commentOn){
                guestslot0.setLayoutY(guestslot0.getLayoutY()-10);
                root.getChildren().add(guestComment);
                comment2.setText("name:"+game.getGuestCardsAtHand().get(0).getName()+"\ndur:"+game.getGuestCardsAtHand().get(0).getDuration()
                        +"\ndamage:"+game.getGuestCardsAtHand().get(0).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(0).getAccuracy()+"\n char:"+game.getGuestCardsAtHand().get(0).getCharacter());
                commentOn=true;
                comment2.toFront();
            }
            else{
                guestslot0.setLayoutY(guestslot0.getLayoutY()+10);
                commentOn=false;
                root.getChildren().remove(guestComment);
                comment2.setText("");
            }
        }
    }
    public void prop7(MouseEvent mouseEvent) {
        if(!hidden){
            if(!commentOn){
                guestslot1.setLayoutY(guestslot1.getLayoutY()-10);
                root.getChildren().add(guestComment);
                comment2.setText("name:"+game.getGuestCardsAtHand().get(1).getName()+"\ndur:"+game.getGuestCardsAtHand().get(1).getDuration()
                        +"\ndamage:"+game.getGuestCardsAtHand().get(1).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(1).getAccuracy()+"\n char:"+game.getGuestCardsAtHand().get(1).getCharacter());
                commentOn=true;
                comment2.toFront();
            }
            else{
                guestslot1.setLayoutY(guestslot1.getLayoutY()+10);
                commentOn=false;
                root.getChildren().remove(guestComment);
                comment2.setText("");
            }
        }
    }
    public void prop8(MouseEvent mouseEvent) {
        if(!hidden){
            if(!commentOn){
                guestslot2.setLayoutY(guestslot2.getLayoutY()-10);
                root.getChildren().add(guestComment);
                comment2.setText("name:"+game.getGuestCardsAtHand().get(2).getName()+"\ndur:"+game.getGuestCardsAtHand().get(2).getDuration()
                        +"\ndamage:"+game.getGuestCardsAtHand().get(2).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(2).getAccuracy()+"\n char:"+game.getGuestCardsAtHand().get(0).getCharacter());
                commentOn=true;
                comment2.toFront();
            }
            else{
                guestslot2.setLayoutY(guestslot2.getLayoutY()+10);
                commentOn=false;
                root.getChildren().remove(guestComment);
                comment2.setText("");
            }
        }
    }
    public void prop9(MouseEvent mouseEvent) {
        if(!hidden){
            if(!commentOn){
                guestslot3.setLayoutY(guestslot3.getLayoutY()-10);
                root.getChildren().add(guestComment);
                comment2.setText("name:"+game.getGuestCardsAtHand().get(3).getName()+"\ndur:"+game.getGuestCardsAtHand().get(3).getDuration()
                        +"\ndamage:"+game.getGuestCardsAtHand().get(3).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(3).getAccuracy()+"\n char:"+game.getGuestCardsAtHand().get(3).getCharacter());
                commentOn=true;
                comment2.toFront();
            }
            else{
                guestslot3.setLayoutY(guestslot3.getLayoutY()+10);
                commentOn=false;
                root.getChildren().remove(guestComment);
                comment2.setText("");
            }
        }
    }
    public void prop10(MouseEvent mouseEvent) {
        if(!hidden){
            if(!commentOn){
                guestslot4.setLayoutY(guestslot4.getLayoutY()-10);
                root.getChildren().add(guestComment);
                comment2.setText("name:"+game.getGuestCardsAtHand().get(4).getName()+"\ndur:"+game.getGuestCardsAtHand().get(4).getDuration()
                        +"\ndamage:"+game.getGuestCardsAtHand().get(4).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(4).getAccuracy()+"\n char:"+game.getGuestCardsAtHand().get(4).getCharacter());
                commentOn=true;
                comment2.toFront();
            }
            else{
                guestslot4.setLayoutY(guestslot4.getLayoutY()+10);
                commentOn=false;
                root.getChildren().remove(guestComment);
                comment2.setText("");
            }
        }
    }
    public void prop11(MouseEvent mouseEvent) {
        if(game.getGuestCardsAtHand().size()<6)
            return;
        if(!hidden){
            if(!commentOn){
                guestslot5.setLayoutY(guestslot5.getLayoutY()-10);
                root.getChildren().add(guestComment);
                comment2.setText("name:"+game.getGuestCardsAtHand().get(5).getName()+"\ndur:"+game.getGuestCardsAtHand().get(5).getDuration()
                        +"\ndamage:"+game.getGuestCardsAtHand().get(5).getDamage()+"\nacc:"+game.getGuestCardsAtHand().get(5).getAccuracy()+"\n char:"+game.getGuestCardsAtHand().get(5).getCharacter());
                commentOn=true;
                comment2.toFront();
            }
            else{
                guestslot5.setLayoutY(guestslot5.getLayoutY()+10);
                commentOn=false;
                root.getChildren().remove(guestComment);
                comment2.setText("");
            }
        }
    }
    public void guestWins(){
        bar.getMovingBarAnimation().stop();
        bar.setLayoutX(1);
        bar.setLayoutY(120);
        ApplicationData.getHost().deBuffCard();
        ApplicationData.getGuest().deBuffCard();
        StringBuilder reward=new StringBuilder();
        StringBuilder pun=new StringBuilder();
        ApplicationData.getGuest().setHP(game.getHostInitialHP()+30);
        ApplicationData.getHost().setHP(game.getGuestInitialHP()-20);
        reward.append("HP increase: ");reward.append(30);
        if(ApplicationData.getHost().getHP()<15)
            ApplicationData.getHost().setHP(15);
        int decline=game.getHostInitialHP()-ApplicationData.getHost().getHP();
        pun.append("HP decrease: ");pun.append(decline);
        ApplicationData.getGuest().increaseXP(50 *ApplicationData.getHost().getLevel());
        reward.append(",XP increase: ");reward.append(50 *ApplicationData.getHost().getLevel());
        if(game.getBetAmount()==0){
            ApplicationData.getGuest().setCoins(ApplicationData.getGuest().getCoins()+15);
            reward.append(",Coins increased by 15");
        }
        if(game.getBetAmount()!=0){
            ApplicationData.getGuest().setCoins(ApplicationData.getGuest().getCoins()+game.getBetAmount());
            ApplicationData.getHost().setCoins(ApplicationData.getHost().getCoins()-game.getBetAmount());
            reward.append(",Coins increased by ");reward.append(game.getBetAmount());
            pun.append(",Coins decreased by ");pun.append(game.getBetAmount());
        }
        String award=reward.toString();
        String punishment=pun.toString();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        MatchData matchData=new MatchData(ApplicationData.getGuest().getUsername(),ApplicationData.getHost().getUsername(),award,punishment,timestamp,ApplicationData.getGuest().getLevel(),ApplicationData.getHost().getLevel());
        MatchData.addToMatchData(matchData);
        MatchData.addToList(matchData);
        User.updateUserInSQL(ApplicationData.getHost());
        User.updateUserInSQL(ApplicationData.getGuest());
        Stage stage = ApplicationData.getStage();
        try {
            new EndGame().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void hostWins(){
        bar.getMovingBarAnimation().stop();
        bar.setLayoutX(1);
        bar.setLayoutY(120);
        ApplicationData.getHost().deBuffCard();
        ApplicationData.getGuest().deBuffCard();
        StringBuilder reward=new StringBuilder();
        StringBuilder pun=new StringBuilder();
        ApplicationData.getHost().setHP(game.getHostInitialHP()+30);
        ApplicationData.getGuest().setHP(game.getGuestInitialHP()-20);
        reward.append("HP increase: ");reward.append(30);
        if(ApplicationData.getGuest().getHP()<15)
            ApplicationData.getGuest().setHP(15);
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
        String award=reward.toString();
        String punishment=pun.toString();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        MatchData matchData=new MatchData(ApplicationData.getHost().getUsername(),ApplicationData.getGuest().getUsername(),award,punishment,timestamp,ApplicationData.getHost().getLevel(),ApplicationData.getGuest().getLevel());
        MatchData.addToMatchData(matchData);
        MatchData.addToList(matchData);
        User.updateUserInSQL(ApplicationData.getHost());
        User.updateUserInSQL(ApplicationData.getGuest());
        Stage stage = ApplicationData.getStage();
        try {
            new EndGame().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void newRound(){
        System.out.println("new Round");
        bar.getMovingBarAnimation().stop();
        bar.setLayoutX(1);
        bar.setLayoutY(120);
        game.setHostRemainingTurns(4);
        game.setGuestRemainingTurns(4);
        guestTurnNum.setText(String.valueOf(4));
        hostTurnNum.setText(String.valueOf(4));
//        new Image(LoginMenu.class.getResource("/Media/Images/Cards/" + ApplicationData.getHost().getAllPossessedCards().get(i).getName() +".jpeg").toExternalForm(),47,69,false,false);
    }
    public void cardsAtHandHider(int player) {
        hidden=true;
        if (player==2) {
            Collections.shuffle(game.getGuestCardsAtHand());
            guestslot0.setFill(new ImagePattern(hider));
            guestslot1.setFill(new ImagePattern(hider));
            guestslot2.setFill(new ImagePattern(hider));
            guestslot3.setFill(new ImagePattern(hider));
            guestslot4.setFill(new ImagePattern(hider));
            guestslot5.setFill(new ImagePattern(hider));
        }
        else {
            Collections.shuffle(game.getHostCardsAtHand());
            hostslot0.setFill(new ImagePattern(hider));
            hostslot1.setFill(new ImagePattern(hider));
            hostslot2.setFill(new ImagePattern(hider));
            hostslot3.setFill(new ImagePattern(hider));
            hostslot4.setFill(new ImagePattern(hider));
            hostslot5.setFill(new ImagePattern(hider));
        }
    }


    public void activateCopier(ActionEvent actionEvent) {
        String str=copiertext.getText();
        int index1=Integer.parseInt(str);
        if(game.isHostTurn()){
            if (index1>=game.getHostCardsAtHand().size()){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Enter a valid card number");
                alert.showAndWait();
            }
            else {
                ArrayList<Card> ans=game.getHostCardsAtHand();
                ans.add(game.getHostCardsAtHand().get(index1));
                game.setHostCardsAtHand(ans);
                hostslot5.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getHostCardsAtHand().get(index1).getName())));
                //image
                root.getChildren().remove(copiertext);
                root.getChildren().remove(copierButton);
                nextTurn();
            }
        }
        else {
            if (index1>=game.getGuestCardsAtHand().size()){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Enter a valid card number");
                alert.showAndWait();
            }
            else {
                ArrayList<Card> ans=game.getGuestCardsAtHand();
                ans.add(game.getGuestCardsAtHand().get(index1));
                game.setGuestCardsAtHand(ans);
                guestslot5.setFill(new ImagePattern(ApplicationData.getCardsImage().get(game.getGuestCardsAtHand().get(index1).getName())));

                //image
                root.getChildren().remove(copiertext);
                root.getChildren().remove(copierButton);
                nextTurn();
            }
        }
    }

    public void pause(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        ApplicationData.setPlayingMode(true);
        try {
            new SettingMenu().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
