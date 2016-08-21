/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.remote;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author linuxadmin
 */
public class DBConnectionDriver implements Driver {

    private static String URL_PREFIX = "pool:jdbc:mysql";
    private DBConnectionPool pool;

    public DBConnectionDriver(String driverName, String url, String user, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        DriverManager.registerDriver(this);
        Class.forName(driverName).newInstance();
        pool = new DBConnectionPool(url, user, password);
    }

    public Connection connect(String url, Properties info) throws SQLException,CommunicationsException {
        if (acceptsURL(url)) {
            return pool.getConnection();
        }else {
            throw new SQLException("Connection Error");
        }
    }

    public boolean acceptsURL(String url) throws SQLException {

        if (url != null && url.startsWith(URL_PREFIX)) {
            return true;
        }
        return false;
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    public int getMajorVersion() {
        return 1;
    }

    public int getMinorVersion() {
        return 0;
    }

    public boolean jdbcCompliant() {
        return false;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return Logger.getLogger(this.toString());
    }
}
