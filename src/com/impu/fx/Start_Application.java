<<<<<<< Upstream, based on branch 'master' of https://github.com/Dimibe/ImageTools.git
package com.impu.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Start_Application extends Application implements Runnable {

	private Stage primaryStage;
	GraphicsContext gc;
	Canvas canvas;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Fivas");
		
		StackPane root = new StackPane();
		canvas = new Canvas();
		canvas.widthProperty().bind(root.widthProperty());
		canvas.heightProperty().bind(root.heightProperty());
		gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		new Thread(this).start();
	}

	private void drawShapes(GraphicsContext g) {
		g.drawImage(Controller.getInstance().getImage(), 0, 0, getWidth(), getHeight());
	}

	private double getHeight() {
		return primaryStage.getHeight();
	}

	private double getWidth() {
		return primaryStage.getWidth();
	}

	@Override
	public void run() {
		while (true) {

			drawShapes(canvas.getGraphicsContext2D());

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
=======
package com.impu.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Start_Application extends Application implements Runnable {

	private Stage primaryStage;
	GraphicsContext gc;
	Canvas canvas;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Fivas");
		StackPane root = new StackPane();
		canvas = new Canvas();
		canvas.widthProperty().bind(root.widthProperty());
		canvas.heightProperty().bind(root.heightProperty());
		gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		new Thread(this).start();
	}

	private void drawShapes(GraphicsContext g) {

		WritableImage image = Controller.getInstance().getImage();
		g.drawImage(image, 0, 0, getWidth(), getHeight());
	}

	private double getHeight() {
		return primaryStage.getHeight();
	}

	private double getWidth() {
		return primaryStage.getWidth();
	}

	@Override
	public void run() {
		while (true) {

			drawShapes(canvas.getGraphicsContext2D());

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
>>>>>>> 5e05e44 Cleaning up 
