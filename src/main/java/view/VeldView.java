/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import be.inf1.mavenproject2.TimerPeddel;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Paneel;
import model.Peddel;
import model.PowerUp;

/**
 *
 * @author lowie
 */
public class VeldView {

    private final Peddel peddelModel;
    private PowerUp powerUpModel;
    private final Paneel paneelModel;

    private final StenenView stenenView;
    private final PeddelView peddelView;
    private PowerUpView powerUpView;
    private final BallenView ballenView;

    private final Pane paneel;

    private int n;
    private final int intervalPowerUp;
    private final int intervalPowerUpDuration;

    private final TimerPeddel timerPeddel;
    private boolean statusPink;
    private boolean statusBlack;

    private final double peddelMultiplier;

    public VeldView(StenenView stenenView, Peddel peddelModel, BallenView ballenview, PeddelView peddelView, PowerUpView powerUpView,
            PowerUp powerUpModel, TimerPeddel timerPeddel, Paneel paneelModel, Pane paneel) {
        this.peddelModel = peddelModel;
        this.powerUpModel = powerUpModel;
        this.paneelModel = paneelModel;

        this.ballenView = ballenview;
        this.stenenView = stenenView;
        this.peddelView = peddelView;
        this.powerUpView = powerUpView;

        this.paneel = paneel;
        this.timerPeddel = timerPeddel;

        intervalPowerUp = 1;
        intervalPowerUpDuration = 5;

        peddelMultiplier = 1.5;
    }

    public void update() {
        botsingBal();
        peddelView.update();
        ballenView.update();
        stenenView.update();
        powerUpView.update();
        toonPowerUp();
        tijdPeddel();
    }

    private void botsingBal() {
        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();
        ObservableList<Node> ballen = ballenView.getChildrenUnmodifiable();

        for (Node b : ballen) {
            Point2D middelPuntBal = b.localToParent(Point2D.ZERO);
            Bounds boundsBal = b.localToParent(b.getBoundsInLocal());
            double straalBal = boundsBal.getWidth() / 2;

            for (Node s : stenen) {
                Bounds steenBounds = getBoundSteen(s);
                if (middelPuntBal.getY() + straalBal >= steenBounds.getMinY() - 3 //onderkant bal met bovenkant steen
                        && middelPuntBal.getY() + straalBal <= steenBounds.getMinY() + 3
                        && middelPuntBal.getX() >= steenBounds.getMinX()
                        && middelPuntBal.getX() <= steenBounds.getMinX() + steenBounds.getWidth()) {
                    b.setId("1");
                    s.setId("geraakt");
                } else if (middelPuntBal.getY() - straalBal >= steenBounds.getMaxY() - 3 //bovenkant bal met onderkant steen
                        && middelPuntBal.getY() - straalBal <= steenBounds.getMaxY() + 3
                        && middelPuntBal.getX() >= steenBounds.getMinX()
                        && middelPuntBal.getX() <= steenBounds.getMinX() + steenBounds.getWidth()) {
                    b.setId("2");
                    s.setId("geraakt");
                } else if (middelPuntBal.getX() + straalBal >= steenBounds.getMinX() - 3 //rechterkant bal met linkerkant steen
                        && middelPuntBal.getX() + straalBal <= steenBounds.getMinX() + 3
                        && middelPuntBal.getY() >= steenBounds.getMinY()
                        && middelPuntBal.getY() <= steenBounds.getMinY() + steenBounds.getHeight()) {
                    b.setId("3");
                    s.setId("geraakt");
                } else if (middelPuntBal.getX() - straalBal >= steenBounds.getMaxX() - 3 //linkerkant bal met rechterkant steen
                        && middelPuntBal.getX() - straalBal <= steenBounds.getMaxX() + 3
                        && middelPuntBal.getY() >= steenBounds.getMinY()
                        && middelPuntBal.getY() <= steenBounds.getMinY() + steenBounds.getHeight()) {
                    b.setId("4");
                    s.setId("geraakt");
                } else if (Math.sqrt(Math.pow(middelPuntBal.getX() - steenBounds.getMinX(), 2) + Math.pow(middelPuntBal.getY() - steenBounds.getMinY(), 2)) < straalBal) { //linksboven bal
                    b.setId("5");
                    s.setId("geraakt");
                } else if (Math.sqrt(Math.pow(middelPuntBal.getX() - steenBounds.getMaxX(), 2) + Math.pow(middelPuntBal.getY() - steenBounds.getMaxY(), 2)) < straalBal) { //rechtsonder bal
                    b.setId("6");
                    s.setId("geraakt");
                } else if (Math.sqrt(Math.pow(middelPuntBal.getX() - steenBounds.getMinX(), 2) + Math.pow(middelPuntBal.getY() + steenBounds.getHeight() - steenBounds.getMinY(), 2)) < straalBal) { //rechtsboven bal
                    b.setId("7");
                    s.setId("geraakt");
                } else if (Math.sqrt(Math.pow(middelPuntBal.getX() - steenBounds.getMinX(), 2) + Math.pow(middelPuntBal.getY() - steenBounds.getHeight() - steenBounds.getMinY(), 2)) < straalBal) { //linksonder bal
                    b.setId("8");
                    s.setId("geraakt");
                }
            }
            if (middelPuntBal.getY() + straalBal >= peddelModel.getY() - 3
                    && middelPuntBal.getY() + straalBal <= peddelModel.getY() + 3
                    && middelPuntBal.getX() > peddelModel.getX()
                    && middelPuntBal.getX() < peddelModel.getX() + peddelModel.getHuidigeBreedte()) {
                b.setId("10");
            }

            if (Math.sqrt(Math.pow(powerUpView.getRandX() - middelPuntBal.getX(), 2) + Math.pow(powerUpView.getRandY() - middelPuntBal.getY(), 2)) < straalBal + powerUpModel.getStraal() && !powerUpView.getChildrenUnmodifiable().isEmpty()) {
                if (powerUpView.getKleurC().equals(Color.PINK)) {
                    peddelModel.setHuidigeBreedte(peddelModel.getMultiplier() * peddelModel.getBreedte());
                    peddelView.createPeddel();
                    timerPeddel.setTijdPeddel();
                    statusPink = true;
                } else if (powerUpView.getKleurC().equals(Color.PURPLE)) {
                    ballenView.statusPurple = true;
                    timerPeddel.setTijdPeddel();
                } else if (powerUpView.getKleurC().equals(Color.BLACK)) {
                    peddelModel.setHuidigeBreedte((peddelModel.getBreedte() / peddelModel.getMultiplier()));
                    peddelView.createPeddel();
                    timerPeddel.setTijdPeddel();
                    statusBlack = true;
                } else if (powerUpView.getKleurC().equals(Color.GRAY)) {
                    timerPeddel.setTijdPeddel();
                }
                powerUpView.getChildrenUnmodifiable().get(0).setId("1");
                timerPeddel.setTijdPowerUp();
            }
        }
    }

