package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Tank extends Explosion{
    private final double range ;
    public Tank(Game game) {
        super(70, 70, game);
        setX((-WIDTH));
        setY(App.random.nextDouble(game.HEIGHT - HEIGHT - 180, game.HEIGHT - HEIGHT - 160));
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource("/Images/Tank.png")).toExternalForm())));
        range = 150 * game.getLoggedUser().getDifficulty();
    }

    public double getRange() {
        return range;
    }
}
