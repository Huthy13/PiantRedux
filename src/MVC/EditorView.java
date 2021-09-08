package MVC;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import HelperClasses.DynamicPixels;
import HelperClasses.MouseAdapterController;
import HelperClasses.PixelArray;
import HelperClasses.ViewScaling;


@SuppressWarnings("serial")
public class EditorView extends JFrame{
	
	private DynamicPixels canvas;
	private JScrollPane scrollPane;
	private boolean hasPixelArray = false;
	
	public EditorView(MenuBar menuBar){
			
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    //add Menu Bar
	    this.getContentPane().add(BorderLayout.NORTH, menuBar);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.pack();
		this.setSize(800, 600);
		this.setVisible(true);
	}
	
	
	public void setPixelArray(PixelArray pixelArray,MouseAdapterController mouseAdapterController, ViewScaling viewScaling) {
		//keep track of window size
		Dimension originalDimension = this.getSize();
		
		this.canvas = new DynamicPixels(pixelArray, viewScaling);	
		this.hasPixelArray = true;
		
		//assign canvas to the scroll pane
		scrollPane = new JScrollPane(canvas,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//inject mouseAdaptater listeners here
		canvas.addMouseListener(mouseAdapterController.getMouseAdapter());	
		canvas.addMouseMotionListener(mouseAdapterController.getMouseMotionAdapter());
		this.add(scrollPane);
		this.pack();
		
		//reset window size to maintain window size
		this.setSize(originalDimension);
		this.repaint();
	}
	
	public void update() {
		canvas.updateUI();
	}
	
	public boolean getPixelArrayStat() {
		return hasPixelArray;
	}
	
	public void changePixelArray(PixelArray pixelArray) {	
		this.canvas.changePixelArray(pixelArray);
	}
	
	public void zoom() {
		this.canvas.zoom();
		this.update(getGraphics());
	}
	
}
