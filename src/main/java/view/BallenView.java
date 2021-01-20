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
    public boolean statusPurple;

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
                String string = (balNode.getId());
                if (!statusPurple) {
                    if ("1".equals(string)) {
                        if (bal.getVy() > 0) {
                            bal.setVy();
                        }
                    } else if ("2".equals(string)) {
                        if (bal.getVy() < 0) {
                            bal.setVy();
                        }
                    } else if ("3".equals(string)) {
                        if (bal.getVx() > 0) {
                            bal.setVx();
                        }
                    } else if ("4".equals(string)) {
                        if (bal.getVx() < 0) {
                            bal.setVx();
                        }
                    } else if ("5".equals(string)) {
                        bal.setVy();
                        bal.setVx();
                    } else if ("6".equals(string)) {
                        bal.setVx();
                        bal.setVy();
                    } else if ("7".equals(string)) {
                        bal.setVx();
                        bal.setVy();
                    } else if ("8".equals(string)) {
                        bal.setVx();
                        bal.setVy();
                    }
                }
                if ("9".equals(string)) {
                    bal.setVx(bal.getSnelheidX());
                    bal.setVy(bal.getSnelheidY());
                } else if ("10".equals(string)) {
                    if (bal.getVy() > 0) {
                        double snelheidY;
                        double middenPeddelX = peddel.getX() + peddel.getBreedte() / 2;
                        if ((bal.getX() - middenPeddelX) > 0) {
                            if (bal.getVx() < 0) {
                                bal.setVx();
                            }
                            double percentTotMiddenRechts = 1 - (bal.getX() - middenPeddelX) / (peddel.getBreedte() / 2);
                            double snelheid = Math.sqrt(Math.pow(bal.getSnelheid(), 2) / 2);
                            snelheidY = -((percentTotMiddenRechts * (bal.getSnelheid() - snelheid)) + snelheid);
                        } else {
                            if (bal.getVx() > 0) {
                                bal.setVx();
                            }
                            double percentTotMiddenLinks = 1 - (middenPeddelX - bal.getX()) / (peddel.getBreedte() / 2);
                            double snelheid = Math.sqrt(Math.pow(bal.getSnelheid(), 2) / 2);
                            snelheidY = -((percentTotMiddenLinks * (bal.getSnelheid() - snelheid)) + snelheid);
                        }
                        bal.setVy(snelheidY);
                    }
                }
            }
            balNode.setId(null);
            balNode.setTranslateX(bal.getX());
            balNode.setTranslateY(bal.getY());

            if (bal.getY() >= paneel.getHoogte() - bal.getStraal() && b.size() > 1) {
                b.remove(i);
                getChildren().remove(i);
            }
        }
        if (b.get(0).getY() > paneel.getHoogte() - b.get(0).getStraal() && b.get(0).getVy() != 0 && ballen.getBallen().size() == 1) {
            b.get(0).reset();
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
