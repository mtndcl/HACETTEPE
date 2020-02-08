import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		
		
		try {
			Scanner scanner = new Scanner(new File(args[0]));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				String[] splitted = line.split("\t");
				User user = new User( splitted[0], splitted[1], splitted[2],splitted[3], splitted[4], splitted[5]);
				UserCollection.userlist.add(user);
			}

			scanner.close();
		}

		catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
		}
		try {
			Scanner scanner = new Scanner(new File(args[1]));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] splitted = line.split("\t");
				if(new String("ADDFRIEND").equals(splitted[0])){
					UserCollection.addfiend(line);
				}
				if(new String("BLOCKFRIEND").equals(splitted[0])){
					UserCollection.blockfiend(line);
				}
				if(new String("ADDPOST-TEXT").equals(splitted[0])){
					UserCollection.addtext(line);
				}
				if(new String("ADDPOST-IMAGE").equals(splitted[0])){
					UserCollection.addimage(line);
				}
				if(new String("ADDPOST-VIDEO").equals(splitted[0])){
					UserCollection.addvideo(line);
				}
				
				
			}

			scanner.close();
		}

		catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
		}
		
		loginpage firstpage=new loginpage();
		
		
	}

}
