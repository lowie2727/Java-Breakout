/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lowie
 */
public class Peddel {

    private final Paneel paneel;

    private final double hoogte;
    private double breedte;

    private final double offset;
    private double x;
    private double y;
    private double vergroot;

    public Peddel(double breedte, double hoogte, Paneel paneel) {
        this.paneel = paneel;
        offset = 20;
        this.hoogte = hoogte;
        this.breedte = breedte;
        x = (paneel.getBreedte() - breedte) / 2;
        y = paneel.getHoogte() - offset;
        vergroot = 1.5;
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

    public void reset() {
        x = (paneel.getBreedte() - getBreedte()) / 2;
        y = paneel.getHoogte() - offset;
    }

    public void setMax() {
        if (x > paneel.getBreedte() - getBreedte()) {
            x = paneel.getBreedte() - getBreedte();
        }
    }

    public void setMin() {
        if (x < 0) {
            x = 0;
        }
    }

    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param breedte the breedte to set
     */
    public void setBreedte(double breedte) {
        this.breedte = breedte;
    }
}
