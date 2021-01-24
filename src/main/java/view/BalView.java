/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Bal;

/**
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne 
 */
public class BalView extends Region {

    private final Bal bal;
    private Circle c;

    /**
     *
     * @param bal
     */
    public BalView(Bal bal) {
        this.bal = bal;
        createBal();
    }

    /**
     *
     */
    public final void createBal() {
        c = new Circle(bal.getHuidigeStraal(), Color.BLUE);
        getChildren().add(c);
    }
}
