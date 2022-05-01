
public class TimeKeeper {
	private static int CurrentDay;
	
	public TimeKeeper() {
		
		CurrentDay = 0;
		
	}
	
	public static void AdvanceDay(){
		
		CurrentDay++;
		
	}
	
	public static int getCurrentDay() {
		
		return CurrentDay;
		
	}

}
