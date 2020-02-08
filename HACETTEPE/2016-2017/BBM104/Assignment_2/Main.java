import java.io.File;
import java.io.FileNotFoundException;
//import java.sql.Date;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	/**
	 * this main function  
	 * @param read file and make order
	 * @param args .txt files 
	 */
	public static void main(String[] args) {
		
		ArrayList<Admin> adminsList = new ArrayList<Admin>();
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		ArrayList<Techcician> techsList = new ArrayList<Techcician>();
		
		ArrayList<Items> itemsList = new ArrayList<Items>();
		
		ArrayList<Campaigns> Campaigns_List = new ArrayList<Campaigns>();
		
		ArrayList<CARTS> CART_List = new ArrayList<CARTS>();
		
		ArrayList<Order> Order_List = new ArrayList<Order>();
		
		int no_of_custo = 1;
		int ID_of_Items = 1;
		int amount_LAPTOP = 0;
		int amount_DESKTOP = 0;
		int amount_TABLET = 0;
		int amount_TV = 0;
		int amount_SMARTPHONE = 0;
		int amount_BOOK = 0;
		int amount_CDDVD = 0;
		int amount_HAIRCARE = 0;
		int amount_SKINCARE = 0;
		int amount_PERFUME = 0;
		/*
		 * @param control to file
		 */
		try {

			Scanner scanner = new Scanner(new File(args[0]));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				String[] splitted = line.split("\t");
				/*
				 * @param add admin in users.txt file
				 */
				if (new String("ADMIN").equals(splitted[0])) {

					Person a = new Admin(splitted[1], splitted[2], splitted[3], Double.parseDouble(splitted[4]),
							splitted[5]);
					adminsList.add((Admin) a);
					/*
					 * @param add Customer in users.txt file
					 */
				} else if (new String("CUSTOMER").equals(splitted[0])) {
					Person g = new Customer(splitted[1], splitted[2], splitted[3], Double.parseDouble(splitted[4]),
							splitted[5], no_of_custo, Customer.Status.CLASSIC);
					customerList.add((Customer) g);
					no_of_custo += 1;
					/*
					 * @param add techicician in users.txt file
					 */
				} else if (new String("TECH").equals(splitted[0])) {
					Person h = new Techcician(splitted[1], splitted[2], splitted[3], Double.parseDouble(splitted[4]),
							Integer.parseInt(splitted[5]));
					techsList.add((Techcician) h);

				}

			}

			scanner.close();
			Scanner scanner2 = new Scanner(new File(args[1]));
			while (scanner2.hasNextLine()) {
				String line = scanner2.nextLine();
				String[] splitted = line.split("\t");
				/*
				 * @param add DESKTOP from items.txt file
				 * @param every make a desktop,amount_DESKTOP and  ID_of_Items adding  1.
				 */
				if (new String("DESKTOP").equals(splitted[0])) {

					Items a = new Desktop(splitted[0], ID_of_Items, Double.parseDouble(splitted[1]), splitted[2],
							splitted[3], Double.parseDouble(splitted[4]), Double.parseDouble(splitted[5]), splitted[6],
							splitted[7], Double.parseDouble(splitted[8]), Double.parseDouble(splitted[9]),
							splitted[10]);

					itemsList.add((Desktop) a);

					amount_DESKTOP += 1;
					ID_of_Items += 1;
					/*
					 * @param add LAPTOP from items.txt file
					 * @param every make a desktop,amount_LAPTOP and  ID_of_Items adding  1.
					 */
				} else if (new String("LAPTOP").equals(splitted[0])) {

					Items a = new Laptop(splitted[0], ID_of_Items, Double.parseDouble(splitted[1]), splitted[2],
							splitted[3], Double.parseDouble(splitted[4]), Double.parseDouble(splitted[5]), splitted[6],
							splitted[7], Double.parseDouble(splitted[8]), Double.parseDouble(splitted[9]),
							Integer.parseInt(splitted[10]));

					itemsList.add((Laptop) a);

					amount_LAPTOP += 1;
					ID_of_Items += 1;
					/*
					 * @param add LAPTOP from items.txt file
					 * @param every make a desktop,amount_TABLET and  ID_of_Items adding  1.
					 * @param the tablet item adding item_List
					 */
				} else if (new String("TABLET").equals(splitted[0])) {

					Items a = new Tablet(splitted[0], ID_of_Items, Double.parseDouble(splitted[1]), splitted[2],
							splitted[3], Double.parseDouble(splitted[4]), Double.parseDouble(splitted[5]), splitted[6],
							splitted[7], Double.parseDouble(splitted[8]), Double.parseDouble(splitted[9]),
							Double.parseDouble(splitted[10]));

					itemsList.add((Tablet) a);

					amount_TABLET += 1;

					ID_of_Items += 1;
					/*
					 * @param add TV from items.txt file
					 * @param every make a desktop,amount_TV and  ID_of_Items adding  1.
					 * @param the tv item adding item_List
					 */

				} else if (new String("TV").equals(splitted[0])) {

					Items a = new TV(splitted[0], ID_of_Items, Double.parseDouble(splitted[1]), splitted[2],
							splitted[3], Double.parseDouble(splitted[4]), Double.parseDouble(splitted[5]),
							Double.parseDouble(splitted[6]));

					itemsList.add((TV) a);

					amount_TV += 1;
					ID_of_Items += 1;
					/*
					 * @param add SMARTPHONE from items.txt file
					 * @param every make a SMARTPHONE,amount_SMARTPHONE and  ID_of_Items adding  1.
					 * @param the SMARTPHONE item adding item_List
					 */

				} else if (new String("SMARTPHONE").equals(splitted[0])) {

					Items a = new Smartphone(splitted[0], ID_of_Items, Double.parseDouble(splitted[1]), splitted[2],
							splitted[3], Double.parseDouble(splitted[4]), Double.parseDouble(splitted[5]), splitted[6]);

					itemsList.add((Smartphone) a);

					amount_SMARTPHONE += 1;
					ID_of_Items += 1;
				/*
				* @param add BOOK from items.txt file
			    * @param every make a BOOK,amount_BOOK and  ID_of_Items adding  1.
				* @param the BOOK item adding item_List
				*/

				} else if (new String("BOOK").equals(splitted[0])) {

					Items a = new Book(splitted[0], ID_of_Items, Double.parseDouble(splitted[1]), splitted[2],
							splitted[3], splitted[4], splitted[5], Integer.parseInt(splitted[6]));

					itemsList.add((Book) a);

					amount_BOOK += 1;

					ID_of_Items += 1;
					/*
					* @param add CDDVD from items.txt file
				    * @param every make a CDDVD,amount_CDDVD and  ID_of_Items adding  1.
					* @param the CDDVD item adding item_List
					*/

				} else if (new String("CDDVD").equals(splitted[0])) {

					Items a = new DVCV(splitted[0], ID_of_Items, Double.parseDouble(splitted[1]), splitted[2],
							splitted[3], splitted[4], splitted[5]);

					itemsList.add((DVCV) a);

					amount_CDDVD += 1;
					ID_of_Items += 1;
					/*
					* @param add HAIRCARE from items.txt file
				    * @param every make a HAIRCARE,amount_HAIRCARE and  ID_of_Items adding  1.
					* @param the HAIRCARE item adding item_List
					*/
				} else if (new String("HAIRCARE").equals(splitted[0])) {

					Items a = new Hair_care(splitted[0], ID_of_Items, Double.parseDouble(splitted[1]), splitted[2],
							splitted[3], Integer.parseInt(splitted[4]), splitted[5], Double.parseDouble(splitted[6]),
							Integer.parseInt(splitted[7]));

					itemsList.add((Hair_care) a);

					amount_HAIRCARE += 1;
					ID_of_Items += 1;

				}
				/*
				* @param add SKINCARE from items.txt file
			    * @param every make a SKINCARE,amount_SKINCARE and  ID_of_Items adding  1.
				* @param the SKINCARE item adding item_List
				*/

				else if (new String("SKINCARE").equals(splitted[0])) {

					Items a = new Skin_care(splitted[0], ID_of_Items, Double.parseDouble(splitted[1]), splitted[2],
							splitted[3], Integer.parseInt(splitted[4]), splitted[5], Double.parseDouble(splitted[6]),
							Integer.parseInt(splitted[7]));

					itemsList.add((Skin_care) a);

					amount_SKINCARE += 1;
					ID_of_Items += 1;
					/*
					* @param add PERFUME from items.txt file
				    * @param every make a PERFUME,amount_PERFUME and  ID_of_Items adding  1.
					* @param the PERFUME item adding item_List
					*/

				} else if (new String("PERFUME").equals(splitted[0])) {

					Items a = new Perfume(splitted[0], ID_of_Items, Double.parseDouble(splitted[1]), splitted[2],
							splitted[3], Integer.parseInt(splitted[4]), splitted[5], Double.parseDouble(splitted[6]),
							splitted[7]);

					itemsList.add((Perfume) a);

					amount_PERFUME += 1;
					ID_of_Items += 1;

				}

			}
			scanner2.close();

			Scanner scanner1 = new Scanner(new File(args[2]));
			while (scanner1.hasNextLine()) {
				String line = scanner1.nextLine();
				String[] splitted = line.split("\t");
				
				/*
				* @param add CUSTOMER from commands.txt file
			    * @param check admin if we have admin add in Customer_List new a customer
				*/
				if (new String("ADDCUSTOMER").equals(splitted[0])) {
					int exist = 0;
					

					System.out.print("\n"+"COMMAND TEXT: <" + splitted[0] + "	" + splitted[1] + "	" + splitted[2] + "	"
							+ splitted[3] + "	" + splitted[4] + "	" + splitted[5] + ">" + "\n\n");

					/*
					* @param checking admin after that f we have admin adding new admin .
					* 
					*/
					for (Admin sent : adminsList) {
						if (new String(sent.getname()).equals(splitted[1])) {

							Person adam = new Customer(splitted[2], splitted[3], splitted[4],
									Double.parseDouble(splitted[5]), splitted[6], no_of_custo, Customer.Status.CLASSIC);
							
							customerList.add((Customer) adam);
							no_of_custo += 1;
							exist = 1;
							System.out.print("\n");
							break;
						}

					}
					/*
					* @param if we haven't admin written that
					*/
					if (exist == 0) {
						System.out.print("No admin person named " + splitted[1] + " exists!");
						System.out.print("\n");
					}

				} else if (new String("SHOWCUSTOMER").equals(splitted[0])) {
					int has = 0;
					System.out.print("COMMAND TEXT: <" + splitted[0] + "	" + splitted[1] + "	" + splitted[2] + ">"+"\n");
					
					for (Admin sent : adminsList) {
						if (sent.getname().equals(splitted[1])) {
							has = 1;
							for (Customer mu : customerList) {
								if (mu.getcustomerID() == Integer.parseInt(splitted[2])) {
									System.out.print("\n");
									System.out.print("Customer name: " + mu.getname());
									System.out.print("  ID: " + mu.getcustomerID());
									System.out.print("  e-mail: " + mu.getemail());
									System.out.print("  Date of Birth: " + mu.getbirth_day());
									System.out.println("  Status: " + mu.getstatus());
									System.out.print("\n");
									

								}
							}
						}
					}
					
					if (has == 0) {
						System.out.println("No admin person named " + splitted[1] + " exists!");
						System.out.println();
					}
					
				
				} else if (new String("SHOWCUSTOMERS").equals(splitted[0])) {
					System.out.println("COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " >" + "\n");

					int has1 = 0;
					for (Admin sent : adminsList) {

						if (sent.getname().equals(splitted[1])) {
							has1 = 1;
							for (Customer mu : customerList) {
								System.out.print("Customer name: " + mu.getname());
								System.out.print("  ID: " + mu.getcustomerID());
								System.out.print("  e-mail: " + mu.getemail());
								System.out.print("  Date of Birth: " + mu.getbirth_day());
								System.out.println("  Status: " + mu.getstatus() );

							}
							System.out.println();

							break;
						}

					}
					/*
					* @param if we don't have admin wrote that
					*/
					if (has1 == 0) {

						System.out.println("No admin person named " + splitted[1] + " exists!");
					
					}

				}
				/*
				 * @param if we have id in list of admin list,wrote admin info
				 */

				else if (new String("SHOWADMININFO").equals(splitted[0])) { // SHOWADMINONFO
					int has3 = 0;
					System.out.println("COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " >" );

					for (Admin sent : adminsList) {
						if (sent.getname().equals(splitted[1])) {
							has3 = 1;
							
							/*
							 * 
							 * @link {adminDisplay}
							 */
							sent.adminDisplay();
						}
					}
					if (has3 == 0) {
						System.out.println("\n" + "No admin person named " + splitted[1] + " exists!");
						System.out.println();
					}
					
				} 
				/*
				*
				*@param create campaigns
				*@param add this campaigns to array list which name is @link Campaigns_List
				*
				*/
				else if (new String("CREATECAMPAIGN").equals(splitted[0])) {
					int exist = 0;
					int has = 0;
					System.out.println("COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " " + splitted[2] + " "
							+ splitted[3] + " " + splitted[4] + " " + splitted[5] + " >" + "\n");

					for (Admin sent : adminsList) {
						if (new String(sent.getname()).equals(splitted[1])) {
							exist = 1;
							if (Double.parseDouble(splitted[5]) < 50) {
								has = 1;
								Campaigns aa = new Campaigns(splitted[2], splitted[3], splitted[4],
										Double.parseDouble(splitted[5]));
								Campaigns_List.add(aa);
							}

						}

					}
					if (exist == 0) {
						System.out.println("No admin person named " + splitted[1] + " exists!");
						System.out.println();
					}
					if (has == 0 & exist == 1) {
						System.out
								.println("Campaign was not created. Discount rate exceeds maximum rate of 50%." + "\n");

					}

				} else if (new String("ADDADMIN").equals(splitted[0])) {
					int exist = 0;
					System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " " + splitted[2]
							+ " " + splitted[3] + " " + splitted[4] + " " + splitted[5] + splitted[6] + " >" + "\n");

					for (Admin sent : adminsList) {
						if (new String(sent.getname()).equals(splitted[1])) {

							Person a1 = new Admin(splitted[2], splitted[3], splitted[4],
									Double.parseDouble(splitted[5]), splitted[6]);
							adminsList.add((Admin) a1);

							exist = 1;
							break;
						}

					}
					if (exist == 0) {

						System.out.println("No admin person named " + splitted[1] + " exists!");
						System.out.println();
					}

				} else if (new String("ADDTECH").equals(splitted[0])) {
					int exist = 0;
					for (Admin sent : adminsList) {
						if (new String(sent.getname()).equals(splitted[1])) {
							Person a = new Techcician(splitted[2], splitted[3], splitted[4],
									Double.parseDouble(splitted[5]), Integer.parseInt(splitted[6]));

							techsList.add((Techcician) a);
							System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " "
									+ splitted[2] + " " + splitted[3] + " " + splitted[4] + " " + splitted[5]
									+ splitted[6] + " >");

							exist = 1;
						}

					}
					if (exist == 0) {
						System.out.println(
								"\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " " + splitted[2] + " "
										+ splitted[3] + " " + splitted[4] + " " + splitted[5] + splitted[6] + " >");
						System.out.println("No admin person named " + splitted[1] + " exists!");
						System.out.println();
					}
				} else if (new String("LISTITEM").equals(splitted[0])) {
					int exist = 0;
					System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " >");

					for (Admin sent : adminsList) {
						if (new String(sent.getname()).equals(splitted[1])) {
							for (Items ito : itemsList) {

								if (ito.gettyp().equals("DESKTOP")) {

									((Desktop) ito).ShowInfo_Desktop();
								} else if (ito.gettyp().equals("LAPTOP")) {
									((Laptop) ito).ShowInfo_Laptop();
									amount_LAPTOP += 1;
								} else if (ito.gettyp().equals("TABLET")) {
									((Tablet) ito).ShowInfo_Tablet();
								} else if (ito.gettyp().equals("TV")) {
									((TV) ito).ShowInfo_TV();
								} else if (ito.gettyp().equals("SMARTPHONE")) {
									((Smartphone) ito).ShowInfo_Smartphone();
								} else if (ito.gettyp().equals("BOOK")) {
									((Book) ito).ShowInfo_Book();
								} else if (ito.gettyp().equals("CDDVD")) {
									((DVCV) ito).ShowInfo_DVCV();
								} else if (ito.gettyp().equals("HAIRCARE")) {
									((Hair_care) ito).ShowInfo_Hair_care();
								} else if (ito.gettyp().equals("SKINCARE")) {
									((Skin_care) ito).ShowInfo_Skin_care();
								} else if (ito.gettyp().equals("PERFUME")) {
									((Perfume) ito).ShowInfo_Perfume();
								}
							}
							exist = 1;
						}
					}
					for (Techcician sent : techsList) {
						if (new String(sent.getname()).equals(splitted[1])) {
							if (new String(sent.getname()).equals(splitted[1])) {
								for (Items ito : itemsList) {

									if (ito.gettyp().equals("DESKTOP")) {

										((Desktop) ito).ShowInfo_Desktop();
									} else if (ito.gettyp().equals("LAPTOP")) {
										((Laptop) ito).ShowInfo_Laptop();
									} else if (ito.gettyp().equals("TABLET")) {
										((Tablet) ito).ShowInfo_Tablet();
									} else if (ito.gettyp().equals("TV")) {
										((TV) ito).ShowInfo_TV();
									} else if (ito.gettyp().equals("SMARTPHONE")) {
										((Smartphone) ito).ShowInfo_Smartphone();
									} else if (ito.gettyp().equals("BOOK")) {
										((Book) ito).ShowInfo_Book();
									} else if (ito.gettyp().equals("CDDVD")) {
										((DVCV) ito).ShowInfo_DVCV();
									} else if (ito.gettyp().equals("HAIRCARE")) {
										((Hair_care) ito).ShowInfo_Hair_care();
									} else if (ito.gettyp().equals("SKINCARE")) {
										((Skin_care) ito).ShowInfo_Skin_care();
									} else if (ito.gettyp().equals("PERFUME")) {
										((Perfume) ito).ShowInfo_Perfume();
									}
								}
							}
							exist = 1;
						}
					}
					if (exist == 0) {
						System.out.println("No admin or technician person named " + splitted[1] + " exists!");
						System.out.println();
					}
				}

				else if (new String("SHOWITEMSLOWONSTOCK").equals(splitted[0])) {
					int exist = 0;

					for (Admin sent : adminsList) {
						if (new String(sent.getname()).equals(splitted[1])) {

							if (splitted.length == 2) {

								System.out.println("BOOK : " + amount_BOOK);
								System.out.println("CDDVD : " + amount_CDDVD);

								System.out.println("DESKTOP : " + amount_DESKTOP);
								System.out.println("LAPTOP : " + amount_LAPTOP);
								System.out.println("TABLET : " + amount_TABLET);
								System.out.println("TV : " + amount_TV);
								System.out.println("SMARTPHONE : " + amount_SMARTPHONE);
								System.out.println("HAIRCARE : " + amount_HAIRCARE);
								System.out.println("PERFUME : " + amount_PERFUME);
								System.out.println("SKINCARE : " + amount_SKINCARE);

							} else if (splitted.length == 3) {
								System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1]
										+ splitted[2] + " >");
								if (amount_BOOK < Integer.parseInt(splitted[2])) {
									System.out.println("BOOK : " + amount_BOOK);
								}

								if (amount_CDDVD < Integer.parseInt(splitted[2])) {
									System.out.println("CDDVD : " + amount_CDDVD);
								}
								if (amount_DESKTOP < Integer.parseInt(splitted[2])) {
									System.out.println("DESKTOP :" + amount_DESKTOP);
								}
								if (amount_LAPTOP < Integer.parseInt(splitted[2])) {
									System.out.println("LAPTOP :" + amount_LAPTOP);
								}
								if (amount_TABLET < Integer.parseInt(splitted[2])) {
									System.out.println("TABLET : " + amount_TABLET);
								}
								if (amount_TV < Integer.parseInt(splitted[2])) {
									System.out.println("TV : " + amount_TV);
								}
								if (amount_SMARTPHONE < Integer.parseInt(splitted[2])) {
									System.out.println("SMARTPHONE : " + amount_SMARTPHONE);
								}
								
								if (amount_HAIRCARE < Integer.parseInt(splitted[2])) {
									System.out.println("HAIRCARE : " + amount_HAIRCARE);
								}
								if (amount_PERFUME < Integer.parseInt(splitted[2])) {
									System.out.println("PERFUME : " + amount_PERFUME);
								}
								if (amount_SKINCARE < Integer.parseInt(splitted[2])) {
									System.out.println("SKINCARE : " + amount_SKINCARE);
								}
								
							}
							exist = 1;
						}

					}
					for (Techcician sent1 : techsList) {
						if (new String(sent1.getname()).equals(splitted[1])) {

							if (splitted.length == 2) {
								System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " >");

								System.out.println(
										"\n" + "COMMAND TEXT:emrah < " + splitted[0] + " " + splitted[1] + " >");
								
								System.out.println("BOOK : " + amount_BOOK);
								System.out.println("CDDVD : " + amount_CDDVD);

								System.out.println("DESKTOP : " + amount_DESKTOP);
								System.out.println("LAPTOP : " + amount_LAPTOP);
								System.out.println("TABLET : " + amount_TABLET);
								System.out.println("TV : " + amount_TV);
								System.out.println("SMARTPHONE : " + amount_SMARTPHONE);
								System.out.println("HAIRCARE : " + amount_HAIRCARE);
								System.out.println("PERFUME : " + amount_PERFUME);
								System.out.println("SKINCARE : " + amount_SKINCARE);

							} else if (splitted.length == 3) {
								System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " "
										+ splitted[2] + " >");
								if (amount_BOOK < Integer.parseInt(splitted[2])) {
									System.out.println("BOOK : " + amount_BOOK);
								}

								if (amount_CDDVD < Integer.parseInt(splitted[2])) {
									System.out.println("CDDVD : " + amount_CDDVD);
								}
								if (amount_DESKTOP < Integer.parseInt(splitted[2])) {
									System.out.println("DESKTOP :" + amount_DESKTOP);
								}
								if (amount_LAPTOP < Integer.parseInt(splitted[2])) {
									System.out.println("LAPTOP :" + amount_LAPTOP);
								}
								if (amount_TABLET < Integer.parseInt(splitted[2])) {
									System.out.println("TABLET : " + amount_TABLET);
								}
								if (amount_TV < Integer.parseInt(splitted[2])) {
									System.out.println("TV : " + amount_TV);
								}
								if (amount_SMARTPHONE < Integer.parseInt(splitted[2])) {
									System.out.println("SMARTPHONE : " + amount_SMARTPHONE);
								}
								
								if (amount_HAIRCARE < Integer.parseInt(splitted[2])) {
									System.out.println("HAIRCARE : " + amount_HAIRCARE);
								}
								if (amount_PERFUME < Integer.parseInt(splitted[2])) {
									System.out.println("PERFUME : " + amount_PERFUME);
								}
								if (amount_SKINCARE < Integer.parseInt(splitted[2])) {
									System.out.println("SKINCARE : " + amount_SKINCARE);
								}
							}
							exist = 1;
						}

					}
					if (exist == 0) {
						System.out.println("No admin or technician person named " + splitted[1] + " exists!");
						System.out.println();
					}
				} else if (new String("SHOWVIP").equals(splitted[0])) {
					System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " >" + "\n");

					int exist = 0;
					for (Admin sent : adminsList) {
						if (new String(sent.getname()).equals(splitted[1])) {
							for (Customer adam : customerList) {
								if (adam.status.equals("GOLDEN")) {

									adam.Display();
								}
							}
							exist = 1;
						}
					}

					for (Techcician sent : techsList) {
						if (new String(sent.getname()).equals(splitted[1])) {
							for (Customer adam : customerList) {
								if (adam.status.equals("GOLDEN")) {
									adam.Display();
								}
							}
							exist = 1;
						}
					}
					if (exist == 0) {
						System.out.println("No admin or technician person named  " + splitted[1] + " exists!");
						System.out.println();
					}
				} else if (new String("DISPITEMSOF").equals(splitted[0])) {
					int exist = 0;
					System.out.println(
							"\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " " + splitted[2] + " >");

					for (Techcician sent : techsList) {
						if (new String(sent.getname()).equals(splitted[1])) {
							String[] typ = splitted[2].split(":");
							for (String tip : typ) {
								if (tip.equals("DESKTOP")) {
									for (Items ma : itemsList) {
										if (ma.gettyp().equals("DESKTOP")) {
											((Desktop) ma).ShowInfo_Desktop();
										}
									}
								} else if (tip.equals("LAPTOP")) {
									for (Items ma : itemsList) {
										if (ma.gettyp().equals("LAPTOP")) {

											((Laptop) ma).ShowInfo_Laptop();

										}
									}
								} else if (tip.equals("TABLET")) {
									for (Items ma : itemsList) {
										if (ma.gettyp().equals("TABLET")) {

											((Tablet) ma).ShowInfo_Tablet();

										}
									}
								} else if (tip.equals("TV")) {
									for (Items ma : itemsList) {
										if (ma.gettyp().equals("TV")) {

											((TV) ma).ShowInfo_TV();

										}
									}
								} else if (tip.equals("SMARTPHONE")) {
									for (Items ma : itemsList) {
										if (ma.gettyp().equals("SMARTPHONE")) {

											((Smartphone) ma).ShowInfo_Smartphone();

										}
									}
								} else if (tip.equals("BOOK")) {
									for (Items ma : itemsList) {
										if (ma.gettyp().equals("BOOK")) {

											((Book) ma).ShowInfo_Book();

										}
									}
								} else if (tip.equals("CDDVD")) {
									for (Items ma : itemsList) {
										if (ma.gettyp().equals("CDDVD")) {

											((DVCV) ma).ShowInfo_DVCV();

										}
									}
								} else if (tip.equals("HAIRCARE")) {
									for (Items ma : itemsList) {
										if (ma.gettyp().equals("HAIRCARE")) {

											((Hair_care) ma).ShowInfo_Hair_care();

										}
									}
								} else if (tip.equals("SKINCARE")) {
									for (Items ma : itemsList) {
										if (ma.gettyp().equals("SKINCARE")) {

											((Skin_care) ma).ShowInfo_Skin_care();
										}
									}
								} else if (tip.equals("PERFUME")) {
									for (Items ma : itemsList) {
										if (ma.gettyp().equals("PERFUME")) {

											((Perfume) ma).ShowInfo_Perfume();
										}
									}
								}
							}
							exist = 1;
						}
					}
					if (exist == 0) {
						System.out.println("No technician person named " + splitted[1] + " exists!");
						System.out.println();
					}
				}

				else if (new String("ADDITEM").equals(splitted[0])) {
					System.out.println(
							"\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " " + splitted[2] + " >");

					int exist = 0;
					for (Techcician sent : techsList) {
						exist = 1;
						if (new String(sent.getname()).equals(splitted[1])) {
							String[] info_item = splitted[2].split(":");
							if (info_item[0].equals("DESKTOP")) {
								Items a = new Desktop(info_item[0], ID_of_Items, Double.parseDouble(info_item[1]),
										info_item[2], info_item[3], Double.parseDouble(info_item[4]),
										Double.parseDouble(info_item[5]), info_item[6], info_item[7],
										Double.parseDouble(info_item[8]), Double.parseDouble(info_item[9]),
										info_item[10]);

								itemsList.add((Desktop) a);

								amount_DESKTOP += 1;
								ID_of_Items += 1;
							}

							else if (info_item[0].equals("LAPTOP")) {

								Items a = new Laptop(info_item[0], ID_of_Items, Double.parseDouble(info_item[1]),
										info_item[2], info_item[3], Double.parseDouble(info_item[4]),
										Double.parseDouble(info_item[5]), info_item[6], info_item[7],
										Double.parseDouble(info_item[8]), Double.parseDouble(info_item[9]),
										Integer.parseInt(info_item[10]));

								itemsList.add((Laptop) a);

								amount_LAPTOP += 1;
								ID_of_Items += 1;

							} else if (info_item[0].equals("TABLET")) {

								Items a = new Tablet(info_item[0], ID_of_Items, Double.parseDouble(info_item[1]),
										info_item[2], info_item[3], Double.parseDouble(info_item[4]),
										Double.parseDouble(info_item[5]), info_item[6], info_item[7],
										Double.parseDouble(info_item[8]), Double.parseDouble(info_item[9]),
										Double.parseDouble(info_item[10]));

								itemsList.add((Tablet) a);

								amount_TABLET += 1;

								ID_of_Items += 1;

							} else if (info_item[0].equals("TV")) {

								Items a = new TV(info_item[0], ID_of_Items, Double.parseDouble(info_item[1]),
										info_item[2], info_item[3], Double.parseDouble(info_item[4]),
										Double.parseDouble(info_item[5]), Double.parseDouble(info_item[6]));

								itemsList.add((TV) a);

								amount_TV += 1;
								ID_of_Items += 1;

							} else if (info_item[0].equals("SMARTPHONE")) {

								Items a = new Smartphone(info_item[0], ID_of_Items, Double.parseDouble(info_item[1]),
										info_item[2], info_item[3], Double.parseDouble(info_item[4]),
										Double.parseDouble(info_item[5]), info_item[6]);

								itemsList.add((Smartphone) a);

								amount_SMARTPHONE += 1;
								ID_of_Items += 1;

							} else if (info_item[0].equals("BOOK")) {

								Items a = new Book(info_item[0], ID_of_Items, Double.parseDouble(info_item[1]),
										info_item[2], info_item[3], info_item[4], info_item[5],
										Integer.parseInt(info_item[6]));

								itemsList.add((Book) a);

								amount_BOOK += 1;

								ID_of_Items += 1;

							} else if (info_item[0].equals("CDDVD")) {

								Items a = new DVCV(info_item[0], ID_of_Items, Double.parseDouble(info_item[1]),
										info_item[2], info_item[3], info_item[4], info_item[5]);

								itemsList.add((DVCV) a);

								amount_CDDVD += 1;
								ID_of_Items += 1;

							} else if (info_item[0].equals("HAIRCARE")) {

								Items a = new Hair_care(info_item[0], ID_of_Items, Double.parseDouble(info_item[1]),
										info_item[2], info_item[3], Integer.parseInt(info_item[4]), info_item[5],
										Double.parseDouble(info_item[6]), Integer.parseInt(info_item[7]));

								itemsList.add((Hair_care) a);

								amount_HAIRCARE += 1;
								ID_of_Items += 1;

							}

							else if (info_item[0].equals("SKINCARE")) {

								Items a = new Skin_care(info_item[0], ID_of_Items, Double.parseDouble(info_item[1]),
										info_item[2], info_item[3], Integer.parseInt(info_item[4]), info_item[5],
										Double.parseDouble(info_item[6]), Integer.parseInt(info_item[7]));

								itemsList.add((Skin_care) a);

								amount_SKINCARE += 1;
								ID_of_Items += 1;

							} else if (info_item[0].equals("PERFUME")) {

								Items a = new Perfume(info_item[0], ID_of_Items, Double.parseDouble(info_item[1]),
										info_item[2], info_item[3], Integer.parseInt(info_item[4]), info_item[5],
										Double.parseDouble(info_item[6]), info_item[7]);

								itemsList.add((Perfume) a);

								amount_PERFUME += 1;
								ID_of_Items += 1;

							} else {
								System.out.println("No item type " + info_item[0] + " found");

							}

						}
						if (exist == 0) {
							System.out.println("No admin person technician " + splitted[1] + " exists!");
							System.out.println();
						}
					}

				}

				else if (new String("SHOWORDERS").equals(splitted[0])) {
					int exist = 0;
					System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " >"+"\n");

					for (Techcician sent : techsList) {
						if (new String(sent.getname()).equals(splitted[1])) {
							if (sent.getsenior() == 1) {
								System.out.println("Order History: ");
								for (Order orderl: Order_List) {
									int number=orderl.getpurchased_items().size();
									
									System.out.print("Order date: " +orderl.getorderDate());
									System.out.print(" Customer ID :"+orderl.getCustomerID());
									System.out.print(" Total cost :"+orderl.gettotalCost());
									System.out.print(" Number of purchased items :"+number); 
									System.out.println();
								}
								System.out.println();
							} else {
								System.out.println();
								System.out.print(splitted[1] + " is not authorized to display orders!");
							}
							exist = 1;
						}

					}
					if (exist == 0) {
						System.out.println("No technician person named " + splitted[1] + " exists!");
						System.out.println();
					}
				} else if (new String("CHPASS").equals(splitted[0])) {
					int exist = 0;
					System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " " + splitted[2]
							+ " " + splitted[3] + " >" + "\n");

					for (Customer sent : customerList) {
						if (sent.getcustomerID() == Integer.parseInt(splitted[1])) {
							if (sent.getpassword().equals(splitted[2])) {

								sent.setpassword(splitted[3]);

								System.out.println("The password has been successfully changed.");
							} else {

								System.out.println(
										"The given password does not match the current password. Please try again.");
							}

							exist = 1;

						}

					}
					if (exist == 0) {

						System.out.println("No customer with ID number " + splitted[1] + " exists!");
						System.out.println();
					}

				} else if (new String("DEPOSITMONEY").equals(splitted[0])) {
					int exist = 0;
					System.out.println(
							"\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " " + splitted[2] + " >");

					for (Customer sent : customerList) {
						if (sent.getcustomerID() == Integer.parseInt(splitted[1])) {

							((Customer) sent).addBalance(Double.parseDouble((splitted[2])));
							exist = 1;
						}

					}
					if (exist == 0) {
						System.out.println("\n" + "No customer with ID number " + splitted[1] + " exists!");
						System.out.println();
					}
				} else if (new String("SHOWCAMPAIGNS").equals(splitted[0])) {
					int exist = 0;
					int has = 0;
					System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " >"+"\n");

					for (Customer sent : customerList) {
						if (sent.getcustomerID() == Integer.parseInt(splitted[1])) {
							exist = 1;
							System.out.println("Active campaigns :");
							for (Campaigns ca : Campaigns_List) {
								has = 1;
								
								System.out.println( (int)ca.getdiscountRate() + "% sale of " + ca.getitemType() + " until "
										+ ca.getendDate());
							}

						}

					}
					if (has == 0 & exist == 1) {
						System.out.println("No campaign has been created so far!");
						System.out.println();
					}
					if (exist == 0) {
						System.out.println("No customer with ID number " + splitted[1] + " exists!");
						System.out.println();
					}
				} else if (new String("ADDTOCART").equals(splitted[0])) {
					int exist = 0;
					int in=0;
					System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " " + splitted[2]
							+ ">" + "\n");

					for (Customer sent : customerList) {
						if (sent.getcustomerID() == Integer.parseInt(splitted[1])) {
							for (Items ito : itemsList) {
								if(ito.gettyp().equals("DESKTOP") & amount_DESKTOP>0){
									in=1;
									if (ito.getID() == Integer.parseInt(splitted[2])) {
										boolean cardExist = false;
										for (CARTS caar : CART_List) {
											if (caar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
												caar.addtocart(Integer.parseInt(splitted[2]));
												cardExist = true;
										
												
											}
										}
										if (cardExist == false) {
											CARTS a = new CARTS(Integer.parseInt(splitted[1]),
													Integer.parseInt(splitted[2]));
											CART_List.add(a);
										}
										System.out.println(
												"The item " + ito.gettyp() + " has been successfully added to your cart.");
									}
									
								}
								else if(ito.gettyp().equals("LAPTOP") & amount_LAPTOP>0){
									in=1;
									if (ito.getID() == Integer.parseInt(splitted[2])) {
										boolean cardExist = false;
										for (CARTS caar : CART_List) {
											if (caar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
												caar.addtocart(Integer.parseInt(splitted[2]));
												cardExist = true;
										
												
											}
										}
										if (cardExist == false) {
											CARTS a = new CARTS(Integer.parseInt(splitted[1]),
													Integer.parseInt(splitted[2]));
											CART_List.add(a);
										}
										System.out.println(
												"The item " + ito.gettyp() + " has been successfully added to your cart.");
									}
									
								}
								else if(ito.gettyp().equals("TABLET") & amount_TABLET>0){
									if (ito.getID() == Integer.parseInt(splitted[2])) {
										in=1;
										boolean cardExist = false;
										for (CARTS caar : CART_List) {
											if (caar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
												caar.addtocart(Integer.parseInt(splitted[2]));
												cardExist = true;
										
												
											}
										}
										if (cardExist == false) {
											CARTS a = new CARTS(Integer.parseInt(splitted[1]),
													Integer.parseInt(splitted[2]));
											CART_List.add(a);
										}
										System.out.println(
												"The item " + ito.gettyp() + " has been successfully added to your cart.");
									}
									
								}
								else if(ito.gettyp().equals("TV") & amount_TV>0){
									in=1;
									if (ito.getID() == Integer.parseInt(splitted[2])) {
										boolean cardExist = false;
										for (CARTS caar : CART_List) {
											if (caar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
												caar.addtocart(Integer.parseInt(splitted[2]));
												cardExist = true;
										
												
											}
										}
										if (cardExist == false) {
											CARTS a = new CARTS(Integer.parseInt(splitted[1]),
													Integer.parseInt(splitted[2]));
											CART_List.add(a);
										}
										System.out.println(
												"The item " + ito.gettyp() + " has been successfully added to your cart.");
									}
									
								}
								else if(ito.gettyp().equals("SMARTPHONE") & amount_SMARTPHONE>0){
									in=1;
									if (ito.getID() == Integer.parseInt(splitted[2])) {
										boolean cardExist = false;
										for (CARTS caar : CART_List) {
											if (caar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
												caar.addtocart(Integer.parseInt(splitted[2]));
												cardExist = true;
										
												
											}
										}
										if (cardExist == false) {
											CARTS a = new CARTS(Integer.parseInt(splitted[1]),
													Integer.parseInt(splitted[2]));
											CART_List.add(a);
										}
										System.out.println(
												"The item " + ito.gettyp() + " has been successfully added to your cart.");
									}
									
								}
								else if(ito.gettyp().equals("BOOK") & amount_BOOK>0){
									in=1;
									if (ito.getID() == Integer.parseInt(splitted[2])) {
										boolean cardExist = false;
										for (CARTS caar : CART_List) {
											if (caar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
												caar.addtocart(Integer.parseInt(splitted[2]));
												cardExist = true;
										
												
											}
										}
										if (cardExist == false) {
											CARTS a = new CARTS(Integer.parseInt(splitted[1]),
													Integer.parseInt(splitted[2]));
											CART_List.add(a);
										}
										System.out.println(
												"The item " + ito.gettyp() + " has been successfully added to your cart.");
									}
									
								}
								else if(ito.gettyp().equals("CDDVD") & amount_CDDVD>0){
									in=1;
									if (ito.getID() == Integer.parseInt(splitted[2])) {
										boolean cardExist = false;
										for (CARTS caar : CART_List) {
											if (caar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
												caar.addtocart(Integer.parseInt(splitted[2]));
												cardExist = true;
										
												
											}
										}
										if (cardExist == false) {
											CARTS a = new CARTS(Integer.parseInt(splitted[1]),
													Integer.parseInt(splitted[2]));
											CART_List.add(a);
										}
										System.out.println(
												"The item " + ito.gettyp() + " has been successfully added to your cart.");
									}
									
								}
								else if(ito.gettyp().equals("HAIRCARE") & amount_HAIRCARE>0){
									in=1;
									if (ito.getID() == Integer.parseInt(splitted[2])) {
										boolean cardExist = false;
										for (CARTS caar : CART_List) {
											if (caar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
												caar.addtocart(Integer.parseInt(splitted[2]));
												cardExist = true;
										
												
											}
										}
										if (cardExist == false) {
											CARTS a = new CARTS(Integer.parseInt(splitted[1]),
													Integer.parseInt(splitted[2]));
											CART_List.add(a);
										}
										System.out.println(
												"The item " + ito.gettyp() + " has been successfully added to your cart.");
									}
									
								}
								else if(ito.gettyp().equals("SKINCARE") & amount_SKINCARE>0){
									in=1;
									if (ito.getID() == Integer.parseInt(splitted[2])) {
										boolean cardExist = false;
										for (CARTS caar : CART_List) {
											if (caar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
												caar.addtocart(Integer.parseInt(splitted[2]));
												cardExist = true;
										
												
											}
										}
										if (cardExist == false) {
											CARTS a = new CARTS(Integer.parseInt(splitted[1]),
													Integer.parseInt(splitted[2]));
											CART_List.add(a);
										}
										System.out.println(
												"The item " + ito.gettyp() + " has been successfully added to your cart.");
									}
									
								}
								else if(ito.gettyp().equals("PERFUME") & amount_PERFUME>0){
									in=1;
									if (ito.getID() == Integer.parseInt(splitted[2])) {
										boolean cardExist = false;
										for (CARTS caar : CART_List) {
											if (caar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
												caar.addtocart(Integer.parseInt(splitted[2]));
												cardExist = true;
										
												
											}
										}
										if (cardExist == false) {
											CARTS a = new CARTS(Integer.parseInt(splitted[1]),
													Integer.parseInt(splitted[2]));
											CART_List.add(a);
										}
										System.out.println(
												"The item " + ito.gettyp() + " has been successfully added to your cart.");
									}
									
								}
								if(in==0){
									System.out.println("We are sorry. The item is temporarily unavailable.");
								}
							
							}
							if (ID_of_Items < Integer.parseInt(splitted[2])) {
								System.out.println("Invalid item ID");
								System.out.println();
							}
							exist = 1;

						}
					}
					if (exist == 0) {
						System.out.println("No customer with ID number " + splitted[1] + " exists!");
					
					}
				} else if (new String("EMPTYCART").equals(splitted[0])) {
					int exist = 0;
					System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " >" + "\n");

					for (Customer sent : customerList) {
						if (sent.getcustomerID() == Integer.parseInt(splitted[1])) {
							for (CARTS castID : CART_List) {
								if (castID.getCustomerIDC() == Integer.parseInt(splitted[1])) {

									CART_List.remove(castID);
									System.out.println("The cart has been emptied.");
									break;

								}

							}
							exist = 1;
						}

					}
					if (exist == 0) {
						System.out.println("No customer with ID number " + splitted[1] + " exists!");
						System.out.println();
					}
				}

				else if (new String("ORDER").equals(splitted[0])) {
					int paso = 0;
					int custo = 0;
					int urun_var = 0;

					System.out.println("\n" + "COMMAND TEXT: < " + splitted[0] + " " + splitted[1] + " " + splitted[2]
							+ " >" + "\n");
					CARTS cart = null;
					for (Customer sent : customerList) {

						if (sent.getcustomerID() == Integer.parseInt(splitted[1])) {

							custo = 1;
							if (sent.getpassword().equals(splitted[2])) {
								paso = 1;

								double sum = 0;
								for (CARTS cartlar : CART_List) {
									if (cartlar.getCustomerIDC() == Integer.parseInt(splitted[1])) {
										urun_var = 1;
										ArrayList<Integer> alinacaklar = cartlar.getItemIDC();

										for (Integer alinacak : alinacaklar) {
											for (Items urunlist : itemsList) {
												if (urunlist.getID() == alinacak) {
													double bu_kadar_indirim = 0;
													sum = sum + urunlist.getprice();

													for (Campaigns ca : Campaigns_List) {
														if (ca.getitemType().equals(urunlist.gettyp())
																& urunlist.getID() == alinacak) {
															bu_kadar_indirim += urunlist.getprice()
																	* (ca.getdiscountRate() / (100));
														}
													}
													sum = sum - bu_kadar_indirim;

												}
											}
										}
										if (sent.getstatus() == Customer.Status.CLASSIC) {
											if (sent.getbalance() < sum) {
												System.out.println("Order could not be placed. Insufficient funds.");
												break;

											}

											else if (sent.getbalance() > sum) {
				
												sent.setpaid(sum + sent.getpaid());
												sent.setBalance(sent.getbalance() - sum);
													
												for (Integer alinacakler : alinacaklar) {
													for (Items urunlist : itemsList) {
														if (urunlist.getID() == alinacakler) {	
															if(urunlist.gettyp().equals("DESKTOP")){
																amount_DESKTOP--;
															}
															if(urunlist.gettyp().equals("LAPTOP")){
																amount_LAPTOP--;
															}
															if(urunlist.gettyp().equals("TABLET")){
																amount_TABLET--;
															}
															if(urunlist.gettyp().equals("TV")){
																amount_TV--;
															}
															if(urunlist.gettyp().equals("SMARTPHONE")){
																amount_SMARTPHONE--;
															}
															if(urunlist.gettyp().equals("BOOK")){
																amount_BOOK--;
															}
															if(urunlist.gettyp().equals("CDDVD")){
																amount_CDDVD--;
															}
															if(urunlist.gettyp().equals("HAIRCARE")){
																amount_HAIRCARE--;
															}
															if(urunlist.gettyp().equals("SKINCARE")){
																amount_SKINCARE--;
															}
															if(urunlist.gettyp().equals("PERFUME")){
																amount_PERFUME--;
															}
															
														}
													  }
													}											
												
												
												System.out.println(
														"Done! Your order will be delivered as soon as possible. Thank you!");
												CART_List.remove(cart);
												
												
											
												if (sent.getpaid() > 5000) {
													sent.setstatus(Customer.Status.GOLDEN);

													System.out.print("Congratulations! You have been upgraded ");
													System.out.print("to a GOLDEN MEMBER! You have earned a");
													System.out.println("discount of 15% on all purchases.");

												
												}

												else if (sent.getpaid() > 1000) {
													sent.setstatus(Customer.Status.SILVER);

													System.out.print("Congratulations! You have been upgraded ");
													System.out.print("to a SILVER MEMBER! You have earned a");
													System.out.println("discount of 10% on all purchases.");
													System.out.println("You need to spend " + (5000 - sent.getpaid())
															+ " more TL to become a GOLDEN MEMBER. ");

													

												}

												else {
													System.out.println("You need to spend " + (1000 - sent.getpaid())
															+ " more TL to become a SILVER MEMBER. ");
												}
											}

										} else if (sent.getstatus() == Customer.Status.SILVER) {

											if (sent.getbalance() < (sum - sum * (0.01))) {
												System.out.println("Order could not be placed. Insufficient funds.");
												break;

											}

											else if (sent.getbalance() > sum - sum * (0.01)) {
												sent.setBalance(sent.getbalance() - sum - sum * (0.01));
												sent.setpaid(sent.getpaid() + sum - sum * (0.01));
												for (Integer alinacakler : alinacaklar) {
													for (Items urunlist : itemsList) {
														if (urunlist.getID() == alinacakler) {	
															if(urunlist.gettyp().equals("DESKTOP")){
																amount_DESKTOP--;
															}
															if(urunlist.gettyp().equals("LAPTOP")){
																amount_LAPTOP--;
															}
															if(urunlist.gettyp().equals("TABLET")){
																amount_TABLET--;
															}
															if(urunlist.gettyp().equals("TV")){
																amount_TV--;
															}
															if(urunlist.gettyp().equals("SMARTPHONE")){
																amount_SMARTPHONE--;
															}
															if(urunlist.gettyp().equals("BOOK")){
																amount_BOOK--;
															}
															if(urunlist.gettyp().equals("CDDVD")){
																amount_CDDVD--;
															}
															if(urunlist.gettyp().equals("HAIRCARE")){
																amount_HAIRCARE--;
															}
															if(urunlist.gettyp().equals("SKINCARE")){
																amount_SKINCARE--;
															}
															if(urunlist.gettyp().equals("PERFUME")){
																amount_PERFUME--;
															}
															
														}
													  }
													}
												System.out.println(
														"Done! Your order will be delivered as soon as possible. Thank you!");
												CART_List.remove(cart);
												if (sent.getpaid() > 5000) {
													sent.setstatus(Customer.Status.GOLDEN);

													System.out.print("Congratulations! You have been upgraded ");
													System.out.print("to a GOLDEN MEMBER! You have earned a");
													System.out.println("discount of 15% on all purchases.");

												

												}

												else {
													System.out.println("You need to spend " + (5000 - sent.getpaid())
															+ " more TL to become a GOLDEN MEMBER. ");
							

												}

											}

										} else if (sent.getstatus() == Customer.Status.GOLDEN) {
											if (sent.getbalance() < (sum - sum * (0.15))) {
												System.out.println("Order could not be placed. Insufficient funds.");
												break;
											}
											else if(sent.getbalance() > (sum - sum * (0.15))){
												
												for (Integer alinacakler : alinacaklar) {
													for (Items urunlist : itemsList) {
														if (urunlist.getID() == alinacakler) {	
															if(urunlist.gettyp().equals("DESKTOP")){
																amount_DESKTOP--;
															}
															if(urunlist.gettyp().equals("LAPTOP")){
																amount_LAPTOP--;
															}
															if(urunlist.gettyp().equals("TABLET")){
																amount_TABLET--;
															}
															if(urunlist.gettyp().equals("TV")){
																amount_TV--;
															}
															if(urunlist.gettyp().equals("SMARTPHONE")){
																amount_SMARTPHONE--;
															}
															if(urunlist.gettyp().equals("BOOK")){
																amount_BOOK--;
															}
															if(urunlist.gettyp().equals("CDDVD")){
																amount_CDDVD--;
															}
															if(urunlist.gettyp().equals("HAIRCARE")){
																amount_HAIRCARE--;
															}
															if(urunlist.gettyp().equals("SKINCARE")){
																amount_SKINCARE--;
															}
															if(urunlist.gettyp().equals("PERFUME")){
																amount_PERFUME--;
															}
															
														}
													  }
													}
											sum -= sum * (0.15);
											
											CART_List.remove(cart);
											
											System.out.println(
													"Done! Your order will be delivered as soon as possible. Thank you!");
											sent.setBalance(sent.getbalance() - sum);

										}
									}
										DateFormat dateFormat = new SimpleDateFormat("dd MM mm HH:mm:ss yyyy");
										Date today = new Date();
										String reportDate = dateFormat.format(today);
										
										Order a=new Order(reportDate,sum, cartlar.getItemIDC(),Integer.parseInt(splitted[1]));
										Order_List.add(a);
									}
									
								}

							}
						}
					}
					if (custo == 0) {
						System.out.println("No customer with ID number " + splitted[1] + " exists!");
						System.out.println();
					}
					if (paso == 0 & custo == 1) {
						System.out.println("Order could not be placed. Invalid password.");
						
					}
					if (paso == 1 & urun_var == 0) {
						System.out.println("You should add some items to your cart before order request!");
						
					}
				}

			}

			scanner1.close();
		}

		catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
		}

	}

}
