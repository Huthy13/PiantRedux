package HelperClasses;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DynamicPixels extends JPanel {
	private PixelArray pixels;
	private ViewScaling viewScaling;
	
	public DynamicPixels(PixelArray pixels, ViewScaling viewScaling) {
		this.pixels = pixels;
		this.viewScaling = viewScaling;
		setBackground(Color.LIGHT_GRAY);
	}
	
	public void changePixelArray(PixelArray pixelArray) {
		this.pixels = pixelArray;
		this.repaint();
	}
	
	public void zoom() {
		this.repaint();		
		setPreferredSize(new Dimension(pixels.getPixelWidth()*viewScaling.getPixelSize(), 
				pixels.getPixelHeight()*viewScaling.getPixelSize()));
	}
	
	public int getPixelSize() {
		return this.viewScaling.getPixelSize();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
			
		Graphics2D g2d = (Graphics2D) g;
		Pixel[][] pixel = pixels.getPixelArrayInPixels();
				
		int topPos = 0;
		for(int row = pixel.length - 1; row > -1; row--) {
			int leftPos = 0;
			for(Pixel p : pixel[row]) {
				Rectangle2D.Double block = new Rectangle2D.Double(leftPos, topPos,viewScaling.getPixelSize(),viewScaling.getPixelSize());
				g2d.setColor(p.getColor());
				g2d.fill(block);
				leftPos += viewScaling.getPixelSize();
			}
			topPos += viewScaling.getPixelSize();
		}
	}
	
}
