import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Map;
import java.util.regex.Pattern;

import javafx.scene.layout.Background;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.shape.Circle;

/**
 * Controller class
 *
 * @author christiesandberg
 */
public class Controller extends Application {
    private Model model;
    private View view;
    Garden myGarden;

    final int PLANT_MENU_WIDTH = 200;
    final int TOOLBAR_HEIGHT = 100;
    int editFlag = 0;
    Background gardenBackGround;
    File selectedFile;


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start for program begins showing windows on screen
     */
    @Override
    public void start(Stage theStage) throws IOException {
        model = new Model("myGarden");
        view = new View(theStage);
        view.setBackground(view.questionnaire);

        view.questionnaire.next.setOnAction(e -> saveAndContinue());
        view.questionnairePopupWindow.button.setOnAction(e -> view.removeBackground(view.questionnairePopupWindow));
        view.questionnairePopupWindow2.button.setOnAction(e -> view.removeBackground(view.questionnairePopupWindow2));
        view.questionnaire.imageSelection.setOnAction(e -> openFilePicker());

        view.editGardenWindow.save.setOnAction(e -> openSave());
        view.editGardenWindow.load.setOnAction(e -> openLoad());
        view.editGardenWindow.evaluation.setOnAction(e -> openScore());
        view.editGardenWindow.care.setOnAction(e -> openCare());
        view.editGardenWindow.menu.filterContents.applyMisc.setOnAction(e -> filterMiscOrPlants(model.gardenObjects, model.placeableMisc));
        view.editGardenWindow.menu.filterContents.apply.setOnAction(e -> applyFilter(model.gardenObjects));
        view.editGardenWindow.menu.filterContents.reset.setOnAction(e -> resetFilter(model.gardenObjects));
        view.editGardenWindow.menu.filterContents.applySearch.setOnAction(e -> applySearchFilter(model.gardenObjects, model.placeableMisc));
        view.editGardenWindow.menu.filterContents.clearSearch.setOnAction(e -> clearSearchBar());
        
        view.loadWindow.close.setOnAction(e -> view.removeBackground(view.loadWindow));
        view.loadWindow.button.setOnAction(e -> loadHandler());

        view.saveWindow.close.setOnAction(e -> view.removeBackground(view.saveWindow));
        view.saveWindow.button.setOnAction(e -> saveHandler());

        view.scoreWindow.close.setOnAction(e -> view.removeBackground(view.scoreWindow));
        view.scoreWindow.fixPlants.setOnAction(e -> comboHandler("evaluation"));

        view.careGuide.back.setOnAction(e -> view.removeBackground(view.careGuide));
        view.careGuide.combo.setOnAction(e -> comboHandler("care"));

        //view.seasonalCareGuide.next.setOnAction(e -> view.removeBackground(view.seasonalCareGuide));
        theStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            try {
                closeWindowEvent(event);
            } catch (CloneNotSupportedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        theStage.show();
    }

    public void openFilePicker() {
        view.questionnaire.imageSelection.setSelected(false);
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(view.scene.getWindow());
    }

    /**
     * Able to close garden window and exit, checks if you want to exit before saving
     *
     * @param event attempting to close window
     * @throws CloneNotSupportedException throws an exception
     */
    private void closeWindowEvent(WindowEvent event) throws CloneNotSupportedException {
        myGarden = model.getGarden();
        if (myGarden.setChanged()) {  // if the dataset has changed, alert the user with a popup
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getButtonTypes().remove(ButtonType.OK);
            alert.getButtonTypes().add(ButtonType.CANCEL);
            alert.getButtonTypes().add(ButtonType.YES);
            alert.setTitle("Quit application");
            alert.setContentText(String.format("Close without saving?"));
            Optional<ButtonType> res = alert.showAndWait();

            if (res.isPresent()) {
                if (res.get().equals(ButtonType.CANCEL))
                    event.consume();
            }
        }
    }

    /**
     * Checks to see if all of the fields in the questionnaire window are filled out.
     * @return boolean of whether the questionnaire is filled or not
     */
    public boolean isQuestionnaireFilledOut() {
        boolean filledOut = true;
        String name = view.questionnaire.getGardenName();
        String width = view.questionnaire.getGardenDimTextWidth();
        String height = view.questionnaire.getGardenDimTextHeight();
        boolean clay = view.questionnaire.clayButton.isSelected();
        boolean sandy = view.questionnaire.sandyButton.isSelected();
        boolean silty = view.questionnaire.siltyButton.isSelected();
        boolean peaty = view.questionnaire.peatyButton.isSelected();
        boolean chalky = view.questionnaire.chalkyButton.isSelected();
        boolean loamy = view.questionnaire.loamyButton.isSelected();

        if (name.isEmpty() || width.isEmpty() || height.isEmpty() || (!clay && !sandy && !silty && !peaty && !chalky && !loamy)) {
            filledOut = false;
        }

        return filledOut;
    }

    /**
     * Checks the dimension text fields to make sure that there are no other characters besides numbers in them.
     * @return boolean of whether there are other characters or not
     */
    public boolean isTextFloatOrInt() {
    	boolean inputcorrect = true;
    	String length = view.questionnaire.getGardenDimTextHeight();
    	String width = view.questionnaire.getGardenDimTextWidth();
    	int leny = length.length();
    	int lenx = width.length();
    	for(int i = 0; i < leny; i++) {
    		if(Character.isLetter(length.charAt(i))) {
        		System.out.println("text false");
        		inputcorrect = false;
        		return inputcorrect;
        	}
    	}
    	for(int i = 0; i < lenx; i++) {
    		if(Character.isLetter(width.charAt(i))) {
        		System.out.println("text false");
        		inputcorrect = false;
        		return inputcorrect;
        	}
    	}
    	return inputcorrect;
    	
    }

    /**
     * Save and continue menu for garden
     */
    public void saveAndContinue() {
        if (!isQuestionnaireFilledOut()) {
            view.setBackground(view.questionnairePopupWindow);
        } 
        else if(!isTextFloatOrInt()){
        	view.setBackground(view.questionnairePopupWindow2);
        }
        else {
            model.garden.setName(view.questionnaire.getGardenName());
            view.editGardenWindow.gardenDimTextWidth = view.questionnaire.getGardenDimTextWidth();
            view.editGardenWindow.gardenDimTextHeight = view.questionnaire.getGardenDimTextHeight();
            view.editGardenWindow.updateGardenDimensions();
            view.removeBackground(view.questionnaire);
            createGardenImage();
            view.editGardenWindow.menu.getChildren().add(addPlantsToScrollPane(model.placeablePlants, model.placeableMisc));
            List<Double> points = view.questionnaire.polygon.getPoints();
            ObservableList<Double> coordinates = FXCollections.observableArrayList();
            int size = 14;
            for (int i = 0; i < size; i++) {
                double point1 = points.get(i);
                point1 = point1 * 2.5;
                coordinates.add(point1);
            }
            setSoil();
            view.garden.soil = (ArrayList<String>) view.questionnaire.soilType.clone();
            view.setBackground(view.editGardenWindow);
        }
    }

    /**
     * Applies filter to plants available
     *
     * @param placeablePlants list of placeable plants
     */
    public void applyFilter(HashMap<String, GardenObject> placeablePlants) {
    	view.editGardenWindow.menu.plantTilePane.getChildren().clear();
    	view.editGardenWindow.menu.miscTilePane.getChildren().clear();
        String selectedType = view.editGardenWindow.menu.filterContents.getSelectedType(view.editGardenWindow.menu.filterContents.types);
        System.out.println(selectedType);
        HashMap<String, Object> filteredPlants = new HashMap<String, Object>();
        for (Map.Entry mapElement : placeablePlants.entrySet()) {
            String key = ((String) mapElement.getKey());
            Plant value = ((Plant) mapElement.getValue());
            System.out.println(value.getType());
            if (value.getType().equals(selectedType)) {
                filteredPlants.put(key, value);
            }
        }
        view.editGardenWindow.menu.plantScrollPane.setContent(addFilteredPlantsToScrollPane(placeablePlants, filteredPlants));
    }

    /**
     * Adds placeable plants to the scroll pane to be seen once filter is applied
     *
     * @param placeablePlants list of plants to be placed
     * @param filteredPlants  list of plants in filter
     * @return returns a VBox of plants in filter
     */
    public VBox addFilteredPlantsToScrollPane(HashMap<String, Plant> placeablePlants,
                                              HashMap<String, Object> filteredPlants) {
        view.editGardenWindow.menu.plantTilePane.getChildren().clear();

        for (Object placeable : filteredPlants.values()) {
        	if (placeable instanceof Plant) {
        		view.editGardenWindow.menu.plantTilePane.getChildren().add(makeNodeForPlant((Plant) placeable));
                ((Plant) placeable).getPlantImage().setOnDragDetected(e -> drag(e, (Plant) placeable));
        	}
        	else if(placeable instanceof MiscPlaceable) {
        		view.editGardenWindow.menu.plantTilePane.getChildren().add(makeNodeForMisc((MiscPlaceable) placeable));
                ((MiscPlaceable) placeable).getMiscImage().setOnDragDetected(e -> drag(e, (MiscPlaceable) placeable));
        	}
            
        }
        return view.editGardenWindow.menu.filteredPlants;
    }

    /**
     * Function takes in lists of objects and filters them according to search query in plant filter
     * @param placeablePlants full list of placeable plant objects.
     * @param placeableMisc full list of placeable miscellaneous objects
     */
    public void applySearchFilter(HashMap<String, Plant> placeablePlants, HashMap<String, MiscPlaceable> placeableMisc) {
        //System.out.println(view.editGardenWindow.menu.filterContents.getSelectedType(view.editGardenWindow.menu.filterContents.types));
        String query = view.editGardenWindow.menu.filterContents.searchField.getText();
        System.out.println("filtering...");
        System.out.println(query);
        HashMap<String, Object> filteredPlants = new HashMap<String, Object>();
        for (Map.Entry mapElement : placeablePlants.entrySet()) { 
               String key = ((String)mapElement.getKey());
               Plant value = ((Plant)mapElement.getValue()); 
               //System.out.println(value); 
               //System.out.println(value.getType(value.name)); 
               if (value.getName().toLowerCase().contains(query.toLowerCase())) {
                   filteredPlants.put(key, value);
               }
               
            } 
        for (Map.Entry mapElement : placeableMisc.entrySet()) { 
        	   String key = ((String)mapElement.getKey());
	           MiscPlaceable value = ((MiscPlaceable)mapElement.getValue()); 
	           //System.out.println(value); 
	           //System.out.println(value.getType(value.name)); 
	           if (value.getName().toLowerCase().contains(query.toLowerCase())) {
	               filteredPlants.put(key, value);
	           }
           
        }
       System.out.println(filteredPlants);
       //view.editGardenWindow.menu.getChildren().remove(view.editGardenWindow.menu.plantScrollPane);
       view.editGardenWindow.menu.plantScrollPane.setContent(addFilteredPlantsToScrollPane(placeablePlants,filteredPlants));

    }

    /**
     * Filter scroll pane based on placeable object type (plant or miscellaneous).
     * @param gardenObjects placeablePlant total list of placeable plant objects
     * @param placeableMisc placeableMisc total list of placeable misc objects
     * @return filteredPlants VBox with the filtered plants
     */
    public VBox filterMiscOrPlants(HashMap<String, GardenObject> gardenObjects, HashMap<String, MiscPlaceable> placeableMisc) {
    	view.editGardenWindow.menu.plantTilePane.getChildren().clear();
    	view.editGardenWindow.menu.miscTilePane.getChildren().clear();
    	if(view.editGardenWindow.menu.filterContents.plant.isSelected()) {
    		view.editGardenWindow.menu.filterContents.typeMenu.setVisible(true);
    		for (GardenObject gardenObject : gardenObjects.values()) {
                view.editGardenWindow.menu.plantTilePane.getChildren().add(makeNodeForPlant(gardenObject));
                gardenObject.getPlantImage()().setOnDragDetected(e -> drag(e, gardenObject));
            }
    	}
    	if(view.editGardenWindow.menu.filterContents.misc.isSelected()) {
    		if(!(view.editGardenWindow.menu.filterContents.plant.isSelected())) {
    			view.editGardenWindow.menu.filterContents.typeMenu.setVisible(false);
    			view.editGardenWindow.menu.filterContents.typeMenu.managedProperty().bind(view.editGardenWindow.menu.filterContents.typeMenu.visibleProperty());
    		}
    		else {
    			view.editGardenWindow.menu.filterContents.typeMenu.setVisible(true);
    		}
    		for (MiscPlaceable misc : placeableMisc.values()) {
    			view.editGardenWindow.menu.plantTilePane.getChildren().add(makeNodeForMisc(misc));
                misc.getMiscImage().setOnDragDetected(e -> drag(e, misc));
    		}
    		
    	}
    	return view.editGardenWindow.menu.filteredPlants;
    }


    /**
     * Sets the searchField's text to an empty string.
     */
    public void clearSearchBar() {
        view.editGardenWindow.menu.filterContents.searchField.setText("");
    }

    /**
     * On click, the filter will reset and put plants back in to have a fresh start
     *
     * @param gardenObjects list of placeable plants to be seen in pane
     * @return returns reset filter to be used
     */
    public VBox resetFilter(HashMap<String, GardenObject> gardenObjects) {
        view.editGardenWindow.menu.plantTilePane.getChildren().clear();

        for (GardenObject gardenObject : gardenObjects.values()) {
            view.editGardenWindow.menu.plantTilePane.getChildren().add(makeNodeForPlant(gardenObject));
            gardenObject.getPlantImage().setOnDragDetected(e -> drag(e, gardenObject));
        }
        return view.editGardenWindow.menu.filteredPlants;
    }

    /**
     * Opens save window to be seen by user
     */
    public void openSave() {
        view.setBackground(view.saveWindow);
    }

    /**
     * Opens load window to be seen by user
     */
    public void openLoad() {
        view.setBackground(view.loadWindow);
    }

    /**
     * Opens score window to be seen by user
     */
    public void openScore() {
        view.setBackground(view.scoreWindow);
        ArrayList<String> plantNames = model.getSoilEvaluation();
        view.scoreWindow.fixPlants.setItems(FXCollections.observableList(plantNames));
        view.scoreWindow.updateScore(String.valueOf(model.garden.getScore()));
        view.scoreWindow.populateAnimals(model.getAllPollinators());
    }

    /**
     * Opens care window to be seen by user
     */
    public void openCare() {
        ArrayList<String> plantNames = new ArrayList<String>();
        for (Plant p : model.garden.getPlants().values()) {
            plantNames.add(p.getName());
        }
        view.careGuide.updateCareGuide(FXCollections.observableList(plantNames));
        view.setBackground(view.careGuide);
        view.careGuide.updateCareGuide(FXCollections.observableList(plantNames));
        view.setBackground(view.careGuide);
    }

    /**
     * Adds DragOver and DragDropped event handlers to the plant that is passed in
     * to the method.
     *
     * @param plant
     */
    public void addDragHandlers(Plant plant) {
        view.editGardenWindow.gardenWindow.setOnDragOver(e -> dragOver(e));
        view.editGardenWindow.gardenWindow
                .setOnDragDropped(e -> dragDropped(e, view.editGardenWindow.gardenWindow.stackPane, plant));
    }

    /**
     * Adds DragOver and DragDropped event handlers to the misc placeable that is passed in
     * to the method.
     *
     * @param misc
     */
    public void addDragHandlers(MiscPlaceable misc) {
        view.editGardenWindow.gardenWindow.setOnDragOver(e -> dragOver(e));
        view.editGardenWindow.gardenWindow
                .setOnDragDropped(e -> dragDropped(e, view.editGardenWindow.gardenWindow.stackPane, misc));
    }

    /**
     * Fetches all of the plants, creates a Plant Node for that plant, and adds it
     * to the PlantMenu.
     *
     * @param placeablePlants
     * @return ScrollPane
     */
    public ScrollPane addPlantsToScrollPane(HashMap<String, GardenObject> gardenObjects, HashMap<String, MiscPlaceable> placeableMisc) {
        for (GardenObject gardenObject : gardenObjects.values()) {
            view.editGardenWindow.menu.plantTilePane.getChildren().add(makeNodeForPlant(gardenObject));
            gardenObject.getPlantImage().setOnDragDetected(e -> drag(e, gardenObject));
        }
        for (MiscPlaceable misc : placeableMisc.values()) {
            view.editGardenWindow.menu.miscTilePane.getChildren().add(makeNodeForMisc(misc));
            misc.getMiscImage().setOnDragDetected(e -> drag(e, misc));
        }
        view.editGardenWindow.menu.plantScrollPane.setContent(view.editGardenWindow.menu.filteredPlants);
        //addMiscToScrollPane(model.placeableMisc);
        return view.editGardenWindow.menu.plantScrollPane;
    }

    /**
     * Creates the nodes that are added to the PlantMenu, the nodes consist of two
     * HBox's that contain the plant image and plant name and they are added to a
     * VBox that is then added to the PlantMenu.
     *
     * @param gardenObject
     * @return VBox
     */
    public VBox makeNodeForPlant(GardenObject gardenObject) {
        HBox p = new HBox(gardenObject.getPlantImage());
        HBox n = new HBox(new Label(gardenObject.getPlant().toString()));
        VBox l = new VBox();
        l.getChildren().addAll(p, n);
        return l;

    }

    /**
     * Creates the nodes that are added to the PlantMenu, the nodes consist of two
     * HBox's that contain the misc image and misc name and they are added to a
     * VBox that is then added to the PlantMenu.
     *
     * @param misc
     * @return VBox
     */
    public VBox makeNodeForMisc(MiscPlaceable misc) {
        HBox p = new HBox(misc.getMiscImage());
        HBox n = new HBox(new Label(misc.toString()));
        VBox l = new VBox();
        l.getChildren().addAll(p, n);
        return l;

    }


    // Drag and Drop Handlers

    /**
     * This method is called every time a drag detected event is triggered. It grabs
     * the image of the plant that is dragged, creates a DragBoard and
     * ClipboardContent object, sets the clipboard content to the plant image, and
     * adds the rest of the drag event handlers to the image.
     *
     * @param event
     * @param gardenObject
     */
    public void drag(MouseEvent event, GardenObject gardenObject) {
        ImageView image = gardenObject.getPlantImage();

        /* drag was detected, start a drag-and-drop gesture */
        /* allow any transfer mode */
        Dragboard db = image.startDragAndDrop(TransferMode.COPY);

        /* Put an image on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putImage(image.getImage());
        db.setContent(content);

        addDragHandlers(gardenObject);
        event.consume();
    }

    /**
     * This method is called every time a drag detected event is triggered. It grabs
     * the image of the misc that is dragged, creates a DragBoard and
     * ClipboardContent object, sets the clipboard content to the misc image, and
     * adds the rest of the drag event handlers to the image.
     *
     * @param event
     * @param misc
     */
    public void drag(MouseEvent event, MiscPlaceable misc) {
        ImageView image = misc.getMiscImage();

        /* drag was detected, start a drag-and-drop gesture */
        /* allow any transfer mode */
        Dragboard db = image.startDragAndDrop(TransferMode.COPY);

        /* Put an image on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putImage(image.getImage());
        db.setContent(content);

        addDragHandlers(misc);
        event.consume();
    }


    /**
     * This method is called every time a drag over event is triggered. It checks if
     * the DragBoard has an image and sets the transfer mode of the event.
     *
     * @param event
     */
    public void dragOver(DragEvent event) {
        Dragboard dragboard = event.getDragboard();

        /* data is dragged over the target */
        /*
         * accept it only if it is not dragged from the same node and if it has image
         * data
         */
        if (event.getDragboard().hasImage()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    /**
     * This method is called every time a drag dropped event is triggered. It
     * duplicates the plant being dropped at the location, adds it to the garden and
     * the GardenPane.
     *
     * @param event
     * @param stack
     * @param plant
     */
    public void dragDropped(DragEvent event, StackPane stack, Plant plant) {

        /* data dropped */
        /* if there is image data on dragboard, read it and use it */
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasImage()) {
            ImageView plantImage = new ImageView((Image) dragboard.getContent(DataFormat.IMAGE));
            plantImage.setClip(new Circle(scalePlantsToGardenX(plant) / 2, scalePlantsToGardenY(plant) / 2, scalePlantsToGardenX(plant) / 2));
            plantImage.setFitWidth(scalePlantsToGardenX(plant));
            plantImage.setFitHeight(scalePlantsToGardenY(plant));

            // Create and add the plant to the garden object
            Plant dupPlant = new Plant(plant.getName(), (int) Math.round(event.getX() - PLANT_MENU_WIDTH), (int) Math.round(event.getY() - TOOLBAR_HEIGHT));
            model.garden.addPlant(dupPlant);

            // Create garden object for hover
            GardenObject gardenObject = new GardenObject(dupPlant, plantImage);
            StackPane imagePane = gardenObject.getImagePane();
            imagePane.setTranslateX((int) Math.round(event.getX() - (view.editGardenWindow.GARDEN_PANE_WIDTH / 2)));
            imagePane.setTranslateY((int) Math.round(event.getY() - (imagePane.getPrefHeight() / 2)));
            System.out.println("Pane Translate: " + (event.getX() - (view.editGardenWindow.GARDEN_PANE_WIDTH / 2)) + " " + (event.getY() - (plantImage.getFitHeight() / 2)));
            System.out.println(event.getX() + " " + event.getY());
            stack.getChildren().add(imagePane);

            // Grab root plant to pass into event handlers
            HashMap<String, Plant> placeablePlants = model.getPlaceablePlants();
            Plant rootPlant = placeablePlants.get(dupPlant.getName() + "_0" + "_0");

            gardenObject.infoButton.setOnAction(e -> hoverInfoHandler(dupPlant, rootPlant, imagePane));
            gardenObject.deleteButton.setOnAction(e -> removePlantFromGarden(dupPlant, imagePane));
            gardenObject.editButton.setOnAction(e -> movePlant(dupPlant, rootPlant, gardenObject));

            success = true;
        }

        /*
         * let the source know whether the image was successfully transferred and used
         */
        event.setDropCompleted(success);

        event.consume();
    }

    /**
     * This method is called every time a drag dropped event is triggered. It
     * duplicates the misc placeable being dropped at the location, adds it to the garden and
     * the GardenPane.
     *
     * @param event
     * @param stack
     * @param misc
     */
    public void dragDropped(DragEvent event, StackPane stack, MiscPlaceable misc) {

        /* data dropped */
        /* if there is image data on dragboard, read it and use it */
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasImage()) {
            ImageView miscImage = new ImageView((Image) dragboard.getContent(DataFormat.IMAGE));
            miscImage.setClip(new Circle(scaleMiscToGardenY(misc) / 2, scaleMiscToGardenY(misc) / 2, scaleMiscToGardenX(misc) / 2));
            miscImage.setFitHeight(scaleMiscToGardenY(misc));
            miscImage.setFitWidth(scaleMiscToGardenX(misc));

            // Create and add the misc object to the garden object
            MiscPlaceable dupMisc = new MiscPlaceable(misc.getName(), (int) Math.round(event.getX() - PLANT_MENU_WIDTH), (int) Math.round(event.getY() - TOOLBAR_HEIGHT), miscImage.getImage());
            model.garden.addMisc(dupMisc);

            StackPane imagePane = dupMisc.createGardenObject(miscImage);
            imagePane.setTranslateX((int) Math.round(event.getX() - (view.editGardenWindow.GARDEN_PANE_WIDTH / 2)));
            imagePane.setTranslateY((int) Math.round(event.getY() - (imagePane.getPrefHeight() / 2)));
            System.out.println("Pane Translate: " + (event.getX() - (view.editGardenWindow.GARDEN_PANE_WIDTH / 2)) + " " + (event.getY() - (miscImage.getFitHeight() / 2)));
            stack.getChildren().add(imagePane);

            HashMap<String, MiscPlaceable> placeableMisc = model.getPlaceableMisc();
            MiscPlaceable rootMisc = placeableMisc.get(dupMisc.getName() + "_0" + "_0");

            dupMisc.infoButton.setOnAction(e -> hoverInfoHandler(dupMisc, rootMisc, imagePane));
            dupMisc.deleteButton.setOnAction(e -> removeMiscFromGarden(dupMisc, imagePane));
            dupMisc.editButton.setOnAction(e -> moveMisc(dupMisc, rootMisc, imagePane));

            success = true;
        }

        /*
         * let the source know whether the image was successfully transferred and used
         */
        event.setDropCompleted(success);

        event.consume();
    }

    /**
     * Creates a PlantHoverWindow and sets event handlers for when the close button
     * is clicked and when the removePlant button is clicked.
     *
     * @param plant
     * @param rootPlant
     * @param imagePane
     */
    public void hoverInfoHandler(Plant plant, Plant rootPlant, StackPane imagePane) {
        PlantHoverWindow hoverWindow = new PlantHoverWindow(400, 300, "", plant, rootPlant);
        view.setBackground(hoverWindow);
        hoverWindow.relocate(view.MAX_WIDTH / 4, view.MAX_HEIGHT / 4);
        hoverWindow.close.setOnAction(e -> view.removeBackground(hoverWindow));
    }

    /**
     * Creates a MiscHoverWindow and sets event handlers for when the close button
     * is clicked and when the removeMisc button is clicked.
     *
     * @param misc
     * @param rootMisc
     * @param imagePane
     */
    public void hoverInfoHandler(MiscPlaceable misc, MiscPlaceable rootMisc, StackPane imagePane) {
        MiscHoverWindow hoverWindow = new MiscHoverWindow(400, 300, "", misc, rootMisc);
        view.setBackground(hoverWindow);
        hoverWindow.relocate(view.MAX_WIDTH / 4, view.MAX_HEIGHT / 4);
        hoverWindow.close.setOnAction(e -> view.removeBackground(hoverWindow));
    }

    /**
     * Grabs the plants HashMap from the garden, deletes a plant from it, and
     * removes it from the GardenPane.
     *
     * @param plant
     * @param imagePane
     */
    public void removePlantFromGarden(Plant plant, StackPane imagePane) {
        HashMap<String, Plant> plants = model.getGarden().getPlants();
        plants.remove(plant.getId());
        view.editGardenWindow.gardenWindow.stackPane.getChildren().removeAll(imagePane);
    }

    /**
     * Grabs the miscObj HashMap from the garden, deletes a misc placeable from it, and
     * removes it from the GardenPane.
     *
     * @param misc
     * @param imagePane
     */
    public void removeMiscFromGarden(MiscPlaceable misc, StackPane imagePane) {
        HashMap<String, Plant> miscObjs = model.getGarden().getPlants();
        miscObjs.remove(misc.getId());
        view.editGardenWindow.gardenWindow.stackPane.getChildren().removeAll(imagePane);
    }

    /**
     * Allows us to move the plant after it has been placed.
     * @param plant
     * @param rootPlant
     * @param gardenObject
     */
    public void movePlant(Plant plant, Plant rootPlant, GardenObject gardenObject) {
        gardenObject.infoButton.setVisible(false);
        gardenObject.deleteButton.setVisible(false);
        gardenObject.editButton.setVisible(false);
        final Delta sceneLoc = new Delta();
        final Delta stackPaneLoc = new Delta();

        editFlag = 1;

        gardenObject.imagePane.setStyle("-fx-background-color: transparent;"
                + " -fx-border-color: transparent;"
                + " -fx-border-width: 0;"
                + " -fx-border-radius: 0;");

        gardenObject.getImagePane().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (editFlag == 1) {
                    sceneLoc.x = event.getSceneX() - PLANT_MENU_WIDTH;
                    sceneLoc.y = event.getSceneY() - TOOLBAR_HEIGHT;
                    stackPaneLoc.x = gardenObject.getImagePane().getTranslateX();
                    stackPaneLoc.y = gardenObject.getImagePane().getTranslateY();
                    gardenObject.getImagePane().setCursor(Cursor.MOVE);
                }
            }
        });

        gardenObject.getImagePane().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (editFlag == 1) {
                    double eventX = event.getSceneX() - PLANT_MENU_WIDTH;
                    double eventY = event.getSceneY() - TOOLBAR_HEIGHT;
                    gardenObject.getImagePane().setTranslateX(stackPaneLoc.x + (eventX - sceneLoc.x));
                    gardenObject.getImagePane().setTranslateY(stackPaneLoc.y + (eventY - sceneLoc.y));

                    event.consume();
                }
            }
        });

        gardenObject.getImagePane().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int placedX = (int) (event.getSceneX() - PLANT_MENU_WIDTH);
                int placedY = (int) (event.getSceneY() - TOOLBAR_HEIGHT);
                model.garden.updatePlantCoordinates(plant, placedX, placedY);
                editFlag = 0;
            }
        });

    }

    /**
     * Allows us to move the misc placeable after it has been placed.
     * @param misc
     * @param rootMisc
     * @param imagePane
     */
    public void moveMisc(MiscPlaceable misc, MiscPlaceable rootMisc, StackPane imagePane) {
        misc.infoButton.setVisible(false);
        misc.deleteButton.setVisible(false);
        misc.editButton.setVisible(false);
        final Delta sceneLoc = new Delta();
        final Delta stackPaneLoc = new Delta();
        editFlag = 1;

        imagePane.setStyle("-fx-background-color: transparent;"
                + " -fx-border-color: transparent;"
                + " -fx-border-width: 0;"
                + " -fx-border-radius: 0;");

        imagePane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (editFlag == 1) {
                    sceneLoc.x = event.getSceneX() - PLANT_MENU_WIDTH;
                    sceneLoc.y = event.getSceneY() - TOOLBAR_HEIGHT;
                    stackPaneLoc.x = imagePane.getTranslateX();
                    stackPaneLoc.y = imagePane.getTranslateY();
                    imagePane.setCursor(Cursor.MOVE);
                }
            }
        });

        imagePane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (editFlag == 1) {
                    double eventX = event.getSceneX() - PLANT_MENU_WIDTH;
                    double eventY = event.getSceneY() - TOOLBAR_HEIGHT;
                    imagePane.setTranslateX(stackPaneLoc.x + (eventX - sceneLoc.x));
                    imagePane.setTranslateY(stackPaneLoc.y + (eventY - sceneLoc.y));

                    event.consume();
                }
            }
        });

        imagePane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int placedX = (int) (event.getSceneX() - PLANT_MENU_WIDTH);
                int placedY = (int) (event.getSceneY() - TOOLBAR_HEIGHT);
                model.garden.updateMiscCoordinates(misc, placedX, placedY);
                editFlag = 0;
            }
        });
    }


    /**
     * Grabs the response from the filename TextField and passes that into
     * loadGarden(), refreshes the garden, and closes the loadWindow.
     */
    public void loadHandler() {
        String response = view.loadWindow.filename.getText();
        loadGarden(response, model.getGarden());
        view.editGardenWindow.gardenWindow.refreshGarden(model.garden);
        view.removeBackground(view.loadWindow);
    }

    /**
     * Grabs the response from the filename TextField and passes that into
     * saveGarden() and then closes the saveWindow.
     */
    public void saveHandler() {
        String response = view.saveWindow.filename.getText();
        saveGarden(response, model.getGarden());
        view.removeBackground(view.saveWindow);
    }

    /**
     * Gets the string of the selected value in the ComboBox and uses that to
     * retrieve plant info from the CareGuide.
     */
    public void comboHandler(String type) {
        if (type == "evaluation") {
            String plantName = (String) view.scoreWindow.fixPlants.getValue();
            HashMap<String, Plant> plants = model.getPlaceablePlants();
            Plant selectedPlant = plants.get(plantName + "_0" + "_0");
            view.scoreWindow.updateSoilInfo(selectedPlant);
        } else if (type == "care") {
            String plantName = (String) view.careGuide.combo.getValue();
            HashMap<String, Plant> plants = model.getPlaceablePlants();
            Plant selectedPlant = plants.get(plantName + "_0" + "_0");
            view.careGuide.updatePlantInfo(selectedPlant);
        }
    }

