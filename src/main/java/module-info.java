module be.inf1.mavenproject2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.media;
    opens be.inf1.atariBreakout to javafx.fxml;
    exports be.inf1.atariBreakout;
}
