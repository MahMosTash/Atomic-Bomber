package model;

import javafx.scene.image.Image;
import view.Main;

import java.util.Objects;
import java.util.Random;

public class User {
    private String username;
    private String password;
    private String avatarImage;
    private int difficulty = 1;
    private int score = 0;
    private int kills = 0;
    private int totalShots = 0;
    private Game game = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        App.allUsers.add(this);
        avatarImage = "/Images/Avatar" + (App.random.nextInt(6) + 1) + ".jpg";
    }

    public static User getUserByUsername(String username) {
        for (User user : App.allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                this.difficulty = 1;
                break;
            case "Medium":
                this.difficulty = 2;
                break;
            case "Hard":
                this.difficulty = 3;
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public int getKills() {
        return kills;
    }

    public int getTotalShots() {
        return totalShots;
    }

    public void addKill(int killCount) {
        kills += killCount;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void addTotalShots(int shots) {
        totalShots += shots;
    }

    public double getAccuracy() {
        if (totalShots == 0) {
            return 0;
        }
        return (double) kills * 100 / totalShots;
    }
    public int getDifficultyScore() {
        return kills * difficulty;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

