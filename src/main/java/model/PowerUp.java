package model;

/**
 * klasse PowerUp
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class PowerUp {

    /**
     * de straal van de PowerUp
     */
    private final double straal;
    /**
     * de x positie van het middelpunt van een PowerUp
     */
    private final double x;
    /**
     * de y positie van het middelpunt van een PowerUp
     */
    private final double y;
    /**
     * de enum klasse Kleuren
     */
    private Kleuren kleur;
    /**
     * geeft de status of een bal een PowerUp geraakt heeft
     */
    private boolean geraakt;

    /**
     * constructor voor objecten van de klasse PowerUp
     *
     * @param straal de straal van de PowerUp
     * @param paneel het speelveld van de powerUp
     */
    public PowerUp(double straal, Paneel paneel) {
        this.straal = straal;
        x = Math.random() * paneel.getBreedte();
        y = Math.random() * paneel.getHoogte() / 2;
        kleurBal();
    }

    /**
     * geeft de straal van de PowerUp
     *
     * @return straal
     */
    public double getStraal() {
        return straal;
    }

    /**
     * geeft de x positie van de PowerUp
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * geeft de y positie van de PowerUp
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * methode om 1 van de 4 PowerUps willekeurig te selecteren
     */
    private void kleurBal() {
        int i = (int) Math.round(Math.random() * 4);
        switch (i) {
            case 1:
                kleur = Kleuren.PAARS;
                break;
            case 2:
                kleur = Kleuren.ZWART;
                break;
            case 3:
                kleur = Kleuren.ROZE;
                break;
            default:
                kleur = Kleuren.GRIJS;
                break;
        }
    }

    /**
     * geeft de kleur van de PowerUp
     *
     * @return kleur
     */
    public Kleuren getKleurBal() {
        return kleur;
    }

    /**
     * geeft de status of de bal geraakt is
     *
     * @return geraakt
     */
    public boolean isGeraakt() {
        return geraakt;
    }

    /**
     * methode om de status van de PowerUp te veranderen
     *
     * @param geraakt status of de PowerUp geraakt is
     */
    public void setGeraakt(boolean geraakt) {
        this.geraakt = geraakt;
    }
}
