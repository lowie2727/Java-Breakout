package model;

import be.inf1.mavenproject2.StartPaginaController;

/**
 * klasse Stenen
 *
 * @author Lowie Van Vyve, Arnaud Paquet, Jonas Vandenborne
 */
public class Stenen {

    private Steen stenen[][];
    /**
     * het aantal rijen stenen
     */
    private final int rijen;
    /**
     * het aantal kolommen stenen
     */
    private int kolommen;
    /**
     * de breedte van de witte ruimte tussen 2 stenen
     */
    private final double offsetBreedte;
    /**
     * de hoogte van de witte ruimte tussen 2 stenen
     */
    private final double offsetHoogte;
    /**
     * de hoogte van de witte ruimte tussen de stenen en de rand van het paneel
     */
    private double offsetBreedtePaneel;
    /**
     * de breedte van de witte ruimte tussen de stenen en de rand van het paneel
     */
    private final double offsetHoogtePaneel;
    private final Paneel paneel;
    private final Steen steen;

    /**
     * constructur voor objecten van de klasse Stenen
     *
     * @param paneel het paneel waar de bal op komt
     * @param kolommen het aantal kolommen stenen
     */
    public Stenen(Paneel paneel, int kolommen) {
        steen = new Steen(60, 20, 0, 0);
        this.paneel = paneel;
        this.rijen = StartPaginaController.getAantalRijen();
        this.kolommen = kolommen;
        offsetBreedte = 5;
        offsetHoogte = 5;
        setMaxKolommen(this.kolommen);
        setOffsetBreedtePaneel(this.kolommen);
        offsetHoogtePaneel = 50;
        maakStenen();

    }

    /**
     * methode om een matrix van stenen te maken
     */
    public void maakStenen() {
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
     * methode om de stenen te resetten
     */
    public void reset() {
        maakStenen();
    }

    /**
     * geeft alle stenen in een 2D-ArrayList
     *
     * @return stenen
     */
    public Steen[][] getStenen() {
        return stenen;
    }

    /**
     * geeft een steen op een bepaalde positie
     * @param j de rijen in de 2D-ArrayList
     * @param i de kolommen in de 2D-ArrayList
     * @return stenen[rijen][kolommen]
     */
    public Steen getSteen(int j, int i) {
        return stenen[j][i];
    }

    /**
     * geeft het aantal stenen die nog in het veld staan
     *
     * @return aantalStenen
     */
    public int getAantalStenen() {
        int aantalStenen = 0;
        for (int j = 0; j < rijen; j++) {
            for (int i = 0; i < kolommen; i++) {
                if (!stenen[j][i].isGeraakt()) {
                    aantalStenen++;
                }
            }
        }
        return aantalStenen;
    }

    /**
     * geeft het aantal rijen stenen
     *
     * @return rijen
     */
    public int getRijen() {
        return rijen;
    }

    /**
     * geeft het aantal kolommen stenen
     *
     * @return kolommen
     */
    public int getKolommen() {
        return kolommen;
    }

    /**
     * geeft de breedte van de witte ruimte tussen 2 stenen
     *
     * @return offsetBreedte
     */
    public double getOffsetBreedte() {
        return offsetBreedte;
    }

    /**
     * geeft de hoogte van de witte ruimte tussen 2 stenen
     *
     * @return offsetHoogte
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
     * geeft de hoogte van de witte ruimte tussen de stenen en de rand van het
     * paneel
     *
     * @return offsetHoogtePaneel
     */
    public double getOffsetHoogtePaneel() {
        return offsetHoogtePaneel;
    }

    /**
     * geeft de breedte van de witte ruimte tussen de stenen en de rand van het
     * paneel
     *
     * @return offsetBreedtePaneel
     */
    public double getOffsetBreedtePaneel() {
        return offsetBreedtePaneel;
    }
}
