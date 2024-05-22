package model;

import enums.Medias;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class App {
    public static Stage stage;
    public static Random random = new Random();
    public static Media media = Medias.DardeDoori.getMedia();
    public static MediaPlayer mediaPlayer = new MediaPlayer(media);
    public static ArrayList<User> allUsers = new ArrayList<>();
    public static User loggedInUser = null;
    public static boolean grayscale = false;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        App.allUsers = allUsers;
    }
    public static void musicPlayer() {
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        mediaPlayer.setVolume(2);
    }
    public static void deleteAccount(){
        allUsers.remove(loggedInUser);
        loggedInUser = null;
    }
}
