/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Kleuren;
import model.PowerUp;

/**
 *
 * @author jonas
 */
public class PowerUpView extends Region {

    private Circle c;
    private final PowerUp powerUp;

    /**
     *
     * @param powerUp
     */
    public PowerUpView(PowerUp powerUp) {
        this.powerUp = powerUp;
        createPowerUp();
    }

    /**
     *
     */
    public void createPowerUp() {
        getChildren().clear();
        if (powerUp.getKleurBal() == Kleuren.ROZE) {
            c = new Circle(powerUp.getStraal(), Color.PINK);
        } else if (powerUp.getKleurBal() == Kleuren.PAARS) {
            c = new Circle(powerUp.getStraal(), Color.PURPLE);
        } else if (powerUp.getKleurBal() == Kleuren.ZWART) {
            c = new Circle(powerUp.getStraal(), Color.BLACK);
        } else {
            c = new Circle(powerUp.getStraal(), Color.GRAY);
        }
        c.setTranslateX(powerUp.getX());
        c.setTranslateY(powerUp.getY());
        getChildren().add(c);

    }

    /**
     *
     */
    public void update() {
        getChildren().clear();
        if (!powerUp.isGeraakt()) {
            createPowerUp();
        }
    }

    /**
     *
     */
    public void reset() {
        getChildren().clear();
    }
}
