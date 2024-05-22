package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;

public class MigTransition extends Transition {
    private double speed = 1.2;
    private final Game game;
    private final Mig mig;
    private final Pane root;
    private final Airplane airplane;
    private boolean hadShot = false;
    public MigTransition(Game game, Mig mig, Pane root) {
        this.game = game;
        this.mig = mig;
        this.root = root;
        this.airplane = game.getAirplane();
        game.animations.add(this);
        speed *= ((double) (5 - game.getLoggedUser().getDifficulty()) / 4) ;
        mig.setAnimation(this);
        setCycleDuration(Duration.seconds(5));
        setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v) {
        mig.setX(mig.getX() + speed);
        if (mig.getX() > game.WIDTH) {
            root.getChildren().remove(mig);
            game.enemies.remove(mig);
        }
        double distance = Math.sqrt(Math.pow(airplane.getX() - mig.getX(), 2) + Math.pow(airplane.getY() - mig.getY(), 2));
        if (distance <= mig.getRange()) {
            if (!hadShot) {
                hadShot = true;
                MigMissile migMissile = new MigMissile(game, airplane, mig);
                root.getChildren().add(migMissile);
                game.migMissiles.add(migMissile);
                new MigMissileTransition(mig, migMissile, airplane, game, root).play();
            }
        } else {
            hadShot = false;
        }
    }
}
