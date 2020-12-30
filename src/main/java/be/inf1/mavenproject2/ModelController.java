package be.inf1.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Bal;
import model.Paneel;
import model.Peddel;
import view.BalView;
import view.PeddelView;

public class ModelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane paneel;

    @FXML
    private Button button;

    private Peddel peddel;
    private Bal bal;
    private Paneel venster;

    private BalView balView;
    private PeddelView peddelView;

    private @FXML
    void initialize() {
        venster = new Paneel();
        paneel.setPrefSize(venster.getBreedte(), venster.getHoogte());

        peddel = new Peddel(venster);
        bal = new Bal(venster, peddel);

        peddelView = new PeddelView(peddel);
        balView = new BalView(bal);

        paneel.getChildren().addAll(peddelView, balView);
        update();

        peddelView.setFocusTraversable(true);
        button.setFocusTraversable(false);

        button.setOnAction(this::reset);
        paneel.setOnMouseMoved(this::beweeg);

        UpdateBal b = new UpdateBal(bal, this);
        Timer t = new Timer(true);
        t.scheduleAtFixedRate(b, 0, 4);

    }

    public void update() {
        peddelView.update();
        balView.update();
    }

    private void reset(ActionEvent e) {
        bal.reset();
    }

    private void beweeg(MouseEvent m) {

        peddel.setX(m.getX() - (peddel.getBreedte()) / 2);
        peddel.setMin();
        peddel.setMax();
    }
}
