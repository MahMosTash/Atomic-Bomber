package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class MigMissile extends Rectangle {
    private final Game game;
    private final Airplane airplane;
    private final Mig mig;
    private final double speed = 2.5;
    private final double WIDTH = 20;
    private final double HEIGHT = 10;
    private double angle;

    public MigMissile(Game game, Airplane airplane, Mig mig) {
        super(20, 10);
        this.game = game;
        this.airplane = airplane;
        this.mig = mig;
        angle = Math.atan2(airplane.getY() - mig.getY(), (airplane.getX() + 50) - mig.getX());
        setX(mig.getX() + mig.WIDTH / 2);
        setY(mig.getY() + mig.HEIGHT / 2);
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource
                ("/Images/MigMissile.png")).toExternalForm())));

    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

}
