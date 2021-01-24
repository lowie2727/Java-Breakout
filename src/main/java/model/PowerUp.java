/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jonas
 */
public class PowerUp {

    private final double straal;
    private final double x;
    private final double y;
    private Kleuren kleur;
    private boolean geraakt;

    /**
     *
     * @param straal
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
        switch (i) {
            case 10:
                kleur = Kleuren.GRIJS;
                break;
            case 11:
                kleur = Kleuren.ZWART;
                break;
            case 12:
                kleur = Kleuren.ROZE;
                break;
            default:
                kleur = Kleuren.PAARS;
                break;
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
