import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayCraps extends JFrame {

	private JPanel mainPanel;
	private JPanel labelAndTextPanel;
	private JPanel buttonPanel;
	private JPanel rollingDicePanel;
	
	private JLabel die1Label;
	private JLabel die2Label;
	private JLabel dieSumLabel;
	private JLabel pointLabel;
	private JLabel statusLabel;
	
	private JTextField die1TextField;
	private JTextField die2TextField;
	private JTextField dieSumTextField;
	private JTextField pointTextField;
	
	private JLabel imageLabel;
	
	private JButton rollButton;
	
	//Craps craps;
	
	public PlayCraps(String title) {
		super(title);
		
		// main panel to hold everything
		mainPanel = new JPanel(new BorderLayout(0, 10));
		
		// set 3 panels to hold the labels and text
		// the button 
		// and the image of rolling dice
		labelAndTextPanel = new JPanel(new GridLayout(2, 4));
		buttonPanel = new JPanel(new BorderLayout());
		rollingDicePanel = new JPanel(new FlowLayout());

		// create JLabels and JTextFields and add to labelAndText JPanel
		// text fields will be only 2 columns wide. A die only has one digit
		// text fields will not be editable. User don't need to type anything 
		die1Label = new JLabel("Die One: ");
		labelAndTextPanel.add(die1Label);

		die1TextField = new JTextField(2); 
		die1TextField.setEditable(false);
		labelAndTextPanel.add(die1TextField);
		
		
		die2Label = new JLabel("Die Two: ");
		labelAndTextPanel.add(die2Label);
		
		die2TextField = new JTextField(2);
		die2TextField.setEditable(false);
		labelAndTextPanel.add(die2TextField);
		
		
		dieSumLabel = new JLabel("Dice Sum: ");
		labelAndTextPanel.add(dieSumLabel);
		
		dieSumTextField = new JTextField(2);
		dieSumTextField.setEditable(false);
		labelAndTextPanel.add(dieSumTextField);

		
		pointLabel = new JLabel("Point: ");
		labelAndTextPanel.add(pointLabel);
		
		pointTextField = new JTextField(2);
		pointTextField.setEditable(false);
		labelAndTextPanel.add(pointTextField);
		
		// add labelsAndTextPanel to mainPanel NORTH
		mainPanel.add(labelAndTextPanel, BorderLayout.NORTH);
		
		// create the craps object start rolling the dice
		//craps = new Craps();
		
		// create the label to display the game result
		statusLabel = new JLabel();
		
		// create rollButton and add to buttonPanel
		rollButton = new JButton("Roll");
		rollButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Craps craps = new Craps();
						String status = craps.play();
						die1TextField.setText(String.valueOf(craps.getDie1()));
						die2TextField.setText(String.valueOf(craps.getDie2()));
						
						dieSumTextField.setText(String.valueOf(craps.getSumOfDice()));
						pointTextField.setText(String.valueOf(craps.getMyPoint()));
						
						statusLabel.setText(status);

					}
				});
		buttonPanel.add(rollButton, BorderLayout.NORTH);
		buttonPanel.add(statusLabel, BorderLayout.CENTER);
		
		
		// add buttonPanel to mainPanel CENTER
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		
		
		// create imageLabel and add to rollingDicePanel
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("rollingDice.gif"));
		imageLabel = new JLabel();
		imageLabel.setIcon(imageIcon);
		rollingDicePanel.add(imageLabel);
		
		// add rollingDice to mainPanel SOUTH
		mainPanel.add(rollingDicePanel, BorderLayout.SOUTH);
		
		
		
		// add mainPanel to JFrame
		add(mainPanel);
		
	}
}
