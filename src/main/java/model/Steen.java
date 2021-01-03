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

    private final double breedte;
    private final double hoogte;

    public Steen() {
        hoogte = 20;
        breedte = 60;
    }

    /**
     * @return
     */
    public double getBreedte() {
        return breedte;
    }

    /**
     * @return
     */
    public double getHoogte() {
        return hoogte;
    }
}
