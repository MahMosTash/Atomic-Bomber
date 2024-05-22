package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Mig extends Explosion {
    private final double range;
    public Mig(Game game) {
        super(110, 40, game);
        setX((-WIDTH));
        setY(App.random.nextDouble(100, game.HEIGHT - HEIGHT - 400));
        range = 250 * game.getLoggedUser().getDifficulty();
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource("/Images/Mig.png")).toExternalForm())));
    }

    public double getRange() {
        return range;
    }
}
