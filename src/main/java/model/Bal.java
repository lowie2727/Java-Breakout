package model;

/**
 * klasse Bal
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Bal {

    private final Paneel paneel;
    private final double straal;
    private double huidigeStraal;

    /**
     * de x positie van het middelpunt van een bal
     */
    private double x;
    /**
     * de y positie van het middelpunt van een bal
     */
    private double y;
    /**
     * de snelheid in de x richting van een bal
     */
    private double vx;
    /**
     * de snelheid in de y richting van een bal
     */
    private double vy;

    /**
     * de beginsnelheid in de x richting van een bal
     */
    private final double snelheidX;
    /**
     * de beginsnelheid in de y richting van een bal
     */
    private final double snelheidY;
    /**
     * de vectoriële som van de totale snelheid van een bal
     */
    private final double snelheid;

    /**
     * de status of de bal in godMode is
     */
    private boolean godMode;
    /**
     * de factor waarmee de bal groter wordt tijdens de PowerUp
     */
    private final double multiplier;

    /**
     * constructor voor objecten van de klasse Bal
     *
     * @param paneel is het paneel dat aangemaakt wordt
     * @param straal is de straal van de bal
     */
    public Bal(Paneel paneel, double straal) {
        this.paneel = paneel;
        this.straal = straal;

        snelheidX = 4;
        snelheidY = -4;
        snelheid = Math.sqrt((Math.pow(snelheidX, 2)) + Math.pow(snelheidY, 2));

        huidigeStraal = straal;

        x = paneel.getBreedte() / 2;
        y = paneel.getHoogte() - straal - 20;
        vx = 0;
        vy = 0;

        multiplier = 1.5;
    }

    /**
     * geeft de x-coördinaten van het middelpunt van de bal
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * geeft de y-coördinaten van het middelpunt van de bal
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * geeft de snelheid van de bal in de x richting
     *
     * @return vx
     */
    public double getVx() {
        return vx;
    }

    /**
     * geeft de snelheid van de bal in de y richting
     *
     * @return vy
     */
    public double getVy() {
        return vy;
    }

    /**
     * geeft de huidige straal van de bal
     *
     * @return huidigeStraal
     */
    public double getHuidigeStraal() {
        return huidigeStraal;
    }

    /**
     * geeft de factor waarmee de bal vergroot wordt tijdens de PowerUp
     *
     * @return multiplier
     */
    public double getMutiplier() {
        return multiplier;
    }

    /**
     * geeft de status van de godMode van een bal terug
     *
     * @return godMode true indien godMode actief is godMode wil zeggen dat de
     * snelheid van de bal niet veranderd als hij botst met een steen, maar de
     * steen gaat wel weg als de bal de steen raakt
     */
    public boolean isGodMode() {
        return godMode;
    }

    /**
     * geeft de beginstraal van de bal terug
     *
     * @return straal
     */
    public double getStraal() {
        return straal;
    }

    /**
     * geeft de beginsnelheid in de x richting terug waarmee de bal vertrekt
     *
     * @return snelheidX
     */
    public double getSnelheidX() {
        return snelheidX;
    }

    /**
     * geeft de beginsnelheid in de y richting terug waarmee de bal vertrekt
     *
     * @return snleheidY
     */
    public double getSnelheidY() {
        return snelheidY;
    }

    /**
     * geeft de totale vertoriële som van beginsnelheid terug waarmee de bal
     * vertrekt
     *
     * @return snelheid
     */
    public double getSnelheid() {
        return snelheid;
    }

    /**
     * verplaatst de bal verticaal
     *
     * @return y
     */
    public double verticaal() {
        y = y + vy;
        return y;
    }

    /**
     * verplaatst de bal horizontaal
     *
     * @return x
     */
    public double horizontaal() {
        x = x + vx;
        return x;
    }

    /**
     * de methode die de bal reset
     */
    public void reset() {
        x = paneel.getBreedte() / 2;
        y = paneel.getHoogte() - straal - 20;
        vx = 0;
        vy = 0;
        godMode = false;
        huidigeStraal = straal;
    }

    /**
     * verandert de status van godMode
     *
     * @param godMode true indien de godMode actief is
     */
    public void setGodMode(boolean godMode) {
        this.godMode = godMode;
    }

    /**
     * verandert de huidigestraal
     *
     * @param straal de nieuwe huidige straal
     */
    public void setHuidigeStraal(double straal) {
        huidigeStraal = straal;
    }

    /**
     * verandert de snelheid in de x richting
     *
     * @param vx de snelheid in de x richting
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * verandert de snelheid in de y richting
     *
     * @param vy de snelheid in de y richting
     */
    public void setVy(double vy) {
        this.vy = vy;
        if (vx < 0) {
            vx = Math.sqrt((Math.pow(snelheid, 2)) - Math.pow(vy, 2));
            vx = vx * -1;
        } else {
            vx = Math.sqrt((Math.pow(snelheid, 2)) - Math.pow(vy, 2));
        }
    }

    /**
     * keert de x snelheid om
     */
    public void setVx() {
        vx = -vx;
    }

    /**
     * keert de y snelheid om
     */
    public void setVy() {
        vy = -vy;
    }

    /**
     * verandert de x positie
     *
     * @param x de x positie
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * verandert de y positie
     *
     * @param y de y positie
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * keert de x snelheid om als de bal zijn maximale x positie bereikt
     */
    private void setMaxX() {
        if (x >= paneel.getBreedte() - huidigeStraal) {
            if (vx > 0) {
                vx = -vx;
            }
        }
    }

    /**
     * keert de x snelheid om als de bal zijn minimale x positie bereikt
     */
    private void setMinX() {
        if (x <= huidigeStraal) {
            if (vx < 0) {
                vx = -vx;
            }
        }
    }

    /**
     * keert de y snelheid om als de bal zijn maximale y positie bereikt
     */
    private void setMaxY() {
        if (y >= paneel.getHoogte() - huidigeStraal) {
            vy = 0;
            vx = 0;
        }
    }

    /**
     * keert de y snelheid om als de bal zijn minimale y positie bereikt
     */
    private void setMinY() {
        if (y <= huidigeStraal) {
            if (vy < 0) {
                vy = -vy;
            }
        }
    }

    /**
     * gaat de methodes die opgeroepen zijn controleren en uitvoeren
     */
    public void tick() {
        setMaxX();
        setMinX();
        setMaxY();
        setMinY();
        horizontaal();
        verticaal();
    }

}
