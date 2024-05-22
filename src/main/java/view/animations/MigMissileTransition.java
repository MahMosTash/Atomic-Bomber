package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;

public class MigMissileTransition extends Transition {
    private final MigMissile migMissile;
    private final Mig mig;
    private final Game game;
    private final Pane root;
    private final Airplane airplane;
    private final double speed = 2.5;

    public MigMissileTransition(Mig mig, MigMissile migMissile, Airplane airplane, Game game, Pane root) {
        this.mig = mig;
        this.migMissile = migMissile;
        this.game = game;
        this.root = root;
        this.airplane = airplane;
        game.animations.add(this);

        setCycleDuration(Duration.seconds(5));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        migMissile.setAngle(Math.atan2((airplane.getY() - mig.getY()), (airplane.getX() - mig.getX())));
        migMissile.setX(migMissile.getX() + speed * Math.cos(migMissile.getAngle()));
        migMissile.setY(migMissile.getY() + speed * Math.sin(migMissile.getAngle()));
        migMissile.setRotate(-migMissile.getAngle() * 180 / Math.PI);
        if (migMissile.getX() < 0 || migMissile.getX() > root.getWidth() || migMissile.getY() < 0 || migMissile.getY() > root.getHeight()) {
            root.getChildren().remove(migMissile);
            game.migMissiles.remove(migMissile);
            this.stop();
        }
        if (migMissile.getBoundsInParent().intersects(airplane.getBoundsInParent())) {
            game.getAirplane().setHealth(game.getAirplane().getHealth() - 100);
            root.getChildren().remove(migMissile);
            game.migMissiles.remove(migMissile);
            this.stop();
        }
    }
}
