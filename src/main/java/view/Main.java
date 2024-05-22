package view;

import controller.SavingController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.App;
import view.menus.RegisterMenu;

import java.util.Objects;

public class Main extends Application {
    public static Stage stage;

    public static void main(String[] args) throws Exception {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        if (SavingController.loadUsers() != null) {
            App.setAllUsers(SavingController.loadUsers());
        }
        App.musicPlayer();
        Main.stage = stage;
        stage.setResizable(false);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Icon.jpg")));
        stage.getIcons().add(icon);
        RegisterMenu registerMenu = new RegisterMenu();
        registerMenu.start(stage);
    }

}