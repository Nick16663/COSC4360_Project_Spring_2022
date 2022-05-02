import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MailTrackingSystem{
	
	static int[] programStateRef = new int[1];
	
	public static boolean EmployeeMenu(Scanner kb, ArrayList<String> userList, ArrayList<String> passList) throws InterruptedException, FileNotFoundException {
		
		final int MAX_CHOICE_NUM = 4;
		
		boolean retryMenuInput = false;
		int choice;
		//boolean login = false;
		boolean runEmployee = true;
		
    	String userToken;
    	String passToken;
    	
    	
    	System.out.print("\nType \"BACK\" to quit login");
    	System.out.print("\nUsername: ");
    	userToken = kb.next();
    	kb.nextLine();
    		
    	if(userToken.equals("BACK")) {
    		programStateRef[0] = 0;
    		System.out.println("\n\n=== Logout Selected =============================================================================================================="); 
    		return true;
    		
    	}else {
    		
    		System.out.print("\nPassword: ");
	    	passToken = kb.next();
	    	kb.nextLine();
	    		
	    	if(passToken.equals("BACK")) {
	    		programStateRef[0] = 0;
	    		System.out.println("\n\n=== Logout Selected =============================================================================================================="); 
	    		return true;
	    	}else {
	    		if(userList.contains(userToken) && passList.get(userList.indexOf(userToken)).equals(passToken)) {
		    		System.out.println("\n\n"); 
		    		while(runEmployee) {
		    			do {
		    				retryMenuInput = false;
		    				System.out.println("====== Employee Menu ==============================================================================================");
		    				System.out.println("1) Track Package");
		    				System.out.println("2) Update Package Location");
		    				System.out.println("3) COntact Postmaster");
							System.out.println("4) Logout");
						    	
							System.out.print("\nPlease select an option (1 - "+MAX_CHOICE_NUM+"): ");
						    		
							choice = kb.nextInt();
						    		
							if(choice <= 0 || choice > MAX_CHOICE_NUM) {
								System.out.println("\nInvalid Choice");
								retryMenuInput = true;
							    Thread.sleep(3000);
							}
					    		
		    			}while(retryMenuInput);
					    		
		    			
					    	switch(choice) {
					    	case 1: System.out.println("\n\nTrack Package"); break;
					    	case 2: System.out.println("\n\nUpdate Package"); break;
					    	case 3: System.out.println("\n\nCOntact Postmaster"); break;
					    	case 4: System.out.println("\n\n=== Logout Selected =============================================================================================================="); 
					    			programStateRef[0] = 0; 
					    			runEmployee = false;
					    			break;
					    	}
				    		
			    	}
	    		
	    		}else {
	    			System.out.println("\n\n\n=== Invalid Username or Password! ===\n");
	    		}
	    		
	    	}
    	}
    	return true;
    }// End of showMenu =======================================================================

	public static boolean displayMenu(Scanner kb) throws InterruptedException {
	    	
		final int MAX_CHOICE_NUM = 4;
		boolean retry = false;
		int choice;
		do {
			retry = false;
			System.out.println("====== Menu ==============================================================================================");
			System.out.println("1) Employee Login");
			System.out.println("2) Send a Package");
			System.out.println("3) Track a Package");
			System.out.println("4) Exit Program");
			System.out.print("\nPlease select an option (1 - "+MAX_CHOICE_NUM+"): ");
				    		
			choice = kb.nextInt();
				    		
			if(choice <= 0 || choice > MAX_CHOICE_NUM) {
				System.out.println("\nInvalid Choice");
				retry = true;
				Thread.sleep(3000);

			}
			    		
		}while(retry);
		    		
		switch(choice) {
		case 1: System.out.println("\n\n\n\n====== Employee Login =============================================================================================================="); 
				programStateRef[0] = 1;
		    	break;
		case 2: System.out.println("\n\nSend a Package"); break;
		case 3: System.out.println("\n\nTrack a Package"); break;
		case 4: System.out.println("\n\n=== Exit Program Selected =============================================================================================================="); 
				return false;
		}
		    		
	    	
	    	
	    	return true;
	}// End of showMenu =======================================================================
	 
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException, FileNotFoundException{
    	
    	final String EMPLOYEE_PATH = "src\\employeeDatabase.txt";
		
		Scanner kb = new Scanner(System.in);
		Scanner employeeDatabase = new Scanner(new File(EMPLOYEE_PATH));
		
		ArrayList<String> userNames = new ArrayList<String>();
		
		ArrayList<String> passWords = new ArrayList<String>();
		
		
		while(employeeDatabase.hasNextLine()) {
			
			userNames.add(employeeDatabase.next());
			passWords.add(employeeDatabase.next());
			
		}
		
		
    	boolean runProgram = true;
    	final double programVer = 0.01;
    	
    	int programState = 0;
    	
    	programStateRef[0] = programState;
    	
    	//Map testDriver = Map.getInstance();
    	
    	//ArrayList<Package> packageList = new ArrayList<Package>();
    	
    	System.out.println("=== Mail Tracking System ver "+programVer+" ==========================================================================================================");
 		Thread.sleep(2000);
    	
    	do {
    		
    		if(programStateRef[0] == 0)
    			runProgram = displayMenu(kb);
    		
    		if(programStateRef[0] == 1)
    			runProgram = EmployeeMenu(kb, userNames, passWords);
    		
    		if(programStateRef[0] == 2) {} //todo: other program states
    			
    		
    		if(programStateRef[0] == 3) {} //todo: other program states
    			
    	
    	}while(runProgram);
    	
    	System.out.println("\n\n=== Program Terminated, Have a Good Day! ===============================================================================================");
    	
    	
    	
    	/*
    	
    	String testRecName = "Jack Nicholson";
    	String testRecAddr = "123 Maple Street";
    	String testRecCity = "Honolulu";
    	String testRecState = "Hawaii";
    	int testRecZip = 77482;
    	String testCurCity = "Los_Angeles";
    	Map.PostOffice testCurrLoc = testDriver.getPostOffice(testCurCity);
    	
    	Package pack = new Package(testRecName, testRecAddr, testRecCity, testRecState, testRecZip, testCurrLoc, testCurCity);
    	
    	System.out.println(pack);
    	
    	pack.goTo(testDriver.getStatePostOffice("HI"));
    	
    	System.out.println(pack);
    	
    	for(int i = 0; i < testDriver.officeList.size(); i++) {
    		System.out.println(testDriver.officeList.get(i));
    	}
    	
    	System.out.println(testDriver.officeList.get(0).getNeighborList());
    	
    	System.out.println(testDriver.roadList.get(0).getEdgeTime()+" hour drive");
    	*/
    	
    	
    	
    }
    
    
}