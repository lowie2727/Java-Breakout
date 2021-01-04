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

    public Stenen(Paneel paneel, Steen steen) {
        this.paneel = paneel;
        this.steen = steen;
        rijen = 16;
        kolommen = 9000;
        setMaxKolommen(kolommen);
        offsetBreedte = 5;
        offsetHoogte = 5;
        setOffsetBreedtePaneel(kolommen);
        offsetHoogtePaneel = 50;
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

    /**
     * @return
     */
    public Steen[][] getStenen() {
        return stenen;
    }

    /**
     * @return
     */
    public int getRijen() {
        return rijen;
    }

    /**
     * @return
     */
    public int getKolommen() {
        return kolommen;
    }

    /**
     * @return
     */
    public double getOffsetBreedte() {
        return offsetBreedte;
    }

    /**
     * @return
     */
    public double getOffsetHoogte() {
        return offsetHoogte;
    }

    private void setMaxKolommen(int kolommen) {
        if (kolommen * (steen.getBreedte() + offsetBreedte) >= paneel.getBreedte()) {
            this.kolommen = 15;
        }
    }

    private void setOffsetBreedtePaneel(int kolommen) {
        offsetBreedtePaneel = (paneel.getBreedte() - kolommen * (steen.getBreedte() + offsetBreedte) - offsetBreedte) / 2;
    }

    /**
     * @return
     */
    public double getOffsetHoogtePaneel() {
        return offsetHoogtePaneel;
    }

    /**
     * @return
     */
    public double getOffsetBreedtePaneel() {
        return offsetBreedtePaneel;
    }
}
