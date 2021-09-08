package HelperClasses;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
public class PixelArray {
	
	private Pixel[][] colorArray;
	private int defaultWidth;
	private int defaultHeight;
	
	final int BITS_PER_PIXEL = 24;
	final int BYTES_PER_COLOR = 1;
	
	public PixelArray(ViewScaling viewScaling) {
		this.defaultWidth = viewScaling.getDefaultWidth();
		this.defaultHeight = viewScaling.getDefaultHeight();
		colorArray = new Pixel[defaultHeight][defaultWidth];
		for(int i = 0; i < defaultWidth; i++) {
			for(int j = 0; j < defaultHeight ; j++) {
			colorArray[j][i] = new Pixel(Color.WHITE);	
			}	
		}
	}
	
	public PixelArray(byte[] importedArray,int height, int width){
			
		colorArray = new Pixel[height][width];
		int paddingRequirement = (width % 4);
		
		int index = 0;
		int blue = 0;
		int green = 0;
		int red = 0;
		
		for(int i = 0; i < colorArray.length; i++) {
			for(int j = 0; j < colorArray[i].length; j++) {
				
				//parse color using bitwise and to convert into an unsigned integer
				blue = ((Byte)importedArray[index++]) & 0xff;
				green = ((Byte)importedArray[index++]) & 0xff;
				red = ((Byte)importedArray[index++]) & 0xff;
					
				colorArray[i][j] = new Pixel(new Color(red,green,blue));
			}
			
			//skip the buffer bits
			index = index + paddingRequirement;	
		}		
	}	

	public void changePixelColor(int row, int column, Color color) {
		
		this.colorArray[row][column].setColor(color);
	}
	
	public Pixel[][] getPixelArrayInPixels(){
		return this.colorArray;
	}
		
	public byte[] getPixelArray() {

		return colorArrayToBytes();
	}
	
	public int getPixelWidth() {
		return colorArray[0].length;
	}
	public int getPixelHeight() {
		return colorArray.length;
	}
	public int getRawPixelArraySize() {
		//calculate total pixels
		int total = (colorArray.length * colorArray[0].length) * (BITS_PER_PIXEL/8);
		//add in padding size
		total = total + (colorArray.length*2);
		return total;
	}
	
	private byte[] colorArrayToBytes() {
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		//calculate padding requirement
		int paddingRequirement = (colorArray[0].length % 4);
		
		for(int j = 0; j < colorArray.length; j++) { 
			for(Pixel i:colorArray[j]) {		
				outputStream.write(formatBytes(i.getColor().getBlue()));
				outputStream.write(formatBytes(i.getColor().getGreen()));
				outputStream.write(formatBytes(i.getColor().getRed()));
			}
			
			//Add padding at the end of the row
			for (int i = 0; i < paddingRequirement; i++) {
				outputStream.write(formatBytes(0));
			}	
		}	
		return outputStream.toByteArray();	
	}

	
	private byte formatBytes(Integer value) {	
		return value.byteValue();
	}
}
