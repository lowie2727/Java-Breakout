package model;

/**
 * klasse Paneel
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Paneel {

    /**
     * de breedte van het paneel
     */
    private final double breedte;
    /**
     * de hoogte van het paneel
     */
    private final double hoogte;

    /**
     * constructur voor objecten van klasse Paneel
     *
     * @param breedte de breedte van het paneel
     * @param hoogte de hoogte van het paneel
     */
    public Paneel(double breedte, double hoogte) {
        this.breedte = breedte;
        this.hoogte = hoogte;
    }

    /**
     * geeft de breedte van het paneel
     *
     * @return breedte
     */
    public double getBreedte() {
        return breedte;
    }

    /**
     * geeft de hoogte van het paneel
     *
     * @return hoogte
     */
    public double getHoogte() {
        return hoogte;
    }
}
