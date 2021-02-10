import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

/**
 * Save window class
 * @author christiesandberg
 *
 */
public class SaveWindow extends PopupWindow {
    final int OFFSET = 50;

    TextField filename = new TextField();
    HBox box = new HBox(filename);
    Button button = new Button("Save garden...");
    Label label = new Label("Enter the name of the garden file\nyou wish to save. (no extension)");

    /**
     * Constructor for SaveWindow which displays a textbox for a user to name the garden file they are saving
     *
     * @param length Length of window in pixels
     * @param width  Width of window in pixels
     */
    public SaveWindow(int length, int width) {
        super(length, width, "Save your garden");
        label.setTextFill(Color.WHITE);
        label.relocate(length / 4, width / 4);
        setStyle();
        filename.relocate(length / 4, width / 2);
        button.relocate(length / 5 + (OFFSET / 2), width / 2 + OFFSET);
        this.getChildren().addAll(filename, button, label);
    }

    /**
     * Sets style of save window
     */
    void setStyle() {
        this.setStyle("-fx-background-color: green;"
                + " -fx-border-color: #502525;"
                + " -fx-border-width: 3;"
                + " -fx-border-radius: 3;"
                + " -fx-margin: 75;");
    }
}
