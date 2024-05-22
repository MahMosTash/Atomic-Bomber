package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Airplane;
import model.Cluster;
import model.Game;
import model.Radioactive;

public class AddClusterTransition extends Transition {
    private final Cluster cluster;
    private final double speed = 1;
    private final Airplane airplane;
    private final Game game;
    private final Pane root;

    public AddClusterTransition(Cluster cluster, Airplane airplane, Game game, Pane root) {
        this.cluster = cluster;
        this.airplane = airplane;
        this.game = game;
        this.root = root;
        game.animations.add(this);
        setCycleDuration(Duration.seconds(5));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        cluster.setY(cluster.getY() - speed);
        if (cluster.getY() < 0) {
            root.getChildren().remove(cluster);
        }
        if (cluster.getBoundsInParent().intersects(airplane.getBoundsInParent())) {
            game.addClusterBomb();
            root.getChildren().remove(cluster);
            this.stop();
        }
    }
}
