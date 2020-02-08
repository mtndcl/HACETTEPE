import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.FlowLayout;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JScrollBar;
import java.awt.BorderLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class loginpage {

	private static JFrame frame;
	private JTextField txtUsername;
	private JPasswordField textpassword;
	public static JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void Loginpage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginpage window = new loginpage();
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
	public loginpage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(100, 100, 532, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Facebook Login Page");
		JLabel lblusername = new JLabel("Username");
		lblusername.setBounds(324, 40, 86, 22);
		frame.getContentPane().add(lblusername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(324, 73, 86, 22);
		frame.getContentPane().add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(420, 41, 86, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		textpassword = new JPasswordField();
		textpassword.setBounds(420, 74, 86, 20);
		frame.getContentPane().add(textpassword);
		textpassword.setColumns(10);

		JLabel logofacebook = new JLabel("");
		logofacebook.setFont(logofacebook.getFont().deriveFont(logofacebook.getFont().getSize() + 1f));
		Image icon = new ImageIcon(this.getClass().getResource(
				"/Facebooklogo1.png")).getImage();
		logofacebook.setIcon(new ImageIcon(icon));
		logofacebook.setBounds(10, 11, 304, 128);
		frame.getContentPane().add(logofacebook);

		JButton btnnewuser = new JButton("New User");
		btnnewuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Createusers nw = new Createusers();
				frame.setVisible(false);

			}
		});
		btnnewuser.setBounds(389, 248, 117, 23);
		frame.getContentPane().add(btnnewuser);

		JLabel lblRegistered = new JLabel("Registered  Users");
		lblRegistered.setBounds(10, 159, 114, 22);
		frame.getContentPane().add(lblRegistered);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(SystemColor.text);
		panel.setBounds(10, 185, 496, 61);
		frame.getContentPane().add(panel);

		DefaultListModel<String> model = new DefaultListModel<>();
		panel.setLayout(new BorderLayout(0, 0));
		JList<String> list = new JList<>(model);
		list.setSelectedIndex(5);
		list.setVisibleRowCount(2);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		panel.add(list);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setMinimum(100);
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		panel.add(scrollBar, BorderLayout.SOUTH);
		for (User user : UserCollection.userlist) {
			model.addElement(user.getUser_name());

		}

		JButton btnremoveuser = new JButton("Remove User");
		btnremoveuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
				int selectedOption = JOptionPane.showConfirmDialog(
						null, "Are you sure delete " +list.getSelectedValue()+
								" ?", "Choose",
						JOptionPane.YES_NO_OPTION);
				
				if (selectedOption == JOptionPane.YES_OPTION) {
					Iterator<TextPost> itr = UserCollection.posts
							.iterator();
					while (itr.hasNext()) {
						int go = 1;
						TextPost number = itr.next();

						if (list.getSelectedValue().equals(number.getUsername())
								&& go == 1) {

							go = 0;
							itr.remove();
//							System.out
//									.println("bu kullanicinin bu mesai  :"
//											+ list.getSelectedValue()
//											+ "  "
//											+ number.getText()
//											+ "  silindi");

						}

					}

					for (User user : UserCollection.userlist) {

						for (Friends fro : UserCollection.friends) {

							if (user.getUser_name().equals(
									fro.getusername())) {
								for (String arkadslisteleri : fro
										.getfriend()) {

									if (list.getSelectedValue()
											.equals(arkadslisteleri)) {

										fro.getfriend().remove(
												list.getSelectedValue());
										
										break;
									}
								}
							}
						}
					}

					for (User user : UserCollection.userlist) {

						for (Friends fro : UserCollection.blockfriends) {

							if (user.getUser_name().equals(
									fro.getusername())) {
								for (String arkadslisteleri : fro
										.getfriend()) {

									if (list.getSelectedValue()
											.equals(arkadslisteleri)) {

										fro.getfriend().remove(
												list.getSelectedValue());
										
										break;
									}
								}
							}
						}
					}
					for (TextPost post : UserCollection.posts) {
						for (User etikenlenen : post
								.getTaggedfriend()) {
							if (list.getSelectedValue().equals(etikenlenen
									.getUser_name())) {
								post.getTaggedfriend().remove(
										etikenlenen);
								

								break;
							}
						}
					}
					for (User user : UserCollection.userlist) {

						if (user.getUser_name().equals(list.getSelectedValue())) {
							int selectedIndex = list
									.getSelectedIndex();
							
							if (selectedIndex != -1) {
								model.remove(selectedIndex);
								UserCollection.userlist
										.remove(user);
								

								break;
							}

						}

					}
				
				
				}
				
				if (selectedOption == JOptionPane.NO_OPTION){
					
				}
					}catch(Exception a){
					
				}
			}
		});
		btnremoveuser.setBounds(10, 248, 127, 23);
		frame.getContentPane().add(btnremoveuser);
		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {

				if (event.getValueIsAdjusting() == true) {
				
					String selected = list.getSelectedValue();
					
					for (User user : UserCollection.userlist) {

						if (user.getUser_name().equals(selected)) {
							txtUsername.setText(user.getUser_name());
							textpassword.setText(user.getPassword());
							break;

						}
					}

				

				}
			}
		}

		);

		JButton btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int okey = 0;
				if (UserCollection.userlogin.size() == 0) {
					for (User user : UserCollection.userlist) {
						try{
						if (user.getUser_name().equals(txtUsername.getText())) {

							if (user.getPassword().equals(
									textpassword.getText())) {
								okey = 1;
								UserCollection.userlogin.add(user);
								Profilpage profilpage = new Profilpage();
								frame.setVisible(false);

								break;
							}
						}
					}catch (Exception e) {
						
					}

					}

					if (okey == 0) {
						JOptionPane.showMessageDialog(frame, "Username or Password is wrong");
					}
				} else {
					JOptionPane.showMessageDialog(frame,
							"Please log out and try again");
				}

			}
		});
		btnlogin.setBounds(417, 118, 89, 23);
		frame.getContentPane().add(btnlogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(0, 0, 526, 14);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("System");
		label.setBounds(0, 2, 280, 12);
		label.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 11));
		label.setBackground(SystemColor.activeCaption);
		panel_1.add(label);
		frame.setVisible(true);

	}
	public static JPanel  getloginpagepanel(){
		return panel;
		
	}
	public static JFrame  getloginpageframe(){
	return frame;
	
}
}


