package HelperClasses;
import java.awt.Dimension;

public class ViewScaling {
	
	private Dimension arraySize;
	private int pixelSize;
	private int defaultWidth;
	private int defaultHeight;
	
	public ViewScaling(int pixelSize, Dimension arraySize) {
		this.arraySize = arraySize;
		this.pixelSize = pixelSize;
		this.defaultWidth = arraySize.width;
		this.defaultHeight = arraySize.height;
	}
	
	public void update(int pixelSize, Dimension arraySize) {
		this.arraySize = arraySize;
		this.pixelSize = pixelSize;
	}
	
	public Dimension calcRawPixelPosition(Dimension rawPosition) {
		//invert Height
		int invertedHeight = (getDimension().height-(rawPosition.height/getPixelSize()) - 1);
		int width = rawPosition.width/getPixelSize();
		
		return new Dimension(width,invertedHeight);
	}
	
	public void setPixelSize(int pixelSize) {
		this.pixelSize = pixelSize;
	}
	
	public int getPixelSize() {
		return this.pixelSize;
	}
	
	public Dimension getDimension() {
		return this.arraySize;
	}
	
	public int getDefaultWidth() {
		return this.defaultWidth;
	}
	
	public int getDefaultHeight() {
		return this.defaultHeight;
	}
	
}
