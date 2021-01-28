package view;

import javafx.scene.layout.Region;
import model.Steen;
import model.Stenen;

/**
 * klasse StenenView
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class StenenView extends Region {

    private final Stenen stenen;

    /**
     * constructor voor objecten van StenenView
     *
     * @param stenen vraagt het model Stenen op
     */
    public StenenView(Stenen stenen) {
        this.stenen = stenen;
        maakStenen();
    }

    /**
     * deze methode update StenenView
     */
    public void update() {
        int n = 0;
        for (int j = 0; j < stenen.getRijen(); j++) {
            for (int i = 0; i < stenen.getKolommen(); i++) {
                if (stenen.getStenen()[j][i].isGeraakt()) {
                    getChildren().get(n).setVisible(false);
                }
                n++;
            }
        }
    }

    /**
     * deze methode maakt stenen aan
     */
    private void maakStenen() {
        getChildren().clear();
        Steen s[][] = stenen.getStenen();
        for (int j = 0; j < stenen.getRijen(); j++) {
            for (int i = 0; i < stenen.getKolommen(); i++) {
                SteenView sv = new SteenView(s[j][i]);
                sv.setTranslateX(s[j][i].getX());
                sv.setTranslateY(s[j][i].getY());
                getChildren().add(sv);
            }
        }
    }
}
