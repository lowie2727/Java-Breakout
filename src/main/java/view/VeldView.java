/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
    private PeddelView peddelView;
    private final Peddel peddelModel;
    private final BallenView ballenView;
    private final double cos;
    private final double straal;

    public VeldView(StenenView stenenView, Peddel peddelModel, BallenView ballenview) {
        this.ballenView = ballenview;
        this.peddelModel = peddelModel;
        this.stenenView = stenenView;
        cos = Math.cos(Math.toRadians(45));
        straal = getStraal();
    }

    public void update() {
        botsingBal();
        stenenView.update();
        ballenView.update();

    }

    private void botsingBal() {
        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();
        ObservableList<Node> ballen = ballenView.getChildrenUnmodifiable();

        for (Node b : ballen) {
            Point2D middelPunt = b.localToParent(Point2D.ZERO);
            if (middelPunt.getY() + straal >= peddelModel.getY() - 1
                    && middelPunt.getY() + straal <= peddelModel.getY() + 1
                    && middelPunt.getX() > peddelModel.getX()
                    && middelPunt.getX() < peddelModel.getX() + peddelModel.getBreedte()) {
                b.setId("10");
            }
        }

        int n = 0;
        for (Node s : stenen) {
            Bounds steenBounds = getBoundSteen(s);
            for (Node b : ballen) {
                Point2D middelPunt = b.localToParent(Point2D.ZERO);
                Bounds boundsBal = b.localToParent(b.getBoundsInLocal());
                if (middelPunt.getY() + straal >= steenBounds.getMinY() - 3 //onderkant bal
                        && middelPunt.getY() + straal <= steenBounds.getMinY() + 3
                        && middelPunt.getX() >= steenBounds.getMinX()
                        && middelPunt.getX() <= steenBounds.getMinX() + steenBounds.getWidth()) {
                    b.setId("1");
                    s.setId("geraakt");
                } else if (middelPunt.getY() - straal >= steenBounds.getMaxY() - 3 //bovenkant bal
                        && middelPunt.getY() - straal <= steenBounds.getMaxY() + 3
                        && middelPunt.getX() >= steenBounds.getMinX()
                        && middelPunt.getX() <= steenBounds.getMinX() + steenBounds.getWidth()) {
                    b.setId("2");
                    s.setId("geraakt");
                } else if (middelPunt.getX() + straal >= steenBounds.getMinX() - 3 //rechterkant bal
                        && middelPunt.getX() + straal <= steenBounds.getMinX() + 3
                        && middelPunt.getY() >= steenBounds.getMinY()
                        && middelPunt.getY() <= steenBounds.getMinY() + steenBounds.getHeight()) {
                    b.setId("3");
                    s.setId("geraakt");
                } else if (middelPunt.getX() - straal >= steenBounds.getMaxX() - 3 //linkerkant bal
                        && middelPunt.getX() - straal <= steenBounds.getMaxX() + 3
                        && middelPunt.getY() >= steenBounds.getMinY()
                        && middelPunt.getY() <= steenBounds.getMinY() + steenBounds.getHeight()) {
                    b.setId("4");
                    s.setId("geraakt");
                } else if (boundsBal.getMinY() >= steenBounds.getMaxY() - 2 //linksboven bal
                        && boundsBal.getMinY() <= steenBounds.getMaxY()
                        && boundsBal.getMinX() >= steenBounds.getMaxX() - 2
                        && boundsBal.getMinX() <= steenBounds.getMaxX()) {
                    b.setId("5");
                    s.setId("geraakt");
                } else if (boundsBal.getMaxX() >= steenBounds.getMinX() //rechtsonder bal
                        && boundsBal.getMaxX() <= steenBounds.getMinX() + 2
                        && boundsBal.getMaxY() >= steenBounds.getMinY()
                        && boundsBal.getMaxY() <= steenBounds.getMinY() + 2) {
                    b.setId("6");
                    s.setId("geraakt");
                } else if (middelPunt.getX() + straal * cos >= steenBounds.getMinX() //rechtsboven bal
                        && middelPunt.getX() + straal * cos <= steenBounds.getMinX() + 2
                        && middelPunt.getY() - straal * cos >= steenBounds.getMaxY() - 2
                        && middelPunt.getY() - straal * cos <= steenBounds.getMaxY()) {
                    b.setId("7");
                    s.setId("geraakt");
                } else if (middelPunt.getX() - straal * cos >= steenBounds.getMaxX() - 2 //linksonder bal
                        && middelPunt.getX() - straal * cos <= steenBounds.getMaxX()
                        && middelPunt.getY() + straal * cos >= steenBounds.getMinY()
                        && middelPunt.getY() + straal * cos <= steenBounds.getMinY() + 2) {
                    b.setId("8");
                    s.setId("geraakt");
                }
            }
            n++;
        }
    }

    private double getStraal() {
        double str = 0;
        ObservableList<Node> ballen = ballenView.getChildrenUnmodifiable();
        for (Node b : ballen) {
            Bounds layoutBounds = b.getLayoutBounds();
            str = layoutBounds.getWidth() / 2;
            break;
        }
        return str;
    }

    private Bounds getBoundSteen(Node s) {
        return s.localToParent(s.getBoundsInLocal());
    }
}
