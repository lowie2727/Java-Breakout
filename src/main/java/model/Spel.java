package model;

import be.inf1.atariBreakout.StartPaginaController;
import be.inf1.atariBreakout.TimerPeddel;
import javafx.scene.control.Alert;

import java.util.ArrayList;

/**
 * klasse Spel
 * <p>
 * Lowie Van Vyve: 65% Arnaud Paquet: 10% Jonas Vandenborne: 25%
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Spel {

    private final int aantalBallen;
    private final ArrayList<Bal> ballen;
    private Steen[][] stenen;
    private final Steen steen;
    private final double offsetBreedte;
    private final double offsetHoogte;
    private double offsetBreedtePaneel;
    private final double offsetHoogtePaneel;
    private boolean gewonnen;
    private PowerUp powerUp;
    private final Peddel peddel;
    private final Paneel paneel;
    private final int rijen;
    private int kolommen;
    private int i;

    private final TimerPeddel timerPeddel;
    /**
     * de tijdsduur van een powerup
     */
    private final int maxTijdsduurPowerUp;

    /**
     * de tijdsduur tussen powerups in
     */
    private final int maxTijdsduurTussenPowerUp;
    /**
     * de status of de boolean zichtbaar is
     */
    private boolean toonLabel;
    /**
     * de status of de bal geraakt is
     */
    private boolean balStatus;
    /**
     * de status of de bal weg is
     */
    private boolean balStatusNa;

    /**
     * constructor voor objecten van klasse Spel
     *
     * @param paneel      vraagt het model van Paneel op
     * @param timerPeddel vraagt de timerpeddel op
     */
    public Spel(Paneel paneel, TimerPeddel timerPeddel) {
        this.paneel = paneel;

        steen = new Steen(60, 20, 0, 0);
        peddel = new Peddel(10, paneel);
        powerUp = new PowerUp(20, paneel);
        offsetBreedte = 5;
        offsetHoogte = 5;
        offsetHoogtePaneel = 50;
        rijen = StartPaginaController.getAantalRijen();
        kolommen = 30;
        setMaxKolommen(kolommen);
        setOffsetBreedtePaneel(kolommen);

        ballen = new ArrayList<>();
        aantalBallen = 1;
        maakBallen();
        maakStenen();

        this.timerPeddel = timerPeddel;
        this.maxTijdsduurTussenPowerUp = StartPaginaController.getIntervalPowerUp();
        this.maxTijdsduurPowerUp = StartPaginaController.getIntervalPowerUpDuration();
    }

    /**
     * deze methode update de klasse Spel
     */
    public void update() {
        botsingBalStenen();
        botsingBalPeddel();
        botsingBalPowerUp();
        PowerUpVoorbij();
        toonPowerUp();
        checkGewonnen();
        notificatie();
    }

    /**
     * de methode reset de klasse Spel
     */
    public void reset() {
        resetBallen();
        peddel.reset();
        resetStenen();
        toonLabel = false;
        timerPeddel.setTijdsintervalPowerUp();
        timerPeddel.setTijdsduurPowerUp();
        powerUp = new PowerUp(20, paneel);
    }

    private void checkGewonnen() {
        if (getAantalStenen() == 0 && i==0) {
            gewonnen = true;
        }
    }

    public int getAantalStenen() {
        int aantalStenen = 0;
        for (int j = 0; j < rijen; j++) {
            for (int i = 0; i < kolommen; i++) {
                if (!stenen[j][i].isGeraakt()) {
                    aantalStenen++;
                }
            }
        }
        return aantalStenen;
    }

    private void resetStenen() {
        maakStenen();
        i=0;
        gewonnen = false;
    }

    public int getRijen() {
        return rijen;
    }

    public int getKolommen() {
        return kolommen;
    }

    private void maakBallen() {
        for (int i = 0; i < aantalBallen; i++) {
            ballen.add(new Bal(paneel, 8));
        }
    }

    private void maakStenen() {
        stenen = new Steen[rijen][kolommen];
        for (int j = 0; j < rijen; j++) {
            for (int i = 0; i < kolommen; i++) {
                double breedte = steen.getBreedte() + offsetBreedte;
                double hoogte = steen.getHoogte() + offsetHoogte;
                stenen[j][i] = new Steen(steen.getBreedte(), steen.getHoogte(),
                        breedte * i + offsetBreedtePaneel, hoogte * j + offsetHoogtePaneel);
            }
        }
    }

    private void setMaxKolommen(int kolommen) {
        if (kolommen * (steen.getBreedte() + offsetBreedte) >= paneel.getBreedte()) {
            this.kolommen = (int) ((paneel.getBreedte() / (steen.getBreedte() + offsetBreedte))) - 1;
        }
    }

    private void resetBallen() {
        for (int i = ballen.size() - 1; i >= 0; i--) {
            if (i == 0) {
                ballen.get(i).reset();
            } else {
                ballen.remove(i);
            }
        }
    }

    private void setOffsetBreedtePaneel(int kolommen) {
        offsetBreedtePaneel = (paneel.getBreedte() - kolommen * (steen.getBreedte() + offsetBreedte) - offsetBreedte) / 2;
    }

    public ArrayList<Bal> getBallen() {
        return ballen;
    }

    /**
     * deze methode zorgt voor de notificaties zoals gewonnen en verloren
     */
    public void notificatie() {
        if (gewonnen && i==0) {
            gewonnen();
            i=1;
        }
    }

    /**
     * deze methode laat een bericht zien als je gewonnen bent
     */
    private void gewonnen() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("gewonnen");
        alert.setContentText("je hebt gewonnen!\nproficiat!\ndruk op reset om opnieuw te beginnen");
        alert.show();
    }

    public Steen getSteen(int j, int i) {
        return stenen[j][i];
    }

    /**
     * deze methode regiseert de botsingen tussen de bal en de stenen
     */
    private void botsingBalStenen() {
        for (Bal bal : ballen) {
            for (int j = 0; j < rijen; j++) {
                for (int i = 0; i < kolommen; i++) {
                    Steen steen = getSteen(j, i);
                    if (!steen.isGeraakt()) {
                        if (bal.getY() + bal.getHuidigeStraal() >= steen.getY() - 3 //onderkant bal met bovenkant steen
                                && bal.getY() + bal.getHuidigeStraal() <= steen.getY() + 3
                                && bal.getX() >= steen.getX()
                                && bal.getX() <= steen.getX() + steen.getBreedte()) {
                            steen.setGeraakt();
                            if (bal.getVy() > 0 && !bal.isGodMode()) {
                                bal.setVy();
                            }
                        } else if (bal.getY() - bal.getHuidigeStraal() >= steen.getY() + steen.getHoogte() - 3 //bovenkant bal met onderkant steen
                                && bal.getY() - bal.getHuidigeStraal() <= steen.getY() + steen.getHoogte() + 3
                                && bal.getX() >= steen.getX()
                                && bal.getX() <= steen.getX() + steen.getBreedte()) {
                            steen.setGeraakt();
                            if (bal.getVy() < 0 && !bal.isGodMode()) {
                                bal.setVy();
                            }
                        } else if (bal.getX() + bal.getHuidigeStraal() >= steen.getX() - 3 //rechterkant bal met linkerkant steen
                                && bal.getX() + bal.getHuidigeStraal() <= steen.getX() + 3
                                && bal.getY() >= steen.getY()
                                && bal.getY() <= steen.getY() + steen.getHoogte()) {
                            steen.setGeraakt();
                            if (bal.getVx() > 0 && !bal.isGodMode()) {
                                bal.setVx();
                            }
                        } else if (bal.getX() - bal.getHuidigeStraal() >= steen.getX() + steen.getBreedte() - 3 //linkerkant bal met rechterkant steen
                                && bal.getX() - bal.getHuidigeStraal() <= steen.getX() + steen.getBreedte() + 3
                                && bal.getY() >= steen.getY()
                                && bal.getY() <= steen.getY() + steen.getHoogte()) {
                            if (bal.getVx() < 0 && !bal.isGodMode()) {
                                steen.setGeraakt();
                                bal.setVx();
                            }
                        } else if (Math.sqrt(Math.pow(bal.getX() - (steen.getX() + steen.getBreedte()), 2) + Math.pow(bal.getY() - (steen.getY() + steen.getHoogte()), 2)) < bal.getHuidigeStraal() && !bal.isGodMode()) {  //linksboven bal
                            steen.setGeraakt();
                            if (bal.getVx() < 0 && bal.getVy() < 0) {
                                bal.setVx();
                                bal.setVy();
                            }
                        } else if (Math.sqrt(Math.pow(bal.getX() - steen.getX(), 2) + Math.pow(bal.getY() - steen.getY(), 2)) < bal.getHuidigeStraal() && !bal.isGodMode()) {  //rechtsonder bal
                            steen.setGeraakt();
                            if (bal.getVx() > 0 && bal.getVy() > 0) {
                                bal.setVx();
                                bal.setVy();
                            }
                        } else if (Math.sqrt(Math.pow(bal.getX() - steen.getX(), 2) + Math.pow(bal.getY() - (steen.getY() + steen.getHoogte()), 2)) < bal.getHuidigeStraal() && !bal.isGodMode()) {  //rechtsboven bal
                            steen.setGeraakt();
                            if (bal.getVx() > 0 && bal.getVy() < 0) {
                                bal.setVx();
                                bal.setVy();
                            }
                        } else if (Math.sqrt(Math.pow(bal.getX() - (steen.getX() + steen.getBreedte()), 2) + Math.pow(bal.getY() - steen.getY(), 2)) < bal.getHuidigeStraal() && !bal.isGodMode()) {  //linksonder bal
                            steen.setGeraakt();
                            if (bal.getVx() < 0 && bal.getVy() > 0) {
                                bal.setVx();
                                bal.setVy();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * de methode registreert de botsing tussen de bal en de peddel
     */
    private void botsingBalPeddel() {
        for (Bal bal : ballen) {
            if (bal.getY() + bal.getHuidigeStraal() >= peddel.getY() - 3
                    && bal.getY() + bal.getHuidigeStraal() <= peddel.getY() + 3
                    && bal.getX() >= peddel.getX()
                    && bal.getX() <= peddel.getX() + peddel.getHuidigeBreedte()) {

                if (bal.getVy() > 0) {
                    double snelheidBalY;
                    double middenPeddelX = peddel.getX() + peddel.getHuidigeBreedte() / 2;
                    if ((bal.getX() - middenPeddelX) > 0) {
                        if (bal.getVx() < 0) {
                            bal.setVx();
                        }
                        double percentTotMiddenRechts = 1 - (bal.getX() - middenPeddelX) / (peddel.getHuidigeBreedte() / 2);
                        double snelheid = Math.sqrt(Math.pow(bal.getSnelheid(), 2) / 2);
                        snelheidBalY = -((percentTotMiddenRechts * (bal.getSnelheid() - snelheid)) + snelheid);    //schaal van 0->1 naar gewenste schaal omzetten
                    } else {
                        if (bal.getVx() > 0) {
                            bal.setVx();
                        }
                        double percentTotMiddenPeddelLinks = 1 - (middenPeddelX - bal.getX()) / (peddel.getHuidigeBreedte() / 2);
                        double snelheid = Math.sqrt(Math.pow(bal.getSnelheid(), 2) / 2);
                        snelheidBalY = -((percentTotMiddenPeddelLinks * (bal.getSnelheid() - snelheid)) + snelheid);
                    }
                    bal.setVy(snelheidBalY);
                }
            }
        }
    }

    /**
     * deze methode registreert de botsing tussen de bal en de powerup
     */
    private void botsingBalPowerUp() {
        for (Bal bal : ballen) {
            if (Math.sqrt(Math.pow(powerUp.getX() - bal.getX(), 2) + Math.pow(powerUp.getY() - bal.getY(), 2)) < bal.getHuidigeStraal() + powerUp.getStraal() && !powerUp.isGeraakt()) {
                toonLabel = true;
                powerUp.setGeraakt(true);
                balStatus = true;
                if (powerUp.getKleurBal() == Kleuren.ROZE) {
                    peddel.setHuidigeBreedte(peddel.getBreedte() * peddel.getMultiplier());
                } else if (powerUp.getKleurBal() == Kleuren.PAARS) {
                    for (Bal b : ballen) {
                        b.setGodMode(true);
                    }
                } else if (powerUp.getKleurBal() == Kleuren.ZWART) {
                    peddel.setHuidigeBreedte(peddel.getBreedte() / peddel.getMultiplier());
                } else if (powerUp.getKleurBal() == Kleuren.GRIJS) {
                    for (Bal b : ballen) {
                        b.setHuidigeStraal(b.getStraal() * b.getMutiplier());
                    }
                }
                timerPeddel.setTijdsduurPowerUp();

            }
        }
    }

    /**
     * deze methode zegt wanneer de powerup gedaan is
     */
    private void PowerUpVoorbij() {
        if (timerPeddel.getTijdsduurPowerUp() > maxTijdsduurPowerUp && powerUp.isGeraakt() && /*toonLabel*/ balStatus) {
            toonLabel = false;
            balStatus = false;
            balStatusNa = true;
            if (powerUp.getKleurBal() == Kleuren.ROZE || powerUp.getKleurBal() == Kleuren.ZWART) {
                peddel.setHuidigeBreedte(peddel.getBreedte());
            } else if (powerUp.getKleurBal() == Kleuren.PAARS) {
                for (Bal bal : ballen) {
                    bal.setGodMode(false);
                }
            } else if (powerUp.getKleurBal() == Kleuren.GRIJS) {
                for (Bal bal : ballen) {
                    bal.setHuidigeStraal(bal.getStraal());
                }
            }
            timerPeddel.setTijdsintervalPowerUp();
            timerPeddel.setTijdsduurPowerUp();
        }
    }

    /**
     * deze methode print de label voor de tijdsduur van de powerup
     *
     * @return tekst in label voor tijd
     */
    public String labelPowerUp() {
        if (toonLabel) {
            return maxTijdsduurPowerUp - timerPeddel.getTijdsduurPowerUp() + "";
        }
        return maxTijdsduurPowerUp + "";
    }

    /**
     * deze methode toont een powerup na een bepaalde tijd
     */
    private void toonPowerUp() {
        if (timerPeddel.getTijdsintervalPowerUp() > maxTijdsduurTussenPowerUp && powerUp.isGeraakt() && balStatusNa) {
            powerUp = new PowerUp(20, paneel);
            timerPeddel.setTijdsintervalPowerUp();
            powerUp.setGeraakt(false);
            balStatusNa = false;
        }
    }

    /**
     * geeft de peddel terug
     *
     * @return peddel de peddel
     */
    public Peddel getPeddel() {
        return peddel;
    }

    /**
     * geeft de powerup terug
     *
     * @return puwerUp de powerUp
     */
    public PowerUp getPowerUp() {
        return powerUp;
    }
}
