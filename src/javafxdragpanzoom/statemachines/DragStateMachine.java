package javafxdragpanzoom.statemachines;

import javafx.geometry.Point2D;

/**
 *
 * @author givelpa
 */
public class DragStateMachine {
    States state = States.IDLE;
    double oldX = 0.0;
    double oldY = 0.0;
    
    public void event(EventTypes event, double newX, double newY, ITranslatable item){
        switch(state){
            case IDLE:
                switch(event){
                    case PRESSED:
                        oldX = newX;
                        oldY = newY;
                        state = States.DRAGGING;
                        break;
                    case MOVED:
                        //forbidden
                        break;
                    case RELEASED:
                        //forbidden
                        break;
                }
                break;
            case DRAGGING:
                switch(event){
                    case PRESSED:
                        //forbidden
                        break;
                    case MOVED:
                        item.translate(newX-oldX,newY-oldY);
                        state = States.DRAGGING;
                        break;
                    case RELEASED:
                        oldX = newX;
                        oldY = newY;
                        state = States.IDLE;
                        break;
                }
                break;
        }
    }
}
