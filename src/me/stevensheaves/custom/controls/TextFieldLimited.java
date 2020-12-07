package me.stevensheaves.custom.controls;

import javafx.beans.NamedArg;
import javafx.scene.control.TextField;

/**
 * Text input component extends the functionality of the <code>TextField</code> class to set a maximum number of characters as input.
 */
public class TextFieldLimited extends TextField {
    /**
     * Holds integer value for maximum length of input
     */
    private int maxLength;

    /**
     * Creates a <code>TextFieldLimited</code> component with a maximum input length of 9 characters,
     * unless a <code>maxLength</code> property is specified. The default value is 9 to ensure safe integers.
     * @param maxLength
     * A <code>maxLength</code> property can be specified either in the .fxml file, as a property (example: <code>maxLength="26"</code>)
     * or in code. To specify in code, <code>TextFieldLimited.setMaxLength(int maxLengthToBeSet);</code>
     */
    public TextFieldLimited(@NamedArg("maxLength") String maxLength) {
        if (maxLength!=null){
            setMaxLength(Integer.parseInt(maxLength));
        } else {
            setMaxLength(9);
        }

    }

    /**
     * Sets the <code>maxLength</code> value of a <code>TextFieldLimited</code> instance.
     * @param maxLength
     * Can either be set in code using <code>(component).setMaxLength(int maxLength)</code> or in .fxml file
     * by using (example: <code>maxLength="26"</code>) as a tag.
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     *
     * Deletes or backspaces the user's input
     * @param start
     * The starting index in the range, inclusive.
     * @param end
     * The ending index in the range, exclusive. This is one-past the last character to delete (consistent with the String manipulation methods).
     * @param text
     *  The text that is to replace the range. This must not be null.
     */
    @Override
    public void replaceText(int start, int end, String text) {
        if (text.equals("")) {
            super.replaceText(start, end, text);
        } else if (getText().length() < maxLength) {
            super.replaceText(start, end, text);
        }
    }

    /**
     * Deletes or backspaces the users input whenever the text being appended is longer than the max length.
     * @param text
     * The current text inside the <code>TextFieldLimited</code> component.
     */
    @Override
    public void replaceSelection(String text) {
        // Delete or backspace user input.
        if (text.equals("")) {
            super.replaceSelection(text);
        } else if (getText().length() < maxLength) {
            // Add characters, but don't exceed maxLength.
            if (text.length() > maxLength - getText().length()) {
                text = text.substring(0, maxLength- getText().length());
            }
            super.replaceSelection(text);
        }
    }
}
