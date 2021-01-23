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
    private double x;
    private double y;
    private boolean geraakt;

    public Steen(double breedte, double hoogte, double x, double y) {
        this.hoogte = hoogte;
        this.breedte = breedte;
        this.x = x;
        this.y = y;
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
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public boolean isGeraakt(){
        return geraakt;
    }
    
    public void setGeraakt(){
        geraakt = true;
    }
}
