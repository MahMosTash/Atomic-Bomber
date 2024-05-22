package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Explosion;

import java.util.ArrayList;
import java.util.Objects;

public class EnemyExplosion extends Transition {
    private final Explosion enemy;
    private final Pane root;
    private final ArrayList<Explosion> enemies;

    public EnemyExplosion(Explosion enemy, Pane root, ArrayList<Explosion> enemies) {
        this.enemy = enemy;
        this.root = root;
        this.enemies = enemies;
        setCycleCount(1);
        this.setCycleDuration(Duration.millis(2000));
    }

    @Override
    protected void interpolate(double v) {
        if (enemy == null){
            return;
        }
        enemy.getAnimation().stop();
        int number = 1;
        number = (int) (v * 10) + 1;
        if (number > 19) number = 19;
        enemy.setFill(new ImagePattern(new Image(
                Objects.requireNonNull(EnemyExplosion.class.getResource("/Images/Fire/Fire" + number + ".png")).toExternalForm())));
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enemies.remove(enemy);
                root.getChildren().remove(enemy);
            }
        });
    }
}
