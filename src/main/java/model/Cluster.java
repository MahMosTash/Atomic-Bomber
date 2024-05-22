package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Cluster extends Rocket{
    public final Cluster cluster;
    private double angle;
    public boolean exploded = false;
    public double explodeTime = 0.05;

    public Cluster(Game game, Airplane airplane, Boolean fromAirplane, Cluster cluster) {
        super(40, 20, game, airplane, 6);
        this.cluster = cluster;
        if (fromAirplane) {
            setX(airplane.getX() + airplane.WIDTH / 2);
            setY(airplane.getY() + airplane.HEIGHT /2);
            angle = 3 * Math.PI / 2;
        } else {
            setX(cluster.getX() + cluster.WIDTH / 2);
            setY(cluster.getY() + cluster.HEIGHT);
            exploded = true;
        }
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource("/Images/Missile.png")).toExternalForm())));
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public boolean fromAirplane() {
        return airplane != null;
    }
}