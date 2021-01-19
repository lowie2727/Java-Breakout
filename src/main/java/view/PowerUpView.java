/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Paneel;
import model.PowerUp;

/**
 *
 * @author jonas
 */
public class PowerUpView extends Region {

    private Circle c;
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
        c = new Circle(powerUp.getStraal(), Color.PINK);

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

}
