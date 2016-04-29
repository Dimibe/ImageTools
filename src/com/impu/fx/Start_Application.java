package com.impu.fx;

import java.io.File;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
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

		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		Menu menuFiles = new Menu("File");

		MenuItem openItem = new MenuItem("Open Image");
		openItem.setOnAction(
				e -> Controller.getInstance().loadWritableImage(loadFile(Controller.getInstance().getFile())));
		menuFiles.getItems().add(openItem);

		MenuItem saveItem = new MenuItem("Save Image");
		saveItem.setOnAction(e -> System.out.println("test")); // TODO
		menuFiles.getItems().add(saveItem);

		Menu menuEdit = new Menu("Edit");
		Menu subMenuShowView = new Menu("show View");

		MenuItem cutOffItem = new MenuItem("Cut Off");
		cutOffItem.setOnAction(e -> Controller.getInstance().loadCutOff());
		subMenuShowView.getItems().add(cutOffItem);

		menuEdit.getItems().add(subMenuShowView);

		menuBar.getMenus().addAll(menuFiles, menuEdit);
		((VBox) scene.getRoot()).getChildren().add(menuBar);

		canvas = new Canvas();
		canvas.widthProperty().bind(scene.widthProperty());
		canvas.heightProperty().bind(scene.heightProperty());
		((VBox) scene.getRoot()).getChildren().add(canvas);

		primaryStage.setScene(scene);
		primaryStage.show();

		new Thread(this).start();
	}

	private void draw(GraphicsContext g) {
		WritableImage image = Controller.getInstance().getImage();
		if (image != null) {
			g.drawImage(image, 0, 0, getWidth(), getHeight());
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

	public File loadFile(File currentFile) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(currentFile == null ? null : currentFile.getParentFile());
		fc.setTitle("Open Image");
		return fc.showOpenDialog(primaryStage);
	}

	public double newCutOff(double currentCutOff) {
		TextInputDialog dialog = new TextInputDialog("" + currentCutOff);
		dialog.setHeaderText("CutOff");
		Optional<String> result = dialog.showAndWait();
		return Double.parseDouble(result.get());
	}

	private double getHeight() {
		return primaryStage.getHeight();
	}

	private double getWidth() {
		return primaryStage.getWidth();
	}

	public static Start_Application getApplication() {
		return application;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
