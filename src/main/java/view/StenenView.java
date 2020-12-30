/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import model.Steen;
import model.Stenen;

/**
 *
 * @author lowie
 */
public class StenenView extends Region{
    private SteenView steenView;
    private Stenen stenen;
    
    public StenenView(Stenen stenen){
        this.stenen = stenen;
        createStenen();
        //update();
    }

    private void createStenen() {
        int n = 0;
        int m = 0;
        Steen s[][] = stenen.getStenen();
        for(int j = 0;j<stenen.getRijen();j++){
            for(int i = 0;i<stenen.getKolommen();i++){
                double breedte = s[j][i].getBreedte() + 10;
                double hoogte = s[j][i].getHoogte() + 10;
                SteenView sv = new SteenView(s[j][i]);
                sv.setTranslateX(breedte *n);
                sv.setTranslateY(hoogte*m);
                n++;
                if(n == stenen.getKolommen()){
                    n = 0;
                }
                getChildren().add(sv);
            }
            m++;
        }
    }

    private void update() {
        
    }
    
}
