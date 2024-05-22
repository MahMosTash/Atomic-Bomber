package view.menus;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.App;
import model.User;
import view.Main;

public class ChangeUsernameController {
    public PasswordField passwordField;
    public TextField usernameField;

    public void submit(MouseEvent mouseEvent) throws Exception {
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
        if (!App.getLoggedInUser().getPassword().equals(password)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect password");
            alert.setContentText("Please enter your current password");
            alert.showAndWait();
            return;
        }
        if (User.getUserByUsername(username) != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username taken");
            alert.setContentText("This username is already taken, please choose another one");
            alert.showAndWait();
            return;
        }
        App.getLoggedInUser().setUsername(username);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Username changed successfully");
        alert.setContentText("Your username has been changed to " + username);
        alert.showAndWait();
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.start(Main.stage);
    }
}
