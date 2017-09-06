package javafxdragpanzoom.view.views;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

/**
 * Classe abstraite décrivant un Pane contenant une grille et dont la mise à l'échelle est toujours homothétique
 * @author saporito
 */
public abstract class AbstractHomotheticPaneGrid extends AbstractHomotheticPane {
    
    // Style et tailles à attribuer aux réalisations de cette classe abstraite
    private final String STYLE = "-fx-background-color: lightgrey; -fx-border-color: blue;";
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int GRID_OFFSET = 50;
    
    /** Group contenant la grille */
    protected final Group grid = new Group();
    
    /**
     * Initialiser le composant et créer la grille 
     * (ce constructeur de classe abstraite sera appelé depuis le constructeur de ses réalisations) 
     */
    public AbstractHomotheticPaneGrid() {
        // Créer la grille
        setStyle(STYLE);
        setWidth(WIDTH);
        setHeight(HEIGHT);
        
        for (int i = 50; i < WIDTH; i+=GRID_OFFSET) {
            grid.getChildren().add(new Line(1,i,HEIGHT,i));
        }
        for (int i = 50; i < HEIGHT; i+=GRID_OFFSET) {
            grid.getChildren().add(new Line(i,1,i,WIDTH));
        }
        getChildren().add(0,grid);
    }
}
