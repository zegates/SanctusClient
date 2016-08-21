/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.other;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Nanayakkara
 */
public class IntegerOnly extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet as) throws BadLocationException {
        int len = text.length();
        if (len > 0) {
            /* Here you can place your other checks 
             * that you need to perform and do add 
             * the same checks for replace method 
             * as well. 
             */
            if (Character.isDigit(text.charAt(len - 1))) {
                super.insertString(fb, offset, text, as);
            } else if ((text.charAt(len - 1) + "").equals(".")) {
                super.insertString(fb, offset, text, as);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet as) throws BadLocationException {
        int len = text.length();
        if (len > 0) {
            if (Character.isDigit(text.charAt(len - 1))) {
                super.replace(fb, offset, length, text, as);
            } else if ((text.charAt(len - 1) + "").equals(".")) {
                super.insertString(fb, offset, text, as);
            } else {
//                    JOptionPane.showMessageDialog(null, "Please Enter a valid Integer Value." 
//                                                            , "Invalid Input : ", JOptionPane.ERROR_MESSAGE); 
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
