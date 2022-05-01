
public class MailTrackingSystem{

    @SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException{
    	
    	Map testDriver = Map.getInstance();
    	
    	String testRecName = "Jack Nicholson";
    	String testRecAddr = "123 Maple Street";
    	String testRecCity = "Dallas";
    	String testRecState = "Texas";
    	int testRecZip = 77482;
    	String testCurCity = "Oklahoma_City";
    	Map.PostOffice testCurrLoc = testDriver.getPostOffice(testCurCity);
    	
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
    	
    	/*
    	for(int i = 0; i < testDriver.officeList.size(); i++) {
    		System.out.println(testDriver.officeList.get(i));
    	}
    	
    	System.out.println(testDriver.officeList.get(0).getNeighborList());
    	
    	System.out.println(testDriver.roadList.get(0).getEdgeTime()+" hour drive");
    	*/
    }
}