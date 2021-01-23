/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import model.Paneel;
import model.PowerUp;

/**
 *
 * @author jonas
 */
public class PowerUpView extends Region {

    private Circle c;
    private Circle i;
    private final PowerUp powerUp;
    private final Paneel paneelModel;
    private double randX;
    private double randY;

    public PowerUpView(PowerUp powerUp, Paneel paneelModel) {
        this.powerUp = powerUp;
        this.paneelModel = paneelModel;
        createPowerUp();
    }

    public final void createPowerUp() {
        long i = Math.round(Math.random() * 4);
        if (i == 10) {
            c = new Circle(powerUp.getStraal(), Color.PINK);
        } else if (i == 11) {
            c = new Circle(powerUp.getStraal(), Color.PURPLE);
        } else if (i == 12) {
            c = new Circle(powerUp.getStraal(), Color.BLACK);
        } else {
            c = new Circle(powerUp.getStraal(), Color.GRAY);
        }
        randX = Math.random() * paneelModel.getBreedte();
        randY = Math.random() * paneelModel.getHoogte() / 2;
        c.setTranslateX(randX);
        c.setCenterY(randY);

        getChildren().add(c);

    }

    public void update() {
        if (!getChildren().isEmpty()) {
            if ("1".equals(getChildren().get(0).getId())) {
                getChildren().remove(0);
            }
        }
    }

    public void reset() {
        getChildren().clear();
    }

    /**
     * @return the randX
     */
    public double getRandX() {
        return randX;
    }

    /**
     * @return the randY
     */
    public double getRandY() {
        return randY;
    }

    /**
     * @return the c
     */
    public Paint getKleurC() {
        //Paint s = Color.PINK;
        //Paint u = (Color) c.getFill();
        return c.getFill();
    }

}
