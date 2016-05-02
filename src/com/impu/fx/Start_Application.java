package com.impu.fx;

import java.io.File;
import java.util.Optional;

import com.impu.filter.BitmapFilter;
import com.impu.filter.DefaultFilter;
import com.impu.filter.Filter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Start_Application extends Application implements Runnable {

	private static Start_Application application;

	private Stage primaryStage;
	private Canvas canvas;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Start_Application.application = this;
		this.primaryStage = primaryStage;
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

		menuEdit.getItems().add(subMenuFilter);

		Menu menuView = new Menu("View");

		MenuItem cutOffItem = new MenuItem("Cut Off");
		cutOffItem.setOnAction(e -> Controller.getInstance().loadCutOff());
		menuView.getItems().add(cutOffItem);

		menuBar.getMenus().addAll(menuFiles, menuEdit, menuView);
		((VBox) scene.getRoot()).getChildren().add(menuBar);

		canvas = new Canvas();
		canvas.widthProperty().bind(scene.widthProperty());
		canvas.heightProperty().bind(scene.heightProperty());
		((VBox) scene.getRoot()).getChildren().add(canvas);

		primaryStage.setScene(scene);
		primaryStage.show();

		new Thread(this).start();
	}

	private void addFilterToMenu(Menu menu, String name, Filter filter) {
		MenuItem filterItem = new MenuItem(name);
		filterItem.setOnAction((e) -> Controller.getInstance().setFilter(filter));
		menu.getItems().add(filterItem);
	}

	private void draw(GraphicsContext g) {
		WritableImage image = Controller.getInstance().getImage();
		if (image != null) {
			g.drawImage(image, 0, 0, image.getWidth(), image.getHeight());
		}
	}

	@Override
	public void run() {
		while (true) {

			draw(canvas.getGraphicsContext2D());

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public File saveFile(File currentFile) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(currentFile == null ? null : currentFile.getParentFile());
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (*.png)", "*.png"));
		fc.setTitle("Save Image");
		return fc.showSaveDialog(primaryStage);
	}

	public File loadFile(File currentFile) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(currentFile == null ? null : currentFile.getParentFile());
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (*.png)", "*.png", "*.jpeg", "*.jpg"));
		fc.setTitle("Open Image");
		return fc.showOpenDialog(primaryStage);
	}

	public double newCutOff(double currentCutOff) {
		TextInputDialog dialog = new TextInputDialog("" + currentCutOff);
		dialog.initOwner(primaryStage);
		dialog.setHeaderText("CutOff");
		Optional<String> result = dialog.showAndWait();
		return Double.parseDouble(result.get());
	}

	public double getHeight() {
		return primaryStage.getHeight();
	}

	public double getWidth() {
		return primaryStage.getWidth();
	}

	public static Start_Application getApplication() {
		return application;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
