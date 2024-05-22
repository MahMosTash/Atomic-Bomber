package view.menus;

import enums.Medias;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import model.App;
import model.User;
import view.loading_screens.LoadingScreen3;
import view.Main;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class AvatarMenuController {
    public ImageView avatarImage;
    public void initialize() throws Exception{
        User user = App.getLoggedInUser();
        String imagePath = user.getAvatarImage();
        Image image;
        if (imagePath.contains("/Images/Avatar")) {
            image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        } else {
            image = new Image(imagePath);
        }
        avatarImage.setImage(image);
    }

    public void chooseAvatar(MouseEvent mouseEvent) throws Exception {
        ChooseAvatar chooseAvatar = new ChooseAvatar();
        chooseAvatar.start(Main.stage);
    }
    public void selectAvatar(MouseEvent mouseEvent) throws Exception {
        SelectAvatar selectAvatar = new SelectAvatar();
        selectAvatar.start(Main.stage);
    }

    public void dragDrop(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            List<File> files = db.getFiles();
            if (files.size() == 1) {
                File file = files.get(0);
                User user = App.getLoggedInUser();
                user.setAvatarImage(file.toURI().toString());
                Image image = new Image(file.toURI().toString());
                avatarImage.setImage(image);
                success = true;
            }
        }
        dragEvent.setDropCompleted(success);
        dragEvent.consume();

    }

    public void dragOver(DragEvent dragEvent) {
        if (dragEvent.getGestureSource() != avatarImage && dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.COPY);
        }
        dragEvent.consume();
    }

    public void profileMenu(MouseEvent mouseEvent) throws Exception {
        App.media = Medias.DardeDoori.getMedia();
        App.musicPlayer();
        LoadingScreen3 loadingScreen3 = new LoadingScreen3();
        loadingScreen3.start(Main.stage);
    }
}
