import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.FileChooser;

/**
 * The first window of our application to configure the users garden.
 */
public class QuestionnaireWindow extends Window {
    Garden garden;
    Button next;
    ToggleGroup gardenShape;
    Label gardenName;
    TextField gardenNameField;
    ImageView ourLogo;
    Label gardenShapeLabel;
    Image rectangleLayout;
    Label gardenAskForY1;
    TextField gardenX1Field;
    Label gardenImageSelectionLabel;
    TextField gardenImageSelectionField;
    Label gardenImageOr;
    int gardenImageX = 100;
    int gardenImageY = 300;
    String savedName = " ";
    Group root;
    Polygon polygon;
    Double[] array = {0d, 0d, 25d, 0d, 50d, 0d, 75d, 0d, 100d, 0d, 125d, 0d, 150d, 0d};
    ArrayList<Double> coordinates = new ArrayList<Double>(Arrays.asList(array));
    Label soilSelection;
    ArrayList<String> soilType = new ArrayList<String>();

    RadioButton clayButton;
    RadioButton sandyButton;
    RadioButton siltyButton;
    RadioButton peatyButton;
    RadioButton chalkyButton;
    RadioButton loamyButton;
    RadioButton selectedButton;
    RadioButton imageSelection;
    RadioButton drawSelection;

    HBox hbox;
    int hboxHeight = 200;
    int hboxWidth = 200;
    Label gardenDimEntryWidth;
    TextField gardenDimTextWidth;
    Label gardenDimEntryHeight;
    TextField gardenDimTextHeight;
    Label claylabel;
    Label sandylabel;
    Label loamylabel;
    Label chalkylabel;
    Label peatylabel;
    Label siltylabel;
    Image clay;
    Image sandy;
    Image loamy;
    Image chalky;
    Image peaty;
    Image silty;
    ImageView claysoil;
    ImageView sandysoil;
    ImageView loamysoil;
    ImageView chalkysoil;
    ImageView peatysoil;
    ImageView siltysoil;
    final int X_OFFSET = 50;
    final int X_OFFSET2 = 465;
    final int X_OFFSET3 = 150;
    final int RADIO_X_OFFSET = 25;
    final int SOIL_ROW1_Y_OFFSET = 180;
    final int SOIL_ROW2_Y_OFFSET = 290;
    final int SOIL_ROW3_Y_OFFSET = 400;
    int logorelocatex = 200;
    int logorelocatey = 10;
    int gardenNamerelocatex = X_OFFSET;
    int gardenNamerelocatey = 120;
    int gardenNameFieldrelocatex = X_OFFSET;
    int gardenNameFieldrelocatey = 145;
    int gardenDimEntryWX = X_OFFSET;
    int gardenDimEntryWY = 190;
    int gardenDimTextWX = X_OFFSET;
    int gardenDimTextWY = 215;
    int gardenDimEntryHX = X_OFFSET;
    int gardenDimEntryHY = 260;
    int gardenDimTextHX = X_OFFSET;
    int gardenDimTextHY = 285;
    int imageSelectionX = 125;
    int imageSelectionY = 325;
    int drawSelectionX = X_OFFSET;
    int drawSelectionY = 325;
    int gardenShapeLabelRelocateX = X_OFFSET;
    int gardenShapeLabelRelocateY = 355;
    int Hboxx = X_OFFSET;
    int Hboxy = 380;
    int gardenImageSelectionX = X_OFFSET;
    int gardenImageSelectionY = 270;
    int gardenImageSelectionFieldX = 10;
    int gardenImageSelectionFieldY = 290;
    int soilSelectionrelocatex = 465;
    int soilSelectionrelocatey = 120;
    int soilxy = 75;
    int cLabelx = X_OFFSET2;
    int cLabely = SOIL_ROW1_Y_OFFSET - 25;
    int cButtonx = X_OFFSET2 - RADIO_X_OFFSET;
    int cButtony = SOIL_ROW1_Y_OFFSET;
    int cSoilx = X_OFFSET2;
    int cSoily = SOIL_ROW1_Y_OFFSET;
    int sLabelx = X_OFFSET2 + X_OFFSET3;
    int sLabely = SOIL_ROW1_Y_OFFSET - 25;
    int sButtonx = X_OFFSET2 + X_OFFSET3 - RADIO_X_OFFSET;
    int sButtony = SOIL_ROW1_Y_OFFSET;
    int sSoilx = X_OFFSET2 + X_OFFSET3;
    int sSoily = SOIL_ROW1_Y_OFFSET;
    int siLabelx = X_OFFSET2;
    int siLabely = SOIL_ROW2_Y_OFFSET - 25;
    int siButtonx = X_OFFSET2 - RADIO_X_OFFSET;
    int siButtony = SOIL_ROW2_Y_OFFSET;
    int siSoilx = X_OFFSET2;
    int siSoily = SOIL_ROW2_Y_OFFSET;
    int pLabelx = X_OFFSET2 + X_OFFSET3;
    int pLabely = SOIL_ROW2_Y_OFFSET - 25;
    int pButtonx = X_OFFSET2 + X_OFFSET3 - RADIO_X_OFFSET;
    int pButtony = SOIL_ROW2_Y_OFFSET;
    int pSoilx = X_OFFSET2 + X_OFFSET3;
    int pSoily = SOIL_ROW2_Y_OFFSET;
    int chLabelx = X_OFFSET2;
    int chLabely = SOIL_ROW3_Y_OFFSET - 25;
    int chButtonx = X_OFFSET2 - RADIO_X_OFFSET;
    int chButtony = SOIL_ROW3_Y_OFFSET;
    int chSoilx = X_OFFSET2;
    int chSoily = SOIL_ROW3_Y_OFFSET;
    int lLabelx = X_OFFSET2 + X_OFFSET3;
    int lLabely = SOIL_ROW3_Y_OFFSET - 25;
    int lButtonx = X_OFFSET2 + X_OFFSET3 - RADIO_X_OFFSET;
    int lButtony = SOIL_ROW3_Y_OFFSET;
    int lSoilx = X_OFFSET2 + X_OFFSET3;
    int lSoily = SOIL_ROW3_Y_OFFSET;
    int nextrelocatex = 320;
    int nextrelocatey = 530;

