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



public class ControlFrame extends JFrame
{ 
  private JPanel mainPanel;
  private final JPanel calcPanel;
  private final JPanel soundPanel;
  private DrawImageControlPanel imagePanel;  // *** modified code
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
  
  public ControlFrame(String title)
  {
    super( title );
    mainPanel = new JPanel( new BorderLayout() );
    mainPanel.setSize(200, 250);    
    
    calcPanel = new JPanel( new FlowLayout() );    
    calcPanel.setSize(200, 200); 
    
    imagePanel = new DrawImageControlPanel();// *** modified code
    imagePanel.setLayout(new BorderLayout());
    imagePanel.setSize(200, 200);// *** modified code

    
    
    soundPanel = new JPanel( new FlowLayout());
    soundPanel.setSize(200, 200);
    
    
    
    final DrawControlPanel drawPanel = new DrawControlPanel();
    drawPanel.setSize(200, 200);    
    
    this.setContentPane( mainPanel );
    
    final JMenuBar bar = new JMenuBar();  // Create a JMenuBar so we can attach menus to it.// *** modified code
    
    JMenu fileMenu = new JMenu( "File" );
    fileMenu.setMnemonic( 'F' );
    
    
    JMenuItem aboutItem = new JMenuItem( "About..." );
    aboutItem.setMnemonic( 'A' );
    fileMenu.add( aboutItem );
    aboutItem.addActionListener(
      new ActionListener()  // Beginning of anonymous inner class
      {
        public void actionPerformed( ActionEvent event )
        {
          JOptionPane.showMessageDialog( ControlFrame.this,
                                      "This application provides enhanced\n control over multimedia projects.",
                                      "About", JOptionPane.PLAIN_MESSAGE );
        }
     }  // End of anonymous inner class
    );
      
    
    setJMenuBar( bar );  // Attach the JMenuBar to the ControlFrame.
    bar.add( fileMenu );  // Add the file menu to the JMenuBar.
    
    
    
    
    JMenu imageMenu = new JMenu("Image");
    imageMenu.setMnemonic('S');
    
    JMenuItem showPicture = new JMenuItem("Show Picture");// *** modified code
    showPicture.setMnemonic( 'S' );// *** modified code
    
    
    
    JMenu soundMenu = new JMenu("Sound");
    soundMenu.setMnemonic('o');
    
    JMenuItem loadSoundItem = new JMenuItem("Load Sound");
    loadSoundItem.setMnemonic('d');
    
   
    
    
    
    
  
    final JMenu colorMenu = new JMenu( "Color" );
    colorMenu.setMnemonic( 'C' );
    
    JMenuItem redItem = new JMenuItem( "Red" );
    colorMenu.add( redItem );
    redItem.addActionListener(
      new ActionListener()  // Beginning of anonymous inner class
      {
        public void actionPerformed( ActionEvent event )
        {
          drawPanel.setFillColor( Color.RED );
          repaint();
        }
     }  // End of anonymous inner class
    );
    
    JMenuItem blueItem = new JMenuItem( "Blue" );
    colorMenu.add( blueItem );
    blueItem.addActionListener(
      new ActionListener()  // Beginning of anonymous inner class
      {
        public void actionPerformed( ActionEvent event )
        {
          drawPanel.setFillColor( Color.BLUE );
          repaint();
        }
     }  // End of anonymous inner class
    );
    
    JMenuItem magentaItem = new JMenuItem( "Magenta" );
    colorMenu.add( magentaItem );
    magentaItem.addActionListener(
      new ActionListener()  // Beginning of anonymous inner class
      {
        public void actionPerformed( ActionEvent event )
        {
          drawPanel.setFillColor( Color.MAGENTA );
          repaint();
        }
     }  // End of anonymous inner class
    );
    
    JMenuItem cyanItem = new JMenuItem( "Cyan" );
    colorMenu.add( cyanItem );
    cyanItem.addActionListener(
      new ActionListener()  // Beginning of anonymous inner class
      {
        public void actionPerformed( ActionEvent event )
        {
          drawPanel.setFillColor( Color.CYAN );
          repaint();
        }
     }  // End of anonymous inner class
    );
     
    JMenuItem calcPanelItem = new JMenuItem( "Calculate" );
    calcPanelItem.setMnemonic( 'C' );
    fileMenu.add( calcPanelItem );
    calcPanelItem.addActionListener(
      new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
        {
          bar.remove( colorMenu );
          bar.remove(imageMenu);
          mainPanel.remove( drawPanel );
          mainPanel.remove( widthJSlider );
          mainPanel.remove(imagePanel);
          xValTextField.setText("");
          yValTextField.setText("");
          calcJLabel.setText( "" );
          mainPanel.add( calcPanel, BorderLayout.CENTER );
          validate();
          repaint();
        }
      }
    );
    
