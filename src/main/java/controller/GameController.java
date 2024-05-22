package controller;

import enums.Medias;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import model.*;
import view.Main;
import view.animations.AirplaneTransition;
import view.animations.ClusterTransition;
import view.animations.MissileTransition;
import view.animations.RadioactiveTransition;
import view.menus.MainMenu;

import java.util.Objects;

public class GameController {


    public static void shootMissile(Game game, Pane pane, Airplane airplane) {
        Missile missile = new Missile(game, airplane);
        game.rockets.add(missile);
        int airplaneIndex = pane.getChildren().indexOf(airplane);
        pane.getChildren().add(airplaneIndex, missile);
        MissileTransition missileTransition = new MissileTransition(game, missile, pane);
        missileTransition.play();
        Medias.Missile.getMediaPlayer().play();
    }

    public static void shootCluster(Game game, Pane pane, Airplane airplane) {
        Cluster cluster = new Cluster(game, airplane, true, null);
        game.rockets.add(cluster);
        int airplaneIndex = pane.getChildren().indexOf(airplane);
        pane.getChildren().add(airplaneIndex, cluster);
        ClusterTransition clusterTransition = new ClusterTransition(game, cluster, pane);
        clusterTransition.play();
        Medias.Cluster.getMediaPlayer().play();
    }

    public static void shootRadioactiveBomb(Game game, Pane pane, Airplane airplane) {
        Radioactive radioactive = new Radioactive(game, airplane);
        game.rockets.add(radioactive);
        int airplaneIndex = pane.getChildren().indexOf(airplane);
        pane.getChildren().add(airplaneIndex, radioactive);
        RadioactiveTransition radioactiveTransition = new RadioactiveTransition(game, radioactive, pane);
        radioactiveTransition.play();
        Medias.Radioactive.getMediaPlayer().play();
    }

    public static void addRadioActive(Game game) {
        game.addRadioActiveBomb();
    }

    public static void addCluster(Game game) {
        game.addClusterBomb();
    }

