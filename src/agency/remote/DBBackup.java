/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.remote;

import connn.ReadObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import agency.other.LReader;

/**
 *
 * @author Sandaruwan
 */
public class DBBackup {

    private static String USER = "root";
    private static String HOST = "localhost";
    private static String PW = "zegates";
    private static String DB = "tireshop";
    private static String RUSER = "tireshopdb";
    private static String RHOST = "182.50.133.152";
    private static String RPW = "TireZe2224";
    private static String RDB = "tireshopdb";
    private static LReader lReader = null;

    public static int backUpDB() throws IOException, InterruptedException, ClassNotFoundException {
        if (lReader == null) {
            initialize();
        }
        String path = lReader.getMySqlPath();
        //     System.out.println("ds " +path);
        // path=path.replaceAll("\\","\\\\");
        //      System.out.println("ds " +path);
        //  String bck = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --single-transaction --opt -u " + USER + " --host=" + HOST + " -p" + PW + " " + DB + " --add-drop-database --add-drop-table -r c:/temp.sql";

        String bck = path + "/mysqldump --single-transaction --opt -u " + USER + " --host=" + HOST + " -p" + PW + " " + DB + " --add-drop-database --add-drop-table  -r c:/temp.sql";
        //String bck = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --single-transaction --opt -u " + USER + " --host=" + HOST + " -p" + PW + " " + DB + " --add-drop-database --add-drop-table  -r c:/windows/temp.sql";

        //  System.out.println(bck);
        Process runTime = Runtime.getRuntime().exec(bck);
        //  System.out.println(bck);
        return runTime.waitFor();
    }

    public static int restoreBackUp() throws IOException, InterruptedException, ClassNotFoundException {
        if (lReader == null) {
            initialize();
        }
        String path = lReader.getMySqlPath();
        //   System.out.println("ds " +path);
        // path=path.replaceAll("\\\\","\\");
        String bck = path + "\\mysql -u " + RUSER + " --host=" + RHOST + " -p" + RPW + " " + RDB + " -e \"source c:/temp.sql\"";
        //     System.out.println(bck);
        Process runTime = Runtime.getRuntime().exec(bck);
        return runTime.waitFor();
    }

    private static void initialize() throws IOException, ClassNotFoundException {
        try {
            lReader = new LReader();
            RDB = lReader.getRDB();
            RHOST = lReader.getRHOST();
            RPW = lReader.getRPW();
            RUSER = lReader.getRUSER();

            RemoteDBConnector.d=RDB;
            RemoteDBConnector.h=RHOST;
            RemoteDBConnector.p=RPW;
            RemoteDBConnector.u=RUSER;
            
            ReadObject ro = new ReadObject();
            PW = ro.getUser().getPassword();
            USER = ro.getUser().getUsername();
        } catch (SAXException | ParserConfigurationException ex) {
            Logger.getLogger(DBBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
