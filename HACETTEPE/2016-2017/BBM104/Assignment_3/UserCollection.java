import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class UserCollection {

	static ArrayList<User> userlist = new ArrayList<User>();
	static ArrayList<User> usersignin = new ArrayList<User>();
	static ArrayList<User> addfriend = new ArrayList<User>();
	static ArrayList<User> bloclist = new ArrayList<User>();

	public UserCollection() {

	}
/**
 * 
 * @param ID  id of post
 * @param really_name real name of user 
 * @param user_name nickname of user 
 * @param password	password
 * @param birth_date birth time of user
 * @param school user school
 * @return   add user in user list
 */
	public static boolean addUser(int ID, String really_name, String user_name,
			String password, String birth_date, String school) {

		User user = new User(ID, really_name, user_name, password, birth_date,
				school);

		userlist.add(user);
		System.out.println("-----------------------");
		System.out.println("Command: ADDUSER " + ID + " " + really_name + " "
				+ user_name + " " + password + " " + birth_date + " " + school);
		System.out.println(really_name + " has been successfully added.");

		return true;

	}
/**
 * 
 * @param ID     who you want remove fot user
 * @return  remove user from user list
 */
	public static boolean removeUser(int ID) {
		System.out.println("-----------------------");

		System.out.println("Command: REMOVEUSER " + ID);

		int have = 0;
		for (User user : userlist) {
			if (user.getID() == ID) {
				have = 1;
				userlist.remove(user);
				break;
			}
		}
		if (have == 0) {
			System.out.println("No such user!");
		}
		if (have == 1) {
			System.out.println("User has been successfully removed.");
		}
		return true;

	}
/**
 * 
 * @param command   command word
 * @param line   all of argument
 * @return make user process
 */
	public static boolean userSingIn(String command, String line) {
		String[] info = line.split("\t");
		if (new String("SIGNIN").equals(command)) {
			int haveuser = 0;
			int passpasword = 0;
			System.out.println("-----------------------");
			System.out.println("Command: SINGIN " + info[1] + " " + info[2]);
			for (User user : userlist) {

				if (user.getUser_name().equals(info[1]))
					haveuser = 1;
				if (user.getPassword().equals(info[2])) {
					passpasword = 1;
					if(usersignin.size()==0){
						System.out.println("You have successfully signed in.");
						usersignin.add(user);
					}

				}

			}
			if ((haveuser == 1 && passpasword == 0)
					|| (haveuser == 0 && passpasword == 1)) {
				System.out
						.println("Invalid username or password! Please try again.");

			}
			if (haveuser == 0) {
				System.out.println("No such user!");

			}

		}
		if (new String("SIGNOUT").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: SIGNOUT");
			System.out.println("You have successfully signed out.");
			usersignin.clear();
			addfriend.clear();
			bloclist.clear();

		}
		if (new String("CHPASS").equals(command)) {
			int in = 0;
			int pass = 0;
			System.out.println("-----------------------");
			System.out.println("Command: CHPASS" + " " + info[1] + " "
					+ info[2]);

			if (UserCollection.usersignin.size() == 1) {
				in = 1;
				for (User user : UserCollection.usersignin) {

					if (user.getPassword().equals(info[1])) {
						pass = 1;
						user.setPassword(info[2]);
					}
				}

			}
			if (in == 0 && in == 0) {
				System.out.println("Error: Please sign in and try again.");

			}
			if (in == 1 && pass == 0) {
				System.out.println("Password mismatch! Please, try again.");

			}

		}
		if (new String("ADDFRIEND").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: ADDFRIEND" + " " + info[1]);
			if (usersignin.size() == 1) {

				int haveuser = 0;
				int havefriend = 0;

				for (User friend : addfriend) {
					if (friend.getUser_name().equals(info[1])) {
						haveuser = 1;
						havefriend = 1;
						System.out
								.println("This user is already in your friend list!");
						break;
					}

				}
				for (User user : userlist) {

					if (user.getUser_name().equals(info[1]) && havefriend == 0) {
						haveuser = 1;
						addfriend.add(user);
						System.out
								.println(info[1]
										+ " has been successfully added to your friend list.");

					}

				}
				if (haveuser == 0) {
					System.out.println("No such user!");

				}
			} else {
				System.out.println("Error: Please sign in and try again.");

			}
		}
		if (new String("REMOVEFRIEND").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: REMOVEFRIEND" + " " + info[1]);
			int havefro = 0;
			if (usersignin.size() == 1) {

				for (User friend : addfriend) {

					if (friend.getUser_name().equals(info[1])) {
						havefro = 1;
						System.out
								.println(info[1]
										+ " has been successfully removed from your friend list.");
						addfriend.remove(friend);
						break;
					}
				}
				if (havefro == 0) {
					System.out.println("No such friend!");
				}
			} else {
				System.out.println("Error: Please sign in and try again.");

			}

		}
		if (new String("BLOCK").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: BLOCK" + " " + info[1]);
			int haveuser = 0;
			if (usersignin.size() == 1) {
				for (User user : userlist) {
					if (user.getUser_name().equals(info[1])) {
						haveuser = 1;
						bloclist.add(user);
						System.out.println(user.getUser_name()
								+ " has been successfully blocked.");
					}
				}
				if (haveuser == 0) {
					System.out.println("No such user!");

				}

			} else {
				System.out.println("Error: Please sign in and try again.");

			}
		}
		if (new String("UNBLOCK").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: UNBLOCK" + " " + info[1]);
			int haveuser = 0;
			if (usersignin.size() == 1) {

				for (User blocked : bloclist) {
					if (blocked.getUser_name().equals(info[1])) {
						haveuser = 1;
						bloclist.remove(blocked);
						System.out.println(blocked.getUser_name()
								+ " has been successfully unblocked.");
						break;
					}
				}
				if (haveuser == 0) {
					System.out
							.println("No such user in your blocked users list!");
				}
			} else {
				System.out.println("Error: Please sign in and try again.");

			}
		}
		if (new String("LISTFRIENDS").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: LISTFRIENDS");
			if (usersignin.size() == 1) {
				if (addfriend.size() == 0) {
					System.out.println("You have not added any friends yet!");
				} else {
					for (User friend : addfriend) {
						System.out.println("Name: " + friend.getReally_name());
						System.out
								.println("Username: " + friend.getUser_name());
						System.out.println("Date of Birth: "
								+ friend.getbirth_date());
						System.out.println("School: " + friend.getSchool());
						System.out.println("-----------------------");
					}
				}
			} else {
				System.out.println("Error: Please sign in and try again.");

			}

		}
		if (new String("LISTUSERS").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: LISTUSERS");
			if (usersignin.size() == 1) {
				for (User user : userlist) {
					System.out.println("Name: " + user.getReally_name());
					System.out.println("Username: " + user.getUser_name());
					System.out
							.println("Date of Birth: " + user.getbirth_date());
					System.out.println("School: " + user.getSchool());
					System.out.println("-----------------------");
				}

			} else {
				System.out.println("Error: Please sign in and try again.");

			}
		}
		if (new String("UPDATEPROFILE").equals(command)) {

			System.out.println("-----------------------");
			System.out.println("Command: UPDATEPROFILE" + " " + info[1] + " "
					+ info[2] + " " + info[3]);
			if (usersignin.size() == 1) {

				for (User in : usersignin) {

					in.setReally_name(info[1]);
					in.setbirth_date(info[2]);
					in.setSchool(info[3]);
				}

			} else {
				System.out.println("Error: Please sign in and try again.");

			}

		}
		if (new String("SHOWBLOCKEDFRIENDS").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: SHOWBLOCKEDFRIENDS");
			int blockfriend = 0;
			int print = 0;
			if (usersignin.size() == 1) {
				if (bloclist.size() == 0) {
					print = 1;
					System.out.println("You have not blocked any users yet!");

				}
				for (User friend : addfriend) {
					for (User block : bloclist) {
						if (friend.getUser_name().equals(block.getUser_name())) {
							blockfriend = 1;
							System.out.println("Name: "
									+ block.getReally_name());
							System.out.println("Username: "
									+ block.getUser_name());
							System.out.println("Date of Birth: "
									+ block.getbirth_date());
							System.out.println("School: " + block.getSchool());
							System.out.println("-----------------------");
						}
					}

				}
				if (blockfriend == 0 && print == 0) {
					System.out.println("You have not blocked any friends yet!");
				}

			} else {
				System.out.println("Error: Please sign in and try again.");

			}
		}
		if (new String("SHOWBLOCKEDUSERS").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: SHOWBLOCKEDUSERS");
			if (usersignin.size() == 1) {
				if (bloclist.size() == 0) {
					System.out.println("You have not blocked any users yet!");
				}
				for (User block : bloclist) {
					System.out.println("Name: " + block.getReally_name());
					System.out.println("Username: " + block.getUser_name());
					System.out.println("Date of Birth: "
							+ block.getbirth_date());
					System.out.println("School: " + block.getSchool());
					System.out.println("-----------------------");
				}
			} else {
				System.out.println("Error: Please sign in and try again.");

			}
		}
		if (new String("ADDPOST-TEXT").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: ADDPOST-TEXT " + info[1] + " "
					+ info[2] + " " + info[3] + " " + info[4]);

			UUID id = UUID.randomUUID();

			if (usersignin.size() == 1) {

				ArrayList<String> taglenmeyikazananlar = new ArrayList<String>();
				ArrayList<String> taglenmeyikazaNAMAYANLAR = new ArrayList<String>();
				String[] taglenmeyeadaylar = info[4].split(":");
				int i;
				for (i = 0; i < taglenmeyeadaylar.length; i++) {

					int burada = 0;
					for (User friend : addfriend) {

						if (friend.getUser_name().equals(taglenmeyeadaylar[i])) {
							burada = 1;

							taglenmeyikazananlar.add(taglenmeyeadaylar[i]);

						}
					}
					if (burada == 0) {
						taglenmeyikazaNAMAYANLAR.add(taglenmeyeadaylar[i]);

					}

				}

				for (String sevimeyenler : taglenmeyikazaNAMAYANLAR) {

					System.out.println("Username " + sevimeyenler
							+ " is not your friend, and will not be tagged!");

				}
				TextPost postcuk = new TextPost(usersignin.get(0)
						.getUser_name(), id, info[1],
						Double.parseDouble(info[2]),
						Double.parseDouble(info[3]), taglenmeyikazananlar);

				User.textposts.add(postcuk);
				User.allposts.add(postcuk);

				System.out.println("The post has been successfully added.");

			} else {
				System.out.println("Error: Please sign in and try again.");

			}
		}
		if (new String("ADDPOST-IMAGE").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: ADDPOST-IMAGE " + info[1] + " "
					+ info[2] + " " + info[3] + " " + info[4] + " " + info[5]
					+ " " + info[6]);

			UUID id = UUID.randomUUID();

			if (usersignin.size() == 1) {

				ArrayList<String> taglenmeyikazananlar = new ArrayList<String>();
				ArrayList<String> taglenmeyikazaNAMAYANLAR = new ArrayList<String>();
				String[] taglenmeyeadaylar = info[4].split(":");
				int i;
				for (i = 0; i < taglenmeyeadaylar.length; i++) {

					int burada = 0;
					for (User friend : addfriend) {

						if (friend.getUser_name().equals(taglenmeyeadaylar[i])) {
							burada = 1;

							taglenmeyikazananlar.add(taglenmeyeadaylar[i]);

						}
					}
					if (burada == 0) {
						taglenmeyikazaNAMAYANLAR.add(taglenmeyeadaylar[i]);

					}

				}

				for (String sevimeyenler : taglenmeyikazaNAMAYANLAR) {

					System.out.println("Username " + sevimeyenler
							+ " is not your friend, and will not be tagged!");

				}
				String[] coz = info[6].split("x");
				TextPost image = new ImagePost(
						usersignin.get(0).getUser_name(), id, info[1],
						Double.parseDouble(info[2]),
						Double.parseDouble(info[3]), taglenmeyikazananlar,
						info[5], coz[0], coz[1]);
				User.imageposts.add(image);
				User.allposts.add(image);
				System.out.println("The post has been successfully added.");

			} else {
				System.out.println("Error: Please sign in and try again.");

			}
		}
		if (new String("ADDPOST-VIDEO").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: ADDPOST-VIDEO " + info[1] + " "
					+ info[2] + " " + info[3] + " " + info[4] + " " + info[5]
					+ " " + info[6]);

			UUID id = UUID.randomUUID();

			if (usersignin.size() == 1) {
				if (Integer.parseInt(info[6]) <= 10) {

					ArrayList<String> taglenmeyikazananlar = new ArrayList<String>();
					ArrayList<String> taglenmeyikazaNAMAYANLAR = new ArrayList<String>();
					String[] taglenmeyeadaylar = info[4].split(":");
					int i;
					for (i = 0; i < taglenmeyeadaylar.length; i++) {

						int burada = 0;
						for (User friend : addfriend) {

							if (friend.getUser_name().equals(
									taglenmeyeadaylar[i])) {
								burada = 1;

								taglenmeyikazananlar.add(taglenmeyeadaylar[i]);

							}
						}
						if (burada == 0) {
							taglenmeyikazaNAMAYANLAR.add(taglenmeyeadaylar[i]);

						}

					}

					for (String sevimeyenler : taglenmeyikazaNAMAYANLAR) {

						System.out
								.println("Username "
										+ sevimeyenler
										+ " is not your friend, and will not be tagged!");

					}
					TextPost video = new VideoPost(usersignin.get(0)
							.getUser_name(), id, info[1],
							Double.parseDouble(info[2]),
							Double.parseDouble(info[3]), taglenmeyikazananlar,
							info[5], Double.parseDouble(info[6]));

					User.videoposts.add(video);
					User.allposts.add(video);
					System.out.println("The post has been successfully added.");

				}

			} else {
				System.out.println("Error: Please sign in and try again.");

			}
		}
		if (new String("REMOVELASTPOST").equals(command)) {
			System.out.println("-----------------------");
			System.out.println("Command: REMOVELASTPOST ");

			if (usersignin.size() == 1) {
				if (User.allposts.size() == 0) {
					System.out.println("Error: You don not have any posts.");
				} else {
					int a = 0;
					for (Post post : User.allposts) {
						a++;
						if (User.allposts.size() == a) {
							User.allposts.remove(post);
							System.out
									.println("Your last post has been successfully removed.");
							break;
						}
					}
				}

			} else {
				System.out.println("Error: Please sign in and try again.");

			}
		}
		return true;
	}
