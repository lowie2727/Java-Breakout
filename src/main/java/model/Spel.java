package model;

import be.inf1.mavenproject2.StartPaginaController;
import be.inf1.mavenproject2.TimerPeddel;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 * klasse Spel
 *
 * Lowie Van Vyve: 65%
 * Arnaud Paquet: 10%
 * Jonas Vandenborne: 25%
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Spel {

    private final Ballen ballen;
    private final Stenen stenen;
    private PowerUp powerUp;
    private final Peddel peddel;
    private final Paneel paneel;

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
     * @param paneel vraagt het model van Paneel op
     * @param timerPeddel vraagt de timerpeddel op
     */
    public Spel(Paneel paneel, TimerPeddel timerPeddel) {
        this.paneel = paneel;
        ballen = new Ballen(paneel, 1);
        peddel = new Peddel(10, paneel);
        stenen = new Stenen(paneel, 500);
        powerUp = new PowerUp(20, paneel);

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
        notificatie();
        stenen.gewonnen();
    }

    /**
     * de methode reset de klasse Spel
     */
    public void reset() {
        ballen.reset();
        peddel.reset();
        stenen.reset();
        toonLabel = false;
        timerPeddel.setTijdsintervalPowerUp();
        timerPeddel.setTijdsduurPowerUp();
        powerUp = new PowerUp(20, paneel);
    }
    
    /**
     * deze methode zorgt voor de notificaties zoals gewonnen en verloren
     */
    public void notificatie(){
        if(stenen.isGewonnen()){
            gewonnen();
            reset();
        }
    }
    
    
    /**
     * deze methode laat een bericht zien als je dood bent
     */
    private void gewonnen() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("game over");
        alert.setContentText("je bent dood...druk op reset om opnieuw te beginnen");
        alert.show();
    }

    /**
     * deze methode regiseert de botsingen tussen de bal en de stenen
     */
    private void botsingBalStenen() {
        for (Bal bal : ballen.getBallen()) {
            for (int j = 0; j < stenen.getRijen(); j++) {
                for (int i = 0; i < stenen.getKolommen(); i++) {
                    Steen steen = stenen.getSteen(j, i);
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
        for (Bal bal : ballen.getBallen()) {
            if (bal.getY() + bal.getHuidigeStraal() >= peddel.getY() - 3
                    && bal.getY() + bal.getHuidigeStraal() <= peddel.getY() + 3
                    && bal.getX() >= peddel.getX()
                    && bal.getX() <= peddel.getX() + peddel.getHuidigeBreedte()) {

                if (bal.getVy() > 0) {
                    double snelheidBalY;
                    double middenPeddelX = peddel.getX() + peddel.getBreedte() / 2;
                    if ((bal.getX() - middenPeddelX) > 0) {
                        if (bal.getVx() < 0) {
                            bal.setVx();
                        }
                        double percentTotMiddenRechts = 1 - (bal.getX() - middenPeddelX) / (peddel.getBreedte() / 2);
                        double snelheid = Math.sqrt(Math.pow(bal.getSnelheid(), 2) / 2);
                        snelheidBalY = -((percentTotMiddenRechts * (bal.getSnelheid() - snelheid)) + snelheid);    //schaal van 0->1 naar gewenste schaal omzetten
                    } else {
                        if (bal.getVx() > 0) {
                            bal.setVx();
                        }
                        double percentTotMiddenPeddelLinks = 1 - (middenPeddelX - bal.getX()) / (peddel.getBreedte() / 2);
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
        ArrayList<Bal> ballenLijst = ballen.getBallen();
        for (Bal bal : ballenLijst) {
            if (Math.sqrt(Math.pow(powerUp.getX() - bal.getX(), 2) + Math.pow(powerUp.getY() - bal.getY(), 2)) < bal.getHuidigeStraal() + powerUp.getStraal() && !powerUp.isGeraakt()) {
                toonLabel = true;
                powerUp.setGeraakt(true);
                balStatus = true;
                if (powerUp.getKleurBal() == Kleuren.ROZE) {
                    peddel.setHuidigeBreedte(peddel.getBreedte() * peddel.getMultiplier());
                } else if (powerUp.getKleurBal() == Kleuren.PAARS) {
                    for (Bal b : ballenLijst) {
                        b.setGodMode(true);
                    }
                } else if (powerUp.getKleurBal() == Kleuren.ZWART) {
                    peddel.setHuidigeBreedte(peddel.getBreedte() / peddel.getMultiplier());
                } else if (powerUp.getKleurBal() == Kleuren.GRIJS) {
                    for (Bal b : ballenLijst) {
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
                for (Bal bal : ballen.getBallen()) {
                    bal.setGodMode(false);
                }
            } else if (powerUp.getKleurBal() == Kleuren.GRIJS) {
                for (Bal bal : ballen.getBallen()) {
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
     * geeft de ballen terug
     *
     * @return ballen de ballen
     */
    public Ballen getBallen() {
        return ballen;
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
     * geeft de stenen terug
     *
     * @return stenen de stenen
     */
    public Stenen getStenen() {
        return stenen;
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
