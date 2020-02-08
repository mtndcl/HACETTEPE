import java.awt.EventQueue;






import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JScrollBar;



















import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;





import javax.swing.JTable;





import java.util.ArrayList;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.BorderLayout;




public class Profilpage {

	private JFrame frame;
	private JTextField searhfriend;
	private JButton btncreatepost;
	private JButton btnlogout;
	private JButton btnremoveuser;
	private JLabel lbluserphoto;
	private JLabel lblusername;
	private JLabel lblshool;
	private JLabel lbltakeshool;
	private JLabel lblrela;
	private JTable table;
	public static JPanel  mypostpanel;
	public static int id;
	/**
	 * Launch the application.
	 */
	public static void	Profilpage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profilpage window = new Profilpage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Profilpage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setTitle("Profil Page");
		frame.setBounds(100, 100, 821, 627);
		frame.setResizable(false);
		
		
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblsearhfriend = new JLabel("Search Friend");
		lblsearhfriend.setBounds(138, 11, 96, 23);
	
		lblsearhfriend.setForeground(Color.WHITE);
		lblsearhfriend.setBackground(Color.LIGHT_GRAY);
		String adam=null;
		for(User f: UserCollection.userlogin){
			adam=f.getUser_name();
		}
		JButton btnaddfriend = new JButton("Add Friend");
		btnaddfriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selecteduser=table.getValueAt(table.getSelectedRow(), 0).toString();
				
