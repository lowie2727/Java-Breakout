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
public class Bal {

    private Paneel paneel;
    private Peddel peddel;
    private final double straal;
    private double vx;
    private double vy;
    private double x;
    private double y;

    public Bal(Paneel paneel, Peddel peddel) {
        this.paneel = paneel;
        this.peddel = peddel;
        vx = 0.5;
        vy = -0.5;
        straal = 8;
        x = paneel.getBreedte() / 2;
        y = peddel.getY() - straal;
    }

    /**
     * @return the straal
     */
    public double getStraal() {
        return straal;
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

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public double verticaal() {
        y = y + vy;
        return y;
    }

    public double horizontaal() {
        x = x + vx;
        return x;
    }

    public void setMaxX() {
        if (x >= paneel.getBreedte() - getStraal()) {
            vx = vx * -1;
        }
    }

    public void setMinX() {
        if (x <= getStraal()) {
            vx = vx * -1;
        }
    }

    public void setMaxY() {
        if (y >= paneel.getHoogte() - getStraal()) {
            vx = 0;
            vy = 0;
        }
    }

    public void setMinY() {
        if (y <= getStraal()) {
            vy = vy * -1;
        }
    }

    public void reset() {
        x = paneel.getBreedte() / 2;
        y = peddel.getY() - getStraal();
        vx = 0.5;
        vy = -0.5;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void tick() {
        horizontaal();
        verticaal();
        setMaxX();
        setMaxY();
        setMinX();
        setMinY();

    }

}
