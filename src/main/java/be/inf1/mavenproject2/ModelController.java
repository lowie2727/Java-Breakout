package be.inf1.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Bal;
import model.Paneel;
import model.Peddel;
import model.Steen;
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
    private Button startButton;

    @FXML
    private Button resetButton;

    @FXML
    private TextField textBox;

    private Peddel peddelModel;
    private Steen steenModel;
    private Bal balModel;
    private Paneel vensterModel;
    private Stenen stenenModel;

    private BalView balView;
    private PeddelView peddelView;
    private PaneelView paneelView;
    private StenenView stenenView;

    private int n;

    @FXML
    void initialize() {
        vensterModel = new Paneel();
        paneel.setPrefSize(vensterModel.getBreedte(), vensterModel.getHoogte());

        steenModel = new Steen();
        peddelModel = new Peddel(vensterModel);
        balModel = new Bal(vensterModel, peddelModel);
        stenenModel = new Stenen(vensterModel, steenModel);

        peddelView = new PeddelView(peddelModel);
        balView = new BalView(balModel);
        paneelView = new PaneelView(vensterModel);
        stenenView = new StenenView(stenenModel);

        paneel.getChildren().addAll(peddelView, balView, paneelView, stenenView);
        update();

        peddelView.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);

        resetButton.setOnAction(this::reset);
        paneel.setOnMouseMoved(this::beweeg);
        startButton.setOnAction(this::start);

        UpdateBal b = new UpdateBal(balModel, this);
        Timer t = new Timer(true);
        t.scheduleAtFixedRate(b, 0, 1);

    }

    public void update() {
        if (n == 1) {
            botsingBal();
            peddelView.update();
            stenenView.update();
            balView.update();
        }
        textBox.setText(stenenView.aantalStenen() + "");
    }

    private void reset(ActionEvent e) {
        peddelModel.reset();
        balModel.reset();
        balView.update();
        peddelView.update();
        stenenView.maakStenen();
        n = 0;
    }

    private void start(ActionEvent e) {
        n = 1;
        balModel.setVx(0.5);
        balModel.setVy(-0.5);
    }

    private void beweeg(MouseEvent m) {
        peddelModel.setX(m.getX() - (peddelModel.getBreedte()) / 2);
        peddelModel.setMin();
        peddelModel.setMax();
        stenenView.update();
    }

    private void botsingBal() {
        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();
        ObservableList<Node> ballen = balView.getChildrenUnmodifiable();

        if (balModel.getY() + balModel.getStraal() >= peddelModel.getY() - 3
                && balModel.getY() + balModel.getStraal() <= peddelModel.getY() + 3
                && balModel.getX() + balModel.getStraal() > peddelModel.getX()
                && balModel.getX() + balModel.getStraal() < peddelModel.getX() + peddelModel.getBreedte()) {
            balModel.setVy(-0.5);
        }

        for (Node s : stenen) {
            for (Node b : ballen) {
                Bounds boundSteen = s.localToParent(s.getBoundsInLocal());
                //b.localToParent(Point2D.ZERO)
                if (balModel.getOBorder() >= boundSteen.getMinY() - 3 //onderkant bal
                        && balModel.getOBorder() <= boundSteen.getMinY() + 3
                        && balModel.getX() >= boundSteen.getMinX()
                        && balModel.getX() <= boundSteen.getMinX() + boundSteen.getWidth()) {
                    balModel.setVy(-0.5);
                    s.setId("geraakt");
                } else if (balModel.getBBorder() >= boundSteen.getMaxY() - 3 //bovenkant bal
                        && balModel.getBBorder() <= boundSteen.getMaxY() + 3
                        && balModel.getX() >= boundSteen.getMinX()
                        && balModel.getX() <= boundSteen.getMinX() + boundSteen.getWidth()) {
                    balModel.setVy(0.5);
                    s.setId("geraakt");
                } else if (balModel.getRBorder() >= boundSteen.getMinX() - 3 //rechterkant bal
                        && balModel.getRBorder() <= boundSteen.getMinX() + 3
                        && balModel.getY() >= boundSteen.getMinY()
                        && balModel.getY() <= boundSteen.getMinY() + boundSteen.getHeight()) {
                    balModel.setVx(-0.5);
                    s.setId("geraakt");
                } else if (balModel.getLBorder() >= boundSteen.getMaxX() - 3 //linkerkant bal
                        && balModel.getLBorder() <= boundSteen.getMaxX() + 3
                        && balModel.getY() >= boundSteen.getMinY()
                        && balModel.getY() <= boundSteen.getMinY() + boundSteen.getHeight()) {
                    balModel.setVx(0.5);
                    s.setId("geraakt");
                } else if (balModel.getBRLBorderY() >= boundSteen.getMaxY() - 5 //linksboven bal
                        && balModel.getBRLBorderY() <= boundSteen.getMaxY()
                        && balModel.getLOBBorderX() >= boundSteen.getMaxX() - 5
                        && balModel.getLOBBorderX() <= boundSteen.getMaxX()) {
                    balModel.setVy(0.5);
                    balModel.setVx(0.5);
                    s.setId("geraakt");
                } else if (balModel.getROBBorderX() >= boundSteen.getMinX() //rechtsonder bal
                        && balModel.getROBBorderX() <= boundSteen.getMinX() + 5
                        && balModel.getORLBorderY() >= boundSteen.getMinY()
                        && balModel.getORLBorderY() <= boundSteen.getMinY() + 5) {
                    balModel.setVx(-0.5);
                    balModel.setVy(-0.5);
                    s.setId("geraakt");
                } else if (balModel.getROBBorderX() >= boundSteen.getMinX() //rechtsboven bal
                        && balModel.getROBBorderX() <= boundSteen.getMinX() + 5
                        && balModel.getBRLBorderY() >= boundSteen.getMaxY() - 5
                        && balModel.getBRLBorderY() <= boundSteen.getMaxY()) {
                    balModel.setVx(-0.5);
                    balModel.setVy(0.5);
                    s.setId("geraakt");

                } else if (balModel.getLOBBorderX() >= boundSteen.getMaxX() - 5 //linksonder bal
                        && balModel.getLOBBorderX() <= boundSteen.getMaxX()
                        && balModel.getORLBorderY() >= boundSteen.getMinY()
                        && balModel.getORLBorderY() <= boundSteen.getMinY() + 5) {
                    balModel.setVx(0.5);
                    balModel.setVy(-0.5);
                    s.setId("geraakt");
                }
            }
        }
    }
}
