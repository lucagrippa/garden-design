import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

import java.util.HashMap;

/**
 * Edit garden window class
 * @author christiesandberg
 *
 */
public class EditGardenWindow extends Window {
    GardenPane gardenWindow;
    PlantMenu menu;
    BorderPane rootPane;
    Button load;
    Button save;
    Button evaluation;
    Button care;
    ToolBar toolBar;
    FilterMenu filter;
    final int TOOLBAR_WIDTH = MAX_WIDTH;
    final int TOOLBAR_HEIGHT = MAX_HEIGHT / 7;
    final int PLANT_MENU_WIDTH = MAX_WIDTH / 4;
    final int PLANT_MENU_HEIGHT = MAX_HEIGHT - TOOLBAR_HEIGHT;
    final int GARDEN_PANE_WIDTH = MAX_WIDTH - PLANT_MENU_WIDTH;
    final int GARDEN_PANE_HEIGHT = MAX_HEIGHT - TOOLBAR_HEIGHT;
    final int ROOT_PANE_X = 0;
    final int ROOT_PANE_Y = 0;

    Label gardenDimWidth;
    Label gardenDimHeight;
    String gardenDimTextWidth;
    String gardenDimTextHeight;

    /**
     * Constructor for the EditGardenWindow creates a PlantMenu, GardenPane, and ToolBar object and adds them to a
     * BorderPane to be displayed as the main window.
     *
     * @param length
     * @param width
     */
    public EditGardenWindow(int length, int width) {
        super(length, width);

        this.menu = new PlantMenu(PLANT_MENU_WIDTH, PLANT_MENU_HEIGHT);
        this.gardenWindow = new GardenPane(GARDEN_PANE_WIDTH, GARDEN_PANE_HEIGHT);

        // Create and configure rootPane
        this.rootPane = new BorderPane();
        rootPane.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        rootPane.setLeft(menu);
        rootPane.setCenter(gardenWindow);

        rootPane.relocate(ROOT_PANE_X, ROOT_PANE_Y);

        this.toolBar = new ToolBar();
        this.load = new Button("Load Garden");
        this.save = new Button("Save Garden");
        this.evaluation = new Button("Garden Evaluation");
        this.care = new Button("Care Guide");
        this.gardenDimWidth = new Label("Garden Width: " + gardenDimTextWidth);
        this.gardenDimHeight = new Label("Garden Height: " + gardenDimTextHeight);

        toolBar.getItems().addAll(load, save, evaluation, care, gardenDimWidth, gardenDimHeight);
        rootPane.setTop(toolBar);
        this.getChildren().addAll(rootPane);
    }

    /**
     * Updates the gardens dimensions when inputted by user
     */
    public void updateGardenDimensions() {
        gardenDimWidth = new Label("Garden Dimensions: " + gardenDimTextWidth);
        gardenDimHeight = new Label("Garden Dimensions: " + gardenDimTextHeight);
        toolBar.getItems().clear();
        toolBar.getItems().addAll(load, save, evaluation, care, gardenDimWidth, gardenDimHeight);
    }

    /**
     * Sets the background color, border color, border width, border radius, and margin of the window.
     */
    @Override
    void setStyle() {
        this.setStyle("-fx-background-color: white;"
                + " -fx-border-color: black;"
                + " -fx-border-width: 5;"
                + " -fx-border-radius: 5;"
                + " -fx-margin: 50;");
    }

}
