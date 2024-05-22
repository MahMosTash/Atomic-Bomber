package controller;

import model.App;
import model.User;
import view.Main;

public class RegisterController {
    public static void register(String username, String password) {
        User user = new User(username, password);}

    public static boolean isUsernameTaken(String username) {
        return User.getUserByUsername(username) != null;
    }
    public static void playAsGuest() {
        User user = new User("Guest" + App.random.nextInt(10000000), "Guest");
        App.allUsers.remove(user);
        App.setLoggedInUser(user);
    }
}
