import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

//Map class begins ===========================================================================
public class Map {
	
	private static Map instance;
	
	public static ArrayList<PostOffice> officeList = new ArrayList<PostOffice>(); //gets? sets?
	public static ArrayList<Edge> roadList = new ArrayList<Edge>();
	
	
	@SuppressWarnings("unused")
	private static TimeKeeper time;
	
	final String POSTOFFICE_PATH = "src\\postoffices.txt";
	final String ROADS_PATH = "src\\roads.txt";
	
	//Map constructor begins ===========================================================================
	private Map() {
		
		Scanner input;
		Scanner input2;
		
		try {
			
			time = new TimeKeeper();
			
			
			
			input = new Scanner(new File(POSTOFFICE_PATH));
			
			
			input2 = new Scanner(new File(ROADS_PATH));
			
			
			
			String city;
			String state;
			String isHub;
			boolean bool;
			
			String city1;
			String city2;
			PostOffice cityObj1 = new PostOffice();
			PostOffice cityObj2 = new PostOffice();
			int weight;
			
			while(input.hasNextLine()) {
				
				city = input.next();
				state = input.next();
				isHub = input.next();
				bool = false;
				
				if(isHub.toLowerCase().equals("true")) {
					bool = true;
				}else{
					bool = false;
				}
				officeList.add(new PostOffice(city, state, bool)); 
				
			}

			input.close();
			
			while(input2.hasNextLine()) {
				
				city1 = input2.next();
				city2 = input2.next();
				weight = input2.nextInt();
			
				
				
				for(int i = 0; i < officeList.size(); i++) {
					if(city1.equals(officeList.get(i).getCityName())) {
						cityObj1 = officeList.get(i);
					}
					
					if(city2.equals(officeList.get(i).getCityName())) {
						cityObj2 = officeList.get(i);
					}
				}
				
				
				
				roadList.add(new Edge(cityObj1, cityObj2, weight));
				roadList.add(new Edge(cityObj2, cityObj1, weight));

				
			}
			
			input2.close();
		
		} 
		catch (FileNotFoundException e) {
			System.out.print("File not found!");
		}
	}//Map constructor ends ===========================================================================
	
	
	
	static {
		instance = new Map();
	}
	
	public static Map getInstance() {
		return instance;
	}
	
	public ArrayList<Edge> getRoadList(){
		return roadList;
	}
	
	public PostOffice getPostOffice(String name) { //Quick way to retrieve a Post Office from the ArrayList via City Name
		for(int i = 0; i < officeList.size(); i++) {
			if(name.equals(officeList.get(i).getCityName())) {
				return officeList.get(i);
			}
		}
		return new PostOffice(name); //Return PO with given name and default attributes if not found in database
	}
	
	public PostOffice getStatePostOffice(String state) { //Quick way to retrieve a Post Office from the ArrayList via State Name (ONLY ABBREVIATIONS, i.e. TX, NE, LA, etc.)
		for(int i = 0; i < officeList.size(); i++) {
			if(state.equals(officeList.get(i).getState())) {
				return officeList.get(i);
			}
		}
		return new PostOffice(); //Return default PO if state not found in database
	}
	
//PostOffice class begins ===========================================================================
	static class PostOffice implements Comparable<PostOffice>{
		private String cityName;
		private String state;
		private int packagesSent;
		private int packagesReceived;
		private boolean available;
		private boolean hub;
		public double minDistance = Double.POSITIVE_INFINITY;
		private PostOffice previous;
		
		//private ArrayList<Edge> neighborPaths = new ArrayList<Edge>();
		
		PostOffice(){
			
			this.cityName = "DEFAULT_CITY";
			this.state = "DEFAULT_STATE";
			this.hub = false;
			
			packagesSent = 0;
			packagesReceived = 0;
			available = true;
			
		}
		
		PostOffice(String cityName){
			
			this.cityName = cityName;
			this.state = "DEFAULT_STATE";
			this.hub = false;
			
			packagesSent = 0;
			packagesReceived = 0;
			available = true;
			
		}
		
		PostOffice(String cityName, String state, boolean hub){
			
			this.cityName = cityName;
			this.state = state;
			this.hub = hub;
			
			packagesSent = 0;
			packagesReceived = 0;
			available = true;
			
		}
		
		public void setPackagesSent(int num) {
			packagesSent = num;
		}
		
		public void setPackagesReceived(int num) {
			packagesReceived = num;
		}
		
		public void addPackagesSent(int num) {
			packagesSent += num;
		}
		
		public void addPackagesReceived(int num) {
			packagesReceived += num;
		}
		/*
		public void addNeighbor(PostOffice neighbor, double distance) {
			neighbors.put(neighbor, distance);
		}
		*/
		public void setAvailability(boolean status) {
			available = status;
		}
		
		public String getCityName() {
			return cityName;
		}
		
		public String getState() {
			return state;
		}
		
		public int getPackagesSent() {
			return packagesSent;
		}
		
