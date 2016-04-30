package com.impu.used;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.impu.swing.Start;
import com.sun.istack.internal.NotNull;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageTools {

	public static WritableImage resizeImage(WritableImage image, double width, double height) {
		BufferedImage bimg = new BufferedImage((int) image.getWidth(), (int) image.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		SwingFXUtils.fromFXImage(image, bimg);
		bimg = ImageTools.resizeImage(bimg, (int) width, (int) height);
		return SwingFXUtils.toFXImage(bimg, null);
	}

	public static WritableImage convertToWritableImage(@NotNull Image image) {
		PixelReader pixelReader = image.getPixelReader();
		WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		PixelWriter pixelWriter = wImage.getPixelWriter();
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				pixelWriter.setColor(x, y, pixelReader.getColor(x, y));
			}
		}
		return wImage;
	}

	public static WritableImage getBitmapImage(WritableImage image, double cutOff) {
		if (image == null) {
			return null;
		}
		WritableImage bitmapImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		PixelWriter pw = bitmapImage.getPixelWriter();
		PixelReader pr = image.getPixelReader();
		for (int x = 0; x < bitmapImage.getWidth(); x++) {
			for (int y = 0; y < bitmapImage.getHeight(); y++) {
				Color c = pr.getColor(x, y);
				// double brightness = c.getBrightness();
				double brightness = (double) (0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue());
				pw.setColor(x, y, brightness >= cutOff ? Color.WHITE : Color.BLACK);
			}
		}
		return bitmapImage;
	}

	// TODO: complete method
	public static WritableImage getGreyImage(WritableImage image) {
		if (image == null) {
			return null;
		}
		WritableImage greyImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		// PixelWriter pw = greyImage.getPixelWriter();

		return greyImage;
	}

	public static void save(WritableImage image, File file) {
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
			System.out.println("Image saved under " + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
		g.dispose();

		return resizedImage;
	}

	@Deprecated
	public static BufferedImage getGreyImage(BufferedImage img) {
		if (img == null) {
			return null;
		}
		BufferedImage greyImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g = (Graphics2D) greyImage.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return greyImage;
	}

	@Deprecated
	public static BufferedImage getBitmapImage(BufferedImage img, int cutOff) {
		if (img == null) {
			return null;
		}
		BufferedImage bitmapImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < bitmapImage.getWidth(); x++) {
			for (int y = 0; y < bitmapImage.getHeight(); y++) {
				java.awt.Color c = new java.awt.Color(img.getRGB(x, y));
				int brightness = (int) (0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue());
				bitmapImage.setRGB(x, y,
						brightness >= cutOff ? java.awt.Color.WHITE.getRGB() : java.awt.Color.BLACK.getRGB());
			}
		}
		return bitmapImage;
	}

	@Deprecated
	public static void saveImage(BufferedImage image, int width, int height) {
		File file = Start.getInstance().getFile();
		if (file != null && !file.getAbsolutePath().endsWith(".png")) {
			file = new File(file.getAbsolutePath() + ".png");
		}
		image = resizeImage(image, width, height);
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
