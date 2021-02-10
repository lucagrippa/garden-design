import javafx.scene.paint.Color;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Popup Window class
 * @author christiesandberg
 *
 */
public class PopupWindow extends Window {
    final int CLOSE_BUTTON_LOCATION = 10;
    Button close;
    String message;
    View view;

    final int ERROR_X = 75;
    final int ERROR_Y = 65;

    /**
     * Popup Window constructor
     *
     * @param length  determines popup window length
     * @param width   determines popup window width
     * @param message determines message to be displayed within window
     */
    public PopupWindow(int length, int width, String message) {
        super(length, width);
        this.message = message;
        setStyle();
        Label label = new Label(message);
        label.relocate(ERROR_X, ERROR_Y);
        label.setStyle("-fx-text-fill: black;"
                + " -fx-font-size: 30px;"
                + " -fx-font-weight: bold;"
                + " -fx-margin: 50;");
        //label.setFont(new Font("Calibri", 20));

        close = new Button("X");
        close.relocate(CLOSE_BUTTON_LOCATION, CLOSE_BUTTON_LOCATION);
        this.getChildren().addAll(label, close);
    }

    /**
     * New Style specific to popup window class
     */
    @Override
    void setStyle() {
        this.setStyle("-fx-background-color: #FFFFFF;"
                + " -fx-background-radius: 5;"
                + " -fx-border-color: black;"
                + " -fx-border-width: 3;"
                + " -fx-border-radius: 3;"
                + " -fx-margin: 75;");
    }
}
