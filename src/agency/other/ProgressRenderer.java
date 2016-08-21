/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.other;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Sandaruwan
 */
public class ProgressRenderer extends DefaultTableCellRenderer {

    private final JProgressBar b = new JProgressBar(0, 100);

    public ProgressRenderer() {
        super();
        setOpaque(true);
        b.setStringPainted(true);
        b.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        b.setUI(new GradientPalletProgressBarUI());
        //b.addPropertyChangeListener(new ProgressListener(b));






    }
//    private int valueTask=0;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Integer i = (Integer) value;
        String text = "Completed";
        if (i == null) {
            i = 0;
        }
        b.setString(value + "%");
        final int valueTask = new Integer(i);
        if (i < 0) {
            text = "Error";
        } else if (i <= 100) {
            b.setValue(i);
            return b;
        }

        super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
        return this;
    }
}