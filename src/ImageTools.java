import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTools {

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

	public static BufferedImage getBitmapImage(BufferedImage img, int cutOff) {
		if (img == null) {
			return null;
		}
		BufferedImage bitmapImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < bitmapImage.getWidth(); x++) {
			for (int y = 0; y < bitmapImage.getHeight(); y++) {
				Color c = new Color(img.getRGB(x, y));
				int brightness = (int) (0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue());
				bitmapImage.setRGB(x, y, brightness >= cutOff ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
			}

		}
		return bitmapImage;
	}

	public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
		g.dispose();

		return resizedImage;
	}

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
