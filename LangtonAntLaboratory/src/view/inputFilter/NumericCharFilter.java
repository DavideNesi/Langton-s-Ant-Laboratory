/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.inputFilter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericCharFilter extends DocumentFilter {

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset,
            String string, AttributeSet attr)
            throws BadLocationException {

        StringBuilder buffer = new StringBuilder(string);
        for (int i = buffer.length() - 1; i >= 0; i--) {
            char ch = buffer.charAt(i);
            if (!Character.isLetter(ch) && !Character.isDigit(ch)) {
                buffer.deleteCharAt(i);
            }
        }
        super.insertString(fb, offset, buffer.toString(), attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb,
            int offset, int length, String string, AttributeSet attr) throws BadLocationException {
        if (length > 0) {
            fb.remove(offset, length);
        }
        insertString(fb, offset, string, attr);
    }
}
