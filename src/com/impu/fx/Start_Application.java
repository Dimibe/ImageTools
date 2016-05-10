package com.impu.fx;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;

public class Start_Application extends Application {

	private static Start_Application application;

	private FavasGui gui;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Start_Application.application = this;
		gui = new FavasGui(primaryStage);
		gui.create();
		gui.show();
		
		// load sample image on startup
		Controller.getInstance().loadImage(new File("./res/Penguins.jpg"));
	}

	public static Start_Application getApplication() {
		return application;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
