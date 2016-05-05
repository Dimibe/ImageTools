package com.impu.filter;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class FilterImpl implements Filter {

	private String name;

	public FilterImpl(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((FilterImpl) obj).name);
	}

	public abstract VBox getOptionGui(Stage primaryStage);

}
