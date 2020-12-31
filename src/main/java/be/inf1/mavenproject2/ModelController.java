package be.inf1.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Bal;
import model.Paneel;
import model.Peddel;
import model.Stenen;
import view.BalView;
import view.PaneelView;
import view.PeddelView;
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

    private Peddel peddelModel;
    private Bal balModel;
    private Paneel vensterModel;
    private Stenen stenenModel;

    private BalView balView;
    private PeddelView peddelView;
    private PaneelView paneelView;
    private StenenView stenenView;

    @FXML
    void initialize() {
        vensterModel = new Paneel();
        paneel.setPrefSize(vensterModel.getBreedte(), vensterModel.getHoogte());

        peddelModel = new Peddel(vensterModel);
        balModel = new Bal(vensterModel, peddelModel);
        stenenModel = new Stenen();

        peddelView = new PeddelView(peddelModel);

        balView = new BalView(balModel);
        paneelView = new PaneelView(vensterModel);
        stenenView = new StenenView(stenenModel);

        paneel.getChildren().addAll(peddelView, balView, paneelView, stenenView);
        update();

        peddelView.setFocusTraversable(true);
        button.setFocusTraversable(false);

        button.setOnAction(this::reset);
        paneel.setOnMouseMoved(this::beweeg);

        UpdateBal b = new UpdateBal(balModel, this);
        Timer t = new Timer(true);
        t.scheduleAtFixedRate(b, 0, 4);

    }

    public void update() {
        botsingBal();
        peddelView.update();
        balView.update();
    }

    private void reset(ActionEvent e) {
        balModel.reset();
        peddelModel.reset();
    }

    private void beweeg(MouseEvent m) {
        peddelModel.setX(m.getX() - (peddelModel.getBreedte()) / 2);
        peddelModel.setMin();
        peddelModel.setMax();
    }

    private void botsingBal() {
        ObservableList<Node> peddel = peddelView.getChildrenUnmodifiable();
        ObservableList<Node> bal = balView.getChildrenUnmodifiable();
        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();

        for (Node b : bal) {
            Bounds boundBal = b.localToScene(b.getBoundsInLocal());
            for (Node s : stenen) {
                Bounds boundSteen = s.localToScene(s.getBoundsInLocal());
                if (boundBal.intersects(boundSteen)) {
                    if (boundBal.getMaxX() == boundSteen.getMinX()) {
                        balModel.setVx(-1);
                    } else if (boundBal.getMinX() == boundSteen.getMaxX()) {
                        balModel.setVx(1);
                    } else if (boundBal.getMinY() == boundSteen.getMaxY()) {
                        balModel.setVy(1);
                    } else if (boundBal.getMaxY() == boundSteen.getMinY()) {
                        balModel.setVy(-1);
                    }
                }
            }
            for (Node p : peddel) {
                Bounds boundPeddel = p.localToScene(p.getBoundsInLocal());
                if (boundBal.intersects(boundPeddel)) {
                    if (boundBal.getMaxX() == boundPeddel.getMinX()) {
                        balModel.setVx(-1);
                    } else if (boundBal.getMinX() == boundPeddel.getMaxX()) {
                        balModel.setVx(1);
                    } else if (boundBal.getMinY() == boundPeddel.getMaxY()) {
                        balModel.setVy(1);
                    } else if (boundBal.getMaxY() == boundPeddel.getMinY()) {
                        balModel.setVy(-1);
                    }
                }
            }
        }
    }
}
