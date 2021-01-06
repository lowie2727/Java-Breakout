/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.scene.layout.Region;
import model.Bal;
import model.Ballen;

/**
 *
 * @author lowie
 */
public class BallenView extends Region {

    private Ballen ballen;
    private Bal balModel;

    public BallenView(Ballen ballen) {
        this.balModel = new Bal(null);
        this.ballen = ballen;
        maakBallen();
        update();
    }

    public void maakBallen() {
        getChildren().clear();
        ArrayList<Bal> b = ballen.getBallen();
        for (int i = 0; i < ballen.getaantalBallen(); i++) {
            BalView bv = new BalView(b.get(i));
            bv.setTranslateX(b.get(i).getX());
            bv.setTranslateY(b.get(i).getY());
            getChildren().add(bv);
        }
    }

    public void update() {
        ArrayList<Bal> b = ballen.getBallen();
        for (int i = 0; i <= b.size() - 1; i++) {
            getChildren().get(i).setTranslateX(b.get(i).getX());
            getChildren().get(i).setTranslateY(b.get(i).getY());
            if (null != getChildren().get(i).getId()) {
                switch (getChildren().get(i).getId()) {
                    case "1":
                        if (b.get(i).getVy() > 0) {
                            b.get(i).setVy();
                        }
                        break;
                    case "2":
                        if (b.get(i).getVy() < 0) {
                            b.get(i).setVy();
                        }
                        break;
                    case "3":
                        if (b.get(i).getVx() > 0) {
                            b.get(i).setVx();
                        }
                        break;
                    case "4":
                        if (b.get(i).getVx() < 0) {
                            b.get(i).setVx();
                        }
                        break;
                    case "5":
                        if (b.get(i).getVx() < 0 && b.get(i).getVy() < 0) {
                            b.get(i).setVy();
                            b.get(i).setVx();
                        }
                        break;
                    case "6":
                        if (b.get(i).getVx() > 0 && b.get(i).getVy() > 0) {
                            b.get(i).setVx();
                            b.get(i).setVy();
                        }
                        break;
                    case "7":
                        if (b.get(i).getVx() > 0 && b.get(i).getVy() < 0) {
                            b.get(i).setVx();
                            b.get(i).setVy();
                        }
                        break;
                    case "8":
                        if (b.get(i).getVx() < 0 && b.get(i).getVy() > 0) {
                            b.get(i).setVx();
                            b.get(i).setVy();
                        }
                        break;
                    case "9":
                        b.get(i).setVx(balModel.getSnelheidX());
                        b.get(i).setVy(balModel.getSnelheidY());
                        break;
                    default:
                        break;
                }
            }
            getChildren().get(i).setId(null);
        }
    }

    public void reset() {
        ArrayList<Bal> ballenLijst = this.ballen.getBallen();
        for (int i = 50; i > 1; i--) {
            if (getChildren().size() + 1 > i && !getChildren().isEmpty()) {
                getChildren().remove(i - 1);
            }
            if (!ballenLijst.isEmpty() && ballenLijst.size() + 1 > i) {
                ballenLijst.remove(i - 1);
            }
        }
        update();
        ArrayList<Bal> b = ballen.getBallen();
        b.get(0).setVx(0);
        b.get(0).setVy(0);
        b.get(0).setX(500);
        b.get(0).setY(472);
        getChildren().get(0).setTranslateX(b.get(0).getX());
        getChildren().get(0).setTranslateY(b.get(0).getY());

    }
}
