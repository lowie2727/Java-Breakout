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
    private Circle c;

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
    private void maakCirkel() {
        c = new Circle(bal.getHuidigeStraal(), Color.BLUE);
        c.setTranslateX(bal.getX());
        c.setTranslateY(bal.getY());
        getChildren().add(c);
    }

    public void update(){
        if(bal.getStraal()!= bal.getHuidigeStraal()){
            c = new Circle(bal.getHuidigeStraal(), Color.BLUE);
        }
        c.setTranslateX(bal.getX());
        c.setTranslateY(bal.getY());
    }
}
