/*
 * Jason Arnold
 * CIST 2373 JAVA 3
 * Semester Project ApptList Class
 */
package DentistWebApp;

public class ApptList {
    public int count = 0;                                    //initialize count of appointments in list at 0
    public Appointment apptArray[] = new Appointment[10];    //instantiate new array for adding Appointments to
    
    public void addAccount(Appointment a1)
    {
        apptArray[count] = a1;                               //add current appointment into the vector corresponsing to count
        count++;                                             //increment count
    }
    
    public void display()
    {
        for (int i=0; i<count; i++)                         //for loop to display each appointment in array
        {
            apptArray[i].display();
        }
    }
    
    //get appointment from specific index
    public Appointment getAppointment(int index)
    {
        Appointment a1 = apptArray[index];
        return a1;
    }
    
    public static void main(String[] args)
    {
        
        ApptList alist = new ApptList();

	Appointment a = new Appointment("may 1, 2021, 10 am", "A912", "D201", "625");
	Appointment b = new Appointment("April 23, 2021, 11am", "A903", "D201", "125");

	alist.addAccount(a);
	alist.addAccount(b);

	alist.display();
        
    }
    
}
