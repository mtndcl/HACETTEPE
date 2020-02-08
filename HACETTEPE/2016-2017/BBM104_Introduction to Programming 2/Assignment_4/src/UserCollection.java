
import java.util.ArrayList;


public class UserCollection {

	static ArrayList<Friends> blockfriends = new ArrayList<Friends>();
	static ArrayList<TextPost> posts = new ArrayList<TextPost>();
	static ArrayList<Friends> friends = new ArrayList<Friends>();
	static ArrayList<User> userlist = new ArrayList<User>();
	static ArrayList<User> userlogin = new ArrayList<User>();
	static String[] data = { new String() };
	static int a=0;
	public static boolean addUser(String takenamesurname, String takeusername,
			String takepassword, String takebirthdate, String takeschool,
			String takerelationship) {
		int a = 0;
		User user = new User(takenamesurname, takeusername, takepassword,
				takebirthdate, takeschool, takerelationship);
		userlist.add(user);

		data[a] = user.getUser_name();

		a++;

		/*
		 * ok okey=new ok(); okey.ok();
		 */

		return true;

	}

	public static boolean addfiend(String line) {
		String[] splitted = line.split("\t");
		
		
		int haveuser1=0;
		int haveuser2=0;
		for(User  isteyen: userlist){
			if(isteyen.getUser_name().equals(splitted[1])){
				haveuser1=1;
				//System.out.println(splitted[1]+"  bole biri var");
				}
			}
	
		for(User  istenen: userlist){
			if(istenen.getUser_name().equals(splitted[2])){
				haveuser2=1;
			//	System.out.println(splitted[2]+"  bole biri var");
				}
			}
		int blofro1=0;
		for(Friends  blo: blockfriends){
			
			
			if(blo.getusername().equals(splitted[1])){
				for(String  bb: blo.getfriend()){
					if(bb.equals(splitted[2])){
						blofro1=1;
					//	System.out.println(splitted[1]+ splitted[2]+"ı bloklanmis");
					}
				}
			}
		}
		int blofro2=0;
		for(Friends  blobo: blockfriends){
			if(blobo.getusername().equals(splitted[2])){
				for(String  bb: blobo.getfriend()){
					if(bb.equals(splitted[1])){
						blofro2=1;
						//System.out.println(splitted[2]+ splitted[1]+"ı bloklanmis");
					}
				}
			}
		}
		int havethidfro1=0;
		for(Friends  fro: friends){
			if(fro.getusername().equals(splitted[1])){
				for(String arka:  fro.getfriend()){
					if(arka.equals(splitted[2])){
						havethidfro1=1;
						//System.out.println(splitted[2]+ splitted[1]+" ile zateni arkadas");
					}
				}
			}
		}
		int havethidfro2=0;
		for(Friends  fro: friends){
			if(fro.getusername().equals(splitted[2])){
				for(String arka:  fro.getfriend()){
					if(arka.equals(splitted[1])){
						havethidfro2=1;
					//	System.out.println(splitted[1]+ splitted[2]+"  ile zateni arkadas");
					}
				}
			}
		}
		
		if(haveuser1==1 &&  haveuser2==1 && blofro1==0 && blofro2==0&& havethidfro1==0 ){
			int var=0;
			for(Friends ekleyen: friends){
				if(ekleyen.getusername().equals(splitted[1])){
					var=1;
					ekleyen.addtofriend(splitted[2]);
				//	System.out.println(splitted[1]+" acik olana"+ splitted[2]+" girdi");
				}
				
				
			}
			if(var==0){
				Friends friend = new Friends(splitted[1],splitted[2]);
				friends.add(friend);
			//	System.out.println(splitted[1]+" icin acildi "+ splitted[2]+" eklendi");
			}
		}
		
		if(haveuser1==1 &&  haveuser2==1 && blofro1==0 && blofro2==0&& havethidfro2==0 ){
			int var=0;
			for(Friends ekleyen: friends){
				if(ekleyen.getusername().equals(splitted[2])){
					var=1;
					ekleyen.addtofriend(splitted[1]);
					//System.out.println(splitted[2]+" acik olana"+ splitted[1]+" girdi");
					
				}
				
				
			}
			if(var==0){
				Friends friend = new Friends(splitted[2],splitted[1]);
				friends.add(friend);
				//System.out.println(splitted[2]+" icin acildi "+ splitted[1]+" eklendi");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		 * 
		 * for (User user : userlist) {

			if (user.getUser_name().equals(splitted[1])) {
				for (User arkadasolcak : userlist) {
					if (arkadasolcak.getUser_name().equals(splitted[2])) {
						int hicarkadasyok = 0;
						for (Friends fro : friends) {
							if (user.getUser_name().equals(fro.getusername())) {
								hicarkadasyok = 1;
								fro.addtofriend(splitted[2]);
								
							}
						}
						if (hicarkadasyok == 0) {
							Friends friend = new Friends(splitted[1],
									splitted[2]);
							friends.add(friend);
						}
					}
				}

			}

		}
	
		 */

		return true;

	}

	public static boolean blockfiend(String line) {
		String[] splitted = line.split("\t");

		
		int haveuser1=0;
		int haveuser2=0;
		for(User  isteyen: userlist){
			if(isteyen.getUser_name().equals(splitted[1])){
				haveuser1=1;
				//System.out.println(splitted[1]+"  bole biri var");
				}
			}
	
		for(User  istenen: userlist){
			if(istenen.getUser_name().equals(splitted[2])){
				haveuser2=1;
				//System.out.println(splitted[2]+"  bole biri var");
				}
			}
		int blofro1=0;
		for(Friends  blo: blockfriends){
			
			
			if(blo.getusername().equals(splitted[1])){
				for(String  bb: blo.getfriend()){
					if(bb.equals(splitted[2])){
						blofro1=1;
				//		System.out.println(splitted[1]+ "  "+splitted[2]+"  zateni bloklanmaiş");
					}
				}
			}
		}
		int blofro2=0;
		for(Friends  blobo: blockfriends){
			if(blobo.getusername().equals(splitted[2])){
				for(String  bb: blobo.getfriend()){
					if(bb.equals(splitted[1])){
						blofro2=1;
					//	System.out.println(splitted[2]+ "  "+splitted[1]+"  zateni bloklanmaiş");
					}
				}
			}
		}
		int havethidfro1=0;
		for(Friends  fro: friends){
			if(fro.getusername().equals(splitted[1])){
				for(String arka:  fro.getfriend()){
					if(arka.equals(splitted[2])){
						havethidfro1=1;
						fro.getfriend().remove(splitted[2]);
						//System.out.println(splitted[2]+"  artik "+ splitted[1]+" ile  arkadas  değil");
						break;
					}
				}
			}
		}
		int havethidfro2=0;
		for(Friends  fro: friends){
			if(fro.getusername().equals(splitted[2])){
				for(String arka:  fro.getfriend()){
					if(arka.equals(splitted[1])){
						havethidfro2=1;
						fro.getfriend().remove(splitted[1]);
						//System.out.println(splitted[1]+ "  artik "+splitted[2]+"    arkadas  değil");
						break;
					}
				}
			}
		}
		
		if(haveuser1==1 &&  haveuser2==1 && blofro1==0 && blofro2==0 ){
			int var=0;
			for(Friends ekleyen: blockfriends){
				if(ekleyen.getusername().equals(splitted[1])){
					var=1;
					ekleyen.addtofriend(splitted[2]);
					//System.out.println(splitted[1]+" acik olana"+ splitted[2]+" girdi");
				}
				
				
			}
			if(var==0){
				Friends friend = new Friends(splitted[1],splitted[2]);
				blockfriends.add(friend);
				//System.out.println(splitted[1]+" icin acildi "+ splitted[2]+" eklendi");
			}
		}
		
		/*
		 * 
		 * if(haveuser1==1 &&  haveuser2==1 && blofro1==0 && blofro2==0&& havethidfro2==0 ){
			int var=0;
			for(Friends ekleyen: blockfriends){
				if(ekleyen.getusername().equals(splitted[2])){
					var=1;
					ekleyen.addtofriend(splitted[1]);
					System.out.println(splitted[2]+" acik olana"+ splitted[1]+" girdi");
					
				}
				
				
			}
			if(var==0){
				Friends friend = new Friends(splitted[2],splitted[1]);
				blockfriends.add(friend);
				System.out.println(splitted[2]+" icin acildi "+ splitted[1]+" eklendi");
			}
		}
		 */
		
		
		

		return true;

	}

	public static boolean addtext(String line) {
		String[] splitted = line.split("\t");
		
		ArrayList<User> etiketlenenler = new ArrayList<User>();
		String[] taglenecekler = splitted[5].split(":");
		for (User kul : userlist) {
			if (kul.getUser_name().equals(splitted[1])) {
				for (User user : userlist) {
					for (int i = 0; i < taglenecekler.length; i++) {
						if (user.getUser_name().equals(taglenecekler[i])) {
							for (User kull : userlist) {
								if (kull.getUser_name().equals(taglenecekler[i])) {
							etiketlenenler.add(user);
								}
								}
						}
					}
				}
//				ArrayList<User> newetiketlenenler = new ArrayList<User>();
//				for(Friends  user: friends){
//					if(user.getusername().equals(splitted[1])){
//						for(String fro: user.getfriend()){
//							for(User  üye: etiketlenenler){
//								if(fro.equals(üye.getUser_name())){
//									newetiketlenenler.add(üye);
//								}
//							}
//						}
//					}
//				}
				
				TextPost post = new TextPost(a,splitted[1], splitted[2],
						Double.parseDouble(splitted[3]),
						Double.parseDouble(splitted[4]), etiketlenenler);

				posts.add(post);
				a++;
			}
		}
		return true;

	}

	public static boolean addimage(String line) {
		String[] splitted = line.split("\t");

		ArrayList<User> etiketlenenler = new ArrayList<User>();
		String[] taglenecekler = splitted[5].split(":");
		for (User kul : userlist) {
			if (kul.getUser_name().equals(splitted[1])) {
				for (User user : userlist) {
					for (int i = 0; i < taglenecekler.length; i++) {

						if (user.getUser_name().equals(taglenecekler[i])) {
							for (User kull : userlist) {
								if (kull.getUser_name().equals(taglenecekler[i])) {
							etiketlenenler.add(user);
								}
								}
						}
					}
				}
//				ArrayList<User> newetiketlenenler = new ArrayList<User>();
//				for(Friends  user: friends){
//					if(user.getusername().equals(splitted[1])){
//						for(String fro: user.getfriend()){
//							for(User  üye: etiketlenenler){
//								if(fro.equals(üye.getUser_name())){
//									newetiketlenenler.add(üye);
//								}
//							}
//						}
//					}
//				}
				TextPost post = new ImagePost(a,splitted[1], splitted[2],
						Double.parseDouble(splitted[3]),
						Double.parseDouble(splitted[4]), etiketlenenler,
						splitted[6], splitted[7]);

				posts.add(post);
				a++;
			}
		}
		return true;

	}

	public static boolean addvideo(String line) {
		String[] splitted = line.split("\t");

		ArrayList<User> etiketlenenler = new ArrayList<User>();
		String[] taglenecekler = splitted[5].split(":");
		
		if(10>=Integer.parseInt(splitted[7])){
		for (User kul : userlist) {
			if (kul.getUser_name().equals(splitted[1])) {
				for (User user : userlist) {
					for (int i = 0; i < taglenecekler.length; i++) {
						if (user.getUser_name().equals(taglenecekler[i])) {
							for (User kull : userlist) {
								if (kull.getUser_name().equals(taglenecekler[i])) {
							etiketlenenler.add(user);
								}
								}
						}
					}
				}
//				ArrayList<User> newetiketlenenler = new ArrayList<User>();
//				for(Friends  user: friends){
//					if(user.getusername().equals(splitted[1])){
//						for(String fro: user.getfriend()){
//							for(User  üye: etiketlenenler){
//								if(fro.equals(üye.getUser_name())){
//									newetiketlenenler.add(üye);
//								}
//							}
//						}
//					}
//				}
				TextPost post = new VideoPost(a,splitted[1], splitted[2],
						Double.parseDouble(splitted[3]),
						Double.parseDouble(splitted[4]), etiketlenenler,
						splitted[6], Double.parseDouble(splitted[7]));

				posts.add(post);
				a++;
			}
		}
		}
		return true;

	}

	



}
