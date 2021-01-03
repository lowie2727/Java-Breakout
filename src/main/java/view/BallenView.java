/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.scene.layout.Region;
import model.Bal;
import model.Ballen;

/**
 *
 * @author lowie
 */
public class BallenView extends Region{
    private Ballen ballen;
    
    public BallenView(Ballen ballen){
        this.ballen = ballen;
        maakBallen();
        //update();
        
    }

    public void maakBallen() {
        getChildren().clear();
        ArrayList<Bal> b = ballen.getBallen();
        for(int i=0; i<ballen.getAantalExtraBallen();i++){
            BalView bv = new BalView(b.get(i));
            bv.setTranslateX(10);
            bv.setTranslateY(10);
        }
    }

    public void update() {
        
    }
}
