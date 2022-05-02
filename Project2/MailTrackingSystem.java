import java.util.List;

public class MailTrackingSystem{

    @SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException{
    	
    	
    	Map testDriver = Map.getInstance();
    	
    	
    	String testRecName = "Jack Nicholson";
    	String testRecAddr = "123 Maple Street";
    	String testRecCity = "Dallas";
    	String testRecState = "Texas";
    	int testRecZip = 77482;
    	String testCurCity = "New_Orleans";
    	Map.PostOffice testCurrLoc = testDriver.getPostOffice(testCurCity);
    	Map.PostOffice testDestLoc = testDriver.getPostOffice("Minneapolis");
    	
    	Map.computePaths(testCurrLoc);
    	
    	System.out.println("Distance to " + testDestLoc + ": " + testDestLoc.minDistance );
    	
    	List<Map.PostOffice> path = Map.getShortestPathTo(testDestLoc);
    	
    	System.out.println("Path: " + path);
    	
    	for(int i = 0; i < path.size(); i++) {
    		System.out.println(path.get(i).getCityName());
    	}
    	
    	System.out.println(testCurrLoc.getNeighborList());
    	
    	/*
    	Package pack = new Package(testRecName, testRecAddr, testRecCity, testRecState, testRecZip, testCurrLoc, testCurCity);
    	
    	System.out.println(pack);
    	
    	pack.goTo(testDriver.getStatePostOffice("KS"));
    	
    	System.out.println(pack);
    	
    	pack.goTo(testDriver.getPostOffice("Lincoln"));
    	
    	System.out.println(pack);
    	
    	pack.goTo(testDriver.getStatePostOffice("CO"));
    	
    	System.out.println(pack);
    	
    	pack.goTo(testDriver.getStatePostOffice("NM"));
    	
    	System.out.println(pack);
    	
    	pack.goTo(testDriver.getStatePostOffice("TX"));
    	
    	*/
    	
    	/*
    	for(int i = 0; i < testDriver.officeList.size(); i++) {
    		System.out.println(testDriver.officeList.get(i));
    	}
    	
    	System.out.println(testDriver.officeList.get(0).getNeighborList());
    	
    	System.out.println(testDriver.roadList.get(0).getEdgeTime()+" hour drive");
    	*/
    }
}
