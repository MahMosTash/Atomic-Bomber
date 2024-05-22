package view.animations;

import enums.Medias;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.*;

import java.util.Objects;

public class MissileTransition extends Transition {
    private final Game game;
    private final Missile missile;
    private final Pane root;
    private double xSpeed;
    private double ySpeed;
    private final double g = 1.5;
    private double lastTime = 0;

    public MissileTransition(Game game, Missile missile, Pane root) {
        this.game = game;
        this.missile = missile;
        this.root = root;
        game.animations.add(this);
        game.addShot();
        xSpeed = missile.getSpeed() * Math.cos(missile.getAngle());
        ySpeed = -missile.getSpeed() * Math.sin(missile.getAngle());
        setCycleDuration(Duration.seconds(5));
        setCycleCount(-1);
        missile.setAnimation(this);
    }

    @Override
    protected void interpolate(double v) {
        double currentTime = v * 5;
        if (currentTime - lastTime >= 0.05) {
            ySpeed += g;
            lastTime = currentTime;
        }
        missile.setRotate(-missile.getAngle() * 180 / Math.PI);
        double x = missile.getX() + xSpeed;
        double y = missile.getY() + ySpeed;
        missile.setX(x);
        missile.setY(y);
        missile.setAngle(Math.atan2(-ySpeed, xSpeed));
        if (x < 0 || x > root.getWidth()) {
            root.getChildren().remove(missile);
            game.rockets.remove(missile);
            this.stop();
        }
        if (y > root.getHeight() - 220) {
            RocketExplosion rocketExplosion = new RocketExplosion(missile, root, game.rockets);
            rocketExplosion.play();
        }
        for (Explosion child : game.enemies) {
            if (child.getBoundsInParent().intersects(missile.getBoundsInParent())) {
                if (child.isHit()) continue;
                if (child instanceof Building) {
                    Radioactive radioactive = new Radioactive(game, missile.getAirplane());
                    radioactive.setX(child.getX());
                    radioactive.setY(child.getY());
                    radioactive.prefHeight(60);
                    radioactive.prefWidth(60);
                    root.getChildren().add(radioactive);
                    AddRadioactiveTransition addRadioactiveTransition = new AddRadioactiveTransition(radioactive, missile.getAirplane(), game, root);
                    addRadioactiveTransition.play();
                }
                if (child instanceof Trench) {
                    Cluster cluster = new Cluster(game, missile.getAirplane(), true, null);
                    cluster.setX(child.getX());
                    cluster.setY(child.getY());
                    cluster.prefHeight(60);
                    cluster.prefWidth(60);
                    root.getChildren().add(cluster);
                    AddClusterTransition addClusterTransition = new AddClusterTransition(cluster, missile.getAirplane(), game, root);
                    addClusterTransition.play();
                }
                game.getGameLauncher().progressBar.setProgress(game.getGameLauncher().progressBar.getProgress() + 0.1);
                game.addKill();
                game.addScore(child);
                Medias.Explosion.play();
                child.setHit(true);
                EnemyExplosion enemyExplosion = new EnemyExplosion(child, root, game.enemies);
                enemyExplosion.play();
                root.getChildren().remove(missile);
                game.rockets.remove(missile);
                this.stop();
                break;
            }
        }
    }
}
