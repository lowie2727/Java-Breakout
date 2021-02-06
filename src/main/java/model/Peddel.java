package model;

import be.inf1.atariBreakout.StartPaginaController;

/**
 * klasse Peddel
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Peddel {

    private final Paneel paneel;
    /**
     * de hoogte
     */
    private final double hoogte;
    /**
     * de breedte
     */
    private final double breedte;
    /**
     * de huidige breedte
     */
    private double huidigeBreedte;

    /**
     * de afstand van de peddel boven de onderkant van het veld
     */
    private final double offset;
    /**
     * de x positie van de peddel van de linkerboven hoek
     */
    private double x;
    /**
     * de y positie van de peddel van de linkerboven hoek
     */
    private double y;

    /**
     * de factor waarmee de peddel breder wordt tijdens de PowerUp
     */
    private final double multiplier;

    /**
     * constructur voor objecten van klasse Peddel
     *
     * @param hoogte de hoogte
     * @param paneel is het paneel dat aangemaakt wordt
     */
    public Peddel(double hoogte, Paneel paneel) {
        this.paneel = paneel;
        this.hoogte = hoogte;
        this.breedte = StartPaginaController.getPeddelBreedte();
        offset = 20;
        multiplier = 1.5;
        huidigeBreedte = breedte;
        x = (paneel.getBreedte() - breedte) / 2;
        y = paneel.getHoogte() - offset;
    }

    /**
     * geeft de hoogte van de peddel in het begin van het spel
     *
     * @return hoogte
     */
    public double getHoogte() {
        return hoogte;
    }

    /**
     * geeft de breedte van de peddel in het begin van het spel
     *
     * @return breedte
     */
    public double getBreedte() {
        return breedte;
    }

    /**
     * geeft de x positie
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * geeft de y positie
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * geeft de factor waarmee de peddel breder wordt tijdens de PowerUp
     *
     * @return multiplier
     */
    public double getMultiplier() {
        return multiplier;
    }

    /**
     * geeft de huidige breedte
     *
     * @return huidigeBreedte
     */
    public double getHuidigeBreedte() {
        return huidigeBreedte;
    }

    /**
     * de methode die de peddel reset
     */
    public void reset() {
        x = (paneel.getBreedte() - breedte) / 2;
        y = paneel.getHoogte() - offset;
        huidigeBreedte = breedte;
    }

    /**
     * methode die de x positie verandert
     *
     * @param x de x positie van de peddel
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * methode die de huidige breedteverandert
     *
     * @param breedte the breedte van de peddel
     */
    public void setHuidigeBreedte(double breedte) {
        this.huidigeBreedte = breedte;
    }
}
