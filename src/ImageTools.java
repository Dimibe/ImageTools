import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageTools {

	public static BufferedImage getGreyImage(BufferedImage img) {
		BufferedImage greyImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g = (Graphics2D) greyImage.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return greyImage;
	}

	public static BufferedImage getBitmapImage(BufferedImage img) {
		BufferedImage bitmapImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < bitmapImage.getWidth(); x++) {
			for (int y = 0; y < bitmapImage.getHeight(); y++) {
				Color c = new Color(img.getRGB(x, y));
				int brightness = (int) (0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue());
				bitmapImage.setRGB(x, y, brightness > Const.CUTOFF ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
			}

		}
		return bitmapImage;
	}

}
