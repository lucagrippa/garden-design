import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Window class
 *
 * @author christiesandberg
 */
public abstract class Window extends Pane {
    int windowLength;
    int windowWidth;
    int MAX_WIDTH = 800;
    int MAX_HEIGHT = 600;

    /**
     * Constructor for window class
     *
     * @param length Length length of window
     * @param width Width  width of window
     */
    public Window(int length, int width) {
        this.windowLength = length;
        this.windowWidth = width;
        setDimensions();
    }

    /**
     * Sets dimensions for window
     */
    public void setDimensions() {
        this.setPrefSize(windowLength, windowWidth);
    }

    /**
     * Sets style for window
     */
    abstract void setStyle();
}
