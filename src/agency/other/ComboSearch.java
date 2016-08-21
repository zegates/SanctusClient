package agency.other;


import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class ComboSearch {

    private ArrayList<String> ar = new ArrayList<>();
    private JTextField txt;

    public void setSearchableCombo(final JComboBox cmb, boolean mustSort, final String noReultsText) {
        ar = new ArrayList<>();
        final int s = cmb.getItemCount();
        for (int i = 0; i < s; i++) {
            boolean exists = false;
            for (int j = 0; j < ar.size(); j++) {
                if (ar.get(j).equalsIgnoreCase(cmb.getItemAt(i) + "")) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                ar.add(cmb.getItemAt(i) + "");
            }
        }
        if (mustSort) {
            Collections.sort(ar);
        }
        cmb.setLightWeightPopupEnabled(true);
        txt = (JTextField) cmb.getEditor().getEditorComponent();
        
        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent ev){
                txt.selectAll();
            }
        
        });
        
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                int key = evt.getKeyCode();
                if (!(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP)) {
                    String s = txt.getText();
                    int caret = txt.getCaretPosition();
                    cmb.hidePopup();
                    cmb.removeAllItems();
                    for (int i = 0; i < ar.size(); i++) {
                        if (ar.get(i).trim().toLowerCase().contains(s.substring(0, caret))) {
                            cmb.addItem(ar.get(i));
                        }
                    }
                    cmb.showPopup();
                    if (cmb.getItemCount() == 0) {
                        cmb.addItem(noReultsText);
                    }
                    txt.setText(s);
                    txt.setCaretPosition(caret);
                } else if (key == KeyEvent.VK_ESCAPE) {
                    cmb.setSelectedItem(txt.getText());
                    cmb.hidePopup();
                } else if (key == KeyEvent.VK_ENTER && cmb.getSelectedIndex()==-1) {
                    if(cmb.getItemCount()==1 && !cmb.getItemAt(0).equals(noReultsText)){
                        cmb.setSelectedIndex(0) ;
                    }else if(cmb.getItemCount()>1){
                        cmb.setSelectedIndex(0) ;
                    }
                }
            }
        });
    }
    
    public void refreshList(JComboBox cmb){
        ar.clear();
        for (int i = 0; i < cmb.getItemCount(); i++) {
            boolean exists = false;
            for (int j = 0; j < ar.size(); j++) {
                if (ar.get(j).equalsIgnoreCase(cmb.getItemAt(i) + "")) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                ar.add(cmb.getItemAt(i) + "");
            }
        }
    }
}
