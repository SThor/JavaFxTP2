package javafxdragpanzoom.view.views;

import javafx.geometry.Point2D;
import javafx.scene.transform.Scale;

/**
 * Pane homothétique dont la mise à l'échelle est gérée par les méthodes standard de Node
 * @author saporito
 */
public class HomotheticPaneGridStandard extends AbstractHomotheticPaneGrid {

    public HomotheticPaneGridStandard() {
        super();
        // La seule chose à faire ici est de lier le mécanisme de mise à l'échelle
        // lié au choix d'implémentation (les méthodes standard setScaleX...)
        // au mécanisme de gestion de la mise à l'échelle homothétique 
        // et au modèle issus d'AbstractHomotheticPane.
        // Toutes les transformations se feront ensuite directement sur le modèle 
        // via les méthodes setScale(...) réalisées dans cette classe.
        scaleXProperty().bind(scaleProperty());
        scaleYProperty().bind(scaleProperty());
    }

    @Override
    public void setScale(double scale) {
        scaleProperty().set(scale);
    }

    @Override
    public void setScale(double scale, double pivotX, double pivotY) {
        Point2D screenOldPivot = localToParent(pivotX,pivotY);
        
        setScale(scale);
        
        Point2D screenNewPivot = localToParent(pivotX,pivotY);
        double deltaX = screenOldPivot.getX()-screenNewPivot.getX();
        double deltaY = screenOldPivot.getY()-screenNewPivot.getY();
        setTranslateX(getTranslateX()+deltaX);
        setTranslateY(getTranslateY()+deltaY);
    }

    @Override
    public void addScale(double deltaScale, double pivotX, double pivotY) {
        getTransforms().add(new Scale(deltaScale, deltaScale, pivotX, pivotY));
    }

    @Override
    public void translate(double dx, double dy) {
        setTranslateX(getTranslateX()+dx);
        setTranslateY(getTranslateY()+dy);
    }
}
