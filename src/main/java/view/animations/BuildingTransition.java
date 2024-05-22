package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Building;
import model.Game;
import model.Truck;

public class BuildingTransition extends Transition {
    private final double speed = 0.4;
    private final Game game;
    private final Building building;
    private final Pane root;

    public BuildingTransition(Game game, Building building, Pane root) {
        this.game = game;
        this.building = building;
        this.root = root;
        game.animations.add(this);
        setCycleDuration(Duration.seconds(10));
        setCycleCount(-1);
        building.setAnimation(this);
    }

    @Override
    protected void interpolate(double v) {
        building.setX(building.getX() - speed);
        if (building.getX() < -building.WIDTH){
            root.getChildren().remove(building);
            game.enemies.remove(building);
        }

    }
}
