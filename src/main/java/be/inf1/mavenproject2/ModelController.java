package be.inf1.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    private Label label;

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
        peddelModel = new Peddel(300, 10, paneelModel);  //breedte, hoogte
        stenenModel = new Stenen(paneelModel, steenModel, 2, 500);  //rijen, kolommen
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
    }

    public void update() {
        if (!status) {
            for (Node b : ballenView.getChildrenUnmodifiable()) {
                b.setId("9");
            }
        }

        if (status) {
            peddelView.update();
            veldView.update();
        }
        label.setText(stenenView.getAantalStenen() + "");
    }

    private void start(ActionEvent e) {

        if (!status) {
            timerPeddel = new Timer(true);
            TimerPeddel t  = new TimerPeddel();
            timerPeddel.scheduleAtFixedRate(t, 0, 1000);
            timerBal = new Timer(true);
            veldView = new VeldView(stenenView, peddelModel, ballenView, peddelView, powerUpView, powerUpModel);

            for (Bal bal : ballenModel.getBallen()) {
                UpdateBal b = new UpdateBal(bal, this);
                timerBal.scheduleAtFixedRate(b, 0, 1);
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
}
