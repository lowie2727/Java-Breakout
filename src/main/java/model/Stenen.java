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
public class Stenen {

    private Steen stenen[][];
    private int rijen;
    private int kolommen;

    public Stenen() {
        rijen = 4;
        kolommen = 15;
        createMatrix();
    }

    public void createMatrix() {
        stenen = new Steen[rijen][kolommen];
        for (int j = 0; j < rijen; j++) {
            for (int i = 0; i < kolommen; i++) {
                stenen[i][j] = new Steen();
            }
        }
    }

    public Steen[][] getStenen() {
        return stenen;
    }

    public int getRijen() {
        return rijen;
    }

    public int getKolommen() {
        return kolommen;
    }

}
