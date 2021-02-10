import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Plant Hover Window class
 * @author christiesandberg
 *
 */
public class PlantHoverWindow extends PopupWindow {
    StackPane imagePane;
    ImageView image;
    Label plantName;
    final int IMAGE_X = 50;
    final int IMAGE_Y = 50;
    final int IMAGE_CORNER_RADIUS = 10;
    final int PLANT_NAME_X = 50;
    final int PLANT_NAME_Y = 160;

    /**
     * Constructor for a PlantHoverWindow object, creates an ImageView, Label, and Button and adds them to the Window.
     *
     * @param length
     * @param width
     * @param message
     * @param plant
     * @param rootPlant
     */
    public PlantHoverWindow(int length, int width, String message, Plant plant, Plant rootPlant) {
        super(length, width, message);
        imagePane = new StackPane();
        image = plant.getPlantImage();
        imagePane.getChildren().addAll(image);
        imagePane.relocate(IMAGE_X, IMAGE_Y);
        imagePane.setStyle("-fx-background-color: transparent;"
                + " -fx-background-radius: 5;"
                + " -fx-border-color: #502525;"
                + " -fx-border-width: 3;"
                + " -fx-border-radius: 3;");

        plantName = new Label(plant.getName());
        plantName.relocate(PLANT_NAME_X, PLANT_NAME_Y);
        plantName.setStyle("-fx-font-size: 16px;"
                + " -fx-font-weight: bold;");

        this.getChildren().addAll(imagePane, plantName);
    }


}
