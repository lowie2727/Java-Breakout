/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.inf1.mavenproject2;

import java.util.TimerTask;
import javafx.application.Platform;
import model.Bal;

/**
 *
 * @author lowie
 */
public class UpdateBal extends TimerTask {

    private Bal bal;
    private ModelController controller;

    public UpdateBal(Bal balModel, ModelController controller) {
        this.bal = balModel;
        this.controller = controller;
    }

    @Override
    public void run() {
        bal.tick();
        Platform.runLater(controller::update);
    }

}