//    /**
//     * Gets the HashMap of all the plants placed into the garden and returns it.
//     *
//     * @return HashMap<String, Plant>
//     */
//    public static HashMap<String, Plant> getGardenPlants() {
//        return model.garden.getPlantsInGarden();
//    }

    /**
     * Updates the gardens soil attribute based on teh radio buttons selected in questionnaire window.
     */
    public void setSoil() {
        if (view.questionnaire.clayButton.isSelected()) {
            if (!model.garden.soil.contains("clay")) {
                model.garden.soil.add("clay");
            }
        }
        if (view.questionnaire.sandyButton.isSelected()) {
            if (!model.garden.soil.contains("sandy")) {
                model.garden.soil.add("sandy");
            }
        }
        if (view.questionnaire.siltyButton.isSelected()) {
            if (!model.garden.soil.contains("silty")) {
                model.garden.soil.add("silty");
            }
        }
        if (view.questionnaire.peatyButton.isSelected()) {
            if (!model.garden.soil.contains("peaty")) {
                model.garden.soil.add("peaty");
            }
        }
        if (view.questionnaire.chalkyButton.isSelected()) {
            if (!model.garden.soil.contains("chalky")) {
                model.garden.soil.add("chalky");
            }
        }
        if (view.questionnaire.loamyButton.isSelected()) {
            if (!model.garden.soil.contains("loamy")) {
                model.garden.soil.add("loamy");
            }
        }
    }

    /**
     * Scales the plant image width based on the stored width value and width of the garden.
     * @param plant
     * @return
     */
    public float scalePlantsToGardenX(Plant plant) {
        return ((Float.parseFloat(plant.getPlantWidth()) / Float.parseFloat(view.editGardenWindow.gardenDimTextWidth)) * view.editGardenWindow.GARDEN_PANE_WIDTH);

    }

    /**
     * Scales the plant image length based on the stored length value and width of the garden.
     * @param plant
     * @return
     */
    public float scalePlantsToGardenY(Plant plant) {
        return ((Float.parseFloat(plant.getPlantLength()) / Float.parseFloat(view.editGardenWindow.gardenDimTextHeight)) * view.editGardenWindow.MAX_HEIGHT);

    }

    /**
     * Scales the misc image width based on the stored width value and width of the garden.
     * @param misc
     * @return
     */
    public float scaleMiscToGardenX(MiscPlaceable misc) {
        return ((Float.parseFloat(misc.getMiscWidth()) / Float.parseFloat(view.editGardenWindow.gardenDimTextWidth)) * view.editGardenWindow.GARDEN_PANE_WIDTH);

    }

    /**
     * Scales the misc image length based on the stored length value and width of the garden.
     * @param misc
     * @return
     */
    public float scaleMiscToGardenY(MiscPlaceable misc) {
        return ((Float.parseFloat(misc.getMiscLength()) / Float.parseFloat(view.editGardenWindow.gardenDimTextHeight)) * view.editGardenWindow.MAX_HEIGHT);

    }

    /**
     * If a file is selected this method will use it to set the background of the users garden,
     * otherwise it will set the background as the polygon.
     */
    public void createGardenImage() {
        if (selectedFile != null) {
            Image gardenImage = null;
            try {
                gardenImage = new Image(new FileInputStream(selectedFile.getAbsolutePath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BackgroundImage gardenBackGroundImage = new BackgroundImage(gardenImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            gardenBackGround = new Background(gardenBackGroundImage);
            view.editGardenWindow.gardenWindow.setBackground(gardenBackGround);
        } else {
            view.editGardenWindow.updateGardenDimensions();
            view.editGardenWindow.gardenWindow.setStyle(" -fx-background-color: brown;" + "-fx-border-color: black;" +
                    " -fx-border-width: 5;" + " -fx-border-radius: 5;" + " -fx-margin: 50;");
            List<Double> points = view.questionnaire.polygon.getPoints();
            ObservableList<Double> coordinates = FXCollections.observableArrayList();
            int size = 14;
            for (int i = 0; i < size; i++) {
                double point1 = points.get(i);
                point1 = point1 * 2.5;
                coordinates.add(point1);
            }
            view.editGardenWindow.gardenWindow.polygon = view.editGardenWindow.gardenWindow.drawgarden(coordinates);
        }
    }

    /**
     * Load garden object from file
     *
     * @param gardenName string to access desired garden file
     *                   Function will produce garden based on info from file
     */
    public void loadGarden(String gardenName, Garden garden) {
        try {
            FileInputStream fis = new FileInputStream("./serializable/" + gardenName + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            //System.out.print(ois.readObject());
            garden = (Garden) ois.readObject();
            System.out.println(garden.getName());
            garden.printPlants();
            ois.close();
        } catch (Exception ex) {
            System.out.println("Error loading garden, check stacktrace.");
            ex.printStackTrace();
        }
    }

    /**
     * Save garden object to file.
     *
     * @param gardenName string to create/save over desired garden file
     *                   Function will save garden object to a file
     */
    public void saveGarden(String gardenName, Garden garden) {
        System.out.println("Saving Garden: ");
        garden.setName(gardenName);
        garden.printPlants();
        try {
            FileOutputStream fos = new FileOutputStream("./serializable/" + garden.name + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(garden);
            oos.close();
        } catch (Exception ex) {
            System.out.println("Error saving garden file!");
            ex.printStackTrace();
        }
    }

}
