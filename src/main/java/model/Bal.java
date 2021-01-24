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
    private double huidigeStraal;

    private double x;
    private double y;
    private double vx;
    private double vy;

    private final double snelheidX;
    private final double snelheidY;
    private final double snelheid;

    private boolean godMode;
    private final double multiplier;

    /**
     *
     * @param paneel
     * @param straal
     */
    public Bal(Paneel paneel, double straal) {
        this.paneel = paneel;
        this.straal = straal;

        snelheidX = 4;
        snelheidY = -4;
        snelheid = Math.sqrt((Math.pow(snelheidX, 2)) + Math.pow(snelheidY, 2));

        huidigeStraal = straal;

        x = paneel.getBreedte() / 2;
        y = paneel.getHoogte() - straal - 20;
        vx = 0;
        vy = 0;
        
        multiplier = 1.5;
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
     *
     * @return
     */
    public double getHuidigeStraal() {
        return huidigeStraal;
    }

    /**
     *
     * @return
     */
    public double getMutiplier() {
        return multiplier;
    }

    /**
     *
     * @return
     */
    public boolean isGodMode() {
        return godMode;
    }

    /**
     * @return
     */
    public double getStraal() {
        return straal;
    }

    /**
     *
     * @return
     */
    public double getSnelheidX() {
        return snelheidX;
    }

    /**
     *
     * @return
     */
    public double getSnelheidY() {
        return snelheidY;
    }

    /**
     *
     * @return
     */
    public double getSnelheid() {
        return snelheid;
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

    /**
     *
     */
    public void reset() {
        x = paneel.getBreedte() / 2;
        y = paneel.getHoogte() - straal - 20;
        vx = 0;
        vy = 0;
    }

    /**
     *
     * @param stat
     */
    public void setGodMode(boolean stat) {
        godMode = stat;
    }

    /**
     *
     * @param straal
     */
    public void setHuidigeStraal(double straal) {
        huidigeStraal = straal;
    }

    /**
     *
     * @param vx
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     *
     * @param vy
     */
    public void setVy(double vy) {
        this.vy = vy;
        if (vx < 0) {
            vx = Math.sqrt((Math.pow(snelheid, 2)) - Math.pow(vy, 2));
            vx = vx * -1;
        } else {
            vx = Math.sqrt((Math.pow(snelheid, 2)) - Math.pow(vy, 2));
        }
    }

    /**
     *
     */
    public void setVx() {
        vx = -vx;
    }

    /**
     *
     */
    public void setVy() {
        vy = -vy;
    }

    /**
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     *
     */
    public void setMaxX() {
        if (x >= paneel.getBreedte() - getStraal()) {
            if (vx > 0) {
                vx = -vx;
            }
        }
    }

    /**
     *
     */
    public void setMinX() {
        if (x <= getStraal()) {
            if (vx < 0) {
                vx = -vx;
            }
        }
    }

    /**
     *
     */
    public void setMaxY() {
        if (y >= paneel.getHoogte() - straal) {
            vy = 0;
            vx = 0;
        }
    }

    /**
     *
     */
    public void setMinY() {
        if (y <= straal) {
            if (vy < 0) {
                vy = -vy;
            }
        }
    }

    /**
     *
     */
    public void tick() {
        setMaxX();
        setMinX();
        setMaxY();
        setMinY();
        horizontaal();
        verticaal();
    }

    
    
}
