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
    private Bal balModel;
    private Steen steenModel;
    private Paneel vensterModel;
    private Stenen stenenModel;
    private Ballen ballenModel;

    private PeddelView peddelView;
    private PaneelView paneelView;
    private StenenView stenenView;
    private VeldView veldView;
    private BallenView ballenView;

    private int n = 0;
    private Timer timer;

    @FXML
    void initialize() {

        vensterModel = new Paneel(1000, 500);    //breedte, hoogte
        paneel.setPrefSize(vensterModel.getBreedte(), vensterModel.getHoogte());

        steenModel = new Steen(60, 20);  //breedte, hoogte
        balModel = new Bal(vensterModel, 8); //paneel, straal
        ballenModel = new Ballen(balModel);
        peddelModel = new Peddel(500, 10, vensterModel);  //breedte, hoogte
        stenenModel = new Stenen(vensterModel, steenModel, 2, 500);  //rijen, kolommen

        ballenView = new BallenView(ballenModel, balModel, peddelModel);
        paneelView = new PaneelView(vensterModel);
        peddelView = new PeddelView(peddelModel);
        paneelView = new PaneelView(vensterModel);
        stenenView = new StenenView(stenenModel);

        paneel.getChildren().addAll(peddelView, paneelView, stenenView, ballenView);
        update();

        startButton.setFocusTraversable(true);

        resetButton.setOnAction(this::reset);
        startButton.setOnAction(this::start);
        paneel.setOnMouseMoved(this::beweeg);

    }

    public void update() {
        if (n == 0) {
            for (Node b : ballenView.getChildrenUnmodifiable()) {
                b.setId("9");
            }
        }

        if (n != 0) {
            veldView = new VeldView(stenenView, peddelModel, ballenView);
            ballenView.update();
            veldView.botsingBal();
            peddelView.update();
            stenenView.update();
        }
        textBox.setText(stenenView.aantalStenen() + "");
    }

    private void start(ActionEvent e) {
        n++;
        if (n == 1) {
            for (Bal bal : ballenModel.getBallen()) {
                UpdateBal b = new UpdateBal(bal, this);
                timer = new Timer(true);
                timer.scheduleAtFixedRate(b, 0, 1);
            }
        }
    }

    public void reset(ActionEvent e) {
        peddelModel.reset();
        peddelView.update();
        stenenView.maakStenen();
        ballenView.reset();
        timer.cancel();
        n = 0;
    }

    private void beweeg(MouseEvent m) {
        peddelModel.setX(m.getX() - (peddelModel.getBreedte()) / 2);
        peddelModel.setMin();
        peddelModel.setMax();
    }
}
