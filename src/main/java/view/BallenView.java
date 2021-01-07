/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
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
            Bal bal = b.get(i);
            Node balNode = getChildren().get(i);
            balNode.setTranslateX(bal.getX());
            balNode.setTranslateY(bal.getY());
            if (null != balNode.getId()) {
                switch (balNode.getId()) {
                    case "1":
                        if (bal.getVy() > 0) {
                            bal.setVy();
                        }
                        break;
                    case "2":
                        if (bal.getVy() < 0) {
                            bal.setVy();
                        }
                        break;
                    case "3":
                        if (bal.getVx() > 0) {
                            bal.setVx();
                        }
                        break;
                    case "4":
                        if (bal.getVx() < 0) {
                            bal.setVx();
                        }
                        break;
                    case "5":
                        if (bal.getVx() < 0 && bal.getVy() < 0) {
                            bal.setVy();
                            bal.setVx();
                        }
                        break;
                    case "6":
                        if (bal.getVx() > 0 && bal.getVy() > 0) {
                            bal.setVx();
                            bal.setVy();
                        }
                        break;
                    case "7":
                        if (bal.getVx() > 0 && bal.getVy() < 0) {
                            bal.setVx();
                            bal.setVy();
                        }
                        break;
                    case "8":
                        if (bal.getVx() < 0 && bal.getVy() > 0) {
                            bal.setVx();
                            bal.setVy();
                        }
                        break;
                    case "9":
                        bal.setVx(balModel.getSnelheidX());
                        bal.setVy(balModel.getSnelheidY());
                        break;
                    default:
                        break;
                }
            }
            balNode.setId(null);
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
