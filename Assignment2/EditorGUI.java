import java.awt.*;                              // include window-handling
import java.awt.event.*;                        // include event-handling
import javax.swing.*;                           // include Swing


public class EditorGUI extends JFrame implements ActionListener {

  // ------------------------------ Constants ------------------------------

  /** Text area height (rows) */
  private final static int textHeight = 30;

  /** Text area horizonal inset (pixels) */
  private final static int textInset = 10;

  /** Text area width (columns) */
  public final static int textWidth = 60;

  // ------------------------------ GUI components ------------------------------

  /** Button panel */
  private JPanel buttonPanel = new JPanel();

  /** Clear button */
  private JButton clearButton = new JButton("Clear");

  /** Text formatter */
  private Formatter formatter;

  /** Find button */
  private JButton findButton = new JButton("Find:");

  /** Find field */
  private JTextField findField = new JTextField(textWidth / 5);

  /** Lines button */
  private JButton linesButton = new JButton("Lines:");

  /** Replace button */
  private JButton replaceButton = new JButton("Replace:");

  /** Replace field */
  private JTextField replaceField = new JTextField(textWidth / 5);

  /** Status field */
  private JLabel statusLabel = new JLabel("Ready");

  /** Text area */
  private JTextArea textArea = new JTextArea(textHeight, textWidth);

  /** Selected text background colour */
  private Color textBackground = new Color(250, 240, 230);

  /** Text font */
  private Font textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

  /** Selected text foreground colour */
  private Color textForeground = new Color(255, 0, 50);

  /** Scrollable pane */
  private JScrollPane textPane = new JScrollPane(
    textArea,
    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
  );

  /** Tidy button */
  private JButton tidyButton = new JButton("Tidy");

  /** Words button */
  private JButton wordsButton = new JButton("Words");

  // ------------------------------ Constructors ------------------------------

  /**
    Make application window and create a new text formatter.
  */
  public EditorGUI() {              // construct application
    setTitle("Editor");             // set window title
    createGUI();                    // configure window
    pack();                         // pack window components and set window size
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // close window on exit
    formatter = new Formatter(textArea);    // create new text formatter
  }

  /**
    Initialise editor application and set up on-screen display.
   */
  private void createGUI() {
    Container contentPane = getContentPane();   // get content pane
    contentPane.setLayout(new BorderLayout());  // create border layout

    buttonPanel.add(clearButton);           // add clear button to panel
    clearButton.addActionListener(this);    // listen for for button
    buttonPanel.add(linesButton);           // add lines button to panel
    linesButton.addActionListener(this);    // listen for words lines
    buttonPanel.add(wordsButton);           // add words button to panel
    wordsButton.addActionListener(this);    // listen for words button
    buttonPanel.add(tidyButton);            // add tidy button to panel
    tidyButton.addActionListener(this);     // listen for tidy button
    buttonPanel.add(findButton);            // add find button to panel
    findButton.addActionListener(this);     // listen for find button
    buttonPanel.add(findField);             // add find field to panel
    buttonPanel.add(replaceButton);         // add replace button to panel
    replaceButton.addActionListener(this);  // listen for replace button
    buttonPanel.add(replaceField);          // add replace field to panel

    textArea.setFont(textFont);             // set text font
    textArea.setSelectionColor(textBackground); // set selection background
    textArea.setSelectedTextColor(textForeground); // set selection foreground

    contentPane.add(buttonPanel, BorderLayout.NORTH);   // add button panel to layout
    contentPane.add(Box.createHorizontalStrut(textInset), BorderLayout.WEST);   // add main area to layout
    contentPane.add(textPane, BorderLayout.CENTER);  // add main area to layout
    contentPane.add(Box.createHorizontalStrut(textInset), BorderLayout.EAST); // add main area to layout
    contentPane.add(statusLabel, BorderLayout.SOUTH); // add status label to layout
  }

  // ------------------------------ Methods ------------------------------

  /**
    Deal with user action.

    @param event    user action
  */
  public void actionPerformed(ActionEvent event) { // check user action
    String status = "";                            // initialise status
    if (event.getSource() == clearButton) {        // clear button clicked?
      formatter.clearText();                       // clear text
      status = "Text: cleared";                    // set status
    }
    else if (event.getSource() == findButton) {   // find button clicked?
      String searchText = findField.getText();    // get search text to find
      boolean found = formatter.findText(searchText); // find text
      if (found)                // word found?
        status = "Found: " + searchText;          // set status
      else                      // text not found
        status = "Not found: " + searchText;      // set status
    }
    else if (event.getSource() == linesButton) {  // lines button clicked?
      int count = formatter.countLines();         // count lines
      status = "Lines: " + count;                 // set status
    }
    else if (event.getSource() == replaceButton) { // replace button clicked?
      String searchText = findField.getText();    // get search text
      String replacementText = replaceField.getText();// get replacement text
      boolean found = formatter.replaceText(searchText, replacementText); // replace text
      if (found)                // text replaced?
        status = "Replaced: " + searchText;      // set status
      else                      // text not found
        status = "Not replaced: " + searchText;  // set status
    }
 
  else if (event.getSource() == tidyButton) {  // tidy button clicked?
  
        formatter.tidyText( );                      // tidy text
 
        status = "Text: tidied";                   // set status
   }
    else if (event.getSource() == wordsButton) { // count button clicked?
      int count = formatter.countWords();        // count words
      status = "Words: " + count;                // set status
    }
    textArea.requestFocus();                     // adjust focus to show selection
    showStatus(status);                          // update status line
  }

  /**
    Display status in status field.

    @param status   status message
  */
  private void showStatus(String status) {  // show status
    statusLabel.setText(status);        // display status
  }

}
