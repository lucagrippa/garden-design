import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.control.Button;

public class GardenObject {

    final int IMAGE_RADIUS = 50;
    final int INFO_BUTTON_X = 41;
    final int INFO_BUTTON_Y = -41;
    final int DELETE_BUTTON_X = -23;
    final int DELETE_BUTTON_Y = -41;
    final int EDIT_BUTTON_X = -34;
    final int EDIT_BUTTON_Y = 39;
    final int GARDEN_OBJECT_WIDTH = 115;
    final int GARDEN_OBJECT_HEIGHT = 115;
    final int IMAGE_X = 100;
    final int IMAGE_Y = 100;

    Button infoButton;
    Button deleteButton;
    Button editButton;
    StackPane imagePane;
    Plant plant;
    ImageView plantImage;

    public GardenObject(Plant plant, ImageView plantImage) {
        this.plant = plant;

        this.plantImage = plantImage;
        plantImage.setFitHeight(IMAGE_Y);
        plantImage.setFitWidth(IMAGE_X);

        this.infoButton = new Button("...");
        infoButton.setVisible(false);
        infoButton.setTranslateX(INFO_BUTTON_X);
        infoButton.setTranslateY(INFO_BUTTON_Y);

        this.deleteButton = new Button("Remove");
        deleteButton.setVisible(false);
        deleteButton.setTranslateX(DELETE_BUTTON_X);
        deleteButton.setTranslateY(DELETE_BUTTON_Y);

        this.editButton = new Button("Edit");
        editButton.setVisible(false);
        editButton.setTranslateX(EDIT_BUTTON_X);
        editButton.setTranslateY(EDIT_BUTTON_Y);

        this.imagePane = new StackPane();

        if (plantImage.getFitWidth() < GARDEN_OBJECT_WIDTH || plantImage.getFitHeight() < GARDEN_OBJECT_HEIGHT) {
            imagePane.setMaxSize(GARDEN_OBJECT_WIDTH, GARDEN_OBJECT_HEIGHT);
            imagePane.setPrefSize(GARDEN_OBJECT_WIDTH, GARDEN_OBJECT_HEIGHT);
        } else {
            imagePane.setMaxSize(plantImage.getFitWidth(), plantImage.getFitHeight());
            imagePane.setPrefSize(plantImage.getFitWidth(), plantImage.getFitHeight());
        }

        imagePane.getChildren().addAll(plantImage, infoButton, deleteButton, editButton);

        Animation delay = new PauseTransition(Duration.seconds(0.5));

        imagePane.setOnMouseEntered(e -> {
            delay.playFromStart();
        });


        delay.setOnFinished(e -> {
            System.out.println("HOVERING");
            infoButton.setVisible(true);
            deleteButton.setVisible(true);
            editButton.setVisible(true);
            imagePane.setStyle("-fx-background-color: lightgrey;"
                    + "-fx-background-radius: 5;"
                    + " -fx-border-color: #502525;"
                    + " -fx-border-width: 2;"
                    + " -fx-border-radius: 3;");
        });

        imagePane.setOnMouseExited(e -> {
            delay.stop();
            System.out.println("HOVERING");
            infoButton.setVisible(false);
            deleteButton.setVisible(false);
            editButton.setVisible(false);
            imagePane.setStyle("-fx-background-color: transparent;"
                    + " -fx-border-color: transparent;"
                    + " -fx-border-width: 0;"
                    + " -fx-border-radius: 0;");
        });
    }

    public StackPane getImagePane() {
        return imagePane;
    }

    public void setImagePane(StackPane imagePane) {
        this.imagePane = imagePane;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public ImageView getPlantImage() {
        return plantImage;
    }

    public void setPlantImage(ImageView plantImage) {
        this.plantImage = plantImage;
    }
}
