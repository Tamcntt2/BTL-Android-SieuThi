package com.example.sieuthi.database;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    Connection conn = null;
    String uname, pass, ip, port, db, url;
    String c = "net.sourceforge.jtds.jdbc.Driver";
    public Connection connectionclass(){
        ip = "127.0.0.1";
        db = "QLCuaHangThucPham";
        uname = "NgoVanKhai";
        pass = "123";
        port = "1433";
        url = "jdbc:jtds:sqlserver://" + ip +":"+port+";DatabaseName=" + db;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName(c);
            conn = DriverManager.getConnection(url, uname, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
