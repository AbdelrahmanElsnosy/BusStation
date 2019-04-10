package users;
import java.util.ArrayList;

import files.File;
public class Costumer implements ValidateUserOnSystem,CancelTrip{
	File file=new File();
	String userName;
	/**
	 * 
	 * @param
	 * @param
	 * @param
	 * @return
	 */
	public boolean validateUserData(String fileName,String userName,String password) {
		this.userName=userName;
		return file.validateUser("Customers", userName, password);
	}
	public void addNewUser(String userName,String password,String gendar,String age,String balance) {
		file.addnewUser (userName, password, gendar, age, balance);
	}
	/**
	 * 
	 *@param
	 */
	@Override
	public void CancelTrip(String tripName) {
		file.editeRecord(userName, tripName, "null");
		if(file.searchForRecord(tripName, userName)) {
			file.deleteRecords(tripName, userName);
			file.incrementDecrementNumberOfSeats("CancelTrip", tripName);
			} 
	}
	/**
	 * 
	 * @param tripName
	 */
	public boolean bookTrip(String tripName ,int numberOfSeats) {
		boolean booked=false;
		int tempNumberOfSeats=file.getNumberOfSeats(tripName);
		float userBalance=file.checkCustomerBalance(userName);
		float tripPrice=file.getTripPrice(tripName);
		float totalPrice=numberOfSeats*tripPrice;
		if(userBalance >= totalPrice && tempNumberOfSeats>=numberOfSeats) {
			booked=true;
			file.setUserBalance(userName, userBalance-totalPrice);
			file.addToTrip(userName, tripName, Float.toString(numberOfSeats));
			file.incrementDecrementNumberOfSeats("bookTrip", tripName);
		}
		return booked;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> displayAllTrips(){
		ArrayList<String> list=new ArrayList<String>();
		list=file.convertAllFileDataToArray("Trips");
		return list;
	}
	public String[] getUserData(){
		String [] userData=new String[3];
		file.getUserData(this.userName);
		return userData;
	}

}
