package model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Truck extends Explosion{

    public final Group enemies = new Group();
    public Truck(Game game) {
        super(120, 80, game);
        setX((-WIDTH));
        setY(App.random.nextDouble(game.HEIGHT - HEIGHT - 180, game.HEIGHT - HEIGHT - 160));
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource("/Images/Truck.png")).toExternalForm())));
    }
}
