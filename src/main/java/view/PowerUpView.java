package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Kleuren;
import model.PowerUp;

/**
 * klasse PowerUpView
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class PowerUpView extends Region {

    private final PowerUp powerUp;

    /**
     * constructor voor objecten van PowerUpView
     *
     * @param powerUp vraagt model PowerUp op
     */
    public PowerUpView(PowerUp powerUp) {
        this.powerUp = powerUp;
        maakPowerUp();
    }

    /**
     * deze methode maakt een nieuwe PowerUp
     */
    private void maakPowerUp() {
        Circle c;
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
     * deze methode update PowerUpView
     */
    public void update() {
        getChildren().clear();
        if (!powerUp.isGeraakt()) {
            maakPowerUp();
        }
    }
}
