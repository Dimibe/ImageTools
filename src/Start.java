import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public abstract class Start implements Runnable {

	protected static Start main;

	protected ScreenListener sl;
	protected View view;

	protected BufferedImage img;
	protected Dimension originalSize;
	protected File file;
	protected Thread t;

	protected int cutOff = 120;

	protected Start() {
		sl = new ScreenListener();
		view = new View();
		t = new Thread(this);
		t.start();
	}

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

	public File getFile() {
		JFileChooser jfc = new JFileChooser(file);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile();
		}
		return null;
	}

	@Override
	public void run() {
		while (true) {

			view.setImage(getImage());
			view.update();

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public abstract BufferedImage getImage();

	public Dimension getOriginalSize() {
		return originalSize;
	}

	public int getCutOff() {
		return cutOff;
	}

	public static Start getInstance() {
		return main;
	}

	public void setCutOff(String cutOff) {
		this.cutOff = Integer.parseInt(cutOff);
	}

}
