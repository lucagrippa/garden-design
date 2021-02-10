import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

/**
 * Load window class
 * @author christiesandberg
 *
 */
public class LoadWindow extends PopupWindow {
    final int OFFSET = 50;
    TextField filename = new TextField();
    HBox box = new HBox(filename);
    Button button = new Button("Load garden");
    Label label = new Label("Enter the name of your garden file\n(without the .ser extension)");

    /**
     * Constructor for LoadWindow which gives the user the option of inputting a filename of a garden to be loaded
     *
     * @param length Length in pixels of the window
     * @param width  Width in pixels of the window
     */
    public LoadWindow(int length, int width) {
        super(length, width, "Load garden file");
        filename.setText("myGarden");
        label.setTextFill(Color.WHITE);
        label.relocate(length / 4, width / 4);
        setStyle();
        filename.relocate(length / 4, width / 2);
        button.relocate(length / 5 + (OFFSET / 2), width / 2 + OFFSET);
        this.getChildren().addAll(filename, button, label);
    }

    /**
     * Sets style for load window
     */
    void setStyle() {
        this.setStyle("-fx-background-color: green;"
                + " -fx-border-color: #502525;"
                + " -fx-border-width: 3;"
                + " -fx-border-radius: 3;"
                + " -fx-margin: 75;");
    }
}
