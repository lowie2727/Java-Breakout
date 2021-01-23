/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import be.inf1.mavenproject2.StartPaginaController;
import java.util.Arrays;

/**
 *
 * @author lowie
 */
public class Stenen {

    private Steen stenen[][];
    private final int rijen;
    private int kolommen;
    private final double offsetBreedte;
    private final double offsetHoogte;
    private double offsetBreedtePaneel;
    private final double offsetHoogtePaneel;
    private final Paneel paneel;
    private Steen steen;

    public Stenen(Paneel paneel, int kolommen) {
        this.paneel = paneel;
        this.rijen = StartPaginaController.getAantalRijen();
        this.kolommen = kolommen;
        offsetBreedte = 5;
        offsetHoogte = 5;
        setMaxKolommen(this.kolommen);
        setOffsetBreedtePaneel(this.kolommen);
        offsetHoogtePaneel = 50;
        createMatrix();
        steen = getSteen(0, 0);
    }

    private void createMatrix() {
        stenen = new Steen[rijen][kolommen];
        for (int j = 0; j < rijen; j++) {
            for (int i = 0; i < kolommen; i++) {
                double breedte = steen.getBreedte() + offsetBreedte;
                double hoogte = steen.getHoogte() + offsetHoogte;
                stenen[j][i] = new Steen(steen.getBreedte(), steen.getHoogte(),
                        breedte * i + offsetBreedtePaneel, hoogte * j + offsetHoogtePaneel);
            }
        }
    }

    /**
     * @return
     */
    public Steen[][] getStenen() {
        return stenen;
    }

    public Steen getSteen(int j, int i) {
        return stenen[j][i];
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
            this.kolommen = (int) ((paneel.getBreedte() / (steen.getBreedte() + offsetBreedte))) - 1;
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
