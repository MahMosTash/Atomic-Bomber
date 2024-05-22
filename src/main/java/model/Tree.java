package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Tree extends Explosion{
    public Tree(Game game) {
        super(150, 150, game);
        setX((game.WIDTH + WIDTH));
        setY(App.random.nextDouble(game.HEIGHT - HEIGHT - 180, game.HEIGHT - HEIGHT - 160));
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource("/Images/Tree.png")).toExternalForm())));
    }
}
