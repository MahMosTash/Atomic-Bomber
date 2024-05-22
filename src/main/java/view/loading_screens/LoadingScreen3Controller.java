package view.loading_screens;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import view.Main;
import view.menus.ProfileMenu;

public class LoadingScreen3Controller {
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
            for (int i = 0; i < 40; i++) {
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
                new ProfileMenu().start(Main.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
