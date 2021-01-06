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
import model.Bal;
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
    private double straal;

    public VeldView(StenenView stenenView, Peddel peddelModel, BallenView ballenview) {
        this.ballenView = ballenview;
        this.peddelModel = peddelModel;
        this.stenenView = stenenView;
        cos = Math.cos(Math.toRadians(45));
        straal = getStraal();
    }

    public void botsingBal() {
        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();
        ObservableList<Node> ballen = ballenView.getChildrenUnmodifiable();

        for (Node b : ballen) {
            Point2D middelPunt = b.localToParent(Point2D.ZERO);
            if (middelPunt.getY() + straal >= peddelModel.getY() - 3
                    && middelPunt.getY() + straal <= peddelModel.getY() + 3
                    && middelPunt.getX() > peddelModel.getX()
                    && middelPunt.getX() < peddelModel.getX() + peddelModel.getBreedte()) {
                b.setId("1");
            }
        }

        for (Node s : stenen) {
            Bounds boundSteen = s.localToParent(s.getBoundsInLocal());
            for (Node b : ballen) {
                Point2D middelPunt = b.localToParent(Point2D.ZERO);
                Bounds boundsBal = b.localToParent(b.getBoundsInLocal());
                if (middelPunt.getY() + straal >= boundSteen.getMinY() - 3 //onderkant bal
                        && middelPunt.getY() + straal <= boundSteen.getMinY() + 3
                        && middelPunt.getX() >= boundSteen.getMinX()
                        && middelPunt.getX() <= boundSteen.getMinX() + boundSteen.getWidth()) {
                    b.setId("1");
                    s.setId("geraakt");
                } else if (middelPunt.getY() - straal >= boundSteen.getMaxY() - 3 //bovenkant bal
                        && middelPunt.getY() - straal <= boundSteen.getMaxY() + 3
                        && middelPunt.getX() >= boundSteen.getMinX()
                        && middelPunt.getX() <= boundSteen.getMinX() + boundSteen.getWidth()) {
                    b.setId("2");
                    s.setId("geraakt");
                } else if (middelPunt.getX() + straal >= boundSteen.getMinX() - 3 //rechterkant bal
                        && middelPunt.getX() + straal <= boundSteen.getMinX() + 3
                        && middelPunt.getY() >= boundSteen.getMinY()
                        && middelPunt.getY() <= boundSteen.getMinY() + boundSteen.getHeight()) {
                    b.setId("3");
                    s.setId("geraakt");
                } else if (middelPunt.getX() - straal >= boundSteen.getMaxX() - 3 //linkerkant bal
                        && middelPunt.getX() - straal <= boundSteen.getMaxX() + 3
                        && middelPunt.getY() >= boundSteen.getMinY()
                        && middelPunt.getY() <= boundSteen.getMinY() + boundSteen.getHeight()) {
                    b.setId("4");
                    s.setId("geraakt");
                } else if (boundsBal.getMinY() >= boundSteen.getMaxY() - 7 //linksboven bal
                        && boundsBal.getMinY() <= boundSteen.getMaxY()
                        && boundsBal.getMinX() >= boundSteen.getMaxX() - 7
                        && boundsBal.getMinX() <= boundSteen.getMaxX()) {
                    b.setId("5");
                    s.setId("geraakt");
                } else if (boundsBal.getMaxX() >= boundSteen.getMinX() //rechtsonder bal
                        && boundsBal.getMaxX() <= boundSteen.getMinX() + 7
                        && boundsBal.getMaxY() >= boundSteen.getMinY()
                        && boundsBal.getMaxY() <= boundSteen.getMinY() + 7) {
                    b.setId("6");
                    s.setId("geraakt");
                } else if (middelPunt.getX() + straal * cos >= boundSteen.getMinX() //rechtsboven bal
                        && middelPunt.getX() + straal * cos <= boundSteen.getMinX() + 7
                        && middelPunt.getY() - straal * cos >= boundSteen.getMaxY() - 7
                        && middelPunt.getY() - straal * cos <= boundSteen.getMaxY()) {
                    b.setId("7");
                    s.setId("geraakt");
                } else if (middelPunt.getX() - straal * cos >= boundSteen.getMaxX() - 7 //linksonder bal
                        && middelPunt.getX() - straal * cos <= boundSteen.getMaxX()
                        && middelPunt.getY() + straal * cos >= boundSteen.getMinY()
                        && middelPunt.getY() + straal * cos <= boundSteen.getMinY() + 7) {
                    b.setId("8");
                    s.setId("geraakt");
                }
            }
        }
    }

    private double getStraal() {
        double straal = 0;
        ObservableList<Node> ballen = ballenView.getChildrenUnmodifiable();
        for (Node b : ballen) {
            Bounds layoutBounds = b.getLayoutBounds();
            straal = layoutBounds.getWidth() / 2;
            break;
        }
        return straal;
    }
}
