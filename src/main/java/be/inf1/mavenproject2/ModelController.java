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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Bal;
import model.Ballen;
import model.Paneel;
import model.Peddel;
import model.PowerUp;
import model.Steen;
import model.Stenen;
import view.BallenView;
import view.PaneelView;
import view.PeddelView;
import view.PowerUpView;
import view.StenenView;
import view.VeldView;

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

    private Peddel peddelModel;
    private Bal balModel;
    private Steen steenModel;
    private Paneel paneelModel;
    private Stenen stenenModel;
    private Ballen ballenModel;
    private PowerUp powerUpModel;

    private PeddelView peddelView;
    private PaneelView paneelView;
    private StenenView stenenView;
    private VeldView veldView;
    private BallenView ballenView;
    private PowerUpView powerUpView;

    private boolean status;
    private Timer timerBal;
    private Timer timerPeddel;

    @FXML
    void initialize() {

        paneelModel = new Paneel(1000, 500);    //breedte, hoogte
        paneel.setPrefSize(paneelModel.getBreedte(), paneelModel.getHoogte());

        steenModel = new Steen(60, 20);  //breedte, hoogte
        ballenModel = new Ballen(paneelModel, 1);  //aantalBallen
        peddelModel = new Peddel(500, 10, paneelModel);  //breedte, hoogte
        stenenModel = new Stenen(paneelModel, steenModel, 3, 500);  //rijen, kolommen
        powerUpModel = new PowerUp(30);

        ballenView = new BallenView(ballenModel, peddelModel, paneelModel);
        paneelView = new PaneelView(paneelModel);
        peddelView = new PeddelView(peddelModel);
        paneelView = new PaneelView(paneelModel);
        stenenView = new StenenView(stenenModel);
        powerUpView = new PowerUpView(powerUpModel, paneelModel);

        paneel.getChildren().addAll(peddelView, paneelView, stenenView, ballenView, powerUpView);

        update();

        startButton.setFocusTraversable(true);

        resetButton.setOnAction(this::reset);
        startButton.setOnAction(this::start);
        paneel.setOnMouseMoved(this::beweegPeddel);
        gaNaarStartButton.setOnAction(this::gaNaarStart);
    }

    public void update() {
        if (!status) {
            for (Node b : ballenView.getChildrenUnmodifiable()) {
                b.setId("9");
            }

        }

        if (status) {
            peddelView = veldView.getPeddelView();
            peddelView.update();
            veldView.update();
            labelTijd.setText(veldView.timerPeddel());

        }
        label.setText(stenenView.getAantalStenen() + "");
    }

    private void start(ActionEvent e) {

        if (!status) {
            timerPeddel = new Timer(true);
            TimerPeddel t = new TimerPeddel();
            timerPeddel.scheduleAtFixedRate(t, 0, 1000);

            timerBal = new Timer(true);
            veldView = new VeldView(stenenView, peddelModel, ballenView, peddelView, powerUpView,
                    powerUpModel, t, paneelModel, paneel);

            for (Bal bal : ballenModel.getBallen()) {
                UpdateBal b = new UpdateBal(bal, this);
                timerBal.scheduleAtFixedRate(b, 0, 5);
            }
            status = true;
        }

    }

    public void reset(ActionEvent e) {
        if (status) {
            veldView.reset();
            timerBal.cancel();
            status = false;
        }
    }

    private void beweegPeddel(MouseEvent m) {
        peddelModel.setX(m.getX() - (peddelModel.getBreedte()) / 2);
        peddelModel.setMin();
        peddelModel.setMax();
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
        } catch (IOException io) {
        } catch (NullPointerException nu) {
        }
    }
}
