import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import java.io.Serializable;

/**
 * Plant class that is a placeable object to be added to our garden
 *
 * @author christiesandberg
 */
public class Plant extends Placeable implements Serializable {
    String id;
    String soilInfo;
    String pollinationInfo;
    String type;
    String winterInfo;
    String springInfo;
    String summerInfo;
    String fallInfo;
    String waterInfo;
    String length;
    String width;
    float plantCanopyX;
    float plantCanopyY;

    /**
     * Default constructor for Plant with no coordinates, defaults to (0, 0)
     *
     * @param name  String name for plant object
     * @param image Image associated with plant object
     */
    public Plant(String name) {
        super(name);
        this.id = name + "_0" + "_0";
    }

    /**
     * Constructor for Plant that supports setting coordinates
     *
     * @param name  String name for plant object
     * @param x     X coordinate for plant object
     * @param y     Y coordinate for plant object
     * @param image Image associated with plant object
     */
    public Plant(String name, int x, int y) {
        super(name, x, y);
        this.id = name + "_" + String.valueOf(x) + "_" + String.valueOf(y);
    }

    /**
     * Full-sized constructor for when all plant info is parsed from CSV file
     *
     * @param name            String name for plant object
     * @param x               X coordinate for plant object
     * @param y               Y coordinate for plant object
     * @param pollinationInfo Information about pollination for the plant
     * @param soilInfo        Information about soil requirements for plant
     * @param waterInfo       Information about watering requirements for plant
     * @param winterInfo      Information about caring for plant during winter
     * @param springInfo      Information about caring for plant during spring
     * @param summerInfo      Information about caring for plant during summer
     * @param fallInfo        Information about caring for plant during fall
     * @param image           Image associated with plant
     */
    public Plant(String name, int x, int y, String pollinationInfo, String soilInfo, String waterInfo, String winterInfo, String springInfo, String summerInfo, String fallInfo, String type, String length, String width) {
        super(name, x, y);
        this.id = name + "_0" + "_0";
        this.winterInfo = winterInfo;
        this.springInfo = springInfo;
        this.summerInfo = summerInfo;
        this.fallInfo = fallInfo;
        this.soilInfo = soilInfo;
        this.waterInfo = waterInfo;
        this.pollinationInfo = pollinationInfo;
        this.type = type;
        this.length = length;
        this.width = width;
    }

    /**
     * Set toString method if plant needs to be printed
     *
     * @return Name value of string
     */
    public String toString() {
        return this.name;
    }

    /**
     * Print plant name with coordinates for testing and verification purposes
     */
    public void printCoordinates() {
        System.out.print(name);
        System.out.print(" with coordinates: (");
        System.out.print(this.x);
        System.out.print(", ");
        System.out.print(this.y);
        System.out.println(")");
    }

    /**
     * Change the x coordinate attribute of plant and update id accordingly
     *
     * @param x New x coordinate of plant
     */
    @Override
    public void setX(int x) {
        super.setX(x);
        this.id = name + "_" + String.valueOf(x) + "_" + String.valueOf(this.y);
    }

    /**
     * Change the y coordinate attribute of plant and update id accordingly
     *
     * @param y New y coordinate of plant
     */
    @Override
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
        super.setX(x);
        super.setY(y);
        this.id = name + "_" + String.valueOf(x) + "_" + String.valueOf(y);
    }

    /**
     * Return ID attribute of plant
     *
     * @return ID attribute of plant
     */
    public String getId() {
        return id;
    }

    /**
     * Set new plant ID
     *
     * @param id the new ID to be set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get winter info
     *
     * @return winterInfo
     */
    public String getWinterInfo() {
        return winterInfo;
    }

    /**
     * Set new winter info
     *
     * @param winterInfo New winter info
     */
    public void setWinterInfo(String winterInfo) {
        this.winterInfo = winterInfo;
    }

    /**
     * Get spring info
     *
     * @return springInfo
     */
    public String getSpringInfo() {
        return springInfo;
    }

    /**
     * Set new spring info
     *
     * @param springInfo New spring info
     */
    public void setSpringInfo(String springInfo) {
        this.springInfo = springInfo;
    }

    /**
     * Get summer info
     *
     * @return summerInfo
     */
    public String getSummerInfo() {
        return summerInfo;
    }

    /**
     * Set new summer info
     *
     * @param summerInfo New summer info
     */
    public void setSummerInfo(String summerInfo) {
        this.summerInfo = summerInfo;
    }

    /**
     * Get fall info
     *
     * @return fallInfo
     */
    public String getFallInfo() {
        return fallInfo;
    }

    /**
     * Set new fall info
     *
     * @param fallInfo New fall info
     */
    public void setFallInfo(String fallInfo) {
        this.fallInfo = fallInfo;
    }

    /**
     * Get water info
     *
     * @return waterInfo
     */
    public String getWaterInfo() {
        return waterInfo;
    }

    /**
     * Set new water info
     *
     * @param waterInfo New water info
     */
    public void setWaterInfo(String waterInfo) {
        this.waterInfo = waterInfo;
    }

    /**
     * Get soil info
     *
     * @return soilInfo
     */
    public String getSoilInfo() {
        return soilInfo;
    }

    /**
     * Set new soil info
     *
     * @param soilInfo New soil info
     */
    public void setSoilInfo(String soilInfo) {
        this.soilInfo = soilInfo;
    }

    /**
     * Get pollination info
     *
     * @return pollinationInfo
     */
    public String getPollinationInfo() {
        return pollinationInfo;
    }

    /**
     * Set new pollination info
     *
     * @param pollinationInfo New pollination info
     */
    public void setPollinationInfo(String pollinationInfo) {
        this.pollinationInfo = pollinationInfo;
    }

    /**
     * Set type
     *
     * @param type New type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Return plant type
     * @return
     */
    public String getType() {
        return type;
    }




    /**
     * Sets plant width 
     * @param width plant width 
     */
    public void setPlantWdith(String width) {
        this.width = width;
    }

    /**
     * Gets plant width
     * @return width plant width
     */
    public String getPlantWidth() {
        return width;
    }

    /**
     * Sets plant length
     * @param length plant length
     */
    public void setPlantLength(String length) {
        this.length = length;
    }

    /**
     * Gets plant length
     * @return plant length
     */
    public String getPlantLength() {
        return length;
    }

    /**
     * Sets canopy X size
     * @param plantCanopyX canopy size
     */
    public void setPlantCanopyX(float plantCanopyX) {
        this.plantCanopyX = plantCanopyX;
    }

    /**
     * Sets canopy Y size
     * @param plantCanopyY canopy size
     */
    public void setPlantCanopyY(float plantCanopyY) {
        this.plantCanopyY = plantCanopyY;
    }

    /**
     * Gets canopy Y size
     * @return canopy size
     */
    public float getPlantCanopyX() {
        return plantCanopyX;
    }
    

    /**
     * Gets canopy Y size
     * @return canopy size
     */
    public float getPlantCanopyY() {
        return plantCanopyY;
    }
}
