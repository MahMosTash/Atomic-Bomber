package view.menus;

import controller.RegisterController;
import controller.SavingController;
import enums.Fields;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.loading_screens.LoadingScreen1;
import view.loading_screens.LoadingScreen4;
import view.Main;

public class RegisterMenuController {
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField passwordCheckField;
    public void register(MouseEvent mouseEvent) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String passwordCheck = passwordCheckField.getText();
        if (username.isEmpty() || password.isEmpty() || passwordCheck.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }
        if (Fields.validUsername.getMather(username) == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username");
            alert.setContentText("Username must contain only letters and numbers and must have at least 5 characters");
            alert.showAndWait();
            return;
        }
        if (Fields.strongPassword.getMather(password) == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid password");
            alert.setContentText("Password must contain at least 8 characters, one letter, one number and one special character");
            alert.showAndWait();
            return;
        }
        if (!password.equals(passwordCheck)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passwords don't match");
            alert.setContentText("Please enter the same password in both fields");
            alert.showAndWait();
            return;
        }
        if (RegisterController.isUsernameTaken(username)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username taken");
            alert.setContentText("This username is already taken, please choose another one");
            alert.showAndWait();
            return;
        }
        RegisterController.register(username, password);
        SavingController.saveUsers();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Account created");
        alert.setContentText("Your account has been created successfully, you can now login");
        alert.showAndWait();
        LoadingScreen1 loadingScreen1 = new LoadingScreen1();
        loadingScreen1.start(Main.stage);
    }

    public void goTolLoginMenu(MouseEvent mouseEvent) throws Exception {
        LoadingScreen1 loadingScreen1 = new LoadingScreen1();
        loadingScreen1.start(Main.stage);
    }

    public void playAsGuest(MouseEvent mouseEvent) throws Exception {
        RegisterController.playAsGuest();
        LoadingScreen4 loadingScreen4 = new LoadingScreen4();
        loadingScreen4.start(Main.stage);
    }
}
