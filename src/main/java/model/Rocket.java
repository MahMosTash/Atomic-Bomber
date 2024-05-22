package model;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;


public abstract class Rocket extends Rectangle {
    protected final double WIDTH ;
    protected final double HEIGHT;
    protected final Game game;
    protected final Airplane airplane;
    protected final double speed;
    protected Transition animation;
    public Rocket(double width, double height, Game game, Airplane airplane, double speed) {
        super(width, height);
        WIDTH = width;
        HEIGHT = height;
        this.game = game;
        this.airplane = airplane;
        this.speed = speed;
    }

    public Game getGame() {
        return game;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public double getSpeed() {
        return speed;
    }

    public Transition getAnimation() {
        return animation;
    }

    public void setAnimation(Transition animation) {
        this.animation = animation;
    }
}
