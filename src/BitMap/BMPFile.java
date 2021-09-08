package BitMap;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import HelperClasses.PixelArray;

public class BMPFile{
	
	public static void createBMP(PixelArray pixelArray) {
				
		DIBHeader dibHeader = new DIBHeader(pixelArray);
		BMPHeader bmpHeader = new BMPHeader("BM",pixelArray, dibHeader);
		
		try {
			//create a writer
			
			FileOutputStream fos = new FileOutputStream(new File("output.bmp"));
			BufferedOutputStream writer = new BufferedOutputStream(fos);
			
			//write data to file
			
			writer.write(bmpHeader.getHeader());
			writer.write(dibHeader.getHeader());
			writer.write(pixelArray.getPixelArray());
			
			//flush remaining bytes
			writer.flush();
			
			//close the writer
			writer.close();
		}
		
		
		catch (IOException ex) {	
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex, "IO Exception",JOptionPane.ERROR_MESSAGE);
			System.out.println("Save Failed");
		}

		
		
	}
	
	
	@SuppressWarnings("resource")
	public static PixelArray openBMP() {
		
		PixelArray pixelArray;
		
		try {
			
			byte[] bmpHeader = new byte[BMPHeader.STANDARD_BMP_HEADER_SIZE];
			byte[] dibHeader = new byte[DIBHeader.DIB_HEADER_STANDARD_SIZE];
			
			FileInputStream fis = new FileInputStream("output.bmp");
			fis.read(bmpHeader,0,BMPHeader.STANDARD_BMP_HEADER_SIZE);
			fis.read(dibHeader,0,DIBHeader.DIB_HEADER_STANDARD_SIZE);
			
			DIBHeader newFile = new DIBHeader(dibHeader);
						
						
			byte[] importedPixelArray = new byte[((newFile.getPixelHeight()*newFile.getPixelWidth()*3)+(newFile.getPixelHeight()*2))];
			
			fis.read(importedPixelArray);
						
			pixelArray = new PixelArray(importedPixelArray,newFile.getPixelHeight(),newFile.getPixelWidth());
			
			
			return pixelArray;
			

			
		}
		
		catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("Failed to open");
		}
		
		
		return null;
		
		
	}
	

}
