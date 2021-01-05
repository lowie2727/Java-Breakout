/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import model.Bal;
import model.Ballen;

/**
 *
 * @author lowie
 */
public class BallenView extends Region {

    private Ballen ballen;
    private static final double SNELHEID = 1;

    public BallenView(Ballen ballen) {
        this.ballen = ballen;
        maakBallen();
        update();
    }

    private void maakBallen() {
        getChildren().clear();
        ArrayList<Bal> b = ballen.getBallen();
        for (int i = 0; i < ballen.getAantalExtraBallen(); i++) {
            BalView bv = new BalView(b.get(i));
            bv.setTranslateX(b.get(i).getX());
            bv.setTranslateY(b.get(i).getY());
            getChildren().add(bv);
        }
    }

    public void update() {
        ArrayList<Bal> b = ballen.getBallen();
        for (int i = 0; i < ballen.getAantalExtraBallen(); i++) {
            getChildren().get(i).setTranslateX(b.get(i).getX());
            getChildren().get(i).setTranslateY(b.get(i).getY());
            if (null != getChildren().get(i).getId()) {
                switch (getChildren().get(i).getId()) {
                    case "1":
                        b.get(i).setVy(-SNELHEID);
                        break;
                    case "2":
                        b.get(i).setVy(SNELHEID);
                        break;
                    case "3":
                        b.get(i).setVx(-SNELHEID);
                        break;
                    case "4":
                        b.get(i).setVx(SNELHEID);
                        break;
                    case "5":
                        b.get(i).setVy(SNELHEID);
                        b.get(i).setVx(SNELHEID);
                        break;
                    case "6":
                        b.get(i).setVx(-SNELHEID);
                        b.get(i).setVy(-SNELHEID);
                        break;
                    case "7":
                        b.get(i).setVx(-SNELHEID);
                        b.get(i).setVy(SNELHEID);
                        break;
                    case "8":
                        b.get(i).setVx(SNELHEID);
                        b.get(i).setVy(-SNELHEID);
                        break;
                    default:
                        break;
                }
            }
            getChildren().get(i).setId(null);
        }
    }

    public void reset() {
        ArrayList<Bal> bal = ballen.getBallen();
        ObservableList<Node> children = getChildren();
        int n = 0;
        for (Node b : children) {
            if (!getChildren().isEmpty()) {
                if (n != 0) {
                    bal.remove(n);
                    getChildren().remove(n);

                } else {
                    bal.get(n).reset();
                }
                n++;
            } else {
                break;
            }
        }

    }
}
