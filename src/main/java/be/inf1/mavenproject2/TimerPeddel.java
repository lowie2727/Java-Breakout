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

    private int tijdPowerUp;
    private int tijdPeddel;

    public TimerPeddel() {

    }

    @Override
    public void run() {
        tijdPowerUp++;
        tijdPeddel++;
    }

    /**
     * @return the t
     */
    public int getTijdPowerUp() {
        return tijdPowerUp;
    }

    public void setTijdPowerUp() {
        tijdPowerUp = 0;
    }

    /**
     * @return the tijdPeddel
     */
    public int getTijdPeddel() {
        return tijdPeddel;
    }

    /**
     * @param tijdPeddel the tijdPeddel to set
     */
    public void setTijdPeddel() {
        tijdPeddel = 0;
    }
}
