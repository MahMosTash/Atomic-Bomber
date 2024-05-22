package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Explosion;
import model.Rocket;

import java.util.ArrayList;
import java.util.Objects;

public class RocketExplosion extends Transition {
    private final Rocket rocket;
    private final Pane root;
    private final ArrayList<Rocket> rockets;

    public RocketExplosion(Rocket rocket, Pane root, ArrayList<Rocket> rockets) {
        this.rocket = rocket;
        this.root = root;
        this.rockets = rockets;
        setCycleCount(1);
        this.setCycleDuration(Duration.millis(2000));
    }


    @Override
    protected void interpolate(double v) {
        if (rocket == null){
            return;
        }
        rocket.getAnimation().stop();
        int number = (int) (v * 10) + 1;
        if (number > 19) number = 19;
        rocket.setFill(new ImagePattern(new Image(
                Objects.requireNonNull(EnemyExplosion.class.getResource("/Images/Fire/Fire" + number + ".png")).toExternalForm())));
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rockets.remove(rocket);
                root.getChildren().remove(rocket);
            }
        });
    }
}