    private Bounds getBoundSteen(Node s) {
        return s.localToParent(s.getBoundsInLocal());
    }

    public void reset() {
        ballenView.reset();
        peddelModel.reset();
        peddelView.update();
        powerUpView.reset();
        stenenView.maakStenen();
    }

    private void toonPowerUp() {
        if (powerUpView.getChildrenUnmodifiable().isEmpty() && timerPeddel.getTijdPowerUp() > intervalPowerUp) {
            this.powerUpModel = new PowerUp(powerUpModel.getStraal());
            timerPeddel.setTijdPowerUp();
            powerUpView = new PowerUpView(powerUpModel, paneelModel);
            paneel.getChildren().add(powerUpView);
        }
    }

    private void tijdPeddel() {
        if (timerPeddel.getTijdPeddel() > intervalPowerUpDuration && statusPink) {
            peddelModel.setHuidigeBreedte(peddelModel.getBreedte());
            peddelView.createPeddel();
            statusPink = false;
            timerPeddel.setTijdPowerUp();
        } else if (timerPeddel.getTijdPeddel() > intervalPowerUpDuration && ballenView.statusPurple) {
            ballenView.statusPurple = false;
            timerPeddel.setTijdPowerUp();
        } else if (timerPeddel.getTijdPeddel() > intervalPowerUpDuration && statusBlack) {
            peddelModel.setHuidigeBreedte(peddelModel.getBreedte());
            peddelView.createPeddel();
            statusBlack = false;
            timerPeddel.setTijdPowerUp();
        }
    }

    /**
     * @return the peddelView
     */
    public PeddelView getPeddelView() {
        return peddelView;
    }

    public String timerPeddel() {
        if (statusPink || ballenView.statusPurple || statusBlack) {
            return (intervalPowerUpDuration - timerPeddel.getTijdPeddel() + "");
        }
        return intervalPowerUpDuration + "";
    }
}
