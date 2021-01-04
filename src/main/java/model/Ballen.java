/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author lowie
 */
public class Ballen {

    private ArrayList<Bal> ballen;
    private int aantalExtraBallen;

    public Ballen() {
        aantalExtraBallen = 2;
        createBallen();
    }

    public void createBallen() {
        ballen = new ArrayList<>(aantalExtraBallen);
        for (int i = 0; i < aantalExtraBallen; i++) {

            ballen.add(new Bal(new Paneel()));
        }
    }
    
    public ArrayList<Bal> getBallen(){
        return ballen;
    }
    
    public int getAantalExtraBallen(){
        return aantalExtraBallen;
    }
}
