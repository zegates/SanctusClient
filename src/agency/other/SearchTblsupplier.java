/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.other;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gayath
 */
public class SearchTblsupplier {

    private ArrayList<String> ar;
    private ArrayList<String> ar1;
    private ArrayList<String> ar2;
    private int rowCount;
    private int columnCount;
    public int searchcell;
    public boolean acending = true;
    String[][] searchData;
    DefaultTableModel dtm;

    public void SearchTblData(final JTable searchtbl, final JTextField txt) {
        rowCount = searchtbl.getRowCount();
        columnCount = searchtbl.getColumnCount();
        searchData = new String[rowCount][columnCount];
        ar = new ArrayList<>();
        ar1 = new ArrayList<>();
        ar2 = new ArrayList<>();
        dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) searchtbl.getModel();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                searchData[i][j] = searchtbl.getValueAt(i, j) + "";
            }
            ar.add(searchData[i][0]);
            ar1.add(searchData[i][1]);
            ar2.add(searchData[i][2]);
        }
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (searchcell == 0) {
                    String s = txt.getText();
                    int caret = txt.getCaretPosition();
                    dtm.setRowCount(0);
//                    if(acending){
//                        Collections.sort(ar);
//                    }else{
//                        Collections.reverse(ar);
//                    }
                    for (int i = 0; i < ar.size(); i++) {
                        if (ar.get(i).toUpperCase().contains(s.substring(0, caret).toUpperCase())) {
                            String[] row = new String[columnCount];
                            for (int j = 0; j < columnCount; j++) {
                                row[j] = searchData[i][j];
//                                System.out.println("AAAAA");
                            }
                            dtm.addRow(row);
                        }
                        txt.setText(s);
                        txt.setCaretPosition(caret);
                    }
                } else if (searchcell == 1) {
                    String s = txt.getText();
                    int caret = txt.getCaretPosition();
                    dtm.setRowCount(0);
//                    if(acending){
//                        Collections.sort(ar1);
//                    }else{
//                        Collections.reverse(ar1);
//                    }
                    for (int i = 0; i < ar1.size(); i++) {
                        if (ar1.get(i).toUpperCase().contains(s.substring(0, caret).toUpperCase())) {
                            String[] row = new String[columnCount];
                            for (int j = 0; j < columnCount; j++) {
                                row[j] = searchData[i][j];
//                                System.out.println("AAAAA");
                            }
                            dtm.addRow(row);
                        }
                        txt.setText(s);
                        txt.setCaretPosition(caret);
                    }
                } else if (searchcell == 2) {
                    String s = txt.getText();
                    int caret = txt.getCaretPosition();
                    dtm.setRowCount(0);
//                    if(acending){
//                        Collections.sort(ar2);
//                    }else{
//                        Collections.reverse(ar2);
//                    }
                    for (int i = 0; i < ar2.size(); i++) {
                        if (ar2.get(i).toUpperCase().contains(s.substring(0, caret).toUpperCase())) {
                            String[] row = new String[columnCount];
                            for (int j = 0; j < columnCount; j++) {
                                row[j] = searchData[i][j];
//                                System.out.println("AAAAA");
                            }
                            dtm.addRow(row);
                        }
                        txt.setText(s);
                        txt.setCaretPosition(caret);
                    }
                }
            }
        });
    }
}
