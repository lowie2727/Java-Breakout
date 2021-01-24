/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * klasse Paneel
 * 
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Paneel {

    private final double breedte;
    private final double hoogte;

    /**
     * constructur voor objecten van klasse Paneel
     * 
     * @param breedte de breedte van het paneel
     * @param hoogte de hoogte van het paneel
     */
    public Paneel(double breedte, double hoogte) {
        this.breedte = breedte;
        this.hoogte = hoogte;
    }

    /**
     * @return de breedte van het paneel
     */
    public double getBreedte() {
        return breedte;
    }

    /**
     * @return de hoogte van het paneel
     */
    public double getHoogte() {
        return hoogte;
    }
}
