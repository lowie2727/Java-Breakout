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
    private final Paneel paneelModel;
    private final double straal;

    public Ballen(Paneel vensterModel, int aantalBallen) {
        straal = 8;
        this.paneelModel = vensterModel;
        this.aantalBallen = 1;
        createBallen();
    }

    public final void createBallen() {
        ballen = new ArrayList<>(aantalBallen);
        for (int i = 0; i < aantalBallen; i++) {
            ballen.add(new Bal(paneelModel, straal));
        }
    }

    public void reset() {
        for (int i = 0; i < aantalBallen; i++) {
            if (i == 0) {
                ballen.get(i).reset();
            } else {
                ballen.remove(i);
            }

        }
    }

    public ArrayList<Bal> getBallen() {
        return ballen;
    }

    public Bal getBal(int i) {
        return ballen.get(i);
    }

    public int getAantalBallen() {
        return aantalBallen;
    }
}
