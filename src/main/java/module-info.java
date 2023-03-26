module com.codingnuts.game {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.codingnuts.game to javafx.fxml;
    exports com.codingnuts.game;
}
