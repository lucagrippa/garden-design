
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * View class creates visible objects on screen
 *
 * @author christiesandberg
 */
public class View {
    Scene scene;
    Background background;
    BorderPane rootPane; //create rootpane
    QuestionnaireWindow questionnaire;
    EditGardenWindow editGardenWindow;
    GardenPane gardenWindow;
    PlantMenu menu;
    Garden garden;
    ScoreWindow scoreWindow;
    CareGuide careGuide;
    QuestionnairePopupWindow questionnairePopupWindow;
    QuestionnairePopupWindow2 questionnairePopupWindow2;
    SaveWindow saveWindow;
    LoadWindow loadWindow;
    PlantHoverWindow hoverWindow;

    int MAX_WIDTH = 800;
    int MAX_HEIGHT = 600;

    /**
     * Constructor of view, makes everything appear
     *
     * @param theStage        The root we add everything to
     */
    public View(Stage theStage) {
        theStage.setTitle("Garden Party");
        rootPane = new BorderPane();
        rootPane.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        background = new Background(MAX_WIDTH, MAX_HEIGHT);

        careGuide = new CareGuide(MAX_WIDTH, MAX_HEIGHT);
        scene = new Scene(background, MAX_WIDTH, MAX_HEIGHT);

        // Questionnaire Window
        questionnaire = new QuestionnaireWindow(MAX_WIDTH, MAX_HEIGHT);

        garden = new Garden("Diane");

        // Edit Garden Window
        editGardenWindow = new EditGardenWindow(MAX_WIDTH, MAX_HEIGHT);

        //Questionnaire Popup Window
        questionnairePopupWindow = new QuestionnairePopupWindow(MAX_WIDTH / 2, MAX_HEIGHT / 2);
        questionnairePopupWindow.relocate(MAX_WIDTH / 4, MAX_HEIGHT / 4);
        
         //Questionnaire Popup Window2
        questionnairePopupWindow2 = new QuestionnairePopupWindow2(MAX_WIDTH / 2, MAX_HEIGHT / 2);
        questionnairePopupWindow2.relocate(MAX_WIDTH / 4, MAX_HEIGHT / 4);

        //Save Window
        saveWindow = new SaveWindow(MAX_WIDTH / 3, MAX_HEIGHT / 3);
        saveWindow.relocate(MAX_WIDTH / 4, MAX_HEIGHT / 4);

        //Load Window
        loadWindow = new LoadWindow(MAX_WIDTH / 3, MAX_HEIGHT / 3);
        loadWindow.relocate(MAX_WIDTH / 4, MAX_HEIGHT / 4);

        //ScoreWindow
        scoreWindow = new ScoreWindow(MAX_WIDTH, MAX_HEIGHT);

        // Set the scene
        theStage.setScene(scene);
    }

    /**
     * Sets background for windows
     *
     * @param window window being passed to set the background for
     */
    public void setBackground(Window window) {
        background.addWindow(window);
    }

    /**
     * Removes background of window
     *
     * @param window window being passed to be removed
     */
    public void removeBackground(Window window) {
        background.removeWindow(window);
    }

    /**
     * Gets max width to use
     *
     * @return max width of window
     */
    public int getWidth() {
        return MAX_WIDTH;
    }

    /**
     * Gets max height to use
     *
     * @return max height of window
     */
    public int getHeight() {
        return MAX_HEIGHT;
    }

}
