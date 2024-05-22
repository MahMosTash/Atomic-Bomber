package view.animations;

import controller.GameController;
import enums.Medias;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Airplane;
import model.Explosion;
import model.Game;

import java.util.Objects;

public class AirplaneTransition extends Transition {
    private final Game game;
    private final Airplane airplane;
    private final Pane root;
    private final double speed = 2.5;
    private boolean up, down, left, right;
    private boolean isFlipped = false;

    public AirplaneTransition(Game game, Airplane airplane, Pane pane) {
        this.game = game;
        this.airplane = airplane;
        this.root = pane;
        game.animations.add(this);
        airplane.setAnimation(this);
        setCycleDuration(Duration.seconds(5));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double piAngle = 2 * Math.PI;
        double dAngle = Math.PI / 180;
        airplane.setRotate(-airplane.angle * 180 / Math.PI);
        if ((this.airplane.angle >= (piAngle / 4) && this.airplane.angle < (3 * piAngle / 4)) && !isFlipped) {
            isFlipped = true;
            airplane.setScaleY(-1);
        }
        if (((this.airplane.angle > 0 && this.airplane.angle < (piAngle / 4)) || (this.airplane.angle >= (3 * piAngle / 4) && this.airplane.angle < (piAngle))) && isFlipped) {
            isFlipped = false;
            airplane.setScaleY(1);
        }
        if (airplane.angle < 0) {
            this.airplane.angle = piAngle + this.airplane.angle;
        } else if (airplane.angle > piAngle) {
            this.airplane.angle = this.airplane.angle - piAngle;
        }

        double y = airplane.getY() - speed * Math.sin(airplane.angle);
        double x = airplane.getX() + speed * Math.cos(airplane.angle);

        if (isUp()) {
            if ((this.airplane.angle >= (piAngle / 4) && this.airplane.angle < (3 * piAngle / 4))) {
                airplane.angle -= dAngle;
            }
            if ((this.airplane.angle > 0 && this.airplane.angle < (piAngle / 4)) || (this.airplane.angle >= (3 * piAngle / 4) && this.airplane.angle < (piAngle))) {
                airplane.angle += dAngle;
            }
        }
        if (isDown()) {
            if ((this.airplane.angle >= (piAngle / 4) && this.airplane.angle < (3 * piAngle / 4))) {
                airplane.angle += dAngle;
            }
            if ((this.airplane.angle > 0 && this.airplane.angle < (piAngle / 4)) || (this.airplane.angle >= (3 * piAngle / 4) && this.airplane.angle < (piAngle))) {
                airplane.angle -= dAngle;
            }
        }
        if (isRight()) {
            if ((this.airplane.angle < (piAngle / 2) && this.airplane.angle >= 0)) {
                airplane.angle -= dAngle;
            }
            if ((this.airplane.angle >= (piAngle / 2) && this.airplane.angle < piAngle)) {
                airplane.angle += dAngle;
            }
        }
        if (isLeft()) {
            if ((this.airplane.angle >= 0 && this.airplane.angle < (piAngle / 2))) {
                airplane.angle += dAngle;
            }
            if ((this.airplane.angle >= (piAngle / 2) && this.airplane.angle < piAngle)) {
                airplane.angle -= dAngle;
            }
        }
        if (y <= 0) {
            this.airplane.angle = piAngle - this.airplane.angle;
            y = airplane.getY();
        }
        if (x <= -airplane.WIDTH) {
            x = root.getWidth();
        } else if (x >= root.getWidth()) {
            x = -airplane.WIDTH;
        }
        airplane.setY(y);
        airplane.setX(x);
        if (y > game.HEIGHT - 125 - airplane.HEIGHT) {
            game.getGameLauncher().airplane = null;
            root.requestFocus();
            Medias.Explosion.getMediaPlayer().play();
            AirplaneExplosion airplaneExplosion = new AirplaneExplosion(airplane, root);
            airplaneExplosion.play();
            game.getGameLauncher().addRevive();
            System.out.println("Game Over!");
        }
        for (Explosion child : game.enemies) {
            if (child.getBoundsInParent().intersects(airplane.getBoundsInParent())) {
                if (child.isHit()) continue;
                Medias.Explosion.getMediaPlayer().play();
                child.setHit(true);
                EnemyExplosion enemyExplosion = new EnemyExplosion(child, root, game.enemies);
                enemyExplosion.play();
                game.getGameLauncher().airplane = null;
                root.requestFocus();
                AirplaneExplosion airplaneExplosion = new AirplaneExplosion(airplane, root);
                airplaneExplosion.play();
                game.getGameLauncher().addRevive();
                this.stop();
                break;
            }
        }
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

}
