/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Peddel;

/**
 *
 * @author lowie
 */
public class PeddelView extends Region {

    private Peddel peddel;
    private Rectangle p;

    public PeddelView(Peddel peddel) {
        this.peddel = peddel;
        createPeddel();
        update();
    }

    public void update() {
        getChildren().clear();
        p.setTranslateX(peddel.getX());
        p.setTranslateY(peddel.getY());
        getChildren().add(p);
    }

    private void createPeddel() {
        p = new Rectangle(peddel.getBreedte(), peddel.getHoogte(), Color.BLACK);
    }

    public Rectangle getRechthoek() {
        return p;
    }
}
