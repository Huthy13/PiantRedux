package MVC;

import java.awt.Color;
import java.awt.Dimension;

import BitMap.BMPFile;
import HelperClasses.MouseAdapterController;
import HelperClasses.PixelArray;
import HelperClasses.ViewScaling;
import Tools.*;


public class EditorController {
	
	private EditorView view;
	private EditorModel model;
	private PixelArray pixelArray;
	private ViewScaling viewScaling;
	private Color currentColor = Color.BLACK;
	private ITool currentTool;
	private MouseAdapterController mouseAdapterController;
	
	public EditorController(EditorView view, EditorModel model){
		
		viewScaling = new ViewScaling(5, new Dimension(100,100));
		currentTool = new PencilTool(this);
		mouseAdapterController = new MouseAdapterController(currentTool);
				
		this.view = view;
		this.model = model;	
	
	}
	
	//changing main settings
	public void setCurrentColor(Color color) {
		this.currentColor = color;
	}
		
	public void setCurrentTool(ITool tool) {
		this.currentTool = tool;
		mouseAdapterController.setCurrentTool(tool);
	}
	
	public void zoom(boolean zoom) {
		if (zoom) {
			viewScaling.setPixelSize(viewScaling.getPixelSize() * 2);
		}
		else{
			if (viewScaling.getPixelSize() > 1) {
				viewScaling.setPixelSize(viewScaling.getPixelSize() / 2);
			}
		}
		view.zoom();
	}
	
	//Drawing
	public void modifyPixel(Dimension rawPosition) {
		model.changePixel(viewScaling.calcRawPixelPosition(rawPosition), currentColor);
		view.update();
	}
	
	public void drawLine(Dimension start, Dimension stop){
		model.drawLine(viewScaling.calcRawPixelPosition(start),viewScaling.calcRawPixelPosition(stop));
	}
		
	//Handling Files
	public void newProject() {
		pixelArray = new PixelArray(viewScaling);
		setupViewandModel();
	}
	
	public void openProject() {
		pixelArray = BMPFile.openBMP();
		setupViewandModel();
	}
	
	public void saveProject() {
		BMPFile.createBMP(pixelArray);
	}
		
	//update the view and model	
	private void setupViewandModel() {
		
		if(view.getPixelArrayStat()) {
			//modify the view after loading new pixel arrays
			view.changePixelArray(pixelArray);
		}
		else {
			//initial setup of the view
			view.setPixelArray(pixelArray, mouseAdapterController, viewScaling);
		}
		model.setPixelArray(pixelArray);
	}
}
