package view.menus;

import controller.LoginController;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.loading_screens.LoadingScreen2;
import view.loading_screens.LoadingScreen4;
import view.Main;

public class LoginMenuController {
    public TextField usernameField;
    public PasswordField passwordField;

    public void login(MouseEvent mouseEvent) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }
        if (!LoginController.canLogin(username, password)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username or password");
            alert.setContentText("Username or password is incorrect");
            alert.showAndWait();
            return;
        }
        LoginController.login(username);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Entered Successfully");
        alert.setContentText("You have entered your account successfully");
        alert.showAndWait();
        LoadingScreen4 loadingScreen4 = new LoadingScreen4();
        loadingScreen4.start(Main.stage);
    }

    public void goToRegisterMenu(MouseEvent mouseEvent) throws Exception {
        LoadingScreen2 loadingScreen2 = new LoadingScreen2();
        loadingScreen2.start(Main.stage);
    }
}