/**
 * 
 * @param username   user name of user
 */
	public static void showPosts(String username) {
		System.out.println("-----------------------");
		System.out.println("Command: SHOWPOSTS " + username);
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date date = new Date();
		int adamvar = 0;
		int mesajvar = 0;
		
		for (User users : userlist) {
			
			if (users.getUser_name().equals(username)) {
				adamvar = 1;
				
					
				
				int yaz=0;
				if(yaz==0 ){
					System.out.println("**************");
					System.out.println(username+"s Posts");
					System.out.println("**************");
					yaz=1;
				}
				for (Post postlar : User.allposts) {
					if (username.equals(postlar.getusername())) {
						mesajvar = 1;
						
						if(postlar.getClass().equals(TextPost.class)){
							System.out.println(postlar.getText());
							System.out.println(postlar.getLatitude() + ", "
									+ postlar.getLongitude());
							System.out.println(dateFormat.format(date));
							if (postlar.gettaggedfriend().size() > 0) {
								System.out.print("Friends tagged in this post: ");
								int a = 0;
								for (int i = 0; postlar.gettaggedfriend().size() > i; i++) {
									System.out.print(postlar.gettaggedfriend().get(i));
									a++;
									if (a != postlar.gettaggedfriend().size()) {
										System.out.print(" ,");
									}
								}
								System.out.println();
								

							}
							System.out.println("-----------------------");
						}
						if(postlar.getClass().equals(ImagePost.class)){
							System.out.println(postlar.getText());
							System.out.println(postlar.getLatitude() + ", "
									+ postlar.getLongitude());
							System.out.println(dateFormat.format(date));
							System.out.println("Image: "+postlar.getfilename());
							System.out.println("Image resolution: "+postlar.getresolutionx()+"x"+postlar.getresolutiony());
							if (postlar.gettaggedfriend().size() > 0) {
								System.out.print("Friends tagged in this post: ");
								int a = 0;
								for (int i = 0; postlar.gettaggedfriend().size() > i; i++) {
									System.out.print(postlar.gettaggedfriend().get(i));
									a++;
									if (a != postlar.gettaggedfriend().size()) {
										System.out.print(" ,");
									}
								}
								System.out.println();
								

							}
							System.out.println("-----------------------");
						}
						if(postlar.getClass().equals(VideoPost.class)){
							System.out.println(postlar.getText());
							System.out.println(postlar.getLatitude() + ", "
									+ postlar.getLongitude());
							System.out.println(dateFormat.format(date));
							System.out.println("Video : "+postlar.getfilename());
							System.out.println("Video duration: "+postlar.getduration()+" minutes");
							if (postlar.gettaggedfriend().size() > 0) {
								System.out.print("Friends tagged in this post: ");
								int a = 0;
								for (int i = 0; postlar.gettaggedfriend().size() > i; i++) {
									System.out.print(postlar.gettaggedfriend().get(i));
									a++;
									if (a != postlar.gettaggedfriend().size()) {
										System.out.print(" ,");
									}
								}
								System.out.println();
								

							}
							System.out.println("-----------------------");
						}

					}
					

			}
		}
			
		
			
		}
		if (adamvar == 0 ) {
			System.out.println("No such user!");
		} 
			
	
		if (mesajvar == 0 && adamvar == 1) {
				System.out.println(username + " does not have any posts yet.");
		}

	}

}
