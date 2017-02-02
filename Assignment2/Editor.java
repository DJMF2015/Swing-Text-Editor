import java.awt.*;    // For window operations

/**
  Final version of the main class that supports a simple text editor. It
  operates on text in the main area using the following functions.


*/

public class Editor {

  /**
    Start application, setting set up on-screen display.

    @param args         command-line arguments (unused)
  */
  public static void main(String args[]) {	// main program
    EditorGUI editor = new EditorGUI();		// create text editor window
    // place window in centre of screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = editor.getSize();
    editor.setLocation(
      screenSize.width / 2 - (frameSize.width / 2),
      screenSize.height / 2 - (frameSize.height / 2));
    editor.setVisible(true);				// make window visible
  }

}
