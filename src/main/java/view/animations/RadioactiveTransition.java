package view.animations;

import enums.Medias;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Explosion;
import model.Game;
import model.Radioactive;

import java.util.Objects;

public class RadioactiveTransition extends Transition {
    private final Game game;
    private final Radioactive radioactive;
    private double xSpeed;
    private double ySpeed;
    private final Pane root;
    private final double g = 1.5;
    private double lastTime = 0;

    public RadioactiveTransition(Game game, Radioactive radioactive, Pane root) {
        this.game = game;
        this.radioactive = radioactive;
        this.root = root;
        game.animations.add(this);
        game.addShot();
        this.xSpeed = game.getAirplane().getXSpeed();
        this.ySpeed = radioactive.getSpeed();
        setCycleDuration(Duration.seconds(5));
        setCycleCount(-1);
        radioactive.setAnimation(this);
    }

    @Override
    protected void interpolate(double v) {
        double currentTime = v * 5;
        if (currentTime - lastTime >= 0.05) {
            ySpeed += g;
            lastTime = currentTime;
        }
        double x = radioactive.getX() + xSpeed;
        double y = radioactive.getY() + ySpeed;
        radioactive.setX(x);
        radioactive.setY(y);
        if (x < 0 || x > root.getWidth()) {
            root.getChildren().remove(radioactive);
            game.rockets.remove(radioactive);
            this.stop();
        }
        if (y > root.getHeight() - 220) {
            RocketExplosion rocketExplosion = new RocketExplosion(radioactive, root, game.rockets);
            rocketExplosion.play();
        }
        for (Explosion child : game.enemies) {
            if (child.getBoundsInParent().intersects(radioactive.getBoundsInParent())) {
                if (child.isHit()) continue;
                game.addKill();
                game.addScore(child);
                child.setHit(true);
                game.getGameLauncher().progressBar.setProgress(game.getGameLauncher().progressBar.getProgress() + 0.1);
                Medias.Explosion.play();
                EnemyExplosion enemyExplosion = new EnemyExplosion(child, root, game.enemies);
                enemyExplosion.play();
                root.getChildren().remove(radioactive);
                this.stop();
                break;
            }
        }

    }
}
