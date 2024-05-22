package controller;

import model.App;
import model.User;

public class LoginController {
    public static boolean canLogin(String username, String password) {
        User user = User.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public static void login(String username) {
        App.loggedInUser = User.getUserByUsername(username);
    }
}
