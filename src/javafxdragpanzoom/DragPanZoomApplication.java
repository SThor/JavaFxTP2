/*
 * Application inspirée de :
 * http://stackoverflow.com/questions/29506156/javafx-8-zooming-relative-to-mouse-pointer
 * - Modification de l'architecture,
 * - amélioration du mécanisme général,
 * - déclinaison en 2 versions (méthodes standard et matrices de transformation),
 * - modification zoom (ajout d'éléments de taille invariante quel que soit le facteur de zoom), 
 *   en 2 versions (gestion des invariants grâce à une branche parallèle du graphe de scène ou par transformation inverse).
 *   Ce projet considère la gestion par transformation inverse.
 * - gestion pan et drag par machines à états
 */
package javafxdragpanzoom;

import javafxdragpanzoom.view.views.AbstractHomotheticPane;
import javafxdragpanzoom.view.views.AbstractHomotheticPaneRectangle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafxdragpanzoom.statemachines.DragStateMachine;
import javafxdragpanzoom.view.controls.HomotheticPaneDragManager;
import javafxdragpanzoom.view.controls.HomotheticPanePanManager;
import javafxdragpanzoom.view.controls.HomotheticPaneZoomManager;
import javafxdragpanzoom.view.views.HomotheticPaneGridMatrix;
import javafxdragpanzoom.view.views.HomotheticPaneGridStandard;
import javafxdragpanzoom.view.views.HomotheticPaneRectangleMatrix;
import javafxdragpanzoom.view.views.HomotheticPaneRectangleStandard;

public class DragPanZoomApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Racine du graphe de scène
        Group root = new Group();

        // Conteneur sur lequel on veut faire du pan & zoom
        AbstractHomotheticPane panAndZoomPane = new HomotheticPaneGridMatrix();
        panAndZoomPane.setLayoutX(100);
        panAndZoomPane.setLayoutY(100);
        root.getChildren().add(panAndZoomPane);
        
        // Forme sur laquelle on veut faire du drag
        AbstractHomotheticPaneRectangle rect1 = new HomotheticPaneRectangleMatrix(panAndZoomPane.scaleProperty());
        rect1.setLayoutX(450);
        rect1.setLayoutY(450);
        panAndZoomPane.getChildren().add(rect1);

        // Creation de la scène
        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.setTitle("TP Manipulation directe");
        stage.show();
        
        //Pan
        DragStateMachine panStateMachine = new DragStateMachine();
        HomotheticPanePanManager panManager = new HomotheticPanePanManager(panAndZoomPane, panStateMachine);
        
        //Drag
        DragStateMachine dragStateMachine = new DragStateMachine();
        HomotheticPaneDragManager dragManager = new HomotheticPaneDragManager(rect1, dragStateMachine);
        
        //Zoom
        HomotheticPaneZoomManager zoomManager = new HomotheticPaneZoomManager(panAndZoomPane);
        

        // Déplacer le noeud avec les touches de direction
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                int offset = 50;
                int dx = 0;
                int dy = 0;
                switch (event.getCode()) {
                    case UP:
                        dx = 0;
                        dy = - offset;
                        break;
                    case DOWN:
                        dx = 0;
                        dy = offset;
                        break;
                    case LEFT:
                        dx = - offset;
                        dy = 0;
                        break;
                    case RIGHT:
                        dx = offset;
                        dy = 0;
                        break;
                    case P:
                        panAndZoomPane.addScale(1.1,0,0);
                        break;
                    case M:
                        panAndZoomPane.addScale(1/1.1,0,0);
                        break;
                    default:
                        break;
                }
                rect1.setTranslateX(rect1.getTranslateX() + dx);
                rect1.setTranslateY(rect1.getTranslateY() + dy);
            }
        });
    }
}
