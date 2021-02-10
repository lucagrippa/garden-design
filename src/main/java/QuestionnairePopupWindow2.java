import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Popup window that appears when charcters other than numbers are placed in the dimensions textbox
 */
public class QuestionnairePopupWindow2 extends PopupWindow{
	final int OFFSETY = -150;
	final int OFFSETX = 20;
    Button button;
    Label label;
    Label label2;

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
    public QuestionnairePopupWindow2(int length, int width) {
        super(length, width, "Error");
        setStyle();
        button = new Button("Fine...");
        button.relocate(BUTTON_X, BUTTON_Y);
        label = new Label("Length and width must be integer");
        label.setStyle("-fx-text-fill: black;"
                + " -fx-font-size: 18px;"
                + " -fx-font-weight: bold;"
                + " -fx-margin: 50;");
        label.relocate(MESSAGE_X, MESSAGE_Y);
        label2 = new Label("or decimal without units!");
        label2.setStyle("-fx-text-fill: black;"
                + " -fx-font-size: 18px;"
                + " -fx-font-weight: bold;"
                + " -fx-margin: 50;");
        label2.relocate(MESSAGE_X, MESSAGE_Y + 25);
        this.getChildren().addAll(button, label, label2);
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
