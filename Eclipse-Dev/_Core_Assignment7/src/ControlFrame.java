
// ControlFrame.java
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ControlFrame extends JFrame {

	private Color[] colors = {Color.RED, Color.BLUE, Color.CYAN, Color.MAGENTA};
	private String[] colorNames = {"Red", "Blue", "Cyan", "Magenta"};
	private JPanel mainPanel;
	private final DrawControlPanel drawPanel;
	private final JPanel calcPanel;
	private final JPanel soundPanel;
	private final JPanel encodePanel;
	private final JPanel decodePanel;
	final JMenuBar bar;
	private DrawImageControlPanel imagePanel; // *** modified code
	private JSlider widthJSlider;
	private JTextField xValTextField;
	private JTextField yValTextField;
	private JLabel soundLabel;
	private JLabel calcJLabel;
	private JButton calcJButton;
	private JButton soundButton;
	private JTextArea soundTextArea;
	private JScrollBar soundScrollBar;

	private String xStr;
	private String yStr;

	private String picName;

	private String soundName;
	private Sound sound;

	public ControlFrame(String title) {
		super(title);
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setSize(200, 250);

		calcPanel = new JPanel(new FlowLayout());
		calcPanel.setSize(200, 200);

		// create imagePanel
		imagePanel = new DrawImageControlPanel();
		// set imagePanel layout to BorderLayout
		imagePanel.setLayout(new BorderLayout());
		// set imagePanel size
		imagePanel.setSize(200, 200);

		// create soundPanel and set layout to FlowLayout
		soundPanel = new JPanel(new FlowLayout());
		// set soundPanel size
		soundPanel.setSize(200, 200);

		// crate encode & decode JPanels and set their size to 200,200.
		// Layout will be BorderLayout
		encodePanel = new JPanel(new BorderLayout());
		encodePanel.setSize(200, 200);

		decodePanel = new JPanel(new BorderLayout());
		decodePanel.setSize(200, 200);
		
		drawPanel = new DrawControlPanel();
		drawPanel.setSize(200, 200);

		this.setContentPane(mainPanel);

		bar = new JMenuBar(); // Create a JMenuBar so we can attach menus to it.

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');

		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.setMnemonic('A');
		fileMenu.add(aboutItem);
		aboutItem.addActionListener(new ActionListener() // Beginning of anonymous inner class
		{
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(ControlFrame.this,
						"This application provides enhanced\n control over multimedia projects.", "About",
						JOptionPane.PLAIN_MESSAGE);
			}
		} // End of anonymous inner class
		);

		setJMenuBar(bar); // Attach the JMenuBar to the ControlFrame.
		bar.add(fileMenu); // Add the file menu to the JMenuBar.

		// create image menu and set mnemonic
		JMenu imageMenu = new JMenu("Image");
		imageMenu.setMnemonic('S');

		// create show picture item (submenu) that will appear under File Menu
		JMenuItem showPicture = new JMenuItem("Show Picture");
		showPicture.setMnemonic('S');// *** modified code

		// create sound Menu and set mnemonic
		JMenu soundMenu = new JMenu("Sound");
		soundMenu.setMnemonic('o');

		// create load sound item (submenu) that will appear under File Menu
		JMenuItem loadSoundItem = new JMenuItem("Load Sound");
		loadSoundItem.setMnemonic('d');

		// create encode menu and set mnemonic
		JMenuItem encodeMenuItem = new JMenuItem("Encode");
		encodeMenuItem.setMnemonic('e');

		// create decode menu and set mnemonic
		JMenuItem decodeMenuItem = new JMenuItem("Decode");
		decodeMenuItem.setMnemonic('d');

		// create encode/decode submenut and add to file
		JMenu encodeDecode = new JMenu("Encode/Decode");
		encodeDecode.setMnemonic('E');

		final JMenu colorMenu = new JMenu("Color");
		colorMenu.setMnemonic('C');

	
		// ================================================================================
		// UNNESCESSARY COMPLEX CODE START. LEFT FOR DEMONSTRATION PURPOSES
		// ================================================================================		
			
		
//		JMenuItem redItem = new JMenuItem("Red");
//		colorMenu.add(redItem);
//		redItem.addActionListener(new ActionListener() // Beginning of anonymous inner class
//		{
//			public void actionPerformed(ActionEvent event) {
//				drawPanel.setFillColor(Color.RED);
//				repaint();
//			}
//		} // End of anonymous inner class
//		);
//
//		JMenuItem blueItem = new JMenuItem("Blue");
//		colorMenu.add(blueItem);
//		blueItem.addActionListener(new ActionListener() // Beginning of anonymous inner class
//		{
//			public void actionPerformed(ActionEvent event) {
//				drawPanel.setFillColor(Color.BLUE);
//				repaint();
//			}
//		} // End of anonymous inner class
//		);
//
//		JMenuItem magentaItem = new JMenuItem("Magenta");
//		colorMenu.add(magentaItem);
//		magentaItem.addActionListener(new ActionListener() // Beginning of anonymous inner class
//		{
//			public void actionPerformed(ActionEvent event) {
//				drawPanel.setFillColor(Color.MAGENTA);
//				repaint();
//			}
//		} // End of anonymous inner class
//		);
//
//		JMenuItem cyanItem = new JMenuItem("Cyan");
//		colorMenu.add(cyanItem);
//		cyanItem.addActionListener(new ActionListener() // Beginning of anonymous inner class
//		{
//			public void actionPerformed(ActionEvent event) {
//				drawPanel.setFillColor(Color.CYAN);
//				repaint();
//			}
//		} // End of anonymous inner class
//		);
		
		// ================================================================================
		// UNNESCESSARY COMPLEX CODE END. LEFT FOR DEMONSTRATION PURPOSES
		// ================================================================================		

		JMenuItem calcPanelItem = new JMenuItem("Calculate");
		calcPanelItem.setMnemonic('C');
		fileMenu.add(calcPanelItem);
		calcPanelItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {	
				removeMenuItems(colorMenu,imageMenu);
				mainPanel.remove(drawPanel);
				mainPanel.remove(widthJSlider);
				mainPanel.remove(imagePanel);
				xValTextField.setText("");
				yValTextField.setText("");
				calcJLabel.setText("");
				mainPanel.add(calcPanel, BorderLayout.CENTER);
				validate();
				repaint();
			}
		});
		
		
		// ================================================================================
		// DRAW PANEL START
		// ================================================================================		
		
		// color change event handler
		ColorHandler colorHandler = new ColorHandler();
		
		
		// Create the color menus and assign event handlers to each one
		// will change the oval color depending on the selected submenu
		for (int i = 0; i < colorNames.length; i++) {
			JMenuItem colorMenuItem = new JMenuItem(colorNames[i]);
			colorMenu.add(colorMenuItem);
			colorMenuItem.addActionListener(colorHandler);
		}

		JMenuItem drawPanelItem = new JMenuItem("DrawPanel");
		drawPanelItem.setMnemonic('D');
		fileMenu.add(drawPanelItem);
		cleanMainPanel();
		drawPanelItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				bar.add(colorMenu);
				removeMenuItems(imageMenu, soundMenu);
				mainPanel.remove(calcPanel);
				mainPanel.remove(imagePanel);
				drawPanel.setBackground(Color.WHITE);
				mainPanel.add(drawPanel, BorderLayout.CENTER);
				mainPanel.add(widthJSlider, BorderLayout.SOUTH);
				validate();
				repaint();
			}
		});
		
		
		widthJSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, drawPanel.getOvalWidth());
		widthJSlider.setMajorTickSpacing(10);
		widthJSlider.setPaintTicks(true);

		widthJSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				drawPanel.setOvalWidth(widthJSlider.getValue());
				repaint();
			}
		});
		
		// ================================================================================
		// DRAW PANEL END
		// ================================================================================

		
		
		// ================================================================================
		// SOUND MENU START
		// ================================================================================

		// add loadSoundItem submenu to File Menu
		fileMenu.add(loadSoundItem);
		// add action listener to loadSoundItem
		loadSoundItem.addActionListener(new ActionListener() { // Beginning of anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// remove menus from previous methods in case they are shown
				removeMenuItems(colorMenu, imageMenu);

				// remove old panel, so no stale panels appear when the menu is clicked
				cleanMainPanel();

				// add the sound Menu to the Menu bar
				bar.add(soundMenu);
				// allow the user to select the file to open
				soundName = FileChooser.pickAFile();				
				// assign the chosen sound to the sound variable
				sound = new Sound(soundName);

				// add the soudPanel, which in this case is empty panel
				mainPanel.add(soundPanel);

				// validate it and repaint it so if anything else was painted on the panel
				// it is not repainted.
				validate();
				repaint();
			}
		} // End of anonymous inner class
		);

		// create sumbenu to play a sound
		JMenuItem playSoundItem = new JMenuItem("Play sound");
		// setting playSound menu mnemonic
		playSoundItem.setMnemonic('l');
		// add play sound submenu to the Sound Menu
		soundMenu.add(playSoundItem);
		// set the action to perform when menu is clicked on
		playSoundItem.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// play the sound
				sound.play();
			}
		} // End anonymous inner class
		);

		// create submenu to mirror a sound
		JMenuItem mirrorSoundItem = new JMenuItem("Mirror sound");
		// setting mirrorSound menu mnemonic
		mirrorSoundItem.setMnemonic('m');
		// add mirrorSound submenu to Sound Menu
		soundMenu.add(mirrorSoundItem);
		// set the action to perform when menu is clicked on
		mirrorSoundItem.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// mirror the sound
				sound.mirrorFrontToBack();
				// now play it
				sound.play();
			}
		} // End anonymous inner class
		);

		// createing a play button and a label to display the sound name
		soundButton = new JButton("Play");
		soundLabel = new JLabel();

		// create submenu to revert the sound back to its original state
		JMenuItem revertSoundItem = new JMenuItem("Revert sound");
		// set the revertSound submenu mnemonic
		revertSoundItem.setMnemonic('v');
		// add the revertSound submenu to the Sound Menu
		soundMenu.add(revertSoundItem);
		// set the action to perform when the menu is clicked
		revertSoundItem.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// add the soundlabel and button to the soundPanel
				// soundButton actionListener implementation to follow
				soundPanel.add(soundLabel);
				soundPanel.add(soundButton);

				// rewrite the sound object
				sound = new Sound(soundName);
				// set the sound label to the sound name
				soundLabel.setText("Sound name: "
						+ soundName.substring(soundName.lastIndexOf(File.separator) + 1, soundName.length() - 4));

				// repaint the JPanel (soundPanel) so the new sound name is displyed
				validate();
				repaint();
			}
		} // End anonymous inner class
		);

		// add scroll bar, not that we actually need it for this, but ...
		soundScrollBar = new JScrollBar(SwingConstants.VERTICAL);

		// create a sound text area that will display information about the sound
		// set properties of the textArea, so it is visible, has a transparent
		// background and
		// cannot be edited. Also add a scroll bar to it.
		soundTextArea = new JTextArea();
		soundTextArea.setVisible(true);
		soundTextArea.setEditable(false);
		soundTextArea.setOpaque(false);
		soundTextArea.add(soundScrollBar);

		// create sumbemu soundDisplay that will display information about the sound
		JMenuItem soundDisplayItem = new JMenuItem("Display Sound");
		// set soundDisplay submenu mnemonic
		soundDisplayItem.setMnemonic('D');
		// add the displaySound submenu to the Sound Menu
		soundMenu.add(soundDisplayItem);
		// setting the action to peform when the menu is clicked
		soundDisplayItem.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// clean up the JPanel if sound has been reverted before that
				mainPanel.remove(soundPanel);

				// get the sound toString representation and display it in the textAre
				String soundText = sound.toString();
				soundTextArea.setText(soundText);

				// add the information to the JPanel and Left align it
				mainPanel.add(soundTextArea, FlowLayout.LEFT);

				// reapint the JPanel with the new information
				validate();
				repaint();
			}
		} // End anonymous inner class
		);

		// set action to perform when the sound button is pressed
		soundButton.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// create new sound object, using the name that was already selected
				// when the user clicked on the load sound menu
				Sound s = new Sound(soundName);
				// play that funky music
				s.play();

				validate();
				repaint();
			}
		} // End anonymous inner class
		);

		// ================================================================================
		// SOUND MENU END
		// ================================================================================

		
		
		// ================================================================================
		// IMAGE MENU START
		// ================================================================================

		// add showPicture submenu to File Menu
		fileMenu.add(showPicture);
		// setting the action to perform when selecting the menu
		showPicture.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // Beginning anonymous inner class
				// clear all other menues, except File Menu
				removeMenuItems(colorMenu, soundMenu);

				// add image menu
				bar.add(imageMenu);
				
				// clear out the JPanel from any other methods
				cleanMainPanel();
				
				// allow the user to select the image to display
				picName = FileChooser.pickAFile();
				// before displaying the image check if the cancel is not clicked
				// in which case the picName variable will be null
				// if image is selected, invoke the imagePanel JPanel methods
				// and add it to the main panel, then repaint
				if (picName != null) {
					imagePanel.setFileName(picName);
					imagePanel.setPicture(picName);
					mainPanel.add(imagePanel, BorderLayout.CENTER);
					validate();
					repaint();
				}
			}
		} // End anonymous inner class
		);

		// create revert submenu
		JMenuItem revertItem = new JMenuItem("Revert");
		// set the mnemonic for the revert submenu
		revertItem.setMnemonic('R');
		// add the revert submenu to the Image Menu
		imageMenu.add(revertItem);
		// setting actions to perform when the menu is selected
		revertItem.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// reset the picture of the imagePanel to the original
				// then repaint the JPanel
				imagePanel.setPicture(picName);
				validate();
				repaint();
			}
		} // End anonymous inner class
		);

		// create grayscale submenu
		JMenuItem grayScaleItem = new JMenuItem("Grayscale");
		// set menu mnemonic
		grayScaleItem.setMnemonic('G');
		// add grayscale submentu to the Image menu
		imageMenu.add(grayScaleItem);
		// setting actions to perform when menu is selected
		grayScaleItem.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// modify the image in imagePanel
				// it will be automatically repainted in the panel
				// so the image will be grayscaled
				// once the mainPanel is repainted too the newly modified image
				// will be displayed
				imagePanel.grayscale();
				validate();
				repaint();
			}
		} // End anonymous inner class
		);

		// create sepia submenu
		JMenuItem sepiaItem = new JMenuItem("Sepia");
		// set sepai submenu mnemonic
		sepiaItem.setMnemonic('P');
		// add the sepia submenu to the Image Menu
		imageMenu.add(sepiaItem);
		// setting actions to perform when menu is selected
		sepiaItem.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// modify the image in imagePanel
				// it will be automatically repainted in the panel
				// so the image will have the sepia filter applied to it
				// once the mainPanel is repainted too the newly modified image
				// will be displayed
				imagePanel.sepia();
				validate();
				repaint();
			}
		} // End anonymous inner class
		);

		// create negate submentu
		JMenuItem negateItem = new JMenuItem("Negate");
		// set negate submenu mnemonic
		negateItem.setMnemonic('N');
		// add the negate submenu to the Image Menu
		imageMenu.add(negateItem);
		// setting actions to perform when menu is selected
		negateItem.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// modify the image in imagePanel
				// it will be automatically repainted in the panel
				// so the image will have the negate filter applied to it
				// once the mainPanel is repainted too the newly modified image
				// will be displayed
				imagePanel.negate();
				validate();
				repaint();
			}
		} // End anonymous inner class
		);

		// ================================================================================
		// IMAGE MENU END
		// ================================================================================

		
		
		// ================================================================================
		// ENCODE/DECODE MENU START
		// ================================================================================
		// add main menu to file menu
		fileMenu.add(encodeDecode);

		// add both encode and decode submenus to main menu
		encodeDecode.add(encodeMenuItem);
		encodeDecode.add(decodeMenuItem);

		// encode the picture
		encodeMenuItem.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// remove other panels
				cleanMainPanel();
				
				// remove other menus
				removeMenuItems(soundMenu, colorMenu, imageMenu);

				// get a text file to encrypt
				JOptionPane.showMessageDialog(null, "Select text file to encode");
				String sourceFileName = FileChooser.pickAFile();

				String dir = null;
				// get the dir
				// this is where the output file will be saved to
				if (sourceFileName != null) {
					dir = sourceFileName.substring(0, sourceFileName.lastIndexOf(File.separatorChar) + 1);	
				}
				

				// get a picutre to encode to
				JOptionPane.showMessageDialog(null, "Select picture to encode to");
				Picture pictureToEncodeTo = new Picture(FileChooser.pickAFile());

				// get the target file
				// it will be saved in the same dir as the text file
				String targetFileName = dir;
				targetFileName += JOptionPane.showInputDialog("Enter file name of the output picture");

				// create a steganographer obj
				Steganographer sten = new Steganographer();

				if (sourceFileName != null && pictureToEncodeTo != null && targetFileName != null) {
					// encrypt the text into the picture
					sten.encode(sourceFileName, pictureToEncodeTo, targetFileName);

					// display a text with the path to the encrypted image
					JTextArea text = new JTextArea("Encoded to: " + targetFileName + ".png", 1, mainPanel.getWidth() - 1);
					text.setWrapStyleWord(true);
					text.setLineWrap(true);
					text.setOpaque(false);
					text.setEditable(false);
					encodePanel.add(text);
					mainPanel.add(encodePanel, BorderLayout.CENTER);
					validate();
					repaint();	
				}
			}
		} // End anonymous inner class
		);

		decodeMenuItem.addActionListener(new ActionListener() { // Beginning anonymous inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				// remove other panels
				cleanMainPanel();
				

				// remove other menus
				removeMenuItems(soundMenu, colorMenu, imageMenu);

				// get the picture to be decrypted
				Picture picture = new Picture(FileChooser.pickAFile());

				if (picture != null) {
					// create a steganographer obj
					Steganographer sten = new Steganographer();

					// decrypt the image
					String decodedString = sten.decode(picture);

					// display the decrypted text
					// add scrollbar if needed
					JScrollPane scrollBar;
					JTextArea decodedText = new JTextArea(decodedString, 10, 20);
					decodedText.setWrapStyleWord(true);
					decodedText.setLineWrap(true);
					decodedText.setOpaque(false);
					decodedText.setEditable(false);
					scrollBar = new JScrollPane(decodedText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
							JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

					decodePanel.add(scrollBar);
					mainPanel.add(decodePanel, BorderLayout.CENTER);
					validate();
					repaint();					
				}
			}
		} // End anonymous inner class
		);

		// ================================================================================
		// ENCODE/DECODE MENU END
		// ================================================================================

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('x');
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});


		xValTextField = new JTextField(3);
		xValTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				xStr = event.getActionCommand();
			}
		});

		calcPanel.add(xValTextField);

		yValTextField = new JTextField(3);
		yValTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				yStr = event.getActionCommand();
			}
		});

		calcPanel.add(yValTextField);

		calcJButton = new JButton("Calculate");
		calcJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					int x = Integer.parseInt(xStr);
					int y = Integer.parseInt(yStr);
					int result = x + y;
					calcJLabel.setText(xStr + " + " + yStr + " = " + result);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(ControlFrame.this,
							"You must enter a valid number and then <ENTER> for each textbox!", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		calcPanel.add(calcJButton);

		calcJLabel = new JLabel();
		calcPanel.add(calcJLabel, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(200, 250);
		setVisible(true);
		validate();
	}
	
	// anonymous inner class that will be used as event handler for color Menu Item selection
	private class ColorHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// get the name of the selected color submenu
			String menuColor = e.getActionCommand();
			
			// try to match the color to one of the colors in the color names array
			// if match is found repaint the oval shape using the new color
			// indexes in colorNames and colors arrays should match !!!
			for (int i = 0; i < colorNames.length; i++) {
				if (menuColor.equals(colorNames[i])) {
					drawPanel.setFillColor(colors[i]);
					repaint();
				}
			}	
		}
		
	}
	
	private void cleanMainPanel() {
		mainPanel.removeAll();
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	private void removeMenuItems(JMenuItem...items) {
		for (JMenuItem item : items) {
			bar.remove(item);
		}
	}
	
}
