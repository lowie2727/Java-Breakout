/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import be.inf1.mavenproject2.StartPaginaController;

/**
 *
 * @author lowie
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
     *
     * @param hoogte
     * @param paneel
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
     * @return
     */
    public double getHoogte() {
        return hoogte;
    }

    /**
     * @return
     */
    public double getBreedte() {
        return breedte;
    }

    /**
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * @return
     */
    public double getOffset() {
        return offset;
    }

    /**
     *
     * @return
     */
    public double getMultiplier() {
        return multiplier;
    }

    /**
     *
     * @return
     */
    public double getHuidigeBreedte() {
        return huidigeBreedte;
    }

    /**
     *
     */
    public void reset() {
        x = (paneel.getBreedte() - breedte) / 2;
        y = paneel.getHoogte() - offset;
        huidigeBreedte = breedte;
    }

    /**
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param breedte the breedte to set
     */
    public void setHuidigeBreedte(double breedte) {
        this.huidigeBreedte = breedte;
    }
}
