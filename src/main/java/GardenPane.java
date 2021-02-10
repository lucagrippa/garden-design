import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.layout.StackPane;

import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * Garden Pane class
 * @author christiesandberg
 *
 */
public class GardenPane extends Window {
    Garden garden;
    Button close;
    StackPane stackPane;
    StackPane polygonPane;
    Group root;
    Polygon polygon;
    int G_PANE_WIDTH = MAX_WIDTH / 4 * 3;


    /**
     * Constructor creates the stack pane and adds and modifies all the children
     *
     * @param length - window length
     * @param width  - window length
     */
    public GardenPane(int length, int width) {
        super(length, width);
        setStyle();
        this.polygon = new Polygon();
        polygon.setTranslateX(width / 25);
        polygon.setTranslateY(length / 8);

        this.polygonPane = new StackPane();
        polygonPane.getChildren().add(polygon);

        this.root = new Group();

        this.stackPane = new StackPane();
        stackPane.setPrefWidth(G_PANE_WIDTH);
        stackPane.getChildren().add(root);

        this.getChildren().addAll(polygonPane, stackPane);
    }

    /**
     * Sets style for garden pane
     */
    @Override
    void setStyle() {
        this.setStyle(" -fx-border-color: black;" + " -fx-border-width: 5;"
                + " -fx-border-radius: 5;" + " -fx-margin: 50;");
    }

    /**
     * Draws the polygon and sets it's characteristics
     *
     * @param observableList - a double list that allows listeners to track changes when they occur.
     * @return polygon -  two-dimensional region within a coordinate space
     */
    public Polygon drawgarden(ObservableList<Double> observableList) {
        polygon.getPoints().setAll(observableList);
        polygon.setStroke(Color.FORESTGREEN);
        polygon.setStrokeWidth(4);
        polygon.setStrokeLineCap(StrokeLineCap.ROUND);
        polygon.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
        return polygon;
    }

//    /**
//     * Deletes all images in the garden and places new images
//     *
//     * @param garden - takes in garden which holds plants
//     */
//    public void refreshGarden(Garden garden) {
//        // Delete all images in garden already
//        stackPane.getChildren().clear();
//
//        // Re-populate with new images
//        for (Plant p : garden.plants.values()) {
//            try {
//                p.plantImage = new ImageView(new Image(new FileInputStream("./src/main/resources/images/" + p.name + ".jpg")));
//                p.plantImage.setFitHeight(100);
//                p.plantImage.setFitWidth(100);
//                // TODO: Not drawing in the exact same location!
//
//                p.plantImage.setTranslateX(p.x);// - G_PANE_WIDTH/2);
//                p.plantImage.setTranslateY(p.y);//s - (125/2));
//                this.stackPane.getChildren().add(p.plantImage);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


}
