package javafxdragpanzoom.view.controls;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafxdragpanzoom.statemachines.DragStateMachine;
import javafxdragpanzoom.statemachines.EventTypes;
import javafxdragpanzoom.view.views.AbstractHomotheticPane;
import javafxdragpanzoom.view.views.AbstractHomotheticPaneGrid;

/**
 *
 * @author givelpa
 */
public class HomotheticPanePanManager {
    AbstractHomotheticPane paneToPan;
    DragStateMachine stateMachine;

    public HomotheticPanePanManager(AbstractHomotheticPane pane, DragStateMachine stateMachine) {
        this.paneToPan = pane;
        this.stateMachine = stateMachine;
        
        connectEvents();
    }

    private void connectEvents() {
        paneToPan.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            stateMachine.event(EventTypes.PRESSED, event.getX(), event.getY(), paneToPan);
        });
        
        paneToPan.addEventHandler(MouseEvent.MOUSE_DRAGGED, (event) -> {
            stateMachine.event(EventTypes.MOVED, event.getX(), event.getY(), paneToPan);
        });
        
        paneToPan.addEventHandler(MouseEvent.MOUSE_RELEASED, (event) -> {
            stateMachine.event(EventTypes.RELEASED, event.getX(), event.getY(), paneToPan);
        });
    }
}
