package view.loading_screens;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import model.App;
import view.GameLauncher;
import view.Main;

public class LoadingScreen6Controller {
    public ProgressBar progressBar;

    public void initialize() throws Exception {
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    Thread.sleep(10);
                    addProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(50);
                    addProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(5);
                    addProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(80);
                    addProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                    addProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finish();
        }).start();

    }

    public void addProgress() {
        progressBar.setProgress(progressBar.getProgress() + 0.01);
    }

    public void finish() {
        Platform.runLater(() -> {
            try {
                new GameLauncher(App.loggedInUser).start(Main.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
