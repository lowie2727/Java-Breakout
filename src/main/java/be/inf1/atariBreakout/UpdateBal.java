/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.inf1.atariBreakout;

import javafx.application.Platform;
import model.Bal;

import java.util.TimerTask;

/**
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class UpdateBal extends TimerTask {

    private final Bal balModel;
    private final ModelController controller;

    public UpdateBal(Bal balModel, ModelController controller) {
        this.balModel = balModel;
        this.controller = controller;
    }

    @Override
    public void run() {
        balModel.tick();
        Platform.runLater(controller::update);
    }
}
