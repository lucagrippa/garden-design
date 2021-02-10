import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sun.prism.Graphics;

/**
 * Garden class
 * @author christiesandberg
 *
 */
public class Garden implements Serializable, Cloneable {
    String name;
    HashMap<String, Plant> plants;
    HashMap<String, MiscPlaceable> miscObjs;
    int x1Points[] = {};
    int y1Points[] = {};
    HashMap<String, String> miscelleaneousInfo;
    HashMap<String, Integer> ratings;
    boolean setChanged;
    boolean saved;
    Garden prevSavedGarden;
    private double score = 0.0;
    ArrayList<String> soil;

    /**
     * Sets name of garden and creates hashmap for plants
     *
     * @param name - name of garden
     */
    public Garden(String name) {
        this.name = name;
        plants = new HashMap<String, Plant>();
        miscObjs = new HashMap<String, MiscPlaceable>();
        setChanged = true;
        saved = false;
        soil = new ArrayList<String>();
    }

    /**
     * Subject to change - from x1points to getting the biggest point in coordinates
     *
     * @param x1Points - change to coordinates
     * @return - max coordinate
     */
    public int largest(int x1Points[]) {
        int i;
        int max = x1Points[0];
        for (i = 1; i < x1Points.length; i++)
            if (x1Points[i] > max)
                max = x1Points[i];

        return max;
    }

    /**
     * Adds plant to garden based on id
     * @param plant
     */
    public void addPlant(Plant plant) {
        this.plants.put(plant.getId(), plant);
    }

    /**
     * Adds misc to garden based on id
     * @param misc
     */
    public void addMisc(MiscPlaceable misc) {
        this.miscObjs.put(misc.getId(), misc);
    }

    /**
     * removes plant from hashmap
     *
     * @param key - key to hashmap
     */
    public void removePlant(String key) {
        this.plants.remove(key);
    }

    /**
     * Removes misc from hashmap
     * @param key key to hashmap
     */
    public void removeMisc(String key) {
        this.miscObjs.remove(key);
    }

    /**
     * prints the plant names in the garden
     */
    public void printPlants() {
        System.out.println("Plants in garden:");
        for (Plant plant : this.plants.values()) {
            plant.printCoordinates();
        }
    }

    /**
     * checks for duplicate plants in the garden
     *
     * @param name - takes the name of the plant
     * @return - duplicates - an array of plants
     */
    public ArrayList<Plant> getDuplicatePlants(String name) {
        ArrayList<Plant> duplicates = new ArrayList<Plant>();
        Iterator it = plants.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Plant plantEntry = (Plant) entry.getValue();
            if (plantEntry.getName().equals(name)) {
                duplicates.add((Plant) entry.getValue());
            }
        }
        return duplicates;
    }

    /**
     * Updates the plants coordinates when moved
     * @param plant plant they want to move
     * @param x x coordinate of plant
     * @param y y coordinate of plant
     */
    public void updatePlantCoordinates(Plant plant, int x, int y) {
        plants.remove(plant.getId());
        plant.setCoordinates(x, y);
        plants.put(plant.getId(), plant);
    }
    
    /**
     * Updates the misc objects coordinates when moved
     * @param misc misc they want to move
     * @param x x coordinate of misc
     * @param y y coordinate of misc
     */
    public void updateMiscCoordinates(MiscPlaceable misc, int x, int y) {
    	miscObjs.remove(misc.getId());
    	misc.setCoordinates(x, y);
    	miscObjs.put(misc.getId(), misc);
    	}

    /**
     * @param plant - plant
     * @param x     - x location of plant
     * @param y     - y location of plant
     */
    public void changePlantCoordinates(Plant plant, int x, int y) {
        if ((x > largest(x1Points)) || (y > largest(y1Points)) || (x < 0) || (y < 0)) {
            System.out.println("Coordinates out of range, try again");
        } else {
            plant.setCoordinates(x, y);
        }
    }

    /**
     * gets the plant
     *
     * @param key - key for plant hashmap
     * @return plants key
     */
    public Plant getPlant(String key) {
        return this.plants.get(key);
    }

    /**
     * Get misc object 
     * @param key key of misc object
     * @return returns misc object with passed key
     */
    public MiscPlaceable getMiscObj(String key) {
        return this.miscObjs.get(key);
    }

    /**
     * gets plants name
     *
     * @return - name of plant
     */
    public String getName() {
        return name;
    }

    /**
     * sets name of plant
     *
     * @param name - name of plant
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets plants hashmap
     *
     * @return plants hashmap
     */
    public HashMap<String, Plant> getPlants() {
        return plants;
    }

    /**
     * Gets misc objects hashmap
     * @return returns misc object hashmap
     */
    public HashMap<String, MiscPlaceable> getMiscObjs() {
        return miscObjs;
    }

    /**
     * sets plants
     *
     * @param plants - plant hashmap
     */
    public void setPlants(HashMap<String, Plant> plants) {
        this.plants = plants;
    }

    /**
     * Gets X points
     * @return X points
     */
    public int[] getx1Points() {
        return x1Points;
    }

    /**
     * Sets X points
     * @param x1Points X points
     */
    public void setx1Points(int[] x1Points) {
        this.x1Points = x1Points;
    }

    /**
     * Gets Y points
     * @return Y Points
     */
    public int[] gety1Points() {
        return y1Points;
    }

    /**
     * Sets Y points
     * @param y1Points Y Points
     */
    public void sety1Points(int[] y1Points) {
        this.y1Points = y1Points;
    }

    /**
     * sets miscellaneous Info
     *
     * @param miscelleaneousInfo - hashmap of miscellaneous info
     */
    public void setMiscelleaneousInfo(HashMap<String, String> miscelleaneousInfo) {
        this.miscelleaneousInfo = miscelleaneousInfo;
    }

    /**
     * gets hashmap of ratings
     *
     * @return hashmap of ratings
     */
    public HashMap<String, Integer> getRatings() {
        return ratings;
    }

    /**
     * sets ratgins
     *
     * @param ratings - hashmap of ratings
     */
    public void setRatings(HashMap<String, Integer> ratings) {
        this.ratings = ratings;
    }

    /**
     * @return -
     * @throws CloneNotSupportedException
     */
    public Garden storedState() throws CloneNotSupportedException {
        prevSavedGarden = (Garden) this.clone();
        return prevSavedGarden;
    }

    /**
     * Function to require users to save the garden
     * @return boolean if the garden was saved
     * @throws CloneNotSupportedException throws an exception
     */
    public boolean setChanged() throws CloneNotSupportedException {
        if (saved) {
            if (this.equals(this.storedState())) {
                setChanged = false;
                saved = false;
            }
            setChanged = true;
            saved = true;
        } else {
            setChanged = true;
            saved = true;
        }
        return setChanged;
    }

    /**
     * adds plants to hashmap
     *
     * @param plant - plant objct
     */
    public void addPlantToHashMap(Plant plant) {
        HashMap<String, Plant> plants = this.getPlants();
        plants.put(plant.getId(), plant);
    }

//    /**
//     * @return - returns plant object
//     */
//    public static HashMap<String, Plant> getPlantsInGarden() {
//        return plants;
//    }

 
    /**
     * Gets score
     * @return score of garden
     */
    public double getScore() {
        return score;
    }

    /**
     * sets score
     * @param score score of garden
     */
    public void setScore(int score) {
        this.score = score;
    }
}