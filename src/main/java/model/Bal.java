/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lowie
 */
public class Bal {

    private final Paneel paneel;
    private final double straal;
    private double huidigeStraal;

    private double x;
    private double y;
    private double vx;
    private double vy;

    private final double snelheidX;
    private final double snelheidY;
    private final double snelheid;

    private boolean godMode;
    private final double multiplier;

    /**
     * constructor voor objecten van de klasse bal
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
     * @return geeft de x-coördinaten van het middelpunt van de bal
     */
    public double getX() {
        return x;
    }

    /**
     * @return geeft de y-coördinaten van het middelpunt van de bal
     */
    public double getY() {
        return y;
    }

    /**
     * @return geeft de snelheid van de bal in de x richting 
     */
    public double getVx() {
        return vx;
    }

    /**
     * @return geeft de snelheid van de bal in de y richting
     */
    public double getVy() {
        return vy;
    }

    /**
     * @return geeft de huidige straal van de bal
     */
    public double getHuidigeStraal() {
        return huidigeStraal;
    }

    /**
     * @return geeft de factor waarmee de bal vergroot wordt tijdens de PowerUp
     */
    public double getMutiplier() {
        return multiplier;
    }

    /**
     * @return geeft de status van de Bal van de bal terug
     * Bal wil zeggen dat de snelheid van de bal niet veranderd als hij botst met een steen, 
     * maar de steen gaat wel weg als de bal de steen raakt
     */
    public boolean isGodMode() {
        return godMode;
    }

    /**
     * @return geeft de beginstraal van de bal terug
     */
    public double getStraal() {
        return straal;
    }

    /**
     * @return geeft de beginsnelheid in de x richting terug waarmee de bal vertrekt
     */
    public double getSnelheidX() {
        return snelheidX;
    }

    /**
     * @return geeft de beginsnelheid in de y richting terug waarmee de bal vertrekt
     */
    public double getSnelheidY() {
        return snelheidY;
    }

    /**
     * @return geeft de totale vertoriële som van beginsnelheid terug waarmee de bal vertrekt
     */
    public double getSnelheid() {
        return snelheid;
    }

   

    /**
     * @return verplaatst de bal verticaal
     */
    public double verticaal() {
        y = y + vy;
        return y;
    }

    /**
     * @return verplaatst de bal horizontaal
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
    }

    /**
     * verandert de status van godMode
     * @param godMode nieuwe status van godMode
     */
    public void setGodMode(boolean godMode) {
        this.godMode = godMode;
    }

    /**
     * verandert de huidigestraal
     * @param straal de nieuwe huidige straal 
     */
    public void setHuidigeStraal(double straal) {
        huidigeStraal = straal;
    }

    /**
     * verandert de snelheid in de x richting
     * @param vx de snelheid in de x richting
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * verandert de snelheid in de y richting
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
     * @param x de x positie
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * verandert de y positie
     * @param y de y positie
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * keert de x snelheid om als de bal zijn maximale x positie bereikt
     */
    private void setMaxX() {
        if (x >= paneel.getBreedte() - getStraal()) {
            if (vx > 0) {
                vx = -vx;
            }
        }
    }

    /**
     * keert de x snelheid om als de bal zijn minimale x positie bereikt
     */
    private void setMinX() {
        if (x <= getStraal()) {
            if (vx < 0) {
                vx = -vx;
            }
        }
    }

    /**
     * keert de y snelheid om als de bal zijn maximale y positie bereikt
     */
    private void setMaxY() {
        if (y >= paneel.getHoogte() - straal) {
            vy = 0;
            vx = 0;
        }
    }

    /**
     * keert de y snelheid om als de bal zijn minimale y positie bereikt
     */
    private void setMinY() {
        if (y <= straal) {
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
