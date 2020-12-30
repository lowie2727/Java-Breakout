package be.inf1.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Bal;
import model.Paneel;
import model.Peddel;
import model.Steen;
import model.Stenen;
import view.BalView;
import view.PaneelView;
import view.PeddelView;
import view.SteenView;
import view.StenenView;

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
    private Stenen stenen;

    private BalView balView;
    private PeddelView peddelView;
    private PaneelView paneelView;
    private StenenView stenenView;

    private Steen steen;
    private SteenView steenView;

    @FXML
    void initialize() {
        venster = new Paneel();
        paneel.setPrefSize(venster.getBreedte(), venster.getHoogte());

        peddel = new Peddel(venster);
        bal = new Bal(venster, peddel);
        stenen = new Stenen();
        //steen = new Steen();

        peddelView = new PeddelView(peddel);
        balView = new BalView(bal);
        paneelView = new PaneelView(venster);
        //steenView = new SteenView(steen);
        stenenView = new StenenView(stenen);

        paneel.getChildren().addAll(peddelView, balView, paneelView, steenView);
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
        Rectangle r = peddelView.getRechthoek();
        Bounds boundsR = r.localToScene(r.getBoundsInLocal());
        Circle c = balView.getBal();
        Bounds boundsC = c.localToScene(c.getBoundsInLocal());
        if (boundsC.intersects(boundsR)) {
            if (bal.getVy() > 0) {
                bal.vy = bal.vy * -1;
            }
        }
        peddelView.update();
        balView.update();

    }

    private void reset(ActionEvent e) {
        bal.reset();
        peddel.reset();
    }

    private void beweeg(MouseEvent m) {
        peddel.setX(m.getX() - (peddel.getBreedte()) / 2);
        peddel.setMin();
        peddel.setMax();
    }
}
