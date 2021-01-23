/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import model.Steen;
import model.Stenen;

/**
 *
 * @author lowie
 */
public class StenenView extends Region {

    private final Stenen stenen;

    public StenenView(Stenen stenen) {
        this.stenen = stenen;
        maakStenen();
        update();
    }

    public final void update() {
        checkView();
    }

    public final void maakStenen() {
        getChildren().clear();
        Steen s[][] = stenen.getStenen();
        for (int j = 0; j < stenen.getRijen(); j++) {
            for (int i = 0; i < stenen.getKolommen(); i++) {
                SteenView sv = new SteenView(s[j][i]);
                sv.setId("nietGeraakt");
                sv.setTranslateX(s[j][i].getX());
                sv.setTranslateY(s[j][i].getY());
                getChildren().add(sv);
            }
        }
    }

    public void checkView() {
        int n = 0;
        for (int j = 0; j < stenen.getRijen(); j++) {
            for (int i = 0; i < stenen.getKolommen(); i++) {
                if (getChildren().isEmpty()) {
                    break;
                }
                if (n >= getChildren().size()) {
                    n = getChildren().size() - 1;
                }
                getChildren().get(n);
                if (getChildren().get(n).getId().equals("geraakt")) {
                    getChildren().remove(n);
                }
                n++;
            }
        }
    }

    public int getAantalStenen() {
        return getChildren().size();
    }
}
