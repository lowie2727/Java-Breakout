/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jonas
 */
public class PowerUp {

    private double straal;

    public PowerUp(double straal) {
        this.straal = straal;
    }

    /**
     * @return the diameter
     */
    public double getStraal() {
        return straal;
    }
}
