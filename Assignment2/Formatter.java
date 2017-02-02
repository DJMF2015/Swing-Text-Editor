import java.awt.*;                              // include window-handling
import java.io.*;                               // include file-handling
import javax.swing.*; 
import javax.swing.text.*;                          // include Swing

/**
Incomplete version of a formatter class to support a simple text editor.

@author       Simon Jones (based on original code by Kenneth J. Turner)
@version      16/11/2015
 */
public class Formatter {

    // ------------------------------ Constants ------------------------------

    /** Text area width (characters) */
    public final static int textWidth = 80;

    // ------------------------------ Variables ------------------------------

    /** Text area */
    private JTextArea textArea;

    /** Current text length */
    private int textLength;

    /** Current text position (character offset) */
    private int textPosition = 0;

    /** Current text words */
    private String textWords;

    // ------------------------------ Constructor (complete) ------------------------------

    /**
    Create the text formatter, noting the main text area.

    @param textPane text area in user interface
     */
    public Formatter(JTextArea textArea) { // formatter constructor
        this.textArea = textArea;            // set text area
    }

    // ------------------------------ Methods (complete) ------------------------------

    /**
    Display debug message in the console window.

    @param message  message to be displayed
     */
    private void debug(String message) {    // show debug message
        System.err.println(message);        // display debug message
    }

    /**
    Get the current text details, storing the text in <var>textWords</var>, and
    the cursor position in <var>textPosition</var>.
     */
    public void getText() {             // get current text details
        textWords = textArea.getText();  // get text area position
        textPosition = textArea.getCaretPosition(); 
  
    }

    /**
    Highlight selection in text area, e.g. start 3 end 6 selects characters 3, 4
    and 5.

    @param position1    start of selection (first character)
    @param position2    end of selection (last character - not selected)
     */
    public void setSelection(int position1, int position2) { // set selection
        textArea.setCaretPosition(position1);   // set text selection start
        textArea.moveCaretPosition(position2);  // set text selection end
    }

    // ------------------------------ Methods (incomplete) ------------------------------

    /**
    Remove all text in the main area.
     */
    public void clearText() {           // clear text area
        textArea.setText(""); //clear textArea with empty string
        /**
        Count lines in main area, considering a line to be one or more characters
        ending with a newline (or the end of the text).

        @return     line count
         */
    }

    public int countLines() {  
        int Count = 0;
        int lineStart=0;
        getText();
        textLength=textWords.length();
        int charCount;

        for(charCount=0;charCount<textLength -1;charCount++){
            char c;
            c = textWords.charAt(charCount);
            if(c == '\n' && (textWords.charAt(charCount + 1) != '\n'))
                Count++;
        }
        
        if(textWords.charAt(charCount) == '\n')
            Count++;
        return Count;
    }
    /**
    Count words in main area, considering a word to be a number of consecutive
    non-space characters.
    @return     word count
     */
    /* The number of lines is just one plus the number of times the
    end of line character, '\n', occurs in the text. */
    public int countWords(){
        int wordCount = 0;
        int charCount = 0;
        textWords = textArea.getText();
        charCount = textWords.length();
        for (int i = 0; i < charCount; i++) {
            boolean  inWord;  // Is character i the start of a word?
            if ( Character.isLetter (textWords.charAt(i)) == false )//Character.isLetterOrDigit  http://www.dotnetperls.com/character
                inWord= false;  // No.  It's not a letter.
            else if (i == 0)
                inWord = true;   // Yes.  It's a letter at start of text.
            else if ( Character.isLetter(textWords.charAt(i-1)) )
                inWord = false;  // No.  It's a letter preceded by a letter.
            else if ( textWords.charAt(i-1) == '\'' && i > 1 
            && Character.isLetter(textWords.charAt(i-2)) )
                inWord = false;  // No.  It's a continuation of a word
            //      after an apostrophe.
            else
                inWord = true;   // Yes.  It's a letter preceded by
            //       a non-letter.
            if ( inWord)
                wordCount++;
        }return wordCount;
    }

