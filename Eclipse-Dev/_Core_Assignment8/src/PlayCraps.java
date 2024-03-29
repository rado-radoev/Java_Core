import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
		// set the panel default size. The JFrmae will respect this dimension
		mainPanel.setPreferredSize(new Dimension(300, 420));
		
		/* 
		 set 3 panels to hold the labels and text
		 the button and results text
		 and the image of rolling dice 
		*/
		labelAndTextPanel = new JPanel();
		labelAndTextPanel.setLayout(new BoxLayout(labelAndTextPanel, BoxLayout.PAGE_AXIS));
		buttonAndResultsPanel = new JPanel(new FlowLayout());
		rollingDicePanel = new JPanel(new FlowLayout());
		
		/* 
		 text panels that will hold the JLabels and JTextFields
		 each row of components is in a separate JPanel
		 then both panels are added to one panel,
		 which is then added to the NORTH position of the main panel
		 The two row JPanels are set to Box Layout with a LINE_AXIS orientation (left to right)
		 and are CENTER aligned
		*/
		labelAndTextPanelRow1 = new JPanel();
		labelAndTextPanelRow1.setLayout(new BoxLayout(labelAndTextPanelRow1, BoxLayout.LINE_AXIS));
		labelAndTextPanelRow1.setAlignmentX(CENTER_ALIGNMENT);
		labelAndTextPanelRow2 = new JPanel();
		labelAndTextPanelRow2.setLayout(new BoxLayout(labelAndTextPanelRow2, BoxLayout.LINE_AXIS));
		labelAndTextPanelRow2.setAlignmentX(CENTER_ALIGNMENT);
		
		/* the button and the display of the results are added to to a grid layout panel
		 the button and the results are separated in their own panels.
		 each panel is the added to the main gridlayout buttonAndResults JPanel,
		 which is then added to the main JPanel CENTER position
		*/
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		resultsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		
		// ================================================================================
		// LABELS AND TEXTFIELDS ROW 1 START
		// ================================================================================	
		// add some clever empty areas so the text is aligned
		// then creating the JLabel and adding it to the JPanel
		labelAndTextPanelRow1.add(Box.createRigidArea(new Dimension(10, 0)));
		die1Label = new JLabel("Die One:");
		labelAndTextPanelRow1.add(die1Label);
		
		// add space between die 1 label and die 1 text fields
		labelAndTextPanelRow1.add(Box.createRigidArea(new Dimension(20, 0)));

		// create JText field for die 1. Set it to not editable and add maximum size,
		// otherwise it will be assigned as much space as it is available. 
		// add it to the JPanel
		die1TextField = new JTextField(2); 
		die1TextField.setEditable(false);
		die1TextField.setMaximumSize(new Dimension(50, 50));
		labelAndTextPanelRow1.add(die1TextField);
		
		// add some spacing between die1 text field and die 2 jlabel
		labelAndTextPanelRow1.add(Box.createRigidArea(new Dimension(30, 0)));
		
		// add die 2 JLabel to JPanel
		die2Label = new JLabel("Die Two:");
		labelAndTextPanelRow1.add(die2Label);
		
		// create die2 text field. Set it to not editable and add maximum size,
		// otherwise it will be assigned as much space as it is available.
		// add it to JPanel
		die2TextField = new JTextField(2);
		die2TextField.setEditable(false);
		die2TextField.setMaximumSize(new Dimension(50, 50));	
		labelAndTextPanelRow1.add(die2TextField);
		
		// ================================================================================
		// LABELS AND TEXTFIELDS ROW 1 END
		// ================================================================================	
		
		
		// ================================================================================
		// LABELS AND TEXTFIELDS ROW 2 START
		// ================================================================================	
		// add some clever empty areas so the text is aligned
		// then creating the JLabel and adding it to the JPanel
		labelAndTextPanelRow2.add(Box.createRigidArea(new Dimension(10, 0)));
		dieSumLabel = new JLabel("Dice Sum:");
		labelAndTextPanelRow2.add(dieSumLabel);
		
		// add space between dice sum JLabel and dice sum JTextField 
		labelAndTextPanelRow2.add(Box.createRigidArea(new Dimension(10, 0)));
		
		// create dice sum text field. Set it to not editable and add maximum size,
		// otherwise it will be assigned as much space as it is available.
		// add it to JPanel
		dieSumTextField = new JTextField(2);
		dieSumTextField.setEditable(false);
		dieSumTextField.setMaximumSize(new Dimension(50, 50));;
		labelAndTextPanelRow2.add(dieSumTextField);

		// add some spacing between dice sum text field and point jlable
		labelAndTextPanelRow2.add(Box.createRigidArea(new Dimension(30, 0)));
		
		// add point JLabel to JPanel
		pointLabel = new JLabel("Point:");
		labelAndTextPanelRow2.add(pointLabel);
		
		// create point text field. Set it to not editable and add maximum size,
		// otherwise it will be assigned as much space as it is available.
		pointTextField = new JTextField(2);
		pointTextField.setEditable(false);
		pointTextField.setMaximumSize(new Dimension(50, 50));
		
		// before adding the point to the JPanel set an empty space between the text field
		// and the end of the panel
		labelAndTextPanelRow2.add(Box.createRigidArea(new Dimension(20, 0)));
		
		// now add the point to the panel
		labelAndTextPanelRow2.add(pointTextField);
		
		// ================================================================================
		// LABELS AND TEXTFIELDS ROW 2 END
		// ================================================================================	

		// add both rows to the JPanel that will hold the labels and text
		labelAndTextPanel.add(labelAndTextPanelRow1);
		labelAndTextPanel.add(labelAndTextPanelRow2);
		
		// add labelsAndTextPanel to mainPanel NORTH
		mainPanel.add(labelAndTextPanel, BorderLayout.PAGE_START);
		
		
		// create the textArea to display the game result
		resultsText = new JTextArea(15,15);
		resultsText.setFont(new Font("Arial", Font.BOLD, 14));
		
		// create the scroller for the JTextArea
		resultsScroll = new JScrollPane();
		resultsScroll.setViewportView(resultsText);
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
		mainPanel.add(rollingDicePanel, BorderLayout.PAGE_END);
		
		
		// add mainPanel to JFrame
		add(mainPanel);
	}
	
}
