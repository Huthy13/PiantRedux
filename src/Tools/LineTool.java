package Tools;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import MVC.EditorController;

public class LineTool implements ITool{
	private EditorController controller;
	private Dimension start;
	private Dimension stop;
	
	public LineTool(EditorController controller) {
		this.controller = controller;
	}
		
	@Override
	public void mousePress(MouseEvent e) {
		this.start = new Dimension(e.getX(),e.getY());		
	}

	@Override
	public void mouseRelease(MouseEvent e) {

		
	}

	@Override
	public void mouseDragging(MouseEvent e) {
		this.stop = new Dimension(e.getX(),e.getY());
		controller.drawLine(start, stop);
		
	}

	
	
}
