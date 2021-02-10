import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Poppup window that appears when all of the fields are not filled out.
 */
public class QuestionnairePopupWindow extends PopupWindow {
    final int OFFSET = 50;
    Button button;
    Label label;

    final int MESSAGE_X = 75;
    final int MESSAGE_Y = 125;
    final int BUTTON_X = 150;
    final int BUTTON_Y = 200;

    /**
     * Constructor for SaveWindow which displays a textbox for a user to name the garden file they are saving
     *
     * @param length Length of window in pixels
     * @param width  Width of window in pixels
     */
    public QuestionnairePopupWindow(int length, int width) {
        super(length, width, "Error");
        setStyle();
        button = new Button("Fine...");
        button.relocate(BUTTON_X, BUTTON_Y);
        label = new Label("You must fill out the questionnaire first!");
        label.setStyle("-fx-text-fill: black;"
                + " -fx-font-size: 18px;"
                + " -fx-font-weight: bold;"
                + " -fx-margin: 50;");
        label.relocate(MESSAGE_X, MESSAGE_Y);
        this.getChildren().addAll(button, label);
    }

    /**
     * Sets the style of the window.
     */
    void setStyle() {
        this.setStyle("-fx-background-color: #FFFFFF;"
                + " -fx-background-radius: 5;"
                + " -fx-font-size: 16px;"
                + " -fx-font-weight: bold;"
                + " -fx-border-color: black;"
                + " -fx-border-width: 3;"
                + " -fx-border-radius: 3;"
                + " -fx-margin: 75;");
    }
}
