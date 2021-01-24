/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     *
     * @param ballen
     */
    public BallenView(Ballen ballen) {
        this.ballen = ballen;
        maakBallen();
        update();
    }

    /**
     *
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
     *
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
     *
     */
    public void reset() {
        for (int i = ballen.getAantalBallen() - 1; i >= 0; i--) {
            if (i != 0) {
                getChildren().remove(i);
            }
        }
    }

    private void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("game over");
        alert.setContentText("je bent dood...\ndruk op reset om opnieuw te beginnen");
        alert.showAndWait();
    }
}
