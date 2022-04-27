import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Map {
	
	static class PostOffice{
		private String cityName;
		private String state;
		private int packagesSent;
		private int packagesReceived;
		private boolean available;
		private boolean hub;
		private LinkedHashMap<PostOffice, Double> neighbors = new LinkedHashMap<PostOffice, Double>();
		
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
		
		public void addNeighbor(PostOffice neighbor, double distance) {
			neighbors.put(neighbor, distance);
		}
		
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
		
		public void getNeighbors() {
			System.out.println(neighbors);
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
	}
	
	
	
	static class Edge {
		int source;
		int destination;
		int weight;

		public Edge(int source, int destination, int weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}
	}

	static class Graph {
		int vertices;
		LinkedList<Edge> [] adjacencylist;

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
		PostOffice Pittsburgh = new PostOffice("PittsBurgh", "PA", true);
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
		/*
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 2, 3);
		graph.addEdge(1, 3, 2);
		graph.addEdge(1, 2, 5);
		graph.addEdge(2, 3, 7);
		graph.addEdge(3, 4, 2);
		graph.addEdge(4, 0, 4);
		graph.addEdge(4, 1, 4);
		graph.addEdge(4, 5, 6);
		graph.printGraph();*/
	}
}
