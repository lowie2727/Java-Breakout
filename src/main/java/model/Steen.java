package model;

/**
 * klasse Steen
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Steen {

    /**
     * de breedte van een steen
     */
    private final double breedte;
    /**
     * de hoogte van een steen
     */
    private final double hoogte;
    /**
     * de x positie van een steen
     */
    private final double x;
    /**
     * de y positie van een steen
     */
    private final double y;
    /**
     * de status van de bal of deze geraakt is
     */
    private boolean geraakt;

    /**
     * constructor voor objecten van de klasse Steen
     *
     * @param breedte de breedte van 1 steen
     * @param hoogte  de hoogte van 1 steen
     * @param x       de x positie van een steen
     * @param y       de y positie van een steen
     */
    public Steen(double breedte, double hoogte, double x, double y) {
        this.hoogte = hoogte;
        this.breedte = breedte;
        this.x = x;
        this.y = y;
    }

    /**
     * geeft de breedte van een steen
     *
     * @return breedte
     */
    public double getBreedte() {
        return breedte;
    }

    /**
     * geeft de hoogte van een steen
     *
     * @return hoogte
     */
    public double getHoogte() {
        return hoogte;
    }

    /**
     * geeft de x positie van een steen
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * geeft de y positie van een steen
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * geeft de status van geraakt, de bal is geraakt of niet
     *
     * @return geraakt
     */
    public boolean isGeraakt() {
        return geraakt;
    }

    /**
     * verandert de status van geraakt
     */
    public void setGeraakt() {
        geraakt = true;
    }
}
