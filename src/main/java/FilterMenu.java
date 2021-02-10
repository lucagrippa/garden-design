import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


/**
 * Filter menu class
 * @author christiesandberg
 *
 */
public class FilterMenu extends Window {
	VBox rootContent;
	RadioButton flower;
	RadioButton shrub;
	RadioButton tree;
	Button apply;
	Button reset;
	Button clearSearch;
	Button applySearch;
	RadioButton selectedButton;
	TitledPane typeMenu;
	ToggleGroup types;
	TitledPane searchMenu;
	ToolBar searchBar;
	TextField searchField;
	TitledPane plantOrMisc;
	RadioButton plant;
	RadioButton misc;
	Button applyMisc;
	
	/**
	 * Constructor for filter menu
	 * @param length dictates length of window (inherited from parent Window class)
	 * @param width dictates width of window (inherited from parent Window class)
	 */
	public FilterMenu(int length, int width) {
		 super(length, width);
		 //Create and configure plantScrollPane
		 rootContent = new VBox();
		 
		 //Create search bar
		 searchBar = new ToolBar();
		 searchField = new TextField();
		 searchField.setPrefWidth(100);
		 applySearch = new Button("Search");
		 clearSearch = new Button("Clear");
		 searchBar.getItems().addAll(searchField, applySearch, clearSearch);
		 
		 plantOrMisc = new TitledPane();
		 plantOrMisc.setText("Miscellaneous");
		 HBox placeableGroup = new HBox();
		 plant = new RadioButton("Plants");
		 misc = new RadioButton("Miscellaneous");
		 VBox applyMiscBox = new VBox();
		 applyMisc = new Button("Apply");
		 placeableGroup.getChildren().addAll(plant,misc);
		 applyMiscBox.getChildren().addAll(placeableGroup, applyMisc);
		 plantOrMisc.setContent(applyMiscBox);
		 
		 
		 typeMenu = new TitledPane();
		 typeMenu.setText("Type");
		 //Set up Radio Buttons for each type of plant for filtering
		 types = new ToggleGroup();
		 HBox typeGroup = new HBox();
		 flower = new RadioButton("Perennial");
		 flower.setToggleGroup(types);
//		 flower.setSelected(true);

		 shrub = new RadioButton("Shrub");
		 shrub.setToggleGroup(types);
		  
		 tree = new RadioButton("Tree");
		 tree.setToggleGroup(types);
		 VBox typeBox = new VBox();
		 apply = new Button("Apply");
		 typeGroup.getChildren().addAll(flower, shrub, tree, apply);
		 typeBox.getChildren().addAll(typeGroup,apply);
		 typeMenu.setContent(typeBox);
		 
		 //Create buttons for applying selected filter and resetting plants
		 
		 reset = new Button("Reset All");
		 
		 //Add all nodes to root pane
		 rootContent.getChildren().addAll(searchBar,plantOrMisc,typeMenu, reset);
		 this.getChildren().addAll(rootContent);
	}

    /**
     * Set Style method to make specific changes to this window
     */
    @Override
    void setStyle() {
    }

    /**
     * Method to get selected type from toggle group to pass into filter
     *
     * @param types take in the various buttons for filtering
     * @return selected type as a string to compare to plant String type property
     */
    public String getSelectedType(ToggleGroup types) {
        selectedButton = (RadioButton) types.getSelectedToggle();
        String selectedType = new String();
        selectedType = selectedButton.getText();
        return selectedType;
    }
}
