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
        
        //peddelView.setFocusTraversable(true);
        //resetButton.setFocusTraversable(false);
        
        resetButton.setOnAction(this::reset);
        paneel.setOnMouseMoved(this::beweeg);
        
        UpdateBal b = new UpdateBal(balModel, this);
        Timer t = new Timer(true);
        t.scheduleAtFixedRate(b, 0, 1);
        
    }
    
    public void update() {
        botsingBal();
        peddelView.update();
        balView.update();
        textBox.setText(stenenModel.getRijen() * stenenModel.getKolommen() + "");
    }
    
    private void reset(ActionEvent e) {
        balModel.reset();
        peddelModel.reset();
    }
    
    private void beweeg(MouseEvent m) {
        peddelModel.setX(m.getX() - (peddelModel.getBreedte()) / 2);
        peddelModel.setMin();
        peddelModel.setMax();
        stenenView.update();
    }
    
    private void botsingBal() {
        
        ObservableList<Node> peddel = peddelView.getChildrenUnmodifiable();
        ObservableList<Node> bal = balView.getChildrenUnmodifiable();
        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();
        
        if (balModel.getY() + balModel.getStraal() >= peddelModel.getY() - 3
                && balModel.getY() + balModel.getStraal() <= peddelModel.getY() + 3) {
            balModel.setVy(-0.5);
        }
        
        for (Node s : stenen) {
            Bounds boundSteen = s.localToParent(s.getBoundsInLocal());
            if (balModel.getY() + balModel.getStraal() >= boundSteen.getMinY() - 3
                    && balModel.getY() + balModel.getStraal() <= boundSteen.getMinY() + 3
                    && balModel.getX() > boundSteen.getMinX()
                    && balModel.getX() < boundSteen.getMinX() + boundSteen.getWidth()) {
                balModel.setVy(-0.5);
            }
            if (balModel.getY() - balModel.getStraal() >= boundSteen.getMaxY() - 3
                    && balModel.getY() - balModel.getStraal() <= boundSteen.getMaxY() + 3
                    && balModel.getX() > boundSteen.getMinX()
                    && balModel.getX() < boundSteen.getMinX() + boundSteen.getWidth()) {
                balModel.setVy(0.5);
            }
            if (balModel.getX() + balModel.getStraal() >= boundSteen.getMinX() - 3
                    && balModel.getX() + balModel.getStraal() <= boundSteen.getMinX() + 3
                    && balModel.getY() > boundSteen.getMinY()
                    && balModel.getY() < boundSteen.getMinY() + boundSteen.getHeight()) {;
                balModel.setVx(-0.5);
            }
            if (balModel.getX() - balModel.getStraal() >= boundSteen.getMaxX() - 3
                    && balModel.getX() - balModel.getStraal() <= boundSteen.getMaxX() + 3
                    && balModel.getY() > boundSteen.getMinY()
                    && balModel.getY() < boundSteen.getMinY() + boundSteen.getHeight()) {
                balModel.setVx(0.5);
            }
        }
    }
}
