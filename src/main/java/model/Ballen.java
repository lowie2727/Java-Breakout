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

    private final ArrayList<Bal> ballen;
    private final int aantalBallen;
    private final Paneel paneelModel;
    private final double straal;

    public Ballen(Paneel vensterModel, int aantalBallen) {
        straal = 8;
        this.paneelModel = vensterModel;
        this.aantalBallen = 5;
        ballen = new ArrayList<>();
        createBallen();
    }

    public final void createBallen() {
        for (int i = 0; i < aantalBallen; i++) {
            ballen.add(new Bal(paneelModel, straal));
        }
    }

    public void reset() {
        for (int i = ballen.size() - 1; i >= 0; i--) {
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

    public int getHuidigAantalBallen() {
        return ballen.size();
    }

    public int getAantalBallen() {
        return aantalBallen;
    }
}
