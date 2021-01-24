package be.inf1.mavenproject2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class InstellingenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    private static int breedtePeddel;
    private static int rijenAantal;
    private static int balStraal;
    private static int durationPowerUp;
    private static int powerUpInterval;

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
        breedtePeddel = (int) sliderPeddel.getValue();
        StartPaginaController.setAantalRijen((int) sliderRijen.getValue());
        rijenAantal = (int) sliderRijen.getValue();
        StartPaginaController.setStraalBal((int) sliderStraalBal.getValue());
        balStraal = (int) sliderStraalBal.getValue();
        StartPaginaController.setIntervalPowerUpDuration((int) sliderPowerUpDuration.getValue());
        durationPowerUp = (int) sliderPowerUpDuration.getValue();
        StartPaginaController.setIntervalPowerUp((int) sliderPowerUp.getValue());
        powerUpInterval = (int) sliderPowerUp.getValue();
        klaarOmTeSpelen = true;
    }

    /**
     * @return breedtePeddel geeft de breedte van de peddel terug
     */
    public static int getBreedtePeddel() {
        return breedtePeddel;
    }

    /**
     * @return het rijenAantal geeft het aantal rijen stenen terug
     */
    public static int getRijenAantal() {
        return rijenAantal;
    }

    /**
     * @return de balStraal geeft de straal van de bal terug
     */
    public static int getBalStraal() {
        return balStraal;
    }

    /**
     * @return de durationPowerUp geeft de tijd terug hoelang een PowerUp duurt
     */
    public static int getDurationPowerUp() {
        return durationPowerUp;
    }

    /**
     * @return de powerUpInterval geeft de tijd terug wanneer een nieuwe PowerUp
     * tevoorschijn mag komen
     */
    public static int getPowerUpInterval() {
        return powerUpInterval;
    }

    /**
     * @return klaarOmTeSpelen geeft de toestand terug of de ok knop is
     * ingeklikt om de instellingen te bevestigen
     */
    public static boolean isKlaarOmTeSpelen() {
        return klaarOmTeSpelen;
    }
}
