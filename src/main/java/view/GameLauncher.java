package view;

import controller.GameController;
import enums.Medias;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.animations.*;
import view.menus.MainMenu;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameLauncher extends Application {
    public final double WIDTH = 1200;
    public final double HEIGHT = 708;
    public Airplane airplane;
    public Game game;
    public Pane pane;

    private Timeline timeline;
    private Timeline adding;

    public ProgressBar progressBar;
    public Text waveNumber;
    public Text clusterBombs;
    public Text radioActiveBombs;
    public Text kills;
    public Text revive;
    public ProgressBar doYouWantToRevive = new ProgressBar();
    public User loggedUser;
    private boolean isGameOver = false;

    public boolean isPaused = false;
    public boolean isRevived = false;

    public GameLauncher(User loggedUser) {
        this.loggedUser = loggedUser;
        System.out.println(1);
        if (loggedUser.getGame() == null) {
            game = new Game(loggedUser, this);
            loggedUser.setGame(game);
        } else {
            System.out.println(1);
            game = loggedUser.getGame();
            game.setGameLauncher(this);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        App.media = Medias.Hitler.getMedia();
        App.musicPlayer();
        App.mediaPlayer.setVolume(0.2);
        Pane root = new Pane();
        this.pane = root;
        setSize(root);
        root.setBackground(new Background(createBackgroundImage()));
        createAirplane();
        createWave();
        Scene scene = new Scene(root);
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                try {
                    if (!isPaused) {
                        isPaused = true;
                        GameController.pause(game, root, timeline);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (event.getCode() == KeyCode.H) {
                if (airplane == null) {
                    isRevived = true;
                    root.getChildren().remove(revive);
                    root.getChildren().remove(doYouWantToRevive);
                    adding.stop();
                    resetProgress();
                    createAirplane();
                    airplane.requestFocus();
                }
            }
        });
        stage.setScene(scene);
        stage.centerOnScreen();
        if (App.grayscale) {
            ColorAdjust grayscale = new ColorAdjust();
            grayscale.setSaturation(-1);
            stage.getScene().getRoot().setEffect(grayscale);
        }
        createEssentials();
        Timeline updateNumber = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            radioActiveBombs.setText("x " + game.getRadioActiveBombs());
            clusterBombs.setText("x " + game.getClusterBombs());
            kills.setText("x " + game.getKills());
            waveNumber.setText("Wave: " + game.getWave());
            if (game.getAirplane().getHealth() <= 0) {
                try {
                    GameController.gameOver(game, pane, timeline);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }));
        updateNumber.setCycleCount(Timeline.INDEFINITE);
        updateNumber.play();
        stage.show();
        airplane.requestFocus();
    }

    private void setSize(Pane pane) {
        pane.setMinHeight(HEIGHT);
        pane.setMaxHeight(HEIGHT);
        pane.setMinWidth(WIDTH);
        pane.setMaxWidth(WIDTH);
    }

    private BackgroundImage createBackgroundImage() {
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/Images/Background.jpg")).toExternalForm(), WIDTH, HEIGHT, false, false);
        return new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
    }

    public void goToNextWave() {
        if (game.getWave() == 3 && !isGameOver) {
            try {
                GameController.showWinningWindow(game, pane, timeline);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        game.setWave(game.getWave() + 1);
        timeline.stop();
        if (game.getWave() <= 3 && !isGameOver) {
            Text newWave = new Text(WIDTH / 2 - 100, HEIGHT / 2, "Wave " + game.getWave() + " is coming!");
            newWave.setStyle("-fx-font-size: 50; -fx-fill: white;");
            Text accuracy = new Text(WIDTH / 2 - 100, HEIGHT / 2 + 50, "Accuracy: " + game.getWaveAccuracy());
            accuracy.setStyle("-fx-font-size: 30; -fx-fill: white;");
            pane.getChildren().add(newWave);
            pane.getChildren().add(accuracy);
            Timeline wave = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                pane.getChildren().remove(newWave);
                pane.getChildren().remove(accuracy);
                createWave();
            }));
            timeline = wave;
            wave.play();
        }
        game.resetKillWave();
    }

    public void resetProgress() {
        doYouWantToRevive.setProgress(0);
        doYouWantToRevive.setPrefSize(game.WIDTH, 50);
        doYouWantToRevive.setLayoutX(0);
        doYouWantToRevive.setLayoutY(game.HEIGHT - 125);
        doYouWantToRevive.setStyle("-fx-accent: red;");
    }

    public void createWave() {
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            tasks.add(this::createTank);
        }
        for (int i = 0; i < 8; i++) {
            tasks.add(this::createTruck);
        }
        for (int i = 0; i < 4; i++) {
            tasks.add(this::createTrench);
        }
        for (int i = 0; i < 3; i++) {
            tasks.add(this::createTree);
        }
        for (int i = 0; i < 2; i++) {
            tasks.add(this::createBuilding);
        }
        if (game.getWave() == 3) {
            for (int i = 0; i < 4; i++) {
                tasks.add(this::createMig);
            }
        }
        Collections.shuffle(tasks, App.random);
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            Runnable task = tasks.remove(0);
            Platform.runLater(task);
        }));
        timeline.setCycleCount(25);
        timeline.setOnFinished(event -> {
            goToNextWave();
        });
        timeline.play();
    }

    public void createTank() {
        Tank tank = new Tank(game);
        game.enemies.add(tank);
        pane.getChildren().add(tank);
        TankTransition tankTransition = new TankTransition(game, tank, pane);
        tankTransition.play();
    }

    public void createRandomTank() {
        Tank tank = new Tank(game);
        tank.setX(App.random.nextInt((int) WIDTH));
        game.enemies.add(tank);
        pane.getChildren().add(tank);
        TankTransition tankTransition = new TankTransition(game, tank, pane);
        tankTransition.play();
    }

    public void createTruck() {
        Truck truck = new Truck(game);
        game.enemies.add(truck);
        pane.getChildren().add(truck);
        TruckTransition truckTransition = new TruckTransition(game, truck, pane);
        truckTransition.play();
    }

    public void createBuilding() {
        Building building = new Building(game);
        game.enemies.add(building);
        pane.getChildren().add(building);
        BuildingTransition buildingTransition = new BuildingTransition(game, building, pane);
        buildingTransition.play();
    }

    public void createTree() {
        Tree tree = new Tree(game);
        game.enemies.add(tree);
        pane.getChildren().add(tree);
        TreeTransition treeTransition = new TreeTransition(game, tree, pane);
        treeTransition.play();
    }

    public void createTrench() {
        Trench trench = new Trench(game);
        game.enemies.add(trench);
        pane.getChildren().add(trench);
        TrenchTransition trenchTransition = new TrenchTransition(game, trench, pane);
        trenchTransition.play();
    }

    public void createMig() {
        Text message = new Text(WIDTH / 2, HEIGHT / 2, "Watch out for mig...");
        message.setStyle("-fx-font-size: 30; -fx-fill: white;");
        pane.getChildren().add(message);
        Timeline delay = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            pane.getChildren().remove(message);
            Mig mig = new Mig(game);
            game.enemies.add(mig);
            pane.getChildren().add(mig);
            MigTransition migTransition = new MigTransition(game, mig, pane);
            migTransition.play();
        }));
        delay.play();
    }

    private void createAirplane() {
        airplane = new Airplane(game);
        pane.getChildren().add(airplane);
        AirplaneTransition airplaneTransition = new AirplaneTransition(game, airplane, pane);
        airplaneTransition.play();
        airplane.setOnKeyPressed(event -> {
            airplane.requestFocus();
            if (event.getCode() == KeyCode.W) {
                airplaneTransition.setUp(true);
            }
            if (event.getCode() == KeyCode.S) {
                airplaneTransition.setDown(true);
            }
            if (event.getCode() == KeyCode.A) {
                airplaneTransition.setLeft(true);
            }
            if (event.getCode() == KeyCode.D) {
                airplaneTransition.setRight(true);
            }
            if (event.getCode() == KeyCode.SPACE) {
                GameController.shootMissile(game, pane, airplane);
            }
            if (event.getCode() == KeyCode.C) {
                if (game.getClusterBombs() > 0) {
                    GameController.shootCluster(game, pane, airplane);
                    game.reduceClusterBomb();
                }
            }
            if (event.getCode() == KeyCode.R) {
                if (game.getRadioActiveBombs() > 0) {
                    GameController.shootRadioactiveBomb(game, pane, airplane);
                    game.reduceRadioActiveBomb();
                }
            }
            if (event.getCode() == KeyCode.P) {
                goToNextWave();
            }
            if (event.getCode() == KeyCode.G) {
                GameController.addRadioActive(game);
            }
            if (event.getCode() == KeyCode.COMMAND) {
                GameController.addCluster(game);
            }
            if (event.getCode() == KeyCode.T) {
                createRandomTank();
            }
            if (event.getCode() == KeyCode.TAB) {
                if (progressBar.getProgress() >= 0.97) {
                    progressBar.setProgress(0);
                    GameController.Freeze(game, pane, timeline);
                }
            }
        });
        airplane.setOnKeyReleased(event -> {
            airplane.requestFocus();
            if (event.getCode() == KeyCode.D) {
                airplaneTransition.setRight(false);
            }
            if (event.getCode() == KeyCode.A) {
                airplaneTransition.setLeft(false);
            }
            if (event.getCode() == KeyCode.W) {
                airplaneTransition.setUp(false);
            }
            if (event.getCode() == KeyCode.S) {
                airplaneTransition.setDown(false);
            }
        });
    }

    public void addRevive() {
        isRevived = false;
        if (!pane.getChildren().contains(doYouWantToRevive)) {
            pane.getChildren().add(doYouWantToRevive);
        }

        if (!pane.getChildren().contains(revive)) {
            pane.getChildren().add(revive);
        }
        adding = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (isRevived) {
                doYouWantToRevive.setProgress(0);
                adding.stop();
                return;
            }
            doYouWantToRevive.setProgress(doYouWantToRevive.getProgress() + 0.01);
        }));
        adding.setCycleCount(100);
        adding.setOnFinished(event -> {
            if (!isRevived) {
                try {
                    GameController.gameOver(game, pane, timeline);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        adding.play();
    }

    public void createEssentials() {
        Rectangle rectangle = new Rectangle(0, HEIGHT - 50, WIDTH, 50);
        rectangle.setFill(Paint.valueOf("white"));
        pane.getChildren().add(rectangle);
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(WIDTH);
        progressBar.setLayoutX(0);
        progressBar.setLayoutY(HEIGHT - 10);
        progressBar.setProgress(0);
        progressBar.setEffect(new ColorAdjust(255, 0, 0, 0));
        pane.getChildren().add(progressBar);
        ImageView radioActiveBomb = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/Images/Radioactive.png")).toExternalForm()));
        radioActiveBomb.setX(10);
        radioActiveBomb.setY(HEIGHT - 50);
        radioActiveBomb.setFitHeight(40);
        radioActiveBomb.setFitWidth(40);
        pane.getChildren().add(radioActiveBomb);
        radioActiveBombs = new Text(60, HEIGHT - 30, "x " + game.getRadioActiveBombs());
        pane.getChildren().add(radioActiveBombs);
        ImageView clusterBomb = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/Images/Missile.png")).toExternalForm()));
        clusterBomb.setX(110);
        clusterBomb.setY(HEIGHT - 50);
        clusterBomb.setFitHeight(40);
        clusterBomb.setFitWidth(40);
        pane.getChildren().add(clusterBomb);
        clusterBombs = new Text(160, HEIGHT - 30, "x " + game.getClusterBombs());
        pane.getChildren().add(clusterBombs);
        ImageView kill = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/Images/Kill.png")).toExternalForm()));
        kill.setX(210);
        kill.setY(HEIGHT - 50);
        kill.setFitHeight(40);
        kill.setFitWidth(40);
        pane.getChildren().add(kill);
        kills = new Text(260, HEIGHT - 30, "x " + game.getKills());
        pane.getChildren().add(kills);
        waveNumber = new Text(WIDTH - 100, HEIGHT - 30, "Wave: " + game.getWave());
        pane.getChildren().add(waveNumber);
        revive = new Text("Do you want to revive?");
        revive.setLayoutX(game.WIDTH / 2 - 150);
        revive.setLayoutY(game.HEIGHT - 90);
        revive.setStyle("-fx-fill: white;");
        revive.setStyle("-fx-font-size: 30;");
        resetProgress();
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
