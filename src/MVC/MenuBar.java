package MVC;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Tools.*;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar{
	
	private EditorController controller;
	
	public void setupMenuBar(EditorController controller) {
		this.controller = controller;
	}
	
	//Main Menu Bar
	public MenuBar(){
		JMenu fileMenu = new JMenu("File");
		JMenu viewMenu = new JMenu("View");
		JMenu toolMenu = new JMenu("Tools");
		JMenu colorMenu = new JMenu("Color");
		
		//add tabs to menu bar
		this.add(fileMenu);
		this.add(viewMenu);
		this.add(toolMenu);
		this.add(colorMenu);
		
		//File menu Selection
		JMenuItem newPB = new JMenuItem("New");
		JMenuItem savePB = new JMenuItem("Save");
		JMenuItem openPB = new JMenuItem("Open");
		
		//view menu selection
		JMenuItem zoomInPB = new JMenuItem("Zoom In");
		JMenuItem zoomOutPB = new JMenuItem("Zoom Out");
		
		//tool menu selection
		JMenuItem pencilPB = new JMenuItem("Pencil");
		JMenuItem linePB = new JMenuItem("Line");
		
		JMenuItem redPB = new JMenuItem("Red");
		JMenuItem bluePB = new JMenuItem("Blue");
		JMenuItem greenPB = new JMenuItem("Green");
		
		//add options to file menu
		fileMenu.add(newPB);
		fileMenu.add(savePB);
		fileMenu.add(openPB);
		
		//add options to view menu
		viewMenu.add(zoomInPB);
		viewMenu.add(zoomOutPB);
		
		//add options to tool menu
		toolMenu.add(pencilPB);
		toolMenu.add(linePB);
		
		//add options to color menu
		colorMenu.add(redPB);
		colorMenu.add(bluePB);
		colorMenu.add(greenPB);
		
		
	 
		//*******add listeners to the File menu*******	 
		class newListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				controller.newProject();	
			}
		}
		
		class saveListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				controller.saveProject();
			}
		}	
		
		class openListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				controller.openProject();		
			}
		}
	 
		//create objects for file listeners
		newListener newListenerInstance = new newListener();
		saveListener saveListenerInstance = new saveListener();
		openListener openListenerInstance = new openListener();
    
		//assigns listeners objects to screen objects
		newPB.addActionListener(newListenerInstance);
		savePB.addActionListener(saveListenerInstance);
		openPB.addActionListener(openListenerInstance);
	
	
	
		//*******add listeners to the View menu*******	 
		class ZoomInListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				controller.zoom(true);	
			}
		}
	
		class ZoomOutListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				controller.zoom(false);	
			}
		}
	
		//create objects for view listeners
		ZoomInListener zoomInListenerInstance = new ZoomInListener();
		ZoomOutListener zoomOutListenerInstance = new ZoomOutListener();

		//assigns listeners objects to screen objects
		zoomInPB.addActionListener(zoomInListenerInstance);
		zoomOutPB.addActionListener(zoomOutListenerInstance);
	
		
		//*******add listeners to the Tool menu*******	 
		class PencilListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				controller.setCurrentTool(new PencilTool(controller));	
			}
		}
		 
		class LineListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				controller.setCurrentTool(new LineTool(controller));		
			}
		}
	
		//create objects for view listeners
		PencilListener pencilListenerInstance = new PencilListener();
		LineListener lineListenerInstance = new LineListener();

		//assigns listeners objects to screen objects
		pencilPB.addActionListener(pencilListenerInstance);
		linePB.addActionListener(lineListenerInstance);
		
		//*******add listeners to the color menu*******	 
		class RedListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				controller.setCurrentColor(Color.RED);
			}
		}
		
		class BlueListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				controller.setCurrentColor(Color.BLUE);	
			}
		}	
		
		class GreenListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				controller.setCurrentColor(Color.GREEN);		
			}
		}
	 
		//create objects for file listeners
		RedListener redListenerInstance = new RedListener();
		GreenListener greenListenerInstance = new GreenListener();
		BlueListener blueListenerInstance = new BlueListener();
    
		//assigns listeners objects to screen objects
		redPB.addActionListener(redListenerInstance);
		greenPB.addActionListener(greenListenerInstance);
		bluePB.addActionListener(blueListenerInstance);
		
		
	}
}
