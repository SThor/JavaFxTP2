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
public class HomotheticPaneDragManager {
    AbstractHomotheticPane paneToDrag;
    DragStateMachine stateMachine;

    public HomotheticPaneDragManager(AbstractHomotheticPane pane, DragStateMachine stateMachine) {
        this.paneToDrag = pane;
        this.stateMachine = stateMachine;
        
        connectEvents();
    }

    private void connectEvents() {
        paneToDrag.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            stateMachine.event(EventTypes.PRESSED, event.getX(), event.getY(), paneToDrag);
            event.consume();
        });
        
        paneToDrag.addEventHandler(MouseEvent.MOUSE_DRAGGED, (event) -> {
            stateMachine.event(EventTypes.MOVED, event.getX(), event.getY(), paneToDrag);
            event.consume();
        });
        
        paneToDrag.addEventHandler(MouseEvent.MOUSE_RELEASED, (event) -> {
            stateMachine.event(EventTypes.RELEASED, event.getX(), event.getY(), paneToDrag);
            event.consume();
        });
    }
}
