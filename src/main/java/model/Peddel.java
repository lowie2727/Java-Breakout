/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import be.inf1.mavenproject2.StartPaginaController;

/**
 * klasse Peddel
 * 
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Peddel {

    private final Paneel paneel;

    private final double hoogte;
    private final double breedte;
    private double huidigeBreedte;

    private final double offset;
    private double x;
    private double y;

    private final double multiplier;

    /**
     * constructur voor objecten van klasse Peddel
     * 
     * @param hoogte de hoogte van de peddel
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
     * @return geeft de hoogte van de peddel in het begin van het spel
     */
    public double getHoogte() {
        return hoogte;
    }

    /**
     * @return geeft de breedte van de peddel in het begin van het spel
     */
    public double getBreedte() {
        return breedte;
    }

    /**
     * @return geeft de x positie van de peddel
     */
    public double getX() {
        return x;
    }

    /**
     * @return geeft de y positie van de peddel
     */
    public double getY() {
        return y;
    }

    /**
     * @return geeft hoe ver de peddel van de onderkant van het paneel staat
     */
    public double getOffset() {
        return offset;
    }

    /**
     * @return geeft de factor waarmee de bal breder wordt tijdens de PowerUp
     */
    public double getMultiplier() {
        return multiplier;
    }

    /**
     * @return geeft de huidige breedte van de peddel
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
     * methode die de x positie van de peddel verandert
     * @param x de x positie van de peddel
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * methode die de huidige breedte van de bal verandert
     * @param breedte the breedte van de peddel
     */
    public void setHuidigeBreedte(double breedte) {
        this.huidigeBreedte = breedte;
    }
}
