package javafxdragpanzoom.view.views;

import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * Pane contenant un rectangle et dont la mise à l'échelle, toujours homothétique, est gérée par les méthodes standard de Node
 * @author saporito
 */
public class HomotheticPaneRectangleMatrix extends AbstractHomotheticPaneRectangle {

    public HomotheticPaneRectangleMatrix() {
        super();
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
