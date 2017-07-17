
import java.awt.EventQueue;

public class Driver {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIpanel.getInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
