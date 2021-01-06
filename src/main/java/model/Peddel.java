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
        breedte = 1200;
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
}
