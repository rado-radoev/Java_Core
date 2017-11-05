import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.PointerInfo;
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
		GroupLayout layout = new GroupLayout(labelAndTextPanel);
		labelAndTextPanel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		

		buttonAndResultsPanel = new JPanel(new FlowLayout());
		rollingDicePanel = new JPanel(new FlowLayout());
		
		/* 
		 text panels that will hold the JLabels and JTextFields
		 each row of components is in a separate JPanel
		 then both panels are added to one panel,
		 which is then added to the NORTH position of the main panel
		*/
		//labelAndTextPanelRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 5));
		//labelAndTextPanelRow2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 5));

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
		die1Label = new JLabel("Die One:");


		die1TextField = new JTextField(2); 
		die1TextField.setEditable(false);

		
		die2Label = new JLabel("Die Two:");

		
		die2TextField = new JTextField(2);
		die2TextField.setEditable(false);

		
		dieSumLabel = new JLabel("Dice Sum:");

		
		dieSumTextField = new JTextField(2);
		dieSumTextField.setEditable(false);


		pointLabel = new JLabel("Point:");

		
		pointTextField = new JTextField(2);
		pointTextField.setEditable(false);

		layout.linkSize(SwingConstants.HORIZONTAL, die1TextField ,die2TextField, dieSumTextField, pointTextField);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addGroup(layout.createSequentialGroup()
							.addComponent(die1Label)
							.addComponent(die1TextField)
							.addComponent(die2Label)
							.addComponent(die2TextField)))	
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
							.addComponent(dieSumLabel)
							.addComponent(dieSumTextField)
							.addComponent(pointLabel)
							.addComponent(pointTextField)))	
			);
		
		
		
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
