import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * Background class 
 * @author christiesandberg
 *
 */
public class Background extends Window {

    /**
     * Default constructor for Background class
     *
     * @param length Length of window in pixels
     * @param width  Width of window in pixels
     */
    public Background(int length, int width) {
        super(length, width);
    }

    /**
     * Empty overriden method from parent class, unused
     */
    @Override
    void setStyle() {
    }

    /**
     * Remove a window from the viewable background
     *
     * @param window Window you wish to remove from viewable background
     */
    public void removeWindow(Window window) {
        this.getChildren().remove(window);
    }

    /**
     * Add window to the viewable background
     *
     * @param window Window object you wish to add to viewable background
     */
    public void addWindow(Window window) {
        this.getChildren().add(window);
    }


}
