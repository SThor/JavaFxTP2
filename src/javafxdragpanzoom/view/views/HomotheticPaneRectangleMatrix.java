package javafxdragpanzoom.view.views;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * Pane contenant un rectangle et dont la mise à l'échelle, toujours homothétique, est gérée par les méthodes standard de Node
 * @author saporito
 */
public class HomotheticPaneRectangleMatrix extends AbstractHomotheticPaneRectangle {


    public HomotheticPaneRectangleMatrix(DoubleProperty scaleProperty) {
        super(scaleProperty);
    }

    @Override
    public void setScale(double scale) {
        setScale(scale,0,0);
    }

    @Override
    public void setScale(double scale, double pivotX, double pivotY) {
        getTransforms().removeIf((t) -> {
            return t instanceof Scale;
        });
        addScale(scale, pivotX, pivotY);
    }

    @Override
    public void addScale(double deltaScale, double pivotX, double pivotY) {
        getTransforms().add(new Scale(deltaScale, deltaScale, pivotX, pivotY));
        
        scaleProperty().set(getScale()*deltaScale);
    }

    @Override
    public void translate(double dx, double dy) {
        getTransforms().add(new Translate(dx, dy));
    }
    
}
