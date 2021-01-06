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
    private ArrayList<Bounds> s;

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
                Point2D middelPunt = b.localToParent(Point2D.ZERO);
                Bounds boundsBal = b.localToParent(b.getBoundsInLocal());
                if (middelPunt.getY() + straal >= this.s.get(n).getMinY() - 3 //onderkant bal
                        && middelPunt.getY() + straal <= this.s.get(n).getMinY() + 3
                        && middelPunt.getX() >= this.s.get(n).getMinX()
                        && middelPunt.getX() <= this.s.get(n).getMinX() + this.s.get(n).getWidth()) {
                    b.setId("1");
                    s.setId("geraakt");
                } else if (middelPunt.getY() - straal >= this.s.get(n).getMaxY() - 3 //bovenkant bal
                        && middelPunt.getY() - straal <= this.s.get(n).getMaxY() + 3
                        && middelPunt.getX() >= this.s.get(n).getMinX()
                        && middelPunt.getX() <= this.s.get(n).getMinX() + this.s.get(n).getWidth()) {
                    b.setId("2");
                    s.setId("geraakt");
                } else if (middelPunt.getX() + straal >= this.s.get(n).getMinX() - 3 //rechterkant bal
                        && middelPunt.getX() + straal <= this.s.get(n).getMinX() + 3
                        && middelPunt.getY() >= this.s.get(n).getMinY()
                        && middelPunt.getY() <= this.s.get(n).getMinY() + this.s.get(n).getHeight()) {
                    b.setId("3");
                    s.setId("geraakt");
                } else if (middelPunt.getX() - straal >= this.s.get(n).getMaxX() - 3 //linkerkant bal
                        && middelPunt.getX() - straal <= this.s.get(n).getMaxX() + 3
                        && middelPunt.getY() >= this.s.get(n).getMinY()
                        && middelPunt.getY() <= this.s.get(n).getMinY() + this.s.get(n).getHeight()) {
                    b.setId("4");
                    s.setId("geraakt");
                } else if (boundsBal.getMinY() >= this.s.get(n).getMaxY() - 7 //linksboven bal
                        && boundsBal.getMinY() <= this.s.get(n).getMaxY()
                        && boundsBal.getMinX() >= this.s.get(n).getMaxX() - 7
                        && boundsBal.getMinX() <= this.s.get(n).getMaxX()) {
                    b.setId("5");
                    s.setId("geraakt");
                } else if (boundsBal.getMaxX() >= this.s.get(n).getMinX() //rechtsonder bal
                        && boundsBal.getMaxX() <= this.s.get(n).getMinX() + 7
                        && boundsBal.getMaxY() >= this.s.get(n).getMinY()
                        && boundsBal.getMaxY() <= this.s.get(n).getMinY() + 7) {
                    b.setId("6");
                    s.setId("geraakt");
                } else if (middelPunt.getX() + straal * cos >= this.s.get(n).getMinX() //rechtsboven bal
                        && middelPunt.getX() + straal * cos <= this.s.get(n).getMinX() + 7
                        && middelPunt.getY() - straal * cos >= this.s.get(n).getMaxY() - 7
                        && middelPunt.getY() - straal * cos <= this.s.get(n).getMaxY()) {
                    b.setId("7");
                    s.setId("geraakt");
                } else if (middelPunt.getX() - straal * cos >= this.s.get(n).getMaxX() - 7 //linksonder bal
                        && middelPunt.getX() - straal * cos <= this.s.get(n).getMaxX()
                        && middelPunt.getY() + straal * cos >= this.s.get(n).getMinY()
                        && middelPunt.getY() + straal * cos <= this.s.get(n).getMinY() + 7) {
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
