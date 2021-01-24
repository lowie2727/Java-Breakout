package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Paneel;

/**
 * klasse PaneelView
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class PaneelView extends Region {

    private final Paneel paneel;

    /**
     * constructur voor objecten van PaneelView
     *
     * @param paneel 
     */
    public PaneelView(Paneel paneel) {
        this.paneel = paneel;
        maakRand();
    }

    private void maakRand() {
        Rectangle r = new Rectangle(paneel.getBreedte(), paneel.getHoogte(), Color.BLACK);
        r.setFill(null);
        r.setStroke(Color.BLACK);
        r.setStrokeType(StrokeType.OUTSIDE);
        r.setStrokeWidth(2);
        getChildren().add(r);
    }
}
