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

    private ArrayList<Bal> ballen;
    private final int aantalBallen;
    private final Paneel paneelModel;
    private double straal;

    public Ballen(Paneel vensterModel, int aantalBallen) {
        straal = StartPaginaController.getStraalBal();
        this.paneelModel = vensterModel;
        this.aantalBallen = aantalBallen;
        createBallen();
    }

    public final void createBallen() {
        ballen = new ArrayList<>(aantalBallen);
        for (int i = 0; i < aantalBallen; i++) {
            ballen.add(new Bal(paneelModel, straal));
        }
    }

    public ArrayList<Bal> getBallen() {
        return ballen;
    }

    public int getAantalBallen() {
        return aantalBallen;
    }
}
