/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Paneel;

/**
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class PaneelView extends Region {

    private final Paneel paneel;
    private Rectangle r;

    /**
     *
     * @param paneel
     */
    public PaneelView(Paneel paneel) {
        this.paneel = paneel;
        createBorder();
    }

    private void createBorder() {
        r = new Rectangle(paneel.getBreedte(), paneel.getHoogte(), Color.BLACK);
        r.setFill(null);
        r.setStroke(Color.BLACK);
        r.setStrokeType(StrokeType.OUTSIDE);
        r.setStrokeWidth(2);
        getChildren().add(r);
    }
}
