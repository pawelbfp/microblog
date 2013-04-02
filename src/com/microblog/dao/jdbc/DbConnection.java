package com.microblog.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.runner.Version;

public class DbConnection {

	public static void main(String[] args) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

    
        String url = "jdbc:postgresql://localhost:5432/Microblog";
        String user = "postgres";
        String password = "test";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
            
//            rs = st.executeQuery("SELECT * from contact");
//            if (rs.next()) {
//                System.out.println(rs.getString(1));
//            }
            
            rs = st.executeQuery("SELECT Username from Users");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }            
            

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Version.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
	}
	
	public static Connection getConnection() {
		Connection con = null;

		String url = "jdbc:postgresql://localhost:5432/Microblog";
		String user = "postgres";
		String password = "test";

		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Version.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		}
		return con;
	}
	
	

}
