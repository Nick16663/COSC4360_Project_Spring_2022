import java.util.ArrayList;

public class CityHub {
	
	private String name; //City's name
	private String state; //City's state
	private boolean isHub; //Is this city a main hub?
	private int numReceived; //Number of packages received
	private int numSent; //Number of packages sent
	private ArrayList<CityHub> neighbors; //List of city's neighbors
	
	// Constructors
	
	public CityHub() { //Default
		name = "DEFAULT";
		state = "DEFAULT_STATE";
		isHub = false;
		numReceived = 0;
		numSent = 0;
	}
	
	public CityHub(String n, String s, boolean bool, CityHub neighborCity) { //Partial constructor that designates one neighboring city.
		name = n;
		state = s;
		isHub = bool;
		numReceived = 0;
		numSent = 0;
		neighbors.add(neighborCity);
	}
	
	public CityHub(String n, String s, boolean bool, int rec, int sent, CityHub neighborCity) { //Partial constructor that designates number of sent and received packages and one neighboring city.
		name = n;
		state = s;
		isHub = bool;
		numReceived = rec;
		numSent = sent;
		neighbors.add(neighborCity);
	}
	
	public CityHub(String n, String s, boolean bool, int rec, int sent, ArrayList<CityHub> neigh) { //Full constructor
		name = n;
		state = s;
		isHub = bool;
		numReceived = rec;
		numSent = sent;
		neighbors = neigh;
	}
	
	// Methods regarding
	// the city's name.
	
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	
	// Methods regarding
	// the city's state.
	
	public String getState() {
		return state;
	}
	
	public void setState(String s) {
		state = s;
	}
	
	
	// Methods regarding
	// whether the city is a Hub or not.
	
	public boolean getIsHub() {
		return isHub;
	}
	
	public void setHub(boolean bool) {
		isHub = bool;
	}
	
	
	// Methods regarding
	// the number of received items, or numReceived.
	
	public int getNumReceived() {
		return numReceived;
	}
	
	public void incNumReceived() {
		numReceived++;
	}
	
	public void incNumreceived(int i) {
		numReceived += i;
	}
	
	public void setNumReceived(int i) {
		numReceived = i;
	}
	
	
	// Methods regarding
	// the number of sent items, or numSent.
	
	public int getNumSent() {
		return numSent;
	}
	
	public void incNumSent() {
		numSent++;
	}
	
	public void incNumSent(int i) {
		numSent += i;
	}
	
	public void setNumSent(int i) {
		numSent = i;
	}
	
	
	// Methods regarding
	// the city's neighbors in the form of an ArrayList.
	
	public ArrayList<CityHub> getNeighbors() {
		return neighbors;
	}
	
	public CityHub getNeighborAt(int i) {
		return neighbors.get(i);
	}
	
	public boolean hasNeighbor(CityHub city) {
		if(neighbors.contains(city)) {
			return true;
		}else
			return false;
	}
	
	public boolean addNeighbor(CityHub city) {
		if(!neighbors.contains(city)) {
			neighbors.add(city);
			return true;
		}else
			return false;
	}
	
	public boolean removeNeighbors(CityHub city) {
		if(neighbors.contains(city)) {
			neighbors.remove(city);
			return true;
		}else
			return false;
	}
	
	public boolean removeNeighbors(int i) {
		if(neighbors.size() > i) {
			neighbors.remove(i);
			return true;
		}else
			return false;
	}
	
	//Weird method, may or may not use; attempts to add neighboring cities via name (string), state (string), and hub status (boolean)
	
	/*
	public void addNeighbor(String cityName, String cityState, boolean hub) {
		CityHub city = new CityHub(cityName, cityState, hub, this);
		neighbors.add(city);
	}
	*/
}
