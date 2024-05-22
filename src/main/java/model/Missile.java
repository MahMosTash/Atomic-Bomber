package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Missile extends Rocket{

    private double angle;

    public Missile(Game game, Airplane airplane) {
        super(40, 20, game, airplane, 6);
        angle = airplane.angle;
        setX(airplane.getX() + airplane.WIDTH);
        setY(airplane.getY() + airplane.HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource("/Images/Missile.png")).toExternalForm())));
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

}
