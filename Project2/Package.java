public class Package {
	
	final int DayCreated = TimeKeeper.getCurrentDay(); //without testing, I'm assuming this works on initialization
	private int DaysInTransit = (TimeKeeper.getCurrentDay() - DayCreated);
	
	//Receiver Information
	private String RcvrName;
	private String RcvrAddress;
	private String RcvrCity;
	private String RcvrState;
	private int RcvrZip;
	
	//Sender Information
	private String SndrName;
	private String SndrAddress;
	private String SndrCity;
	private String SndrState;
	private int SndrZip;
	
	//Map Information
	private Map.PostOffice currentLocOffice;
	private String currentLocName;
	
	//Has package arrived?
	private boolean hasArrived;
	
	//private boolean isTracking = true;
	
	Package(){ //Default constructor, probably don't use in actual execution
		
		this.RcvrName = "DEFAULT_NAME";
		this.RcvrAddress = "DEFAULT_ADDR";
		this.RcvrCity = "DEFAULT_CITY";
		this.RcvrState = "DEFAULT_STATE";
		this.RcvrZip = 99999;
		
		this.SndrName = "DEFAULT_NAME";
		this.SndrAddress = "DEFAULT_ADDR";
		this.SndrCity = "DEFAULT_CITY";
		this.SndrState = "DEFAULT_STATE";
		this.SndrZip = 00000;
		
		currentLocOffice = new Map.PostOffice();
		currentLocName = "DEFAULT_CITY";
		
		hasArrived = false;
		
	}
	
	Package(String Rname, String Raddress, String Rcity, String Rstate, int Rzip, Map.PostOffice currLoc, String currLocName){ //Receiver constructor
		
		this.RcvrName = Rname;
		this.RcvrAddress = Raddress;
		this.RcvrCity = Rcity;
		this.RcvrState = Rstate;
		this.RcvrZip = Rzip;
		
		this.SndrName = "DEFAULT_NAME";
		this.SndrAddress = "DEFAULT_ADDR";
		this.SndrCity = "DEFAULT_CITY";
		this.SndrState = "DEFAULT_STATE";
		this.SndrZip = 00000;
		
		currentLocOffice = currLoc;
		currentLocName = currLocName;
		
		hasArrived = false;
		
		Map.computePaths(currLoc);
		
		path = Map.getShortestPathTo(Map.getInstance().getStatePostOffice(Rstate));
		
		System.out.println("Path: " + path);
    	
		//Debug Print
    		for(int i = 0; i < path.size(); i++) {
    			System.out.println(path.get(i).getCityName());
    		}
		
	}
	
	Package(String Rname, String Raddress, String Rcity, String Rstate, int Rzip, String Sname, String Saddress, String Scity, String Sstate, int Szip, Map.PostOffice currLoc, String currLocName){ //Full constructor
		
		this.RcvrName = Rname;
		this.RcvrAddress = Raddress;
		this.RcvrCity = Rcity;
		this.RcvrState = Rstate;
		this.RcvrZip = Rzip;
		
		this.SndrName = Sname;
		this.SndrAddress = Saddress;
		this.SndrCity = Scity;
		this.SndrState = Sstate;
		this.SndrZip = Szip;
		
		currentLocOffice = currLoc;
		currentLocName = currLocName;
		
		hasArrived = false;
		
		Map.computePaths(currLoc);
		
		path = Map.getShortestPathTo(Map.getInstance().getStatePostOffice(Rstate));
		
		System.out.println("Path: " + path);
    	
			
		//Debug Print
    		for(int i = 0; i < path.size(); i++) {
    			System.out.println(path.get(i).getCityName());
    		}
		
	}
	
	public void getRcvrInfo() {
		
		System.out.println(RcvrName + "\n" + RcvrAddress + "\n " + RcvrCity + ", " + RcvrState +
				" " + RcvrZip);
	}
	
	public String getRcvrName() {
		
		return RcvrName;
	}
	
	public void setRcvrName(String name) {
		
		this.RcvrName = name;
	}
	
	public String getRcvrAddress() {
		
		return RcvrAddress;
	}
	
	public void setRcvrAddress(String address) {
		
		this.RcvrAddress = address;
	}
	
	public String getRcvrCity() {
		
		return RcvrCity;
	}
	
	public void setRcvrCity(String city) {
		
		this.RcvrCity = city;
	}
	
	public String getRcvrState() {
		
		return RcvrState;
	}
	
	public void setRcvrState(String state) {
		
		this.RcvrState = state;
	}
	
	public int getRcvrZip() {
		
		return RcvrZip;
	}
	
	public void setRcvrZip(int zip) {
		
		this.RcvrZip = zip;
	}
	
	public String getSndrName() {
		
		return SndrName;
	}
	
	public void setSndrName(String name) {
		
		this.SndrName = name;
	}
	
	public String getSndrAddress() {
		
		return SndrAddress;
	}
	
	public void setSndrAddress(String address) {
		
		this.SndrAddress = address;
	}
	
	public String getSndrCity() {
		
		return SndrCity;
	}
	
	public void setSndrCity(String city) {
		
		this.SndrCity = city;
	}
	
	public String getSndrState() {
		
		return SndrState;
	}
	
	public void setSndrState(String state) {
		
		this.SndrState = state;
	}
	
	public int getSndrZip() {
		
		return SndrZip;
	}
	
	public void setSndrZip(int zip) {
		
		this.SndrZip = zip;
	}
	
	public Map.PostOffice getCurrLocOffice(){
		return currentLocOffice;
	}
	
	public void setCurrLocOffice(Map.PostOffice x){
		currentLocOffice = x;
	}
	
	public String getCurrentLocName() {
		return currentLocName;
	}
	
	public void setCurrentLocName(String name) {
		currentLocName = name;
	}
	
	public boolean hasArrived() {
		return hasArrived;
	}
	
	public void setArrival(boolean bool) {
		hasArrived = bool;
	}
	
	public void addDaysInTransit() {
		DaysInTransit += 1;
	}
	
	public int getDaysInTransit() {
		return DaysInTransit;
	}
	
	public void setDaysInTransit(int transitDays) {
		DaysInTransit = transitDays;
	}
	
	
	public boolean goTo(Map.PostOffice x) throws InterruptedException {
		if(this.currentLocOffice.getObjNeighbors().contains(x)) {
			
			
			
			
			
			this.currentLocOffice.addPackagesSent(1);
			
			System.out.println("Travelling from "+currentLocOffice.getCityName()+" to "+x.getCityName());
			
			
			
			this.setCurrentLocName("In Transit");
			setDaysInTransit(0);
		
			do {
			
				this.addDaysInTransit();
				
				System.out.println("Package currently in Transit...");
				
				Thread.sleep(2000);
				
				//System.out.println("Day "+this.getDaysInTransit()+" of transit: \n Press any key to continue...");
					
				
									
			}while(this.getDaysInTransit()*24 < currentLocOffice.getEdgeTo(x).getEdgeTime() + (int)(Math.random()*25)*Math.random()*6+1);
			
			
			
			System.out.println("Travel time took "+this.getDaysInTransit()+" day(s)\n");
			
			System.out.println("Package arrived in "+x.getCityName());
			
			
			this.setCurrLocOffice(x);
			this.setCurrentLocName(x.getCityName());
			x.addPackagesReceived(1);
			return true;
			
			
			
		}else {
			
			System.out.println("ERR: "+x.getCityName()+" is not a neighbor of "+currentLocOffice.getCityName());
			
			return false;
		}
	}
	
	public String toString() {
		if(currentLocName.equals("In Transit"))
			return  "Package to "+RcvrName+" in "+RcvrCity+", "+RcvrState+" last seen at "+currentLocOffice.getCityName();
		else
			return "Package to "+RcvrName+" in "+RcvrCity+", "+RcvrState+" currently at "+currentLocOffice.getCityName();
	}
	
	
// Commented out for testing purposes ==========================================================
	
	/*
	private PackageState currentState = new SortingState();


	public void setState(PackageState state) {

	this.currentState = state;

	}

	public void getState() {

	this.currentState.showState();

	}


	public interface PackageState {

	  void send(Package pkg);

	  void receive(Package pkg);

	  void showState();

	}

/*

	public class SortingState implements PackageState {

	  @Override

	  public void send(Package pkg) {

	    pkg.setState(new TransitState());

	  }



	  @Override

	  public void receive(Package pkg) {

	    System.out.println("The package has yet to be sent. It's at the initial post office.");

	  }



	  @Override

	  public void showState() {

	    System.out.println("This package is at the initial post office it was mailed from.");

	  }

	}

	
	public class TransitState implements PackageState {

	  @Override

	  public void send(Package pkg) {

	    System.out.println("The package has already been set.");

	  }

	  @Override

	  public void receive(Package pkg) {

	    pkg.setState(new ReceivedState());

	  }


	  @Override

	  public void showState() {

	    System.out.println("This package is in transit.");

	  }

	}


	public class ReceivedState implements PackageState {

	  @Override

	  public void send(Package pkg) {

		  pkg.setState(new TransitState());
		  
	  }


	  @Override

	  public void receive(Package pkg) {

		  System.out.println("The package is already received at " + PostOffice());

	  }



	  @Override

	  public void showState() {

	    System.out.println("The package is currently received at " + PostOffice());

	  }

	}
*/
}


