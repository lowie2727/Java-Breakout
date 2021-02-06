package model;

/**
 * klasse Paneel
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Paneel {

    /**
     * de breedte
     */
    private final double breedte;
    /**
     * de hoogte
     */
    private final double hoogte;

    /**
     * constructur voor objecten van klasse Paneel
     *
     * @param breedte de breedte
     * @param hoogte  de hoogte
     */
    public Paneel(double breedte, double hoogte) {
        this.breedte = breedte;
        this.hoogte = hoogte;
    }

    /**
     * geeft de breedte
     *
     * @return breedte
     */
    public double getBreedte() {
        return breedte;
    }

    /**
     * geeft de hoogte
     *
     * @return hoogte
     */
    public double getHoogte() {
        return hoogte;
    }
}
