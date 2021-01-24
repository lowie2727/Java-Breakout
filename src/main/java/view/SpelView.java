package view;

import javafx.scene.layout.Pane;
import model.Paneel;
import model.Spel;

/**
 * klasse SpelView
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class SpelView {

    private PeddelView peddelView;
    private final PaneelView paneelView;
    private StenenView stenenView;
    private final BallenView ballenView;
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
        ballenView = new BallenView(spel.getBallen());
        paneelView = new PaneelView(paneelModel);
        peddelView = new PeddelView(spel.getPeddel());
        stenenView = new StenenView(spel.getStenen());
        powerUpView = new PowerUpView(spel.getPowerUp());
        this.paneel = paneel;
        paneel.getChildren().addAll(ballenView, paneelView, peddelView, stenenView, powerUpView);
    }

    /**
     * deze methode update SpelView
     */
    public void update() {
        paneel.getChildren().clear();
        ballenView.update();
        peddelView = new PeddelView(spel.getPeddel());
        stenenView = new StenenView(spel.getStenen());
        stenenView.update();
        powerUpView = new PowerUpView(spel.getPowerUp());
        powerUpView.update();
        paneel.getChildren().addAll(ballenView, paneelView, peddelView, stenenView, powerUpView);
    }
}
