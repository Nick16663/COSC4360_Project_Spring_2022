
public class Package {
	
	final int DayCreated = TimeKeeper.getCurrentDay(); //without testing, I'm assuming this works on initialization
	private int DaysInTransit = (TimeKeeper.getCurrentDay() - DayCreated);
	private String RcvrName;
	private String RcvrAddress;
	private String RcvrCity;
	private String RcvrState;
	private int RcvrZip;
	private String SndrName;
	private String SndrAddress;
	private String SndrCity;
	private String SndrState;
	private int SndrZip;
	
	
	Package(String Rname, String Raddress, String Rcity, String Rstate, int Rzip){
		
		this.RcvrName = Rname;
		this.RcvrAddress = Raddress;
		this.RcvrCity = Rcity;
		this.RcvrState = Rstate;
		this.RcvrZip = Rzip;
		
		
	}
	
	Package(String Rname, String Raddress, String Rcity, String Rstate, int Rzip,
			String Sname, String Saddress, String Scity, String Sstate, int Szip){
		
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
	
	private PackageState currentState = new SortingState();


	public void setState(PackageState state) {

	this.currentState = state;

	}

	public void getState(PackageState state) {

	state.showState();

	}



	public interface PackageState {

	  void send(Package pkg);

	  void receive(Package pkg);

	  void showState();

	}



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

}



