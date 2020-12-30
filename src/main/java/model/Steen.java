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
public class Steen {

    public double breedte;
    public double hoogte;

    public Steen() {
        hoogte = 20;
        breedte = 60;
    }

    /**
     * @return the breedte
     */
    public double getBreedte() {
        return breedte;
    }

    /**
     * @return the hoogte
     */
    public double getHoogte() {
        return hoogte;
    }
}