		public int getPackagesReceived() {
			return packagesReceived;
		}
		public PostOffice getPrevious() {
			return previous;
		}
		public ArrayList<Edge> getNeighborList() {
			
			ArrayList<Edge> tempList = new ArrayList<Edge>();
			
			for(int i = 0; i < roadList.size(); i++) {
				if(cityName.equals(roadList.get(i).source.getCityName())) {
					tempList.add(roadList.get(i));
				}
			}
			
			return tempList;
		}
		
		public Edge getEdgeTo(PostOffice x) {
			for(int i = 0; i < this.getNeighborList().size(); i++) {
				
				if(getNeighborList().get(i).source.getCityName().equals(this.getCityName()) && getNeighborList().get(i).destination.getCityName().equals(x.getCityName())) {
					return getNeighborList().get(i);
				}
			}
			
			System.out.println("Edge not found!");
			
			return null;
		}
		
		public ArrayList<String> getNeighbors() {
			
			ArrayList<String> tempList = new ArrayList<String>();
			
			for(int i = 0; i < roadList.size(); i++) {
				if(cityName.equals(roadList.get(i).source.getCityName())) {
					tempList.add(roadList.get(i).destination.getCityName());
				}
			}
			
			return tempList;
		}
		
		public ArrayList<PostOffice> getObjNeighbors() {
			
			ArrayList<PostOffice> tempList = new ArrayList<PostOffice>();
			
			for(int i = 0; i < roadList.size(); i++) {
				if(cityName.equals(roadList.get(i).source.getCityName())) {
					for(int j = 0; j < officeList.size(); j++) {
						if(roadList.get(i).destination.getCityName().equals(officeList.get(j).getCityName())) {
							tempList.add(officeList.get(j));
						}
					}
				}
			}
			
			return tempList;
		}
		
		public boolean isAvailable() {
			return available;
		}
		
		public boolean isHub() {
			return hub;
		}
		
		public String toString() {
			return "City: "+cityName+", State: "+state+", Packages Sent: "+packagesSent+", Packages Received: "+packagesReceived+", isAvailable: "+available+", isHub: "+hub;
		}

		public int compareTo(PostOffice other) {
			return Double.compare(minDistance, other.minDistance);
		}
	}//End of PostOffice class  ===========================================================================
	
	
	
// Edge class begins ===========================================================================
	static class Edge {
		PostOffice source;
		PostOffice destination;
		int weight;

		public Edge(PostOffice source, PostOffice destination, int weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}

		public double getEdgeTime() {
			return (double)weight / 65;
		}
		
