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
    private double straal;
    private double vx;
    private double vy;
    private double x;
    private double y;
    private double cos;

    public Bal(Paneel paneel, Peddel peddel) {
        this.paneel = paneel;
        this.peddel = peddel;
        vx = 0;
        vy = 0;
        straal = 8;
        x = paneel.getBreedte() / 2;
        y = peddel.getY() - straal;

        cos = 2 * Math.cos(Math.toRadians(45));
    }

    public Bal(Paneel paneel) {
        this.paneel = paneel;
        x = 500;
        y = 472;
        vx = 0;
        vy = 0;
        straal = 8;
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
            vx = -1;
        }
    }

    public void setMinX() {
        if (x <= getStraal()) {
            vx = 1;
        }
    }

    public void setMaxY() {
        if (y >= paneel.getHoogte() - getStraal()) {
            vy = 0;
            vx = 0;
        }
    }

    public void setMinY() {
        if (y <= getStraal()) {
            vy = 1;
        }
    }

    public void reset() {
        //x = paneel.getBreedte() / 2;
        x = 500;
        //y = peddel.getY() - getStraal();
        y = 472;
        vx = 0;
        vy = 0;
    }

    public void start() {
        vx = 1;
        vy = -1;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void tick() {
        setMaxX();
        setMaxY();
        setMinX();
        setMinY();
        horizontaal();
        verticaal();

        //horizontaal();
        //verticaal();
    }

    public double getCos() {
        return cos;
    }
}
