package Tools;
import MVC.EditorController;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

public class PencilTool implements ITool{
	EditorController controller;
	
	public PencilTool(EditorController controller) {
		this.controller = controller;
	}
	
	public void mousePress(MouseEvent e) {
		controller.modifyPixel(new Dimension(e.getX(),e.getY()));
	}
	
	public void mouseRelease(MouseEvent e) {
		return;		
	}
	
	public void mouseDragging(MouseEvent e) {
		controller.modifyPixel(new Dimension(e.getX(),e.getY()));
	}

}
