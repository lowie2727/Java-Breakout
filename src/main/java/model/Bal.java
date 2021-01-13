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

    private final Paneel paneel;
    private final double straal;
    private double vx;
    private double vy;
    private double x;
    private double y;
    private final double SnelheidX;
    private final double SnelheidY;
    private double hy;

    public Bal(Paneel paneel, double straal) {
        SnelheidX = 0.3;
        SnelheidY = -0.3;
        this.paneel = paneel;
        x = 500;
        y = 472;
        vx = 0;
        vy = 0;
        hy = 1;
        this.straal = straal;
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
    public double getStraal() {
        return straal;
    }

    public double getSnelheidX() {
        return SnelheidX;
    }

    public double getSnelheidY() {
        return SnelheidY;
    }

    /**
     * @return the hy
     */
    public double getHy() {
        return hy;
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

    public void reset() {
        x = paneel.getBreedte() / 2;
        y = paneel.getHoogte() - getStraal() - 20;
        vx = 0;
        vy = 0;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void setVx() {
        vx = -vx;
    }

    public void setVy() {
        vy = -vy * hy;
    }

    public void setHy(double hy) {
        this.hy = hy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setMaxX() {
        if (x >= paneel.getBreedte() - getStraal()) {
            if (vx > 0) {
                vx = -vx;
            }
        }
    }

    public void setMinX() {
        if (x <= getStraal()) {
            if (vx < 0) {
                vx = -vx;
            }
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
            if (vy < 0) {
                vy = -vy;
            }
        }
    }

    public void tick() {
        setMaxX();
        setMaxY();
        setMinX();
        setMinY();
        horizontaal();
        verticaal();
    }

}
