/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import model.Bal;
import model.Ballen;
import model.Paneel;
import model.Peddel;

/**
 *
 * @author lowie
 */
public class BallenView extends Region {

    private final Ballen ballen;
    private final Peddel peddel;
    private final Paneel paneel;

    public BallenView(Ballen ballen, Peddel peddel, Paneel paneel) {
        this.ballen = ballen;
        this.peddel = peddel;
        this.paneel = paneel;
        maakBallen();
        update();
    }

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

    public final void update() {
        ArrayList<Bal> b = ballen.getBallen();
        for (int i = 0; i <= b.size() - 1; i++) {
            Bal bal = b.get(i);
            Node balNode = getChildren().get(i);
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
                        bal.setVy();
                        bal.setVx();
                        break;
                    case "6":
                        bal.setVx();
                        bal.setVy();
                        break;
                    case "7":
                        bal.setVx();
                        bal.setVy();
                        break;
                    case "8":
                        bal.setVx();
                        bal.setVy();
                        break;
                    case "9":
                        bal.setVx(bal.getSnelheidX());
                        bal.setVy(bal.getSnelheidY());
                        break;
                    case "10":
                        if (bal.getVy() > 0) {
                            double midden = peddel.getX() + peddel.getBreedte() / 2;
                            if ((bal.getX() - midden) > 0) {
                                if (bal.getVx() < 0) {
                                    bal.setVx();
                                }
                                double temp1 = 1 - (bal.getX() - midden) / (peddel.getBreedte() / 2);
                                bal.setHy((temp1 * 0.7) + 0.3);
                            } else {
                                if (bal.getVx() > 0) {
                                    bal.setVx();
                                }
                                double temp2 = 1 - (midden - bal.getX()) / (peddel.getBreedte() / 2);
                                bal.setHy((temp2 * 0.7) + 0.3);
                            }
                            bal.setVy(bal.getSnelheidY() * bal.getHy());
                        }
                    default:
                        break;
                }
                balNode.setId(null);
            }

            balNode.setTranslateX(bal.getX());
            balNode.setTranslateY(bal.getY());

            if (bal.getY() >= paneel.getHoogte() - bal.getStraal() && b.size() > 1) {
                b.remove(i);
                getChildren().remove(i);
            }
        }

        if (b.get(0).getY() > paneel.getHoogte() - b.get(0).getStraal() && b.get(0).getVy() != 0 && ballen.getBallen().size() == 1) {
            b.get(0).setVx(0);
            b.get(0).setVy(0);
            gameOver();
        }
    }

    public void reset() {
        ArrayList<Bal> ballenLijst = ballen.getBallen();
        for (int i = getChildren().size(); i > 1; i--) {
            getChildren().remove(i - 1);
            ballenLijst.remove(i - 1);
        }
        update();
        Bal b = ballenLijst.get(0);
        b.reset();
        getChildren().get(0).setTranslateX(b.getX());
        getChildren().get(0).setTranslateY(b.getY());

    }

    private void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("game over");
        alert.setContentText("je bent dood...\ndruk op reset om opnieuw te beginnen");
        alert.showAndWait();
    }

    public int getAantalBallen() {
        return getChildren().size();
    }
}
