module AtomicBomber {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires com.google.gson;

    exports view;
    opens view to javafx.fxml;
    exports view.menus;
    opens view.menus to javafx.fxml;
    exports view.loading_screens;
    opens view.loading_screens to javafx.fxml;
    exports model;
    opens model to com.google.gson,com.fasterxml.jackson.databind;}