		public String toString() {
			return weight+" mile route from "+source.getCityName()+" to "+destination.getCityName();
		}
	}
	
	
	public static void computePaths(PostOffice source)
    {
        source.minDistance = 0.;
        PriorityQueue<PostOffice> PostOfficeQueue = new PriorityQueue<PostOffice>();
        PostOfficeQueue.add(source);

        while (!PostOfficeQueue.isEmpty()) {
            PostOffice u = PostOfficeQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.getNeighborList())
            {
            	PostOffice v = e.destination;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                	PostOfficeQueue.remove(v);

                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    PostOfficeQueue.add(v);
                }
            }
        }
    }

    public static List<PostOffice> getShortestPathTo(PostOffice target)
    {
        List<PostOffice> path = new ArrayList<PostOffice>();
        for (PostOffice vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// End of Edge class  ===========================================================================

// Commented out for testing purposes
	/*
	static class Graph {
		int vertices;
		LinkedList<Edge> [] adjacencylist;

		@SuppressWarnings("unchecked")
		Graph(int vertices) {
			this.vertices = vertices;
			adjacencylist = new LinkedList[vertices];
			//initialize adjacency lists for all the vertices
			for (int i = 0; i <vertices ; i++) {
				adjacencylist[i] = new LinkedList<>();
			}
		}

		public void addEdge(int source, int destination, int weight) {
			Edge edge = new Edge(source, destination, weight);
			adjacencylist[source].addFirst(edge); //for directed graph
		}

		public void printGraph(){
			for (int i = 0; i <vertices ; i++) {
				LinkedList<Edge> list = adjacencylist[i];
				for (int j = 0; j <list.size() ; j++) {
					System.out.println("vertex-" + i + " is connected to " +
							list.get(j).destination + " with weight " + list.get(j).weight);
				}
			}
		}
		
	}
	*/
	
	
	
	
	/*
	
	public static void main(String[] args) {
		
		//State Hubs (should be 50)
		
		PostOffice Montgomery = new PostOffice("Montgomery", "AL", true);
		PostOffice Anchorage = new PostOffice("Anchorage", "AK", true);
		PostOffice Phoenix = new PostOffice("Phoenix", "AZ", true);
		PostOffice LittleRock = new PostOffice("Little Rock", "AR", true);
		PostOffice LosAngeles = new PostOffice("Los Angeles", "CA", true);
		PostOffice Denver = new PostOffice("Denver", "CO", true);
		PostOffice Hartford = new PostOffice("Hartford", "CT", true);
		PostOffice Wilmington = new PostOffice("Wilmington", "DE", true);
		PostOffice Orlando = new PostOffice("Orlando", "FL", true);
		PostOffice Atlanta = new PostOffice("Atlanta", "GA", true);
		PostOffice Honolulu = new PostOffice("Honolulu", "HI", true);
		PostOffice Boise = new PostOffice("Boise", "ID", true);
		PostOffice Chicago = new PostOffice("Chicago", "IL", true);
		PostOffice Indianapolis = new PostOffice("Indianapolis", "IN", true);
		PostOffice CedarRapids = new PostOffice("Cedar Rapids", "IA", true);
		PostOffice KansasCity = new PostOffice("KansasCity", "KS", true);
		PostOffice Louisville = new PostOffice("Louisville", "KY", true);
		PostOffice NewOrleans = new PostOffice("New Orleans", "LA", true);
		PostOffice Bangor = new PostOffice("Bangor", "ME", true);
		PostOffice Baltimore = new PostOffice("Baltimore", "MD", true);
		PostOffice Boston = new PostOffice("Boston", "MA", true);
		PostOffice Detroit = new PostOffice("Detroit", "MI", true);
		PostOffice Minneapolis = new PostOffice("Minneapolis", "MN", true);
		PostOffice Jackson = new PostOffice("Jackson", "MS", true);
		PostOffice StLouis = new PostOffice("St. Louis", "MO", true);
		PostOffice GreatFalls = new PostOffice("Great Falls", "MT", true);
		PostOffice Lincoln = new PostOffice("Lincoln", "NE", true);
		PostOffice LasVegas = new PostOffice("Las Vegas", "NV", true);
		PostOffice Concord = new PostOffice("Concord", "NH", true);
		PostOffice Trenton = new PostOffice("Trenton", "NJ", true);
		PostOffice Albuquerque = new PostOffice("Albuquerque", "NM", true);
		PostOffice NewYorkCity = new PostOffice("New York City", "NY", true);
		PostOffice Charlotte = new PostOffice("Charlotte", "NC", true);
		PostOffice Fargo = new PostOffice("Fargo", "ND", true);
		PostOffice Columbus = new PostOffice("Columbus", "OH", true);
		PostOffice OklahomaCity = new PostOffice("Oklahoma City", "OK", true);
		PostOffice Portland = new PostOffice("Portland", "OR", true);
		PostOffice Pittsburgh = new PostOffice("Pittsburgh", "PA", true);
		PostOffice Providence = new PostOffice("Providence", "RI", true);
		PostOffice Charleston = new PostOffice("Charleston", "SC", true);
		PostOffice SiouxFalls = new PostOffice("Sioux Falls", "SD", true);
		PostOffice Nashville = new PostOffice("Nashville", "TN", true);
		PostOffice Dallas = new PostOffice("Dallas", "TX", true);
		PostOffice SaltLakeCity = new PostOffice("Salt Lake City", "UT", true);
		PostOffice Burlington = new PostOffice("Burlington", "VT", true);
		PostOffice Richmond = new PostOffice("Richmond", "VA", true);
		PostOffice Seattle = new PostOffice("Seattle", "WA", true);
		PostOffice Morgantown = new PostOffice("Morgantown", "WV", true);
		PostOffice Milwaukee = new PostOffice("Milwaukee", "WI", true);
		PostOffice Buffalo = new PostOffice("Buffalo", "WY", true);
		
		//Texas sub-PostOffices
		PostOffice Houston = new PostOffice("Houston", "TX", false);
		PostOffice Austin = new PostOffice("Austin", "TX", false);
		PostOffice SanAntonio = new PostOffice("San Antonio", "TX", false);
		
		
		//NOTE may just want to make the hubs the only post offices that are neighbors with other state post offices to reduce edges
		//and sub postoffices may only have one neighbor being the hub
		//example Austin goes to Dallas before going to Houston rather than Austin to Houston
		
		Houston.addPackagesReceived(5);
		
		Houston.addNeighbor(Austin, 162);
		
		Houston.addNeighbor(Dallas, 239);
		
		Dallas.addNeighbor(NewOrleans, 492);
		
		
		
		System.out.println(Houston);
		System.out.println(Dallas);
		
		Houston.getNeighbors();
		Dallas.getNeighbors();
		
		
		
		int vertices = 6;
		Graph graph = new Graph(vertices);
		
		//graph.addEdge(0, 1, 4);
		//graph.addEdge(0, 2, 3);
		//graph.addEdge(1, 3, 2);
		//graph.addEdge(1, 2, 5);
		//graph.addEdge(2, 3, 7);
		//graph.addEdge(3, 4, 2);
		//graph.addEdge(4, 0, 4);
		//graph.addEdge(4, 1, 4);
		//graph.addEdge(4, 5, 6);
		//graph.printGraph();
	}
	
	*/
} //Map class ends ===========================================================================
