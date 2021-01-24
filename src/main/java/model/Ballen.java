/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import be.inf1.mavenproject2.StartPaginaController;
import java.util.ArrayList;

/**
 * klasse Ballen
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Ballen {

    private final ArrayList<Bal> ballen;
    private final int aantalBallen;
    private final Paneel paneelModel;
    private final double straal;

    /**
     * contructor voor objecten van de klasse Ballen
     *
     * @param paneelModel is het paneel dat aangemaakt wordt
     * @param aantalBallen is de het aantal ballen
     */
    public Ballen(Paneel paneelModel, int aantalBallen) {
        straal = StartPaginaController.getStraalBal();
        this.paneelModel = paneelModel;
        this.aantalBallen = 1;
        ballen = new ArrayList<>();
        maakBallen();
    }

    /**
     * maakt alle ballen aan
     */
    private void maakBallen() {
        for (int i = 0; i < aantalBallen; i++) {
            ballen.add(new Bal(paneelModel, straal));
        }
    }

    /**
     * de methode die de ballen reset
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
     * geeft alle ballen in de ArrayList van het type bal
     * @return ballen in de ArrayList
     */
    public ArrayList<Bal> getBallen() {
        return ballen;
    }

    /**
     * geeft een bal op positie i in de ArrayList
     * @param i positie in de ArrayList
     * @return bal op positie i
     */
    public Bal getBal(int i) {
        return ballen.get(i);
    }

    /**
     * neemt de grote van de ArrayList ballen
     * @return grote van de ArrayList
     */
    public int getHuidigAantalBallen() {
        return ballen.size();
    }

    /**
     * geeft het aantal ballen in het begin van het spel
     * @return aantal ballen
     */
    public int getAantalBallen() {
        return aantalBallen;
    }
}
