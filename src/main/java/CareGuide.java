import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This class is the care guide window to show how to take care of plants in all seasons and plant characteristics
 *
 * @author christiesandberg
 */
public class CareGuide extends Window {
	// Window Values
	final double SEASON_IMG_DIMENSION = 75.0;


	// Organize information
	Button back = new Button("Back");
	ComboBox<String> combo = new ComboBox<String>();
	ObservableList<String> plantNames;
	BorderPane border = new BorderPane();
	VBox seasonal = new VBox();
	FlowPane plantInfo = new FlowPane();

	// Attributes that get overwritten depending on plant selected
	ImageView image = new ImageView();
	Label name = new Label("Plant name");
	Label type = new Label("Plant type");
	Label soil = new Label("Soil information");
	Label food = new Label("Food information");
	Label pollination = new Label("Pollination information");
	Label winter = new Label("Winter information:");
	Label spring = new Label("Spring information:");
	Label summer = new Label("Summer information:");
	Label fall = new Label("Fall information:");
	Label menu = new Label("Seasonal Care Guide");
	 
	/**
	 * Constructor for the care guide, sets up all text and images to be placed
	 * @param length length of window
	 * @param width width of window
	 */
	public CareGuide(int length, int width) {
		super(length, width);
		setStyle();

		//Create drop-down menu of plants
		combo.setPadding(new Insets(5, 0, 5, 0));
		HBox comboBox = new HBox(combo);
		comboBox.setPadding(new Insets(10, 10, 10, 10));
		
		HBox backBox = new HBox(back);
		backBox.setPadding(new Insets(10, 10, 10, 10));
		
		//border.setCenter(plantInfo);

		this.getChildren().addAll(border, seasonal, plantInfo);
		
		
		menu.setFont(new Font("Calibri", 30));
		VBox i = new VBox(image);
		i.setPadding(new Insets(10, 10, 10, 10));
		VBox info = new VBox();
		info.setMargin(menu, new Insets(20, 20, 20, 20));
		info.setMargin(name, new Insets(20, 20, 20, 20));
		info.setMargin(type, new Insets(20, 20, 20, 20));
		info.setMargin(soil, new Insets(20, 20, 20, 20));
		info.setMargin(food, new Insets(20, 20, 20, 20));
		info.setMargin(pollination, new Insets(20, 20, 20, 20));
		ObservableList list = info.getChildren();
		list.addAll(menu, name, type, soil, food, pollination);
		
		plantInfo.getChildren().add(info);

		ImageView winterImg = new ImageView(new Image("images/winter.jpeg", SEASON_IMG_DIMENSION, SEASON_IMG_DIMENSION, false, false));
		ImageView springImg = new ImageView(new Image("images/spring.jpeg", SEASON_IMG_DIMENSION, SEASON_IMG_DIMENSION, false, false));
		ImageView summerImg = new ImageView(new Image("images/summer.jpg", SEASON_IMG_DIMENSION, SEASON_IMG_DIMENSION, false, false));
		ImageView fallImg = new ImageView(new Image("images/fall.jpeg", SEASON_IMG_DIMENSION, SEASON_IMG_DIMENSION, false, false));
	
		winter.setPadding(new Insets(5, 0, 5, 0));
		spring.setPadding(new Insets(5, 0, 5, 0));
		summer.setPadding(new Insets(5, 0, 5, 0));
		fall.setPadding(new Insets(5, 0, 5, 0));
		seasonal.setPadding(new Insets(10, 0, 10, 0));
		seasonal.getChildren().addAll(winterImg, winter, springImg, spring,summerImg, summer, fallImg, fall );

		border.setTop(comboBox);
		border.setLeft(i);
		border.setCenter(plantInfo);
		border.setRight(seasonal);
		border.setBottom(backBox);
	}
	
	/**
	 * updates the care guide and places the plant names into drop down
	 */
	public void updateCareGuide(ObservableList<String> plantNames) {
		combo.setItems(plantNames);
		combo.setPromptText("Select plant to view information...");
	}


	/**
	 * Sets the style for care window
	 */
	@Override
	void setStyle() {
		this.setStyle("-fx-background-color: #f0eeee;"
				+ " -fx-border-color: #green;"
				+ " -fx-border-width: 3;"
				+ " -fx-border-radius: 3;"
				+ " -fx-margin: 75;");
	}

	/**
	 *  Updates the plant info depending on what plant is selected on drop down
	 * @param plant Selected plant from drop down
	 */
	void updatePlantInfo(Plant plant){
		name.setText("Name: " + plant.getName());
		image.setImage(plant.getPlantImage().getImage());
		image.setFitHeight((int) SEASON_IMG_DIMENSION * 2);
		image.setFitWidth((int) SEASON_IMG_DIMENSION * 2);
		type.setText("Plant type: " + plant.getType());
		soil.setText("Preferred soil types: " + plant.getSoilInfo());
		food.setText("Watering preference: " + plant.getWaterInfo());
		pollination.setText("Animals attracted: " + plant.getPollinationInfo());
		winter.setText(plant.getWinterInfo());
		spring.setText(plant.getSpringInfo());
		summer.setText(plant.getSummerInfo());
		fall.setText(plant.getFallInfo());	
	}
}