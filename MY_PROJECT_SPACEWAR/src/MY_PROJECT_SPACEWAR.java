
import java.awt.*;
import javax.swing.JFrame;

public class MY_PROJECT_SPACEWAR {
    
	public static void main(String[] args) {
		JFrame container = new JFrame("SpaceWar");

		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                container.getContentPane().setBackground(Color.BLACK);
                
		container.setContentPane(new SpacePanel());		
		container.setResizable(false);
		container.pack();
		container.setLocationRelativeTo(null);
		container.setVisible(true);
	}
}