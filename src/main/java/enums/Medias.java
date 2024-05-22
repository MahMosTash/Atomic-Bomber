package enums;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view.Main;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Medias {
    DardeDoori("/Media/DardeDoori.m4a"),
    Azhir("/Media/Azhir.mp3"),
    Hitler("/Media/DamnThings.mp3"),
    Missile("/Media/Missile.mp3"),
    Cluster("/Media/Cluster.mp3"),
    Radioactive("/Media/Radioactive.mp3"),
    Explosion("/Media/explosion.mp3"),
    GameOver("/Media/GameOver.mp3");
    private final Media media;

    Medias(String pattern) {
        this.media = new Media(Objects.requireNonNull(Main.class.getResource(pattern)).toExternalForm());
    }

    public Media getMedia() {
        return media;
    }
    public MediaPlayer getMediaPlayer(){
        return new MediaPlayer(media);
    }
    public void play(){
        MediaPlayer mediaPlayer = getMediaPlayer();
        mediaPlayer.setVolume(5);
        mediaPlayer.play();
    }
}