    JMenuItem drawPanelItem = new JMenuItem( "DrawPanel" );
    drawPanelItem.setMnemonic( 'D' );
    fileMenu.add( drawPanelItem );
    drawPanelItem.addActionListener(
      new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
        {
          bar.add( colorMenu );    
          bar.remove(imageMenu);
          mainPanel.remove( calcPanel );
          mainPanel.remove(imagePanel);
          drawPanel.setBackground( Color.WHITE );
          mainPanel.add( drawPanel, BorderLayout.CENTER );
          mainPanel.add( widthJSlider, BorderLayout.SOUTH );          
          validate();
          repaint();
        }
      }
    );
    
    fileMenu.add(loadSoundItem);
    loadSoundItem.addActionListener(
    		new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					bar.remove(imageMenu);
					bar.remove(colorMenu);

					mainPanel.remove(drawPanel);
					mainPanel.remove(calcPanel);
					mainPanel.remove(widthJSlider);
					mainPanel.remove(imagePanel);
					
					bar.add(soundMenu);
					soundName = FileChooser.pickAFile();
					sound = new Sound(soundName);
															
					mainPanel.add(soundPanel);
					
					validate();
					repaint();
				}
			});
    
  
    JMenuItem playSoundItem = new JMenuItem("Play sound");
    playSoundItem.setMnemonic('l');
    soundMenu.add(playSoundItem);
    playSoundItem.addActionListener(
    		new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					sound.play();
				}
			});
    
    
    JMenuItem mirrorSoundItem = new JMenuItem("Mirror sound");
    mirrorSoundItem.setMnemonic('m');
    soundMenu.add(mirrorSoundItem);
    mirrorSoundItem.addActionListener( 
    		new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					sound.mirrorFrontToBack();
					sound.play();
				}
			});
   
    soundButton = new JButton("Play");
    soundLabel = new JLabel();
   
    JMenuItem revertSoundItem = new JMenuItem("Revert sound");
    revertSoundItem.setMnemonic('v');
    soundMenu.add(revertSoundItem);
    revertSoundItem.addActionListener(
    		new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					soundPanel.add(soundLabel);
				    soundPanel.add(soundButton);

					sound = new Sound(soundName);
					soundLabel.setText("Sound name: " + soundName.substring(soundName.lastIndexOf(File.separator) + 1, soundName.length() - 4));
					
					validate();
					repaint();
				}
			});
    
    soundScrollBar = new JScrollBar(SwingConstants.VERTICAL);
    
    soundTextArea = new JTextArea();
    soundTextArea.setVisible(true);
    soundTextArea.setEditable(false);
    soundTextArea.add(soundScrollBar);
    
  
    
    JMenuItem soundDisplayItem = new JMenuItem("Display Sound");
    soundDisplayItem.setMnemonic('D');
    soundMenu.add(soundDisplayItem);
    soundDisplayItem.addActionListener(
    		new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String soundText = sound.toString();
					soundTextArea.setText(soundText);
					
					mainPanel.add(soundTextArea);
					
					validate();
					repaint();
				}
			});
  
        
    soundButton.addActionListener(
    		new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {		    
					Sound s = new Sound(soundName);
					s.play();
					
					validate();
					repaint();
				}
			});
    
    
		fileMenu.add(showPicture);// *** modified code
		showPicture.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bar.add(imageMenu);
				bar.remove(colorMenu);

				mainPanel.remove(drawPanel);
				mainPanel.remove(calcPanel);
				mainPanel.remove(widthJSlider);

				picName = FileChooser.pickAFile();
				if (picName != null) {
					imagePanel.setFileName(picName);
					imagePanel.setPicture(picName);
					mainPanel.add(imagePanel, BorderLayout.CENTER);
					validate();
					repaint();
				}
			}
		});
    
    JMenuItem revertItem = new JMenuItem("Revert");
    revertItem.setMnemonic('R');
    imageMenu.add(revertItem);
    revertItem.addActionListener(
    		new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			imagePanel.setPicture(picName);
			validate();
			repaint();
		}
	});
    
    JMenuItem grayScaleItem = new JMenuItem("Grayscale");
    grayScaleItem.setMnemonic('G');
    imageMenu.add(grayScaleItem);
    grayScaleItem.addActionListener(
    		new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			imagePanel.grayscale();
			validate();
			repaint();
		}
	});
    
    JMenuItem sepiaItem = new JMenuItem("Sepia");
    sepiaItem.setMnemonic('P');
    imageMenu.add(sepiaItem);
    sepiaItem.addActionListener(
    		new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			imagePanel.sepia();
			validate();
			repaint();
		}
	});
    
    JMenuItem negateItem = new JMenuItem("Negate");
    negateItem.setMnemonic('N');
    imageMenu.add(negateItem);
    negateItem.addActionListener(
    		new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					imagePanel.negate();
					validate();
					repaint();
				}
			});
     
    JMenuItem exitItem = new JMenuItem( "Exit" );
    exitItem.setMnemonic( 'x' );
    fileMenu.add( exitItem );
    exitItem.addActionListener(
      new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
        {
          System.exit( 0 );
        }
      }
    );
    
    widthJSlider = new JSlider( SwingConstants.HORIZONTAL, 0, 100, drawPanel.getOvalWidth() );
    widthJSlider.setMajorTickSpacing( 10 );
    widthJSlider.setPaintTicks( true );
    
    widthJSlider.addChangeListener(
      new ChangeListener()
      {
        public void stateChanged( ChangeEvent e )
        {
          drawPanel.setOvalWidth( widthJSlider.getValue() );
          repaint();
        }
      }
    );
        
    xValTextField = new JTextField( 3 );
    xValTextField.addActionListener(
      new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
        {
          xStr = event.getActionCommand();
        }
      }
    );                                                                       

    calcPanel.add( xValTextField );

    yValTextField = new JTextField( 3 );
    yValTextField.addActionListener(
      new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
        {
          yStr = event.getActionCommand();
        }
      }
    );     

    calcPanel.add( yValTextField );
    
    calcJButton = new JButton( "Calculate" );   
    calcJButton.addActionListener(
      new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
        {
          try {       
            int x = Integer.parseInt( xStr );
            int y = Integer.parseInt( yStr );
            int result = x + y;
            calcJLabel.setText(xStr + " + " + yStr + " = " + result);
          }
          catch (NumberFormatException e) {
            JOptionPane.showMessageDialog( ControlFrame.this, "You must enter a valid number and then <ENTER> for each textbox!", "Invalid Input", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace();
          }
        }
      }
    );
    calcPanel.add( calcJButton );
    
    calcJLabel = new JLabel();
    calcPanel.add( calcJLabel, BorderLayout.CENTER );
    
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    setSize( 200, 250 );
    setVisible( true );
    validate();
  }
}  