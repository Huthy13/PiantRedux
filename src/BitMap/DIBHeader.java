package BitMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

import HelperClasses.PixelArray;

public class DIBHeader {
	static final int DIB_HEADER_STANDARD_SIZE = 40;
	
	private byte[] sizeOfDibHeader;
	private byte[] pixelWidth;
	private byte[] pixelHeight;
	private byte[] numColorPanes;
	private byte[] numBitsPerPixel;
	private byte[] pixelArrayCompression;
	private byte[] sizeOfRawData;
	private byte[] printResHorizontal;
	private byte[] printResVertical;
	private byte[] numColorInPalette;
	private byte[] importantColors;
	
	
	public DIBHeader(byte[] data) {
		sizeOfDibHeader = Arrays.copyOfRange(data, 0, 4);
		pixelWidth = Arrays.copyOfRange(data, 4, 8);
		pixelHeight = Arrays.copyOfRange(data, 8, 12);
	}
	
	
	public DIBHeader(PixelArray pixelArray) {
				
		sizeOfDibHeader = makeSizeOfDibHearder();
		pixelWidth = makeWidth(pixelArray.getPixelWidth());
		pixelHeight = makeHeight(pixelArray.getPixelHeight());
		numColorPanes = makePanes();
		numBitsPerPixel = makeBitsPerPixel();
		pixelArrayCompression = makePixelCompression();
		sizeOfRawData = makeSizeOfRawData(pixelArray.getRawPixelArraySize());
		printResHorizontal = makePrintResHorizontal();
		printResVertical = makePrintResVertical();
		numColorInPalette = makeNumberOfColor();
		importantColors = importantColors();
	}
	
	
	public byte[] getHeader() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		try {
			outputStream.write(sizeOfDibHeader);
			outputStream.write(pixelWidth);
			outputStream.write(pixelHeight);
			outputStream.write(numColorPanes);
			outputStream.write(numBitsPerPixel);
			outputStream.write(pixelArrayCompression);
			outputStream.write(sizeOfRawData);
			outputStream.write(printResHorizontal);
			outputStream.write(printResVertical);
			outputStream.write(numColorInPalette);
			outputStream.write(importantColors);	

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return outputStream.toByteArray();
	}
	
	public int GetHeaderSize() {
		return convertByteArrayToInt(sizeOfDibHeader);
	}
	
	public int getPixelHeight() {
		return convertByteArrayToInt(pixelHeight);
	}
	
	public int getPixelWidth() {
		return convertByteArrayToInt(pixelWidth);
	}
	
				
	private byte[] makeSizeOfDibHearder() {		
		return formatBytes(4,DIB_HEADER_STANDARD_SIZE);
	}
	
	private byte[] makeWidth(int width) {
		return formatBytes(4,width);
	}
	
	private byte[] makeHeight(int height) {
		return formatBytes(4,height);
	}
	
	private byte[] makePanes() {
		return formatBytes(2,1);
	}
	
	private byte[] makeBitsPerPixel() {
		return formatBytes(2,24);
	}
	
	private byte[] makePixelCompression(){
		return formatBytes(4,0);
	}
	
	private byte[] makeSizeOfRawData(int rawPixelArraySize) {
		return formatBytes(4, rawPixelArraySize);
	}
	
	private byte[] makePrintResHorizontal() {
		return formatBytes(4,0);
	}
	
	private byte[] makePrintResVertical() {
		return formatBytes(4,0);
	}
	
	private byte[] makeNumberOfColor() {
		return formatBytes(4,0);
	}
	
	private byte[] importantColors() {
		return formatBytes(4,0);
	}
	
	
	//helper methods
		
	private int convertByteArrayToInt(byte[] data) {
		
		byte[] temp = new byte[data.length];
		
		int holdingSpot = data.length-1;
		for(byte b : data) {
			temp[holdingSpot] = b;
			holdingSpot--;
		}
			
		return ByteBuffer.wrap(temp).getInt();

	}
	
	
	private byte[] formatBytes(int byteCount, int value) {
		//convert to Big Integer as it has the capability to write to byte array
		BigInteger bigInt = BigInteger.valueOf(value);
		byte[] rawData = bigInt.toByteArray();	
		
		//make our new fixed size data to return
		byte[] returnData = new byte[byteCount];
		
		//reverse bytes
		int count = 0;
		for (int i=rawData.length; i > 0; i--) {
			returnData[i-1] = rawData[count++]; 
		}

		return returnData;
	}
	
	
	
}
