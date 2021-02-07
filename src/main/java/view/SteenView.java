package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Steen;

/**
 * klasse SteenView
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class SteenView extends Region {

    private final Steen steen;

    /**
     * constructor voor objecten van SteenView
     *
     * @param steen vraagt het model Steen op
     */
    public SteenView(Steen steen) {
        this.steen = steen;
        maakSteen();
    }

    /**
     * deze methode maakt een nieuwe steen aan
     */
    private void maakSteen() {
        Rectangle r = new Rectangle(steen.getBreedte(), steen.getHoogte(), Color.RED);
        r.setStroke(Color.ORANGE);
        r.setStrokeType(StrokeType.INSIDE);
        r.setStrokeWidth(2);
        r.setTranslateX(steen.getX());
        r.setTranslateY(steen.getY());
    }
}
