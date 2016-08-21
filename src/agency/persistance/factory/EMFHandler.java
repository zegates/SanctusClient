/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.factory;

import connn.ReadObject;
import connn.WriteObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import model.SQLUser;
import org.xml.sax.SAXException;

/**
 *
 * @author Sandaruwan
 */
public class EMFHandler {

    private static EntityManagerFactory emf = null;

    static {
        try {
            ReadObject ro = new ReadObject();
            SQLUser user = ro.getUser();
            Map<String, String> map = new HashMap();
            map.put("javax.persistence.jdbc.user",user.getUsername() );
            map.put("javax.persistence.jdbc.password", user.getPassword());
            map.put("eclipselink.ddl-generation","drop-and-create-tables");
            emf = Persistence.createEntityManagerFactory("TyreShopPU", map);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Configuration files not found");
            System.exit(0);
        } 
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }
}
