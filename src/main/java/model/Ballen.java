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
    private final int aantalBallen;
    private Paneel paneelModel;

    public Ballen(Paneel vensterModel, int aantalBallen) {
        this.paneelModel = vensterModel;
        this.aantalBallen = aantalBallen;
        createBallen();
    }

    public final void createBallen() {
        ballen = new ArrayList<>(aantalBallen);
        for (int i = 0; i < aantalBallen; i++) {
            ballen.add(new Bal(paneelModel, 8));
        }
    }

    public ArrayList<Bal> getBallen() {
        return ballen;
    }

    public int getAantalBallen() {
        return aantalBallen;
    }
}
