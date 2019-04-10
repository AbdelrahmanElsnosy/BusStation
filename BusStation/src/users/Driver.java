package users;

import java.util.ArrayList;

public class Driver extends Employee implements ValidateUserOnSystem{
/**
 * 
 * @param fileName
 * @param userName
 * @param password
 * @return
 */
	@Override
	public boolean validateUserData(String fileName,String userName,String password) {
		return (file.validateUser("Drivers", userName, password));
	}
	/**
	 *
	 *@param fileName
	 *@return
	 */
	@Override
	public ArrayList<String> displayData(String fileName) {
		ArrayList<String> list=new ArrayList<String>();
		list=file.convertAllFileDataToArray(fileName);
		return list;
	}
}
