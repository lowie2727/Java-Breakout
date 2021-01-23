/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Pane;
import model.Paneel;
import model.Spel;

/**
 *
 * @author lowie
 */
public class SpelView {

    private PeddelView peddelView;
    private PaneelView paneelView;
    private StenenView stenenView;
    private BallenView ballenView;
    private PowerUpView powerUpView;

    private Paneel paneelModel;
    private final Pane paneel;
    private final Spel spel;

    public SpelView(Spel spel, Pane paneel, Paneel paneelModel) {
        this.paneelModel = paneelModel;
        this.spel = spel;
        ballenView = new BallenView(spel.getBallen());
        paneelView = new PaneelView(paneelModel);
        peddelView = new PeddelView(spel.getPeddel());
        stenenView = new StenenView(spel.getStenen());
        powerUpView = new PowerUpView(spel.getPowerUp());
        this.paneel = paneel;

        paneel.getChildren().addAll(ballenView, paneelView, peddelView, stenenView, powerUpView);

    }

    public void update() {
        paneel.getChildren().clear();
        ballenView.update();
        peddelView.update();
        stenenView.update();
        powerUpView = new PowerUpView(spel.getPowerUp());
        powerUpView.update();
        paneel.getChildren().addAll(ballenView, paneelView, peddelView, stenenView, powerUpView);
    }

    public void reset() {
        ballenView = new BallenView(spel.getBallen());
        paneelView = new PaneelView(paneelModel);
        peddelView = new PeddelView(spel.getPeddel());
        stenenView = new StenenView(spel.getStenen());
        powerUpView = new PowerUpView(spel.getPowerUp());
        paneel.getChildren().clear();
        paneel.getChildren().addAll(ballenView, paneelView, peddelView, stenenView, powerUpView);

    }
}
