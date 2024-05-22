package model;

import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import view.GameLauncher;

import java.util.ArrayList;

public class Game {
    public final double WIDTH = 1200;
    public final double HEIGHT = 708;
    private User loggedUser;
    private int score = 0;
    private int kills = 0;
    private int waveShots = 0;
    private int shots = 0;
    private int wave = 1;
    private int waveKills = 0;
    private int waveScore = 0;
    private int clusterBombs = 0;
    private int radioActiveBombs = 0;
    private Airplane airplane;
    private GameLauncher gameLauncher;
    public final ArrayList<Explosion> enemies = new ArrayList<>();
    public final ArrayList<Rocket> rockets = new ArrayList<>();
    public final ArrayList<TankBomb> tankBombs = new ArrayList<>();
    public final ArrayList<MigMissile> migMissiles = new ArrayList<>();

    public final ArrayList<Transition> animations = new ArrayList<>();
    private KeyCode upKey = KeyCode.W;
    private KeyCode downKey = KeyCode.S;
    private KeyCode leftKey = KeyCode.A;
    private KeyCode rightKey = KeyCode.D;

    public Game(User loggedUser, GameLauncher gameLauncher) {
        this.loggedUser = loggedUser;
        this.gameLauncher = gameLauncher;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public int getScore() {
        return score;
    }

    public int getKills() {
        return kills;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public int getWaveKills() {
        return waveKills;
    }

    public int getWaveScore() {
        return waveScore;
    }

    public int getClusterBombs() {
        return clusterBombs;
    }

    public int getRadioActiveBombs() {
        return radioActiveBombs;
    }

    public void addClusterBomb() {
        clusterBombs++;
    }

    public void addRadioActiveBomb() {
        radioActiveBombs++;
    }

    public void reduceClusterBomb() {
        clusterBombs--;
    }

    public void reduceRadioActiveBomb() {
        radioActiveBombs--;
    }
    public void addShot() {
        shots++;
        waveShots++;
    }

    public int getShots() {
        return shots;
    }

    public void addKill() {
        kills++;
        waveKills++;
    }

    public void addScore(Explosion explosion) {
        if (explosion instanceof Building) {
            score += 100;
            waveScore += 100;
        } else if (explosion instanceof Tank) {
            score += 50;
            waveScore += 50;
        } else if (explosion instanceof Truck) {
            score += 20;
            waveScore += 20;
        } else if (explosion instanceof Tree) {
            return;
        } else {
            score += 5;
            waveScore += 5;
        }
    }

    public GameLauncher getGameLauncher() {
        return gameLauncher;
    }

    public String getWaveAccuracy() {
        if (waveShots == 0) {
            return "No shots fired";
        }
        return String.format("%.2f", (double) waveKills / waveShots * 100) + "%";
    }
    public void resetKillWave() {
        waveKills = 0;
        waveScore = 0;
        waveShots = 0;
    }

    public String getTotalAccuracy() {
        if (shots == 0) {
            return "No shots fired";
        }
        return String.format("%.2f", (double) kills / shots * 100) + "%";
    }

    public void setGameLauncher(GameLauncher gameLauncher) {
        this.gameLauncher = gameLauncher;
    }
}
