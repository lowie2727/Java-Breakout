/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * klasse PowerUp
 * 
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne 
 */
public class PowerUp {

    private final double straal;
    private final double x;
    private final double y;
    private Kleuren kleur;
    private boolean geraakt;

    /**
     * constructor voor objecten van de klasse PowerUp
     * 
     * @param straal de straal van de PowerUp
     * @param paneel 
     */
    public PowerUp(double straal, Paneel paneel) {
        this.straal = straal;
        x = Math.random() * paneel.getBreedte();
        y = Math.random() * paneel.getHoogte() / 2;
        kleurBal();
    }

    /**
     * @return the diameter
     */
    public double getStraal() {
        return straal;
    }

    /**
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public double getY() {
        return y;
    }

    private void kleurBal() {
        int i = (int) Math.round(Math.random() * 4);
        if (i == 1) {
            kleur = Kleuren.GRIJS;
        } else if (i == 2) {
            kleur = Kleuren.ZWART;
        } else if (i == 3) {
            kleur = Kleuren.ROZE;
        } else {
            kleur = Kleuren.PAARS;
        }
    }

    /**
     *
     * @return
     */
    public Kleuren getKleurBal() {
        return kleur;
    }

    /**
     *
     * @return
     */
    public boolean isGeraakt() {
        return geraakt;
    }

    /**
     *
     * @param stat
     */
    public void setGeraakt(boolean stat) {
        geraakt = stat;
    }
}
