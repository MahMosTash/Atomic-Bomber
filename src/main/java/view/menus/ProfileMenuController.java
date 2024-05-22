package view.menus;

import controller.SavingController;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.App;
import view.loading_screens.LoadingScreen1;
import view.loading_screens.LoadingScreen2;
import view.loading_screens.LoadingScreen4;
import view.Main;

public class ProfileMenuController {
    public void changeUsername(MouseEvent mouseEvent) throws Exception {
        ChangeUsername changeUsername = new ChangeUsername();
        changeUsername.start(Main.stage);
    }

    public void changePassword(MouseEvent mouseEvent) throws Exception {
        ChangePassword changePassword = new ChangePassword();
        changePassword.start(Main.stage);
    }

    public void deleteUser(MouseEvent mouseEvent) throws Exception {
        App.deleteAccount();
        SavingController.saveUsers();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("User deleted successfully");
        alert.setContentText("Your account has been deleted successfully");
        alert.showAndWait();
        LoadingScreen2 loadingScreen2 = new LoadingScreen2();
        loadingScreen2.start(Main.stage);
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        App.setLoggedInUser(null);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Logged out successfully");
        alert.setContentText("You have been logged out successfully");
        alert.showAndWait();
        LoadingScreen1 loadingScreen1 = new LoadingScreen1();
        loadingScreen1.start(Main.stage);
    }

    public void goToAvatarMenu(MouseEvent mouseEvent) throws Exception {
        AvatarMenu avatarMenu = new AvatarMenu();
        avatarMenu.start(Main.stage);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        LoadingScreen4 loadingScreen4 = new LoadingScreen4();
        loadingScreen4.start(Main.stage);
    }
}
