import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * This class is the side menu on the drag and drop window
 *
 * @author christiesandberg
 */
public class PlantMenu extends StackPane {
    ScrollPane plantScrollPane;
    ScrollPane miscScrollPane;
    TilePane plantTilePane;
    TilePane miscTilePane;
    TitledPane filter;
    FilterMenu filterContents;
    StackPane plantRootPane;
    VBox filteredPlants;
    VBox filteredMisc;
    PopupWindow testPane;

    /**
     * Constructor for the plant menu item, filter, and displays all panes
     *
     * @param length          length of window
     * @param width           width of window
     * @param placeablePlants list of placeable plants
     * @param placeableMisc   list of placeable misc objects
     */
    public PlantMenu(int length, int width) {
        final double SCROLL_PANE_WIDTH = 900;
        final double TILE_PANE_HEIGHT = 600;
        final int X_OFFSET = 200;
        final int Y_OFFSET = 250;

        // Create and configure plantScrollPane
        plantScrollPane = new ScrollPane();
        plantScrollPane.setPrefViewportWidth(SCROLL_PANE_WIDTH / 4);
        plantScrollPane.relocate(0, X_OFFSET);
        plantScrollPane.setFitToWidth(true);

        filteredPlants = new VBox();

        //create filter pane
        filter = new TitledPane();
        filter.setText("Filter");
        filterContents = new FilterMenu(X_OFFSET, Y_OFFSET);

        filter.setContent(filterContents);

        // Create and configure plantTilePane
        plantTilePane = new TilePane(Orientation.HORIZONTAL);
        plantTilePane.setPrefColumns(1);
        plantTilePane.setPrefWidth(SCROLL_PANE_WIDTH / 4);
        plantTilePane.setPrefHeight(TILE_PANE_HEIGHT);

        // Create and configure miscScrollPane
        miscScrollPane = new ScrollPane();
        miscScrollPane.setPrefViewportWidth(SCROLL_PANE_WIDTH / 4);

        // Create and configure miscTilePane
        miscTilePane = new TilePane(Orientation.HORIZONTAL);
        miscTilePane.setPrefColumns(1);
        miscTilePane.setPrefWidth(SCROLL_PANE_WIDTH / 4);
        miscTilePane.setPrefHeight(TILE_PANE_HEIGHT);

        filteredPlants.getChildren().addAll(filter, plantTilePane, miscTilePane);
    }


    /**
     * this will add the picture and name to the pane on the side
     *
     * @param placeablePlants list of placeable plants
     * @return scroll pane that is shown on left of screen
     */
    public ScrollPane addPlantsToScrollPane(HashMap<String, Plant> placeablePlants) {
        for (Plant plant : placeablePlants.values()) {
            plantTilePane.getChildren().add(makeNodeForPlant(plant));
        }
        plantScrollPane.setContent(plantTilePane);
        return plantScrollPane;
    }

    /**
     * This adds the misc objects into a scroll pane to be added into the plant menu to be drag and dropped
     *
     * @param placeableMisc hashmap of available misc objects
     * @return scrollpane of misc objs with the plant scroll pane
     */
    public ScrollPane addMiscToScrollPane(HashMap<String, MiscPlaceable> placeableMisc) {
        for (MiscPlaceable misc : placeableMisc.values()) {
            miscTilePane.getChildren().add(makeNodeForMisc(misc));
        }
        plantScrollPane.setContent(miscTilePane);
        return plantScrollPane;
    }


    /**
     * Helper function to make a node for the plant to be added to the scroll pane
     *
     * @param plant takes in the plant to get the name and image for it
     * @return returns a vbox of individual plant to be a node in scroll pane
     */
    public VBox makeNodeForPlant(Plant plant) {
        HBox p = new HBox(plant.getPlantImage());
        HBox n = new HBox(new Label(plant.toString()));
        VBox l = new VBox();
        l.getChildren().addAll(p, n);
        return l;

    }

    /**
     * Helper function to make a node for the misc to be added
     *
     * @param misc takes in teh misc object to get the name and image for it
     * @return returns a vbox of individual misc object to be a node in scroll pane
     */
    public VBox makeNodeForMisc(MiscPlaceable misc) {
        HBox p = new HBox(misc.getMiscImage());
        HBox n = new HBox(new Label(misc.toString()));
        VBox l = new VBox();
        l.getChildren().addAll(p, n);
        return l;

    }

    /**
     * Getter for plant menu
     *
     * @return a scroll pane that is the plant menu
     */
    public ScrollPane getPlantMenu() {
        return plantScrollPane;
    }

    /**
     * Getter for misc menu
     *
     * @return a scroll pane that is the plant menu
     */
    public ScrollPane getMiscMenu() {
        return miscScrollPane;
    }

}
