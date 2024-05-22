package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Tree;
import model.Trench;

public class TrenchTransition extends Transition {
    private final double speed = 0.4;
    private final Game game;
    private final Trench trench;
    private final Pane root;

    public TrenchTransition(Game game, Trench trench, Pane root) {
        this.game = game;
        this.trench = trench;
        this.root = root;
        game.animations.add(this);

        trench.setAnimation(this);
        setCycleDuration(Duration.seconds(10));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        trench.setX(trench.getX() - speed);
        if (trench.getX() < -trench.WIDTH){
            root.getChildren().remove(trench);
            game.enemies.remove(trench);
        }

    }
}
