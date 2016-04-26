import java.awt.image.BufferedImage;

public class Start_FilterScreenShot extends Start {

	private Start_FilterScreenShot() {
		main = this;

	}

	@Override
	public BufferedImage getImage() {
		return sl.getColorlessScreenShot();
	}

	public static void main(String[] args) {
		new Start_FilterScreenShot();
	}

}
