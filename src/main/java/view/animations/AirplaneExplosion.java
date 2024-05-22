package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Airplane;

import java.util.Objects;

public class AirplaneExplosion extends Transition {
    private final Airplane airplane;
    private final Pane root;

    public AirplaneExplosion(Airplane airplane, Pane root) {
        this.airplane = airplane;
        this.root = root;
        setCycleDuration(Duration.millis(2000));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        if (airplane == null) {
            return;
        }
        airplane.getAnimation().stop();
        int number = (int) (v * 10) + 1;
        if (number > 19) number = 19;
        airplane.setFill(new ImagePattern(new Image(
                Objects.requireNonNull(EnemyExplosion.class.getResource("/Images/Fire/Fire" + number + ".png")).toExternalForm())));
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(airplane);
            }
        });
    }
}
