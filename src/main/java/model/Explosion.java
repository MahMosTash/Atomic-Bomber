package model;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;

public abstract class Explosion extends Rectangle {
    public final double WIDTH;
    public final double HEIGHT;
    protected final Game game;
    protected boolean hit = false;
    protected Transition animation;

    public Explosion(double WIDTH, double HEIGHT, Game game) {
        super(WIDTH, HEIGHT);
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.game = game;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit() {
        return hit;
    }

    public void setAnimation(Transition animation) {
        this.animation = animation;
    }

    public Transition getAnimation() {
        return animation;
    }
}
