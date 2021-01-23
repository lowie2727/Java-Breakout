package be.inf1.mavenproject2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Bal;
import model.Paneel;
import model.Spel;
import view.SpelView;

public class ModelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane paneel;

    @FXML
    private Button startButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button gaNaarStartButton;

    @FXML
    private Label label;

    @FXML
    private Label labelTijd;

    private Spel spel;
    private SpelView spelView;

    private Paneel paneelModel;

    private boolean status;
    private Timer timerBal;
    private Timer timerPeddel;
    
    private TimerPeddel t;

    @FXML
    void initialize() {
        
        timerPeddel = new Timer(true);
            t = new TimerPeddel();
            timerPeddel.scheduleAtFixedRate(t, 0, 1000);
        
        paneelModel = new Paneel(1000, 500);    //breedte, hoogte
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

    public void update() {

        if (status) {
            spel.update();
            spelView.update();
            spel.getPeddel().tick();
            //labelTijd.setText(veldView.timerPeddel());
        }
        //label.setText(stenenView.getAantalStenen() + "");
    }

    private void start(ActionEvent e) {
        if (!status) {
            for (Bal bal : spel.getBallen().getBallen()) {
                bal.setVx(bal.getSnelheidX());
                bal.setVy(bal.getSnelheidY());
            }
            //paneel.getChildren().clear();
            //paneel.getChildren().addAll(peddelView, paneelView, stenenView, ballenView, powerUpView);

            

            timerBal = new Timer(true);

            for (Bal bal : spel.getBallen().getBallen()) {
                UpdateBal b = new UpdateBal(bal, this);
                timerBal.scheduleAtFixedRate(b, 0, 16);
            }
            status = true;
        }

    }

    public void reset(ActionEvent e) {
        if (status) {
            timerBal.cancel();
            timerPeddel.cancel();
            status = false;
        }
    }

    private void beweegPeddel(MouseEvent m) {
        spel.getPeddel().setX(m.getX() - (spel.getPeddel().getHuidigeBreedte()) / 2);

    }

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
        } catch (IOException io) {
        } catch (NullPointerException nu) {
        }
    }

    MediaPlayer mediaPlayer;

    public void speelMuziek() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("startGeluid.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    public void toetsGeluid2() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("m.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }
}
