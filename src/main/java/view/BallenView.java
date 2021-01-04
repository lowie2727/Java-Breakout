/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import model.Bal;
import model.Ballen;

/**
 *
 * @author lowie
 */
public class BallenView extends Region {

    private Ballen ballen;

    public BallenView(Ballen ballen) {
        this.ballen = ballen;
        maakBallen();
        update();

    }

    public void maakBallen() {
        getChildren().clear();
        ArrayList<Bal> b = ballen.getBallen();
        for (int i = 0; i < ballen.getAantalExtraBallen(); i++) {
            BalView bv = new BalView(b.get(i));
            bv.setTranslateX(b.get(i).getX() + (20 * i) + 300);
            bv.setTranslateY(b.get(i).getY() - 600);
            getChildren().add(bv);
        }
    }

    public void update() {
        ArrayList<Bal> b = ballen.getBallen();
        for (int i = 0; i < ballen.getAantalExtraBallen(); i++) {
            //getChildren().get(i).setTranslateX(b.get(i).getX());
            //getChildren().get(i).setTranslateY(b.get(i).getY());
            getChildren().get(i).setTranslateX(b.get(i).getX());
            getChildren().get(i).setTranslateY(b.get(i).getY());

            if ("1".equals(getChildren().get(i).getId())) {
                b.get(i).setVy(-0.5);
                System.out.println("test1");
            } else if ("2".equals(getChildren().get(i).getId())) {
                System.out.println("test2");
                b.get(i).setVy(0.5);
            } else if ("3".equals(getChildren().get(i).getId())) {
                System.out.println("test3");
                b.get(i).setVx(-0.5);
            } else if ("4".equals(getChildren().get(i).getId())) {
                System.out.println("test4");
                b.get(i).setVx(0.5);
            } else if ("5".equals(getChildren().get(i).getId())) {
                System.out.println("test5");
                b.get(i).setVy(0.5);
                b.get(i).setVx(0.5);
            } else if ("6".equals(getChildren().get(i).getId())) {
                System.out.println("test6");
                b.get(i).setVx(-0.5);
                b.get(i).setVy(-0.5);
            } else if ("7".equals(getChildren().get(i).getId())) {
                System.out.println("test7");
                b.get(i).setVx(-0.5);
                b.get(i).setVy(0.5);
            } else if ("8".equals(getChildren().get(i).getId())) {
                System.out.println("test8");
                b.get(i).setVx(-0.5);
                b.get(i).setVy(0.5);
            }
        }
    }
}
