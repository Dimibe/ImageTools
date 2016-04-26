import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class View extends Canvas {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu settingsMenu;
	private JMenuItem saveItem;

	private BufferedImage image;

	public View() {
		setSize(Const.SIZE);
		setupJFrame();
	}

	public void update() {
		createBufferStrategy();
	}

	private void createBufferStrategy() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		draw(g);
		g.dispose();
		bs.show();
	}

	private void draw(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	private void setupJFrame() {
		frame = new JFrame(Const.TITLE);
		frame.setSize(Const.SIZE);
		frame.setUndecorated(Const.UNDECORATED);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(this);

		menuBar = new JMenuBar();

		fileMenu = new JMenu("File");
		saveItem = new JMenuItem("Save As ...");
		fileMenu.add(saveItem);
		
		menuBar.add(fileMenu);

		settingsMenu = new JMenu("Settings");
		menuBar.add(settingsMenu);

		frame.setJMenuBar(menuBar);
		frame.setVisible(true);

	}

	public void setImage(BufferedImage img) {
		this.image = img;
	}

}
