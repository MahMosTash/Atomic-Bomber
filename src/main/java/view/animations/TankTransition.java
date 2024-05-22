package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Airplane;
import model.Game;
import model.Tank;
import model.TankBomb;

public class TankTransition extends Transition {
    private double speed = 1.2;
    private final Game game;
    private final Tank tank;
    private final Pane root;
    private final Airplane airplane;
    private boolean hadShot = false;

    public TankTransition(Game game, Tank tank, Pane root) {
        this.game = game;
        this.tank = tank;
        this.root = root;
        this.airplane = game.getAirplane();
        game.animations.add(this);

        speed *= game.getLoggedUser().getDifficulty();
        tank.setAnimation(this);
        setCycleDuration(Duration.seconds(5));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        tank.setX(tank.getX() + speed);
        if (tank.getX() > game.WIDTH) {
            root.getChildren().remove(tank);
            game.enemies.remove(tank);
        }
        double distance = Math.sqrt(Math.pow(airplane.getX() - tank.getX(), 2) + Math.pow(airplane.getY() - tank.getY(), 2));
        if (distance <= tank.getRange() && game.getWave() >= 2) {
            if (!hadShot) {
                hadShot = true;
                TankBomb tankBomb = new TankBomb(game, airplane, tank);
                root.getChildren().add(tankBomb);
                game.tankBombs.add(tankBomb);
                new TankBombTransition(tank, tankBomb, airplane, game, root).play();
            }
        } else {
            hadShot = false;
        }
    }
}
