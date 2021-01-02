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

    private Paneel paneel;
    private final double hoogte;
    private final double breedte;
    private final double offset;
    private double x;
    private double y;

    public Peddel(Paneel paneel) {
        this.paneel = paneel;
        offset = 20;
        hoogte = 10;
        breedte = 500;
        x = (paneel.getBreedte() - breedte) / 2;
        y = paneel.getHoogte() - offset;
    }

    /**
     * @return the hoogte
     */
    public double getHoogte() {
        return hoogte;
    }

    /**
     * @return the breedte
     */
    public double getBreedte() {
        return breedte;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the offset
     */
    public double getOffset() {
        return offset;
    }

    public void reset() {
        x = (paneel.getBreedte() - getBreedte()) / 2;
        y = paneel.getHoogte() - offset;
    }

    public double setMax() {
        if (x > paneel.getBreedte() - getBreedte()) {
            x = paneel.getBreedte() - getBreedte();
            return x;
        }
        return x;
    }

    public double setMin() {
        if (x < 0) {
            x = 0;
            return x;
        }
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}
