package com.impu.fx;

import java.io.File;

import com.impu.exception.NoInstanceException;
import com.impu.filter.BitmapFilter;
import com.impu.filter.ColorSwapFilter;
import com.impu.filter.DefaultFilter;
import com.impu.filter.FilterImpl;
import com.impu.filter.InverseFilter;
import com.impu.filter.RainbowFilter;
import com.impu.used.Const;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FavasGui {

	private static FavasGui gui;

	private Stage primaryStage;
	private BorderPane borderPane;
	private ImageView view;
	private MenuBar menuBar;
	private VBox optionsMenu;

	public FavasGui(Stage primaryStage) {
		gui = this;
		this.primaryStage = primaryStage;
	}

	public void create() {
		primaryStage.setTitle(Const.TITLE);
		primaryStage.setOnCloseRequest(e -> System.exit(1));
		primaryStage.setMinWidth(Const.MIN_WIDTH);
		primaryStage.setMinHeight(Const.MIN_HEIGHT);

		borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, Const.INIT_WIDTH, Const.INIT_HEIGHT);

		createMenuBar();
		createOptionsMenu();

		view = new ImageView();
		view.fitWidthProperty().bind(scene.widthProperty().subtract(optionsMenu.widthProperty()));
		view.fitHeightProperty().bind(scene.heightProperty().subtract(menuBar.heightProperty()));

		borderPane.setTop(menuBar);
		borderPane.setCenter(view);
		borderPane.setRight(optionsMenu);

		scene.widthProperty().addListener((o, ov, nv) -> Controller.getInstance().resizeImage(getWidth(), getHeight()));
		scene.heightProperty()
				.addListener((o, ov, nv) -> Controller.getInstance().resizeImage(getWidth(), getHeight()));

		primaryStage.setScene(scene);
	}

	private MenuBar createMenuBar() {
		menuBar = new MenuBar();
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

		menuBar.getMenus().addAll(menuFiles, menuEdit, menuView);

		return menuBar;
	}

	public void show() {
		primaryStage.show();
	}

	public void createOptionsMenu() {
		optionsMenu = new VBox();
		optionsMenu.setPrefWidth(Const.OPTIONS_MENU_WIDTH);
		optionsMenu.setPrefHeight(Const.INIT_HEIGHT);
	}

	public void disposeOptionsMenu() {
		optionsMenu.setPrefWidth(0);
	}

	public void updateOptionsMenu() {
		Platform.runLater(() -> {
			if (optionsMenu == null || optionsMenu.getChildren() == null) {
				return;
			}
			optionsMenu.getChildren().clear();
			for (FilterImpl f : Controller.getInstance().getActiveFilters()) {
				VBox box = f.getOptionBox();
				if (box == null) {
					continue;
				}
				optionsMenu.getChildren().add(box);
			}
		});
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

	private void addFilterToMenu(Menu menu, String name, FilterImpl filter) {
		CheckMenuItem filterItem = new CheckMenuItem(name);
		filterItem.setOnAction((e) -> {
			if (filterItem.isSelected()) {
				Controller.getInstance().setFilter(filter);
			} else {
				Controller.getInstance().removeFilter(filter);
			}
			Platform.runLater(() -> updateOptionsMenu());
		});
		menu.getItems().add(filterItem);
	}

	public void setImageToView(Image image) {
		if (image != null) {
			view.setImage(image);
		}
	}

	public double getHeight() {
		return view.getFitHeight();
	}

	public double getWidth() {
		return view.getFitWidth();
	}

	public static FavasGui getInstance() {
		if (gui == null) {
			throw new NoInstanceException();
		}
		return gui;
	}

}
