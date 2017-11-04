import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayCraps extends JFrame {

	private JPanel mainPanel;
	private JPanel labelAndTextPanel;
	private JPanel labelAndTextPanelRow1;
	private JPanel labelAndTextPanelRow2;
	private JPanel buttonAndResultsPanel;
	private JPanel buttonPanel;
	private JPanel resultsPanel;
	private JPanel rollingDicePanel;
	
	private JLabel die1Label;
	private JLabel die2Label;
	private JLabel dieSumLabel;
	private JLabel pointLabel;
	
	private JTextField die1TextField;
	private JTextField die2TextField;
	private JTextField dieSumTextField;
	private JTextField pointTextField;
	
	private JLabel imageLabel;
	
	private JButton rollButton;
	
	private JTextArea resultsText;
	private JScrollPane resultsScroll;
	
	public PlayCraps(String title) {
		super(title);
		
		// main panel to hold everything
		mainPanel = new JPanel(new BorderLayout(0, 10));
		
		/* 
		 set 3 panels to hold the labels and text
		 the button and results text
		 and the image of rolling dice 
		*/
		labelAndTextPanel = new JPanel(new GridLayout(2, 1));
		buttonAndResultsPanel = new JPanel(new FlowLayout());
		rollingDicePanel = new JPanel(new FlowLayout());
		
		/* 
		 text panels that will hold the JLabels and JTextFields
		 each row of components is in a separate JPanel
		 then both panels are added to one panel,
		 which is then added to the NORTH position of the main panel
		*/
		labelAndTextPanelRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 5));
		labelAndTextPanelRow2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 5));

		/* the button and the display of the results are added to to a grid layout panel
		 the button and the results are separated in their own panels.
		 each panel is the added to the main gridlayout buttonAndResults JPanel,
		 which is then added to the main JPanel CENTER position
		*/
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		resultsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		
		/* 
		 create JLabels and JTextFields and add to labelAndText JPanel
		 text fields will be only 2 columns wide. A die only has one digit
		 text fields will not be editable. User don't need to type anything 
		*/
		die1Label = new JLabel("Die One:  ");
		labelAndTextPanelRow1.add(die1Label);

		die1TextField = new JTextField(2); 
		die1TextField.setEditable(false);
		labelAndTextPanelRow1.add(die1TextField);
		
		die2Label = new JLabel("Die Two:");
		labelAndTextPanelRow1.add(die2Label);
		
		die2TextField = new JTextField(2);
		die2TextField.setEditable(false);
		labelAndTextPanelRow1.add(die2TextField);
		
		dieSumLabel = new JLabel("Dice Sum:");
		labelAndTextPanelRow2.add(dieSumLabel);
		
		dieSumTextField = new JTextField(2);
		dieSumTextField.setEditable(false);
		labelAndTextPanelRow2.add(dieSumTextField);

		pointLabel = new JLabel("Point:     ");
		labelAndTextPanelRow2.add(pointLabel);
		
		pointTextField = new JTextField(2);
		pointTextField.setEditable(false);
		labelAndTextPanelRow2.add(pointTextField);

		// add both rows to the JPanel that will hodl the labels and text
		labelAndTextPanel.add(labelAndTextPanelRow1);
		labelAndTextPanel.add(labelAndTextPanelRow2);
		
		// add labelsAndTextPanel to mainPanel NORTH
		mainPanel.add(labelAndTextPanel, BorderLayout.NORTH);
		
		
		// create the textArea to display the game result
		resultsText = new JTextArea(15,15);
		resultsText.setFont(new Font("Arial", Font.BOLD, 14));
		
		// create the scroller for the JTextArea
		resultsScroll = new JScrollPane(resultsText);
		resultsPanel.add(resultsScroll);
		
		// create rollButton and add to buttonPanel
		rollButton = new JButton("Roll");
		buttonPanel.add(rollButton);
		rollButton.addActionListener(
				new ActionListener() { // Beginning anonymous inner class
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Craps craps = new Craps();
						craps.play();
						die1TextField.setText(String.valueOf(craps.getDie1()));
						die2TextField.setText(String.valueOf(craps.getDie2()));
						
						dieSumTextField.setText(String.valueOf(craps.getSumOfDice()));
						pointTextField.setText(String.valueOf(craps.getMyPoint()));
						
						resultsText.setText(craps.getLog());
					}
				} // End anonymous inner class
			);
		// add button and text area to JPanel that will hold them
		buttonAndResultsPanel.add(buttonPanel);
		buttonAndResultsPanel.add(resultsPanel);
		
		
		// add buttonPanel and resultPanel to mainPanel CENTER
		mainPanel.add(buttonAndResultsPanel, BorderLayout.CENTER);
		
		
		// create imageLabel and add to rollingDicePanel
		imageLabel = new JLabel();
		try {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("rollingDice.gif"));
			imageLabel.setIcon(imageIcon); 
		} catch (NullPointerException npe) {
			imageLabel.setText("rollingDice.gif icon not found");
		}

		rollingDicePanel.add(imageLabel);
		
		// add rollingDice to mainPanel SOUTH
		mainPanel.add(rollingDicePanel, BorderLayout.SOUTH);
		
		
		// add mainPanel to JFrame
		add(mainPanel);
	}
}
