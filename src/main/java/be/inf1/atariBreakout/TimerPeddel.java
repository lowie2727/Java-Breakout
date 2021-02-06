/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.inf1.atariBreakout;

import java.util.TimerTask;

/**
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class TimerPeddel extends TimerTask {

    private int tijdsintervalPowerUp;
    private int tijdsduurPowerUp;
    private long tijdTotaal;

    @Override
    public void run() {
        tijdsintervalPowerUp++;
        tijdsduurPowerUp++;
        tijdTotaal++;
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

    public long getTijdTotaal() {
        return tijdTotaal;
    }

    public void setTijdsduurPowerUp() {
        tijdsduurPowerUp = 0;
    }

    public void setTijdTotaal() {
        tijdTotaal = 0;
    }
}
