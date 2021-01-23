/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import model.Bal;
import model.Ballen;
import model.Paneel;
import model.Peddel;

/**
 *
 * @author lowie
 */
public class BallenView extends Region {

    private final Ballen ballen;
    private final Peddel peddel;
    private final Paneel paneel;
    private boolean statusPurple;

    public BallenView(Ballen ballen, Peddel peddel, Paneel paneel) {
        this.ballen = ballen;
        this.peddel = peddel;
        this.paneel = paneel;
        maakBallen();
        update();
    }

    public final void maakBallen() {
        getChildren().clear();
        ArrayList<Bal> b = ballen.getBallen();
        for (int i = 0; i < ballen.getAantalBallen(); i++) {
            b.get(i).setX(b.get(i).getX() + i * 20);
            BalView bv = new BalView(b.get(i));
            bv.setTranslateX(b.get(i).getX());
            bv.setTranslateY(b.get(i).getY());
            getChildren().add(bv);
        }
    }
    
    public void update(){
        getChildren().clear();
        for (int i = 0; i < ballen.getAantalBallen(); i++) {
            BalView bv = new BalView(ballen.getBallen().get(i));
            bv.setTranslateX(ballen.getBallen().get(i).getX());
            bv.setTranslateY(ballen.getBallen().get(i).getY());
            getChildren().add(bv);
        }
        
    }
    
    public void reset(){
        ArrayList<Bal> ballenLijst = ballen.getBallen();
        for (int i = 0; i<ballen.getAantalBallen(); i++) {
            if(i!=0){
                getChildren().remove(i);
            }
        }
    }

    private void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("game over");
        alert.setContentText("je bent dood...\ndruk op reset om opnieuw te beginnen");
        alert.showAndWait();
    }

    public int getAantalBallen() {
        return getChildren().size();
    }

    public boolean getStatusPurple() {
        return statusPurple;
    }

    public void setStatusPurple(boolean statusPurple) {
        this.statusPurple = statusPurple;
    }
}
