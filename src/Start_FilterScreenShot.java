
public class Start_FilterScreenShot implements Runnable {

	private static Start_FilterScreenShot main;
	
	private View view;
	private ScreenListener sl;


	private Thread t;

	private Start_FilterScreenShot() {
		sl = new ScreenListener();
		view = new View();
		t = new Thread(this);
		t.start();

	}

	@Override
	public void run() {
		while (true) {
			view.setImage(sl.getColorlessScreenShot());
			view.update();

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Start_FilterScreenShot.getInstance();
	}

	public static Start_FilterScreenShot getInstance() {
		if (main == null) {
			main = new Start_FilterScreenShot();
		}
		return main;
	}

}
