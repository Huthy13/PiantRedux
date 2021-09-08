package HelperClasses;
import java.awt.Color;

public class Pixel {
	private Color color;
	
	public Pixel(Color color) {
		setColor(color);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
