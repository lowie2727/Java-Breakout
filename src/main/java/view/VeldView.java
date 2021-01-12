/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import model.Peddel;

/**
 *
 * @author lowie
 */
public class VeldView {

    private final StenenView stenenView;
    private final Peddel peddelModel;
    private final BallenView ballenView;
    private final double cos;
    private final double straal;
    private final ArrayList<Bounds> s;

    public VeldView(StenenView stenenView, Peddel peddelModel, BallenView ballenview) {
        this.ballenView = ballenview;
        this.peddelModel = peddelModel;
        this.stenenView = stenenView;
        cos = Math.cos(Math.toRadians(45));
        straal = getStraal();
        s = getStenen();
    }

    public void botsingBal() {
        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();
        ObservableList<Node> ballen = ballenView.getChildrenUnmodifiable();

        for (Node b : ballen) {
            Point2D middelPunt = b.localToParent(Point2D.ZERO);
            if (middelPunt.getY() + straal >= peddelModel.getY() - 4
                    && middelPunt.getY() + straal <= peddelModel.getY() + 4
                    && middelPunt.getX() > peddelModel.getX()
                    && middelPunt.getX() < peddelModel.getX() + peddelModel.getBreedte()) {
                b.setId("1");
            }
        }
        int n = 0;
        for (Node s : stenen) {
            for (Node b : ballen) {
                Bounds array = this.s.get(n);
                Point2D middelPunt = b.localToParent(Point2D.ZERO);
                Bounds boundsBal = b.localToParent(b.getBoundsInLocal());
                if (middelPunt.getY() + straal >= array.getMinY() - 3 //onderkant bal
                        && middelPunt.getY() + straal <= array.getMinY() + 3
                        && middelPunt.getX() >= array.getMinX()
                        && middelPunt.getX() <= array.getMinX() + array.getWidth()) {
                    b.setId("1");
                    s.setId("geraakt");
                } else if (middelPunt.getY() - straal >= array.getMaxY() - 3 //bovenkant bal
                        && middelPunt.getY() - straal <= array.getMaxY() + 3
                        && middelPunt.getX() >= array.getMinX()
                        && middelPunt.getX() <= array.getMinX() + array.getWidth()) {
                    b.setId("2");
                    s.setId("geraakt");
                } else if (middelPunt.getX() + straal >= array.getMinX() - 3 //rechterkant bal
                        && middelPunt.getX() + straal <= array.getMinX() + 3
                        && middelPunt.getY() >= array.getMinY()
                        && middelPunt.getY() <= array.getMinY() + array.getHeight()) {
                    b.setId("3");
                    s.setId("geraakt");
                } else if (middelPunt.getX() - straal >= array.getMaxX() - 3 //linkerkant bal
                        && middelPunt.getX() - straal <= array.getMaxX() + 3
                        && middelPunt.getY() >= array.getMinY()
                        && middelPunt.getY() <= array.getMinY() + array.getHeight()) {
                    b.setId("4");
                    s.setId("geraakt");
                } else if (boundsBal.getMinY() >= array.getMaxY() - 7 //linksboven bal
                        && boundsBal.getMinY() <= array.getMaxY()
                        && boundsBal.getMinX() >= array.getMaxX() - 7
                        && boundsBal.getMinX() <= array.getMaxX()) {
                    b.setId("5");
                    s.setId("geraakt");
                } else if (boundsBal.getMaxX() >= array.getMinX() //rechtsonder bal
                        && boundsBal.getMaxX() <= array.getMinX() + 7
                        && boundsBal.getMaxY() >= array.getMinY()
                        && boundsBal.getMaxY() <= array.getMinY() + 7) {
                    b.setId("6");
                    s.setId("geraakt");
                } else if (middelPunt.getX() + straal * cos >= array.getMinX() //rechtsboven bal
                        && middelPunt.getX() + straal * cos <= array.getMinX() + 7
                        && middelPunt.getY() - straal * cos >= array.getMaxY() - 7
                        && middelPunt.getY() - straal * cos <= array.getMaxY()) {
                    b.setId("7");
                    s.setId("geraakt");
                } else if (middelPunt.getX() - straal * cos >= array.getMaxX() - 7 //linksonder bal
                        && middelPunt.getX() - straal * cos <= array.getMaxX()
                        && middelPunt.getY() + straal * cos >= array.getMinY()
                        && middelPunt.getY() + straal * cos <= array.getMinY() + 7) {
                    b.setId("8");
                    s.setId("geraakt");
                }
            }
            n++;
        }
    }

    private double getStraal() {
        double st = 0;
        ObservableList<Node> ballen = ballenView.getChildrenUnmodifiable();
        for (Node b : ballen) {
            Bounds layoutBounds = b.getLayoutBounds();
            st = layoutBounds.getWidth() / 2;
            break;
        }
        return st;
    }

    private ArrayList<Bounds> getStenen() {
        ArrayList<Bounds> s = new ArrayList<>();
        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();
        for (Node sNode : stenen) {
            Bounds boundSteen = sNode.localToParent(sNode.getBoundsInLocal());
            s.add(boundSteen);
        }
        return s;
    }
}
