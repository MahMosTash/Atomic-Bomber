package view.menus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.App;

import java.net.URL;

public class LoginMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("/FXML/LoginMenu.fxml");
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
