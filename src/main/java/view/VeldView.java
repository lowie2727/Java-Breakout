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
    private final PeddelView peddelView;
    private final Peddel peddelModel;
    private final BallenView ballenView;
    private final double cos;

    public VeldView(StenenView stenenView, Peddel peddelModel, BallenView ballenview, PeddelView peddelView) {
        this.ballenView = ballenview;
        this.peddelModel = peddelModel;
        this.stenenView = stenenView;
        this.peddelView = peddelView;
        cos = Math.cos(Math.toRadians(45));
    }

    public void update() {
        botsingBal();
        ballenView.update();
        stenenView.update();
    }

    private void botsingBal() {
        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();
        ObservableList<Node> ballen = ballenView.getChildrenUnmodifiable();
        double straal;

        for (Node b : ballen) {
            Bounds boundsBal = b.localToParent(b.getBoundsInLocal());
            straal = boundsBal.getWidth() / 2;
            Point2D middelPunt = b.localToParent(Point2D.ZERO);
            if (middelPunt.getY() + straal >= peddelModel.getY() - 1
                    && middelPunt.getY() + straal <= peddelModel.getY() + 1
                    && middelPunt.getX() > peddelModel.getX()
                    && middelPunt.getX() < peddelModel.getX() + peddelModel.getBreedte()) {
                b.setId("10");
            }
        }

        for (Node s : stenen) {
            Bounds steenBounds = getBoundSteen(s);
            for (Node b : ballen) {
                Point2D middelPunt = b.localToParent(Point2D.ZERO);
                Bounds boundsBal = b.localToParent(b.getBoundsInLocal());
                straal = boundsBal.getWidth() / 2;
                if (middelPunt.getY() + straal >= steenBounds.getMinY() - 3 //onderkant bal met bovenkant steen
                        && middelPunt.getY() + straal <= steenBounds.getMinY() + 3
                        && middelPunt.getX() >= steenBounds.getMinX()
                        && middelPunt.getX() <= steenBounds.getMinX() + steenBounds.getWidth()) {
                    System.out.println("case: 1");
                    b.setId("1");
                    s.setId("geraakt");
                } else if (middelPunt.getY() - straal >= steenBounds.getMaxY() - 3 //bovenkant bal met onderkant steen
                        && middelPunt.getY() - straal <= steenBounds.getMaxY() + 3
                        && middelPunt.getX() >= steenBounds.getMinX()
                        && middelPunt.getX() <= steenBounds.getMinX() + steenBounds.getWidth()) {
                    System.out.println("case: 2");
                    b.setId("2");
                    s.setId("geraakt");
                } else if (middelPunt.getX() + straal >= steenBounds.getMinX() - 3 //rechterkant bal met linkerkant steen
                        && middelPunt.getX() + straal <= steenBounds.getMinX() + 3
                        && middelPunt.getY() >= steenBounds.getMinY()
                        && middelPunt.getY() <= steenBounds.getMinY() + steenBounds.getHeight()) {
                    System.out.println("case: 3");
                    b.setId("3");
                    s.setId("geraakt");
                } else if (middelPunt.getX() - straal >= steenBounds.getMaxX() - 3 //linkerkant bal met rechterkant steen
                        && middelPunt.getX() - straal <= steenBounds.getMaxX() + 3
                        && middelPunt.getY() >= steenBounds.getMinY()
                        && middelPunt.getY() <= steenBounds.getMinY() + steenBounds.getHeight()) {
                    System.out.println("case: 4");
                    b.setId("4");
                    s.setId("geraakt");
                } else if (Math.sqrt(Math.pow(middelPunt.getX() - steenBounds.getMinX(), 2) + Math.pow(middelPunt.getY() - steenBounds.getMinY(), 2)) < straal) { //linksboven bal
                    System.out.println("case 5");
                    b.setId("5");
                    s.setId("geraakt");
                } else if (Math.sqrt(Math.pow(middelPunt.getX() - steenBounds.getMaxX(), 2) + Math.pow(middelPunt.getY() - steenBounds.getMaxY(), 2)) < straal) { //rechtsonder bal
                    System.out.println("case 6");
                    b.setId("6");
                    s.setId("geraakt");
                } else if (Math.sqrt(Math.pow(middelPunt.getX() - steenBounds.getMinX(), 2) + Math.pow(middelPunt.getY() + steenBounds.getHeight() - steenBounds.getMinY(), 2)) < straal) { //rechtsboven bal
                    System.out.println("case 7");
                    b.setId("7");
                    s.setId("geraakt");
                } else if (Math.sqrt(Math.pow(middelPunt.getX() - steenBounds.getMinX(), 2) + Math.pow(middelPunt.getY() - steenBounds.getHeight() - steenBounds.getMinY(), 2)) < straal) { //linksonder bal
                    System.out.println("case 8");
                    b.setId("8");
                    s.setId("geraakt");
                }
            }
        }
    }

    private Bounds getBoundSteen(Node s) {
        return s.localToParent(s.getBoundsInLocal());
    }

    public void reset() {
        ballenView.reset();
        peddelModel.reset();
        peddelView.update();
        stenenView.maakStenen();
    }
}
