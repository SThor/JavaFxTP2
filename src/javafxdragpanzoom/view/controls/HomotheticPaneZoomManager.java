/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdragpanzoom.view.controls;

import javafx.scene.input.ScrollEvent;
import javafxdragpanzoom.statemachines.DragStateMachine;
import javafxdragpanzoom.statemachines.EventTypes;
import javafxdragpanzoom.view.views.AbstractHomotheticPane;

/**
 *
 * @author Silma Thoron
 */
public class HomotheticPaneZoomManager {

    private AbstractHomotheticPane paneToZoom;
    private final double DEFAULT_ZOOM_FACTOR = 1.01;
    
    public HomotheticPaneZoomManager(AbstractHomotheticPane pane) {
        this.paneToZoom = pane;        
        connectEvents();
    }

    private void connectEvents() {
        paneToZoom.addEventHandler(ScrollEvent.SCROLL, (event) -> {
            // Soit :
            
//            double zoomFactor = DEFAULT_ZOOM_FACTOR;
//            if (event.getDeltaY() <= 0) {
//                // zoom out
//                zoomFactor = 1 / DEFAULT_ZOOM_FACTOR;
//            }
//            paneToZoom.addScale(zoomFactor,event.getX(),event.getY());
            
            // ou bien :
            paneToZoom.addScale(Math.pow(DEFAULT_ZOOM_FACTOR, event.getDeltaY()),event.getX(),event.getY());
        });
    }
    
}
