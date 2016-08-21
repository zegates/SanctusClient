/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.other;

import tireshop.other.License;
import connn.ReadObject;
import connn.WriteObject;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import agency.remote.RemoteServer;

/**
 *
 * @author Sandaruwan
 */
public class ObjectWriter {

    public static void main(String[] args) {
        try {

    //        License l = new License();
    //        l.setValid(true);
    //       // l.setKey("Zega19728TireG");
    //        ObjectOutputStream oos = null;
    //        try {
    //            FileOutputStream fos = new FileOutputStream("license.vbt");
    //            oos = new ObjectOutputStream(fos);
    //            oos.writeObject(l);
    //        } catch (FileNotFoundException ex) {
    //            Logger.getLogger(ObjectWriter.class.getName()).log(Level.SEVERE, null, ex);
    //        } catch (IOException ex) {
    //            Logger.getLogger(ObjectWriter.class.getName()).log(Level.SEVERE, null, ex);
    //        } finally {
    //            try {
    //                oos.close();
    //            } catch (IOException ex) {
    //                Logger.getLogger(ObjectWriter.class.getName()).log(Level.SEVERE, null, ex);
    //            }
    //        }
            
            
            License l = new License();
            l.setValid(true);
            l.setKey("Zega19728TireG");
            
            RemoteServer remoteServer = new RemoteServer();
            remoteServer.setrDB("lasantha_tyre");
            //remoteServer.setrHOST("216.172.181.205");
          //  remoteServer.setrHOST("182.50.133.152");
            remoteServer.setrPW("BqbVzIhufO_z");
            remoteServer.setrUSER("lasantha_tyre");
            remoteServer.setlDB("tireshop");
            remoteServer.setlHOST("localhost");
            ReadObject ro = new ReadObject();
            remoteServer.setlPW(ro.getUser().getPassword());
            remoteServer.setlUSER(ro.getUser().getUsername());
            
            ObjectOutputStream oos = null;
            try {
                FileOutputStream fos = new FileOutputStream("Remote.rif");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(remoteServer);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ObjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ObjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(ObjectWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SAXException | ParserConfigurationException | IOException ex) {
            Logger.getLogger(ObjectWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
