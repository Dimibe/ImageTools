<<<<<<< Upstream, based on branch 'master' of https://github.com/Dimibe/ImageTools.git
package com.impu.fx;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import com.impu.swing.ScreenListener;
import com.impu.used.ImageTools;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Controller {

	private static Controller controller;

	private ScreenListener sl;

	private WritableImage img;
	private Dimension originalSize;
	private File file;
	private double cutOff = (120.0 / 255.0);

	private Controller() {
		sl = new ScreenListener();

		// Image image = new Image(getFile().toURI().toString());
		BufferedImage image = null;
		try {
			image = ImageIO.read(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

		image = ImageTools.resizeImage(image, image.getWidth(), image.getHeight());

		img = ImageTools.convertToWritableImage(image);
		System.out.println(img.getHeight());
	}

	public Dimension getOriginalSize() {
		return originalSize;
	}

	public double getCutOff() {
		return cutOff;
	}

	public void setCutOff(String cutOff) {
		this.cutOff = Integer.parseInt(cutOff);
	}

	// TODO: refactor
	public WritableImage getImage() {
//		return ImageTools.getBitmapImage(img, getCutOff());
		return img;
	}

	// TODO: refactor
	protected BufferedImage loadImage() {
		file = getFile();
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		originalSize = new Dimension(img.getWidth(), img.getHeight());
		return img;
	}

	// TODO: refactor
	public File getFile() {
		JFileChooser jfc = new JFileChooser(file);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile();
		}
		return null;
	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}
}
=======
package com.impu.fx;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import com.impu.swing.ScreenListener;
import com.impu.used.ImageTools;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Controller {

	private static Controller controller;

	private ScreenListener sl;

	private WritableImage img;
	private Dimension originalSize;
	private File file;
	private double cutOff = 0.5;

	private Controller() {
		sl = new ScreenListener();

		img = ImageTools.convertToWritableImage(new Image(getFile().toURI().toString(), 800, 1120, false, true));
	}

	public Dimension getOriginalSize() {
		return originalSize;
	}

	public double getCutOff() {
		return cutOff;
	}

	public void setCutOff(String cutOff) {
		this.cutOff = Integer.parseInt(cutOff);
	}

	// TODO: refactor
	public WritableImage getImage() {
		return ImageTools.getBitmapImage(img, getCutOff());
	}

	// TODO: refactor
	protected BufferedImage loadImage() {
		file = getFile();
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		originalSize = new Dimension(img.getWidth(), img.getHeight());
		return img;
	}

	// TODO: refactor
	public File getFile() {
		JFileChooser jfc = new JFileChooser(file);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile();
		}
		return null;
	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}
}
>>>>>>> 5e05e44 Cleaning up 
