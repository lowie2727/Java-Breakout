package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Bal;

/**
 * klasse BalView
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class BalView extends Region {

    private final Bal bal;

    /**
     * constructur voor objecten van BalView
     *
     * @param bal vraagt model Bal op
     */
    public BalView(Bal bal) {
        this.bal = bal;
        maakCirkel();
    }

    /**
     * maakt een nieuwe cirkel
     */
    public final void maakCirkel() {
        Circle c = new Circle(bal.getHuidigeStraal(), Color.BLUE);
        getChildren().add(c);
    }
}
