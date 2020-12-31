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
        ObservableList<Node> peddel = peddelView.getChildrenUnmodifiable();
        ObservableList<Node> bal = balView.getChildrenUnmodifiable();
        //for(Node p : peddel){
            //p.getB
        //}
        
        peddel.get(0);
        Rectangle r = peddelView.getRechthoek();
        Bounds boundsR = r.localToScene(r.getBoundsInLocal());
        Circle c = balView.getBal();
        Bounds boundsC = c.localToScene(c.getBoundsInLocal());
        if (boundsC.intersects(boundsR)) {
            if (balModel.getVy() > 0) {
                balModel.setVy(-1);
            }
        }

        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();
        for (Node s : stenen) {
            Bounds bound = s.localToScene(s.getBoundsInLocal());
            if (boundsC.intersects(bound)) {
                if (boundsC.getMaxX() == bound.getMinX()) {
                     balModel.setVx(-1);
                } else if (boundsC.getMinX() == bound.getMaxX()) {
                    balModel.setVx(1);
                } else if (boundsC.getMinY() == bound.getMaxY()) {
                    balModel.setVy(1);
                } else if (boundsC.getMaxY() == bound.getMinY()) {
                    balModel.setVy(-1);
                }
            }

        }

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
}
