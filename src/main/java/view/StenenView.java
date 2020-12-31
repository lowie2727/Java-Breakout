/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Arrays;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Bal;
import model.Steen;
import model.Stenen;
import view.SteenView;

/**
 *
 * @author lowie
 */
public class StenenView extends Region {

    private SteenView steenView;
    private BalView balView;
    private Stenen stenen;
    private Bal bal;
    private SteenView matrix[][];

    public StenenView(Stenen stenen, BalView balView) {
        this.stenen = stenen;
        this.balView = balView;
        createStenen();
        //update();
    }

    private void createStenen() {
        int n = 0;
        int m = 0;
        matrix = new SteenView[stenen.getRijen()][stenen.getKolommen()];
        Steen s[][] = stenen.getStenen();
        for (int j = 0; j < stenen.getRijen(); j++) {
            for (int i = 0; i < stenen.getKolommen(); i++) {
                double breedte = s[j][i].getBreedte() + 10;
                double hoogte = s[j][i].getHoogte() + 10;
                SteenView sv = new SteenView(s[j][i]);
                sv.setTranslateX(breedte * n);
                sv.setTranslateY(hoogte * m);
                matrix[j][i] = new SteenView(s[j][i]);
                //System.out.println(matrix[j][i]);
                //System.out.println(matrix[j][i].getBoundsInLocal());
                n++;
                if (n == stenen.getKolommen()) {
                    n = 0;
                }
                getChildren().add(sv);
                //System.out.println(getChildren());
            }
            m++;
        }
        //System.out.println(Arrays.deepToString(this.matrix));
    }

    public void update() {
        SteenView m[][] = getStenenView();
        for (int j = 0; j < stenen.getRijen(); j++) {
            for (int i = 0; i < stenen.getKolommen(); i++) {
                Circle c = balView.getBal();
                Bounds boundsC = c.localToScene(c.getBoundsInLocal());
                Rectangle rv = m[j][i].getRechthoek();
                Bounds Boundsr = m[j][i].localToScene(m[j][i].getBoundsInParent());
                //System.out.println(Boundsr);
                if (boundsC.intersects(Boundsr)) {
                    bal.vy = bal.vy * -1;
                }

            }
        }

    }
    
    public void update2(){
        ObservableList<Node> children = getChildren();
            
    }

    public Steen[][] getStenen() {
        return stenen.getStenen();
    }

    public SteenView[][] getStenenView() {
        return matrix;
    }

}
