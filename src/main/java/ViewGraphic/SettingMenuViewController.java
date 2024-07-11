package ViewGraphic;

import Model.ApplicationData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.util.Duration;
public class SettingMenuViewController {
    @FXML
    private Button quitButton;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backButton;
    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private Button pauseButton;
    @FXML
    private Button playButton;
    @FXML
    private Button changeButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider songTimeSlider;
    @FXML
    private Label volumeSliderValue;
    private DoubleProperty progress = new SimpleDoubleProperty();
    private MediaPlayer mediaPlayer;


    public void initialize() {
        quitButton.setOnMouseClicked(mouseEvent -> {
            if (ApplicationData.isPlayingMode()) {
                ApplicationData.getGuest().deBuffCard();
                ApplicationData.getHost().deBuffCard();
                Stage stage = ApplicationData.getStage();
                stage.setScene(MainMenu.getScene());
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();
            }
        });
        ApplicationData.getSettingMenu().setController(this);
        mediaPlayer = ApplicationData.getMediaPlayer();
        mediaPlayer.setVolume(volumeSlider.getValue()/100);
        volumeSlider.valueProperty().addListener((Observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(newValue.doubleValue()/100);
            volumeSliderValue.setText(String.format("%.2f", newValue));
        });
        songTimeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov)
            {
                if (songTimeSlider.isPressed()) {
                    mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(songTimeSlider.getValue() / 100));
                }
            }
        });
        mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov)
            {
                updatesValues();
            }
        });

        progress.bind(Bindings.createDoubleBinding(() ->
                        mediaPlayer.getCurrentTime().toMillis() / ApplicationData.getMedia().getDuration().toMillis(),
                mediaPlayer.currentTimeProperty())
        );
        songProgressBar.progressProperty().bind(progress);

    }

    public void goBack(MouseEvent mouseEvent) {
        if (ApplicationData.isPlayingMode()) {
            Stage stage = ApplicationData.getStage();
            stage.setScene(Game.getScene());
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        }
        else {
            Stage stage = ApplicationData.getStage();
            stage.setScene(MainMenu.getScene());
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

        }
    }

    public void pauseMedia(ActionEvent actionEvent) {
        mediaPlayer.pause();
    }

    public void playMedia(ActionEvent actionEvent) {
        mediaPlayer.play();
    }

    public void resetMedia(ActionEvent actionEvent) {
        mediaPlayer.stop();
        ApplicationData.getMediaPlayer().stop();
        ApplicationData.setMediaIndex((ApplicationData.getMediaIndex()+1)%10);
        ApplicationData.setMedia(new Media(SettingMenu.class.getResource("/Media/Music/" + ApplicationData.getMediaIndex() + ".mp3").toExternalForm()));
        mediaPlayer = new MediaPlayer(ApplicationData.getMedia());
        ApplicationData.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        this.initialize();
    }

    private void updatesValues()
    {
        Platform.runLater(new Runnable() {
            public void run()
            {
                // Updating to the new time value
                // This will move the slider while running your video
                songTimeSlider.setValue(mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration().toMillis() * 100);
            }
        });
    }

}
