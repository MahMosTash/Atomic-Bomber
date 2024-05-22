package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Tank;
import model.Truck;

public class TruckTransition extends Transition {
    private final double speed = 1;
    private final Game game;
    private final Truck truck;
    private final Pane root;

    public TruckTransition(Game game, Truck truck, Pane root) {
        this.game = game;
        this.truck = truck;
        this.root = root;
        game.animations.add(this);
        truck.setAnimation(this);
        setCycleDuration(Duration.seconds(10));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        truck.setX(truck.getX() + speed);
        if (truck.getX() > game.WIDTH){
            root.getChildren().remove(truck);
            game.enemies.remove(truck);
        }

    }
}
