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

    private final Peddel peddel;
    private Rectangle p;

    public PeddelView(Peddel peddel) {
        this.peddel = peddel;
        createPeddel();
        update();
    }

    public final void update() {
        getChildren().clear();
        p.setTranslateX(peddel.getX());
        p.setTranslateY(peddel.getY());
        getChildren().add(p);
    }

    public final void createPeddel() {
        getChildren().clear();
        p = new Rectangle(peddel.getHuidigeBreedte(), peddel.getHoogte(), Color.GREEN);

    }
}
