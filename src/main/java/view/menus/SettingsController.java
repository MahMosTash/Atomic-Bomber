package view.menus;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import model.App;
import view.loading_screens.LoadingScreen5;
import view.Main;

public class SettingsController {
    public Button muteButton;
    public Button grayscaleButton;
    public ComboBox<String> comboBox;

    public void initialize() {
        comboBox.getItems().addAll("Easy", "Medium", "Hard");
        if (App.mediaPlayer.isMute()) {
            muteButton.setText("Unmute Music");
        } else {
            muteButton.setText("Mute Music");
        }
        if (App.grayscale) {
            grayscaleButton.setText("Turn Off Grayscale mood");
        } else {
            grayscaleButton.setText("Turn On Grayscale mood");
        }
    }

    public void save(MouseEvent mouseEvent) throws Exception {
        LoadingScreen5 loadingScreen5 = new LoadingScreen5();
        loadingScreen5.start(Main.stage);
    }

    public void mute(MouseEvent mouseEvent) {
        if (App.mediaPlayer.isMute()) {
            App.mediaPlayer.setMute(false);
            muteButton.setText("Mute Music");
        } else {
            App.mediaPlayer.setMute(true);
            muteButton.setText("Unmute Music");
        }
    }

    public void grayscale(MouseEvent mouseEvent) {
        App.grayscale = !App.grayscale;
        if (App.grayscale) {
            grayscaleButton.setText("Turn Off Grayscale mood");
        } else {
            grayscaleButton.setText("Turn On Grayscale mood");
        }

    }

    public void controlKeys(MouseEvent mouseEvent) {

    }

    public void chooseDifficulty(ActionEvent actionEvent) {
        if (comboBox.getValue() != null) {
            App.getLoggedInUser().setDifficulty(comboBox.getValue());
        }
    }
}
