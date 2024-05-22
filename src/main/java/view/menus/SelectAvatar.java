package view.menus;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.App;
import view.Main;

import java.io.File;

public class SelectAvatar extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog(Main.stage);
        if (selectedFile == null) {
            return;
        }
        App.getLoggedInUser().setAvatarImage(selectedFile.toURI().toString());
        AvatarMenu avatarMenu = new AvatarMenu();
        avatarMenu.start(Main.stage);
    }
}
