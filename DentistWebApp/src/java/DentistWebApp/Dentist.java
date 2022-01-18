/*
 * Jason Arnold
 * CIST 2373 JAVA 3
 * Semester Project Dentist Class
 */
package DentistWebApp;

import java.sql.*;


public class Dentist 
{
    final String DBPATH = "jdbc:ucanaccess://C://DB//DentistOfficeMDB.mdb";         //Database path
    String dentId;                                                                  //attribute for dentist ID
    String password;                                                                //attribute for dentist password
    String fName;                                                                   //attribute for dentist first name
    String lName;                                                                   //attribute for dentist last name
    String email;                                                                   //attribute for dentist email
    String office;                                                                  //attribute for dentist office number
    ApptList appts;                                                                 //attribute for dentist appointment list
    
    //no arg constructor
    public Dentist()
    {
        dentId = "";
        password = "";
        fName = "";
        lName = "";
        email = "";
        office = "";
        appts = new ApptList();
    }
    
    //arg constructor
    public Dentist(String id, String pass, String fname, String lname, String em, String off, ApptList appt)
    {
        dentId = id;
        password = pass;
        fName = fname;
        lName = lname;
        email = em;
        office = off;
        appts = appt;
    }
    
    //set and get methods
    public void setDentId(String id)
    {
        dentId = id;
    }
    
    public String getDentId()
    {
        return dentId;
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
    
    public void setEmail(String em)
    {
        email = em;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setOffice(String off)
    {
        office = off;
    }
    
    public String getOffice()
    {
        return office;
    }
    
    public ApptList getApptList()
    {
        return appts;
    }//end set and get mathods
    
    //display method
    public void display()
    {
        System.out.println("Dentist ID                = " + getDentId());
        System.out.println("Dentist PW                = " + getPassword());
        System.out.println("Dentist First Name        = " + getFName());
        System.out.println("Dentist Last Name         = " + getLName());
        System.out.println("Dentist Email             = " + getEmail());
        System.out.println("Dentist Office            = " + getOffice());
        System.out.println("Dentist's appointments: ");
        appts.display();
    }//end display mathod
    
    //get dentist attributes from database
    public void selectDB(String id)
    {
        dentId = id;
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);
            Statement stmt = con.createStatement();
            String sql = "Select * from Dentists where id ='" + getDentId() + "'";
            System.out.println(sql);
            ResultSet result = stmt.executeQuery(sql);
            result.next();
            setPassword(result.getString("passwd"));
            setFName(result.getString("firstName"));
            setLName(result.getString("lastName"));;
            setEmail(result.getString("email")); 
            setOffice(result.getString("office"));
            con.close();
            getAppts();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    } //end selctDB()
    
    //insert new dentist into database
    public void insertDB(String id, String pass, String fname, String lname, String email, String off)
    {
        setDentId(id);
        setPassword(pass);
        setFName(fname);
        setLName(lname);
        setEmail(email);
        setOffice(off);
       
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);
            
            Statement stmt = con.createStatement();
            String sql = "Insert into Dentists values('" + getFName() + "', " +
                                                     "'" + getLName() + "', " +  
                                                     "'" + getEmail() + "', " + 
                                                     "'" + getOffice() + "', " + 
                                                     "'" + getDentId() + "', " +
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
    
    //update existing dentist databse info
    public void updateDB(String id, String pass, String fname, String lname, String email, String office)
    {
        setDentId(id);
        setPassword(pass);
        setFName(fname);
        setLName(lname);
        setEmail(email);
        setOffice(office);
       
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);            
            Statement stmt = con.createStatement();
            String sql = "Update Dentists set passwd = '" + getPassword() +
                         "', firstName = " + "'" + getFName() +
                         "', lastName = " + "'" + getLName() +
                         "', email = " + "'" + getEmail() +
                         "', office = " + "'" + getOffice() +
                         "' where Id = '" + getDentId() + "'"; 
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
    
    //remove dentist record from database
    public void deleteDB()
    {
         
       
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);            
            Statement stmt = con.createStatement();
            String sql = "Delete from Dentists where id = '" + getDentId() + "'";
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
   
    //retrieve dentist's appointment list
    public void getAppts()
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(DBPATH);
            Statement stmt = con.createStatement();
            String sql = "Select * from Appointments where dentId = '" + getDentId() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            Appointment a1;
            while (rs.next())
                    {
                        a1 = new Appointment();
                        a1.setPatient(rs.getString(3));
                        a1.setDateTime(rs.getString(1));
                        a1.setDentist(rs.getString(4));
                        a1.setProcedure(rs.getString(2));
                        appts.addAccount(a1);
                    }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//end getAppts()
    
    
    public static void main(String args[])
    {
        Dentist d1 = new Dentist();
        d1.selectDB("D201");
        d1.display();
    }
    
}
