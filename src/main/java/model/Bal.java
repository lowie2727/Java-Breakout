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
    private final double snelheidX;
    private final double snelheidY;
    private final double snelheid;
    private double hy;

    public Bal(Paneel paneel, double straal) {
        snelheidX = 0.4;
        snelheidY = -0.4;
        snelheid = Math.sqrt((Math.pow(snelheidX, 2)) + Math.pow(snelheidY, 2));
        this.paneel = paneel;
        this.straal = straal;
        x = paneel.getBreedte() / 2;
        y = paneel.getHoogte() - straal - 20;
        vx = 0;
        vy = 0;
        hy = 1;
        
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
        return snelheidX;
    }

    public double getSnelheidY() {
        return snelheidY;
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
        y = paneel.getHoogte() - straal - 20;
        vx = 0;
        vy = 0;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
        vx = Math.sqrt((Math.pow(snelheid, 2)) - Math.pow(vy, 2));
    }

    public void setVx() {
        vx = -vx;
    }

    public void setVy() {
        vy = -vy;
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
        if (y >= paneel.getHoogte() - straal) {
            vy = 0;
            vx = 0;
        }
    }

    public void setMinY() {
        if (y <= straal) {
            if (vy < 0) {
                vy = -vy;
            }
        }
    }

    public void tick() {
        setMaxX();
        setMinX();
        setMinY();
        horizontaal();
        verticaal();
    }

}
