package MVC;

public class Main {

	public static void main(String[] args) {
		
		MenuBar menu = new MenuBar();
		
		
		EditorModel model = new EditorModel();
		EditorView view = new EditorView(menu);
				
		EditorController controller = new EditorController(view,model);
			
		menu.setupMenuBar(controller);
		
	}

}
