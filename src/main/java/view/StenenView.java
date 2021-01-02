/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import model.Steen;
import model.Stenen;

/**
 *
 * @author lowie
 */
public class StenenView extends Region {

    private Stenen stenen;

    public StenenView(Stenen stenen) {
        this.stenen = stenen;
        maakStenen();
        update();
    }

    public void update() {
        //getChildren().clear();
        checkView();

    }

    public void maakStenen() {
        getChildren().clear();
        int n = 0;
        int m = 0;
        Steen s[][] = stenen.getStenen();
        for (int j = 0; j < stenen.getRijen(); j++) {
            for (int i = 0; i < stenen.getKolommen(); i++) {
                double breedte = s[j][i].getBreedte() + stenen.getOffsetBreedte();
                double hoogte = s[j][i].getHoogte() + stenen.getOffsetHoogte();
                SteenView sv = new SteenView(s[j][i]);

                sv.setId("nietGeraakt");
                sv.setTranslateX(breedte * n + stenen.getOffsetBreedtePaneel());
                sv.setTranslateY(hoogte * m + stenen.getOffsetHoogtePaneel());
                n++;
                if (n == stenen.getKolommen()) {
                    n = 0;
                }
                getChildren().add(sv);
            }
            m++;
        }
    }

    public void checkView() {
        int n = 0;
        Steen s[][] = stenen.getStenen();
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

    public int aantalStenen() {
        return getChildren().size();
    }
}
