/*
 * Jason Arnold
 * CIST 2373 JAVA 3
 * Semester Project Patient Class
 */
package DentistWebApp;

import java.sql.*;


public class Patient 
{
    final String DBPATH = "jdbc:ucanaccess://C://DB//DentistOfficeMDB.mdb";         //database path
    String patId;                                                                   //attribute for patient ID
    String password;                                                                //attribute for password
    String fName;                                                                   //attribute for patient first name
    String lName;                                                                   //attribute for patient last name
    String address;                                                                 //attribute for patient address
    String email;                                                                   //attribute for patient email
    String insurance;                                                               //attribute for patient insurance
    Appointment appt;                                                               //attribute for patient appointment
    
    //no arg constructor
    public Patient()
    {
        patId = "";
        password = "";
        fName = "";
        lName = "";
        address = "";
        email = "";
        insurance = "";
        appt = new Appointment();
    }
    
    //arg constructor
    public Patient(String id, String pass, String fname, String lname, String add, String em, String ins, Appointment app)
    {
        patId = id;
        password = pass;
        fName = fname;
        lName = lname;
        address = add;
        email = em;
        insurance = ins;
        appt = app;
    }
    
    //set and get methods
    public void setPatId(String id)
    {
        patId = id;
    }
    
    public String getPatId()
    {
        return patId;
    }
    
    public void setPassword(String pass)
    {
        password = pass;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setFName(String fname)
    {
        fName = fname;
    }
    
    public String getFName()
    {
        return fName;
    }
    
    public void setLName(String lname)
    {
        lName = lname;
    }
    
    public String getLName()
    {
        return lName;
    }
    
    public void setAddress(String add)
    {
        address = add;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setEmail(String em)
    {
        email = em;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setInsurance(String ins)
    {
        insurance = ins;
    }
    
    public String getInsurance()
    {
        return insurance;
    }
    
    public void setAppt(Appointment app)
    {
        appt = app;
    }
    
    public Appointment getAppt()
    {
        return appt;
    }//end set and get methods
    
    //display method
    public void display()
    {
        System.out.println("Patient ID                = " + getPatId());
        System.out.println("Patient PW                = " + getPassword());
        System.out.println("Patient First Name        = " + getFName());
        System.out.println("Patient Last Name         = " + getLName());
        System.out.println("Patient Address           = " + getAddress());
        System.out.println("Patient Email             = " + getEmail());
        System.out.println("Patient Insurance         = " + getInsurance());
        appt.display();
    }//end display method
    
    //fill attributes from database
    public void selectDB(String id)
    {
        patId = id;
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);
            Statement stmt = con.createStatement();
            String sql = "Select * from Patients where patID ='" + getPatId() + "'";
            System.out.println(sql);
            ResultSet result = stmt.executeQuery(sql);
            result.next();
            setPassword(result.getString("passwd"));
            setFName(result.getString("firstName"));
            setLName(result.getString("lastName"));
            setAddress(result.getString("addr"));
            setEmail(result.getString("email")); 
            setInsurance(result.getString("insco"));
            con.close();
            appt.selectDB(patId);
            display();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    } //end selctDB()
    
    //insert new patient into database
    public void insertDB(String id, String pass, String fname, String lname, String address, String email, String ins)
    {
        setPatId(id);
        setPassword(pass);
        setFName(fname);
        setLName(lname);
        setAddress(address);
        setEmail(email);
        setInsurance(ins);
       
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);            
            Statement stmt = con.createStatement();
            String sql = "Insert into Patients values('" + getFName() + "', " +
                                                     "'" + getLName() + "', " + 
                                                     "'" + getAddress() + "', " + 
                                                     "'" + getEmail() + "', " + 
                                                     "'" + getInsurance() + "', " + 
                                                     "'" + getPatId() + "', " +
                                                     "'" + getPassword() + "')"; 
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
      
    }//end insertDB()
    
    //update existing patient in database
    public void updateDB(String id, String pass, String fname, String lname, String address, String email, String ins)
    {
        setPatId(id);
        setPassword(pass);
        setFName(fname);
        setLName(lname);
        setAddress(address);
        setEmail(email);
        setInsurance(ins);
       
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);            
            Statement stmt = con.createStatement();
            String sql = "Update Patients set passwd = '" + getPassword() + 
                         "', firstName = " + "'" + getFName() +  
                         "', lastName = " + "'" + getLName() +  
                         "', addr = " + "'" + getAddress() +  
                         "', email = " + "'" + getEmail() +  
                         "', insCo = " + "'" + getInsurance() + 
                         "' where patId = '" + getPatId() + "'"; 
            System.out.println(sql);
            int n1 = stmt.executeUpdate(sql);
            if (n1==1)
                System.out.println("Record updated Successfully!");
            else
                System.out.println("!!!!!!!!!!!UPDATE FAILED!!!!!!!!!!!!!");
            con.close();
        }
        catch(Exception e1)
        {
            System.out.println(e1);
        }
      
    }//end updateDB()
    
    //remove patient from datanase
    public void deleteDB()
    {
         
       
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);            
            Statement stmt = con.createStatement();
            String sql = "Delete from Patients where patID = '" + getPatId() + "'";
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
      
    }//end deleteDB()
    
    
    public static void main(String args[])
    {
        Patient p1 = new Patient();
        p1.selectDB("A903");
        p1.display();
    }
    
}
