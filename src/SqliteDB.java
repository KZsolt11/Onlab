import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SqliteDB {
	
public ArrayList<String> getDeviceTNames(){
	ArrayList<String> res=new ArrayList<String>();
	String url = "jdbc:sqlite:C:/Users/Zsolt/Documents/egyetemi/Önlab/CompanyDevices.db";
	 Connection c = null;
	 Statement stmt = null;
	    try {
	   
	      c = DriverManager.getConnection(url);
	     
	      
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT TName FROM DEVICETYPE;" );
	      while ( rs.next() ) {     
	         String  name = rs.getString("TName");
	         res.add(name);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	  return res;
}
public ArrayList<String> getRoomNumbers(){
	ArrayList<String> res=new ArrayList<String>();
	String url = "jdbc:sqlite:C:/Users/Zsolt/Documents/egyetemi/Önlab/CompanyDevices.db";
	 Connection c = null;
	 Statement stmt = null;
	    try {
	   
	      c = DriverManager.getConnection(url);
	     
	      
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT TNumber FROM Room;" );
	      while ( rs.next() ) {     
	         String num = rs.getString("TNumber");
	         res.add(num);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	  return res;
}
public ArrayList<String> getRoomNevelo(){
	ArrayList<String> res=new ArrayList<String>();
	String url = "jdbc:sqlite:C:/Users/Zsolt/Documents/egyetemi/Önlab/CompanyDevices.db";
	 Connection c = null;
	 Statement stmt = null;
	    try {
	   
	      c = DriverManager.getConnection(url);
	     
	      
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT Nevelo FROM Room;" );
	      while ( rs.next() ) {     
	         String num = rs.getString("Nevelo");
	         res.add(num);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	  return res;
}
public ArrayList<String> getDeviceNevelo(){
	ArrayList<String> res=new ArrayList<String>();
	String url = "jdbc:sqlite:C:/Users/Zsolt/Documents/egyetemi/Önlab/CompanyDevices.db";
	 Connection c = null;
	 Statement stmt = null;
	    try {
	   
	      c = DriverManager.getConnection(url);
	     
	      
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT Nevelo FROM DEVICETYPE;" );
	      while ( rs.next() ) {     
	         String num = rs.getString("Nevelo");
	         res.add(num);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	  return res;
}
public ArrayList<String> getDeviceNames(){
	ArrayList<String> res=new ArrayList<String>();
	String url = "jdbc:sqlite:C:/Users/Zsolt/Documents/egyetemi/Önlab/CompanyDevices.db";
	 Connection c = null;
	 Statement stmt = null;
	    try {
	   
	      c = DriverManager.getConnection(url);
	     
	      
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT Name FROM DEVICETYPE;" );
	      while ( rs.next() ) {     
	         String num = rs.getString("Name");
	         res.add(num);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	  return res;
}
}
