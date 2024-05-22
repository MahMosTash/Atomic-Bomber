package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Trench extends Explosion{
    public Trench(Game game) {
        super(80, 80, game);
        setX((game.WIDTH + WIDTH));
        setY(App.random.nextDouble(game.HEIGHT - HEIGHT - 180, game.HEIGHT - HEIGHT - 160));
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource("/Images/Trench.png")).toExternalForm())));
    }
}
