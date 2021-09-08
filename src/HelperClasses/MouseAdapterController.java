package HelperClasses;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import Tools.ITool;

public class MouseAdapterController {
	
	private MouseAdapter mouseAdapter;
	private MouseMotionAdapter mouseMotionAdapter;
	private ITool currentTool;
	
	
	public MouseAdapterController(ITool currentTool){
		this.currentTool = currentTool;
		
		mouseMotionAdapter = new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				MouseAdapterController.this.currentTool.mouseDragging(e);
			}
		};
		
		mouseAdapter = new MouseAdapter(){
			public void mousePressed(MouseEvent e) {		
				MouseAdapterController.this.currentTool.mousePress(e);
			}
							
			public void mouseReleased(MouseEvent e) {
				MouseAdapterController.this.currentTool.mouseRelease(e);
			}
		};
	}
	
	public void setCurrentTool(ITool tool) {
		this.currentTool = tool;
	};
	
	public MouseAdapter getMouseAdapter() {
		return this.mouseAdapter;
	}
	
	public MouseMotionAdapter getMouseMotionAdapter() {
		return this.mouseMotionAdapter;
	}
}
