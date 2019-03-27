import java.util.*;

public class  AddRecords{
	private Formatter fileHolder; 
	public int createFile(String fileName) {
		try {
			fileHolder=new Formatter(fileName+".txt");
			return 1;
		}
		catch(Exception e) {
			return 0;
		}
	}
	public void writeToFileCustomers(String customerName , String password) {
		fileHolder.format("%s %s %s \n" ,"Customer" ,customerName , password );
	}
	public void writeToFileDrivers(String driverName,String password ) {
		fileHolder.format("%s %s /n", "Driver" ,driverName , password);
	}
	public void WriteTripsName(String trip) {
		fileHolder.format("%s /n", trip );
	}
	public void closeFile() {
		fileHolder.close();
	}

}
