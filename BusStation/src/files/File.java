package files;
import java.util.Formatter;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
public class File {
    private Formatter fileHolder;
    private Scanner fileScanner;
/**
   * This Function used to create New file or open Existing one
   * @param fileName type of String which is the file you want to open or create
   * @return type of integer which return 1  or 0 depending on errors if 1 no error 0 is an error
   */
    public int createFile(String fileName) {                        
    	try {
			fileHolder=new Formatter(fileName+".txt");
			return 1;
    	}
    	catch(Exception e) {
			return 0;
		}
    }
    /**
     * 
     */
    private int openFile(String fileName) {
    	try {
    		fileScanner=new Scanner(new java.io.File(fileName+".txt"));
    		return 1;
    	}
    	catch (Exception e) {
    		return 0;
    	}
    }
    /**
     * this Function add data to file 
     * @param userName type of String which is user name
     * @param password type of string of String which is password
     */
    public void addnewUser(String userName, String password ,String gendar ,String age ,String balance) {
    	createFile(userName);
    	fileHolder.format("%s,%s,%s,%s,%s,%s,%s \n", userName,gendar,age,balance,"null","null","null");
    	closeFile();
    	createFile("Customers");
    	fileHolder.format("%s,%s \n",userName,password);
    	closeFile();
    }
    public void AddAdminDriver(String fileName,String userName,String password,String gendar,String age,String balance) {
    	createFile(userName);
    	fileHolder.format("%s,%s,%s,%s \n", userName,gendar,age,balance);
    	closeFile();
    	createFile(fileName);
    	fileHolder.format("%s,%s \n",userName,password);
    	closeFile();
    }
 /**
  * 
  * @param fileName
  * @param userName
  * @param password
  * @return
  */
    public boolean validateUser(String fileName,String userName,String password) {
    	boolean found=false;
    	ArrayList<String> list=new ArrayList<String>();
    	list=convertAllFileDataToArray(fileName);
    	for(int i=0;i<list.size();i++) {
    		if(list.get(i).equals(userName+","+password)) {
    			found=true;
    		}
    	}
    	return found;
    }
    /**
     * this function will read all file data and put it in an array
     * @return type of ArrayList which is the file Data
     */
    public ArrayList<String> convertAllFileDataToArray(String fileName){
    	ArrayList<String> myList=new ArrayList<String>();
    	int errorChecked=openFile(fileName);
    	if(errorChecked==1) {
    		while(fileScanner.hasNext())
    			myList.add(fileScanner.nextLine());
    	}
    	else
    		myList=null;
    	return myList;
    }
    /**
     * 
     * @param fileName
     * @param list
     * @return
     */
    private int writeToFileUsingArray(String fileName,ArrayList<String> list) {
    	try {
    		BufferedWriter writer=new BufferedWriter(new FileWriter(fileName+".txt"));
    		for(String x:list) {
    			writer.write(x);
    			writer.newLine();
    		}
    		writer.close();
    		return 1;
    	}
    	catch(Exception e) {
    		return 0;
    	}
    }
    /**
     * 
     * @param fileName
     * @param list
     * @param deletedRecord
     * @return
     */
    public void deleteRecords(String fileName,String deletedRecord)  {
    	ArrayList<String> list=new ArrayList<String>();
    	list=convertAllFileDataToArray(fileName);
    		for(int i=0;i<list.size();i++) {
    			if(list.get(i).contains(deletedRecord) && deletedRecord.length()>3) {
    				list.remove(i);
    				writeToFileUsingArray(fileName, list);
    			}
        		
    		}
    	}
    /**
     * 
     * @param fileName
     * @param editedRecord
     * @param replacedRecord
     */
    public void editeRecord(String fileName,String editedRecord,String replacedRecord) {
    	ArrayList<String> list=new ArrayList<String>();
    	list=convertAllFileDataToArray(fileName);
    	String holdedRecord="";
		for(int i=0;i<list.size();i++) {
			if(list.get(i).contains(editedRecord)) {
				holdedRecord=list.get(i);
				holdedRecord=holdedRecord.replace(editedRecord, replacedRecord);
				list.remove(i);
				list.add(holdedRecord);
			writeToFileUsingArray(fileName, list);
			}
    }
   }
   /**
    * 
    * @param userName
    * @return
    */
    public float checkCustomerBalance(String userName) {
    	String balance="";
    	int errorChecked=openFile(userName);
    	if(errorChecked==1) {
    		fileScanner.useDelimiter("[,\n]");
    		for(int i=0;i<4;i++) {
        		balance=fileScanner.next();
    		}
    	}
    	return Float.parseFloat(balance);
    }
    /**
     * 
     * @param userName
     * @return
     */
    public String[]getUserData(String userName){
    	String[] userData=new String[3];
    	int errorChecked=openFile(userName);
    	if(errorChecked==1) {
    		fileScanner.useDelimiter("[,\n]");
    		for(int i=0;i<3;i++) {
    			userData[i]=fileScanner.next();
    		}
    	}
    	return userData;
    }
    public void addToTrip(String userName,String tripName,String numberOfSeats) {
    	createFile(tripName);
    	fileHolder.format("%s,%s \n", userName,numberOfSeats);
    	fileHolder.close();
    }
    /**
     * 
     * @param userInput
     * @param tripName
     */
    public void incrementDecrementNumberOfSeats(String userInput,String tripName) {
    	switch(userInput) {
       	case"CancelTrip":
    		String numberOfSeats="";
        	int errorChecked=openFile("Trips");
        	if(errorChecked==1) {
        		fileScanner.useDelimiter("[,\n]");
        		while(fileScanner.hasNext() &&!fileScanner.next().equals(tripName));
        		for(int i=0;i<1;i++) {
            		numberOfSeats=fileScanner.next();
        		}
        		int number=Integer.parseInt(numberOfSeats);
        		number++;
        		String newnumberOfSeats=Integer.toString(number);
        		editeRecord("Trips", numberOfSeats, newnumberOfSeats);
        	}
        	break;
    	case "bookTrip":
    		 numberOfSeats="";
        	errorChecked=openFile("Trips");
        	if(errorChecked==1) {
        		fileScanner.useDelimiter("[,\n]");
        		while(fileScanner.hasNext() &&!fileScanner.next().equals(tripName));
        		for(int i=0;i<1;i++) {
            		numberOfSeats=fileScanner.next();
        		}
        		int number=Integer.parseInt(numberOfSeats);
        		number--;
        		String newnumberOfSeats=Integer.toString(number);
        		editeRecord("Trips", numberOfSeats, newnumberOfSeats);
        	}
        	break;
    	}
    }
    /**
     * 
     * @param fileName
     */
    public void deleteFile(String fileName) {
    	java.io.File fileHold=new java.io.File(fileName+".txt");
    	fileHold.delete();
    }
    /**
     * 
     * @param tripName
     * @return
     */
    public int getNumberOfSeats(String tripName) {
		String numberOfSeats="";
		int numberOfSeatsInt=0;
    	int errorChecked=openFile("Trips");
    	if(errorChecked==1) {
    		fileScanner.useDelimiter("[,\n]");
    		while(fileScanner.hasNext() &&!fileScanner.next().equals(tripName));
    		for(int i=0;i<1;i++) {
        		numberOfSeats=fileScanner.next();
    		}
    		 numberOfSeatsInt=Integer.parseInt(numberOfSeats);
    	}
		return numberOfSeatsInt;

    }
    /**
     * 
     * @param tripName
     * @return
     */
    public String getTypeOfVehicle(String tripName) {
    	String vehicle="";
    	int errorChecked=openFile("Trips");
    	if(errorChecked == 1) {
    		fileScanner.useDelimiter("[,\n]");
    		while(fileScanner.hasNext() &&!fileScanner.next().equals(tripName));
    		for(int i=0;i<4;i++) {
    			vehicle=fileScanner.next();
    		}

    	}
    	return vehicle;
    }
    /**
     * 
     * @param tripName
     * @param totalCost
     */
    public void setTripCost(String tripName,float totalCost) {
		String totalCostString="";
    	int errorChecked=openFile("Trips");
    	if(errorChecked==1) {
    		fileScanner.useDelimiter("[,\n]");
    		while(fileScanner.hasNext() &&!fileScanner.next().equals(tripName));
    		for(int i=0;i<2;i++) {
    			totalCostString=fileScanner.next();
    		}
    		String newTotalCostString=Float.toString(totalCost);
    		editeRecord("Trips", totalCostString, newTotalCostString);

    	}
    }
    /**
     * 
     * @param userName
     * @param totalBalance
     */
    public void setUserBalance(String userName,float totalBalance) {
    	String totalBalanceString="";
    	int errorChecked=openFile(userName);
    	if(errorChecked==1) {
    		fileScanner.useDelimiter("[,\n]");
    		while(fileScanner.hasNext() && !fileScanner.next().equals(userName));
    		for(int i=0;i<3;i++) {
    			totalBalanceString=fileScanner.next();
    		}
    		String newTotalCostString=Float.toString(totalBalance);
    		editeRecord(userName, totalBalanceString, newTotalCostString);
    	}
    }
    /**
     * 
     * @param fileName
     * @param searchedRecord
     * @return
     */
    public boolean searchForRecord(String fileName,String searchedRecord) {
    	boolean found=false;
    	String holdedRecord="";
    	int errorChecked=openFile(fileName);
    	if(errorChecked==1) {
    		fileScanner.useDelimiter("[,\n]");
    		while(fileScanner.hasNext() && !found) {
    			holdedRecord=fileScanner.next();
    			if(holdedRecord.trim().toUpperCase().equals(searchedRecord.trim().toUpperCase())) {
    				found=true;
    			}
    		}
    	}
    	return found;
    }
    public float getTripPrice(String tripName) {
		float priceFloat=0;
		String priceString="";
    	int errorChecked=openFile("Trips");
    	if(errorChecked==1) {
    		fileScanner.useDelimiter("[,\n]");
    		while(fileScanner.hasNext() &&!fileScanner.next().equals(tripName));
    		for(int i=0;i<2;i++) {
    			priceString=fileScanner.next();
    		}
    		priceFloat=Float.parseFloat(priceString);
    	}
    	return priceFloat;
    }
 /**
  *This function used to Close Your File
  */
 public void closeFile()
 {
	fileHolder.close();
 }
}