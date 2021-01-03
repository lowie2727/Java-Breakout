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
 * @author lowie
 */
public class BalView extends Region {

    private Bal bal;
    private Circle c;

    public BalView(Bal bal) {
        this.bal = bal;
        createBal();
        update();
    }

    public void update() {
        /*if ("1".equals(getChildren().get(0).getId())) {
            balModel.setVy(-0.5);
        }
        if ("2".equals(getChildren().get(0).getId())) {
            balModel.setVy(0.5);
        }
        if ("3".equals(getChildren().get(0).getId())) {
            balModel.setVx(-0.5);
        }
        if ("4".equals(getChildren().get(0).getId())) {
            balModel.setVx(0.5);
        }
        if ("5".equals(getChildren().get(0).getId())) {
            balModel.setVy(0.5);
            balModel.setVx(0.5);
        }
        if ("6".equals(getChildren().get(0).getId())) {
            balModel.setVx(-0.5);
            balModel.setVy(-0.5);
        }
        if ("7".equals(getChildren().get(0).getId())) {
            balModel.setVx(-0.5);
            balModel.setVy(0.5);
        }
        if ("8".equals(getChildren().get(0).getId())) {
            balModel.setVx(0.5);
            balModel.setVy(-0.5);
        }*/
        getChildren().clear();

        c.setTranslateX(bal.getX());
        c.setTranslateY(bal.getY());
        getChildren().add(c);
    }

    public void createBal() {
        c = new Circle(bal.getStraal(), Color.BLUE);
        getChildren().add(c);
    }
}