    final String textSize = "16";
    final String textColor = "#ffffff";
    final String textFieldColor = "#ffffff";
    final String backgroundColor = "#3a5a40";

    /**
     * Creates the questionnaire window and sets all the attributes and adds them to the pane
     *
     * @param length - window length
     * @param width  - window width
     */
    public QuestionnaireWindow(int length, int width) {
        super(length, width);
        setStyle();

        Image logo = new Image("images/gardenpartyLogo.png");
        ourLogo = new ImageView(logo);
        ourLogo.relocate(logorelocatex, logorelocatey);

        //Garden Name field
        gardenName = new Label("Garden Name: ");
        gardenName.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: 16px;"
                + " -fx-font-weight: bold;"
                + " -fx-margin: 50;");
        gardenName.relocate(gardenNamerelocatex, gardenNamerelocatey);
        gardenNameField = new TextField();
        gardenNameField.setStyle("-fx-background-color: " + textFieldColor + ";");
        gardenNameField.relocate(gardenNameFieldrelocatex, gardenNameFieldrelocatey);

        //Get garden dimension
        gardenDimEntryWidth = new Label("Enter garden width(ft): ");
        gardenDimEntryWidth.relocate(gardenDimEntryWX, gardenDimEntryWY);
        gardenDimEntryWidth.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        gardenDimTextWidth = new TextField();
        gardenDimTextWidth.setStyle("-fx-background-color: " + textFieldColor + ";");
        gardenDimTextWidth.relocate(gardenDimTextWX, gardenDimTextWY);
        gardenDimEntryHeight = new Label("Enter garden height(ft): ");
        gardenDimEntryHeight.relocate(gardenDimEntryHX, gardenDimEntryHY);
        gardenDimEntryHeight.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        gardenDimTextHeight = new TextField();
        gardenDimTextHeight.setStyle("-fx-background-color: " + textFieldColor + ";");
        gardenDimTextHeight.relocate(gardenDimTextHX, gardenDimTextHY);

        // Draw or upload
        ToggleGroup gardenSelection = new ToggleGroup();
        imageSelection = new RadioButton("Upload image");
        imageSelection.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        imageSelection.relocate(imageSelectionX, imageSelectionY);
        imageSelection.setToggleGroup(gardenSelection);
        drawSelection = new RadioButton("Draw");
        drawSelection.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        drawSelection.relocate(drawSelectionX, drawSelectionY);
        drawSelection.setToggleGroup(gardenSelection);

        //create dimensions 
        hbox = new HBox();
        hbox.setStyle("-fx-text-fill: brown;" + " -fx-border-color: black;" + " -fx-border-width: 5;");
        hbox.relocate(Hboxx, Hboxy);
        hbox.setMinHeight(hboxHeight);
        hbox.setMinWidth(hboxWidth);
        hbox.setMaxHeight(hboxHeight);
        hbox.setMaxWidth(hboxWidth);
        polygon = drawgarden();
        root = new Group();
        root.getChildren().add(polygon);
        root.getChildren().addAll(createControlAnchorsFor(polygon.getPoints()));
        hbox.getChildren().addAll(root);

