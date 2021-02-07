package view;

import javafx.scene.layout.Pane;
import model.Bal;
import model.Paneel;
import model.Spel;
import model.Steen;

/**
 * klasse SpelView
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class SpelView {

    private PeddelView peddelView;
    private final PaneelView paneelView;
    private BalView balView;
    private PowerUpView powerUpView;

    private final Pane paneel;
    private final Spel spel;

    /**
     * constructor voor objecten van SpelView
     *
     * @param spel        vraagt model Spel op
     * @param paneel      paneel waar alle views op getoond worden
     * @param paneelModel vraagt model Paneel op
     */
    public SpelView(Spel spel, Pane paneel, Paneel paneelModel) {
        this.spel = spel;
        this.paneel = paneel;
        for (Bal bal : spel.getBallen()) {
            balView = new BalView(bal);
            paneel.getChildren().add(balView);
        }
        paneelView = new PaneelView(paneelModel);
        peddelView = new PeddelView(spel.getPeddel());
        powerUpView = new PowerUpView(spel.getPowerUp());
        maakBallenView();
        paneel.getChildren().addAll(paneelView, peddelView, powerUpView);
    }

    /**
     * deze methode update SpelView
     */
    public void update() {
        System.out.println("update");
        paneel.getChildren().clear();
        maakBallenView();
        maakStenenView();
        peddelView = new PeddelView(spel.getPeddel());
        powerUpView = new PowerUpView(spel.getPowerUp());
        powerUpView.update();
        paneel.getChildren().addAll(paneelView, peddelView, powerUpView);
    }

    private void maakBallenView() {
        for (Bal bal : spel.getBallen()) {
            balView = new BalView(bal);
            paneel.getChildren().add(balView);
        }
    }

    private void maakStenenView() {
        for (int j = 0; j < spel.getRijen(); j++) {
            for (int i = 0; i < spel.getKolommen(); i++) {
                Steen s = spel.getSteen(j, i);

                SteenView sv = new SteenView(s);
                paneel.getChildren().add(sv);
            }
        }
    }
}
