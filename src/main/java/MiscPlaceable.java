import java.io.Serializable;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.Serializable;

/**
 * Miscellaneous Plant class which is a placeable object in garden
 *
 * @author christiesandberg
 */
public class MiscPlaceable extends Placeable implements Serializable {

    final int IMAGE_X = 100;
    final int IMAGE_Y = 100;
    final int IMAGE_RADIUS = 50;
    final int INFO_BUTTON_X = 41;
    final int INFO_BUTTON_Y = -41;
    final int DELETE_BUTTON_X = -23;
    final int DELETE_BUTTON_Y = -41;
    final int EDIT_BUTTON_X = -34;
    final int EDIT_BUTTON_Y = 39;
    final int GARDEN_OBJECT_WIDTH = 115;
    final int GARDEN_OBJECT_HEIGHT = 115;

    ImageView miscImage = new ImageView();
    String id;

    Button infoButton;
    Button deleteButton;
    Button editButton;
    StackPane imagePane;
    String length;
    String width;
    float miscCanopyX;
    float miscCanopyY;

    /**
     * Default constructor for MiscPlaceable, does not get called
     *
     * @param name String value of the name of the misc object
     */

    public MiscPlaceable(String name) {
        super(name);
    }


    /**
     * toString method to get name of misc object
     */
    public String toString() {
        return this.name;
    }

    /**
     * Constructor for MiscPlaceable to set coordinate values
     *
     * @param name   String name of object
     * @param xCoord X coordinate of object
     * @param yCoord Y coordinate of object
     */
    public MiscPlaceable(String name, int xCoord, int yCoord, Image image) {
        super(name, xCoord, yCoord);
        this.id = name + "_" + String.valueOf(xCoord) + "_" + String.valueOf(yCoord);
        this.miscImage.setImage(image);
        this.infoButton = new Button("...");
        this.deleteButton = new Button("Remove");
        this.editButton = new Button("Edit");
        this.imagePane = new StackPane();
        miscImage.setFitHeight(IMAGE_X);
        miscImage.setFitWidth(IMAGE_Y);
    }

    /**
     * Gets ID for misc object
     *
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets ID for misc object
     *
     * @param id the ID for misc object
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets misc image
     *
     * @return image of misc object
     */
    public ImageView getMiscImage() {
        return this.miscImage;
    }



     /** Misc object constructor 
     * @param name name of misc object
     * @param x Y coordinate
     * @param y X coordinate
     * @param image Image of misc object
     * @param length Length of object in real life
     * @param width Width of object in real life
     */
    public MiscPlaceable(String name, int x, int y, Image image, String length, String width) {
        super(name, x, y);
        this.id = name + "_0" + "_0";
        this.width = width;
        this.length = length;
        miscImage.setImage(image);
        miscImage.setFitHeight(IMAGE_X);
        miscImage.setFitWidth(IMAGE_Y);
    }


    /**
     * Sets misc image
     * @param miscImage misc image
     */
    public void setMiscImage(ImageView miscImage) {
        this.miscImage = miscImage;
    }
    /**
     * Change the x coordinate attribute of plant and update id accordingly
     *
     * @param x New x coordinate of plant
     */
    public void setX(int x) {
        super.setX(x);
        this.id = name + "_" + String.valueOf(x) + "_" + String.valueOf(this.y);
    }

    /**
     * Change the y coordinate attribute of plant and update id accordingly
     *
     * @param y New y coordinate of plant
     */

    public void setY(int y) {
        super.setY(y);
        this.id = name + "_" + String.valueOf(this.x) + "_" + String.valueOf(y);
    }

    /**
     * Update coordinates in a single function call
     *
     * @param x New x value of plant
     * @param y New y value of plant
     */
    public void setCoordinates(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Creates garden object for misc object
     *
     * @param miscImage image passed in for misc object
     * @return returns an imagepane for garden object, misc
     */
    public StackPane createGardenObject(ImageView miscImage) {
        infoButton.setVisible(false);
        infoButton.setTranslateX(INFO_BUTTON_X);
        infoButton.setTranslateY(INFO_BUTTON_Y);

        deleteButton.setVisible(false);
        deleteButton.setTranslateX(DELETE_BUTTON_X);
        deleteButton.setTranslateY(DELETE_BUTTON_Y);

        editButton.setVisible(false);
        editButton.setTranslateX(EDIT_BUTTON_X);
        editButton.setTranslateY(EDIT_BUTTON_Y);

        imagePane.setMaxSize(GARDEN_OBJECT_WIDTH, GARDEN_OBJECT_HEIGHT);
        imagePane.setPrefSize(GARDEN_OBJECT_WIDTH, GARDEN_OBJECT_HEIGHT);
        imagePane.getChildren().addAll(miscImage, infoButton, deleteButton, editButton);

        Animation delay = new PauseTransition(Duration.seconds(0.5));

        imagePane.setOnMouseEntered(e -> {
            delay.playFromStart();
        });

        delay.setOnFinished(e -> {
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
            infoButton.setVisible(false);
            deleteButton.setVisible(false);
            editButton.setVisible(false);
            imagePane.setStyle("-fx-background-color: transparent;"
                    + " -fx-border-color: transparent;"
                    + " -fx-border-width: 0;"
                    + " -fx-border-radius: 0;");
        });

        return imagePane;
    }

    /**
     * Sets misc width 
     * @param width width of misc object
     */
    public void setMiscWdith(String width) {
        this.width = width;
    }

    /**
     * Gets misc width
     * @return width of misc object
     */
    public String getMiscWidth() {
        return width;
    }

    /**
     * Sets misc length
     * @param length of misc object
     */
    public void setMiscLength(String length) {
        this.length = length;
    }

    /**
     * Gets misc length
     * @return length of misc object
     */
    public String getMiscLength() {
        return length;
    }

    /**
     * Sets misc X canopy size
     * @param miscCanopyX misc canopy size
     */
    public void setMiscCanopyX(float miscCanopyX) {
        this.miscCanopyX = miscCanopyX;
    }

    /**
     * Sets misc Y canopy size
     * @param plantCanopyY misc canopy size
     */
    public void setMiscCanopyY(float plantCanopyY) {
        this.miscCanopyY = miscCanopyY;
    }

    /**
     * Gets misc X canopy size
     * @return misc canopy size
     */
    public float getMiscCanopyX() {
        return miscCanopyX;
    }

    /**
     * Gets misc Y canopy size
     * @return misc canopy size
     */
    public float getMiscCanopyY() {
        return miscCanopyY;
    }


}
