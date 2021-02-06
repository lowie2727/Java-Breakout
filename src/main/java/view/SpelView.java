package view;

import javafx.scene.layout.Pane;
import model.Bal;
import model.Paneel;
import model.Spel;

import java.util.ArrayList;

/**
 * klasse SpelView
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class SpelView {

    private PeddelView peddelView;
    private final PaneelView paneelView;
    private StenenView stenenView;
    private BalView balView;
    private ArrayList<BalView> ballenView;
    private PowerUpView powerUpView;

    private final Pane paneel;
    private final Spel spel;

    /**
     * constructor voor objecten van SpelView
     *
     * @param spel vraagt model Spel op
     * @param paneel paneel waar alle views op getoond worden
     * @param paneelModel vraagt model Paneel op
     */
    public SpelView(Spel spel, Pane paneel, Paneel paneelModel) {
        this.spel = spel;
        this.paneel = paneel;
        for(Bal bal : spel.getBallen()){
            balView = new BalView(bal);
            paneel.getChildren().add(balView);
        }
        paneelView = new PaneelView(paneelModel);
        peddelView = new PeddelView(spel.getPeddel());
        stenenView = new StenenView(spel.getStenen());
        powerUpView = new PowerUpView(spel.getPowerUp());
        maakBallenView();
        paneel.getChildren().addAll(paneelView, peddelView, stenenView, powerUpView);
    }

    /**
     * deze methode update SpelView
     */
    public void update() {
        paneel.getChildren().clear();
        maakBallenView();
        peddelView = new PeddelView(spel.getPeddel());
        stenenView = new StenenView(spel.getStenen());
        stenenView.update();
        powerUpView = new PowerUpView(spel.getPowerUp());
        powerUpView.update();
        paneel.getChildren().addAll(paneelView, peddelView, stenenView, powerUpView);
    }

    private void maakBallenView(){
        for(Bal bal : spel.getBallen()){
            balView = new BalView(bal);
            paneel.getChildren().add(balView);
        }
    }
}
