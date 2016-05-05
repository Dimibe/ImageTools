package com.impu.fx;

import java.io.File;

import com.impu.exception.NoInstanceException;
import com.impu.filter.BitmapFilter;
import com.impu.filter.ColorSwapFilter;
import com.impu.filter.DefaultFilter;
import com.impu.filter.Filter;
import com.impu.filter.InverseFilter;
import com.impu.filter.RainbowFilter;

import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FavasGui {

	private static FavasGui gui;

	private Stage primaryStage;
	private ImageView view;

	public FavasGui(Stage primaryStage) {
		gui = this;
		this.primaryStage = primaryStage;
	}

	public void create() {
		primaryStage.setTitle("Fivas");
		primaryStage.setOnCloseRequest(e -> System.exit(1));

		Scene scene = new Scene(new VBox(), 800, 600);
		scene.widthProperty().addListener((o, ov, nv) -> Controller.getInstance().resizeImage(getWidth(), getHeight()));
		scene.heightProperty()
				.addListener((o, ov, nv) -> Controller.getInstance().resizeImage(getWidth(), getHeight()));

		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		Menu menuFiles = new Menu("File");

		MenuItem openItem = new MenuItem("Open Image");
		openItem.setOnAction(
				e -> Controller.getInstance().loadWritableImage(loadFile(Controller.getInstance().getFile())));
		menuFiles.getItems().add(openItem);

		MenuItem saveItem = new MenuItem("Save Image");
		saveItem.setOnAction(
				e -> Controller.getInstance().saveWritableImage(saveFile(Controller.getInstance().getFile())));
		menuFiles.getItems().add(saveItem);

		Menu menuEdit = new Menu("Edit");
		Menu subMenuFilter = new Menu("Set Filter");

		addFilterToMenu(subMenuFilter, "No Filter", new DefaultFilter());
		addFilterToMenu(subMenuFilter, "Bitmap Filter", new BitmapFilter());
		addFilterToMenu(subMenuFilter, "Color Swap Filter", new ColorSwapFilter());
		addFilterToMenu(subMenuFilter, "Inverse Filter", new InverseFilter());
		addFilterToMenu(subMenuFilter, "Rainbow Filter", new RainbowFilter());

		((CheckMenuItem) subMenuFilter.getItems().get(0)).setSelected(true);

		menuEdit.getItems().add(subMenuFilter);

		Menu menuView = new Menu("View");

		// MenuItem cutOffItem = new MenuItem("Cut Off");
		// cutOffItem.setOnAction(e -> Controller.getInstance().loadCutOff());
		// menuView.getItems().add(cutOffItem);

		menuBar.getMenus().addAll(menuFiles, menuEdit, menuView);
		((VBox) scene.getRoot()).getChildren().add(menuBar);

		view = new ImageView();
		view.fitWidthProperty().bind(scene.widthProperty());
		view.fitHeightProperty().bind(scene.heightProperty());
		((VBox) scene.getRoot()).getChildren().add(view);

		primaryStage.setScene(scene);

	}

	public void show() {
		primaryStage.show();
	}

	public File saveFile(File currentFile) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(currentFile == null ? new File("res") : currentFile.getParentFile());
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (*.png)", "*.png"));
		fc.setTitle("Save Image");
		return fc.showSaveDialog(primaryStage);
	}

	public File loadFile(File currentFile) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(currentFile == null ? new File("res") : currentFile.getParentFile());
		fc.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("Images (*.png, *.jpeg, *.jpg)", "*.png", "*.jpeg", "*.jpg"));
		fc.setTitle("Open Image");
		return fc.showOpenDialog(primaryStage);
	}

	private void addFilterToMenu(Menu menu, String name, Filter filter) {
		CheckMenuItem filterItem = new CheckMenuItem(name);
		filterItem.setOnAction((e) -> {
			if (filterItem.isSelected()) {
				Controller.getInstance().setFilter(filter);
			} else {
				Controller.getInstance().removeFilter(filter);
			}
		});
		menu.getItems().add(filterItem);
	}

	public void setImageToView() {
		WritableImage image = Controller.getInstance().getImage();
		if (image != null) {
			view.setImage(image);
		}
	}

	public double getHeight() {
		return primaryStage.getHeight();
	}

	public double getWidth() {
		return primaryStage.getWidth();
	}

	public static FavasGui getInstance() {
		if (gui == null) {
			throw new NoInstanceException();
		}
		return gui;
	}

}
