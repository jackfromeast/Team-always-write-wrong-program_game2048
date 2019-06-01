import java.util.*;
public class DotcomBust {
	
	
	private GameHelper helper= new GameHelper();
	private ArrayList<SimpleDotCom> dotComsList=new ArrayList<SimpleDotCom>();
	private int numOfGuesses=0;
	
	private void setGame() {
		SimpleDotCom one= new SimpleDotCom();
		one.setName("Pets.com");
		SimpleDotCom two= new SimpleDotCom();
		two.setName("eToys.com");
		SimpleDotCom three= new SimpleDotCom();
		three.setName("Go2.com");
		dotComsList.add(one);
		dotComsList.add(two);
		dotComsList.add(three);
		
		System.out.println("Your goals is to sink three Dotcoms!");
		System.out.println("Pets.com; eToy.com; Go2.com");
		System.out.println("Try to sink them all in the fewest number of guesses!");
		
		for(SimpleDotCom temp: dotComsList) {
			ArrayList <String> newLocation= helper.placeDotCom(3);
			temp.setLocationCells(newLocation);
			System.out.print("sad");
		}
		
		
	}
	
	private void startPlaying() {
		//System.out.println("sad");
		while(!dotComsList.isEmpty()) {
			//System.out.println("test");
			String userGuess = helper.getUserInput("Enter a guess:");
			checkUserGuess(userGuess);
		}
		finishGame();
	}
	
	private void checkUserGuess(String userGuess) {
		numOfGuesses++;
		String result="miss";
		for(SimpleDotCom temp:dotComsList) {
			result= temp.checkYourself(userGuess);
			if(result.equals("hit")) {
				break;
			}
			if(result.equals("kill")) {
				break;
			}
			System.out.println(result);
		}
	}
	
	private void finishGame(){
		System.out.println("All Dots are dead!Your stock is now worthless!");
		if(numOfGuesses<=18) {
			System.out.println("It only took you"+numOfGuesses+"guesses.");
			System.out.println("You got out before your options sanl.");
		}
		else {
			System.out.println("Took long enough."+numOfGuesses+"guesses.");
			System.out.println("Fish are dancing with your options.");
		}
	}
	

    public static void main(String[] args) {
		// TODO Auto-generated method stub
		DotcomBust game= new DotcomBust();
		game.setGame();
		game.startPlaying();

     }
}

