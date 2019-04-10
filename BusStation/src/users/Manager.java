package users;

import java.util.ArrayList;

public class Manager extends Employee implements ManagaerSpecials,ValidateUserOnSystem,CancelTrip {



	@Override
	public void addNewFile(String fileName) {
		file.createFile(fileName);
	}

	@Override
	public void addNewAdmin(String fileName,String userName,String password ,String gendar ,String age ,String balance) {
		file.AddAdminDriver(fileName, userName, password, gendar, age, balance);
	}

	@Override
	public boolean validateUserData(String fileName,String userName,String password) {
		return file.validateUser("Managers", userName, password);
	}

	@Override
	public ArrayList<String> displayData(String fileName) {
		ArrayList<String> list=new ArrayList<String>();
		list=file.convertAllFileDataToArray(fileName);
		return list;
	}

	@Override
	public void CancelTrip(String tripName) {
		file.deleteRecords("Trips", tripName);
		file.deleteFile(tripName);
	}

}
