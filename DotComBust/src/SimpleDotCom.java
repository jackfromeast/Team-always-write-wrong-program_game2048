import java.util.*;
public class SimpleDotCom {

	private ArrayList<String> locationCells;
	private String name;
	
	public String checkYourself(String usersInput) {
		String result = "miss";
		int index = locationCells.indexOf(usersInput);
		if(index>=0) {
			locationCells.remove(index);
			
			if(locationCells.isEmpty()) {
				result = "kill";
				System.out.println("Ouch! You sunk "+name+"!");
			}else {
				result = "hit";
			}
		}
		return result;
	}
	
	public void setName(String n) {
		name=n;
		
	}
	public void setLocationCells(ArrayList <String> loc){
		locationCells = loc;
	}
}
