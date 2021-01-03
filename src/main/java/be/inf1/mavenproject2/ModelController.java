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
import model.Ballen;
import model.Paneel;
import model.Peddel;
import model.Steen;
import model.Stenen;
import view.BalView;
import view.BallenView;
import view.PaneelView;
import view.PeddelView;
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
    private VeldView veldView;

    private int n;

    private BallenView ballenView;
    private Ballen ballen;

    @FXML
    void initialize() {
        vensterModel = new Paneel();
        paneel.setPrefSize(vensterModel.getBreedte(), vensterModel.getHoogte());

        steenModel = new Steen();
        peddelModel = new Peddel(vensterModel);
        balModel = new Bal(vensterModel, peddelModel);
        stenenModel = new Stenen(vensterModel, steenModel);

        ballen = new Ballen();
        ballenView = new BallenView(ballen);

        paneelView = new PaneelView(vensterModel);
        peddelView = new PeddelView(peddelModel);
        balView = new BalView(balModel);
        paneelView = new PaneelView(vensterModel);
        stenenView = new StenenView(stenenModel);

        paneel.getChildren().addAll(peddelView, balView, paneelView, stenenView, ballenView);
        //update();

        peddelView.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);

        resetButton.setOnAction(this::reset);
        startButton.setOnAction(this::start);
        paneel.setOnMouseMoved(this::beweeg);

        UpdateBal b = new UpdateBal(balModel, this);
        Timer t = new Timer(true);
        t.scheduleAtFixedRate(b, 0, 1);
    }

    public void update() {
        if (n == 1) {
            veldView = new VeldView(stenenView, peddelModel, balView, balModel);
            veldView.botsingBal();
            peddelView.update();
            stenenView.update();
            balView.update();
        }
        textBox.setText(stenenView.aantalStenen() + "");
    }

    private void start(ActionEvent e) {
        n = 1;
        balModel.setVx(0.5);
        balModel.setVy(-0.5);
    }

    private void reset(ActionEvent e) {
        peddelModel.reset();
        balModel.reset();
        balView.update();
        peddelView.update();
        stenenView.maakStenen();
        n = 0;
    }

    private void beweeg(MouseEvent m) {
        peddelModel.setX(m.getX() - (peddelModel.getBreedte()) / 2);
        peddelModel.setMin();
        peddelModel.setMax();
    }
}
