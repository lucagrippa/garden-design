import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * Placeable plants class
 * @author christiesandberg
 *
 */
public abstract class Placeable implements Serializable {
    /**
     * Name of placeable object
     */
    String name;

    /**
     * Image representation of object
     */
    Image image;

    /**
     * X coordinate of the object
     */
    int x;

    /**
     * Y coordinate of the object
     */
    int y;

    /**
     * Width of the object
     */
    int width = 1;

    /**
     * Length of the object
     */
    int height = 1;

    /**
     * Brief description of object to display for hover
     */
    String description;

    /**
     * Default constructor for Placeable parent class. Never needs to be called
     *
     * @param name Name of Placeable object
     */
    public Placeable(String name) {
        this.name = name;
    }

    /**
     * Constructor for Placeable class with x and y coordinates
     *
     * @param name Name of placeable object
     * @param x    X coordinate of placeable object
     * @param y    Y coordinate of placeable object
     */
    public Placeable(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns name attribute
     *
     * @return name, the name of the object
     */
    public String getName() {
        return name;
    }

    /**
     * Set name attribute
     *
     * @param name Name you want to set attribute as
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns x coordinate
     *
     * @return xCoord, the x coordinate of the object
     */
    public int getX() {
        return x;
    }

    /**
     * Set x coordinate attribute
     *
     * @param x Integer of x coordinate you wish to set attribute as
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns y coordinate
     *
     * @return y coordinate of placeable object
     */
    public int getY() {
        return y;
    }

    /**
     * Set y coordinate of object
     *
     * @param y int value of y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns width attribute
     *
     * @return The width value of the object
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set width of object
     *
     * @param width the int width you wish to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns description attribute
     *
     * @return String description of object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns height attribute
     *
     * @return the height value of the object
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the height attribute of the object
     *
     * @param height Int value of new height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}