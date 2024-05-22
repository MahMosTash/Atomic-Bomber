package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Building;
import model.Game;
import model.Tree;

public class TreeTransition extends Transition {
    private final double speed = 0.4;
    private final Game game;
    private final Tree tree;
    private final Pane root;

    public TreeTransition(Game game, Tree tree, Pane root) {
        this.game = game;
        this.tree = tree;
        this.root = root;
        game.animations.add(this);

        tree.setAnimation(this);
        setCycleDuration(Duration.seconds(10));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        tree.setX(tree.getX() - speed);
        if (tree.getX() < -tree.WIDTH){
            root.getChildren().remove(tree);
            game.enemies.remove(tree);
        }

    }
}
