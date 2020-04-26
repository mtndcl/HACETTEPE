import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	
	
	
	public static ArrayList<Sport>  sports=new ArrayList<Sport>();
	
	
	public static void main(String[] args) throws IOException {
		
		
		
		String filename=args[0];
		AddTeams(filename);
		Operations(filename);
		
		int volleyball=1;
		int handball=1;
		int basketball=1;
		int ice_hockey=1;
		FileWriter volleyballfile=CreateFile("volleyball.txt");
		FileWriter handballfile=CreateFile("handball.txt");
		FileWriter basketballfile=CreateFile("basketball.txt");
		FileWriter ice_hockeyfile=CreateFile("ice_hockey.txt");
		for (Sport sport : sports) {
			
			
			switch (sport.getClass().toString()) {
				case "class VOLLEYBALL":
					//System.out.println("---->>>" +sport.toString());
					volleyballfile.write(volleyball++ + "\t"+ sport.toString());
					break;
				case "class HANDBALL":
					//System.out.println("---->>>" +sport.toString());
					handballfile.write(handball++ + "\t"+ sport.toString());
					break;
				case "class BASKETBALL":
					//System.out.println("---->>>" +sport.toString());
					basketballfile.write(basketball++ + "\t"+ sport.toString());
					break;
				case "class ICE_HOCKEY":
					//System.out.println("---->>>" +sport.toString());
					ice_hockeyfile.write(ice_hockey++ + "\t"+ sport.toString());
					break;
				default:
					break;
			}
			
		}
		volleyballfile.close();
		handballfile.close();
		basketballfile.close();
		ice_hockeyfile.close();
		

	}

	private static FileWriter CreateFile(String name) {
		File file = new File(name);
		  
		
		
		try {
			return  new FileWriter(file);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		return null;
	}

	private static void Operations(String filename) {
		try {

            File f = new File(filename);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine =null;
            while ((readLine = b.readLine()) != null) {
                
            	String[] splitted=readLine.split("\t");
            	String[] scores=splitted[3].split(":");
            	int team1score=Integer.parseInt(scores[0]);
            	int team2score=Integer.parseInt(scores[1]);
            	Sport sport1=Findteam(splitted[0]+splitted[1]);
            	
            	Sport sport2=Findteam(splitted[0]+splitted[2]);
            	//System.out.println(splitted[1]+"<---------->"+splitted[2]);
            	if(team1score>team2score) {
            		
            	
            			//System.err.println("Winner  home: "+sport1.getClubName()+" Score : "+sport1.getScore());
            			sport1.Win(team1score, team2score);
            			sport2.Lost(team2score, team1score);
            		
            	}else if(team1score<team2score) {
            		
            			//System.err.println("Winner guest : "+sport2.getClubName() +" Score : "+sport2.getScore());
            			sport1.Lost(team1score, team2score);
            			sport2.Win(team2score, team1score);
            			
            		
            	}else {
            		sport1.Tie(team1score);
            		sport2.Tie(team1score);
            	}
            	
            	
            }
            b.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
		Collections.sort(sports, new Sortteam());
		
	}

	private static Sport Findteam(String id) {
		
		return 	sports.stream().filter(sport -> id.equals(sport.getClubID())).findFirst().orElse(null);
	}

	private static void AddTeams(String filename) {
		
		try {

            File f = new File(filename);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine =null;
            while ((readLine = b.readLine()) != null) {
                
            	String[] splitted=readLine.split("\t");
            	

            	switch(splitted[0]){
            		
            		case "I":
            			
            			AddIceHockey(splitted[1]);
            			AddIceHockey(splitted[2]);
            			
            			break;
            		case "H":
            			AddHandBall(splitted[1]);
            			AddHandBall(splitted[2]);
            			
            			break;
            		case "B":
            			AddBasketball(splitted[1]);
            			AddBasketball(splitted[2]);
            			break;
            		case "V":
            			AddVolleyBall(splitted[1]);
            			AddVolleyBall(splitted[2]);
            			break;
            		default:
            			break;
            	}
            }
            b.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private static boolean isExist(String name) {
		
		Sport x=sports.stream().filter(sport -> name.equals(sport.getClubID())).findFirst().orElse(null);
		return x!=null;
	}
	private static void AddHandBall(String name) {
		if(!isExist("H"+name)) {
			HANDBALL handball=new HANDBALL(name);
			sports.add(handball);
		}
	}
	private static void AddIceHockey(String name) {
		if(!isExist("I"+name)) {
			ICE_HOCKEY hockey=new ICE_HOCKEY(name);
			sports.add(hockey);
		}
	}
	private static void AddBasketball(String name) {
		if(!isExist("B"+name)) {
			BASKETBALL basketball=new BASKETBALL(name);
			sports.add(basketball);
		}
	}
	private static void AddVolleyBall(String name) {
		if(!isExist("V"+name)) {
			VOLLEYBALL volleyball=new VOLLEYBALL(name);
			sports.add(volleyball);
		}
	}

}