        //Garden Shape Field (radio buttons)
        gardenShapeLabel = new Label("Create your garden shape here by dragging nodes.");
        gardenShapeLabel.relocate(gardenShapeLabelRelocateX, gardenShapeLabelRelocateY);
        gardenShapeLabel.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        gardenShape = new ToggleGroup();

        //Soil Selection
        soilSelection = new Label("Select your soil type: ");
        soilSelection.relocate(soilSelectionrelocatex, soilSelectionrelocatey);
        soilSelection.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        clayButton = new RadioButton("");
        claylabel = new Label("Clay");
        claylabel.relocate(cLabelx, cLabely);
        claylabel.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        clayButton.setLayoutX(cButtonx);
        clayButton.setLayoutY(cButtony);
        Image clay = new Image("images/soil-clay.jpg");
        claysoil = new ImageView(clay);
        claysoil.setFitHeight(soilxy);
        claysoil.setFitWidth(soilxy);
        claysoil.relocate(cSoilx, cSoily);

        sandyButton = new RadioButton("");
        sandylabel = new Label("Sandy");
        sandylabel.relocate(sLabelx, sLabely);
        sandylabel.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        sandyButton.setLayoutX(sButtonx);
        sandyButton.setLayoutY(sButtony);
        Image sandy = new Image("images/soil-sandy.jpg");
        sandysoil = new ImageView(sandy);
        sandysoil.setFitHeight(soilxy);
        sandysoil.setFitWidth(soilxy);
        sandysoil.relocate(sSoilx, sSoily);

        siltyButton = new RadioButton("");
        siltylabel = new Label("Silty");
        siltylabel.relocate(siLabelx, siLabely);
        siltylabel.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        siltyButton.setLayoutX(siButtonx);
        siltyButton.setLayoutY(siButtony);
        Image silty = new Image("images/soil-silty.jpg");
        siltysoil = new ImageView(silty);
        siltysoil.setFitHeight(soilxy);
        siltysoil.setFitWidth(soilxy);
        siltysoil.relocate(siSoilx, siSoily);

        peatyButton = new RadioButton("");
        peatylabel = new Label("Peaty");
        peatylabel.relocate(pLabelx, pLabely);
        peatylabel.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        peatyButton.setLayoutX(pButtonx);
        peatyButton.setLayoutY(pButtony);
        Image peaty = new Image("images/soil-peaty.jpg");
        peatysoil = new ImageView(peaty);
        peatysoil.setFitHeight(soilxy);
        peatysoil.setFitWidth(soilxy);
        peatysoil.relocate(pSoilx, pSoily);

        chalkyButton = new RadioButton("");
        chalkylabel = new Label("Chalky");
        chalkylabel.relocate(chLabelx, chLabely);
        chalkylabel.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        chalkyButton.setLayoutX(chButtonx);
        chalkyButton.setLayoutY(chButtony);
        Image chalky = new Image("images/soil-chalky.jpg");
        chalkysoil = new ImageView(chalky);
        chalkysoil.setFitHeight(soilxy);
        chalkysoil.setFitWidth(soilxy);
        chalkysoil.relocate(chSoilx, chSoily);

        loamyButton = new RadioButton("");
        loamylabel = new Label("Loamy");
        loamylabel.relocate(lLabelx, lLabely);
        loamylabel.setStyle("-fx-text-fill: " + textColor + ";"
                + " -fx-font-size: " + textSize + "px;"
                + " -fx-font-weight: bold;");
        loamyButton.setLayoutX(lButtonx);
        loamyButton.setLayoutY(lButtony);
        Image loamy = new Image("images/soil-loamy.jpg");
        loamysoil = new ImageView(loamy);
        loamysoil.setFitHeight(soilxy);
        loamysoil.setFitWidth(soilxy);
        loamysoil.relocate(lSoilx, lSoily);

