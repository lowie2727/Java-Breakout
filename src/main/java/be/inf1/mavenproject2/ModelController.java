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

    private boolean status;
    private Timer timer;

    @FXML
    void initialize() {

        vensterModel = new Paneel(1000, 500);    //breedte, hoogte
        paneel.setPrefSize(vensterModel.getBreedte(), vensterModel.getHoogte());

        steenModel = new Steen(60, 20);  //breedte, hoogte
        //balModel = new Bal(vensterModel, 8); //paneel, straal
        ballenModel = new Ballen(vensterModel);
        peddelModel = new Peddel(200, 10, vensterModel);  //breedte, hoogte
        stenenModel = new Stenen(vensterModel, steenModel, 2, 500);  //rijen, kolommen

        ballenView = new BallenView(ballenModel, peddelModel, vensterModel);
        paneelView = new PaneelView(vensterModel);
        peddelView = new PeddelView(peddelModel);
        paneelView = new PaneelView(vensterModel);
        stenenView = new StenenView(stenenModel);

        paneel.getChildren().addAll(peddelView, paneelView, stenenView, ballenView);

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
        textBox.setText(stenenView.getAantalStenen() + "");
    }

    private void start(ActionEvent e) {

        if (!status) {
            timer = new Timer(true);
            veldView = new VeldView(stenenView, peddelModel, ballenView);
            for (Bal bal : ballenModel.getBallen()) {
                UpdateBal b = new UpdateBal(bal, this);
                timer.scheduleAtFixedRate(b, 0, 1);
            }
            status = true;
        }

    }

    public void reset(ActionEvent e) {

        if (status) {
            peddelModel.reset();
            peddelView.update();
            stenenView.maakStenen();
            ballenView.reset();
            timer.cancel();
            status = false;
        }

    }

    private void beweegPeddel(MouseEvent m) {
        peddelModel.setX(m.getX() - (peddelModel.getBreedte()) / 2);
        peddelModel.setMin();
        peddelModel.setMax();
    }
}
