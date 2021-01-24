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
import javafx.scene.control.RadioButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StartPaginaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button spelregelsButton;

    @FXML
    private Button instellingenButton;

    @FXML
    private RadioButton level1;

    @FXML
    private RadioButton level2;

    @FXML
    private RadioButton level3;

    private static double straalBal;
    private static double peddelBreedte;
    private static int aantalRijen;
    private static int intervalPowerUp;
    private static int intervalPowerUpDuration;

    private @FXML
    void initialize() {
        startButton.setOnAction(this::gaNaarGame);
        exitButton.setOnAction(this::sluitGame);
        spelregelsButton.setOnAction(this::gaNaarSpelregels);
        instellingenButton.setOnAction(this::gaNaarInstellingen);
    }

    private void gaNaarGame(ActionEvent t) {
        if (level1.isSelected()) {
            straalBal = 10;
            peddelBreedte = 500;
            aantalRijen = 2;
            intervalPowerUp = 10;
            intervalPowerUpDuration = 8;
        }
        if (level2.isSelected()) {
            straalBal = 7;
            peddelBreedte = 300;
            aantalRijen = 3;
            intervalPowerUp = 15;
            intervalPowerUpDuration = 6;
        }
        if (level3.isSelected()) {
            straalBal = 5;
            peddelBreedte = 150;
            aantalRijen = 4;
            intervalPowerUp = 20;
            intervalPowerUpDuration = 4;
        }
        if (!level1.isSelected() && !level2.isSelected() && !level3.isSelected() && InstellingenController.isKlaarOmTeSpelen() == false) {
            errorSound();
        }

        if (level1.isSelected() || level2.isSelected() || level3.isSelected() || InstellingenController.isKlaarOmTeSpelen()) {
            //startSound();
            try {
                Parent modelParent = FXMLLoader.load(getClass().getResource("model.fxml"));
                Scene modelScene = new Scene(modelParent, 1100, 600);
                Stage gameScherm = (Stage) ((Node) t.getSource()).getScene().getWindow();
                gameScherm.hide();
                gameScherm.setScene(modelScene);
                gameScherm.show();
            } catch (IOException io) {
            }
        }

    }

    private void sluitGame(ActionEvent t) {
        System.exit(0);
    }

    private void gaNaarSpelregels(ActionEvent t) {
        try {
            Parent modelParent = FXMLLoader.load(getClass().getResource("spelregels.fxml"));
            Scene modelScene = new Scene(modelParent, 1100, 600);
            Stage gameScherm = (Stage) ((Node) t.getSource()).getScene().getWindow();
            gameScherm.hide();
            gameScherm.setTitle("Atari Breakout");
            gameScherm.setScene(modelScene);
            gameScherm.show();
        } catch (IOException io) {
        }
    }

    private void gaNaarInstellingen(ActionEvent t) {
        try {
            Parent modelParent = FXMLLoader.load(getClass().getResource("instellingen.fxml"));
            Scene modelScene = new Scene(modelParent, 1100, 600);
            Stage gameScherm = (Stage) ((Node) t.getSource()).getScene().getWindow();
            gameScherm.hide();
            gameScherm.setTitle("Atari Breakout");
            gameScherm.setScene(modelScene);
            gameScherm.show();
        } catch (IOException io) {
        }
    }

    /**
     * @return the straalBal
     */
    public static double getStraalBal() {
        return straalBal;
    }

    /**
     * @return the peddelBreedte
     */
    public static double getPeddelBreedte() {
        return peddelBreedte;
    }

    /**
     * @return the aantalRijen
     */
    public static int getAantalRijen() {
        return aantalRijen;
    }

    /**
     * @return the intervalPowerUp
     */
    public static int getIntervalPowerUp() {
        return intervalPowerUp;
    }

    /**
     * @return the intervalPowerUpDuration
     */
    public static int getIntervalPowerUpDuration() {
        return intervalPowerUpDuration;
    }

    MediaPlayer mediaPlayer;

    public void errorSound() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("erro.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    /*public void startSound() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("start.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }*/
    /**
     * @param aPeddelBreedte the peddelBreedte to set
     */
    public static void setPeddelBreedte(double aPeddelBreedte) {
        peddelBreedte = aPeddelBreedte;
    }

    /**
     * @param aAantalRijen the aantalRijen to set
     */
    public static void setAantalRijen(int aAantalRijen) {
        aantalRijen = aAantalRijen;
    }

    /**
     * @param aStraalBal the straalBal to set
     */
    public static void setStraalBal(double aStraalBal) {
        straalBal = aStraalBal;
    }

    /**
     * @param aIntervalPowerUp the intervalPowerUp to set
     */
    public static void setIntervalPowerUp(int aIntervalPowerUp) {
        intervalPowerUp = aIntervalPowerUp;
    }

    /**
     * @param aIntervalPowerUpDuration the intervalPowerUpDuration to set
     */
    public static void setIntervalPowerUpDuration(int aIntervalPowerUpDuration) {
        intervalPowerUpDuration = aIntervalPowerUpDuration;
    }
}
