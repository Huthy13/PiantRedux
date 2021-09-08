package BitMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import HelperClasses.PixelArray;

public class BMPHeader {
	
	static final int STANDARD_BMP_HEADER_SIZE = 14;
	
	PixelArray pixelArray;
	DIBHeader dibHeader;
	
	private byte[] fileType;
	private byte[] fileSize;
	private byte[] reservedPadding;
	private byte[] headerOffset;
	
	
	
	public BMPHeader(String file_type, PixelArray pixelArray,DIBHeader dibHeader) {
		
		this.pixelArray = pixelArray;
		this.dibHeader = dibHeader;
		
		fileType = getFileType(file_type);
		fileSize = CalcFileSize();
		reservedPadding = getReservedPadding(4);
		headerOffset = getHeaderOffset(STANDARD_BMP_HEADER_SIZE + dibHeader.GetHeaderSize());
	}
	
	public byte[] getHeader() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		try {
			outputStream.write(fileType);
			outputStream.write(fileSize);
			outputStream.write(reservedPadding);
			outputStream.write(headerOffset);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return outputStream.toByteArray();
		
	}
	
	
	/**
	 * Header Field
	 * @param fileType BM:Windows 31.x,95,NT,...etc.
	 *				   BA: OS/2 struct bitmap array
	 *				   CI: OS/2 struct color icon
	 *				   CP: OS/2 const color pointer
	 *				   IC: OS/2 struct icon
	 *				   PT: OS/2 pointer
	 * @return 2 byte ASCII equivalent
	 */
	private byte[] getFileType(String fileType) {
		return fileType.getBytes(StandardCharsets.US_ASCII);
	}
	
	/**
	 * The size of the BMP file in bytes
	 * @param size in decimal form
	 * @return 4 byte array of file size
	 */
	private byte[] CalcFileSize() {
		//Final Size of the array to return (bmp size standard is 4 bytes)
		final int DATA_SIZE_IN_BYTES = 4;
		
		//calc size of pixel array
		int total = pixelArray.getRawPixelArraySize() + dibHeader.GetHeaderSize() + STANDARD_BMP_HEADER_SIZE;
		
		return populateReverseByteArray(DATA_SIZE_IN_BYTES, total);
	}
	
	/**
	 * reserved padding for future data
	 */
	private static byte[] getReservedPadding(int size) {
		return new byte[size];
	}
	
	private byte[] getHeaderOffset(int headerSize) {
		//Final Size of the array to return (bmp size standard is 4 bytes)
		final int DATA_SIZE_IN_BYTES = 4;

		return populateReverseByteArray(DATA_SIZE_IN_BYTES, headerSize);
	}
		
	private byte[] populateReverseByteArray(int byteCount, int value) {
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
