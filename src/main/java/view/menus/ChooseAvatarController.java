package view.menus;

import enums.Medias;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.App;
import model.User;
import view.loading_screens.LoadingScreen3;
import view.Main;

import java.util.Objects;

public class ChooseAvatarController {
    public ImageView avatarImage;
    private String imagePath;

    public void initialize() throws Exception {
        User user = App.getLoggedInUser();
        imagePath = user.getAvatarImage();
        Image image;
        if (imagePath.contains("/Images/Avatar")) {
            image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        } else {
            image = new Image(imagePath);
        }
        avatarImage.setImage(image);
    }

    public void avatar1(MouseEvent mouseEvent) {
        imagePath = "/Images/Avatar1.jpg";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        avatarImage.setImage(image);
    }

    public void avatar2(MouseEvent mouseEvent) {
        imagePath = "/Images/Avatar2.jpg";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        avatarImage.setImage(image);
    }

    public void avatar4(MouseEvent mouseEvent) {
        imagePath = "/Images/Avatar4.jpg";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        avatarImage.setImage(image);
    }

    public void avatar5(MouseEvent mouseEvent) {
        imagePath = "/Images/Avatar5.jpg";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        avatarImage.setImage(image);
    }

    public void avatar3(MouseEvent mouseEvent) {
        imagePath = "/Images/Avatar3.jpg";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        avatarImage.setImage(image);
    }

    public void avatar6(MouseEvent mouseEvent) {
        imagePath = "/Images/Avatar6.jpg";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        avatarImage.setImage(image);
    }

    public void setAvatar(MouseEvent mouseEvent) throws Exception {
        User user = App.getLoggedInUser();
        user.setAvatarImage(imagePath);
        App.media = Medias.DardeDoori.getMedia();
        App.musicPlayer();
        LoadingScreen3 loadingScreen3 = new LoadingScreen3();
        loadingScreen3.start(Main.stage);
    }
}
