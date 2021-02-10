import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Model class that stores all information.
 */
public class Model implements Serializable {
    Garden garden;
    int[] dimensions;
    HashMap<String, MiscPlaceable> placeableMisc;
    HashMap<String, GardenObject> gardenObjects;

    /**
     * Model constructor
     *
     * @param name passed in as Garden Name
     *             HashMaps initialized for placeable objects
     *             Plants are loaded from plants file
     */
    public Model(String name) {
        garden = new Garden(name);
        placeableMisc = new HashMap<>();
        gardenObjects = new HashMap<>();
        loadPlantsFromFile(gardenObjects, "plants");
        loadMiscFromFile(placeableMisc, "miscPlaceable");
    }

    /**
     * Garden getter
     *
     * @return garden object
     */
    public Garden getGarden() {
        return garden;
    }

    /**
     * Garden setter
     *
     * @param garden object passed in and set
     */
    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    /**
     * Garden dimensions getter
     *
     * @return garden dimensions as a pair of ints
     */
    public int[] getDimensions() {
        return dimensions;
    }

    /**
     * Garden setter
     *
     * @param dimensions pair of integers passed in and set
     */
    public void setDimensions(int[] dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Placeable misc getter
     *
     * @return HashMap of all miscellaneous names and corresponding plant objects
     */
    public HashMap<String, MiscPlaceable> getPlaceableMisc() {
        return placeableMisc;
    }

    /**
     * Placeable misc setter
     *
     * @param placeableMisc HashMap of all miscellaneous names and corresponding plant objects
     */
    public void setPlaceableMisc(HashMap<String, MiscPlaceable> placeableMisc) {
        this.placeableMisc = placeableMisc;
    }

    /**
     * Load plants from file
     *
     * @param gardenObjects HashMap of all plants names and corresponding plant objects
     * @param filename String filename taken in to access plant file to convert
     * @return HashMap of serialized placeable plant objects to be used in rest of application
     */
    public static HashMap<String, GardenObject> loadPlantsFromFile(HashMap<String, GardenObject> gardenObjects, String filename) {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(new File("./src/main/resources/" + filename + ".csv")));
            String line = "";
            String[] buffArr;
            while ((line = buffer.readLine()) != null) {
                buffArr = line.split(",");

                Image img = new Image(new FileInputStream("./src/main/resources/images/" + buffArr[0] + ".jpg"));
                ImageView plantImage = new ImageView(img);
                Plant plant = new Plant(buffArr[0], 0, 0, buffArr[1], buffArr[2], buffArr[3], buffArr[4], buffArr[5], buffArr[6], buffArr[7], buffArr[8], String.valueOf(buffArr[9]), String.valueOf(buffArr[10]));
                GardenObject gardenObject = new GardenObject(plant, plantImage);
                gardenObjects.put(gardenObject.plant.getId(), gardenObject);
            }
            buffer.close();
        } catch (IOException ioe) {
            System.out.println("Error: bad filename");
            ioe.printStackTrace();
        }
        return gardenObjects;
    }


    public static HashMap<String, MiscPlaceable> loadMiscFromFile(HashMap<String, MiscPlaceable> placeableMisc, String filename) {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(new File("./src/main/resources/" + filename + ".csv")));
            String line = "";
            String[] buffArr;
            while ((line = buffer.readLine()) != null) {
                buffArr = line.split(",");
                Image img = new Image(new FileInputStream("./src/main/resources/images/" + buffArr[0] + ".jpg"));
                MiscPlaceable misc = new MiscPlaceable(buffArr[0], 0, 0, img, String.valueOf(buffArr[1]), String.valueOf(buffArr[2]));
                placeableMisc.put(misc.getId(), misc);

            }
            buffer.close();
        } catch (IOException ioe) {
            System.out.println("Error: bad filename");
            ioe.printStackTrace();
        }
        return placeableMisc;
    }



    /**
     * Return an array list of all unique pollinators for plants in garden.
     * @return pollinators, an array list of pollinator names
     */
    public ArrayList<String> getAllPollinators(){
        ArrayList<String> pollinators = new ArrayList<String>();
        String[] buffer;
        for(Plant p : garden.plants.values()){
            Plant selectedPlant = gardenObjects.get(p.name + "_0_0").getPlant();
            buffer = selectedPlant.getPollinationInfo().split("/");
            for(String s : buffer){
                if(!pollinators.contains(s)){
                    pollinators.add(s);
                }
            }
        }
        return pollinators;
    }

    /**
     * Updates garden score and creates array list of plants with incompatible soil preferences.
     * @return incompatiblePlants, an array list of plant names with soil preference incompatible with garden soil
     */
    public ArrayList<String> getSoilEvaluation(){
        ArrayList<String> incompatiblePlants = new ArrayList<String>();
        String[] buffer;
        int totalPlants = 0;
        int compatiblePlants = 0;
        Boolean containsSoil = false;
        for(Plant p: garden.plants.values()){
            totalPlants++;
            Plant selectedPlant = gardenObjects.get(p.name + "_0_0").getPlant();
            buffer = selectedPlant.getSoilInfo().split("/");
            for(String s : buffer){
                if(this.garden.soil.contains(s)){
                    containsSoil = true;
                }
            }
            if(containsSoil){
                compatiblePlants++;
            }else{
                incompatiblePlants.add(p.name);
            }
        }
        if(totalPlants != 0){
            this.garden.setScore((compatiblePlants/totalPlants) * 100);
        }else{
            this.garden.setScore(0);
        }
        return incompatiblePlants;
    }
}