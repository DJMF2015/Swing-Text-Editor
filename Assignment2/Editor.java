import java.awt.*;    // For window operations

/**
  Final version of the main class that supports a simple text editor. It
  operates on text in the main area using the following functions.

  <ul>

    <li>Clear: remove all text</li>

    <li>Lines: show a line count</li>

    <li>Words: show a word count</li>

    <li>Tidy: format paragraphs to fit the main area</li>

    <li>Find: find text in the main area</li>

    <li>Replace: find and replace text in the main area</li>

  </ul>

  @author       Simon Jones (based on original code by Kenneth J. Turner)
  @version      16/11/2015
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
