import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawImageControlPanel extends JPanel {

	private Picture picture;
	private String fileName;
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setPicture(String fileName) {
		this.picture = new Picture(getFileName());
	}
	
	public void setPicture(Picture pic) {
		this.picture = pic;
	}
	
	public Picture getPicture() {
		return picture;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(getPicture().getImage(), 0, 0, null);
	}
	
	public void grayscale() {
		picture.grayscale();
	}
	
	public void sepia() {
		picture.sepiaTint();
	}
	
	public void negate() {
		picture.negate();
	}
	

}
