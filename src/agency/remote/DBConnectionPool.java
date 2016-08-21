/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.remote;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author linuxadmin
 */
class ConnectionChecker extends Thread {

    private DBConnectionPool pool;

    public ConnectionChecker(DBConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(300000);
                pool.checkConnections();
            } catch (InterruptedException ex) {
                Logger.getLogger(ConnectionChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}

public class DBConnectionPool {

    private ArrayList<DBConnection> allConnections;
    private String url, user, password;
    private int pool_capacity = 10;
    private static int timeOut = 60000;

    public DBConnectionPool() {
    }

    public DBConnectionPool(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

        allConnections = new ArrayList<DBConnection>(pool_capacity);

        new ConnectionChecker(this).start();

        System.out.println("pool created...");

    }

    public DBConnection getConnection() throws SQLException,CommunicationsException  {
        System.out.println("getting connection.....");
        // check list to find an available DBConnection
        for (DBConnection con : allConnections) {
            if (con.lease()) {
                return con;
            }
        }

        //com.mysql.jdbc.Driver
        Connection con = DriverManager.getConnection(url, user, password);
        DBConnection dbCon = new DBConnection(con, this);

        dbCon.lease();
        allConnections.add(dbCon);
        return dbCon;
    }

    public void removeConnection(DBConnection dbCon) {
        allConnections.remove(dbCon);
    }

    public void checkConnections() {
        Long now = System.currentTimeMillis();
        for (DBConnection con : allConnections) {

            if (con.isInUse() && (now - con.getLastUse()) > timeOut && !con.validate()) {
                removeConnection(con);
            }
        }

    }

    public void removeAll() {
        for (DBConnection con : allConnections) {
            removeConnection(con);
        }
    }

    public void returnConnection(DBConnection con) {
        con.stopLease();
    }
}
