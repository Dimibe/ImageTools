package com.impu.filter;

import com.impu.fx.Controller;

import javafx.application.Platform;
import javafx.scene.layout.VBox;

public abstract class FilterImpl implements Filter {

	private String name;

	public FilterImpl(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((FilterImpl) obj).name);
	}

	public abstract VBox getOptionBox();

	protected final void imageChanged() {
		Platform.runLater(() -> Controller.getInstance().applyFilterOnImage());
	}

}
