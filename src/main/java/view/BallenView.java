package view;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import model.Bal;
import model.Ballen;

/**
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class BallenView extends Region {

    private final Ballen ballen;

    /**
     * constructor voor objecten van BallenView
     *
     * @param ballen vraagt model Ballen op
     */
    public BallenView(Ballen ballen) {
        this.ballen = ballen;
        maakBallen();
        update();
    }

    /**
     * maakt een ArrayList met ballen aan
     */
    public final void maakBallen() {
        getChildren().clear();
        ArrayList<Bal> b = ballen.getBallen();
        for (int i = 0; i < ballen.getAantalBallen(); i++) {
            b.get(i).setX(b.get(i).getX() + i * 20);
            BalView bv = new BalView(b.get(i));
            bv.setTranslateX(b.get(i).getX());
            bv.setTranslateY(b.get(i).getY());
            getChildren().add(bv);
        }
    }

    /**
     * deze methode update BallenView
     */
    public void update() {
        getChildren().clear();
        for (int i = 0; i < ballen.getHuidigAantalBallen(); i++) {
            BalView bv = new BalView(ballen.getBallen().get(i));
            bv.setTranslateX(ballen.getBallen().get(i).getX());
            bv.setTranslateY(ballen.getBallen().get(i).getY());
            getChildren().add(bv);
        }
    }

    /**
     * de methode reset BallenView 
     */
    public void reset() {
        for (int i = ballen.getAantalBallen() - 1; i >= 0; i--) {
            if (i != 0) {
                getChildren().remove(i);
            }
        }
    }
    
    /**
     * deze methode laat een bericht zien als je dood bent
     */
    private void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("game over");
        alert.setContentText("je bent dood...\ndruk op reset om opnieuw te beginnen");
        alert.showAndWait();
    }
}
