package be.inf1.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private Ballen ballenModel;

    @FXML
    void initialize() {

        vensterModel = new Paneel();
        paneel.setPrefSize(vensterModel.getBreedte(), vensterModel.getHoogte());

        ballenModel = new Ballen();
        steenModel = new Steen();
        peddelModel = new Peddel(vensterModel);
        balModel = new Bal(vensterModel, peddelModel);
        stenenModel = new Stenen(vensterModel, steenModel);

        ballenView = new BallenView(ballenModel);
        paneelView = new PaneelView(vensterModel);
        peddelView = new PeddelView(peddelModel);
        paneelView = new PaneelView(vensterModel);
        stenenView = new StenenView(stenenModel);

        paneel.getChildren().addAll(ballenView, peddelView, paneelView, stenenView);
        //update();

        peddelView.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);

        resetButton.setOnAction(this::reset);
        startButton.setOnAction(this::start);
        paneel.setOnMouseMoved(this::beweeg);

        for (Bal bal : ballenModel.getBallen()) {
            UpdateBal b = new UpdateBal(bal, this);
            Timer t = new Timer(true);
            t.scheduleAtFixedRate(b, 0, 1);
        }
    }

    public void update() {
        if (n == 1) {
            veldView = new VeldView(stenenView, peddelModel, balModel, ballenView);
            //veldView.botsingBal();
            //peddelView.update();
            //stenenView.update();
            //balView.update();
            ballenView.update();
        }
        textBox.setText(stenenView.aantalStenen() + "");
    }

    private void start(ActionEvent e) {
        n = 1;
        for(Node b : ballenView.getChildrenUnmodifiable()){
            b.setId("8");
        }

    }

    private void reset(ActionEvent e) {
        peddelModel.reset();
        //balModel.reset();
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
