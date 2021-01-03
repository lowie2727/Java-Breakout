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

    private StenenView stenenView;
    private Peddel peddelModel;
    private BalView balView;
    private Bal balModel;

    public VeldView(StenenView stenenView, Peddel peddelModel, BalView balView, Bal balModel) {
        this.balModel = balModel;
        this.balView = balView;
        this.peddelModel = peddelModel;
        this.stenenView = stenenView;
    }

    public void botsingBal() {
        ObservableList<Node> stenen = stenenView.getChildrenUnmodifiable();
        ObservableList<Node> ballen = balView.getChildrenUnmodifiable();

        for (Node b : ballen) {
            Point2D middelPunt = b.localToParent(Point2D.ZERO);
            if (middelPunt.getY() + balModel.getStraal() >= peddelModel.getY() - 3
                    && middelPunt.getY() + balModel.getStraal() <= peddelModel.getY() + 3
                    && middelPunt.getX() > peddelModel.getX()
                    && middelPunt.getX() < peddelModel.getX() + peddelModel.getBreedte()) {
                balModel.setVy(-0.5);
                b.setId("1");
            }
        }

        for (Node s : stenen) {
            Bounds boundSteen = s.localToParent(s.getBoundsInLocal());
            for (Node b : ballen) {
                Point2D middelPunt = b.localToParent(Point2D.ZERO);
                Bounds boundsBal = b.localToParent(b.getBoundsInLocal());
                if (middelPunt.getY() + balModel.getStraal() >= boundSteen.getMinY() - 3 //onderkant bal
                        && middelPunt.getY() + balModel.getStraal() <= boundSteen.getMinY() + 3
                        && middelPunt.getX() >= boundSteen.getMinX()
                        && middelPunt.getX() <= boundSteen.getMinX() + boundSteen.getWidth()) {
                    balModel.setVy(-0.5);
                    b.setId("1");
                    s.setId("geraakt");
                } else if (middelPunt.getY() - balModel.getStraal() >= boundSteen.getMaxY() - 3 //bovenkant bal
                        && middelPunt.getY() - balModel.getStraal() <= boundSteen.getMaxY() + 3
                        && middelPunt.getX() >= boundSteen.getMinX()
                        && middelPunt.getX() <= boundSteen.getMinX() + boundSteen.getWidth()) {
                    b.setId("2");
                    balModel.setVy(0.5);
                    s.setId("geraakt");
                } else if (middelPunt.getX() + balModel.getStraal() >= boundSteen.getMinX() - 3 //rechterkant bal
                        && middelPunt.getX() + balModel.getStraal() <= boundSteen.getMinX() + 3
                        && middelPunt.getY() >= boundSteen.getMinY()
                        && middelPunt.getY() <= boundSteen.getMinY() + boundSteen.getHeight()) {
                    b.setId("3");
                    balModel.setVx(-0.5);
                    s.setId("geraakt");
                } else if (middelPunt.getX() - balModel.getStraal() >= boundSteen.getMaxX() - 3 //linkerkant bal
                        && middelPunt.getX() - balModel.getStraal() <= boundSteen.getMaxX() + 3
                        && middelPunt.getY() >= boundSteen.getMinY()
                        && middelPunt.getY() <= boundSteen.getMinY() + boundSteen.getHeight()) {
                    b.setId("4");
                    balModel.setVx(0.5);
                    s.setId("geraakt");
                } else if (boundsBal.getMinY() >= boundSteen.getMaxY() - 5 //linksboven bal
                        && boundsBal.getMinY() <= boundSteen.getMaxY()
                        && boundsBal.getMinX() >= boundSteen.getMaxX() - 5
                        && boundsBal.getMinX() <= boundSteen.getMaxX()) {
                    b.setId("5");
                    balModel.setVy(0.5);
                    balModel.setVx(0.5);
                    s.setId("geraakt");
                } else if (boundsBal.getMaxX() >= boundSteen.getMinX() //rechtsonder bal
                        && boundsBal.getMaxX() <= boundSteen.getMinX() + 5
                        && boundsBal.getMaxY() >= boundSteen.getMinY()
                        && boundsBal.getMaxY() <= boundSteen.getMinY() + 5) {
                    b.setId("6");
                    balModel.setVx(-0.5);
                    balModel.setVy(-0.5);
                    s.setId("geraakt");
                } else if (boundsBal.getCenterX() + balModel.getCos() >= boundSteen.getMinX() //rechtsboven bal
                        && boundsBal.getCenterX() + balModel.getCos() <= boundSteen.getMinX() + 5
                        && boundsBal.getCenterY() - balModel.getCos() >= boundSteen.getMaxY() - 5
                        && boundsBal.getCenterY() - balModel.getCos() <= boundSteen.getMaxY()) {
                    b.setId("7");
                    balModel.setVx(-0.5);
                    balModel.setVy(0.5);
                    s.setId("geraakt");

                } else if (boundsBal.getCenterX() - balModel.getCos() >= boundSteen.getMaxX() - 5 //linksonder bal
                        && boundsBal.getCenterX() - balModel.getCos() <= boundSteen.getMaxX()
                        && boundsBal.getCenterY() + balModel.getCos() >= boundSteen.getMinY()
                        && boundsBal.getCenterY() + balModel.getCos() <= boundSteen.getMinY() + 5) {
                    b.setId("8");
                    balModel.setVx(0.5);
                    balModel.setVy(-0.5);
                    s.setId("geraakt");
                }
            }
        }
    }
}