package be.inf1.atariBreakout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class InstellingenController {

    @FXML
    private Button gaNaarStart;

    @FXML
    private Button okButton;

    @FXML
    private Slider sliderPeddel;

    @FXML
    private Slider sliderRijen;

    @FXML
    private Slider sliderStraalBal;

    @FXML
    private Slider sliderPowerUpDuration;

    @FXML
    private Slider sliderPowerUp;


    private static boolean klaarOmTeSpelen;

    /*
     *initialieerst de scene
     */
    @FXML
    void initialize() {
        gaNaarStart.setOnAction(this::gaNaarStart);
        okButton.setOnAction(this::bevestigVeranderingen);
    }

    /*
     * @param t is de ActionEvent die gekoppeld is aan de knop om naar de startpagina te gaan,
     * deze methode veranderd het scherm dat zichtbaar is
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

    /*
     * @param t is de ActionEvent die gekoppeld is aan de knop om de instellingen van de sliders te bevestigen
     * in deze methode worden de instellingen van de straal, aantalrijen, ....
     * gestuurd naar de modellen die deze nodig hebben
     */
    private void bevestigVeranderingen(ActionEvent t) {
        StartPaginaController.setPeddelBreedte((int) sliderPeddel.getValue());
        StartPaginaController.setAantalRijen((int) sliderRijen.getValue());
        StartPaginaController.setStraalBal((int) sliderStraalBal.getValue());
        StartPaginaController.setIntervalPowerUpDuration((int) sliderPowerUpDuration.getValue());
        StartPaginaController.setIntervalPowerUp((int) sliderPowerUp.getValue());
        klaarOmTeSpelen = true;
    }

    /**
     * @return klaarOmTeSpelen geeft de toestand terug of de ok knop is
     * ingeklikt om de instellingen te bevestigen
     */
    public static boolean isKlaarOmTeSpelen() {
        return klaarOmTeSpelen;
    }
}
