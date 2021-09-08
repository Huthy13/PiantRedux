package Tools;

import java.awt.event.MouseEvent;

public interface ITool{

	public void mousePress(MouseEvent e);
	public void mouseRelease(MouseEvent e);
	public void mouseDragging(MouseEvent e);
	
}
