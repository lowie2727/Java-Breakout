/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lowie
 */
public class Stenen {

    private Steen stenen[][];
    private int rijen;
    private int kolommen;
    private final double offsetBreedte;
    private final double offsetHoogte;
    private double offsetBreedtePaneel;
    private final double offsetHoogtePaneel;
    private Paneel paneel;
    private Steen steen;

    public Stenen(Paneel paneel,Steen steen) {
        this.paneel = paneel;
        this.steen = steen;
        rijen = 2;
        kolommen = 6;
        setMaxKolommen(kolommen);
        offsetBreedte = 5;
        offsetHoogte = 5;
        setOffsetBreedtePaneel(kolommen);
        offsetHoogtePaneel = 60;
        createMatrix();
    }

    private void createMatrix() {
        stenen = new Steen[rijen][kolommen];
        for (int j = 0; j < rijen; j++) {
            for (int i = 0; i < kolommen; i++) {
                stenen[j][i] = new Steen();
            }
        }
    }

    public Steen[][] getStenen() {
        return stenen;
    }

    public int getRijen() {
        return rijen;
    }

    public int getKolommen() {
        return kolommen;
    }
    
    public double getOffsetBreedte(){
        return offsetBreedte;
    }
    
    public double getOffsetHoogte(){
        return offsetHoogte;
    }
    
    private void setMaxKolommen(int kolommen){
        if(kolommen*(steen.getBreedte()+offsetBreedte)>=paneel.getBreedte()){
            this.kolommen = 15;
            
        }
    }
    
    private void setOffsetBreedtePaneel(int kolommen){
        offsetBreedtePaneel = (paneel.getBreedte() - kolommen*(steen.getBreedte()+offsetBreedte)-offsetBreedte)/2;
        
    }
    
    public double getOffsetHoogtePaneel(){
        return offsetHoogtePaneel;
    }
    
    public double getOffsetBreedtePaneel(){
        return offsetBreedtePaneel;
    }

}
