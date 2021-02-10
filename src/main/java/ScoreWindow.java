import java.util.ArrayList;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * Score window class
 * @author christiesandberg
 *
 */
public class ScoreWindow extends Window{
	Button close = new Button("Close");
	Label scoreLabel = new Label("");
	Label scoreDescription = new Label("");
	Label pollinators = new Label("");
	Label soilInfo = new Label("");
	ComboBox<String> fixPlants = new ComboBox<String>();
	BorderPane pane = new BorderPane();
	FlowPane scoreInfo = new FlowPane();
	FlowPane animalList = new FlowPane();
	VBox animals = new VBox();

    /**
     * Constructor for ScoreWindow which visualizes the users garden score based on their plant compatibility
     *
     * @param length Length of window in pixels
     * @param width  Width of window in pixels
     */
    public ScoreWindow(int length, int width) {
        super(length, width);
        setStyle();
		// Create wrapper for logo image
		VBox logoBox = new VBox(new ImageView(new Image("images/gardenpartyLogo.png")));
		// Create wrapper for close button
		VBox backBox = new VBox(close);
		backBox.setPadding(new Insets(10, 10, 10, 10));
		// Create wrapper for combo box
		fixPlants.setPadding(new Insets(5, 0, 5, 0));
		HBox comboBox = new HBox(fixPlants);
		comboBox.setPadding(new Insets(10, 10, 10, 10));

		// Create wrapper for pollinator list
		scoreInfo.getChildren().addAll(new VBox(scoreLabel), new VBox(scoreDescription), comboBox, new VBox(soilInfo));
		scoreInfo.setPadding(new Insets(10, 10, 10, 10));
		// Assign locations in borderpane
		pane.setTop(logoBox);
		pane.setCenter(scoreInfo);
		pane.setBottom(backBox);
		pane.setRight(animalList);
		this.getChildren().add(pane);
    }


	/**
	 * Sets style for score window
	 */
	@Override
	void setStyle() {
		this.setStyle("-fx-background-color: #f0eeee;"
				+ " -fx-border-color: green;"
				+ " -fx-border-width: 3;"
				+ " -fx-border-radius: 3;"
				+ " -fx-margin: 75;");
		scoreLabel.setFont(Font.font("Impact", 30));
		scoreLabel.setTextFill(Color.GREEN);
		scoreDescription.setFont(Font.font("Calibri", 12));
	}

	/**
	 * Updates score number
	 * @param score score passed to update
	 */
	public void updateScore(String score){
		scoreLabel.setText("       "  + score);
		String body = "Your garden has a soil compatibility score of " + score + ". \nThis means that " + score +"% of the plants in your garden have soil preferences compatible with\nthe soil you selected for your garden.";
		if(!score.equals("100.0")){
			body += "\nIn order to improve this metric, you need to address the soil needs of the following plants:";
		}else{
			body += "\n Congratulations, your garden has perfect soil compatibility.";
		}
		scoreDescription.setText(body);
	}

	/**
	 * Gets animals to display on score window screen
	 * @param animals animal list
	 */
	public void populateAnimals(ArrayList<String> animals){
		String body = "";
		animalList.getChildren().clear();
		animalList.getChildren().add(new VBox(new Label("Animals attracted:")));
		VBox animalBulletList = new VBox();
		ObservableList list;
		for(String s: animals){
			Label animalLabel = new Label(s);
			animalBulletList.setMargin(animalLabel, new Insets(0, 5, 5, 5));
			list = animalBulletList.getChildren();
			list.add(animalLabel);
		}
		this.animalList.getChildren().addAll(animalBulletList);
	}

	/**
	 * Updates soil info 
	 * @param plant plant passed to update its soil
	 */
	public void updateSoilInfo(Plant plant){
		soilInfo.setText("This plant requires one or more of the following type of soil:\n" + plant.getSoilInfo());
	}

}
