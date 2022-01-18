/*
 * Jason Arnold
 * CIST 2373 JAVA 3
 * Semester Project Appointment Class
 */
package DentistWebApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Appointment {
    
    final String DBPATH = "jdbc:ucanaccess://C://DB//DentistOfficeMDB.mdb";         //Database path
    String dateTime;                                                                //attribute for appointment date and time
    String patient;                                                                 //attribute for patient ID
    String dentist;                                                                 //attribute for dentist ID  
    String procedure;                                                               //attribute for procedure code
    
    //no args constructor
    public Appointment()
    {
        dateTime = "";
        patient = "";
        dentist = "";
        procedure = "";
    }
    
    //args constructor
    public Appointment(String date, String pat, String dent, String proc)
    {
        dateTime = date;
        patient = pat;
        dentist = dent;
        procedure = proc;
    }
    // set and get methods
    public void setDateTime(String date)
    {
        dateTime = date;
    }
    
    public String getDateTime()
    {
        return dateTime;
    }
    
    public void setPatient(String pat)
    {
        patient = pat;
    }
    
    public String getPatient()
    {
        return patient;
    }
    
    public void setDentist(String dent)
    {
        dentist = dent;
    }
    
    public String getDentist()
    {
        return dentist;
    }
    
    public void setProcedure(String proc)
    {
        procedure = proc;
    }
    
    public String getProcedure()
    {
        return procedure;
    }//end set and get methods
    
    //display method
    public void display()
    {
        System.out.println("Appointment scheduled for " + getPatient() + " at " + getDateTime() + " with " + getDentist() + " for procedure " + getProcedure());
    }
    
    /*************************************
    * selectDB() gets appointment values from database
    **************************************/
    public void selectDB(String patId)
    {
        patient = patId;
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);
            Statement stmt = con.createStatement();
            String sql = "Select * from Appointments where patid ='" + getPatient() + "'";
            System.out.println(sql);
            ResultSet result = stmt.executeQuery(sql);
            result.next();
            setDateTime(result.getString("apptDateTime"));
            setDentist(result.getString("dentId"));
            setProcedure(result.getString("procCode"));
            con.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//end selectDB
    /*****************************************
    * get appointment values from database
    * ****************************************/
    public void selectDBDent(String dentId)
    {
        dentist = dentId;
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);
            Statement stmt = con.createStatement();
            String sql = "Select * from Appointments where dentid ='" + getDentist() + "'";
            System.out.println(sql);
            ResultSet result = stmt.executeQuery(sql);
            result.next();
            setDateTime(result.getString("apptDateTime"));
            setPatient(result.getString("patId"));
            setProcedure(result.getString("procCode"));
            con.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//end selectDB
        
    //insert new appointment into database
    public void insertDB(String date, String pat, String dent, String proc)
    {
        setDateTime(date);
        setPatient(pat);
        setDentist(dent);
        setProcedure(proc);
        
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);
            Statement stmt = con.createStatement();
            String sql = "Insert into Appointments values ('" + getDateTime() + "', " +
                                                          "'" + getProcedure() + "', " +
                                                          "'" + getPatient() + "', " +
                                                          "'" + getDentist() + "')";
            System.out.println(sql);
            int n1 = stmt.executeUpdate(sql);
            if (n1==1)
                System.out.println("Record inserted Successfully!");
            else
                System.out.println("!!!!!!!!!!!INSERT FAILED!!!!!!!!!!!!!");
            con.close();
        }
        catch(Exception e1)
        {
            System.out.println(e1);
        }
    }//end insertDB
    
    //update database
    public void updateDB(String date, String pat, String dent, String proc)
    {
        setDateTime(date);
        setPatient(pat);
        setDentist(dent);
        setProcedure(proc);
        
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);
            Statement stmt = con.createStatement();
            String sql = "Update Appointments set apptDateTime = '" + getDateTime() + "', " +
                                                          "dentid = '" + getDentist() + "', " +
                                                          "procCode = '" + getProcedure() + "' " +
                                                           "where patId = '" + getPatient() + "'";
            System.out.println(sql);
            int n1 = stmt.executeUpdate(sql);
            if (n1==1)
                System.out.println("Record inserted Successfully!");
            else
                System.out.println("!!!!!!!!!!!UPDATE FAILED!!!!!!!!!!!!!");
            con.close();
        }
        catch(Exception e1)
        {
            System.out.println(e1);
        }
    }//end updateDB
    
    //delete appointment from database
    public void deleteDB()
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);
            Statement stmt = con.createStatement();
            String sql = "Delete from Appointments where patid = '" + getPatient() + "'";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n==1)
                System.out.println("Record Deleted.");
            else
                System.out.println("!!!!!!!!!!DELETE FAILED!!!!!!!!!!");
            con.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//end deleteDB
    
    public static void main(String[] args)
    {
        Appointment a1 = new Appointment();
        a1.selectDB("A911");
        a1.display();
    }
}
