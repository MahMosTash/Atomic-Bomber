package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Airplane;
import model.Game;
import model.Radioactive;

public class AddRadioactiveTransition extends Transition {
    private final Radioactive radioactive;
    private final double speed = 1;
    private final Airplane airplane;
    private final Game game;
    private final Pane root;

    public AddRadioactiveTransition(Radioactive radioactive, Airplane airplane, Game game, Pane root) {
        this.radioactive = radioactive;
        this.airplane = airplane;
        this.game = game;
        this.root = root;
        game.animations.add(this);
        setCycleDuration(Duration.seconds(5));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        radioactive.setY(radioactive.getY() - speed);
        if (radioactive.getY() < 0) {
            root.getChildren().remove(radioactive);
        }
        if (radioactive.getBoundsInParent().intersects(airplane.getBoundsInParent())) {
            game.addRadioActiveBomb();
            root.getChildren().remove(radioactive);
            this.stop();
        }
    }
}
