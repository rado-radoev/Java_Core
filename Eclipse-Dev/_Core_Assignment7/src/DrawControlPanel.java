// DrawControlPanel.java
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawControlPanel extends JPanel
{
  private Color fillColor = Color.CYAN;
  private int ovalWidth = 90;
  
  public DrawControlPanel()
  {
    setSize( 200, 200 ); 
  }
  
  public void paintComponent( Graphics g )
  {
    super.paintComponent( g );  // invoke the superclass paintComponent
    this.setBackground( Color.WHITE );
    g.setColor( fillColor );
    g.fillOval( 50, 50, ovalWidth, 60 );
  }
  
  void setFillColor(Color fillColor)
  {
    this.fillColor = fillColor;  
  }    
  
  void setOvalWidth(int ovalWidth)
  {
    this.ovalWidth = ovalWidth;  
  }  

  int getOvalWidth()
  {
    return ovalWidth;  
  }  
}
