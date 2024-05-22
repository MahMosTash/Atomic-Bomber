package view.menus;

import controller.SavingController;
import javafx.scene.control.Alert;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.App;
import view.loading_screens.LoadingScreen3;
import view.loading_screens.LoadingScreen6;
import view.loading_screens.LoadingScreen7;
import view.Main;

import java.util.Objects;

public class MainMenuController {
    public ImageView avatar;
    public Text username;
    public void initialize() {
        username.setText(App.getLoggedInUser().getUsername());
        String imagePath = App.getLoggedInUser().getAvatarImage();
        Image image;
        if (imagePath.contains("/Images/Avatar")) {
            image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        } else {
            image = new Image(imagePath);
        }
        avatar.setImage(image);
    }

    public void start(MouseEvent mouseEvent) throws Exception {
        LoadingScreen6 loadingScreen6 = new LoadingScreen6();
        loadingScreen6.start(Main.stage);
    }

    public void continueToPlay(MouseEvent mouseEvent) throws Exception {
        LoadingScreen7 loadingScreen7 = new LoadingScreen7();
        loadingScreen7.start(Main.stage);
    }

    public void goToProfileMenu(MouseEvent mouseEvent) throws Exception {
        LoadingScreen3 loadingScreen3 = new LoadingScreen3();
        loadingScreen3.start(Main.stage);
    }

    public void leaderboard(MouseEvent mouseEvent) throws Exception {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.start(Main.stage);
    }

    public void settings(MouseEvent mouseEvent) throws Exception {
        Settings settings = new Settings();
        settings.start(Main.stage);
    }

    public void exit(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.showAndWait();
        SavingController.saveUsers();
        if (alert.getResult().getText().equals("OK")) {
            System.exit(0);
        }
    }
}
