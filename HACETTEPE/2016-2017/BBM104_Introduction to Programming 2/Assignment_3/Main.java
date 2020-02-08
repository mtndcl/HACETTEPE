import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Main {
	/**
	 * 
	 * @param args .txt files
	 */
	public static void main(String[] args) {
		int ID = 1;

		try {
			Scanner scanner = new Scanner(new File(args[0]));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				String[] splitted = line.split("\t");
				User user = new User(ID, splitted[0], splitted[1], splitted[2],
						splitted[3], splitted[4]);
				UserCollection.userlist.add(user);
				ID++;
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
				
				if (new String("ADDUSER").equals(splitted[0])) {
					
					UserCollection.addUser(ID, splitted[1], splitted[2],
							splitted[3], splitted[4], splitted[5]);

					ID++;
				}
				if (new String("REMOVEUSER").equals(splitted[0])) {

					UserCollection.removeUser(Integer.parseInt(splitted[1]));

				}
				if (new String("SHOWPOSTS").equals(splitted[0])) {
					UserCollection.showPosts((splitted[1]));
				}
				
					UserCollection.userSingIn(splitted[0], line );	
				
			}

			scanner.close();
		}

		catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
		}

	}

}
