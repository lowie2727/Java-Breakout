/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.inf1.mavenproject2;

import java.util.TimerTask;

/**
 *
 * @author arnoa
 */
public class TimerPeddel extends TimerTask {

    private int tijdsintervalPowerUp;
    private int tijdsduurPowerUp;

    public TimerPeddel() {

    }

    @Override
    public void run() {
        tijdsintervalPowerUp++;
        tijdsduurPowerUp++;
    }

    /**
     * @return the t
     */
    public int getTijdsintervalPowerUp() {
        return tijdsintervalPowerUp;
    }

    public void setTijdsintervalPowerUp() {
        tijdsintervalPowerUp = 0;
    }

    /**
     * @return the tijdsduurPowerUp
     */
    public int getTijdsduurPowerUp() {
        return tijdsduurPowerUp;
    }

    /**
     * @param tijdPeddel the tijdsduurPowerUp to set
     */
    public void settijdsduurPowerUp() {
        tijdsduurPowerUp = 0;
    }
}
