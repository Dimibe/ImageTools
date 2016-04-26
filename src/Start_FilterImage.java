import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Start_FilterImage implements Runnable {

	private static Start_FilterImage main;
	private View view;

	private Thread t;
	private BufferedImage img;

	private Start_FilterImage() {
		view = new View();
		img = loadImage();
		t = new Thread(this);
		t.start();
	}

	private BufferedImage loadImage() {
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(null);
		File file = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = jfc.getSelectedFile();
		}
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(img.getWidth());
		return img;
	}

	@Override
	public void run() {
		while (true) {
			view.setImage(ImageTools.getBitmapImage(img));
			view.update();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Start_FilterImage.getInstance();
	}

	public static Start_FilterImage getInstance() {
		if (main == null) {
			main = new Start_FilterImage();
		}
		return main;
	}

}
