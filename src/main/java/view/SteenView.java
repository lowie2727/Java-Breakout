/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Steen;

/**
 *
 * @author lowie
 */
public class SteenView extends Region {

    private Steen steen;
    private Rectangle r;

    public SteenView(Steen steen) {
        this.steen = steen;
        createSteen();
        //update();
    }

    public void createSteen() {
        r = new Rectangle(steen.getBreedte(), steen.getHoogte(), Color.RED);
        r.setStroke(Color.ORANGE);
        r.setStrokeType(StrokeType.INSIDE);
        r.setStrokeWidth(2);
        getChildren().add(r);
    }
    
    public Rectangle getRechthoek(){
        return r;
    }
//void update nodig

}
