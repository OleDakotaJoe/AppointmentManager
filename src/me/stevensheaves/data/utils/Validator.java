package me.stevensheaves.data.utils;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import me.stevensheaves.custom.controls.TextFieldLimited;

public class Validator {
    /**
     * Validates text fields in javaFx
     * This method is the primary method of the Validator class. It can be used standalone or as a part of the other methods in the class.
     * It finds incorrect data based ont the <code>regex</code> parameter and replaces it with an empty string.
     * This method should be called on a <code>KeyEvent</code>.
     * The method also ignores non-text input, such as arrowKeys, functionKeys, Modifier Keys, NavigationKeys, as well
     * as ignoring when shift or control is down, to prevent buggy behavior)
     * @param event
     * The <code>KeyEvent</code> which is calling the function.
     * @param regex
     * The regular expression to be removed from the expression.
     * @return
     * returns the <code>TextField</code> element which was being validated, for further validation.
     */
    private static TextField textFieldValidator(KeyEvent event, String regex) {
        TextField element = (TextField) event.getSource();
        int caretPosition = element.getCaretPosition();
        int textLength = element.getText().length();
        if ( !event.getCode().isArrowKey()
                && !event.getCode().isFunctionKey()
                && !event.getCode().isModifierKey()
                && !event.getCode().isNavigationKey()
                && !event.isControlDown()
                && !event.isShiftDown()
        ) {
            element.setText(element.getText().replaceAll(regex, ""));
            if(caretPosition < textLength) {
                if(element.getText().matches(regex)) {
                    element.positionCaret(caretPosition - 1);
                } else {
                    element.positionCaret(caretPosition);
                }
            } else {
                element.positionCaret(textLength);
            }
        }
        return element;
    }

    /**
     * Wrapper method which calls the <code>textFieldValidator()</code> method, with the appropriate regular expression to remove all letters.
     * @param event The <code>KeyEvent</code> Object which is passed in when calling this method. Used to determine which element to modify.
     */
    public static void removeLetters(KeyEvent event) {
        textFieldValidator(event, "[a-zA-Z]");
    }

}