    /**
    Search for text in main area, starting from the current cursor position.

    @param searchText   text to be searched for
    @return     true/false if text found/not found

     */
    public boolean findText(String searchText) {    // find text in main area
        boolean found = false;  // initialise find result  
        int startPos = 0;
        textWords = textArea.getText();
        textPosition = textArea.getCaretPosition(); 
        startPos = textWords.indexOf(searchText, textPosition);
        int endPos = searchText.length() + startPos;

        if (startPos>= 0){
            found = true;
            setSelection( startPos, endPos);
            //else false;
            // *** get current text   
            // *** get search text length
            // *** if search text is non-empty
            // ***   get text
            // ***   look for search text from current position
            // ***   if search text is found
            // ***     set selection start
            // ***     move current position to end of found text
            // ***     set selection using start and end of found text
            //  note text as found
        }
        return found;   // return find result

    }

    /**
    Replace text in main area.

    @param searchText       text to be searched for
    @param replacementText  replacement text
    @return         true/false if text replaced/not found
     */
    public boolean replaceText(String searchText, String replacementText) { // Replace text in main area
        boolean replaced = false;   // initialise replace result
        int startPos = 0;
        textWords = textArea.getText();
        textPosition = textArea.getCaretPosition();//??//
        startPos = textWords.indexOf(searchText, textPosition);//start from textPosition look for searchText and return the index (int) of 1st (0) char of searchText
        int endPos = searchText.length() + startPos;
        if (startPos >= 0 && searchText.length() > 0){
            textArea.replaceRange(replacementText, startPos, endPos);
            replaced = true;
            setSelection(startPos,endPos+1);//??
        }
        return replaced;
        // *** get search text length
        // *** get replacement text length
        // *** set whether search text can be found
        // *** if search text is found
        // ***   calculate start of search text
        // ***   update text, replacing the found text with the replacement text
        // ***   set the text area to the new text
        // ***   move the text position to the end of the replacement text
        // ***   set selection using start and end of replacement text
        // ***   note text as replaced
        // return replace result
    }

    /**
    Tidy text in main area:

    <ul>

    <li>
    Ensure a single space between words and one blank line between
    paragraphs. Remove an extra space at the start of end of a line.
    </li>

    </ul>
     */
    public void tidyText() {            // tidy text in main area
        // Tidy text
        int startPos = 0;
        String tidy=textWords;
        textWords = textArea.getText();
        textWords = textWords.trim().replaceAll(" +", " "); // this will take care of multiple lines and spaces in the end
        if(!(textWords.substring(textWords.length() - 1) == "\n")){
            textWords = textWords + "\n"; //this adds a new line in the end if there isn't one already
        }
        textWords = textWords.replaceAll("\n{3,}", "\n\n"); //to replace three or more consecutive new lines
        textArea.setText(textWords); // this will set the text on the text area
        
        
        
        
        // int charCount = textWords.length();
        // int lineStart = 0;
        // for(int i = 0;i<charCount;i++){//?? i<textLength??
        //     //  // *** get text
        //   String textWords = result.trim(;)
        //   textWords = textWords.replaceAll("\\t", "");
        //     //    *** replace multiple consecutive spaces by one space
        // }
        //?? // *** remove a space at the end of the text (i.e. '$')
        // *** remove a space at the start of a line (i.e. after a newline)

        // 
        //   textWords = textWords.replaceAll("\\n", "");
        //  textWords = textWords.replaceAll(" ", "");
        //   textWords = textWords.replaceAll("(?m)^[ \t]*\r?\n", "");// *** remove a space at the end of a line (i.e. before a newline)
        // *** replace three or more consecutive newlines by two newlines
        // *** if text does not end with a newline
        // ***   append a newline
        // *** update text area with new text
    }
}

