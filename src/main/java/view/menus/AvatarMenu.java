package view.menus;

import enums.Medias;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.App;

import java.net.URL;

public class AvatarMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        App.media = Medias.Azhir.getMedia();
        App.musicPlayer();
        URL url = getClass().getResource("/FXML/AvatarMenu.fxml");
        assert url != null;
        Pane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        if (App.grayscale){
            ColorAdjust grayscale = new ColorAdjust();
            grayscale.setSaturation(-1);
            stage.getScene().getRoot().setEffect(grayscale);
        }
        stage.show();
    }
}
