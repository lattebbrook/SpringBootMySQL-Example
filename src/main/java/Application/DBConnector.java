package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBConnector {

    private static String stringConnection = "jdbc:mysql://localhost/company?user=elon&password=musk";
    
    //check connection
    public boolean isConnect(){
        try {
            Connection cn = DriverManager.getConnection(stringConnection);
            if(cn == null) return false;
            cn.close();
            return true;
        } catch(SQLException se) { 
                return false;
        } 
    }
    
    //create 
    public boolean insertStaff(String first_name, String last_name, String branch, String employment_status) {
        try {
            String query = "insert into staff(first_name, last_name, branch, employment_status) values(?, ?, ?, ?);";
            Connection cn = DriverManager.getConnection(stringConnection);
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, branch);
            ps.setString(4, employment_status);
            ps.execute();
            ps.close();
            cn.close();
            return true;
        } catch(SQLException se) { }
            System.err.println("ERRORs: Cannot write into the database");
            return false;
    }

    //read
    public ArrayList listAllStaff() {
        ArrayList result = new ArrayList();
        try {
            String query = "select * from staff";
            Connection cn = DriverManager.getConnection(stringConnection);
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String name = rs.getString("first_name");
                result.add( name);
            } 
            rs.close();
            ps.close();
            cn.close();
        } catch (Exception e) { }
        return result;
    }  
    
    //update
    public boolean updateStaff(String new_first_name, String new_last_name, String first_name, String last_name) {
        try {
            String query = "UPDATE staff SET first_name = ?, last_name = ? WHERE first_name = ? AND last_name = ?";
            Connection cn = DriverManager.getConnection(stringConnection);
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, new_first_name);
            ps.setString(2, new_last_name);
            ps.setString(3, first_name);
            ps.setString(4, last_name);
            ps.executeUpdate();
            cn.close();
            return true;
        } catch(SQLException se) {
            System.err.println("ERROR: Cannot write into the database");
            return false;
        }
    }
    //delete from first_name and last_name
    public boolean removeStaff(String first_name, String last_name) {
        try {
            String query = "delete from staff where first_name = ? and last_name = ?";
            Connection cn = DriverManager.getConnection(stringConnection);
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.execute();
            cn.close();
            return true;
        } catch(SQLException se) {
            System.err.println("ERRORs: Cannot write into the database");
            return false;
        }
    }
}

