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

    private final PeddelView peddelView;
    private final PaneelView paneelView;
    private final StenenView stenenView;
    private final BallenView ballenView;
    private final PowerUpView powerUpView;

    public SpelView(Spel spel, Pane paneel, Paneel paneelModel) {

        ballenView = new BallenView(spel.getBallen(), spel.getPeddel(), paneelModel);
        paneelView = new PaneelView(paneelModel);
        peddelView = new PeddelView(spel.getPeddel());
        stenenView = new StenenView(spel.getStenen());
        powerUpView = new PowerUpView(spel.getPowerUp());

        paneel.getChildren().addAll(ballenView, paneelView, peddelView, stenenView, powerUpView);

    }

    public void update() {
        ballenView.update();
        peddelView.update();
        stenenView.update();
        powerUpView.update();
    }

    public void reset() {
        ballenView.reset();

    }

}