				String addthis=null;
        		for(User user: UserCollection.userlist){
        			if(user.getnamesurname().equals(selecteduser)){
        				addthis=user.getUser_name();
        			}
        		}
				String adam=null;
				for(User f: UserCollection.userlogin){
					adam=f.getUser_name();
				}
				for(Friends in: UserCollection.friends){
					if(in.getusername().equals(adam)){
						in.getfriend().add(addthis);
					}
				}
				for(Friends in: UserCollection.friends){
					if(in.getusername().equals(addthis)){
						in.getfriend().add(adam);
					}
				}
				btnaddfriend.setVisible(false);
				JOptionPane.showMessageDialog(frame," now your friendship began with "+selecteduser);
			}
		});
		btnaddfriend.setBounds(508, 178, 123, 23);
		frame.getContentPane().add(btnaddfriend);
		frame.getContentPane().add(lblsearhfriend);
		
		
		table = new JTable();
		table.setBounds(236, 33, 86, 90);
		table.setRowSelectionAllowed(false);
		table.setShowGrid(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		DefaultTableModel model = new DefaultTableModel(new String[] { "user"},0);
		table.setModel(model);
		
		
		
		frame.getContentPane().add(table);
		table.setVisible(false);
		searhfriend = new JTextField();
		searhfriend.setBounds(236, 11, 86, 23);
		searhfriend.addKeyListener(new KeyAdapter() {
			
			
			@Override
			
			public void keyReleased(KeyEvent e) {
				int rowCount = model.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    model.removeRow(i);
				}
				String searhfroo=searhfriend.getText().toString();
				table.setVisible(true);
				int uzunluk=searhfroo.length();
				int show=0;
				String onlineuser = null;
				for(User c: UserCollection.userlogin){
					onlineuser=c.getUser_name();
				}
				ArrayList<String> dontshow = new ArrayList<String>();
				for(Friends  blo: UserCollection.blockfriends){
					if(blo.getusername().equals(onlineuser)){
						for(String blok:  blo.getfriend()){
							dontshow.add(blok);
						}
					}
				}
				
				for(User user: UserCollection.userlist){
					int blog=0;
					for(String blollular: dontshow){
						if(blollular.equals(user.getUser_name())){
							blog=1;
							
						}
					}
					if(blog==0){
						try{
							
							if(searhfroo.equals(user.getUser_name().substring(0, uzunluk)))	{
							 model.addRow(new Object[]{ user.getnamesurname()});
							 show=1;
							}
						
							
						}catch(Exception e1){
							
						}
					}
				}
				
				
					
					
				
					
				if(searhfroo.equals("") 	|| show==0 ){
					table.setVisible(false);
				}
			}
		});
		searhfriend.setBackground(SystemColor.textHighlightText);
		frame.getContentPane().add(searhfriend);
		searhfriend.setColumns(10);
		
		btncreatepost = new JButton("Create Post");
		btncreatepost.setBounds(610, 11, 106, 23);
	
		frame.getContentPane().add(btncreatepost);
		
		btnlogout = new JButton("Logout");
		btnlogout.setBounds(726, 11, 79, 23);
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserCollection.userlogin.clear();
				frame.setVisible(false);
				loginpage b=new loginpage();
				
			}
		});
		frame.getContentPane().add(btnlogout);
		
		lbluserphoto = new JLabel("");
		lbluserphoto.setBounds(10, 55, 150, 146);
		Image icon=new ImageIcon(this.getClass().getResource("/personicon8.png")).getImage();
		lbluserphoto.setIcon(new ImageIcon(icon));
		frame.getContentPane().add(lbluserphoto);
		frame.setIconImage(new ImageIcon(getClass().getResource("personicon8.png")).getImage());

		lblusername = new JLabel();
		lblusername.setBounds(189, 177, 212, 23);
		lblusername.setFont(new Font("Tahoma", Font.BOLD, 20));
		for(User user: UserCollection.userlogin){
			lblusername.setText(user.getnamesurname());
		}
		frame.getContentPane().add(lblusername);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 212, 212, 209);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Information");
		lblNewLabel.setBounds(0, 0, 140, 14);
		panel.add(lblNewLabel);
		
		JLabel lbldateofbirth = new JLabel("Date of Birth");
		lbldateofbirth.setHorizontalAlignment(SwingConstants.CENTER);
		lbldateofbirth.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbldateofbirth.setBounds(26, 25, 160, 20);
		panel.add(lbldateofbirth);
		
		JLabel lblanswerdate = new JLabel("");
		lblanswerdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblanswerdate.setBounds(26, 47, 170, 20);
		panel.add(lblanswerdate);
		
		lblshool = new JLabel("School Graduated");
		lblshool.setHorizontalAlignment(SwingConstants.CENTER);
		lblshool.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblshool.setBounds(26, 66, 160, 26);
		panel.add(lblshool);
		
		lbltakeshool = new JLabel();
		lbltakeshool.setHorizontalAlignment(SwingConstants.CENTER);
		lbltakeshool.setBounds(26, 92, 170, 20);
		panel.add(lbltakeshool);
		
		lblrela = new JLabel("Relationship Status ");
		lblrela.setHorizontalAlignment(SwingConstants.CENTER);
		lblrela.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblrela.setBounds(26, 113, 170, 20);
		panel.add(lblrela);
		
		
		
		String[]  comboboxoption={"Single", "Complicated", "Divorced", "In relationship"};
		JComboBox comboB = new JComboBox();
		comboB.setModel(new DefaultComboBoxModel(comboboxoption ));
		
		
		comboB.setBounds(26, 144, 160, 20);
		
		
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String relationship=comboB.getSelectedItem().toString();
				for(User user: UserCollection.userlogin){
					
					user.setrelationshipstatus(relationship);
					JOptionPane.showMessageDialog(frame, "your information updated");
				}
			}
		});
		btnupdate.setBounds(26, 175, 94, 23);
		panel.add(btnupdate);
		
		JLabel lblNewLabel_1 = new JLabel("Friends");
		lblNewLabel_1.setBounds(10, 441, 57, 14);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel_1);
		
		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setBounds(72, 441, 72, 14);
		
		frame.getContentPane().add(rdbtnNormal);
		
		JRadioButton rdbtnblocked = new JRadioButton("Blocked");
		rdbtnblocked.setBounds(162, 441, 72, 14);
		
		frame.getContentPane().add(rdbtnblocked);
		
		JPanel panelshowfriend = new JPanel();
		panelshowfriend.setBounds(20, 466, 214, 121);
		panelshowfriend.setBackground(SystemColor.textHighlightText);
		panelshowfriend.setBorder(new LineBorder(SystemColor.textText, 2));
		frame.getContentPane().add(panelshowfriend);
		panelshowfriend.setLayout(null);
		
		
		
		
		DefaultListModel<String> model1 = new DefaultListModel<>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 180, 86);
		panelshowfriend.add(scrollPane);
		JList<String> showfrienlist = new JList<>( model1 );
		scrollPane.setViewportView(showfrienlist);
		showfrienlist.setVisibleRowCount(10);
		showfrienlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showfrienlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		
		showfrienlist.addListSelectionListener(
				new ListSelectionListener() {
					
					
					public void valueChanged(ListSelectionEvent event) {
						String selected = showfrienlist.getSelectedValue();
					
						btnremoveuser.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								setShowfrienslist(selected,rdbtnNormal,rdbtnblocked,showfrienlist,model1);
								
								
							}
						});
						
						
					}
				}
				
				);		
		
		btnremoveuser = new JButton("Remove Selected User");
		btnremoveuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnremoveuser.setBounds(0, 103, 214, 18);
		panelshowfriend.add(btnremoveuser);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNormal);
		group.add(rdbtnblocked);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnHome);
		
		JButton statublock = new JButton("");
		statublock.setBounds(641, 178, 164, 23);
		frame.getContentPane().add(statublock);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(290, 211, 515, 376);
		frame.getContentPane().add(tabbedPane);
		
		mypostpanel = new JPanel();
		mypostpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("post's", null, mypostpanel, null);
		tabbedPane.setEnabledAt(0, true);
		 
		JPanel myfriendpostpanel = new JPanel();
		tabbedPane.addTab("friend post", null, myfriendpostpanel, null);
