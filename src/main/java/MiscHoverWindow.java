import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Miscellaneous Hover Window class, this is used when you hover over a plant in the garden
 * @author christiesandberg
 *
 */
public class MiscHoverWindow extends PopupWindow{
	
	StackPane imagePane;
	ImageView image;
	Label miscName;
	final int IMAGE_X = 50;
	final int IMAGE_Y = 50;
	final int MISC_NAME_X = 50;
	final int MISC_NAME_Y = 160;
	final int REMOVE_MISC_X = 50;
	final int REMOVE_MISC_Y = 200;

	/**
	 * Constructor for a MiscHoverWindow object, creates an ImageView, Label, and Button and adds them to the Window.
	 * @param length
	 * @param width
	 * @param message
	 * @param misc
	 * @param rootMisc
	 */
	public MiscHoverWindow(int length, int width, String message, MiscPlaceable misc, MiscPlaceable rootMisc) {
		super(length, width, message);

		imagePane = new StackPane();
		image = misc.getMiscImage();
		imagePane.getChildren().addAll(image);
		imagePane.relocate(IMAGE_X,IMAGE_Y);
		imagePane.setStyle("-fx-background-color: transparent;"
				+ " -fx-background-radius: 5;"
				+ " -fx-border-color: #502525;"
				+ " -fx-border-width: 3;"
				+ " -fx-border-radius: 3;");


		miscName = new Label(misc.getName());
		miscName.relocate(MISC_NAME_X,MISC_NAME_Y);
		miscName.setStyle("-fx-font-size: 16px;"
		+ " -fx-font-weight: bold;");


		this.getChildren().addAll(imagePane, miscName);
	}

}
