package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Peddel;

/**
 * klasse PeddelView
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class PeddelView extends Region {

    private final Peddel peddel;
    private Rectangle p;

    /**
     * constructor voor objecten van PeddelView
     *
     * @param peddel vraag model Peddel op
     */
    public PeddelView(Peddel peddel) {
        this.peddel = peddel;
        maakPeddel();
        update();
    }

    /**
     * deze methode update PeddelView
     */
    private void update() {
        getChildren().clear();
        p.setTranslateX(peddel.getX());
        p.setTranslateY(peddel.getY());
        getChildren().add(p);
    }

    /**
     * deze methode maakt een nieuwe peddel aan
     */
    private void maakPeddel() {
        getChildren().clear();
        p = new Rectangle(peddel.getHuidigeBreedte(), peddel.getHoogte(), Color.GREEN);

    }
}