//		JScrollPane scroll = new JScrollPane(mypostpanel);
//		 JScrollPane scroll2 = new JScrollPane(myfriendpostpanel);
		btnHome.setVisible(false);
		statublock.setVisible(false);
		rdbtnNormal.addActionListener(new ActionListener() {
			
			
		        public void actionPerformed(ActionEvent e) {
		        	model1.removeAllElements();
		        	if(btnupdate.isVisible()==true){
		        	for(User user : UserCollection.userlogin){
		        		 for(Friends fro: UserCollection.friends){  
		        			 if(user.getUser_name().equals(fro.getusername())){
		        				 model1.removeAllElements();
		        				 for(int i=0; i<fro.getfriend().size();i++){
		        					 model1.addElement( fro.getfriend().get(i) );
		        				 }
		        				 if(model1.size()==0){
		        					 model1.removeAllElements();
		        				 }
		        			 }
		        			   
		        		}  
		        	}
		        	
		        	}else{
		        		model1.removeAllElements();
		        		String selecteduser=table.getValueAt(table.getSelectedRow(), 0).toString();
		        		String dokunulsnsdsm=null;
		        		for(User user:UserCollection.userlist){
		        			if(selecteduser.equals(user.getnamesurname())){
		        				dokunulsnsdsm=user.getUser_name();
		        		}
		        			}
		        		 for(Friends fro: UserCollection.friends){  
		        			 if(dokunulsnsdsm.equals(fro.getusername())){
		        				 model1.removeAllElements();
		        				 for(int i=0; i<fro.getfriend().size();i++){
		        					 model1.addElement( fro.getfriend().get(i) );
		        					 
		        				 }
		        				 if(model1.size()==0){
		        					 model1.removeAllElements();
		        				 }
		        			 }
		        			   
		        		}
		        	}
		        	

		        }
		    });
		rdbtnblocked.addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent g) {
	        	
	        	if(btnupdate.isVisible()==true){
	        	model1.removeAllElements();
	        	for(User user : UserCollection.userlogin){
	        		 for(Friends fro: UserCollection.blockfriends){  
	        			 if(user.getUser_name().equals(fro.getusername())){
	        				 model1.removeAllElements();
	        				 for(int i=0; i<fro.getfriend().size();i++){
	        					 model1.addElement( fro.getfriend().get(i) );
	        					 
	        				 }
	        				 if(model1.size()==0){
	        					 model1.removeAllElements();
	        				 }
	        			 }
	        			   
	        		}  
	        		}
	        	}
	        	else{
	        		model1.removeAllElements();
	        		String selecteduser=table.getValueAt(table.getSelectedRow(), 0).toString();
	        		String dokunulsnsdsm=null;
	        		for(User user:UserCollection.userlist){
	        			if(selecteduser.equals(user.getnamesurname())){
	        				dokunulsnsdsm=user.getUser_name();
	        		}
	        			}
	        		 for(Friends fro: UserCollection.blockfriends){  
	        			 if(dokunulsnsdsm.equals(fro.getusername())){
	        				 model1.removeAllElements();
	        				 for(int i=0; i<fro.getfriend().size();i++){
	        					 model1.addElement( fro.getfriend().get(i) );
	        					 
	        				 }
	        				 if(model1.size()==0){
	        					 model1.removeAllElements();
	        				 }
	        			 }
	        			   
	        		}
	        		 
	        	}
	        
	        	

	        }
	    });
				
				
	
		for(User user: UserCollection.userlogin){
			lblanswerdate.setText(user.getbirth_date());
			lbltakeshool.setText(user.getSchool());
			
			
			for(int i=0;i<comboboxoption.length;i++){
				if(user.getrelationshipstatus().equals(comboboxoption[i])){
					comboB.setSelectedIndex(i);
				}
			}
			
		
			
		}
		panel.add(comboB);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if (event.getValueIsAdjusting() == true) {
	        		btncreatepost.setVisible(false);
	        		table.setVisible(false);
	        		btnremoveuser.setVisible(false);
	        		model1.removeAllElements();
	        		statublock.setText("block this user");
	        		String selecteduser=table.getValueAt(table.getSelectedRow(), 0).toString();
	        		for(User user: UserCollection.userlist){
	        			
	        			if(user.getnamesurname().equals(selecteduser)){
	        				lblanswerdate.setText(user.getbirth_date());
	        				lbltakeshool.setText(user.getSchool());
	        				btnupdate.setVisible(false);
	        				lblusername.setText(user.getnamesurname());
	        				btnHome.setVisible(true);
	        				
	        				for(int i=0;i<comboboxoption.length;i++){
	        					if(user.getrelationshipstatus().equals(comboboxoption[i])){
	        						comboB.setSelectedIndex(i);
	        					}
	        				}
	        				
	        				deletelabals(myfriendpostpanel);
	        				deletelabals(mypostpanel);
	        				comboB.setEnabled(false);

	        			
	        				getpanel().repaint();
	        				showmymessage(mypostpanel, user.getUser_name());
	        				showmyfriendmessage(myfriendpostpanel, user.getUser_name());
	        				setShowfrienslist(user.getnamesurname(),rdbtnNormal,rdbtnblocked,showfrienlist,model1);
	        				
	        				for(User online : UserCollection.userlogin){
	        					for(Friends fro: UserCollection.friends){
	        						if(online.getUser_name().equals(fro.getusername())){
	        							
	        							for(String arka: fro.getfriend()){
	        								if(arka.equals(user.getUser_name())){
	        									
	        									
	        									statublock.setText("block this user");
	        								}
	        							}
	        							
	        							
	        						}
	        					}
	        				}
	        				btnaddfriend.setVisible(true);
	        				for(User online : UserCollection.userlogin){
	        					for(Friends fro: UserCollection.friends){
	        						if(online.getUser_name().equals(fro.getusername())){
	        							int  a=0;
	        							for(String adam: fro.getfriend()){
	        								if(adam.equals(user.getUser_name())){
	        									a=1;
	        									btnaddfriend.setVisible(false);
	        								}
	        							}
	        							if(a==0){
	        								btnaddfriend.setVisible(true);
	        							}
	        						}
	        					}}
							statublock.setVisible(true);
							
										
								
						
	        				
	        			}
	       
	        		}
	        	}
	        	}
	        	
	    });
		
		statublock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selecteduser=table.getValueAt(table.getSelectedRow(), 0).toString();
