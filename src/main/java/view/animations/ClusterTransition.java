package view.animations;

import enums.Medias;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Cluster;
import model.Explosion;
import model.Game;

import java.util.Objects;

public class ClusterTransition extends Transition {
    private final Game game;
    private final Cluster cluster;
    private final Pane root;
    private double xSpeed;
    private double ySpeed;
    private final double g = 1.5;
    private double lastTime = 0;

    public ClusterTransition(Game game, Cluster cluster, Pane root) {
        this.game = game;
        this.cluster = cluster;
        this.root = root;
        game.animations.add(this);
        game.addShot();
        xSpeed = game.getAirplane().getXSpeed() + cluster.getSpeed() * Math.cos(cluster.getAngle());
        ySpeed = -cluster.getSpeed() * Math.sin(cluster.getAngle());
        setCycleDuration(Duration.seconds(5));
        setCycleCount(-1);
        cluster.setAnimation(this);

    }

    @Override
    protected void interpolate(double v) {
        double currentTime = v * 5;
        if (currentTime - lastTime >= 0.05) {
            ySpeed += g;
            lastTime = currentTime;
        }
        if (currentTime >= cluster.explodeTime && !cluster.exploded) {
            for (int i = 0; i < 9; i++) {
                Cluster newCluster = new Cluster(game, null, false, cluster);
                newCluster.setAngle(Math.PI + i  * Math.PI / 8);
                game.rockets.add(newCluster);
                root.getChildren().add(newCluster);
                new ClusterTransition(game, newCluster, root).play();
            }
            cluster.exploded = true;
        }
        cluster.setRotate(-cluster.getAngle() * 180 / Math.PI);
        double x = cluster.getX() + xSpeed;
        double y = cluster.getY() + ySpeed;
        cluster.setX(x);
        cluster.setY(y);
        cluster.setAngle(Math.atan2(-ySpeed, xSpeed));
        if (x < 0 || x > root.getWidth() || (cluster.exploded && cluster.fromAirplane())) {
            root.getChildren().remove(cluster);
            game.rockets.remove(cluster);
            this.stop();
        }
        if (y > root.getHeight() - 220) {
            RocketExplosion rocketExplosion = new RocketExplosion(cluster, root, game.rockets);
            rocketExplosion.play();
        }
        for (Explosion child : game.enemies) {
            if (child.getBoundsInParent().intersects(cluster.getBoundsInParent())) {
                if (child.isHit()) continue;
                game.addKill();
                game.addScore(child);
                Medias.Explosion.play();
                child.setHit(true);
                game.getGameLauncher().progressBar.setProgress(game.getGameLauncher().progressBar.getProgress() + 0.1);
                EnemyExplosion enemyExplosion = new EnemyExplosion(child, root, game.enemies);
                enemyExplosion.play();
                root.getChildren().remove(cluster);
                this.stop();
                break;
            }
        }
    }
}