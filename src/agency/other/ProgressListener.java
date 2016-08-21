/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.other;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JProgressBar;

/**
 *
 * @author Sandaruwan
 */
public class ProgressListener implements PropertyChangeListener {

    private final JProgressBar progressBar;

    ProgressListener(JProgressBar progressBar) {
        this.progressBar = progressBar;
        this.progressBar.setValue(0);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String strPropertyName = evt.getPropertyName();
        if ("progress".equals(strPropertyName)) {
            progressBar.setIndeterminate(false);
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
            ///progressBar.setString(progress+"%");
        }
    }
}