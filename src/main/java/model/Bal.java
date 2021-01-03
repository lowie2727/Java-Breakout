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
    private double cos;

    public Bal(Paneel paneel, Peddel peddel) {
        this.paneel = paneel;
        this.peddel = peddel;
        vx = 0.5;
        vy = -0.5;
        straal = 8;
        x = paneel.getBreedte() / 2;
        y = peddel.getY() - straal;

        cos = Math.cos(Math.toRadians(45));
    }

    /**
     * @return
     */
    public double getStraal() {
        return straal;
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
    public double getVx() {
        return vx;
    }

    /**
     * @return
     */
    public double getVy() {
        return vy;
    }

    /**
     * @return
     */
    public double verticaal() {
        y = y + vy;
        return y;
    }

    /**
     * @return
     */
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

    /**
     * @return
     */
    public double getLBorder() {
        return getX() - getStraal();
    }

    /**
     * @return
     */
    public double getRBorder() {
        return getX() + getStraal();
    }

    /**
     * @return
     */
    public double getBBorder() {
        return getY() - getStraal();
    }

    /**
     * @return
     */
    public double getOBorder() {
        return getY() + getStraal();
    }

    /**
     * @return
     */
    public double getLOBBorderX() {
        return getX() - cos * getStraal();
    }

    /**
     * @return
     */
    public double getROBBorderX() {
        return getX() + cos * getStraal();
    }

    /**
     * @return
     */
    public double getORLBorderY() {
        return getY() + cos * getStraal();
    }

    /**
     * @return
     */
    public double getBRLBorderY() {
        return getY() - cos * getStraal();
    }
}
