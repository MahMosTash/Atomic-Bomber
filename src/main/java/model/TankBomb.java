package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class TankBomb extends Rectangle {
    private final Game game;
    private final Airplane airplane;
    private final Tank tank;
    private final double WIDTH = 20;
    private final double HEIGHT = 10;
    private double angle;

    public TankBomb(Game game, Airplane airplane, Tank tank) {
        super(20, 10);
        this.game = game;
        this.airplane = airplane;
        this.tank = tank;
        angle = Math.atan2(airplane.getY() - tank.getY(), (airplane.getX() + 50) - tank.getX());
        setX(tank.getX() + tank.WIDTH / 2);
        setY(tank.getY() + tank.HEIGHT / 2);
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource
                ("/Images/TankBomb.png")).toExternalForm())));

    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

}