        next = new Button("Save and Continue");
        next.relocate(nextrelocatex, nextrelocatey);
        this.getChildren().addAll(imageSelection, drawSelection, gardenName, gardenNameField, ourLogo, gardenShapeLabel, next, hbox, gardenDimTextWidth, gardenDimEntryWidth, gardenDimTextHeight, gardenDimEntryHeight, soilSelection, clayButton, peatyButton, chalkyButton, loamyButton, sandyButton, siltyButton, peatysoil, siltysoil, claylabel, sandylabel, loamylabel, chalkylabel, peatylabel, siltylabel, claysoil, sandysoil, loamysoil, chalkysoil);

    }


    /**
     * Sets the style of the window
     */
    @Override
    public void setStyle() {
        this.setStyle("-fx-background-color: " + backgroundColor + ";"
                + " -fx-margin: 50;");

    }

    /**
     * If the Garden name field is NULL, adds the input text to savedName
     *
     * @return savedName - the name of the Garden
     */
    public String getGardenName() {
        return gardenNameField.getText();
    }

    public String getGardenDimTextWidth() {
        return gardenDimTextWidth.getText();
    }
    public String getGardenDimTextHeight() {
        return gardenDimTextHeight.getText();
    }

    /**
     * Draws the garden based on the coordinates array and sets the drawings characteristics
     *
     * @return polygon
     */
    public Polygon drawgarden() {
        Polygon polygon = new Polygon();
        polygon.getPoints().setAll(coordinates);
        polygon.setStroke(Color.FORESTGREEN);
        polygon.setStrokeWidth(4);
        polygon.setStrokeLineCap(StrokeLineCap.ROUND);
        polygon.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
        return polygon;
    }

    /**
     * Adds anchors by going through a list
     *
     * @param points -  a list that allows listeners to track changes when they occur
     * @return anchors -  list that of anchors that allows listeners to track changes when dragged
     */
    private ObservableList<Anchor> createControlAnchorsFor(final ObservableList<Double> points) {
        ObservableList<Anchor> anchors = FXCollections.observableArrayList();

        for (int i = 0; i < points.size(); i += 2) {
            final int idx = i;
            DoubleProperty xProperty = new SimpleDoubleProperty(points.get(i));
            DoubleProperty yProperty = new SimpleDoubleProperty(points.get(i + 1));

            xProperty.addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number oldX, Number x) {
                    points.set(idx, (double) x);
                }
            });

            yProperty.addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number oldY, Number y) {
                    points.set(idx + 1, (double) y);
                }
            });

            anchors.add(new Anchor(Color.GOLD, xProperty, yProperty));
        }

        return anchors;
    }

    /**
     * Class used for the nodes when drawing the garden outline.
     */
    public class Anchor extends Circle {
        private final DoubleProperty x, y;

        /**
         * @param color - color encapsulates colors in the default sRGB color space
         * @param x     - double property that holds x coordinate
         * @param y     - double property that hold y coordinate
         */
        Anchor(Color color, DoubleProperty x, DoubleProperty y) {
            super(x.get(), y.get(), 10);
            setFill(color.deriveColor(1, 1, 1, 0.5));
            setStroke(color);
            setStrokeWidth(2);
            setStrokeType(StrokeType.OUTSIDE);
            this.x = x;
            this.y = y;
            x.bind(centerXProperty());
            y.bind(centerYProperty());
            enableDrag();
        }

        /**
         * Creates event handlers for mouse pressed, mouse released, mouse dragged, mouse entered and mouse exited
         */
        private void enableDrag() {
            final Delta dragDelta = new Delta();

            /**
             * creates a mouse event for mouse pressed and creates a delta
             */
            setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // record a delta distance for the drag and drop operation.
                    dragDelta.x = getCenterX() - mouseEvent.getX();
                    dragDelta.y = getCenterY() - mouseEvent.getY();
                    getScene().setCursor(Cursor.MOVE);
                }
            });

            /**
             * creates a mouse event for mouse released and sets the cursors to cursor hand
             */
            setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getScene().setCursor(Cursor.HAND);
                }
            });

            /**
             * creates a mouse event for mouse dragged and for the new drag it sets the new center of the anchor
             */
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    double newX = mouseEvent.getX() + dragDelta.x;
                    if (newX > 0 && newX < (hboxWidth - 30)) {
                        setCenterX(newX);

                    }
                    double newY = mouseEvent.getY() + dragDelta.y;
                    if (newY > 0 && newY < (hboxHeight - 30)) {
                        setCenterY(newY);

                    }
                }
            });

            /**
             * creates a mouse event for mouse entered and if the left button is not clicked sets the cursors to cursor hand
             */
            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.HAND);
                    }
                }
            });

            /**
             * creates a mouse event for mouse exited and if the left button is not clicked sets the cursors to cursor default
             */
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.DEFAULT);
                    }
                }
            });
        }
    }

    /**
     * sets the coordinates array
     */
    public void setCoordinates() {
        this.coordinates = coordinates;
    }

    /**
     * gets the coordinates array
     *
     * @return coordinates
     */
    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public String getSelectedType(ToggleGroup types) {

        selectedButton = (RadioButton) types.getSelectedToggle();
        String selectedType = new String();
        if (selectedButton.getText().equals("Clay")) {
            selectedType = "Clay";
        } else if (selectedButton.getText().equals("Sandy")) {
            selectedType = "Sandy";
        } else if (selectedButton.getText().equals("Silty")) {
            selectedType = "Silty";
        } else if (selectedButton.getText().equals("Peaty")) {
            selectedType = "Peaty";
        } else if (selectedButton.getText().equals("Chalky")) {
            selectedType = "Chalky";
        } else if (selectedButton.getText().equals("Loamy")) {
            selectedType = "Loamy";
        }
        return selectedType;
    }

}

