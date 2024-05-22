package model;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

import java.util.Objects;

public class Airplane extends Rectangle {
    public final double WIDTH = 100;
    public final double HEIGHT = 100;
    private final double speed = 2.5;

    public final Game game;
    public double angle = 0.00001;
    private int health = 500;
    private Transition animation;
    public Airplane(Game game) {
        super(110, 60);
        this.game = game;
        setX((50));
        setY(50);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource("/Images/Airplane.png")).toExternalForm())));
        game.setAirplane(this);
    }
    public double getXSpeed() {
        return speed * Math.cos(angle);
    }

    public Transition getAnimation() {
        return animation;
    }

    public void setAnimation(Transition animation) {
        this.animation = animation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
