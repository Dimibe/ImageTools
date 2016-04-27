package com.impu.swing;
import java.awt.image.BufferedImage;

import com.impu.used.ImageTools;

public class Start_FilterImage extends Start {

	private Start_FilterImage() {
		img = loadImage();

		main = this;
	}

	@Override
	public BufferedImage getImage() {
		return ImageTools.getBitmapImage(img, cutOff);
	}

	public static void main(String[] args) {
		new Start_FilterImage();
	}

}
