import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class ScreenListener {

	private Robot robot;

	public ScreenListener() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getScreenShot() {
		return robot.createScreenCapture(Const.SCREEN_CAPTURE_SIZE);

	}

	public BufferedImage getGreyScreenShot() {
		BufferedImage screenShot = getScreenShot();
		return ImageTools.getGreyImage(screenShot);
	}

	public BufferedImage getColorlessScreenShot() {
		BufferedImage screenShot = getScreenShot();
		return ImageTools.getBitmapImage(screenShot, Start.getInstance().getCutOff());
	}
}
