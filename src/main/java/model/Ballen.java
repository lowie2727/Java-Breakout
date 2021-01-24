/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import be.inf1.mavenproject2.StartPaginaController;
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

    /**
     *
     * @param vensterModel
     * @param aantalBallen
     */
    public Ballen(Paneel vensterModel, int aantalBallen) {
        straal = StartPaginaController.getStraalBal();
        this.paneelModel = vensterModel;
        this.aantalBallen = 1;
        ballen = new ArrayList<>();
        createBallen();
    }

    /**
     *
     */
    public final void createBallen() {
        for (int i = 0; i < aantalBallen; i++) {
            ballen.add(new Bal(paneelModel, straal));
        }
    }

    /**
     *
     */
    public void reset() {
        for (int i = ballen.size() - 1; i >= 0; i--) {
            if (i == 0) {
                ballen.get(i).reset();
            } else {
                ballen.remove(i);
            }
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Bal> getBallen() {
        return ballen;
    }

    /**
     *
     * @param i
     * @return
     */
    public Bal getBal(int i) {
        return ballen.get(i);
    }

    /**
     *
     * @return
     */
    public int getHuidigAantalBallen() {
        return ballen.size();
    }

    /**
     *
     * @return
     */
    public int getAantalBallen() {
        return aantalBallen;
    }
}
