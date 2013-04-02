package com.microblog.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.postgresql.util.PGobject;

import junit.runner.Version;

import com.microblog.core.User;
import com.microblog.interfaces.IUsers;

class MyUserdata extends PGobject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer i;
	String s;
	};

public class UsersPostrgeJdbc implements IUsers {

	public UsersPostrgeJdbc()
	{}
	
	@Override
	public List<User> getNotSubscribedUsers(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> getSubscribedUsers(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public  List<User> getUsers() {
		// TODO Auto-generated method stub
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;
		CallableStatement cs = null;
		
		List<User> userList = null ; 
		
		try {
            con = DbConnection.getConnection();
            
            cs = con.prepareCall("{ call getusers2()}");
            //st = con.createStatement();
            //rs = st.executeQuery("select getusers()");
//            cs.registerOutParameter(1, Types.OTHER);
//            cs.registerOutParameter(2, Types.INTEGER);
//            cs.registerOutParameter(3, Types.VARCHAR);
            cs.execute();
            rs = cs.getResultSet();
        
            userList = new ArrayList<User>();

            
            while (rs.next())
            {
          	
            	Integer id  = rs.getInt(1);
            	String name = rs.getString(2);
            	String pwd = rs.getString(3);

            	//MyUserdata obj = (MyUserdata)rs.getObject(1);
            	
            	
            	userList.add(new User(id,name,pwd));

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
		
		return userList;
	}

	@Override
	public void subscribeUser(User user, User userSubscribed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribeAllUsers(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unsubscribeUser(User user, User userUnSubscribed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unsubscribeAllUsers(User user) {
		// TODO Auto-generated method stub
		
	}
	

}
