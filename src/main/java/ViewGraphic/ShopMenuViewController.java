package ViewGraphic;

import Controller.ShopMenuController;
import javafx.scene.control.Label;
import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.util.ArrayList;

import static java.lang.Math.min;


public class ShopMenuViewController {
    @FXML
    private Label warningLabel;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label damageLabel;
    @FXML
    private Label accuracyLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private Label costLabel;
    @FXML
    private ScrollPane unlockedScrollPane;
    @FXML
    private ScrollPane lockedScrollPane;
    @FXML
    private AnchorPane unlockedPane;
    @FXML
    private AnchorPane lockedPane;
    private ArrayList<ImageView> unlockedImageView = new ArrayList<>();
    private ArrayList<ImageView> lockedImageView = new ArrayList<>();
    private ArrayList<Card> lockedCards = new ArrayList<>();

    private Card selectedCard;
    private boolean buyMode;
    public void initialize() {
        ApplicationData.getShopMenu().setController(this);
        nameLabel.setText("");
        damageLabel.setText("");
        accuracyLabel.setText("");
        durationLabel.setText("");
        levelLabel.setText("");
        costLabel.setText("");

        //anchorPane.setBackground(new Background(ShopMenu.createBackgroundImage()));
        makeScrollPanes();
        for (int i = 0; i < unlockedImageView.size(); i++) {
            ImageView imageView = unlockedImageView.get(i);
            int finalI = i;
            imageView.setOnMouseClicked(mouseEvent -> {
                selectedCard = ApplicationData.getHost().getAllPossessedCards().get(finalI);
                buyMode = false;
                Card upgradedCard = selectedCard.NextLevelCard();
                nameLabel.setText("Name : " + upgradedCard.getName());
                damageLabel.setText("Damage : " + upgradedCard.getDamage());
                accuracyLabel.setText("Accuracy : " + upgradedCard.getAccuracy());
                durationLabel.setText("Duration : " + upgradedCard.getDuration());
                levelLabel.setText("Level : " + upgradedCard.getLevel());
                costLabel.setText("Cost : " + upgradedCard.getUpgradeCost());
            });
        }
        for (int i = 0; i < lockedImageView.size(); i++) {
            ImageView imageView = lockedImageView.get(i);
            int finalI = i;
            imageView.setOnMouseClicked(mouseEvent -> {
                selectedCard = lockedCards.get(finalI);
                buyMode = true;
                nameLabel.setText("Name : " + selectedCard.getName());
                damageLabel.setText("Damage : " + selectedCard.getDamage());
                accuracyLabel.setText("Accuracy : " + selectedCard.getAccuracy());
                durationLabel.setText("Duration : " + selectedCard.getDuration());
                levelLabel.setText("Level : " + selectedCard.getLevel());
                costLabel.setText("Cost : " + selectedCard.getUpgradeCost());
            });
        }

    }

    public void buyCard(MouseEvent mouseEvent) {
        if (buyMode) {
            String outcome = ShopMenuController.buyCard(selectedCard);
            warningLabel.setText(outcome);
        }
        else {
            String outcome = ShopMenuController.upgradeCard(selectedCard);
            warningLabel.setText(outcome);
        }
        initialize();
    }

    public void goBack(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        try {
            new MainMenu().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void makeScrollPanes() {
        LoginMenu.makeImage();
        unlockedPane.getChildren().removeAll(unlockedPane);
        lockedPane.getChildren().removeAll(lockedPane);
        unlockedScrollPane.setContent(null);
        lockedScrollPane.setContent(null);
        unlockedImageView.removeAll(unlockedImageView);
        lockedImageView.removeAll(lockedImageView);

        unlockedScrollPane.prefWidth(600);
        unlockedPane.prefWidth(ApplicationData.getHost().getAllPossessedCards().size() * 100);
        for (int i = 0; i < ApplicationData.getHost().getAllPossessedCards().size(); i++) {
            //Image image = new Image(MainMenu.class.getResource("/Images/" + ApplicationData.getUser().getSongs().get(i).getSinger().getName() + "/" + i + ".jpeg").toExternalForm(), 200, 200, false, false);
            //Image image = ApplicationData.getHost().getAllPossessedCards().get(i).getImage();
            Image image = new Image(ShopMenu.class.getResource("/Media/Images/Cards/" + ApplicationData.getHost().getAllPossessedCards().get(i).getName() + ".jpeg").toExternalForm(),100,100,false,false);
            ImageView tempImageView = new ImageView(image);
            tempImageView.setLayoutX(i*100);
            unlockedImageView.add(tempImageView);
            unlockedPane.getChildren().add(tempImageView);
        }
        unlockedScrollPane.setContent(unlockedPane);

        lockedCards.clear();
        lockedCards.addAll(ApplicationData.getAllCardsArraylist());
        for (int i = 0; i < lockedCards.size(); i++) {
            Card card = lockedCards.get(i);
            for (Card allPossessedCard : ApplicationData.getHost().getAllPossessedCards()) {
                if (card.getName().equals(allPossessedCard.getName())){
                    lockedCards.remove(card);
                    i--;
                }
            }
        }

        lockedScrollPane.prefWidth(600);
        lockedPane.prefWidth(lockedCards.size() * 100);
        for (int i = 0; i < lockedCards.size(); i++) {
            //Image image = new Image(MainMenu.class.getResource("/Images/" + ApplicationData.getUser().getSongs().get(i).getSinger().getName() + "/" + i + ".jpeg").toExternalForm(), 200, 200, false, false);
            Image image = lockedCards.get(i).getImage();
            ImageView tempImageView = new ImageView(image);
            tempImageView.setLayoutX(i*100);
            lockedImageView.add(tempImageView);
            lockedPane.getChildren().add(tempImageView);
        }
        lockedScrollPane.setContent(lockedPane);
    }


}



