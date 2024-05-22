package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Radioactive extends Rocket{

    public Radioactive(Game game, Airplane airplane) {
        super(60, 100, game, airplane, 10);
        setX(airplane.getX() + airplane.WIDTH / 2);
        setY(airplane.getY() + airplane.HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Airplane.class.getResource("/Images/Radioactive.png")).toExternalForm())));
    }


}
