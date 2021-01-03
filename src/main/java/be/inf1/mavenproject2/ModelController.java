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
            Bounds boundSteen = s.localToParent(s.getBoundsInLocal());
            for (Node b : ballen) {

                Point2D middelPunt = b.localToParent(Point2D.ZERO);
                Bounds boundsBal = b.localToParent(b.getBoundsInLocal());
                if (middelPunt.getY() + balModel.getStraal() >= boundSteen.getMinY() - 3 //onderkant bal
                        && middelPunt.getY() + balModel.getStraal() <= boundSteen.getMinY() + 3
                        && middelPunt.getX() >= boundSteen.getMinX()
                        && middelPunt.getX() <= boundSteen.getMinX() + boundSteen.getWidth()) {
                    balModel.setVy(-0.5);
                    b.setId("1");
                    s.setId("geraakt");
                } else if (middelPunt.getY() - balModel.getStraal() >= boundSteen.getMaxY() - 3 //bovenkant bal
                        && middelPunt.getY() - balModel.getStraal() <= boundSteen.getMaxY() + 3
                        && middelPunt.getX() >= boundSteen.getMinX()
                        && middelPunt.getX() <= boundSteen.getMinX() + boundSteen.getWidth()) {
                    b.setId("2");
                    balModel.setVy(0.5);
                    s.setId("geraakt");
                } else if (middelPunt.getX() + balModel.getStraal() >= boundSteen.getMinX() - 3 //rechterkant bal
                        && middelPunt.getX() + balModel.getStraal() <= boundSteen.getMinX() + 3
                        && middelPunt.getY() >= boundSteen.getMinY()
                        && middelPunt.getY() <= boundSteen.getMinY() + boundSteen.getHeight()) {
                    b.setId("3");
                    balModel.setVx(-0.5);
                    s.setId("geraakt");
                } else if (middelPunt.getX() - balModel.getStraal() >= boundSteen.getMaxX() - 3 //linkerkant bal
                        && middelPunt.getX() - balModel.getStraal() <= boundSteen.getMaxX() + 3
                        && middelPunt.getY() >= boundSteen.getMinY()
                        && middelPunt.getY() <= boundSteen.getMinY() + boundSteen.getHeight()) {
                    b.setId("4");
                    balModel.setVx(0.5);
                    s.setId("geraakt");
                } else if (boundsBal.getMinY() >= boundSteen.getMaxY() - 5 //linksboven bal
                        && boundsBal.getMinY() <= boundSteen.getMaxY()
                        && boundsBal.getMinX() >= boundSteen.getMaxX() - 5
                        && boundsBal.getMinX() <= boundSteen.getMaxX()) {
                    b.setId("5");
                    balModel.setVy(0.5);
                    balModel.setVx(0.5);
                    s.setId("geraakt");
                } else if (boundsBal.getMaxX() >= boundSteen.getMinX() //rechtsonder bal
                        && boundsBal.getMaxX() <= boundSteen.getMinX() + 5
                        && boundsBal.getMaxY() >= boundSteen.getMinY()
                        && boundsBal.getMaxY() <= boundSteen.getMinY() + 5) {
                    b.setId("6");
                    balModel.setVx(-0.5);
                    balModel.setVy(-0.5);
                    s.setId("geraakt");
                } else if (boundsBal.getCenterX() + balModel.getCos() >= boundSteen.getMinX() //rechtsboven bal
                        && boundsBal.getCenterX() + balModel.getCos() <= boundSteen.getMinX() + 5
                        && boundsBal.getCenterY() - balModel.getCos() >= boundSteen.getMaxY() - 5
                        && boundsBal.getCenterY() - balModel.getCos() <= boundSteen.getMaxY()) {
                    b.setId("7");
                    balModel.setVx(-0.5);
                    balModel.setVy(0.5);
                    s.setId("geraakt");

                } else if (boundsBal.getCenterX() - balModel.getCos() >= boundSteen.getMaxX() - 5 //linksonder bal
                        && boundsBal.getCenterX() - balModel.getCos() <= boundSteen.getMaxX()
                        && boundsBal.getCenterY() + balModel.getCos() >= boundSteen.getMinY()
                        && boundsBal.getCenterY() + balModel.getCos() <= boundSteen.getMinY() + 5) {
                    b.setId("8");
                    balModel.setVx(0.5);
                    balModel.setVy(-0.5);
                    s.setId("geraakt");
                }
            }
        }
    }
}