    public static void pause(Game game, Pane root, Timeline timeline) {
        timeline.pause();
        for (Transition animation : game.animations) {
            animation.pause();
        }
        Rectangle rectangle = new Rectangle(game.WIDTH / 2 - 250, game.HEIGHT / 2 - 250, 500, 500);
        rectangle.setFill(javafx.scene.paint.Color.rgb(0, 0, 0, 0.5));
        Group group = new Group(rectangle);
        root.getChildren().add(group);
        Text text = new Text(game.WIDTH / 2 - 90, game.HEIGHT / 2 - 100, "Game Paused");
        text.setFill(javafx.scene.paint.Color.WHITE);
        text.setStyle("-fx-font-size: 30");
        root.getChildren().add(text);
        Button resume = new Button("Resume");
        resume.setPrefSize(300, 40);
        resume.setLayoutX(game.WIDTH / 2 - 150);
        resume.setLayoutY(game.HEIGHT / 2 - 70);
        root.getChildren().add(resume);
        Button saveAndExit = new Button("Save and Exit");
        saveAndExit.setPrefSize(300, 40);
        saveAndExit.setLayoutX(game.WIDTH / 2 - 150);
        saveAndExit.setLayoutY(game.HEIGHT / 2 - 20);
        saveAndExit.setOnAction(event -> {
            try {
                GameController.end(game, timeline);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        root.getChildren().add(saveAndExit);
        Button exit = new Button("Exit");
        exit.setPrefSize(300, 40);
        exit.setLayoutX(game.WIDTH / 2 - 150);
        exit.setLayoutY(game.HEIGHT / 2 + 30);
        exit.setOnAction(event -> {
            try {
                game.getLoggedUser().setGame(null);
                GameController.end(game, timeline);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        root.getChildren().add(exit);
        Button MuteMusic = new Button("Mute Music");
        MuteMusic.setPrefSize(300, 40);
        MuteMusic.setLayoutX(game.WIDTH / 2 - 150);
        MuteMusic.setLayoutY(game.HEIGHT / 2 + 80);
        MuteMusic.setOnAction(event -> {
            if (App.mediaPlayer.isMute()) {
                App.mediaPlayer.setMute(false);
            } else {
                App.mediaPlayer.setMute(true);
            }
        });
        root.getChildren().add(MuteMusic);
        Button changeMusic = new Button("Change Music");
        changeMusic.setPrefSize(300, 40);
        changeMusic.setLayoutX(game.WIDTH / 2 - 150);
        changeMusic.setLayoutY(game.HEIGHT / 2 + 130);
        changeMusic.setOnAction(event -> {
            Rectangle rectangle1 = new Rectangle(game.WIDTH / 2 - 250, game.HEIGHT / 2 - 250, 500, 500);
            rectangle1.setFill(javafx.scene.paint.Color.rgb(0, 0, 0, 1));
            Group group1 = new Group(rectangle1);
            root.getChildren().add(group1);
            Text text1 = new Text(game.WIDTH / 2 - 90, game.HEIGHT / 2 - 100, "Choose Music");
            text1.setFill(javafx.scene.paint.Color.WHITE);
            text1.setStyle("-fx-font-size: 30");
            root.getChildren().add(text1);
            Button music1 = new Button("Darde Doori");
            music1.setPrefSize(300, 40);
            music1.setLayoutX(game.WIDTH / 2 - 150);
            music1.setLayoutY(game.HEIGHT / 2 - 70);
            root.getChildren().add(music1);
            Button music2 = new Button("Damn Things");
            music2.setPrefSize(300, 40);
            music2.setLayoutX(game.WIDTH / 2 - 150);
            music2.setLayoutY(game.HEIGHT / 2 - 20);
            root.getChildren().add(music2);
            Button music3 = new Button("Azhir");
            music3.setPrefSize(300, 40);
            music3.setLayoutX(game.WIDTH / 2 - 150);
            music3.setLayoutY(game.HEIGHT / 2 + 30);
            root.getChildren().add(music3);
            music1.setOnAction(event1 -> {
                App.mediaPlayer.stop();
                App.mediaPlayer = Medias.DardeDoori.getMediaPlayer();
                App.mediaPlayer.setVolume(2);
                App.mediaPlayer.play();
                root.getChildren().remove(group1);
                root.getChildren().remove(text1);
                root.getChildren().remove(music1);
                root.getChildren().remove(music2);
                root.getChildren().remove(music3);
            });
            music2.setOnAction(event1 -> {
                App.mediaPlayer.stop();
                App.mediaPlayer = Medias.Hitler.getMediaPlayer();
                App.mediaPlayer.setVolume(2);
                App.mediaPlayer.play();
                root.getChildren().remove(group1);
                root.getChildren().remove(text1);
                root.getChildren().remove(music2);
                root.getChildren().remove(music1);
                root.getChildren().remove(music3);
            });
            music3.setOnAction(event1 -> {
                App.mediaPlayer.stop();
                App.mediaPlayer = Medias.Azhir.getMediaPlayer();
                App.mediaPlayer.setVolume(2);
                App.mediaPlayer.play();
                root.getChildren().remove(group1);
                root.getChildren().remove(text1);
                root.getChildren().remove(music1);
                root.getChildren().remove(music2);
                root.getChildren().remove(music3);
            });
        });
        root.getChildren().add(changeMusic);
        Button showControls = new Button("Show Controls");
        showControls.setPrefSize(300, 40);
        showControls.setLayoutX(game.WIDTH / 2 - 150);
        showControls.setLayoutY(game.HEIGHT / 2 + 180);
        showControls.setOnAction(event -> {
            Rectangle rectangle1 = new Rectangle(game.WIDTH / 2 - 250, game.HEIGHT / 2 - 250, 500, 500);
            rectangle1.setFill(javafx.scene.paint.Color.rgb(0, 0, 0, 1));
            Group group1 = new Group(rectangle1);
            root.getChildren().add(group1);
            Text text1 = new Text(game.WIDTH / 2 - 50, game.HEIGHT / 2 - 150, "Controls");
            text1.setFill(javafx.scene.paint.Color.WHITE);
            text1.setStyle("-fx-font-size: 30");
            text1.setTextAlignment(TextAlignment.CENTER);
            root.getChildren().add(text1);
            Text text2 = new Text(game.WIDTH / 2 - 200, game.HEIGHT / 2 - 50, "Move Left: A Key");
            text2.setFill(javafx.scene.paint.Color.WHITE);
            text2.setStyle("-fx-font-size: 20");
            text2.setTextAlignment(TextAlignment.CENTER);
            root.getChildren().add(text2);
            Text text3 = new Text(game.WIDTH / 2 + 50, game.HEIGHT / 2 - 50, "Move Right: D Key");
            text3.setFill(javafx.scene.paint.Color.WHITE);
            text3.setStyle("-fx-font-size: 20");
            text3.setTextAlignment(TextAlignment.CENTER);
            root.getChildren().add(text3);
            Text text4 = new Text(game.WIDTH / 2 - 200, game.HEIGHT / 2, "Go Up: W Key");
            text4.setFill(javafx.scene.paint.Color.WHITE);
            text4.setStyle("-fx-font-size: 20");
            text4.setTextAlignment(TextAlignment.CENTER);
            root.getChildren().add(text4);
            Text text5 = new Text(game.WIDTH / 2 + 50, game.HEIGHT / 2, "Go Down: S Key");
            text5.setFill(javafx.scene.paint.Color.WHITE);
            text5.setStyle("-fx-font-size: 20");
            text5.setTextAlignment(TextAlignment.CENTER);
            root.getChildren().add(text5);
            Text text6 = new Text(game.WIDTH / 2 - 200, game.HEIGHT / 2 + 50, "Shoot Missile: Space");
            text6.setFill(javafx.scene.paint.Color.WHITE);
            text6.setStyle("-fx-font-size: 20");
            text6.setTextAlignment(TextAlignment.CENTER);
            root.getChildren().add(text6);
            Text text7 = new Text(game.WIDTH / 2 + 50, game.HEIGHT / 2 + 50, "Shoot Cluster: C Key");
            text7.setFill(javafx.scene.paint.Color.WHITE);
            text7.setStyle("-fx-font-size: 20");
            text7.setTextAlignment(TextAlignment.CENTER);
            root.getChildren().add(text7);
            Text text8 = new Text(game.WIDTH / 2 - 135, game.HEIGHT / 2 + 100, "Shoot Radioactive Bomb: R Key");
            text8.setFill(javafx.scene.paint.Color.WHITE);
            text8.setStyle("-fx-font-size: 20");
            root.getChildren().add(text8);
            Button close = new Button("Close");
            close.setPrefSize(300, 40);
            close.setLayoutX(game.WIDTH / 2 - 150);
            close.setLayoutY(game.HEIGHT / 2 + 150);
            close.setOnAction(event1 -> {
                root.getChildren().remove(group1);
                root.getChildren().remove(text1);
                root.getChildren().remove(text2);
                root.getChildren().remove(text3);
                root.getChildren().remove(text4);
                root.getChildren().remove(close);
                root.getChildren().remove(text5);
                root.getChildren().remove(text6);
                root.getChildren().remove(text7);
                root.getChildren().remove(text8);
            });
            root.getChildren().add(close);
        });
        root.getChildren().add(showControls);
        resume.setOnAction(event -> {
            root.getChildren().remove(group);
            root.getChildren().remove(text);
            root.getChildren().remove(resume);
            root.getChildren().remove(saveAndExit);
            root.getChildren().remove(exit);
            root.getChildren().remove(MuteMusic);
            root.getChildren().remove(changeMusic);
            root.getChildren().remove(showControls);
            timeline.play();
            for (Transition animation : game.animations) {
                animation.play();
            }
            game.getAirplane().requestFocus();
            game.getGameLauncher().isPaused = false;
        });
    }
    public static void gameOver(Game game, Pane root, Timeline timeline) throws Exception {
        Medias.GameOver.getMediaPlayer().play();
        game.getGameLauncher().setGameOver(true);
        for (Transition animation : game.animations) {
            animation.setOnFinished(null);
        }
        timeline.setOnFinished(null);
        Rectangle rectangle = new Rectangle(0, 0, game.WIDTH, game.HEIGHT);
        rectangle.setFill(Paint.valueOf("black"));
        Group group = new Group(rectangle);
        root.getChildren().add(group);
        Text text = new Text(game.WIDTH / 2 - 90, game.HEIGHT / 2 - 100, "Game Over");
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: 30");
        Text waveLabel = new Text(game.WIDTH / 2 - 100, game.HEIGHT / 2 - 50, "Last Wave: " + game.getWave());
        waveLabel.setFill(Color.WHITE);
        Text killCountLabel = new Text(game.WIDTH / 2 - 100, game.HEIGHT / 2, "Kill Count: " + game.getKills());
        killCountLabel.setFill(Color.WHITE);
        Text accuracyLabel = new Text(game.WIDTH / 2 - 100, game.HEIGHT / 2 + 50, "Accuracy: " + game.getTotalAccuracy());
        accuracyLabel.setFill(Color.WHITE);
        group.getChildren().add(text);
        group.getChildren().add(waveLabel);
        group.getChildren().add(killCountLabel);
        group.getChildren().add(accuracyLabel);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), group);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), group);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        game.getLoggedUser().setGame(null);
        fadeIn.setOnFinished(event -> fadeOut.play());
        fadeOut.setOnFinished(event -> {
            try {
                GameController.end(game, timeline);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        fadeIn.play();
    }
    public static void end(Game game, Timeline timeline) throws Exception {
        timeline.setOnFinished(null);
        for (Transition animation : game.animations) {
            animation.setOnFinished(null);
        }
        User user = game.getLoggedUser();
        user.addKill(game.getKills());
        user.addScore(game.getScore());
        user.addTotalShots(game.getShots());
        SavingController.saveUsers();
        App.mediaPlayer.setVolume(2);
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(Main.stage);
    }

    public static void Freeze(Game game, Pane pane, Timeline timeline) {
        ColorAdjust frozen = new ColorAdjust();
        frozen.setHue(1);
        pane.setEffect(frozen);
        timeline.stop();
        for (Transition animation : game.animations) {
            if (!(animation instanceof AirplaneTransition)) {
                animation.stop();
            }
        }
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            pane.setEffect(null);
            timeline.play();
            for (Transition animation : game.animations) {
                animation.play();
            }
        });
        pause.play();
    }
    public static void showWinningWindow(Game game, Pane pane, Timeline timeline) {
        double WIDTH = game.WIDTH;
        double HEIGHT = game.HEIGHT;
        Rectangle blackScreen = new Rectangle(0, 0, WIDTH, HEIGHT);
        blackScreen.setFill(Color.BLACK);
        pane.getChildren().add(blackScreen);

        Text winningLabel = new Text(WIDTH / 2 - 100, HEIGHT / 2 - 100, "Congratulations, you won!");
        winningLabel.setFill(Color.WHITE);
        Text waveLabel = new Text(WIDTH / 2 - 100, HEIGHT / 2 - 50, "Last Wave: " + game.getWave());
        waveLabel.setFill(Color.WHITE);
        Text killCountLabel = new Text(WIDTH / 2 - 100, HEIGHT / 2, "Kill Count: " + game.getKills());
        killCountLabel.setFill(Color.WHITE);
        Text accuracyLabel = new Text(WIDTH / 2 - 100, HEIGHT / 2 + 50, "Accuracy: " + game.getTotalAccuracy());
        accuracyLabel.setFill(Color.WHITE);
        game.getLoggedUser().setGame(null);
        Button backButton = new Button("Back to Main Menu");
        backButton.setLayoutX(WIDTH / 2 - 200);
        backButton.setLayoutY(HEIGHT / 2 + 100);
        backButton.setPrefSize(400, 50);
        backButton.setOnAction(event -> {
            pane.getChildren().removeAll(blackScreen, winningLabel, waveLabel, killCountLabel, accuracyLabel, backButton);
            try {
                end(game, timeline);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        pane.getChildren().addAll(winningLabel, waveLabel, killCountLabel, accuracyLabel, backButton);
    }
}