//				if(statublock.getText().equals("Add Friend")){
//					String	onlineadam=null;
//					for(User online: UserCollection.userlogin){
//						onlineadam=online.getUser_name();
//					}
//					for(Friends  friend: UserCollection.friends){
//						if(friend.getusername().equals(onlineadam)){
//							friend.getfriend().add(selecteduser);
//							statublock.setText("block this user");
//								
//							
//						}
//						
//					}
//						
//				}
				if(statublock.getText().equals("block this user")){
					
					
				String	onlineadam=null;
				for(User online: UserCollection.userlogin){
					onlineadam=online.getUser_name();
				}
				String	tiklananadam=null;
				for(User seciliadam: UserCollection.userlist)	{
					if(seciliadam.getnamesurname().equals(selecteduser)){
						tiklananadam=seciliadam.getUser_name();
					}
				}
				String  line="Block	"+onlineadam+"	"+tiklananadam;
				int listeyoksa=0;
				for(Friends bloklulariyeri: UserCollection.blockfriends){
					
					if(bloklulariyeri.getusername().equals(onlineadam)){
						listeyoksa=1;
						bloklulariyeri.getfriend().add(tiklananadam);
						
					}
					
				}
				if(listeyoksa==0){
					UserCollection.blockfiend(line);
					line=null;
				}
				for(Friends arkadaslarinyeri: UserCollection.friends){
					if(arkadaslarinyeri.getusername().equals(onlineadam)){
						arkadaslarinyeri.getfriend().remove(tiklananadam);
					}
				}
				
				statublock.setText("unblock this user");
				
				
			}
			else if(statublock.getText().equals("unblock this user")){
				
				String	onlineadam=null;
				for(User online: UserCollection.userlogin){
					onlineadam=online.getUser_name();
				}
				String	tiklananadam=null;
				for(User seciliadam: UserCollection.userlist)	{
					if(seciliadam.getnamesurname().equals(selecteduser)){
						tiklananadam=seciliadam.getUser_name();
					}
				}
				for(Friends bloklulariyeri: UserCollection.blockfriends){
					if(bloklulariyeri.getusername().equals(onlineadam)){
						bloklulariyeri.getfriend().remove(tiklananadam);
					}
				}
				
				statublock.setText("block this user");
				}
				
			}
		});
		//when clicked home button
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(User online: UserCollection.userlogin){
					btnHome.setVisible(false);
					lblanswerdate.setText(online.getbirth_date());
					lbltakeshool.setText(online.getSchool());
					lblusername.setText(online.getnamesurname());
					statublock.setVisible(false);
					btnupdate.setVisible(true);
					btncreatepost.setVisible(true);
					btnremoveuser.setVisible(true);
					comboB.setEnabled(true);
					btnaddfriend.setVisible(false);
					deletelabals(myfriendpostpanel);
    				deletelabals(mypostpanel);
    				showmymessage(mypostpanel,online.getUser_name());
    				showmyfriendmessage(myfriendpostpanel, online.getUser_name());
					for(int i=0;i<comboboxoption.length;i++){
						if(online.getrelationshipstatus().equals(comboboxoption[i])){
							comboB.setSelectedIndex(i);
						}
					}
					
				}
				
				
			}});
		
		showmymessage(mypostpanel,adam);
		mypostpanel.setLayout(null);
		showmyfriendmessage(myfriendpostpanel,adam);
		myfriendpostpanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		panel_1.setBounds(0, 0, 815, 44);
		frame.getContentPane().add(panel_1);
		
	
	
		btncreatepost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				AddVideopost ps=new AddVideopost();
				
				//ps.AddtextPost();
				
			}
		});
		btnaddfriend.setVisible(false);
		frame.setVisible(true);
	
	}
	public void showmyfriendmessage(JPanel myfriendpostpanel,String adam) {
		int location=0;
		
			for(Friends   arkadaslarim: UserCollection.friends){
				if(adam.equals(arkadaslarim.getusername())){
					for(String    bunlararkadasim: arkadaslarim.getfriend()){
						for(TextPost postler: UserCollection.posts){
							if(bunlararkadasim.equals(postler.getUsername())){
								
								
								String arkadasimiadiyadai=null;
								for(User aa: UserCollection.userlist){
									if(aa.getUser_name().equals(bunlararkadasim)){
										arkadasimiadiyadai=aa.getnamesurname();
									}
								}
								JLabel mesagg=new JLabel();
								mesagg.setBounds(10,location, 480, 84);
					
								Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
								mesagg.setBorder(border);
								mesagg.setFont(new Font(mesagg.getName(), Font.PLAIN, 10));
								String  usertag="Tagged users :";
								int stop=0;
								for(User   taggg:  postler.getTaggedfriend()){
									stop++;
									if(postler.getTaggedfriend().size()==stop){
										
										usertag=usertag+"  "+taggg.getnamesurname();
									}
									else{
										usertag=usertag+"  "+taggg.getnamesurname()+" , ";
										
									}
									
								}
								mesagg.setFont(new Font("T", Font.PLAIN, 50));
								mesagg.setVerticalAlignment(SwingConstants.TOP);
								if(postler.getClass().equals(TextPost.class)){
								String text = "<html>"
										+ "<p style=\"font-family:Arial;font-size:10px;color:black;\"> "+ arkadasimiadiyadai + " has shared"+"</p>"
										+ ""
										+ "<span style=\"font-family:Arial;font-size:20px;color:black;\">  T  </span>"+
											
											
											 "<span style=\"font-family:Arial;font-size:10px;color:blue;\">"+postler.getText()+"</span>"
											+ "<p style=\"font-family:Arial;font-size:10px;color:black;\">"+usertag+"</p>"
											+"</html>";
								mesagg.setText(text);
								mesagg.setFont(new Font("T", Font.PLAIN, 10));
								
								myfriendpostpanel.add(mesagg);
								
								location=location+80;
								}
								if(postler.getClass().equals(ImagePost.class)){
									String text ="<html>"
											+ "<p style=\"font-family:Arial;font-size:10px;color:black;\"> "+ arkadasimiadiyadai + " has shared"+"</p>"
											+ ""
											+ "<span style=\"font-family:Arial;font-size:20px;color:black;\">  I  </span>"+
												
												
												 "<span style=\"font-family:Arial;font-size:10px;color:blue;\">"+postler.getText()+"</span>"
												+ "<p style=\"font-family:Arial;font-size:10px;color:black;\">"+usertag+"</p>"
												+"</html>";
									mesagg.setText(text);
									mesagg.setFont(new Font("T", Font.PLAIN, 10));
									
									myfriendpostpanel.add(mesagg);
									
									location=location+80;
									}
								if(postler.getClass().equals(VideoPost.class)){
									String text = "<html>"
											+ "<p style=\"font-family:Arial;font-size:10px;color:black;\"> "+ arkadasimiadiyadai + " has shared"+"</p>"
											+ ""
											+ "<span style=\"font-family:Arial;font-size:20px;color:black;\">  V  </span>"+
												
												
												 "<span style=\"font-family:Arial;font-size:10px;color:blue;\">"+postler.getText()+"</span>"
												+ "<p style=\"font-family:Arial;font-size:10px;color:black;\">"+usertag+"</p>"
												+"</html>";
									mesagg.setText(text);
									mesagg.setFont(new Font("T", Font.PLAIN, 10));
									
									myfriendpostpanel.add(mesagg);
									
									location=location+80;
									}
								
							}
						}
					}
				}
			
		}
		
	}
	
	
	
	public static void showmymessage(JPanel mypostpanel,String adam) {
		int asagiyukali=0;
		//where  show post of users   //  need refresh  post when user add a message  and if do not tag any user do not show tagged users text
		
			for(TextPost  post: UserCollection.posts){
				if(adam.equals(post.getUsername())){
					
					Dimension d = new Dimension(100,25);
					
					JButton  btntag=new JButton();
					btntag.setBounds(375, 50, 20, 20);
					btntag.setText("Tag Friend");
					btntag.setSize(d);
					btntag.setName(Integer.toString(post.getid()));
					
					
					btntag.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							btntag.setText("Clicked");
							ShowtagableFriend a=new ShowtagableFriend();
						//	ShowtagableFriend b=new ShowtagableFriend();
							
						}});
					
					
					String only=null;
					for(User  s:UserCollection.userlogin){
						only=s.getUser_name();
					}
					
					if(post.getClass().equals(TextPost.class)){
						
						
						String  usertag="Tagged users :";
						int stop=0;
						for(User   taggg:  post.getTaggedfriend()){
							stop++;
							if(post.getTaggedfriend().size()==stop){
								
								usertag=usertag+"  "+taggg.getnamesurname();
							}
							else{
								usertag=usertag+"  "+taggg.getnamesurname()+" , ";
								
							}
							
						}
						JLabel mesagg=new JLabel(post.getUsername());
						
						mesagg.setBounds(10,asagiyukali, 480, 84);
			
						Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
						mesagg.setBorder(border);
						mesagg.setFont(new Font(mesagg.getName(), Font.PLAIN, 10));
						mesagg.setFont(new Font("T", Font.PLAIN, 50));
						mesagg.setVerticalAlignment(SwingConstants.TOP);
						String text = "<html><span style=\"font-family:Arial;font-size:30px;color:black;\">  T  </span>"
								
								
								+ "<span style=\"font-family:Arial;font-size:8px;color:blue;\">"+post.getText()+"</span>"
								+ "<p style=\"font-family:Arial;font-size:9px;color:black;\">"+usertag+"</p>"
								+"</html>";
					
							mesagg.setText(text);
						
						
						mesagg.setFont(new Font("T", Font.PLAIN, 10));
						
						if(adam.equals(only)){
							mesagg.add(btntag);
						}
						mypostpanel.add(mesagg);
						
						asagiyukali=asagiyukali+80;
					}
					if(post.getClass().equals(ImagePost.class)){
						
						
						
						String  usertag="Tagged users :";
						int stop=0;
						for(User   taggg:  post.getTaggedfriend()){
							stop++;
							if(post.getTaggedfriend().size()==stop){
								
								usertag=usertag+"  "+taggg.getnamesurname();
							}
							else{
								usertag=usertag+"  "+taggg.getnamesurname()+" , ";
								
							}
							
						}
						JLabel mesagg=new JLabel(post.getUsername());
						
						mesagg.setBounds(10,asagiyukali, 480, 84);
			
						Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
						mesagg.setBorder(border);
						mesagg.setFont(new Font(mesagg.getName(), Font.PLAIN, 10));
						mesagg.setFont(new Font("T", Font.PLAIN, 50));
						mesagg.setVerticalAlignment(SwingConstants.TOP);
						mesagg.setFont(new Font("I", Font.PLAIN, 50));
						mesagg.setVerticalAlignment(SwingConstants.TOP);
						String text = "<html><span style=\"font-family:Arial;font-size:30px;color:black;\">  I  </span>"
								
								
								+ "<span style=\"font-family:Arial;font-size:8px;color:blue;\">"+post.getText()+"</span>"
								+ "<p style=\"font-family:Arial;font-size:9px;color:black;\">"+usertag+"</p>"
								+"</html>";
						mesagg.setText(text);
						mesagg.setFont(new Font("I", Font.PLAIN, 10));
						if(adam.equals(only)){
							mesagg.add(btntag);
						}
						mypostpanel.add(mesagg);
						
						asagiyukali=asagiyukali+80;
					}
					if(post.getClass().equals(VideoPost.class)){
						
						
						String  usertag="Tagged users :";
						int stop=0;
						for(User   taggg:  post.getTaggedfriend()){
							stop++;
							if(post.getTaggedfriend().size()==stop){
								
								usertag=usertag+"  "+taggg.getnamesurname();
							}
							else{
								usertag=usertag+"  "+taggg.getnamesurname()+" , ";
								
							}
							
						}
						JLabel mesagg=new JLabel(post.getUsername());
						
						mesagg.setBounds(10,asagiyukali, 480, 84);
			
						Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
						mesagg.setBorder(border);
						mesagg.setFont(new Font(mesagg.getName(), Font.PLAIN, 10));
						mesagg.setFont(new Font("T", Font.PLAIN, 50));
						mesagg.setVerticalAlignment(SwingConstants.TOP);
						mesagg.setFont(new Font("V", Font.PLAIN, 50));
						mesagg.setVerticalAlignment(SwingConstants.TOP);
						String text = "<html><span style=\"font-family:Arial;font-size:30px;color:black;\">  V  </span>"
								
								
								+ "<span style=\"font-family:Arial;font-size:8px;color:blue;\">"+post.getText()+"</span>"
								+ "<p style=\"font-family:Arial;font-size:9px;color:black;\">"+usertag+"</p>"
								+"</html>";
						mesagg.setText(text);
						mesagg.setFont(new Font("V", Font.PLAIN, 10));
						if(adam.equals(only)){
							mesagg.add(btntag);
						}
						mypostpanel.add(mesagg);
						
						asagiyukali=asagiyukali+80;
					}
				}
			}
		
		
	}
	public void setShowfrienslist(String selected,JRadioButton rdbtnNormal,JRadioButton  rdbtnblocked,JList<String>showfrienlist,DefaultListModel<String>model1){

		for (User user: UserCollection.userlist ){
			
			if(user.getUser_name().equals(selected)){
				int selectedIndex = showfrienlist.getSelectedIndex();
			
				if (selectedIndex != -1) {
					model1.remove(selectedIndex);
					JOptionPane.showMessageDialog(frame, "User deleted from yout list");
				}
				if (rdbtnNormal.isSelected()==true) {
				
				for(User online: UserCollection.userlogin){
					for(Friends fro: UserCollection.friends){
						if(online.getUser_name().equals(fro.getusername())){
							for(int i=0;i<fro.getfriend().size();i++){
								if(selected.equals(fro.getfriend().get(i))){
									fro.getfriend().remove(selected);
								}
							}
						}
					}
					}
				 }
				 if (rdbtnblocked.isSelected()==true) {
						
						for(User online: UserCollection.userlogin){
							for(Friends fro: UserCollection.blockfriends){
								if(online.getUser_name().equals(fro.getusername())){
									for(int i=0;i<fro.getfriend().size();i++){
										if(selected.equals(fro.getfriend().get(i))){
											fro.getfriend().remove(selected);
										}
									}
								}
							}
							}
						 }
				
				break;
				
			}
			
		}
	}
	public static void deletelabals(JPanel mypostpanel){
		Component[] components = mypostpanel.getComponents();
		
		//JLabel lbl = (JLabel)mypostpanel.getComponent(0);
		for(int i=0;i<components.length;i++){
			try{
			JLabel lbl = (JLabel)mypostpanel.getComponent(i);
			
			mypostpanel.removeAll();
			lbl.setText("");
			
			JButton btn = (JButton)lbl.getComponent(0);
			btn.setVisible(false);
			}catch(Exception f){
				
			}
		}
		
	}
	public static JPanel  getpanel(){
		
		
		return  mypostpanel;
	}
	
	public static void tagfriend(String selectusername,JPanel mypostpanel){
		
		Component[] components = mypostpanel.getComponents();
		for(int i=0;i<components.length;i++){
			try{
			JLabel lbl = (JLabel)mypostpanel.getComponent(i);
			
			
			JButton btn = (JButton)lbl.getComponent(0);
			User  eklenecek=null;
			for(User  a: UserCollection.userlist){
				if(a.getnamesurname().equals(selectusername)){
					eklenecek=a; 
					
				}
			}
		
		
			for(TextPost post: UserCollection.posts){
				if(Integer.parseInt(btn.getName())==post.getid() && btn.getText().equals("Clicked")){
						post.getTaggedfriend().add(eklenecek);
						btn.setText("Tag Friend");
						break;
						
					
				
						
			}
			}
			
			btn.setVisible(false);
			}catch(Exception f){
				
			}
		}
	}
}
