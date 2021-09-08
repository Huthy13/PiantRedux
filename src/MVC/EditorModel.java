package MVC;
import java.awt.Color;
import java.awt.Dimension;

import HelperClasses.PixelArray;

public class EditorModel {
	
	private PixelArray pixelArray;

	public void setPixelArray(PixelArray pixelArray) {
		this.pixelArray = pixelArray;
	}
	
	public void changePixel(int Height, int Width, Color color) {
		//Safety to stay within the bounds of the pixel array
		if(Height < 0) return;
		if(Height > pixelArray.getPixelHeight()-1) return;
		if(Width > pixelArray.getPixelWidth()-1) return;
		if(Width < 0) return;
		
		pixelArray.changePixelColor(Height, Width, color);
	}
	
	public void changePixel(Dimension position, Color color) {
		changePixel(position.height, position.width, color);
	}
	
	public void drawLine(Dimension start, Dimension stop) {
		float slope = (stop.height-start.height)/(stop.width-start.width);
		
		//y=mx+b
		System.out.println("slope: " + slope);
		System.out.println("Start:" + start.toString() + " End:" + stop.toString());
	}
	

	
}
