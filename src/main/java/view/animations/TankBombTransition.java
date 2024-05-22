package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;

public class TankBombTransition extends Transition {
    private final TankBomb tankBomb;
    private final Tank tank;
    private final Game game;
    private final Pane root;
    private final Airplane airplane;
    private final double speed = 2.5;

    public TankBombTransition(Tank tank, TankBomb tankBomb, Airplane airplane, Game game, Pane root) {
        this.tank = tank;
        this.tankBomb = tankBomb;
        this.game = game;
        this.root = root;
        this.airplane = airplane;
        game.animations.add(this);

        setCycleDuration(Duration.seconds(5));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        tankBomb.setAngle(Math.atan2((airplane.getY() - tank.getY()), (airplane.getX() - tank.getX())));
        tankBomb.setX(tankBomb.getX() + speed * Math.cos(tankBomb.getAngle()));
        tankBomb.setY(tankBomb.getY() + speed * Math.sin(tankBomb.getAngle()));
        tankBomb.setRotate(-tankBomb.getAngle() * 180 / Math.PI);
        if (tankBomb.getX() < 0 || tankBomb.getX() > root.getWidth() || tankBomb.getY() < 0 || tankBomb.getY() > root.getHeight()) {
            root.getChildren().remove(tankBomb);
            game.tankBombs.remove(tankBomb);
            this.stop();
        }
        if (tankBomb.getBoundsInParent().intersects(airplane.getBoundsInParent())) {
            game.getAirplane().setHealth(game.getAirplane().getHealth() - 50);
            root.getChildren().remove(tankBomb);
            game.tankBombs.remove(tankBomb);
            this.stop();
        }
    }
}
