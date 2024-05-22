package view.menus;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import model.App;
import view.Main;

public class ChangePasswordController {
    public PasswordField password;
    public PasswordField newPassword;
    public PasswordField newPasswordConfirm;

    public void submit(MouseEvent mouseEvent) throws Exception {
        String password = this.password.getText();
        String newPassword = this.newPassword.getText();
        String newPasswordConfirm = this.newPasswordConfirm.getText();
        if (password.isEmpty() || newPassword.isEmpty() || newPasswordConfirm.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }
        if (!newPassword.equals(newPasswordConfirm)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passwords do not match");
            alert.setContentText("Please make sure the new passwords match");
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
        App.getLoggedInUser().setPassword(newPassword);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Password changed successfully");
        alert.setContentText("Your password has been changed successfully");
        alert.showAndWait();
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.start(Main.stage);
    }
}
