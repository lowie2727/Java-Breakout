package be.inf1.atariBreakout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Bal;
import model.Paneel;
import model.Spel;
import view.SpelView;

import java.io.IOException;
import java.util.Timer;

public class ModelController {

    @FXML
    private Pane paneel;

    @FXML
    private Button startButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button gaNaarStartButton;

    @FXML
    private Label labelAantalStenen;

    @FXML
    private Label labelTijd;

    @FXML
    private Label labelTotaleTijd;

    private Spel spel;
    private SpelView spelView;
    private Paneel paneelModel;

    private boolean status;

    private MediaPlayer mediaPlayer;

    private Timer timerBal;
    private Timer timerPeddel;
    private TimerPeddel t;

    /*
     * initialeerst de scene
     */
    @FXML
    void initialize() {

        timerPeddel = new Timer(true);
        t = new TimerPeddel();
        timerPeddel.scheduleAtFixedRate(t, 0, 1000);

        paneelModel = new Paneel(1000, 500);
        paneel.setPrefSize(paneelModel.getBreedte(), paneelModel.getHoogte());

        spel = new Spel(paneelModel, t);
        spelView = new SpelView(spel, paneel, paneelModel);

        update();

        startButton.setFocusTraversable(false);

        resetButton.setOnAction(this::reset);
        startButton.setOnAction(this::start);
        paneel.setOnMouseMoved(this::beweegPeddel);
        gaNaarStartButton.setOnAction(this::gaNaarStart);
    }

    /*
     * deze methode update het spel aan de hand van tickmethode in UpdateBal
     */
    public void update() {
        if (status) {
            spel.update();
            spelView.update();
            labelTijd.setText(spel.labelPowerUp());
        }
        labelAantalStenen.setText(spel.getAantalStenen() + "");
        labelTotaleTijd.setText(t.getTijdTotaal() + "");
    }

    /*
     * @param e is de ActionEvent die gekoppeld is aan de knop om het spel te starten
     */
    private void start(ActionEvent e) {
        if (!status) {
            for (Bal bal : spel.getBallen()) {
                bal.setVx(bal.getSnelheidX());
                bal.setVy(bal.getSnelheidY());
            }

            timerBal = new Timer(true);
            for (Bal bal : spel.getBallen()) {
                UpdateBal b = new UpdateBal(bal, this);
                timerBal.scheduleAtFixedRate(b, 0, 16);
            }
            status = true;
        }

    }

    /*
     *@param e is de ActionEvent die gekoppeld is aan de knop om het spel te resetten
     */
    private void reset(ActionEvent e) {
        if (status) {
            spel.reset();
            spelView.update();
            timerBal.cancel();
            t.setTijdTotaal();
            status = false;
        }
    }

    /*
     * @param m is de MouseEvent die gekoppeld is aan het bewegen van de mouse om de peddel te bewegen
     */
    private void beweegPeddel(MouseEvent m) {
        if (m.getX() + spel.getPeddel().getHuidigeBreedte() / 2 > paneelModel.getBreedte()) {
            spel.getPeddel().setX(paneelModel.getBreedte() - spel.getPeddel().getHuidigeBreedte());
        } else if (m.getX() - spel.getPeddel().getHuidigeBreedte() / 2 < 0) {
            spel.getPeddel().setX(0);
        } else {
            spel.getPeddel().setX(m.getX() - spel.getPeddel().getHuidigeBreedte() / 2);
        }
    }

    /*
     * @param t is de ActionEvent die gekoppeld is aan de knop om naar de startpagina te gaan
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
            timerBal.cancel();
            timerPeddel.cancel();
        } catch (IOException | NullPointerException io) {
        }
    }
}
