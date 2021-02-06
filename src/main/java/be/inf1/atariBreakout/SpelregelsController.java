package be.inf1.atariBreakout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SpelregelsController {

    @FXML
    private Button gaNaarStartButton;

    /*
     * initialiseert de scene
     */
    @FXML
    void initialize() {
        gaNaarStartButton.setOnAction(this::gaNaarStart);
    }

    /*
     * @param t is de ActionEvent die gekoppeld is aan de knop om terug te gaan naar de startpagina
     */
    private void gaNaarStart(ActionEvent t) {
        try {
            Parent startPaginaParent = FXMLLoader.load(getClass().getResource("startPagina.fxml"));
            Scene modelScene = new Scene(startPaginaParent);
            Stage startScherm = (Stage) ((Node) t.getSource()).getScene().getWindow();
            startScherm.hide();
            startScherm.setTitle("Atari Breakout");
            startScherm.setScene(modelScene);
            startScherm.show();
        } catch (IOException io) {
        }
    }
}
