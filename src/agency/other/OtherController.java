/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.other;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

/**
 *
 * @author Sandaruwan
 */
public class OtherController {

    public static String formatCode(String suffix, Long id, int length) {
        if (id.toString().length() > length) {
            length = id.toString().length() + 1;
        }
        return String.format(suffix + "%0" + length + "d", id);
    }

    public static void setIntegerOnly(JTextField txt) {
        Document doc = txt.getDocument();
        if (doc instanceof AbstractDocument) {
            AbstractDocument abDoc = (AbstractDocument) doc;
            abDoc.setDocumentFilter(new IntegerOnly());
        }
    }

    public static String formatPrice(double price) {
        return String.format("%,.2f", price);
    }

    public static String getDate() {
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String date = year + "-" + (month + 1) + "-" + day;

        return date;
    }

    public static String getCurretTime() {
        Calendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return "" + hour + ":" + minute + ":" + second;
    }

    public static Long stripOutItemCode(String itemCode) {
        return Long.parseLong(itemCode.substring(1));
    }
}
