/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import be.inf1.mavenproject2.StartPaginaController;
import be.inf1.mavenproject2.TimerPeddel;
import java.util.ArrayList;
import model.Bal;

/**
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
    private final int maxTijdsduurPowerUp;
    private final int maxTijdsduurTussenPowerUp;
    private boolean toonLabel;
    private boolean balStatus;
    private boolean balStatusNa;

    /**
     *
     * @param paneel
     * @param timerPeddel
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
     *
     */
    public void update() {
        botsingBalStenen();
        botsingBalPeddel();
        botsingBalPowerUp();
        PowerUpVoorbij();
        toonPowerUp();
    }

    /**
     *
     */
    public void reset() {
        ballen.reset();
        peddel.reset();
        stenen.reset();
        powerUp = new PowerUp(20, paneel);
    }

    /**
     *
     */
    public void botsingBalStenen() {
        for (Bal bal : ballen.getBallen()) {
            for (int j = 0; j < stenen.getRijen(); j++) {
                for (int i = 0; i < stenen.getKolommen(); i++) {
                    Steen steen = stenen.getSteen(j, i);
                    if (!steen.isGeraakt()) {
                        if (bal.getY() + bal.getStraal() >= steen.getY() - 3 //onderkant bal met bovenkant steen
                                && bal.getY() + bal.getStraal() <= steen.getY() + 3
                                && bal.getX() >= steen.getX()
                                && bal.getX() <= steen.getX() + steen.getBreedte()) {
                            steen.setGeraakt();
                            if (bal.getVy() > 0 && !bal.isGodMode()) {
                                bal.setVy();
                            }
                        } else if (bal.getY() - bal.getStraal() >= steen.getY() + steen.getHoogte() - 3 //bovenkant bal met onderkant steen
                                && bal.getY() - bal.getStraal() <= steen.getY() + steen.getHoogte() + 3
                                && bal.getX() >= steen.getX()
                                && bal.getX() <= steen.getX() + steen.getBreedte()) {
                            steen.setGeraakt();
                            if (bal.getVy() < 0 && !bal.isGodMode()) {
                                bal.setVy();
                            }
                        } else if (bal.getX() + bal.getStraal() >= steen.getX() - 3 //rechterkant bal met linkerkant steen
                                && bal.getX() + bal.getStraal() <= steen.getX() + 3
                                && bal.getY() >= steen.getY()
                                && bal.getY() <= steen.getY() + steen.getHoogte()) {
                            steen.setGeraakt();
                            if (bal.getVx() > 0 && !bal.isGodMode()) {
                                bal.setVx();
                            }
                        } else if (bal.getX() - bal.getStraal() >= steen.getX() + steen.getBreedte() - 3 //linkerkant bal met rechterkant steen
                                && bal.getX() - bal.getStraal() <= steen.getX() + steen.getBreedte() + 3
                                && bal.getY() >= steen.getY()
                                && bal.getY() <= steen.getY() + steen.getHoogte()) {
                            if (bal.getVx() < 0 && !bal.isGodMode()) {
                                steen.setGeraakt();
                                bal.setVx();
                            }
                        } else if (Math.sqrt(Math.pow(bal.getX() - (steen.getX() + steen.getBreedte()), 2) + Math.pow(bal.getY() - (steen.getY() + steen.getHoogte()), 2)) < bal.getStraal() && !bal.isGodMode()) {  //linksboven bal
                            steen.setGeraakt();
                            if (bal.getVx() < 0 && bal.getVy() < 0) {
                                bal.setVx();
                                bal.setVy();
                            }
                        } else if (Math.sqrt(Math.pow(bal.getX() - steen.getX(), 2) + Math.pow(bal.getY() - steen.getY(), 2)) < bal.getStraal() && !bal.isGodMode()) {  //rechtsonder bal
                            steen.setGeraakt();
                            if (bal.getVx() > 0 && bal.getVy() > 0) {
                                bal.setVx();
                                bal.setVy();
                            }
                        } else if (Math.sqrt(Math.pow(bal.getX() - steen.getX(), 2) + Math.pow(bal.getY() - (steen.getY() + steen.getHoogte()), 2)) < bal.getStraal() && !bal.isGodMode()) {  //rechtsboven bal
                            steen.setGeraakt();
                            if (bal.getVx() > 0 && bal.getVy() < 0) {
                                bal.setVx();
                                bal.setVy();
                            }
                        } else if (Math.sqrt(Math.pow(bal.getX() - (steen.getX() + steen.getBreedte()), 2) + Math.pow(bal.getY() - steen.getY(), 2)) < bal.getStraal() && !bal.isGodMode()) {  //linksonder bal
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
     *
     */
    public void botsingBalPeddel() {
        for (Bal bal : ballen.getBallen()) {
            if (bal.getY() + bal.getStraal() >= peddel.getY() - 3
                    && bal.getY() + bal.getStraal() <= peddel.getY() + 3
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
     *
     */
    public void botsingBalPowerUp() {
        ArrayList<Bal> ballenLijst = ballen.getBallen();
        for (Bal bal : ballenLijst) {
            if (Math.sqrt(Math.pow(powerUp.getX() - bal.getX(), 2) + Math.pow(powerUp.getY() - bal.getY(), 2)) < bal.getStraal() + powerUp.getStraal() && !powerUp.isGeraakt()) {
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

    private void PowerUpVoorbij() {
        if (timerPeddel.getTijdsduurPowerUp() > maxTijdsduurPowerUp && powerUp.isGeraakt() && balStatus) {
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
     *
     * @return
     */
    public String labelPowerUp() {
        if (toonLabel) {
            return maxTijdsduurPowerUp - timerPeddel.getTijdsduurPowerUp() + "";
        }
        return maxTijdsduurPowerUp + "";
    }

    private void toonPowerUp() {
        if (timerPeddel.getTijdsintervalPowerUp() > maxTijdsduurTussenPowerUp && powerUp.isGeraakt() && balStatusNa) {
            powerUp = new PowerUp(20, paneel);
            timerPeddel.setTijdsintervalPowerUp();
            powerUp.setGeraakt(false);
            balStatusNa = false;
        }
    }

    /**
     *
     * @return
     */
    public Ballen getBallen() {
        return ballen;
    }

    /**
     *
     * @return
     */
    public Peddel getPeddel() {
        return peddel;
    }

    /**
     *
     * @return
     */
    public Stenen getStenen() {
        return stenen;
    }

    /**
     *
     * @return
     */
    public PowerUp getPowerUp() {
        return powerUp;
    }
}
