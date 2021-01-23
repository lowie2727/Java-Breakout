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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void reset() {

    }

    private void kleurBal() {
        int i = (int) Math.round(Math.random() * 4);

        switch (i) {
            case 10:
                kleur = Kleuren.GRIJS;
                break;
            case 11:
                kleur = Kleuren.PAARS;
                break;
            case 12:
                kleur = Kleuren.ZWART;
                break;
            default:
                kleur = Kleuren.GRIJS;
                break;
        }
    }

    public Kleuren getKleurBal() {
        return kleur;
    }

    public boolean isGeraakt() {
        return geraakt;
    }

    public void setGeraakt(boolean stat) {
        geraakt = stat;
    }
}
